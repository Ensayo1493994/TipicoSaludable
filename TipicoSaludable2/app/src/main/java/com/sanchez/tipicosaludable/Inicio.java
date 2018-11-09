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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.sanchez.tipicosaludable.model.Usuarios;

import java.util.ArrayList;


public class Inicio extends Fragment {
    int size, entero;
    Double caloriasmaximas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, tablahistorial;
    private FirebaseAuth firebaseAuth;
    ArrayList<Historial> historial_lista = new ArrayList<Historial>();
    ArrayAdapter<Historial> adaptadorhistorial;
    Historial usuariofound;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        GridView gridView = (GridView) vista.findViewById(R.id.ultimoconsumo);

        TextView textView = vista.findViewById(R.id.txtnoconsumo);

        final TextView textView2 = vista.findViewById(R.id.cal_per);
        inicializarfirebase();



        //--------------SIN CONSULTAR EN FIREBASE---------------


        //-------------CONSULTAR INFO POR USUARIO--------------

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            Query q =databaseReference.orderByChild("usuario").equalTo(user.getDisplayName());
            //Query q =databaseReference.orderByChild("usuario").equalTo("INGRID KATERINE VELASCO LOPEZ");
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*GenericTypeIndicator<ArrayList<Historial>> t = new GenericTypeIndicator<ArrayList<Historial>>(){};
                    historial_lista = dataSnapshot.getValue(t);
                    adaptador = new ArrayAdapter<Historial>(getContext(),android.R.layout.simple_list_item_1,historial_lista);
                    Historial p = new Historial();
                    //textView2.setText(""+p.getCalorías_máximas());
                    Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();*/


                    for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                        Historial p = objSnapshot.getValue(Historial.class);
                        historial_lista.add(p);
                        adaptadorhistorial = new ArrayAdapter<Historial>(getContext(),android.R.layout.simple_list_item_1,historial_lista);
                        //Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();

                        caloriasmaximas = p.getCalorías_máximas();








                    }
                    entero = Integer.valueOf(caloriasmaximas.intValue());
                    textView2.setText(""+entero);





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
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Historial");


    }

}
