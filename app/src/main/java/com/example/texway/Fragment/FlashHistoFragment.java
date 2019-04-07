package com.example.texway.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.texway.FirebaseFacade;
import com.example.texway.Portrait;
import com.example.texway.Product;
import com.example.texway.RecyclerView.ProductViewAdapter;
import com.example.texway.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class FlashHistoFragment extends Fragment {


    RecyclerView recyclerView; // 1 - Declare RecyclerView

    List<Product> products;
    ProductViewAdapter adapter;

    public FlashHistoFragment() {
        // Required empty public constructor
    }

    public static FlashHistoFragment newInstance(String param1, String param2) {
        FlashHistoFragment fragment = new FlashHistoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void scanNow()
    {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanner un produit");
        integrator.setBeepEnabled(false);
        integrator.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() == null) {
                Toast.makeText(getContext(),"Annulé",Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());
                alertdialogbuilder.setTitle("Produit scanné");
                AlertDialog alertDialog = alertdialogbuilder.create();
                alertDialog.setMessage("La produit a été ajouté à la liste");
                alertDialog.show();
                Product product = new Product();
                product.setName("Vêtement");
                product.setBarcode(result.getContents());
                updateUI(product);

            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flash_histo, container, false);
        recyclerView = rootView.findViewById(R.id.historique_list);
        this.configureRecyclerView();

        FloatingActionButton fab = rootView.findViewById(R.id.buttonScan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNow();
            }
        });


        //Placeholder a enlever
        List<String> fakeCompo = new ArrayList<>();
        fakeCompo.add("Substance allergène");
        fakeCompo.add("Substance cancérigène");
        fakeCompo.add("Substance allergène 2");
        fakeCompo.add("Substance allergène 3");
        fakeCompo.add("Substance cancérigène 2");
        fakeCompo.add("Substance allergène");
        fakeCompo.add("Substance cancérigène");
        fakeCompo.add("Substance allergène 2");
        fakeCompo.add("Substance allergène 3");
        fakeCompo.add("Substance cancérigène 2");
        Product product = new Product();
        product.setName("Exemple de vêtement");
        product.setBarcode("11111000000");
        product.setComposition(fakeCompo);
        updateUI(product);

        return rootView;
    }

    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.products = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new ProductViewAdapter(this.products);
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI(Product newProduct){
        products.add(newProduct);
        adapter.notifyDataSetChanged();
    }
}
