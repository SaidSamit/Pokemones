package com.pokemones.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePokemonActivity extends AppCompatActivity {
    EditText editNombre, editTipo;
    Button btnActualizar;
    DBHelper dbHelper;
    int pokemonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pokemon);

        editNombre = findViewById(R.id.editNombre);
        editTipo = findViewById(R.id.editTipo);
        btnActualizar = findViewById(R.id.btnActualizar);
        dbHelper = new DBHelper(this);

        pokemonId = getIntent().getIntExtra("id", -1);
        cargarDatos(pokemonId);

        btnActualizar.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put(PokemonContract.PokemonEntry.COLUMN_NAME, editNombre.getText().toString());
            values.put(PokemonContract.PokemonEntry.COLUMN_TYPE, editTipo.getText().toString());

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
            editNombre.setText(cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_NAME)));
            editTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_TYPE)));
        }
        cursor.close();
    }
}
