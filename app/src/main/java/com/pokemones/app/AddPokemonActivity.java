package com.pokemones.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPokemonActivity extends AppCompatActivity {

    private EditText etNombre;
    private Spinner spinnerTipo;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        etNombre = findViewById(R.id.etNombre);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        dbHelper = new DBHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pokemon_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        findViewById(R.id.btnGuardar).setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String tipo = spinnerTipo.getSelectedItem().toString().trim();

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingresa el nombre del Pokémon", Toast.LENGTH_SHORT).show();
                return;
            }

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
