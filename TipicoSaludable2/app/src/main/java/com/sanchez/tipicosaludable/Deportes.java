package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Deportes extends AppCompatActivity {

    Dialog epicDialog;

    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button  btnFut1,btnFut2,
            btnNata1,btnNata2
                    ;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloFutbol, mensajeFutbol,
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
                ShowFutbol();
            }
        });


        //-----------------------------------------------------------------------------------------------------------------------

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
