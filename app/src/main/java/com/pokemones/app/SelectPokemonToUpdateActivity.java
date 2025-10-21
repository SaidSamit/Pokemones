package com.pokemones.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class SelectPokemonToUpdateActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pokemon_update);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewSeleccionar);

        mostrarPokemones();
    }

    private void mostrarPokemones() {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                PokemonContract.PokemonEntry.TABLE_NAME, null, null, null, null, null, null);

        PokemonCursorAdapter adapter = new PokemonCursorAdapter(this, cursor);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, UpdatePokemonActivity.class);
            intent.putExtra("id", (int) id);
            startActivity(intent);
        });
    }
}
