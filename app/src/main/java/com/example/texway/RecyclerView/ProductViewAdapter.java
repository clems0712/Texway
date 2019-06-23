package com.example.texway.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.texway.Activity.ProductActivity;
import com.example.texway.Product;
import com.example.texway.R;

import java.util.List;


public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    // les vetements
    private List<Product> products;

    // CONSTRUCTOR
    public ProductViewAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder viewHolder, final int position) {
        viewHolder.updateWithProduct(this.products.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), ProductActivity.class);
                intent.putExtra("Product", products.get(position));
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }
}
