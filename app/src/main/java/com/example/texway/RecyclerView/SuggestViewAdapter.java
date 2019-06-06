package com.example.texway.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.texway.Product;
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
    public void onBindViewHolder(SuggestViewHolder viewHolder, int position) {
        List<Product> bad_products = new ArrayList<Product>(suggests.keySet());
        List<Product> good_products = new ArrayList<Product>(suggests.values());

        viewHolder.updateWithSuggest(bad_products.get(position),good_products.get(position));
    }

    @Override
    public int getItemCount() {
        return this.suggests.size();
    }
}
