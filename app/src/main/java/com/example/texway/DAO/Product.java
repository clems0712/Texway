package com.example.texway.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.texway.SQLiteDatabase.ProductDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "texway.db";

    // Names of column
    private static final String TABLE_NAME = "product";
    private static final String KEY = "id";
    private static final String NAME = "name";
    private static final String BARCODE = "barcode";
    private static final String MARQUE = "marque";
    private static final String SCORE = "score";
    private static final String IMAGE = "image";
    private static final String COMPOSITION = "id_composition";

    // Variable's names
    private int id;
    private String name;
    private String barcode;
    private String marque ;
    private transient Bitmap image;
    private List<String> composition;
    private List<String> type;
    private int score;

    private SQLiteDatabase db;

    private ProductDatabase productDatabase;

    private Context context;

    public Product(){
        this.productDatabase = new ProductDatabase(context, TABLE_NAME, null);
        this.context = context;

        this.name = null;
        this.barcode = null;
        this.image = null;
        this.marque = null;
        this.composition = null;
        this.type = null;

        /// A initialiser par compar JSON
        this.score = 0;
    }

    public Product(Context context){
        this.name = null;
        this.barcode = null;
        this.image = null;
        this.marque = null;
        this.composition = null;
        this.type = null;

        /// A initialiser par compar JSON
        this.score = 0;
    }

    public Product( Product P_product){

        this.name = P_product.name;
        this.barcode = P_product.barcode;
        this.marque = P_product.marque;
        this.image = P_product.image;
        this.composition = P_product.composition;
        this.type = P_product.type;
        /// A initialiser par compar JSON
        this.score = 0;
    }

    public void open(Context context){
        this.productDatabase = new ProductDatabase(context, TABLE_NAME, null);
        // Open the database on write mode
        this.db = this.productDatabase.getWritableDatabase();
        Log.d("db", "open: " + db.getVersion());
    }

    public void close(){
        // Closing the access to the database
        this.db.close();
    }

    public SQLiteDatabase getDb(){
        return this.db;
    }

    public void addProductDB() {
        ContentValues values = new ContentValues();
        // add value withe the key(colomn name)
        values.put(NAME, "this.name");
        values.put(BARCODE, "this.barcode");
        values.put(MARQUE, "this.marque");
        values.put(SCORE, 50);

        Log.d("db", "addProductDB: "+ this.db.getVersion());

        if (this.composition != null){
            // add all the componant to the DB
            for (int i = 0; i < this.composition.size(); i++){
                Composition compositionDB = new Composition(this.context);

                compositionDB.setName(this.composition.get(i));
                compositionDB.add();
            }
        }

        if (this.type != null){
            // add all the type of product to the DB
            for (int i = 0; i < this.type.size(); i++){
                Type typeDb = new Type(this.context);

                typeDb.setName(this.type.get(i));
                typeDb.add();
            }
        }

        // insert object into the db
        this.db.insert(TABLE_NAME, null, values);
    }

    public String getName() {
        /*Cursor cursor = db.query(TABLE_NAME,new String[]{BARCODE, NAME}, BARCODE + " LIKE \"" + this.barcode + "\"",null,null,null, null);

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();
        this.name = cursor.getString(1);*/


        return this.name;
    }

    public String getNameByID() {
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY, NAME}, KEY + " LIKE \"" + this.id + "\"",null,null,null, null);

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();
        this.name = cursor.getString(1);

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComposition() {
        return composition;
    }

    public void setComposition(List<String> composition) {
        this.composition = composition;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMarque() {
        return this.marque;
    }

    public String getMarqueById() {
        Cursor cursor = this.db.query(TABLE_NAME,new String[]{KEY, MARQUE, NAME}, KEY + " LIKE \"" + this.id + "\"",null,null,null, null);

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();
        this.marque = cursor.getString(1);

        return this.marque;
    }

    public List<Product> getAll(){
        List<Product> products = new ArrayList<>();

        // Query request
        Cursor cursor = this.db.query(TABLE_NAME,new String[]{KEY, NAME, MARQUE, SCORE}, BARCODE + " LIKE \"" + this.id + "\"",null,null,null, null);

        if (cursor.getCount() == 0){
            Log.d("db", "getAll: vide");
        }else{
            Log.d("db", "getAll: yata");
        }
        // Fill the list of products
        for (int i = 0; i < cursor.getCount(); i++){
            Product product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setMarque(cursor.getString(2));
            product.setScore(cursor.getInt(3));

            products.add(product);
        }

        return products;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
