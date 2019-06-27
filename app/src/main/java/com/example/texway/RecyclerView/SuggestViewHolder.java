package com.example.texway.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.texway.DAO.Product;
import com.example.texway.R;


public class SuggestViewHolder extends RecyclerView.ViewHolder {

    TextView badProductNametextView;
    TextView goodProductNametextView;

    ImageView badProductImage;
    ImageView goodProductImage;

    public SuggestViewHolder(View itemView) {
        super(itemView);
        badProductNametextView = itemView.findViewById(R.id.name_bad_product);
        goodProductNametextView = itemView.findViewById(R.id.name_good_product);
        badProductImage = itemView.findViewById(R.id.image_bad_product);
        goodProductImage = itemView.findViewById(R.id.image_good_product);

    }

    public void updateWithSuggest(Product badproduct, Product goodProduct){
        this.badProductNametextView.setText(badproduct.getName());
        this.goodProductNametextView.setText(goodProduct.getName());

    }
}
