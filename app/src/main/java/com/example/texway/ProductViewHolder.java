package com.example.texway;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ProductViewHolder extends RecyclerView.ViewHolder {

    TextView barcodetextView;
    TextView nametextView;
    TextView scoretextView;
    TextView compotextView;

    public ProductViewHolder(View itemView) {
        super(itemView);
        barcodetextView = itemView.findViewById(R.id.barcode);
        nametextView = itemView.findViewById(R.id.name);
        scoretextView = itemView.findViewById(R.id.score);
        compotextView = itemView.findViewById(R.id.compo);

    }

    public void updateWithProduct(Product product){
        this.nametextView.setText(product.getName());
        this.barcodetextView.setText("Code-Barre : " + product.getBarcode());
        this.compotextView.setText("Composition : " + product.getComposition());
        this.scoretextView.setText("Score : " + product.getScore());

    }
}
