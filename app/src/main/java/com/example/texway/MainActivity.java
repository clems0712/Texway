package com.example.texway;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mWelcomeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button btnscan = (Button)findViewById(R.id.buttonScan);
        LottieAnimationView codebarview =(LottieAnimationView) findViewById(R.id.animationscan);
        codebarview.setOnClickListener(this);
       // btnscan.setOnClickListener(this);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents() == null) {
                Toast.makeText(this,"Result Not Found",Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
                alertdialogbuilder.setMessage(result.getContents()+"\n\nScan Again ?");
                alertdialogbuilder.setTitle("Result Scanned");
                alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanNow();
                    }
                });
                alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertdialogbuilder.create();
                alertDialog.show();
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
        }

        public void scanNow(){
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setCaptureActivity(Portrait.class);
            integrator.setOrientationLocked(false);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("Scan Your Code Bar");
            integrator.initiateScan();

        }

        @Override
         public void onClick(View v){
        scanNow();
        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
