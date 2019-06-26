package com.example.texway;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.texway.SQLiteDatabase.CompositionDatabase;

public class Composition {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "texway.db";

    // Names of column
    public static final String TABLE_NAME = "composition";
    public static final String KEY = "id";
    public static final String NAME = "name";

    private int id;
    private String name;

    private SQLiteDatabase db;

    private CompositionDatabase compositionDatabase;

    public Composition(Context context){
        this.compositionDatabase = new CompositionDatabase(context, TABLE_NAME, null, VERSION);
    }

    public void open(){
        // Open the database on write mode
        this.db = this.compositionDatabase.getWritableDatabase();
    }

    public void close(){
        // Closing the access to the database
        this.db.close();
    }

    public SQLiteDatabase getDb(){
        return this.db;
    }

    public void add(){
        ContentValues values = new ContentValues();

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
