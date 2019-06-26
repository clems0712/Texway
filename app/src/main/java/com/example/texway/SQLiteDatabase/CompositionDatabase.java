package com.example.texway.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompositionDatabase extends SQLiteOpenHelper {
    public static final String KEY = "id";
    public static final String NAME = "name";

    public static final String METIER_TABLE_NAME = "Composition";
    public static final String METIER_TABLE_CREATE =
            "CREATE TABLE " + METIER_TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT);";
    public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + METIER_TABLE_NAME + ";";

    public CompositionDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(METIER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(METIER_TABLE_DROP);
        onCreate(db);
    }
}
