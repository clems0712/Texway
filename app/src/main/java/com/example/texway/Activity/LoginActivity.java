package com.example.texway.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.texway.Class.Dal;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.texway.R;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputPsw;
    EditText inputName ;

   private CallbackManager callbackManager;
   private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

         accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.



        //STORAGE


        setContentView(R.layout.activity_connexion);
        Button connect = (Button) findViewById(R.id.connect);
        LoginButton btnLogin = (LoginButton)findViewById(R.id.connect_facebook);
        connect.setOnClickListener(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        inputName = findViewById(R.id.inputName);
        inputPsw = findViewById(R.id.inputPsw);

        //pour debug A ENLEVER
        inputName.setText("Utilisateur");
        inputPsw.setText("1234");

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        loginSuccessful();
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Snackbar.make(findViewById(android.R.id.content)
                                , "Identifiant ou mot de passe incorrect ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Snackbar.make(findViewById(android.R.id.content)
                                , "Impossible d'établir une connexion", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }

        });
    }
    // fin On Create

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
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
