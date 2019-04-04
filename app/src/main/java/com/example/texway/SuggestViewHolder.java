package com.example.texway;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class SuggestViewHolder extends RecyclerView.ViewHolder {

    TextView badProductNametextView;
    TextView goodProductNametextView;

    public SuggestViewHolder(View itemView) {
        super(itemView);
        badProductNametextView = itemView.findViewById(R.id.name_bad_product);
        goodProductNametextView = itemView.findViewById(R.id.name_good_product);

    }

    public void updateWithSuggest(Product badproduct,Product goodProduct){
        this.badProductNametextView.setText(badproduct.getName());
        this.goodProductNametextView.setText(goodProduct.getName());

    }
}
