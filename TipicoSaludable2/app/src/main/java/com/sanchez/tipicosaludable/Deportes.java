package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;

import java.util.ArrayList;
import java.util.List;

public class Deportes extends AppCompatActivity {


    //Infinitecard
    AdapterCard adapterCard;
    Button btn1,btn2;
    InfiniteCardView infiniteCardView;
    List<Integer> Images = new ArrayList<>();




    Dialog epicDialog;

    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button  btnFut1,btnFut2,
            btnNata1,btnNata2
                    ;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloFutbol, mensajeFutbol,tituloreco,
            titlelNatacion, messeageNatacion
                    ;

    //cierra la alerta segun su layaut
    ImageView Xfutbol,
            Xnatacion
                    ;

    //abre una alerta segun su layaut
    ImageButton futbol,
            natacion
                    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);

        epicDialog = new Dialog(this);

        //ImagButtons
        futbol=(ImageButton)findViewById(R.id.futbol);
        natacion=(ImageButton)findViewById(R.id.natacion);

        //-----------------------------------------------------------------------------------------------------------------------
        //botones para llamar alerts
        futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShowFutbol();
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                ShowRecomendacion();
            }
        });


        //-----------------------------------------------------------------------------------------------------------------------




    }
    //recomendacion

    public void ShowRecomendacion(){

        epicDialog.setContentView(R.layout.recomendacion_alert);
        tituloreco = (TextView) epicDialog.findViewById(R.id.tituloreco);
        infiniteCardView = (InfiniteCardView) epicDialog.findViewById(R.id.view);
        btn1 = (Button) epicDialog.findViewById(R.id.btnFut1);
        btn2 = (Button) epicDialog.findViewById(R.id.btnFut2);

        Images.add (R.drawable.logo1);
        Images.add (R.drawable.logo2);
        Images.add(R.drawable.logoredondo);
        Images.add(R.drawable.logor);

        adapterCard = new AdapterCard(this, Images);

        infiniteCardView.setClickable(true);
        infiniteCardView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT);
        infiniteCardView.setAnimInterpolator(new LinearInterpolator());
        infiniteCardView.setTransformerToFront(new DefaultTransformerToFront());
        infiniteCardView.setTransformerToBack(new DefaultTransformerToBack());
        infiniteCardView.setZIndexTransformerToBack(new DefaultZIndexTransformerCommon());
        infiniteCardView.setAdapter(adapterCard);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infiniteCardView.bringCardToFront(adapterCard.getCount()-1);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infiniteCardView.bringCardToFront(1);
            }
        });

        epicDialog.show();
    }


    //Shows
    public void ShowFutbol(){
        epicDialog.setContentView(R.layout.futbol_alert);
    Xfutbol = (ImageView) epicDialog.findViewById(R.id.Xfutbol);
    btnFut1 = (Button) epicDialog.findViewById(R.id.btnFut1);
    btnFut2 = (Button) epicDialog.findViewById(R.id.btnFut2);
    tituloFutbol = (TextView) epicDialog.findViewById(R.id.tituloFutbol);
    mensajeFutbol = (TextView) epicDialog.findViewById(R.id.mensajeFutbol);

    // Aceptar ----------------------------------------------------------------------------
        btnFut1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();

            epicDialog.dismiss();
        }
    });

    // Cancelar ----------------------------------------------------------------------------
        btnFut2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
            epicDialog.dismiss();
        }
    });
    //Cerrar-------------------------------------------------------------------------------
        Xfutbol.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            epicDialog.dismiss();

        }
    });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
}




}   //------------------------------------------------------------------- final
