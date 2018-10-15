package com.example.saranghae.crud_firebase_deportes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saranghae.crud_firebase_deportes.Model.Deportes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String[]datos=
            new String[]{"Deportes","Ejercicios","Actividad Fisica"};

    private Spinner spinner;

    private List<Deportes> listadeportes = new ArrayList<Deportes>();
    ArrayAdapter<Deportes> deportesArrayAdapter;

    Button btnguardar, btnactualizar, btneliminar;
    EditText edtnombre, edtcalorias, edtdescripcion;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databaserefence;

    ListView listView;
    Deportes deportesSelected;
    public  static String categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner =(Spinner)findViewById(R.id.spinner);
        listView = findViewById(R.id.lista);

        btnguardar = findViewById(R.id.btnguardar);
        btnactualizar = findViewById(R.id.btnactualizar);
        btneliminar = findViewById(R.id.btneliminar);
        edtnombre = findViewById(R.id.edtnombre);
        edtcalorias = findViewById(R.id.edtcalorias);
        edtdescripcion = findViewById(R.id.edtdescripcion);


        inicializarFirebase();
        listarDatos();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deportesSelected =(Deportes) parent.getItemAtPosition(position);
                edtnombre.setText(deportesSelected.getNombre());
                edtcalorias.setText(deportesSelected.getCalorias());
                edtdescripcion.setText(deportesSelected.getDescripcion());

            }
        });


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarDatos();
                //Agregar a la Bd
                /*String nombre= edtnombre.getText().toString();
                String calorias = edtcalorias.getText().toString();
                String descripcion = edtdescripcion.getText().toString();


                Deportes deportes = new Deportes();
                deportes.setUuid(UUID.randomUUID().toString());
                deportes.setNombre(nombre);
                deportes.setCalorias(calorias);
                deportes.setDescripcion(descripcion);
                deportes.setCategoria(categoria);
                deportes.setImagen("12334434334");
                databaserefence.child("Deportes").child(deportes.getUuid()).setValue(deportes);*/





                Toast.makeText(MainActivity.this,"Agregado", Toast.LENGTH_SHORT).show();




                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Seleccionaste: " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                        categoria = ""+parent.getItemAtPosition(position);
                        String nombre= edtnombre.getText().toString();
                        String calorias = edtcalorias.getText().toString();
                        String descripcion = edtdescripcion.getText().toString();


                        Deportes deportes = new Deportes();
                        deportes.setUuid(UUID.randomUUID().toString());
                        deportes.setNombre(nombre);
                        deportes.setCalorias(calorias);
                        deportes.setDescripcion(descripcion);
                        deportes.setCategoria(categoria);
                        deportes.setImagen("12334434334");
                        databaserefence.child("Deportes").child(deportes.getUuid()).setValue(deportes);

                        //Ahi funciona pero asi no es xD
                        // ya que se le puede hacer creo que dormire mañana vuelvo y miro que es la joda con esto porque qu mas

                }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        });

        final ArrayAdapter<String>adaptador=
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        spinner.setAdapter(adaptador);





    }

    private void listarDatos() {
        databaserefence.child("Deportes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listadeportes.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Deportes deportes = objSnapshot.getValue(Deportes.class);
                    listadeportes.add(deportes);

                    deportesArrayAdapter = new ArrayAdapter<Deportes>(MainActivity.this,android.R.layout.simple_list_item_1,listadeportes);
                    listView.setAdapter(deportesArrayAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebasedatabase = FirebaseDatabase.getInstance();
        databaserefence = firebasedatabase.getReference();
    }
    public void limpiarDatos(){
        edtnombre.setText("");
        edtcalorias.setText("");
        edtdescripcion.setText("");

    }

}
