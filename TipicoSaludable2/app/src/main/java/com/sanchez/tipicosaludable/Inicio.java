package com.sanchez.tipicosaludable;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sanchez.tipicosaludable.model.Historial;
import com.sanchez.tipicosaludable.model.Perfil;
import com.sanchez.tipicosaludable.model.Usuarios;

import java.util.ArrayList;


public class Inicio extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    public static int parseo, entero;
    Double caloriasmaximas, caloriasconsumidas;
    FirebaseApp firebaseApp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, tablaperfil;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth firebaseAuth;
    FragmentManager fragmentManager ;

    ArrayList<Historial> historial_lista = new ArrayList<Historial>();

    Historial usuariofound;
    ArrayList<Perfil> perfil_lista = new ArrayList<Perfil>();
    ArrayAdapter<Perfil> adaptadorperfil;
    public  static int temp=0, maximas;
    String nombreusuario;

    //animacion fondo
    LinearLayout bglayout;
    AnimationDrawable animationDrawable;


    //Grafica de Barras

    BarChart barChart;

    SharedPreferences preferencias;


    //datos de los ejes de la grafica de barras, datos de la torta pastel
    private String[] dias = new String[]{"Maxima", "Cal Consumidas", "Cal Quemadas"};
    //private int[] calorias = new int[]{2345, 1234, 3467, 2456, 1654, 2987, 1879, 1963}; // variables con las calorias de todos los dias la primera es la caloria maxima
    private int[] calorias;
    //private int[] color = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY, Color.CYAN, Color.MAGENTA, Color.LTGRAY};
    private int[] color = new int[]{Color.GREEN, Color.YELLOW, Color.RED};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);
        GridView gridView = (GridView) findViewById(R.id.ultimoconsumo);
        barChart = findViewById(R.id.barchart);
        getSupportActionBar().setTitle("Inicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView cal_cosumidas = findViewById(R.id.cal_cons);

        //createCharts();




        preferencias = getSharedPreferences("ejemplo",Context.MODE_PRIVATE);
        String cal = preferencias.getString("calorias","0");
        Double a = Double.parseDouble(cal);
        entero = a.intValue();
        //Toast.makeText(this, ""+entero, Toast.LENGTH_SHORT).show();
        cal_cosumidas.setText(""+entero);
        //falta parsear



        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();



        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    nombreusuario=  user.getDisplayName();
                    //Toast.makeText(getContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }else {

                }

            }
        };







        TextView textView = findViewById(R.id.txtnoconsumo);



        final TextView textView2 = findViewById(R.id.cal_per);
        inicializarfirebase();



        //-------------CONSULTAR INFO POR USUARIO--------------

        /*
        textView2.setText(""+MainActivity.entero);


        maximas = Integer.parseInt(textView2.getText().toString());
        Double ash = ScrollingDetalle.ufff;
        parseo = Integer.valueOf(ash.intValue());
        calorias = new int[]{Integer.parseInt(textView2.getText().toString()),Integer.parseInt(cal_cosumidas.getText().toString()),Lista_Ejercicios2.calquemadas};

        createCharts();*/


        if(user != null){
        Query q2 = tablaperfil.orderByChild("nombre").equalTo(user.getDisplayName());
        //Query q2 =tablaperfil.orderByChild("nombre").equalTo("Leonardo Sanchez");
        q2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                        Perfil p = objSnapshot.getValue(Perfil.class);
                        perfil_lista.add(p);
                        adaptadorperfil = new ArrayAdapter<Perfil>(getApplicationContext(),android.R.layout.simple_list_item_1,perfil_lista);

                        caloriasmaximas = p.getCalorías_maximas();
                    }
                    entero = Integer.valueOf(caloriasmaximas.intValue());
                    textView2.setText(""+entero);
                    maximas = Integer.parseInt(textView2.getText().toString());
                    Double ash = ScrollingDetalle.ufff;
                    parseo = Integer.valueOf(ash.intValue());
                    calorias = new int[]{Integer.parseInt(textView2.getText().toString()),Integer.parseInt(cal_cosumidas.getText().toString()),Lista_Ejercicios2.calquemadas};

                    createCharts();

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        }




        //-------------CONSULTAR INFO POR USUARIO--------------


        if (ScrollingDetalle.ultimoconsumo.size()>0){
            AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(Inicio.this,ScrollingDetalle.ultimoconsumo);
            gridView.setAdapter(adaptador);

        }
        else {
            textView.setText("No has consumido nada hoy");

        }
        /*caloriasconsumidas = ScrollingDetalle.Calorias_consumidas;
        entero = Integer.valueOf(caloriasconsumidas.intValue());

        cal_cosumidas.setText(""+entero);*/

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    private void inicializarfirebase() {
        firebaseApp.initializeApp(Inicio.this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Historial");
        tablaperfil = firebaseDatabase.getReference("Perfil");



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //metodos de la grafica

    //pesonalizar grafica
    private Chart getSameChart(Chart chart, String descripcion, int textcolor, int background, int animateY) {
        chart.getDescription().setText(descripcion);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart); //llamamos la leyenda
        return chart;
    }

    //personalizar leyenda

    private void legend(Chart chart) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);


        //llenar leyenda

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < dias.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = color[i];
            entry.label = dias[i];

            entries.add(entry);
        }

        legend.setCustom(entries);
    }

    private ArrayList<BarEntry> getBarEntries() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < calorias.length; i++)
            entries.add(new BarEntry(i, calorias[i]));// especificamos el valor de los ejes i = x
        return entries;
    }



    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);//esto es para ver de cuanto en cuanto se van a mostrar los datos en la grafica
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(dias));
        axis.setEnabled(false);

    }

    private void axisLeft(YAxis axis) { //recibimos en el eje y
        axis.setSpaceTop(25);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis) { //deshabilitamos uno de los lados
        axis.setEnabled(false);
    }


    //creamos las graficas -------------------------------------------------------------------------------------------------

    public void createCharts() {
        barChart = (BarChart) getSameChart(barChart, "Cal", Color.BLACK, Color.BLUE, 3000);
        barChart.setDrawGridBackground(false);
        barChart.setBackgroundColor(Inicio.this.getColor(R.color.fondog));
        barChart.setDrawBarShadow(true); // sombras de las barras
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

    }

    private DataSet getData(DataSet dataSet) { // poner los datos dentto de la grafica

        dataSet.setColors(color);
        dataSet.setValueTextSize(Color.YELLOW);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData() {

        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(), ""));
        barDataSet.setBarShadowColor(Color.WHITE);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }




}