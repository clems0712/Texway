package com.example.texway.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.texway.DAO.Product;
import com.example.texway.R;
import com.example.texway.RecyclerView.SuggestViewAdapter;

import java.util.LinkedHashMap;


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

        // exemples a enlever
        Product bad = new Product(this.getContext());
        bad.setName("Vêtement 1");
        Product good = new Product(this.getContext());
        good.setName("Vêtement 2");
        updateUI(bad,good);
        Product bad2 = new Product(this.getContext());
        bad.setName("Vêtement 1");
        Product good2 = new Product(this.getContext());
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
