package com.sanchez.tipicosaludable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Deportesfirebase extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Deportes_firebase> listadeportes = new ArrayList<Deportes_firebase>();
    ArrayAdapter<Deportes_firebase> adaptador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_deportesfirebase, container, false);
        final GridView gridView = view.findViewById(R.id.griddeportes);

        inicializarfirebase();

        //---------------------------- DATOS DEPORTE FIREBASE --------------------

        databaseReference.child("Deporte").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Deportes_firebase>> t = new GenericTypeIndicator<ArrayList<Deportes_firebase>>(){};
                listadeportes = dataSnapshot.getValue(t);
                adaptador = new ArrayAdapter<Deportes_firebase>(getContext(),android.R.layout.simple_list_item_1,listadeportes);
                //AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getContext(),listadeportes);
                gridView.setAdapter(adaptadorDeportes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;

    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


}
