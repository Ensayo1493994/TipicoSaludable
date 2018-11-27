package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Deportesfirebase extends Fragment implements AdapterView.OnItemClickListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Deportes_firebase> listadeportes = new ArrayList<Deportes_firebase>();
    ArrayAdapter<Deportes_firebase> adaptador;
    Dialog popupdeportes, popupdeportes2;
    ImageView gifdeporte, xbutton;
    TextView duracion, calorias, textorealizado;
    Deportes_firebase item;

    Button btnrealizar, btncancelar, btnaceptar;
    public static  int resta, calentero, caloriasacumuladas=0;
    Double a = ScrollingDetalle.Calorias_consumidas;
    int i=0;
    //cronometro
    TextView crono,caloriras_depor,duracion_depor;
    ImageView playbuttom,stopbuttom,rewindbuttom,imgdeporte;
    boolean isOn = false;
    int min=0,seg=0,mili=0;
    Handler h = new Handler();
    Thread cronos;
    MediaPlayer alarma;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_deportesfirebase, container, false);
        final GridView gridView = view.findViewById(R.id.griddeportes);
        gridView.setOnItemClickListener(this);
        popupdeportes = new Dialog(getContext());
        popupdeportes2 = new Dialog(getContext());

        inicializarfirebase();

        //---------------------------- DATOS DEPORTE FIREBASE --------------------

        Query q = databaseReference.orderByChild("categoria").equalTo("deporte");
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


                /*
                GenericTypeIndicator<ArrayList<Deportes_firebase>> t = new GenericTypeIndicator<ArrayList<Deportes_firebase>>(){};
                listadeportes = dataSnapshot.getValue(t);
                adaptador = new ArrayAdapter<Deportes_firebase>(getContext(),android.R.layout.simple_list_item_1,listadeportes);
                //AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getContext(),listadeportes);
                gridView.setAdapter(adaptadorDeportes);*/

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
        databaseReference = firebaseDatabase.getReference("Deporte");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


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


        popupdeportes2.setContentView(R.layout.ejerciciorealizado);
        textorealizado = popupdeportes2.findViewById(R.id.texttorelaizado);
        btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);

        textorealizado.setText("Felicidades has quemado un total de "+ calorias.getText());
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupdeportes2.dismiss();
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
                crono = popupdeportes.findViewById(R.id.crono);
                playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                alarma  = MediaPlayer.create(getContext(),R.raw.alarma);

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
                playbuttom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isOn=true;
                    }

                });
                stopbuttom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isOn=false;
                    }
                });
                rewindbuttom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isOn=false;
                        mili=0;
                        seg=0;
                        min=0;
                        crono.setText("00:00:000");

                    }
                });
                cronos = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            if (isOn){
                                try{
                                    Thread.sleep(1);

                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                mili++;
                                if (mili==999){
                                    seg++;
                                    mili=0;
                                }
                                if (seg==59){
                                    min++;
                                    seg=0;
                                }
                                h.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        String m = "", s = "", mi = "";
                                        if (mili<10) {
                                            m = "00" + mili;
                                        }else if (mili<100){
                                            m="0"+mili;
                                        }else {
                                            m=""+mili;
                                        }
                                        if (seg<10){
                                            s ="0"+ seg;
                                        }else {
                                            s ="" +seg;
                                        }if (min<10){
                                            mi="0"+min;
                                        }else {
                                            mi=""+min;
                                        }
                                        crono.setText(mi+":"+s+":"+m);
                                    }
                                });

                            }
                        }

                    }
                });
                cronos.start();


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            if(isOn){
                                try{
                                    Thread.sleep(1);

                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (seg==30){
                                            starsonido();
                                            popupdeportes.dismiss();
                                            popupdeportes2.show();



                                            isOn = false;

                                            //Toast.makeText(getContext(), "Realizado", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }

                    }
                });
                t.start();
                popupdeportes.show();
                seg =0;
                mili=0;
                min =0;
            }


            private void starsonido(){
                alarma.start();
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



        /*
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

                popupdeportes.show();

            }
        });*/


    }

}
