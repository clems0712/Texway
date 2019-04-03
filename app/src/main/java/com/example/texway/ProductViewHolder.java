package com.example.texway;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ProductViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.barcode);

    }

    public void updateWithProduct(Product product){
        this.textView.setText(product.getName());
    }
}
