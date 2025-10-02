package com.pokemones.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView lvPokemones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPokemones = findViewById(R.id.lvPokemones);
        dbHelper = new DBHelper(this);

        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        btnAgregar.setOnClickListener(v ->
                startActivity(new Intent(this, AddPokemonActivity.class)));

        btnActualizar.setOnClickListener(v ->
                startActivity(new Intent(this, UpdatePokemonActivity.class)));

        btnEliminar.setOnClickListener(v ->
                startActivity(new Intent(this, DeletePokemonActivity.class)));

        loadPokemones();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPokemones(); // Recarga la lista al volver de otra actividad
    }

    private void loadPokemones() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(PokemonContract.PokemonEntry.TABLE_NAME,
                null, null, null, null, null, null);

        ArrayList<String> pokemones = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_NAME));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(PokemonContract.PokemonEntry.COLUMN_TYPE));
            pokemones.add("ID: " + id + " - " + nombre + " | Tipo: " + tipo);
        }
        cursor.close();

        lvPokemones.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemones));
    }
}
