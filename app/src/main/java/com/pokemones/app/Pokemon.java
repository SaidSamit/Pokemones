package com.pokemones.app;

public class Pokemon {
    private int id;
    private String nombre;
    private String tipo;

    public Pokemon(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
}
