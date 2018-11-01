package com.example.admin.myfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.myfirebaseapp.model.Comida;
import com.example.admin.myfirebaseapp.model.Deportes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComidasActivity extends AppCompatActivity {

    private List<Comida> listacomida = new ArrayList<Comida>();
    ArrayAdapter<Comida> adaptador;

    EditText edtxnombre, edtxcalorias, edtxproteinas, edtxcaloriasconsumidas;
    Button btninsertar, btnactualizar, btneliminar;
    ListView lista;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Comida comidaselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);

        edtxnombre = findViewById(R.id.edtxnombre);
        edtxcalorias = findViewById(R.id.edtxcalorias);
        edtxproteinas = findViewById(R.id.edtxproteinas);
        edtxcaloriasconsumidas = findViewById(R.id.edtxcaloriasconsumidas);
        btninsertar = findViewById(R.id.btninsert);
        btnactualizar = findViewById(R.id.btnactualizar);
        btneliminar = findViewById(R.id.btneliminar);

        inicializarfirebase();

        listardatos();

        lista = findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                comidaselect = (Comida) adapterView.getItemAtPosition(i);
                edtxnombre.setText(comidaselect.getNombre());
                edtxcalorias.setText(""+comidaselect.getCaorias());
                edtxcaloriasconsumidas.setText(""+comidaselect.getCloriasconsumidas());
                edtxproteinas.setText(comidaselect.getProteinas());
            }
        });




        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtxnombre.getText().toString();
                String carbohidratos = edtxcalorias.getText().toString();
                String proteinas = edtxproteinas.getText().toString();
                int calorias = Integer.parseInt(edtxcalorias.getText().toString());
                int caloriasconsumidas = Integer.parseInt(edtxcaloriasconsumidas.getText().toString());

                if (nombre.equals("")){
                    validacion();
                }
                else {
                    Comida p = new Comida();
                    p.setUuid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setCaorias(calorias);
                    p.setCarbohidratos(carbohidratos);
                    p.setProteinas(proteinas);
                    p.setCloriasconsumidas(caloriasconsumidas);
                    databaseReference.child("Comida").child(p.getUuid()).setValue(p);

                    limpiar();
                    Toast.makeText(ComidasActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comida p = new Comida();
                p.setUuid(comidaselect.getUuid());
                p.setNombre(edtxnombre.getText().toString().trim());
                p.setCaorias(Integer.parseInt(edtxcalorias.getText().toString().trim()));
                p.setCloriasconsumidas(Integer.parseInt(edtxcaloriasconsumidas.getText().toString().trim()));
                p.setProteinas(edtxproteinas.getText().toString().trim());
                databaseReference.child("Comida").child(p.getUuid()).setValue(p);
                Toast.makeText(ComidasActivity.this, "Actualizado", Toast.LENGTH_SHORT).show();
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comida p = new Comida();
                p.setUuid(comidaselect.getUuid());
                databaseReference.child("Comida").child(p.getUuid()).removeValue();
                Toast.makeText(ComidasActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                limpiar();
            }
        });

    }



    private void listardatos() {
        databaseReference.child("Comida").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listacomida.clear();
                for (DataSnapshot objsnapshot : dataSnapshot.getChildren()){
                    Comida p  = objsnapshot.getValue(Comida.class);
                    listacomida.add(p);

                    adaptador  = new ArrayAdapter<Comida>(ComidasActivity.this,android.R.layout.simple_list_item_1,listacomida);
                    lista.setAdapter(adaptador);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiar() {
        edtxnombre.setText("");
        edtxcalorias.setText("");
        edtxproteinas.setText("");
        edtxcaloriasconsumidas.setText("");
    }

    private void validacion() {
        String nombre = edtxnombre.getText().toString();
        String carbohidratos = edtxcalorias.getText().toString();
        String proteinas = edtxproteinas.getText().toString();


        if (nombre.equals("")){
            edtxnombre.setError("Required");
        }
        else if (carbohidratos.equals("")){
            edtxcalorias.setError("Required");
        }
        else if (proteinas.equals("")){
            edtxproteinas.setError("Required");
        }

    }
}
