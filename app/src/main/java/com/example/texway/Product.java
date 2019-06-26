package com.example.texway;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private String name;
    private String barcode;
    private String marque ;
    private transient Bitmap image;
    private List<String> composition;
    private List<String> type;
    private int score;


    public Product( ){

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

    public String getName() {
        return name;
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
        return marque;
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
}
