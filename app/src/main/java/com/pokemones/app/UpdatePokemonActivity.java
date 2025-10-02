package com.pokemones.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePokemonActivity extends AppCompatActivity {
    private EditText etId, etNombre, etTipo;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pokemon);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        dbHelper = new DBHelper(this);

        findViewById(R.id.btnActualizar).setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            String nombre = etNombre.getText().toString();
            String tipo = etTipo.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PokemonContract.PokemonEntry.COLUMN_NAME, nombre);
            values.put(PokemonContract.PokemonEntry.COLUMN_TYPE, tipo);

            int rowsUpdated = db.update(PokemonContract.PokemonEntry.TABLE_NAME, values,
                    PokemonContract.PokemonEntry._ID + " = ?", new String[]{String.valueOf(id)});

            if (rowsUpdated > 0) {
                Toast.makeText(this, "Pokémon actualizado!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "No se encontró el Pokémon.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
