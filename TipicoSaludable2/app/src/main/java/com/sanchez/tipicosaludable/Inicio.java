package com.sanchez.tipicosaludable;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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


public class Inicio extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    int size, entero;
    Double caloriasmaximas;
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
    public  static int temp=0;
    String nombreusuario;


    //Grafica de Barras

    BarChart barChart;


    //datos de los ejes de la grafica de barras, datos de la torta pastel
    private String[] dias = new String[]{"Maxima", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
    private int[] calorias = new int[]{2345, 1234, 3467, 2456, 1654, 2987, 1879, 1963}; // variables con las calorias de todos los dias la primera es la caloria maxima
    private int[] color = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE, Color.GRAY, Color.CYAN, Color.MAGENTA, Color.LTGRAY};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        GridView gridView = (GridView) vista.findViewById(R.id.ultimoconsumo);
        barChart = vista.findViewById(R.id.barchart);
        createCharts();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //nombreusuario=  user.getDisplayName();
        //Toast.makeText(getContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();




        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //nombreusuario=  user.getDisplayName();
                    Toast.makeText(getContext(), ""+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }else {

                }

            }
        };





        TextView textView = vista.findViewById(R.id.txtnoconsumo);

        final TextView textView2 = vista.findViewById(R.id.cal_per);
        inicializarfirebase();

        /*Query q = tablaperfil.orderByChild("nombre").equalTo(nombreusuario);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Perfil p = objSnapshot.getValue(Perfil.class);
                    perfil_lista.add(p);
                    //adaptadorperfil = new ArrayAdapter<Perfil>(Login.this,android.R.layout.simple_list_item_1,perfil_lista);
                    adaptadorperfil = new ArrayAdapter<Perfil>(getContext(),android.R.layout.simple_list_item_1,perfil_lista);
                    //Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();
                    temp=1;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        //-------------CONSULTAR INFO POR USUARIO--------------


        if(user != null){
            Query q2 = tablaperfil.orderByChild("nombre").equalTo(user.getDisplayName());
            //Query q =databaseReference.orderByChild("usuario").equalTo("INGRID KATERINE VELASCO LOPEZ");
            q2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*GenericTypeIndicator<ArrayList<Historial>> t = new GenericTypeIndicator<ArrayList<Historial>>(){};
                    historial_lista = dataSnapshot.getValue(t);
                    adaptador = new ArrayAdapter<Historial>(getContext(),android.R.layout.simple_list_item_1,historial_lista);
                    Historial p = new Historial();
                    //textView2.setText(""+p.getCalorías_máximas());
                    Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();*/
                    try{
                        for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                            Perfil p = objSnapshot.getValue(Perfil.class);
                            perfil_lista.add(p);
                            adaptadorperfil = new ArrayAdapter<Perfil>(getContext(),android.R.layout.simple_list_item_1,perfil_lista);
                            //Toast.makeText(getContext(), ""+p.getCalorías_máximas(), Toast.LENGTH_SHORT).show();

                            caloriasmaximas = p.getCalorías_maximas();








                        }
                        entero = Integer.valueOf(caloriasmaximas.intValue());
                        textView2.setText(""+entero);

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
            AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(getContext(),ScrollingDetalle.ultimoconsumo);
            gridView.setAdapter(adaptador);

        }
        else {
            textView.setText("No has consumido nada hoy");

        }






        return vista;
    }

    private void inicializarfirebase() {
        firebaseApp.initializeApp(getContext());
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
        barChart = (BarChart) getSameChart(barChart, "Calorias", Color.BLACK, Color.WHITE, 3000);
        barChart.setDrawGridBackground(true);
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
