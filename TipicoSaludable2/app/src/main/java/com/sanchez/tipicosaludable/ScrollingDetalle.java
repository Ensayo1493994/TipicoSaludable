package com.sanchez.tipicosaludable;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Historial;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class ScrollingDetalle extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private  FirebaseAuth firebaseAuth;
    public static final String EXTRA_PARAM_ID = "com.herprogramacion.comidas20184K.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Comida itemDetallado;
    private ImageView imagenExtendida, imgcalculo;
    private TextView informacion, informacionprote, informacioncarbo, txtcantidad, resultado, signosuma, signoigual;
    private Button btnconsumodealimeto, btncalcular;
    private double consumo=0, x;
    public static double canti;
    int dia, mes, año, diadespues;
    public static double Calorias_consumidas;
    public static ArrayList<UltimoConsumo> ultimoconsumo = new ArrayList<>();
    int cantidaddelalimento=0;
    int bound = ultimoconsumo.size(), imagenid, igual=0, caster, consultahistorial=0;
    String i;
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
    final Calendar c = Calendar.getInstance();

    private List<Historial> listahistorial = new ArrayList<Historial>();
    ArrayAdapter<Historial> adaptador;

    //FIREBASE
    FirebaseDatabase firebaseDatabase;
    FirebaseApp firebaseApp;
    DatabaseReference databaseReference;
    String nombreusuario;
    Historial p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_detalle);
        //DESCARGAR PDF
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
      //nombreusuario=  user.getDisplayName();




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DESCARGA DE PDF
                downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(itemDetallado.getReceta());
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

                Snackbar.make(view, "Descargando...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //usarToolbar();
        itemDetallado = Comida.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);

        inicializarFirebase();

        epicDialog = new Dialog(this);
        cantidadaconsumir = new Dialog(this);

        informacion = findViewById(R.id.informacion);
        informacioncarbo = findViewById(R.id.informacioncarbo);
        informacionprote = findViewById(R.id.informacionprote);

        btnconsumodealimeto = findViewById(R.id.btnconsumodealimento);
        btnconsumodealimeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    Calorias_consumidas=Calorias_consumidas+(consumo + Double.parseDouble(informacion.getText().toString()));
                }catch (Exception e){
                    Log.e("Error Calorias",e.getMessage());
                }

                showCalcularcantidad();

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
        toolbar.setTitle(itemDetallado.getNombre());

        cargarImagenExtendida();
    }

    private void inicializarFirebase() {
        firebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void showCalcularcantidad() {
        cantidadaconsumir.setContentView(R.layout.popup_cantidad);
        btnaceptar = cantidadaconsumir.findViewById(R.id.btnaceptarconsumir);
        edtxcantidad = cantidadaconsumir.findViewById(R.id.edtxcantidad);
        txtcantidad = cantidadaconsumir.findViewById(R.id.txtcantidad);
        resultado = cantidadaconsumir.findViewById(R.id.resultado);
        imgcalculo = cantidadaconsumir.findViewById(R.id.imgcalculo);
        btncalcular = cantidadaconsumir.findViewById(R.id.btncalcular);
        signosuma = cantidadaconsumir.findViewById(R.id.signosuma);
        signoigual = cantidadaconsumir.findViewById(R.id.signoigual);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //-----------------------MOSTRAR EL ALIMENTO Y LA CANTIDAD DE CAORIAS QUE COMERA---------------
                if (edtxcantidad.length()>0){
                    txtcantidad.setText(edtxcantidad.getText().toString());
                    signoigual.setText("=");
                    signosuma.setText("+");
                    Glide.with(imgcalculo.getContext()).load(itemDetallado.getIdDrawable()).into(imgcalculo);
                    Double resultadocal = Double.parseDouble(itemDetallado.getCalorias())*Double.parseDouble(edtxcantidad.getText().toString());
                    caster = Integer.valueOf(resultadocal.intValue());

                    resultado.setText(""+caster);

                }else{
                    edtxcantidad.setError("Campo Vacio");
                }



            }
        });




        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtxcantidad.length()>0){
                    cantidaddelalimento = Integer.parseInt(edtxcantidad.getText().toString());
                    if (cantidaddelalimento>20){
                        edtxcantidad.setError("No puedes comer tanto");
                        edtxcantidad.setText("");
                    }
                    else {



                        //---------------------- AGREGAR LA IMAGEN AL INICIO -----------------
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

                        //--------------------- FIN -----------------


                        //Calorias_consumidas = cantidaddelalimento*(consumo+Double.parseDouble(informacion.getText().toString()));
                        //Toast.makeText(ScrollingDetalle.this, ""+Calorias_consumidas, Toast.LENGTH_SHORT).show();
                       //Toast.makeText(ScrollingDetalle.this, ""+Calorias_consumidas, Toast.LENGTH_SHORT).show();


                        //----Agregar DATOS HISTORIAL FIREBASE---------
                        Calorias_consumidas = cantidaddelalimento*(consumo+ Double.parseDouble(informacion.getText().toString()));
                        dia = c.get(Calendar.DAY_OF_MONTH);
                        mes = c.get(Calendar.MONTH);
                        año = c.get(Calendar.YEAR);

                        //Toast.makeText(ScrollingDetalle.this, ""+dia+"/"+mes+"/"+año, Toast.LENGTH_SHORT).show();


                        //------ CONSULTAR SI EL USUARIO YA AH CONSUMIDO ALGO ANTES

                        Query consulta = databaseReference.child("Historial").child("usuario").equalTo("Felixangel1 Quelal");
                        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){
                                    consultahistorial = consultahistorial+1;
                                    Toast.makeText(ScrollingDetalle.this, "encontro "+consultahistorial, Toast.LENGTH_SHORT).show();
                                    /*

                                    p  = objsnapshot.getValue(Historial.class);
                                    listahistorial.add(p);

                                    adaptador  = new ArrayAdapter<Historial>(ScrollingDetalle.this,android.R.layout.simple_list_item_1,listahistorial);



                                    //-------------------------------ACTUALIZAR INFO POR USUARIO----------------------------------

                                    Toast.makeText(ScrollingDetalle.this, ""+consultahistorial, Toast.LENGTH_SHORT).show();
                                    if (consultahistorial==1){
                                        //Historial p = new Historial();
                                        Historial a = new Historial();
                                        a.setUid(p.getUid());
                                        a.setCalorias_acumuladas(Lista_Ejercicios2.caloriasacumuladas);
                                        a.setCalorías_consumidas(Calorias_consumidas);
                                        a.setCalorías_excedentes(canti);
                                        a.setCalorías_finales(Lista_Ejercicios2.resta);
                                        a.setCalorías_máximas(CaloriasActivity.actmb);
                                        a.setFecha(""+dia+"-"+(mes+1)+"-"+año);




                                        FirebaseUser user = firebaseAuth.getCurrentUser();


                                        a.setUsuario("Felixangel1 Quelal");
                                        databaseReference.child("Historial").child(a.getUid()).setValue(a);

                                    }*/
                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                        Historial p = new Historial();
                        p.setUid(UUID.randomUUID().toString());
                        p.setCalorias_acumuladas(Deportesfirebase.caloriasacumuladas);
                        p.setCalorías_consumidas(Calorias_consumidas);
                        p.setCalorías_excedentes(canti);
                        p.setCalorías_finales(Deportesfirebase.resta);
                        p.setCalorías_máximas(CaloriasActivity.actmb);
                        p.setFecha(""+dia+"-"+(mes+1)+"-"+año);




                        FirebaseUser user = firebaseAuth.getCurrentUser();


                        //p.setUsuario(nombreusuario);
                        GregorianCalendar calendar = new GregorianCalendar();
                        if (calendar.isLeapYear(año)){
                            Toast.makeText(ScrollingDetalle.this, "El año es bisiesto", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(ScrollingDetalle.this, "No es bisiesto", Toast.LENGTH_SHORT).show();

                        }


                        databaseReference.child("Historial").child(p.getUid()).setValue(p);

                        //------VALIDACION 1 DIA DESPUES --------------------
                        diadespues = dia+1;
                        //Toast.makeText(ScrollingDetalle.this, "dia despues"+diadespues, Toast.LENGTH_SHORT).show();
                        /*while (dia<diadespues){
                            final Calendar c2 = Calendar.getInstance();
                            dia = c2.get(Calendar.DAY_OF_MONTH);

                            if (dia==diadespues){

                                Toast.makeText(ScrollingDetalle.this, "ya paso un dia", Toast.LENGTH_SHORT).show();

                            }
                        }*/



                        //----Agregar DATOS HISTORIAL FIREBASE---------


                        //Toast.makeText(ActividadDetalle.this, ""+Calorias_consumidas, Toast.LENGTH_SHORT).show();
                        x=((CaloriasActivity.actmb*90)/100);
                       // Toast.makeText(ScrollingDetalle.this, ""+CaloriasActivity.actmb, Toast.LENGTH_SHORT).show();
                        if (Calorias_consumidas>CaloriasActivity.actmb){
                            //Toast.makeText(ScrollingDetalle.this, ""+CaloriasActivity.actmb, Toast.LENGTH_SHORT).show();


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
                            finish();

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
                Intent intent = new Intent(ScrollingDetalle.this,Lista_Ejercicios2.class);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
