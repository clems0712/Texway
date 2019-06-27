package com.example.texway.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.texway.Class.Dal;
import com.example.texway.DAO.Product;
import com.example.texway.Portrait;
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
    private ProgressBar spinner;

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


                String Code=result.getContents();
                String Code_format="";
                for(int index_Code=0;index_Code<Code.length();index_Code++){
                    if(Character.getNumericValue(Code.charAt(index_Code)) < 10 && Character.getNumericValue(Code.charAt(index_Code)) >=0 ){
                        Code_format+=Code.charAt(index_Code);

                    }}


                String Store="";
                Product product_base = new Product();
                Product product = new Product();
                Dal DataAcces = new Dal();



                ///CODE BARRE TYPE H&M
                if (Code_format.length()== 47 ){
                    Store="HM";
                    spinner.setVisibility(View.VISIBLE);
                    DataAcces.ReadProduct(Store,Code_format,this);

                } else {
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());
                    alertdialogbuilder.setTitle("Produit introuvable");
                    AlertDialog alertDialog = alertdialogbuilder.create();
                    alertDialog.setMessage("Le produit n'est pas encore noté");
                    alertDialog.show();
                }


            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    public void onDBResult(Product product_base)
    {
        if (product_base != null) {
            updateUI(product_base);
            AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());
            alertdialogbuilder.setTitle("Produit scanné");
            AlertDialog alertDialog = alertdialogbuilder.create();
            alertDialog.setMessage("La produit a été ajouté à la liste");
            alertDialog.show();
            product_base.open(this.getActivity());
            product_base.addProductDB();
        }

        spinner.setVisibility(View.GONE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flash_histo, container, false);
        recyclerView = rootView.findViewById(R.id.historique_list);
        this.configureRecyclerView();
        spinner = (ProgressBar)rootView.findViewById(R.id.progress_bar);

        FloatingActionButton fab = rootView.findViewById(R.id.buttonScan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNow();
            }
        });



        List<Product> products = new ArrayList<>();
        Product pdt = new Product(this.getActivity());
        pdt.open(this.getActivity());
        products = pdt.getAll();

        for (int i = 0; i < products.size(); i++){
            Log.d("db", "onCreateView: " + products.get(i).getId());
        }

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



        ///Recupere le produit generique
        Product product = new Product();
        product.setName("Jean noir");
        product.setMarque("H&M");
        product.setScore(70);
        product.setBarcode("11111000000");
        product.setComposition(fakeCompo);
        product.setImage(BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.jean_noir));
        updateUI(product);


        Product p = new Product();
        p.open(this.getActivity());
        List<Product> plist = p.getAll();

        for (Product a:plist)
            updateUI(a);

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
