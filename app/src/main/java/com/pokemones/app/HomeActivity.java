package com.pokemones.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnAgregar = findViewById(R.id.btnAgregarPokemon);
        Button btnVer = findViewById(R.id.btnVerPokemones);

        btnAgregar.setOnClickListener(v ->
                startActivity(new Intent(this, AddPokemonActivity.class)));

        btnVer.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));
    }
}
