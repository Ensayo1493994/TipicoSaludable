package com.sanchez.tipicosaludable;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Historial;
import com.sanchez.tipicosaludable.model.Perfil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;



public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    SignInButton btnlogin;
    public static final int CODE = 777;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ProgressBar progressBar;
    private CallbackManager callbackManager;
    private LoginButton btnloginfb;
    Animation smalltobig, nothingtocome;
    ImageView logocir;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Perfil> perfil_lista = new ArrayList<Perfil>();
    ArrayAdapter<Perfil> adaptadorperfil;
    int encontrousuario=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progresBar);

        //animacion login

        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        logocir = findViewById(R.id.logocir);
        logocir.startAnimation(smalltobig);

        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);
        inicializarfirebase();

        //btnlogin.startAnimation(nothingtocome);

        // btnloginfb.startAnimation(nothingtocome);

        //----------------------MOSTRAR FORMULARIO UNA SOLA VEZ--------------------
        firebaseAuth = FirebaseAuth.getInstance();



        if (Inicio.temp==1){
            goMain();
        }

        //Login Facebook
        callbackManager = CallbackManager.Factory.create();
        btnloginfb =findViewById(R.id.btnloginfb);
        btnloginfb.setReadPermissions(Arrays.asList("email","public_profile"));
        btnloginfb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,CODE);




                if (Inicio.temp==1){

                    goMain();
                }


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    goMainScreen();
                }
                /*FirebaseUser user = firebaseAuth.getCurrentUser();
                Query q = databaseReference.orderByChild("nombre").equalTo(user.getDisplayName());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        encontrousuario = encontrousuario+1;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if (encontrousuario ==1){
                    goMain();
                }*/

            }
        };

    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Perfil");
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        progressBar.setVisibility(View.VISIBLE);
        btnloginfb.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.firebase_error_login, Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
                btnloginfb.setVisibility(View.VISIBLE);
            }
        });
    }


    private void goMain() {
        Intent intent3 = new Intent(Login.this ,Menu_Lateral.class);
        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent3);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult (result);
        }
    }



    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()){
            firebaseAuthwhitGoogle(result.getSignInAccount());

        }else{
            Toast.makeText(this, "No se pudo acceder a la cuenta", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthwhitGoogle(GoogleSignInAccount signInAccount) {
        progressBar.setVisibility(View.VISIBLE);
        btnlogin.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                btnlogin.setVisibility(View.VISIBLE);

                if(!task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "no se puede autenticar con firebase", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void goMainScreen() {
        Intent intent2 = new Intent(Login.this ,CaloriasActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(firebaseAuthListener != null){

            firebaseAuth.removeAuthStateListener(firebaseAuthListener);

        }


    }

}
