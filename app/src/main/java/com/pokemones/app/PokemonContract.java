package com.pokemones.app;

public final class PokemonContract {
    private PokemonContract() {}

    public static class PokemonEntry {
        public static final String TABLE_NAME = "pokemones";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "nombre";
        public static final String COLUMN_TYPE = "tipo";
    }
}
