package com.pokemones.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPokemonActivity extends AppCompatActivity {
    private EditText etNombre, etTipo;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        dbHelper = new DBHelper(this);

        findViewById(R.id.btnGuardar).setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String tipo = etTipo.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PokemonContract.PokemonEntry.COLUMN_NAME, nombre);
            values.put(PokemonContract.PokemonEntry.COLUMN_TYPE, tipo);

            long newRowId = db.insert(PokemonContract.PokemonEntry.TABLE_NAME, null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "Pokémon guardado!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al guardar.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
