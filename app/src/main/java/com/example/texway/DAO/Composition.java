package com.example.texway.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.texway.SQLiteDatabase.CompositionDatabase;

public class Composition {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "texway.db";

    // Names of column
    private static final String TABLE_NAME = "composition";
    private static final String KEY = "id";
    private static final String NAME = "name";

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
        if (!this.isInsert(this.name)){
            ContentValues values = new ContentValues();

            // add value withe the key(colomn name)
            values.put(NAME, this.name);

            // insert object into the db
            this.db.insert(TABLE_NAME, null, values);
        }

    }

    /*
     * Search if the componant is already in the DB
     */
    public boolean isInsert(String name){
        Cursor a = db.query(TABLE_NAME,new String[]{NAME}, NAME + " LIKE \"" + name + "\"",null,null,null, null);

        if (a.getCount() == 0){
            return false;
        }else{
            return true;
        }
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
