package com.example.texway.SQLiteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabase extends SQLiteOpenHelper {
    private static final String KEY = "id";
    private static final String NAME = "name";
    private static final String BARCODE = "barcode";
    private static final String MARQUE = "marque";
    private static final String SCORE = "score";
    private static final String IMAGE = "image";
    private static final int VERSION = 1;

    private static final String METIER_TABLE_NAME = "Product";
    private static final String METIER_TABLE_CREATE =
            "CREATE TABLE " + METIER_TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    BARCODE + " TEXT, " +
                    MARQUE + " TEXT, " +
                    SCORE + " INTEGER);";
                    /*IMAGE + " TEXT, " +*/
    private static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + METIER_TABLE_NAME + ";";

    public ProductDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, VERSION);
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
