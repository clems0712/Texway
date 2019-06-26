package com.example.texway.Activity;

import android.content.Intent;

import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.Task;


import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    EditText inputPsw;
    EditText inputName ;

    CallbackManager callbackManager;
    //AccessTokenTracker accessTokenTracker;
   // GoogleApiClient.Builder googleApiClientBuilder;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInAccount account;
   // GoogleSignInClient mGoogleSignInClient;
   private static final int REQ_CODE = 9001;
    Intent signInIntent;
    private boolean startActIfLog ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        //AccessToken accessToken = AccessToken.getCurrentAccessToken();
        //boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

      //   accessTokenTracker = new AccessTokenTracker() {
          /*  @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }


        };
        */
        // If the access token is available already assign it.
        setContentView(R.layout.activity_connexion);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


       // mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton btnConnectGoogle = (SignInButton) findViewById(R.id.connect_google);
        btnConnectGoogle.setOnClickListener(this);
        btnConnectGoogle.setSize(SignInButton.SIZE_STANDARD);


        Button connectfacebook = (Button) findViewById(R.id.connect);
        LoginButton btnLogin = (LoginButton)findViewById(R.id.connect_facebook);
        connectfacebook.setOnClickListener(this);
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
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
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);


           // Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //handleSignInResult(task);
        }
    }
    private void handleResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            //Toast.makeText(this,"Connexion réussit",Toast.LENGTH_LONG).show();
            updateUI(true);
        }
        else {
           // Toast.makeText(this,"Echec de la connexion",Toast.LENGTH_LONG).show();
            updateUI(false);
        }
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(true);
    }
*/
    public void  updateUI(boolean isLogin){
        if(isLogin == true){
            Toast.makeText(this,"Connexion réussit",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MenuActivity.class));
            startActIfLog = true;

        }else {
            Toast.makeText(this,"Echec de la connexion",Toast.LENGTH_LONG).show();
            startActIfLog = false ;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // accessTokenTracker.stopTracking();
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
                if(startActIfLog == true ){
                    loginSuccessful();
                    finish();
                }
                //finish();
                break;
            }
            case  R.id.register: {
                //todo register user
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
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
        signIn();

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, REQ_CODE);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
