package com.pokemones.app;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeletePokemonActivity extends AppCompatActivity {
    private EditText etId;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pokemon);

        etId = findViewById(R.id.etId);
        dbHelper = new DBHelper(this);

        findViewById(R.id.btnEliminar).setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int rowsDeleted = db.delete(PokemonContract.PokemonEntry.TABLE_NAME,
                    PokemonContract.PokemonEntry._ID + " = ?", new String[]{String.valueOf(id)});

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Pokémon eliminado!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "No se encontró el Pokémon.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
