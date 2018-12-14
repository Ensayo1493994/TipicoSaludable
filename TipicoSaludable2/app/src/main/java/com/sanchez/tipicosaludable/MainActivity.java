package com.sanchez.tipicosaludable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Perfil;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    ImageView inicio,ejercicios,actividadfisica,deportes,comidas,perfil;
    public static int parseo, entero;
    Double caloriasmaximas, caloriasconsumidas;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseUser firebaseUser;
    FirebaseApp firebaseApp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, tablaperfil;
    ArrayList<Perfil> perfil_lista = new ArrayList<Perfil>();
    ArrayAdapter<Perfil> adaptadorperfil;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cerrasesion:
                LogOut();
                break;
            case R.id.acerca:
                Intent intent = new Intent(MainActivity.this,AcercaDeFragment.class);
                startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicio = findViewById(R.id.inicio);
        perfil = findViewById(R.id.perfil);
        actividadfisica = findViewById(R.id.actividadfisica);
        ejercicios = findViewById(R.id.ejercicios);
        deportes = findViewById(R.id.deportes);
        comidas = findViewById(R.id.comidas);


        inicializarfirabase();


        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);
            }
        });
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Fragment_Perfil.class);
                startActivity(intent1);

            }
        });
        actividadfisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, ActividadFisicaFirebase.class);
                startActivity(intent3);

            }
        });
        ejercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, EjerciciosFirebase.class);
                startActivity(intent4);

            }
        });
        deportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Deportesfirebase.class);
                startActivity(intent2);

            }
        });
        comidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, Fragment_galeria.class);
                startActivity(intent5);
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);
                } else {
                    goLogin();
                }

            }
        };


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Query q2 = tablaperfil.orderByChild("nombre").equalTo(user.getDisplayName());
            //Query q2 =tablaperfil.orderByChild("nombre").equalTo("Leonardo Sanchez");
            q2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                            Perfil p = objSnapshot.getValue(Perfil.class);
                            perfil_lista.add(p);
                            adaptadorperfil = new ArrayAdapter<Perfil>(MainActivity.this, android.R.layout.simple_list_item_1, perfil_lista);

                            caloriasmaximas = p.getCalorías_maximas();
                        }
                        entero = Integer.valueOf(caloriasmaximas.intValue());
                       // Toast.makeText(MainActivity.this, ""+entero, Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }


    private void inicializarfirabase() {
        firebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Historial");
        tablaperfil = firebaseDatabase.getReference("Perfil");
    }


    private void setUserData(FirebaseUser user) {
       user.getDisplayName();
       user.getEmail();


    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);

        /*OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInRsult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInRsult(googleSignInResult);
                }
            });
        }*/

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }

    }

    /*private void handleSignInRsult(GoogleSignInResult result) {

        if (result.isSuccess()){

            GoogleSignInAccount account =  result.getSignInAccount();

            txtNombre.setText(account.getDisplayName());
            txtCorreo.setText(account.getEmail());
            txtId.setText(account.getId());

            //Log.d("MIAPP",account.getPhotoUrl().toString());
            Glide.with(this).load(account.getPhotoUrl()).into(fotop);

        } else {

            goLogin();

        }

    }*/

    private void goLogin() {

        Intent intent = new Intent(MainActivity.this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    public void LogOut(){
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    LoginManager.getInstance().logOut();
                    goLogin();
                } else{
                    Toast.makeText(MainActivity.this, "No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
