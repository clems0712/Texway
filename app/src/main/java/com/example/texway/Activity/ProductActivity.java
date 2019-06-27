package com.example.texway.Activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.texway.DAO.Product;
import com.example.texway.R;
import com.example.texway.Util;

public class ProductActivity extends AppCompatActivity {

    Product product;
    TextView nameProduct;
    TextView marqueProduct;
    TextView scoreProduct;
    TextView compositionProduct;
    ImageView imageProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setTitle("Détail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        product = (Product) getIntent().getSerializableExtra("Product");

        nameProduct = findViewById(R.id.name_product);
        scoreProduct = findViewById(R.id.score_product);
        marqueProduct = findViewById(R.id.marque_product);
        compositionProduct = findViewById(R.id.composition);
        imageProduct = findViewById(R.id.image_product);

        nameProduct.setText(product.getName());
        marqueProduct.setText(product.getMarque());
        scoreProduct.setText("Qualité : "  + Util.getQualityByScore(product.getScore()));

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray != null) {
            product.setImage(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
            imageProduct.setImageBitmap(product.getImage());
        }

        //scrolling
        compositionProduct.setMovementMethod(new ScrollingMovementMethod());



        if (product.getComposition() != null) {
            StringBuilder compoToStr = new StringBuilder();
            for (String textile : product.getComposition()) {
                compoToStr.append(textile + "\n");
            }
            compositionProduct.setText(compoToStr);

        } else
            compositionProduct.setText("Composition inconnue");







    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
