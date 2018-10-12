package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Deportes2 extends Fragment {
    Dialog epicDialog;

    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button btnFut1,btnFut2,
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_deportes2, container, false);
        epicDialog = new Dialog(getContext());
        futbol=(ImageButton)vista.findViewById(R.id.futbol);
        natacion=(ImageButton)vista.findViewById(R.id.natacion);
        futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowFutbol();
            }

            private void ShowFutbol() {
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
                        Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();

                        epicDialog.dismiss();
                    }
                });

                // Cancelar ----------------------------------------------------------------------------
                btnFut2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
        });


        return vista;
    }

}
