package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;
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
import com.sanchez.tipicosaludable.model.*;

import java.util.ArrayList;
import java.util.List;



public class Lista_Ejercicios2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //recomendacion
    public static AdapterCard adapterCard;
    public static Button btn1, btn2;
    public static InfiniteCardView infiniteCardView;
    public static List<Integer> Images = new ArrayList<>();
    private static final String TAG = "Lista_Ejercicios2";
    RecyclerView recyclerView;
    String imgcaminar, imgflexiones,imgsentadilla,imgsalto,imgmarcha,imgclimber;

    //vars
    public static ArrayList<String> mNames = new ArrayList<>();
    public static ArrayList<String> mImageUrls = new ArrayList<>();




    //------------------------------

    Dialog popupdeportes,popupdeportes2;
    public static  int resta, calentero, caloriasacumuladas=0, calquemadas;
    ImageView Xfutbol;
    TextView tituloreco;
    Button btnaceptar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Deportes_firebase> listadeportes = new ArrayList<Deportes_firebase>();
    ArrayAdapter<Deportes_firebase> adaptador;

    Deportes_firebase item;
    Deportes_firebase item2;

    Button btnrealizar, btncancelar;
    Double a = ScrollingDetalle.Calorias_consumidas;
    ImageView gifdeporte, xbutton;
    TextView duracion, calorias;
    int i=0;
    TextView crono,caloriras_depor,duracion_depor;
    ImageView playbuttom,stopbuttom,rewindbuttom,imgdeporte;
    boolean isOn = false;
    int min=0,seg=0,mili=0;
    Handler h = new Handler();
    Thread cronos;
    MediaPlayer alarma;
    TextView relizado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__ejercicios2);
        popupdeportes = new Dialog(this);
        popupdeportes2 = new Dialog(this);
        final GridView gridView = findViewById(R.id.griddeportes);
        gridView.setOnItemClickListener(this);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ShowRecomendacion();

        inicializarfirebase();

        databaseReference.child("Deporte").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){



                    Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                    listadeportes.add(p);
                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);
                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);
                    gridView.setAdapter(adaptadorDeportes);

                }



                /*
                GenericTypeIndicator<ArrayList<Deportes_firebase>> t = new GenericTypeIndicator<ArrayList<Deportes_firebase>>(){};
                listadeportes = dataSnapshot.getValue(t);
                adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);
                //AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);
                gridView.setAdapter(adaptadorDeportes);
*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ejercicios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(Lista_Ejercicios2.this, ScrollingDetalle.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //<ALERTA RECOMENDADOS------------------------->
    public void ShowRecomendacion() {
        popupdeportes.setContentView(R.layout.recomendacion_alert);
        Xfutbol= (ImageView) popupdeportes.findViewById(R.id.Xfutbol);
        tituloreco = (TextView) popupdeportes.findViewById(R.id.tituloreco);
        btn1 = (Button) popupdeportes.findViewById(R.id.btnFut1);
        btn2 = (Button) popupdeportes.findViewById(R.id.btnFut2);
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = popupdeportes.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);


        adapter.setMlistener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {

                switch (position) {
                    case 0:
                        Query q = databaseReference.child("Deporte").orderByChild("nombre").equalTo("Caminar");
                        q.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()) {


                                    final Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                                    listadeportes.add(p);
                                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(), android.R.layout.simple_list_item_1, listadeportes);
                                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(), listadeportes);


                                    //Toast.makeText(Lista_Ejercicios2.this, ""+item2.getCalorias(), Toast.LENGTH_SHORT).show();

                                    popupdeportes.setContentView(R.layout.contendedor_alert);
                                    gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
                                    calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
                                    duracion = popupdeportes.findViewById(R.id.duracion_min2);
                                    btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
                                    btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
                                    xbutton = popupdeportes.findViewById(R.id.xContenedor);

                                    popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                                    relizado = popupdeportes2.findViewById(R.id.texttorelaizado);
                                    btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                                    btnaceptar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            popupdeportes2.dismiss();
                                        }
                                    });
                                    relizado.setText("Felicidades, haz quemado "+p.getCalorias()+" calorias");


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
                                            crono = popupdeportes.findViewById(R.id.crono);
                                            playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                                            stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                                            rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                                            alarma = MediaPlayer.create(Lista_Ejercicios2.this, R.raw.alarma);
                                            imgcaminar = p.getImagen();
                                            Glide.with(getApplicationContext())
                                                    .load(p.getImagen())
                                                    .crossFade()
                                                    .centerCrop()
                                                    .placeholder(R.drawable.imagen)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .thumbnail(0.5f)
                                                    .into(gifdeporte);

                                            calorias.setText(p.getCalorias());
                                            duracion.setText(p.getDuracion());

                                            calentero = Integer.valueOf(a.intValue());
                                            caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                                            resta = calentero - Integer.parseInt(calorias.getText().toString());
                                            calquemadas = Integer.parseInt(calorias.getText().toString());
                                            playbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = true;
                                                }

                                            });
                                            stopbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = false;
                                                }
                                            });
                                            rewindbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = false;
                                                    mili = 0;
                                                    seg = 0;
                                                    min = 0;
                                                    crono.setText("00:00:000");

                                                }
                                            });
                                            cronos = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    while (true) {
                                                        if (isOn) {
                                                            try {
                                                                Thread.sleep(1);

                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            mili++;
                                                            if (mili == 999) {
                                                                seg++;
                                                                mili = 0;
                                                            }
                                                            if (seg == 59) {
                                                                min++;
                                                                seg = 0;
                                                            }
                                                            h.post(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    String m = "", s = "", mi = "";
                                                                    if (mili < 10) {
                                                                        m = "00" + mili;
                                                                    } else if (mili < 100) {
                                                                        m = "0" + mili;
                                                                    } else {
                                                                        m = "" + mili;
                                                                    }
                                                                    if (seg < 10) {
                                                                        s = "0" + seg;
                                                                    } else {
                                                                        s = "" + seg;
                                                                    }
                                                                    if (min < 10) {
                                                                        mi = "0" + min;
                                                                    } else {
                                                                        mi = "" + min;
                                                                    }
                                                                    crono.setText(mi + ":" + s + ":" + m);
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
                                                    while (true) {
                                                        if (isOn) {
                                                            try {
                                                                Thread.sleep(1);

                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            if (seg == 30) {
                                                                starsonido();
                                                                isOn = false;
                                                                popupdeportes.dismiss();
                                                            }
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    if (seg == 30) {
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
                                            seg = 0;
                                            mili = 0;
                                            min = 0;
                                            isOn=false;
                                        }


                                        private void starsonido() {
                                            alarma.start();
                                        }
                                    });

                                    Glide.with(getApplicationContext())
                                            .load(p.getImagen())
                                            .crossFade()
                                            .centerCrop()
                                            .placeholder(R.drawable.imagen)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .thumbnail(0.5f)
                                            .into(gifdeporte);

                                    calorias.setText(p.getCalorias());
                                    duracion.setText(p.getDuracion());


                                    popupdeportes.show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;

                    case 1:
                        //
                        Query q2 = databaseReference.child("Deporte").orderByChild("nombre").equalTo("Flexion Brazo");
                        q2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()) {


                                    final Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                                    listadeportes.add(p);
                                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(), android.R.layout.simple_list_item_1, listadeportes);
                                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(), listadeportes);


                                    //Toast.makeText(Lista_Ejercicios2.this, ""+item2.getCalorias(), Toast.LENGTH_SHORT).show();

                                    popupdeportes.setContentView(R.layout.contendedor_alert);
                                    gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
                                    calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
                                    duracion = popupdeportes.findViewById(R.id.duracion_min2);
                                    btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
                                    btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
                                    xbutton = popupdeportes.findViewById(R.id.xContenedor);

                                    popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                                    btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                                    btnaceptar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            popupdeportes2.dismiss();
                                        }
                                    });
                                    relizado = popupdeportes.findViewById(R.id.texttorelaizado);
                                    relizado.setText("Felicidades, haz quemado "+p.getCalorias()+" calorias");

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
                                            crono = popupdeportes.findViewById(R.id.crono);
                                            playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                                            stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                                            rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                                            alarma = MediaPlayer.create(Lista_Ejercicios2.this, R.raw.alarma);
                                            imgcaminar = p.getImagen();
                                            Glide.with(getApplicationContext())
                                                    .load(p.getImagen())
                                                    .crossFade()
                                                    .centerCrop()
                                                    .placeholder(R.drawable.imagen)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .thumbnail(0.5f)
                                                    .into(gifdeporte);

                                            calorias.setText(p.getCalorias());
                                            duracion.setText(p.getDuracion());

                                            calentero = Integer.valueOf(a.intValue());
                                            caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                                            resta = calentero - Integer.parseInt(calorias.getText().toString());
                                            calquemadas = Integer.parseInt(calorias.getText().toString());
                                            playbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = true;
                                                }

                                            });
                                            stopbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = false;
                                                }
                                            });
                                            rewindbuttom.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    isOn = false;
                                                    mili = 0;
                                                    seg = 0;
                                                    min = 0;
                                                    crono.setText("00:00:000");

                                                }
                                            });
                                            cronos = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    while (true) {
                                                        if (isOn) {
                                                            try {
                                                                Thread.sleep(1);

                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            mili++;
                                                            if (mili == 999) {
                                                                seg++;
                                                                mili = 0;
                                                            }
                                                            if (seg == 59) {
                                                                min++;
                                                                seg = 0;
                                                            }
                                                            h.post(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    String m = "", s = "", mi = "";
                                                                    if (mili < 10) {
                                                                        m = "00" + mili;
                                                                    } else if (mili < 100) {
                                                                        m = "0" + mili;
                                                                    } else {
                                                                        m = "" + mili;
                                                                    }
                                                                    if (seg < 10) {
                                                                        s = "0" + seg;
                                                                    } else {
                                                                        s = "" + seg;
                                                                    }
                                                                    if (min < 10) {
                                                                        mi = "0" + min;
                                                                    } else {
                                                                        mi = "" + min;
                                                                    }
                                                                    crono.setText(mi + ":" + s + ":" + m);
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
                                                    while (true) {
                                                        if (isOn) {
                                                            try {
                                                                Thread.sleep(1);

                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                            if (seg == 30) {
                                                                starsonido();
                                                                isOn = false;
                                                                popupdeportes.dismiss();
                                                            }
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    if (seg == 30) {
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
                                            seg = 0;
                                            mili = 0;
                                            min = 0;
                                            isOn=false;
                                        }


                                        private void starsonido() {
                                            alarma.start();
                                        }
                                    });

                                    Glide.with(getApplicationContext())
                                            .load(p.getImagen())
                                            .crossFade()
                                            .centerCrop()
                                            .placeholder(R.drawable.imagen)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .thumbnail(0.5f)
                                            .into(gifdeporte);

                                    calorias.setText(p.getCalorias());
                                    duracion.setText(p.getDuracion());


                                    popupdeportes.show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;

                    case 2:

                        //
                        Query q3 = databaseReference.child("Deporte").orderByChild("nombre").equalTo("Sentadilla");
                        q3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){





                                    final  Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                                    listadeportes.add(p);
                                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);
                                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);




                                    //Toast.makeText(Lista_Ejercicios2.this, ""+item2.getCalorias(), Toast.LENGTH_SHORT).show();

                                    popupdeportes.setContentView(R.layout.contendedor_alert);
                                    gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
                                    calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
                                    duracion = popupdeportes.findViewById(R.id.duracion_min2);
                                    btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
                                    btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
                                    xbutton = popupdeportes.findViewById(R.id.xContenedor);


                                    popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                                    relizado = popupdeportes2.findViewById(R.id.texttorelaizado);
                                    relizado.setText("Felicidades, haz quemado "+p.getCalorias()+" calorias");
                                    btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                                    btnaceptar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            popupdeportes2.dismiss();
                                        }
                                    });
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
                                            crono = popupdeportes.findViewById(R.id.crono);
                                            playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                                            stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                                            rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                                            alarma  = MediaPlayer.create(Lista_Ejercicios2.this,R.raw.alarma);
                                            imgcaminar = p.getImagen();
                                            Glide.with(getApplicationContext())
                                                    .load(p.getImagen())
                                                    .crossFade()
                                                    .centerCrop()
                                                    .placeholder(R.drawable.imagen)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .thumbnail(0.5f)
                                                    .into(gifdeporte);

                                            calorias.setText(p.getCalorias());
                                            duracion.setText(p.getDuracion());

                                            calentero = Integer.valueOf(a.intValue());
                                            caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                                            resta= calentero - Integer.parseInt(calorias.getText().toString());
                                            calquemadas = Integer.parseInt(calorias.getText().toString());
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
                                                            if (seg==30){
                                                                starsonido();
                                                                isOn = false;
                                                                popupdeportes.dismiss();
                                                            }
                                                            runOnUiThread(new Runnable() {
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
                                            isOn=false;
                                        }


                                        private void starsonido(){
                                            alarma.start();
                                        }
                                    });

                                    Glide.with(getApplicationContext())
                                            .load(p.getImagen())
                                            .crossFade()
                                            .centerCrop()
                                            .placeholder(R.drawable.imagen)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .thumbnail(0.5f)
                                            .into(gifdeporte);

                                    calorias.setText(p.getCalorias());
                                    duracion.setText(p.getDuracion());

                                    popupdeportes.show();


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;

                    case 4:

                        Query q5 = databaseReference.child("Deporte").orderByChild("nombre").equalTo("Marcha");
                        q5.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){





                                    final  Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                                    listadeportes.add(p);
                                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);
                                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);




                                    //Toast.makeText(Lista_Ejercicios2.this, ""+item2.getCalorias(), Toast.LENGTH_SHORT).show();

                                    popupdeportes.setContentView(R.layout.contendedor_alert);
                                    gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
                                    calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
                                    duracion = popupdeportes.findViewById(R.id.duracion_min2);
                                    btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
                                    btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
                                    xbutton = popupdeportes.findViewById(R.id.xContenedor);

                                    popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                                    relizado = popupdeportes2.findViewById(R.id.texttorelaizado);
                                    relizado.setText("Felicidades, haz quemado "+p.getCalorias()+" calorias");
                                    btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                                    btnaceptar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            popupdeportes2.dismiss();
                                        }
                                    });

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
                                            crono = popupdeportes.findViewById(R.id.crono);
                                            playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                                            stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                                            rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                                            alarma  = MediaPlayer.create(Lista_Ejercicios2.this,R.raw.alarma);
                                            imgcaminar = p.getImagen();
                                            Glide.with(getApplicationContext())
                                                    .load(p.getImagen())
                                                    .crossFade()
                                                    .centerCrop()
                                                    .placeholder(R.drawable.imagen)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .thumbnail(0.5f)
                                                    .into(gifdeporte);

                                            calorias.setText(p.getCalorias());
                                            duracion.setText(p.getDuracion());

                                            calentero = Integer.valueOf(a.intValue());
                                            caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                                            resta= calentero - Integer.parseInt(calorias.getText().toString());
                                            calquemadas = Integer.parseInt(calorias.getText().toString());
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
                                                            if (seg==30){
                                                                starsonido();
                                                                isOn = false;
                                                                popupdeportes.dismiss();
                                                            }
                                                            runOnUiThread(new Runnable() {
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

                                                private void starsonido() {
                                                    alarma.start();
                                                }
                                            });
                                            t.start();
                                            popupdeportes.show();
                                            seg =0;
                                            mili=0;
                                            min =0;
                                            isOn=false;
                                        }


                                        private void starsonido(){
                                            alarma.start();
                                        }
                                    });

                                    Glide.with(getApplicationContext())
                                            .load(p.getImagen())
                                            .crossFade()
                                            .centerCrop()
                                            .placeholder(R.drawable.imagen)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .thumbnail(0.5f)
                                            .into(gifdeporte);

                                    calorias.setText(p.getCalorias());
                                    duracion.setText(p.getDuracion());

                                    popupdeportes.show();


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;

                    case 5:

                        //
                        Query q6 = databaseReference.child("Deporte").orderByChild("nombre").equalTo("Climber");
                        q6.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){





                                    final  Deportes_firebase p = objsnapshot.getValue(Deportes_firebase.class);
                                    listadeportes.add(p);
                                    adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);
                                    AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);




                                    //Toast.makeText(Lista_Ejercicios2.this, ""+item2.getCalorias(), Toast.LENGTH_SHORT).show();

                                    popupdeportes.setContentView(R.layout.contendedor_alert);
                                    gifdeporte = popupdeportes.findViewById(R.id.GifDeporte);
                                    calorias = popupdeportes.findViewById(R.id.caloriasdepor2);
                                    duracion = popupdeportes.findViewById(R.id.duracion_min2);
                                    btnrealizar = popupdeportes.findViewById(R.id.btnrealizar);
                                    btncancelar = popupdeportes.findViewById(R.id.btncancelar2);
                                    xbutton = popupdeportes.findViewById(R.id.xContenedor);

                                    popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                                    relizado = popupdeportes2.findViewById(R.id.texttorelaizado);
                                    relizado.setText("Felicidades, haz quemado "+p.getCalorias()+" calorias");
                                    btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                                    btnaceptar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            popupdeportes2.dismiss();
                                        }
                                    });
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
                                            crono = popupdeportes.findViewById(R.id.crono);
                                            playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                                            stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                                            rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                                            alarma  = MediaPlayer.create(Lista_Ejercicios2.this,R.raw.alarma);
                                            imgcaminar = p.getImagen();
                                            Glide.with(getApplicationContext())
                                                    .load(p.getImagen())
                                                    .crossFade()
                                                    .centerCrop()
                                                    .placeholder(R.drawable.imagen)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .thumbnail(0.5f)
                                                    .into(gifdeporte);

                                            calorias.setText(p.getCalorias());
                                            duracion.setText(p.getDuracion());

                                            calentero = Integer.valueOf(a.intValue());
                                            caloriasacumuladas = caloriasacumuladas + Integer.parseInt(calorias.getText().toString());
                                            resta= calentero - Integer.parseInt(calorias.getText().toString());
                                            calquemadas = Integer.parseInt(calorias.getText().toString());
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
                                                            if (seg==30){
                                                                starsonido();
                                                                isOn = false;
                                                                popupdeportes.dismiss();
                                                            }
                                                            runOnUiThread(new Runnable() {
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
                                            isOn=false;
                                        }


                                        private void starsonido(){
                                            alarma.start();
                                        }
                                    });

                                    Glide.with(getApplicationContext())
                                            .load(p.getImagen())
                                            .crossFade()
                                            .centerCrop()
                                            .placeholder(R.drawable.imagen)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .thumbnail(0.5f)
                                            .into(gifdeporte);

                                    calorias.setText(p.getCalorias());
                                    duracion.setText(p.getDuracion());

                                    popupdeportes.show();


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                }
            }

            private void ShowClimber() {


            }

            private void ShowMarchar() {


            }

            private void ShowSaltos() {


            }

            private void ShowSentadilla() {


            }

            private void ShowBrazo() {
                ;



            }

            private void ShowCaminar() {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupdeportes.dismiss();
            }
        });
        Xfutbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupdeportes.dismiss();

            }
        });
        getImages();

        popupdeportes.show();
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add(imgcaminar);
        mNames.add("Caminar");

        //mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        //mNames.add("Tijera");

        mImageUrls.add(imgflexiones);
        mNames.add("Flexiones");

        //mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        //mNames.add("Skipping");


        //mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //mNames.add("escaleras");

        //mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        //mNames.add("Bailar");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Salto cuerda");

        mImageUrls.add(imgsentadilla);
        mNames.add("Sentadilla");

        mImageUrls.add(imgsalto);
        mNames.add("Salto");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Eliptica");

        mImageUrls.add(imgmarcha);
        mNames.add("Marcha");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Titere");

        mImageUrls.add(imgclimber);
        mNames.add("Climber");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("ABD");
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
                crono = popupdeportes.findViewById(R.id.crono);
                playbuttom = popupdeportes.findViewById(R.id.playbuttom);
                stopbuttom = popupdeportes.findViewById(R.id.stopbuttom);
                rewindbuttom = popupdeportes.findViewById(R.id.rewindbuttom);
                alarma  = MediaPlayer.create(Lista_Ejercicios2.this,R.raw.alarma);

                popupdeportes2.setContentView(R.layout.ejerciciorealizado);
                relizado = popupdeportes2.findViewById(R.id.texttorelaizado);
                relizado.setText("Felicidades, haz quemado "+item.getCalorias()+" calorias");
                btnaceptar = popupdeportes2.findViewById(R.id.btnaceptar);
                btnaceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupdeportes2.dismiss();
                    }
                });
                Glide.with(Lista_Ejercicios2.this)
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
                                if (seg==30){
                                    starsonido();
                                    isOn = false;
                                    popupdeportes.dismiss();
                                }
                                runOnUiThread(new Runnable() {
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

                    private void starsonido() {
                        alarma.start();
                    }
                });
                t.start();
                popupdeportes.show();
                seg =0;
                mili=0;
                min =0;
                isOn=false;
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


    }
}