package com.example.texway.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.texway.Activity.ProductActivity;
import com.example.texway.DAO.Product;
import com.example.texway.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class SuggestViewAdapter extends RecyclerView.Adapter<SuggestViewHolder> {

    // les suggestions
    private LinkedHashMap<Product,Product> suggests;

    // CONSTRUCTOR
    public SuggestViewAdapter(LinkedHashMap<Product,Product> suggests) {
        this.suggests = suggests;
    }

    @Override
    public SuggestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.suggest_item, parent, false);

        return new SuggestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SuggestViewHolder viewHolder, final int position) {
        final List<Product> bad_products = new ArrayList<Product>(suggests.keySet());
        final List<Product> good_products = new ArrayList<Product>(suggests.values());

        viewHolder.updateWithSuggest(bad_products.get(position),good_products.get(position));

        viewHolder.badProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.badProductImage.getContext(), ProductActivity.class);
                intent.putExtra("Product", bad_products.get(position));
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });

        viewHolder.goodProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.goodProductImage.getContext(), ProductActivity.class);
                intent.putExtra("Product", good_products.get(position));
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.suggests.size();
    }
}
