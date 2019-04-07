package com.example.texway.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.texway.Product;
import com.example.texway.R;

public class ProductActivity extends AppCompatActivity {

    Product product;
    TextView nameProduct;
    TextView scoreProduct;

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

        nameProduct.setText(product.getName());
        scoreProduct.setText("Score : " + product.getScore());




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
