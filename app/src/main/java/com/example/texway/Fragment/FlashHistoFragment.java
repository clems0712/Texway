package com.example.texway.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.texway.Portrait;
import com.example.texway.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FlashHistoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FlashHistoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashHistoFragment extends Fragment {


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



    }

    private void scanNow()
    {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Your Code Bar");
        integrator.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() == null) {
                Toast.makeText(getContext(),"Result Not Found",Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getContext());
                alertdialogbuilder.setTitle("Result Scanned");
                AlertDialog alertDialog = alertdialogbuilder.create();
                alertDialog.show();
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        FloatingActionButton fab = rootView.findViewById(R.id.buttonScan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNow();
            }
        });
        return rootView;
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
