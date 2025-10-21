package com.pokemones.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    ListView listView;
    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewPokemones);
        btnEditar = findViewById(R.id.btnEditar);

        mostrarPokemones();

        btnEditar.setOnClickListener(v -> mostrarPopup());
    }

    private void mostrarPokemones() {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                PokemonContract.PokemonEntry.TABLE_NAME,
                null, null, null, null, null, null
        );

        PokemonCursorAdapter adapter = new PokemonCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
    }

    private void mostrarPopup() {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_edit_delete, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(popupView).create();

        // Botón para editar Pokémon lleva a lista para seleccionar cuál editar
        popupView.findViewById(R.id.btnEditarPopup).setOnClickListener(v -> {
            startActivity(new Intent(this, SelectPokemonToUpdateActivity.class));
            dialog.dismiss();
        });

        // Botón para eliminar Pokémon lleva a lista para seleccionar cuál eliminar
        popupView.findViewById(R.id.btnEliminarPopup).setOnClickListener(v -> {
            startActivity(new Intent(this, DeletePokemonActivity.class));
            dialog.dismiss();
        });

        dialog.show();
    }
}
