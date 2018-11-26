package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

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

    //vars
    public static ArrayList<String> mNames = new ArrayList<>();
    public static ArrayList<String> mImageUrls = new ArrayList<>();



    //------------------------------

    Dialog popupdeportes;
    public static  int resta, calentero, caloriasacumuladas=0;
    ImageView Xfutbol;
    TextView tituloreco;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Deportes_firebase> listadeportes = new ArrayList<Deportes_firebase>();
    ArrayAdapter<Deportes_firebase> adaptador;

    Deportes_firebase item;

    Button btnrealizar, btncancelar;
    Double a = ScrollingDetalle.Calorias_consumidas;
    ImageView gifdeporte, xbutton;
    TextView duracion, calorias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__ejercicios2);
        popupdeportes = new Dialog(this);
        final GridView gridView = findViewById(R.id.griddeportes);
        gridView.setOnItemClickListener(this);

        ShowRecomendacion();

        inicializarfirebase();

        databaseReference.child("Deporte").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Deportes_firebase>> t = new GenericTypeIndicator<ArrayList<Deportes_firebase>>(){};
                listadeportes = dataSnapshot.getValue(t);
                adaptador = new ArrayAdapter<Deportes_firebase>(getApplicationContext(),android.R.layout.simple_list_item_1,listadeportes);

                //AdaptadorComida adaptadorComida = new AdaptadorComida(getContext(),listacomida);
                AdaptadorDeportes adaptadorDeportes = new AdaptadorDeportes(getApplicationContext(),listadeportes);
                gridView.setAdapter(adaptadorDeportes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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

        /*
        adapter.setMlistener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {


                switch (position){
                    case 0:
                        ShowCaminar();
                        break;

                    case 1:
                        ShowBrazo();
                        break;

                    case 2:
                        ShowSentadilla();
                        break;

                    case 3:
                        ShowSaltos();
                        break;

                    case 4:
                        ShowMarchar();
                        break;

                    case 5:
                        ShowClimber();
                        break;
                }
            }
        });*/

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

        mImageUrls.add(" https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/caminar.png?alt=media&token=c4f6967b-78c5-4bdc-8ccf-20e706ce034e");
        mNames.add("Caminar");

        //mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        //mNames.add("Tijera");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/brazo.png?alt=media&token=2dff3d1a-a385-4f9e-9b1a-121dd2c7ff20");
        mNames.add("Flexiones");

        //mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        //mNames.add("Skipping");


        //mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //mNames.add("escaleras");

        //mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        //mNames.add("Bailar");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Salto cuerda");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/sentadilla.png?alt=media&token=d6c6df4c-507f-4ab1-ad21-b3078c0d11ad");
        mNames.add("Sentadilla");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/saltos.png?alt=media&token=931e2b1d-ecf1-4c9a-8fff-43f7510c6a02");
        mNames.add("Salto");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Eliptica");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/marcha.png?alt=media&token=103be99b-f9dd-4314-9bb5-7af4d0969292");
        mNames.add("Marcha");

        //mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        //mNames.add("Titere");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/climber.png?alt=media&token=3d2ba290-4e81-473f-9534-4d76c54e9a82");
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
                Glide.with(getApplicationContext())
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
