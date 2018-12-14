package com.sanchez.tipicosaludable;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Perfil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



public class Fragment_Perfil extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    TextView actividad, calmax, genero, edad, imc, talla, txtNombre, txtCorreo, txtId,peso;
    public static int parseo, entero;
    Double caloriasmaximas, caloriasconsumidas;
    Perfil perfil;
    CircleImageView fotop;
    Button btnlogout, revoke;
    FirebaseApp firebaseApp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, tablaperfil;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseUser firebaseUser;
    ArrayList<Perfil> perfil_lista = new ArrayList<Perfil>();
    ArrayAdapter<Perfil> adaptadorperfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil");
        inicializarfirebase();
        firebaseAuth = FirebaseAuth.getInstance();


        actividad = findViewById(R.id.actividad);
        edad = findViewById(R.id.edad);
        imc = findViewById(R.id.imc);
        talla = findViewById(R.id.talla);
        peso = findViewById(R.id.peso);
        fotop = findViewById(R.id.fotop);
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnlogout = findViewById(R.id.btnlogout);
        calmax = findViewById(R.id.calmax);



        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    setUserData(user);

                    Query q2 = tablaperfil.orderByChild("nombre").equalTo(user.getDisplayName());
                    //Query q2 =tablaperfil.orderByChild("nombre").equalTo("Leonardo Sanchez");
                    q2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                                    Perfil p = objSnapshot.getValue(Perfil.class);
                                    perfil_lista.add(p);
                                    adaptadorperfil = new ArrayAdapter<Perfil>(Fragment_Perfil.this, android.R.layout.simple_list_item_1, perfil_lista);
                                    actividad.setText(""+p.getActividad());
                                    caloriasmaximas = p.getCalorías_maximas();
                                    entero = Integer.valueOf(caloriasmaximas.intValue());
                                    calmax.setText(""+entero);
                                    talla.setText(""+p.getTalla());
                                    caloriasconsumidas = p.getImc();
                                    parseo = Integer.valueOf(caloriasconsumidas.intValue());
                                    imc.setText(""+parseo);
                                    peso.setText(""+p.getPeso());
                                    edad.setText(""+p.getEdad());

                                }


                            } catch (Exception e) {

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                } else {
                    goLogin();
                }

            }





            private void goLogin() {

                Intent intent = new Intent(Fragment_Perfil.this, Login.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            private void setUserData(FirebaseUser user) {
                txtNombre.setText(user.getDisplayName());
                txtCorreo.setText(user.getEmail());
                Glide.with(Fragment_Perfil.this).load(user.getPhotoUrl()).into(fotop);

            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(Fragment_Perfil.this, MainActivity.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }

    }

    private void goLogin() {

        Intent intent = new Intent(Fragment_Perfil.this,Login.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void inicializarfirebase() {
        firebaseApp.initializeApp(Fragment_Perfil.this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Historial");
        tablaperfil = firebaseDatabase.getReference("Perfil");



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
                    Toast.makeText(Fragment_Perfil.this, "No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
