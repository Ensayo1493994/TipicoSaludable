package com.sanchez.tipicosaludable;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private AdaptadorComida adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        gridView = findViewById(R.id.grid);
        adaptador = new AdaptadorComida(this, Fragment_galeria.listacomida);
        gridView .setAdapter(adaptador);
        gridView.setOnItemClickListener(this);
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Comida item = Comida.getItem(position) ;

        Comida item = (Comida) adapterView.getItemAtPosition(position);


        Intent intent = new Intent(this, ActividadDetalle.class);
        intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());
        startActivity(intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, new Pair<View, String>(view.findViewById(R.id.imagen_comida),ActividadDetalle.VIEW_NAME_HEADER_IMAGE)

            );
            ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle());
        }else{
            startActivity(intent);

        }



    }
}
