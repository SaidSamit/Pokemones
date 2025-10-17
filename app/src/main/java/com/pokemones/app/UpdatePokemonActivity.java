package com.pokemones.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePokemonActivity extends AppCompatActivity {

    EditText editNombre;
    Spinner spinnerEditTipo;
    Button btnActualizar;
    DBHelper dbHelper;
    int pokemonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pokemon);

        editNombre = findViewById(R.id.editNombre);
        spinnerEditTipo = findViewById(R.id.spinnerEditTipo);
        btnActualizar = findViewById(R.id.btnActualizar);
        dbHelper = new DBHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pokemon_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditTipo.setAdapter(adapter);

        pokemonId = getIntent().getIntExtra("id", -1);
        cargarDatos(pokemonId);

        btnActualizar.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put(PokemonContract.PokemonEntry.COLUMN_NAME, editNombre.getText().toString().trim());
            values.put(PokemonContract.PokemonEntry.COLUMN_TYPE, spinnerEditTipo.getSelectedItem().toString().trim());
            dbHelper.getWritableDatabase().update(
                    PokemonContract.PokemonEntry.TABLE_NAME,
                    values,
                    PokemonContract.PokemonEntry._ID + "=?",
                    new String[]{String.valueOf(pokemonId)}
            );
            finish();
        });
    }

    private void cargarDatos(int id) {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                PokemonContract.PokemonEntry.TABLE_NAME,
                null,
                PokemonContract.PokemonEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_NAME));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_TYPE));
            editNombre.setText(nombre);

            ArrayAdapter adapter = (ArrayAdapter) spinnerEditTipo.getAdapter();
            int position = adapter.getPosition(tipo);
            if (position >= 0) spinnerEditTipo.setSelection(position);
        }
        cursor.close();
    }
}
