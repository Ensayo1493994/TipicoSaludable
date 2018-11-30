package com.sanchez.tipicosaludable;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.sanchez.tipicosaludable.model.Perfil;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Perfil extends Fragment implements GoogleApiClient.OnConnectionFailedListener{

    TextView actividad, contextura, genero, edad, imc, talla, txtNombre, txtCorreo, txtId;
    EditText peso;
    Perfil perfil;
    ImageView fotop;
    Button btnlogout, revoke;
   private DatabaseReference databaseReference;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseUser firebaseUser;


    public Fragment_Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment__perfil, container, false);



        actividad = view.findViewById(R.id.actividad);
        contextura = view.findViewById(R.id.contextura);
        genero = view.findViewById(R.id.genero);
        edad = view.findViewById(R.id.edad);
        imc = view.findViewById(R.id.imc);
        talla = view.findViewById(R.id.talla);
        peso = view.findViewById(R.id.peso);
        fotop = view.findViewById(R.id.fotop);
        txtNombre = view.findViewById(R.id.txtNombre);
        txtCorreo = view.findViewById(R.id.txtCorreo);
        txtId = view.findViewById(R.id.txtId);
        fotop = view.findViewById(R.id.fotop);
        btnlogout = view.findViewById(R.id.btnlogout);

        perfil = new Perfil();


        actividad.setText("Actividad fisica: " + perfil.getActividad());
        contextura.setText("Contextura: " + perfil.getContextura());
        genero.setText("genero: " + perfil.getGenero());
        edad.setText("Edad: " + perfil.getEdad());
        imc.setText("Imc: " + perfil.getImc());
        talla.setText("Talla: " + perfil.getTalla());
        peso.setText("Peso: " + perfil.getPeso());
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
                } else {
                    goLogin();
                }
            }

            private void goLogin() {

                Intent intent = new Intent(getContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            private void setUserData(FirebaseUser user) {
                txtNombre.setText(user.getDisplayName());
                txtCorreo.setText(user.getEmail());
                txtId.setText(user.getUid());
                Glide.with(getActivity()).load(user.getPhotoUrl()).into(fotop);

            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



        return view;
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

        Intent intent = new Intent(getActivity(),Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void LogOut(){
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogin();
                } else{
                    Toast.makeText(getActivity(), "No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
