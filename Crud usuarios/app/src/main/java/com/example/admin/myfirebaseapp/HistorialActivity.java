package com.example.admin.myfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.myfirebaseapp.model.Historial;

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

public class HistorialActivity extends AppCompatActivity {



    List<Historial> listPerson = new ArrayList<>();
    ArrayAdapter<Historial> arrayAdapterPersona;

    EditText caloriasC, excedentes, cFinales, cMaximas, fecha, nombre_User;
    ListView listV_personas;

    FirebaseDatabase firebaseDatabase;
    FirebaseApp firebaseApp;
    DatabaseReference databaseReference;

    Historial historialSelected;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        caloriasC = findViewById(R.id.calo_consu);
        excedentes = findViewById(R.id.calo_exceden);
        cFinales = findViewById(R.id.calo_fin);
        cMaximas = findViewById(R.id.calo_max);
        fecha = findViewById(R.id.fecha_histor);
        nombre_User = findViewById(R.id.nom_usuario);
        listV_personas = findViewById(R.id.lista1);




        inicializarFirebase();
        listarDatos();


        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                historialSelected = (Historial) parent.getItemAtPosition(position);
                caloriasC.setText(historialSelected.getCalorías_consumidas());
                excedentes.setText(historialSelected.getCalorías_excedentes());
                cFinales.setText(historialSelected.getCalorías_finales());
                cMaximas.setText(historialSelected.getCalorías_máximas());
                fecha.setText(historialSelected.getFecha());
                nombre_User.setText(historialSelected.getNombre_usuario());
            }
        });



    }



    private void inicializarFirebase() {
        firebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }




    private void listarDatos() {
        databaseReference.child("Historial_Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Historial p = objSnapshot.getValue(Historial.class);
                    listPerson.add(p);
                    arrayAdapterPersona = new ArrayAdapter<>(HistorialActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    //Toast.makeText(MainActivity.this,"Pasó por aqui" + listPerson.size(), Toast.LENGTH_SHORT).show();
                    listV_personas.setAdapter(arrayAdapterPersona);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String calorisC = caloriasC.getText().toString();
        String calorisexce = excedentes.getText().toString();
        String calorisfin = cFinales.getText().toString();
        String calorismax = cMaximas.getText().toString();
        String fechap = fecha.getText().toString();
        String username = nombre_User.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_save:{
                if (calorisC.equals("")){
                    validacion();

                }
                else if (calorisexce.equals("")){
                    validacion();
                }
                else if (calorisfin.equals("")){
                    validacion();
                }
                else if (calorismax.equals("")){
                    validacion();
                }
                else if (fechap.equals("")){
                    validacion();
                }
                else if (username.equals("")){
                    validacion();
                }
                else {
                    Historial p = new Historial();
                    p.setUid(UUID.randomUUID().toString());
                    p.setCalorías_consumidas(calorisC);
                    p.setCalorías_excedentes(calorisexce);
                    p.setCalorías_finales(calorisfin);
                    p.setCalorías_máximas(calorismax);
                    p.setFecha(fechap);
                    p.setNombre_usuario(username);
                    databaseReference.child("Historial_Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
                    limpiarCajas();


                }
                break;
            }
            case R.id.icon_update:{
                Historial p = new Historial();
                p.setUid(historialSelected.getUid());
                p.setCalorías_consumidas(caloriasC.getText().toString().trim());
                p.setCalorías_excedentes(excedentes.getText().toString().trim());
                p.setCalorías_finales(cFinales.getText().toString().trim());
                p.setCalorías_máximas(cMaximas.getText().toString().trim());
                p.setFecha(fecha.getText().toString().trim());
                p.setNombre_usuario(nombre_User.getText().toString().trim());
                databaseReference.child("Historial_Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Historial p = new Historial();
                p.setUid(historialSelected.getUid());
                databaseReference.child("Historial_Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        caloriasC.setText("");
        excedentes.setText("");
        cFinales.setText("");
        cMaximas.setText("");
        fecha.setText("");
        nombre_User.setText("");
    }

    private void validacion() {
        String calorisC = caloriasC.getText().toString();
        String calorisexce = excedentes.getText().toString();
        String calorisfin = cFinales.getText().toString();
        String calorismax = cMaximas.getText().toString();
        String fechap = fecha.getText().toString();
        String username = nombre_User.getText().toString();

        if (calorisC.equals("")){
            caloriasC.setError("Required");
        }
        else if (calorisexce.equals("")){
            cFinales.setError("Required");
        }
        else if (calorisfin.equals("")){
            cFinales.setError("Required");
        }
        else if (calorismax.equals("")){
            cMaximas.setError("Required");
        }
        else if (fechap.equals("")){
            fecha.setError("Required");
        }
        else if (username.equals("")){
            nombre_User.setError("Required");
        }
    }
}