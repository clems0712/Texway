package com.example.texway.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.texway.DAO.Product;
import com.example.texway.R;
import com.example.texway.Util;


public class ProductViewHolder extends RecyclerView.ViewHolder {

    TextView marquetextView;
    TextView nametextView;
    TextView scoretextView;
    ImageView imageImageView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        marquetextView = itemView.findViewById(R.id.marque);
        nametextView = itemView.findViewById(R.id.name);
        scoretextView = itemView.findViewById(R.id.score);
        imageImageView = itemView.findViewById(R.id.image);

    }

    public void updateWithProduct(Product product){
        this.nametextView.setText(product.getName());
        this.marquetextView.setText(product.getMarque());

        this.scoretextView.setText("Qualité : " + Util.getQualityByScore(product.getScore()));


        if (product.getImage() != null)
            this.imageImageView.setImageBitmap(product.getImage());

    }
}
