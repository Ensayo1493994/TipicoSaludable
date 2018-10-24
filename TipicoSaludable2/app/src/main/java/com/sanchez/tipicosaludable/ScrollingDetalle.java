package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ScrollingDetalle extends AppCompatActivity {
    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Comida itemDetallado;
    private ImageView imagenExtendida;
    private TextView informacion, informacionprote, informacioncarbo;
    private Button btnconsumodealimeto;
    private double consumo=0, x;
    private double canti;
    public static double Calorias_consumidas;
    public static ArrayList<UltimoConsumo> ultimoconsumo = new ArrayList<>();
    int cantidaddelalimento=0,i =0;
    int bound = ultimoconsumo.size(), imagenid, igual=0;
    UltimoConsumo consumo1 = new UltimoConsumo();
    //-----------------------------
    //Agregar lo de las alertas
    Dialog epicDialog, cantidadaconsumir;
    ImageView cerrar, cerrarPasada;
    Button btnContinuar,btnCancelar, btnContinuar2,btnCancelar2,btnok, btnaceptar;
    TextView titleTope,mensajeTope,mensajePasada,titlePasada;
    EditText edtxcantidad;
    //DESCARGA DE PDF
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_detalle);
        //DESCARGAR PDF
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DESCARGA DE PDF
                downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("https://www.hogaruniversal.com/sites/default/files/Aborrajado.pdf");
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //usarToolbar();
        itemDetallado = Comida.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);



        epicDialog = new Dialog(this);
        cantidadaconsumir = new Dialog(this);

        informacion = findViewById(R.id.informacion);
        informacioncarbo = findViewById(R.id.informacioncarbo);
        informacionprote = findViewById(R.id.informacionprote);

        btnconsumodealimeto = findViewById(R.id.btnconsumodealimento);
        btnconsumodealimeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--------TERMINAR DE ARREGLARLO

                consumo1.setIdDrawable(Fragment_galeria.n);
                consumo1.setNombre(Fragment_galeria.nombrealimento);
                if (bound>0){
                    for ( imagenid = 0; imagenid < bound; imagenid= imagenid+1){


                        if ((ultimoconsumo.get(imagenid).getIdDrawable()==Fragment_galeria.n) ){
                            //Toast.makeText(ActividadDetalle.this, "encontro igual" + ultimoconsumo.get(imagenid).getIdDrawable()+ " "+ Fragment_galeria.n, Toast.LENGTH_SHORT).show();
                            igual = igual+1;
                        }
                    }
                    if(igual==0){
                        ultimoconsumo.add(consumo1);
                    }
                }else {
                    ultimoconsumo.add(consumo1);

                }

                bound = ultimoconsumo.size();//Toast.makeText(ActividadDetalle.this, ""+itemDetallado.getIdDrawable(), Toast.LENGTH_SHORT).show();
                showCalcularcantidad();
                Calorias_consumidas=Calorias_consumidas+(consumo+ Integer.parseInt(informacion.getText().toString()));
                /*x=((CaloriasActivity.actmb*90)/100);
                if (Calorias_consumidas>CaloriasActivity.actmb){

                    canti= (itemDetallado.getCalorias()-CaloriasActivity.actmb);
                    Toast.makeText(ActividadDetalle.this, "Te has pasado "+canti+" calorias",
                            Toast.LENGTH_LONG).show();
                    showPasada();

                }
                else  if(Calorias_consumidas==CaloriasActivity.actmb){
                    Toast.makeText(ActividadDetalle.this, "No debes comsumir mas alimentos",
                            Toast.LENGTH_LONG).show();
                    ShowTope();

                }
                else if(Calorias_consumidas>=x && x<CaloriasActivity.actmb){
                    canti= (CaloriasActivity.actmb-Calorias_consumidas);
                    Toast.makeText(ActividadDetalle.this, "Te faltan "+canti+" calorias",
                            Toast.LENGTH_LONG).show();
                    ShowTope();
                }*/
            }
        });

        informacion.setText(""+itemDetallado.getCalorias());
        informacioncarbo.setText(itemDetallado.getCarbohidratos());
        informacionprote.setText(itemDetallado.getProteinas());

        cargarImagenExtendida();
    }

    private void showCalcularcantidad() {
        cantidadaconsumir.setContentView(R.layout.popup_cantidad);
        btnaceptar = cantidadaconsumir.findViewById(R.id.btnaceptarconsumir);
        edtxcantidad = cantidadaconsumir.findViewById(R.id.edtxcantidad);

        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtxcantidad.length()>0){
                    cantidaddelalimento = Integer.parseInt(edtxcantidad.getText().toString());
                    if (cantidaddelalimento>=20){
                        edtxcantidad.setError("No puedes comer tanto");
                        edtxcantidad.setText("");

                    }
                    else {
                        Calorias_consumidas = cantidaddelalimento*(consumo+ Integer.parseInt(informacion.getText().toString()));
                        //Toast.makeText(ActividadDetalle.this, ""+Calorias_consumidas, Toast.LENGTH_SHORT).show();
                        x=((CaloriasActivity.actmb*90)/100);
                        if (Calorias_consumidas>CaloriasActivity.actmb){

                            canti= (Calorias_consumidas-CaloriasActivity.actmb);
                            Toast.makeText(ScrollingDetalle.this, "Te has pasado "+canti+" calorias",
                                    Toast.LENGTH_LONG).show();
                            showPasada();
                            cantidadaconsumir.dismiss();

                        }
                        else  if(Calorias_consumidas==CaloriasActivity.actmb){
                            Toast.makeText(ScrollingDetalle.this, "No debes comsumir mas alimentos",
                                    Toast.LENGTH_LONG).show();
                            ShowTope();
                            cantidadaconsumir.dismiss();

                        }
                        else if(Calorias_consumidas>=x && x<CaloriasActivity.actmb){
                            canti= (CaloriasActivity.actmb-Calorias_consumidas);
                            Toast.makeText(ScrollingDetalle.this, "Te faltan "+canti+" calorias",
                                    Toast.LENGTH_LONG).show();
                            ShowTope();
                            cantidadaconsumir.dismiss();
                        }
                        else{
                            Intent intent = new Intent(ScrollingDetalle.this,Menu_Lateral.class);
                            startActivity(intent);

                        }

                    }

                }else{
                    edtxcantidad.setError("Campo Vacio");

                }





            }
        });
        cantidadaconsumir.show();



    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }

    /*private void usarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }*/
    public  void ShowTope(){
        epicDialog.setContentView(R.layout.alerta_tope);
        //Cerrar la ventana de alerta

        cerrar =(ImageView) epicDialog.findViewById(R.id.cerrar);
        //Aceptar consumiendo alimentos

        btnContinuar = (Button) epicDialog.findViewById(R.id.btnContinuar);

        //deshabilita para que no consuma mas alimentos
        btnCancelar = (Button)epicDialog.findViewById(R.id.btnCancelar);

        //ejecuta la ventana de alerta tope
        titleTope = (TextView)epicDialog.findViewById(R.id.titleTope);
        mensajeTope = (TextView) epicDialog.findViewById(R.id.mensajeTope);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

                Toast.makeText(ScrollingDetalle.this,"Aceptaste",Toast.LENGTH_SHORT).show();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                cantidadaconsumir.dismiss();
                Toast.makeText(ScrollingDetalle.this,"Cancelaste",Toast.LENGTH_SHORT).show();

            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                cantidadaconsumir.dismiss();
                Toast.makeText(ScrollingDetalle.this,"Ten cuidado... !!!",Toast.LENGTH_SHORT).show();

            }
        });
        //hace que el fondo de la alerta sea trasnparente
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


    }
    public void showPasada(){
        epicDialog.setContentView(R.layout.alerta_pasada);
        //Cerrar la ventana de alerta

        cerrarPasada =(ImageView) epicDialog.findViewById(R.id.cerrarPasada);
        //Aceptar consumiendo alimentos

        btnContinuar2 = (Button) epicDialog.findViewById(R.id.btnContinuar2);

        //deshabilita para que no consuma mas alimentos
        btnCancelar2 = (Button)epicDialog.findViewById(R.id.btnCancelar2);

        //ejecuta la ventana de alerta tope
        titlePasada = (TextView)epicDialog.findViewById(R.id.titlePasada);
        mensajePasada = (TextView) epicDialog.findViewById(R.id.mensajePasada);

        btnContinuar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                Intent intent = new Intent(ScrollingDetalle.this,Deportes.class);
                startActivity(intent);
                finish();

            }
        });
        btnCancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                Toast.makeText(ScrollingDetalle.this,"Cuida tu alimentaci�n!!",Toast.LENGTH_SHORT).show();

            }
        });
        cerrarPasada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                Toast.makeText(ScrollingDetalle.this,"Cuidate..",Toast.LENGTH_SHORT).show();

            }
        });
        //hace que el fondo de la alerta sea trasnparente
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }

}
