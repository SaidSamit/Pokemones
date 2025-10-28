package com.pokemones.app;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class DeletePokemonActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pokemon);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewEliminar);

        mostrarPokemones();
    }
    public void cancelarAccion(android.view.View view) {
        finish(); // 🔙 Cierra la pantalla y vuelve a la anterior
    }

    private void mostrarPokemones() {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                PokemonContract.PokemonEntry.TABLE_NAME, null, null, null, null, null, null);

        PokemonCursorAdapter adapter = new PokemonCursorAdapter(this, cursor);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            dbHelper.getWritableDatabase().delete(
                    PokemonContract.PokemonEntry.TABLE_NAME,
                    PokemonContract.PokemonEntry._ID + "=?",
                    new String[]{String.valueOf(id)}
            );
            mostrarPokemones(); // refrescar lista después de eliminar
        });
    }
}
