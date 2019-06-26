package com.example.texway.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabase extends SQLiteOpenHelper {
    public static final String KEY = "id";
    public static final String BARCODE = "barcode";
    public static final String MARQUE = "marque";
    public static final String IMAGE = "image";
    public static final String COMPOSITION = "id_composition";

    public static final String METIER_TABLE_NAME = "Product";
    public static final String METIER_TABLE_CREATE =
            "CREATE TABLE " + METIER_TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BARCODE + " TEXT, " +
                    MARQUE + " TEXT, " +
                    IMAGE + " TEXT, " +
                    COMPOSITION + " INTEGER);";
    public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + METIER_TABLE_NAME + ";";

    public ProductDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
