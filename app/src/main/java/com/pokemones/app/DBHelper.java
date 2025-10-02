package com.pokemones.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pokemones.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PokemonContract.PokemonEntry.TABLE_NAME + " (" +
                    PokemonContract.PokemonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PokemonContract.PokemonEntry.COLUMN_NAME + " TEXT," +
                    PokemonContract.PokemonEntry.COLUMN_TYPE + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PokemonContract.PokemonEntry.TABLE_NAME);
        onCreate(db);
    }
}
