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


public class Mostrar_Deportes extends Fragment {

   //Declaracion de vistas y variables
   //botones (1=aceptar) y (2=cancelar) segun su layaut
   Button  btnFut1;
   Button btnFut2;

   Button btnNata1;
   Button btnNata2;

   Button btnCicli1;
   Button btnCicli2;

   Button btnPat1;
   Button btnPat2;

   Button btnBal1;
   Button btnBal2;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloFutbol;
    TextView mensajeFutbol;




    TextView tituloCicli;





    TextView tituloNatacion;
    TextView mensajeNatacion;

    //cierra la alerta segun su layaut
    ImageView Xfutbol;
    ImageView Xnatacion;

    //abre una alerta segun su layaut
    ImageButton futbol;
    ImageButton natacion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mostrar__deportes, container, false);

        //ImagButton---------------------------------------------------------------------------------
        futbol = (ImageButton) view.findViewById(R.id.futbol);
        natacion = (ImageButton) view.findViewById(R.id.natacion);

        //ImagView-----------------------------------------------------------------------------------
        Xfutbol = (ImageView) view.findViewById(R.id.Xfutbol);
        Xnatacion = (ImageView) view.findViewById(R.id.Xnatacion);


        //TextView-----------------------------------------------------------------------------------
        tituloFutbol = (TextView) view.findViewById(R.id.tituloFutbol);
        mensajeFutbol = (TextView) view.findViewById(R.id.mensajeFutbol);

        tituloNatacion = (TextView) view.findViewById(R.id.tituloNatacion);
        tituloCicli = (TextView) view.findViewById(R.id.tituloCicli);
        mensajeFutbol = (TextView) view.findViewById(R.id.mensajeFutbol);


        //Buttons------------------------------------//botones (1=aceptar) y (2=cancelar) segun su layaut
        btnFut1 = (Button) view.findViewById(R.id.btnFut1);
        btnFut2= (Button) view.findViewById(R.id.btnFut2);

        btnNata1 = (Button) view.findViewById(R.id.btnNata1);
        btnNata2= (Button) view.findViewById(R.id.btnNata2);


        //-----------------------------------------------------------------------------------------------------------------------
        //botones para llamar alerts
        futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowFutbol();
            }
        });

        natacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNatacion();
            }
        });
        return view;


    }
    //Shows-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public  void ShowFutbol() {
        final Dialog epicDialog = new Dialog(getContext());
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

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        public  void ShowNatacion(){
            final Dialog epicDialog = new Dialog(getContext());
            epicDialog.setContentView(R.layout.natacion_alert);
            Xnatacion= (ImageView) epicDialog.findViewById(R.id.Xnatacion);
            btnNata1 = (Button) epicDialog.findViewById(R.id.btnNata1);
            btnNata2= (Button) epicDialog.findViewById(R.id.btnNata2);
            tituloNatacion = (TextView) epicDialog.findViewById(R.id.tituloNatacion);
            mensajeNatacion = (TextView) epicDialog.findViewById(R.id.mensajeNatacion);

            // Aceptar ----------------------------------------------------------------------------
            btnNata1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                    epicDialog.dismiss();
                }
            });

            // Cancelar ----------------------------------------------------------------------------
            btnNata2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                    epicDialog.dismiss();
                }
            });
            //Cerrar-------------------------------------------------------------------------------
            Xnatacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    epicDialog.dismiss();

                }
            });
            epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            epicDialog.show();


        }

}
