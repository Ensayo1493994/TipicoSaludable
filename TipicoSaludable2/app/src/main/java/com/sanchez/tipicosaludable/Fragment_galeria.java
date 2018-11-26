package com.sanchez.tipicosaludable;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_galeria extends Fragment implements AdapterView.OnItemClickListener {
    public  static  String n;
    public static String nombrealimento;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Comida> listacomida = new ArrayList<Comida>();
    ArrayAdapter<Comida> adaptador;
    Double parseo;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_galeria, container, false);
        // Inflate the layout for this fragment
        final GridView gridView = (GridView) vista.findViewById(R.id.grid);


/*
        AdaptadorComida adaptador = new AdaptadorComida(getContext());

        gridView .setAdapter(adaptador);*/

        gridView.setOnItemClickListener(this);
        inicializarfirebase();

        //------------------------------------------OBTENER DATOS DESDE FIREBASE-----------------------------------------


        databaseReference.child("Comida").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){

                        Comida p = objsnapshot.getValue(Comida.class);
                        listacomida.add(p);
                        Toast.makeText(getContext(), ""+listacomida.size(), Toast.LENGTH_SHORT).show();
                        adaptador = new ArrayAdapter<Comida>(getContext(),android.R.layout.simple_list_item_1,listacomida);
                        AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                        gridView.setAdapter(adaptadorComida);


                }*/
                GenericTypeIndicator<ArrayList<Comida>> t = new GenericTypeIndicator<ArrayList<Comida>>(){};
                 listacomida = dataSnapshot.getValue(t);

                adaptador = new ArrayAdapter<Comida>(getContext(),android.R.layout.simple_list_item_1,listacomida);
                AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                gridView.setAdapter(adaptadorComida);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //------------------------------------------OBTENER DATOS DESDE FIREBASE-----------------------------------------
        return vista;

    }



    private void inicializarfirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Comida item = (Comida) adapterView.getItemAtPosition(position);
        //n =item.getIdDrawable(),item.getNombre();

        n = item.getIdDrawable();
        nombrealimento = item.getNombre();

        Activity activity = getActivity();
        Intent intent = new Intent(getContext(), ScrollingDetalle.class);
        intent.putExtra(ScrollingDetalle.EXTRA_PARAM_ID, item.getId());

/*        Fragment fragment = null;
        fragment = new Fragment_Detalle();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();*/
        //fragment.getActivity().putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, new Pair<View, String>(view.findViewById(R.id.imagen_comida),ScrollingDetalle.VIEW_NAME_HEADER_IMAGE)

            );
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }else
            startActivity(intent);
    }
}
