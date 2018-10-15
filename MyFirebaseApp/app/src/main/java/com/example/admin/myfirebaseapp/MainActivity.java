package com.example.admin.myfirebaseapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.myfirebaseapp.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {
    public List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;


    EditText nomp, apelli, corre, edad, estatura, sexo;
    ListView listV_personas;

    FirebaseDatabase firebaseDatabase;
    FirebaseApp firebaseApp;
    DatabaseReference databaseReference;


    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomp = findViewById(R.id.nombre_Persona);
        apelli = findViewById(R.id.app_Persona);
        corre = findViewById(R.id.correo_Persona);
        edad = findViewById(R.id.edad_Persona);
        estatura = findViewById(R.id.estatura_Persona);
        sexo = findViewById(R.id.sexo_Persona);
        listV_personas = findViewById(R.id.lista1);

        inicializarFirebase();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomp.setText(personaSelected.getNombre());
                apelli.setText(personaSelected.getApellido());
                corre.setText(personaSelected.getCorreo());
                edad.setText(personaSelected.getEdad());
                estatura.setText(personaSelected.getEstatura());
                sexo.setText(personaSelected.getSexo());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Datos_Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Persona p = objSnapshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre = nomp.getText().toString();
        String apellido = apelli.getText().toString();
        String correo = corre.getText().toString();
        String edadp = edad.getText().toString();
        String estaturap = estatura.getText().toString();
        String sexop = sexo.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (nombre.equals("")){
                    validacion();

                }
                else if (apellido.equals("")){
                    validacion();
                }
                else if (correo.equals("")){
                    validacion();
                }
                else if (edadp.equals("")){
                    validacion();
                }
                else if (estaturap.equals("")){
                    validacion();
                }
                else if (sexop.equals("")){
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setEdad(edadp);
                    p.setEstatura(estaturap);
                    p.setSexo(sexop);
                    databaseReference.child("Datos_Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
                    limpiarCajas();

                }
                break;
            }
            case R.id.icon_save:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombre(nomp.getText().toString().trim());
                p.setApellido(apelli.getText().toString().trim());
                p.setCorreo(corre.getText().toString().trim());
                p.setEdad(edad.getText().toString().trim());
                p.setEstatura(estatura.getText().toString().trim());
                p.setSexo(sexo.getText().toString().trim());
                databaseReference.child("Datos_Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomp.setText("");
        apelli.setText("");
        corre.setText("");
        edad.setText("");
        estatura.setText("");
        sexo.setText("");
    }

    private void validacion() {
        String nombre = nomp.getText().toString();
        String apellido = apelli.getText().toString();
        String correo = corre.getText().toString();
        String edadp = edad.getText().toString();
        String estaturap = estatura.getText().toString();
        String sexop = sexo.getText().toString();

        if (nombre.equals("")){
            nomp.setError("Required");
        }
        else if (apellido.equals("")){
            apelli.setError("Required");
        }
        else if (correo.equals("")){
            corre.setError("Required");
        }
        else if (edadp.equals("")){
            edad.setError("Required");
        }
        else if (estaturap.equals("")){
            estatura.setError("Required");
        }
        else if (sexop.equals("")){
            sexo.setError("Required");
        }
    }
}