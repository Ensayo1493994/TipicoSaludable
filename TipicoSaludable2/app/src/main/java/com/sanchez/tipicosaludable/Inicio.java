package com.sanchez.tipicosaludable;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Historial;
import com.sanchez.tipicosaludable.model.Perfil;
import com.sanchez.tipicosaludable.model.Usuarios;

import java.util.ArrayList;


public class Inicio extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    int size, entero;
    Double caloriasmaximas;
    FirebaseApp firebaseApp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, tablaperfil;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth firebaseAuth;

    ArrayList<Historial> historial_lista = new ArrayList<Historial>();

    Historial usuariofound;
    ArrayList<Perfil> perfil_lista = new ArrayList<Perfil>();
    ArrayAdapter<Perfil> adaptadorperfil;
    public  static int temp=0;
    String nombreusuario;

    //animacion fondo
    LinearLayout bglayout;
    AnimationDrawable animationDrawable;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        GridView gridView = (GridView) vista.findViewById(R.id.ultimoconsumo);

       /* View decorView =getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        LinearLayout linearLayout = (LinearLayout) vista.findViewById(R.id.bglayout);


        animationDrawable = (AnimationDrawable) bglayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
        */



        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //nombreusuario=  user.getDisplayName();
        //Toast.makeText(getContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();




        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //nombreusuario=  user.getDisplayName();
                    Toast.makeText(getContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }else {

                }

            }
        };





        TextView textView = vista.findViewById(R.id.txtnoconsumo);

        final TextView textView2 = vista.findViewById(R.id.cal_per);
        inicializarfirebase();

        /*Query q = tablaperfil.orderByChild("nombre").equalTo(nombreusuario);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Perfil p = objSnapshot.getValue(Perfil.class);
                    perfil_lista.add(p);
                    //adaptadorperfil = new ArrayAdapter<Perfil>(Login.this,android.R.layout.simple_list_item_1,perfil_lista);
                    adaptadorperfil = new ArrayAdapter<Perfil>(getContext(),android.R.layout.simple_list_item_1,perfil_lista);
                    //Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();
                    temp=1;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        //-------------CONSULTAR INFO POR USUARIO--------------


        if(user != null){
            Query q2 = tablaperfil.orderByChild("nombre").equalTo(user.getDisplayName());
            //Query q =databaseReference.orderByChild("usuario").equalTo("INGRID KATERINE VELASCO LOPEZ");
            q2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*GenericTypeIndicator<ArrayList<Historial>> t = new GenericTypeIndicator<ArrayList<Historial>>(){};
                    historial_lista = dataSnapshot.getValue(t);
                    adaptador = new ArrayAdapter<Historial>(getContext(),android.R.layout.simple_list_item_1,historial_lista);
                    Historial p = new Historial();
                    //textView2.setText(""+p.getCalorías_máximas());
                    Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();*/
                    try{
                        for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                            Perfil p = objSnapshot.getValue(Perfil.class);
                            perfil_lista.add(p);
                            adaptadorperfil = new ArrayAdapter<Perfil>(getContext(),android.R.layout.simple_list_item_1,perfil_lista);
                            //Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();

                            caloriasmaximas = p.getCalorías_maximas();








                        }
                        entero = Integer.valueOf(caloriasmaximas.intValue());
                        textView2.setText(""+entero);

                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }




        //-------------CONSULTAR INFO POR USUARIO--------------


        if (ScrollingDetalle.ultimoconsumo.size()>0){
            AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(getContext(),ScrollingDetalle.ultimoconsumo);
            gridView.setAdapter(adaptador);

        }
        else {
            textView.setText("No has consumido nada hoy");

        }






        return vista;
    }

    private void inicializarfirebase() {
        firebaseApp.initializeApp(getContext());
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Historial");
        tablaperfil = firebaseDatabase.getReference("Perfil");



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
