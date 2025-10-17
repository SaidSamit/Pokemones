package com.pokemones.app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

public class PokemonCursorAdapter extends CursorAdapter {

    public PokemonCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_pokemon, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNombre = view.findViewById(R.id.textNombre);
        TextView tvTipo = view.findViewById(R.id.textTipo);
        View container = view.findViewById(R.id.containerItem);

        String nombre = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_NAME));
        String tipo = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_TYPE));

        tvNombre.setText(nombre);
        tvTipo.setText(tipo);

        int bgColor = getColorForType(tipo, context);
        container.setBackgroundColor(bgColor);

        int textColor = ContextCompat.getColor(context, R.color.text_on_dark);
        tvNombre.setTextColor(textColor);
        tvTipo.setTextColor(textColor);
    }

    private int getColorForType(String tipo, Context context) {
        if (tipo == null) return ContextCompat.getColor(context, R.color.type_normal);
        tipo = tipo.toLowerCase().trim();
        switch (tipo) {
            case "fuego": return ContextCompat.getColor(context, R.color.type_fire);
            case "agua": return ContextCompat.getColor(context, R.color.type_water);
            case "eléctrico":
            case "electrico": return ContextCompat.getColor(context, R.color.type_electric);
            case "planta": return ContextCompat.getColor(context, R.color.type_grass);
            case "tierra": return ContextCompat.getColor(context, R.color.type_ground);
            case "hielo": return ContextCompat.getColor(context, R.color.type_ice);
            case "psíquico":
            case "psicologico":
            case "psic": return ContextCompat.getColor(context, R.color.type_psychic);
            case "roca": return ContextCompat.getColor(context, R.color.type_rock);
            default: return ContextCompat.getColor(context, R.color.type_normal);
        }
    }
}
