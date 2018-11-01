package com.example.admin.myfirebaseapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import com.example.admin.myfirebaseapp.model.Deportes;
import com.example.admin.myfirebaseapp.model.Perfil;
import com.example.admin.myfirebaseapp.model.Persona;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeportesActivity extends AppCompatActivity {
    private static final String[]datos=
            new String[]{"Deportes","Ejercicios","Actividad Fisica"};

    private Spinner spinner;
    private static final int GALLERY_INTENT = 1;

    private List<Deportes> listadeportes = new ArrayList<Deportes>();
    ArrayAdapter<Deportes> deportesArrayAdapter;

    Button btnguardar, btnactualizar, btneliminar, btnsubirfoto;
    EditText edtnombre, edtcalorias, edtdescripcion, edtduracion;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databaserefence;
    StorageReference myStorage2;

    ListView listView;
    Deportes deportesSelected;
    public  static String categoria;
    Boolean bandera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);


        spinner =(Spinner)findViewById(R.id.spinner);
        listView = findViewById(R.id.lista);

        btnguardar = findViewById(R.id.btnguardar);
        btnactualizar = findViewById(R.id.btnactualizar);
        btneliminar = findViewById(R.id.btneliminar);
        btnsubirfoto = findViewById(R.id.btnfoto);
        edtnombre = findViewById(R.id.edtnombre);
        edtcalorias = findViewById(R.id.edtcalorias);
        edtdescripcion = findViewById(R.id.edtdescripcion);
        edtduracion = findViewById(R.id.edtduracion);


        inicializarFirebase();
        listarDatos();
        listaDos();





        btnsubirfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deportesSelected =(Deportes) parent.getItemAtPosition(position);
                edtnombre.setText(deportesSelected.getNombre());
                edtcalorias.setText(deportesSelected.getCalorias());
                edtdescripcion.setText(deportesSelected.getDescripcion());
                edtduracion.setText(deportesSelected.getDuracion());
                categoria = ""+parent.getItemAtPosition(position);

            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(DeportesActivity.this);
                builder.setTitle("ADVERTENCIA DE SEGURIDAD");
                builder.setMessage("Esta seguro de que desea eliminar?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Deportes deportes = new Deportes();
                        deportes.setUuid(deportesSelected.getUuid());
                        databaserefence.child("Deportes").child(deportes.getUuid()).removeValue();
                        Toast.makeText(DeportesActivity.this,"Eliminado", Toast.LENGTH_SHORT).show();
                        limpiarDatos();

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DeportesActivity.this,"Excelente",Toast.LENGTH_LONG).show();

                    }
                });
                builder.show();

            }
        });


        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deportes deportes = new Deportes();
                deportes.setUuid(deportesSelected.getUuid());
                deportes.setNombre(edtnombre.getText().toString().trim());
                deportes.setCalorias(edtcalorias.getText().toString().trim());
                deportes.setDescripcion(edtdescripcion.getText().toString().trim());
                deportes.setDuracion(edtduracion.getText().toString().trim());
                databaserefence.child("Deportes").child(deportes.getUuid()).setValue(deportes);
                Toast.makeText(DeportesActivity.this,"Actualizado", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        });


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarDatos();

                Toast.makeText(DeportesActivity.this,"Agregado", Toast.LENGTH_SHORT).show();




                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Seleccionaste: " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                        categoria = ""+parent.getItemAtPosition(position);
                        String nombre= edtnombre.getText().toString();
                        String calorias = edtcalorias.getText().toString();
                        String descripcion = edtdescripcion.getText().toString();
                        String duracion = edtduracion.getText().toString();
                        Deportes deportes = new Deportes();
                        deportes.setUuid(UUID.randomUUID().toString());
                        deportes.setNombre(nombre);
                        deportes.setCalorias(calorias);
                        deportes.setDescripcion(descripcion);
                        deportes.setDuracion(duracion);

                        databaserefence.child("Deportes").child(deportes.getUuid()).setValue(deportes);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        });

        final ArrayAdapter<String> adaptador=
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        spinner.setAdapter(adaptador);
    }



    private void listaDos() {
        DatabaseReference deporte = databaserefence.child("Deportes");
        deporte.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listadeportes.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Deportes deportes = objSnapshot.getValue(Deportes.class);
                    listadeportes.add(deportes);
                    deportesArrayAdapter = new ArrayAdapter<Deportes>(getApplicationContext(), android.R.layout.simple_list_item_1, listadeportes);
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
        myStorage2 = FirebaseStorage.getInstance().getReference();

    }





    private void listarDatos() {
        databaserefence.child("Deportes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listadeportes.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Deportes deportes = objSnapshot.getValue(Deportes.class);
                    listadeportes.add(deportes);

                    deportesArrayAdapter = new ArrayAdapter<Deportes>(DeportesActivity.this,android.R.layout.simple_list_item_1,listadeportes);
                    listView.setAdapter(deportesArrayAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==  GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();

            StorageReference filepath = null;
            if (uri != null) {
                filepath = myStorage2.child("Deportes_Fotos").child(uri.getLastPathSegment());
            }
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(DeportesActivity.this,"Se subió crack", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }




    public void limpiarDatos(){
        edtnombre.setText("");
        edtcalorias.setText("");
        edtdescripcion.setText("");
        edtduracion.setText("");

    }

}
