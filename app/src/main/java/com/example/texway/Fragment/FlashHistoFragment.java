package com.example.texway;

import android.app.AlertDialog;
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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FlashHistoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FlashHistoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashHistoFragment extends Fragment {


    RecyclerView recyclerView; // 1 - Declare RecyclerView

    List<Product> products;
    ProductViewAdapter adapter;

    public FlashHistoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlashHistoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlashHistoFragment newInstance(String param1, String param2) {
        FlashHistoFragment fragment = new FlashHistoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Exemple placeholder


    }

    private void scanNow()
    {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanner un produit");
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

        Product product = new Product();
        product.setName("Exemple de vêtement");
        product.setBarcode("11111 000000");
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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
