package com.example.texway.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.texway.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputPsw;
    EditText inputName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(this);
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        inputName = findViewById(R.id.inputName);
        inputPsw = findViewById(R.id.inputPsw);

        //pour debug A ENLEVER
        inputName.setText("Utilisateur");
        inputPsw.setText("1234");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.connect: {
                //todo activity_connexion user
                loginSuccessful();
                finish();
                break;
            }
            case  R.id.connect_facebook: {
                //todo activity_connexion user
                connect_facebook();
                //finish();
                break;
            }
            case  R.id.connect_google: {
                //todo activity_connexion user
                connect_google();
                //finish();
                break;
            }
            case  R.id.register: {
                //todo register user
                break;
            }
        }
    }

    private void connect_facebook()
    {
        //TODO
    }
    private void connect_google()
    {
        //TODO
    }

    public void loginSuccessful()
    {
        Intent menuAct = new Intent(this, MenuActivity.class);
        startActivity(menuAct);
    }

    public void loginFail()
    {
        Snackbar.make(findViewById(android.R.id.content)
                , "Nom ou mot de passe incorrect", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
