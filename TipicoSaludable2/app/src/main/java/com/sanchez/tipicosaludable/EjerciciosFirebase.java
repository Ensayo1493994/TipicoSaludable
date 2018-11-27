package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class EjerciciosFirebase extends Fragment implements AdapterView.OnItemClickListener{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Deportes_firebase> listadeportes = new ArrayList<Deportes_firebase>();
    ArrayAdapter<Deportes_firebase> adaptador;
    Dialog popupdeportes;
    ImageView gifdeporte, xbutton;
    TextView duracion, calorias;
    Deportes_firebase item;

    Button btnrealizar, btncancelar;
    public static  int resta, calentero, caloriasacumuladas=0;
    Double a = ScrollingDetalle.Calorias_consumidas;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View vista = inflater.inflate(R.layout.fragment_ejercicios_firebase, container, false);
        final GridView gridView = vista.findViewById(R.id.griddeportes);
        gridView.setOnItemClickListener(this);
        popupdeportes = new Dialog(getContext());

        inicializarfirebase();

        Query q  = databaseReference.orderByChild("categoria").equalTo("ejercicio");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){

                    Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                    listadeportes.add(p);
                    adaptador = new ArrayAdapter<Deportes_firebase>(getContext(),android.R.layout.simple_list_item_1,listadeportes);
                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getContext(),listadeportes);
                    gridView.setAdapter(adaptadorDeportes);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


         return vista;


    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Deporte");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        item = (Deportes_firebase) adapterView.getItemAtPosition(position);
        popupdeportes.setContentView(R.layout.contendedor_alert);
        gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
        calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
        duracion = popupdeportes.findViewById(R.id.duracion_min2);
        btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
        btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
        xbutton = popupdeportes.findViewById(R.id.xContenedor);
        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupdeportes.dismiss();
            }
        });
        btnrealizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupdeportes.dismiss();
                popupdeportes.setContentView(R.layout.activity_detalle_deportes);
                gifdeporte = popupdeportes.findViewById(R.id.imgdeporte);
                calorias = popupdeportes.findViewById(R.id.calorias_depor);
                duracion = popupdeportes.findViewById(R.id.duracion_depor);
                Glide.with(getContext())
                        .load(item.getImagen())
                        .crossFade()
                        .centerCrop()
                        .placeholder(R.drawable.imagen)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.5f)
                        .into(gifdeporte);

                calorias.setText(item.getCalorias());
                duracion.setText(item.getDuracion());

                calentero = Integer.valueOf(a.intValue());
                caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                resta= calentero - Integer.parseInt(calorias.getText().toString());

                popupdeportes.show();

            }
        });

        Glide.with(this)
                .load(item.getImagen())
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(gifdeporte);

        calorias.setText(item.getCalorias());
        duracion.setText(item.getDuracion());

        popupdeportes.show();

    }
}
