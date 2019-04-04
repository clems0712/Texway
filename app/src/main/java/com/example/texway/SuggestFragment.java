package com.example.texway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.texway.Product;
import com.example.texway.ProductViewAdapter;
import com.example.texway.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class SuggestFragment extends Fragment {

    RecyclerView recyclerView; // 1 - Declare RecyclerView

    LinkedHashMap<Product,Product> suggests;
    SuggestViewAdapter adapter;

    public SuggestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_suggest, container, false);

        recyclerView = rootView.findViewById(R.id.suggest_list);
        this.configureRecyclerView();

        // exemples
        Product bad = new Product();
        bad.setName("Vêtement 1");

        Product good = new Product();
        good.setName("Vêtement 2");

        updateUI(bad,good);

        Product bad2 = new Product();
        bad.setName("Vêtement 1");

        Product good2 = new Product();
        good.setName("Vêtement 2");

        bad2.setName("mauvais vêtement");

        good2.setName("bon vêtement");

        updateUI(bad2,good2);

        return rootView;
    }

    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.suggests = new LinkedHashMap<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new SuggestViewAdapter(this.suggests);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI(Product badproduct,Product goodproduct){
        suggests.put(badproduct,goodproduct);
        adapter.notifyDataSetChanged();
    }

}
