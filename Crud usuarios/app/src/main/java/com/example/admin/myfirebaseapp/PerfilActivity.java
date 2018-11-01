package com.example.admin.myfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.myfirebaseapp.model.Perfil;
import com.example.admin.myfirebaseapp.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PerfilActivity extends AppCompatActivity {
    List<Perfil> listperfil = new ArrayList<>();
    ArrayAdapter<Perfil> arrayAdapterPerfil;

    EditText activi, calomax, contex, edadd, gener, indice, pesoo, tallaa;

    boolean bandera = false;
    FirebaseDatabase firebaseDatabase;
    FirebaseApp firebaseApp;
    DatabaseReference databaseReference;

    ListView listV_personas;

    Perfil perfilSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        activi = findViewById(R.id.actividadF);
        calomax = findViewById(R.id.calo_maxima);
        contex = findViewById(R.id.contexturaF);
        edadd = findViewById(R.id.edad1);
        gener = findViewById(R.id.genero1);
        indice = findViewById(R.id.nom_indice);
        pesoo = findViewById(R.id.peso1);
        tallaa = findViewById(R.id.talla1);
        listV_personas = findViewById(R.id.lista1);

        inicializarFirebase();
        listaDos();



        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                perfilSelected = (Perfil) parent.getItemAtPosition(position);
                activi.setText(perfilSelected.getActividad());
                calomax.setText(perfilSelected.getCalorías_maximas());
                contex.setText(perfilSelected.getContextura());
                edadd.setText(perfilSelected.getEdad());
                gener.setText(perfilSelected.getGenero());
                indice.setText(perfilSelected.getImc());
                pesoo.setText(perfilSelected.getPeso());
                tallaa.setText(perfilSelected.getTalla());
            }
        });



    }

    private void inicializarFirebase() {
        firebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    private void listaDos(){
        DatabaseReference perfil = databaseReference.child("Perfil");
        perfil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listperfil.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Perfil perfil1 = objSnapshot.getValue(Perfil.class);
                    listperfil.add(perfil1);

                    arrayAdapterPerfil = new ArrayAdapter<Perfil>(PerfilActivity.this, android.R.layout.simple_list_item_1, listperfil);
                    listV_personas.setAdapter(arrayAdapterPerfil);
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
        String activid = activi.getText().toString();
        String calorismaxi = calomax.getText().toString();
        String contextu = contex.getText().toString();
        String edade = edadd.getText().toString();
        String genera = gener.getText().toString();
        String indic = indice.getText().toString();
        String pesos = pesoo.getText().toString();
        String tallas = tallaa.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_save:{
                if (activid.equals("")){
                    validacion();

                }
                else if (calorismaxi.equals("")){
                    validacion();
                }
                else if (contextu.equals("")){
                    validacion();
                }
                else if (edade.equals("")){
                    validacion();
                }
                else if (genera.equals("")){
                    validacion();
                }
                else if (indic.equals("")){
                    validacion();
                }
                else if (pesos.equals("")){
                    validacion();
                }
                else if (tallas.equals("")){
                    validacion();
                }
                else {
                    Perfil p = new Perfil();
                    p.setUid(UUID.randomUUID().toString());
                    p.setActividad(activid);
                    p.setCalorías_maximas(calorismaxi);
                    p.setContextura(contextu);
                    p.setEdad(edade);
                    p.setGenero(genera);
                    p.setImc(indic);
                    p.setPeso(pesos);
                    p.setTalla(tallas);
                    databaseReference.child("Perfil_Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
                    limpiarCajas();

                }
                break;
            }
            case R.id.icon_update:{
                Perfil p = new Perfil();
                p.setUid(perfilSelected.getUid());
                p.setActividad(activi.getText().toString().trim());
                p.setCalorías_maximas(calomax.getText().toString().trim());
                p.setContextura(contex.getText().toString().trim());
                p.setEdad(edadd.getText().toString().trim());
                p.setGenero(gener.getText().toString().trim());
                p.setImc(indice.getText().toString().trim());
                p.setPeso(pesoo.getText().toString().trim());
                p.setTalla(tallaa.getText().toString().trim());
                databaseReference.child("Perfil_Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Perfil p = new Perfil();
                p.setUid(perfilSelected.getUid());
                databaseReference.child("Perfil_Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        activi.setText("");
        calomax.setText("");
        contex.setText("");
        edadd.setText("");
        gener.setText("");
        indice.setText("");
        pesoo.setText("");
        tallaa.setText("");
    }

    private void validacion() {
        String actividd = activi.getText().toString();
        String caloriesmaxi = calomax.getText().toString();
        String contextu = contex.getText().toString();
        String edadde = edadd.getText().toString();
        String generr = gener.getText().toString();
        String indicc = indice.getText().toString();
        String pessoo = pesoo.getText().toString();
        String tallaaa = tallaa.getText().toString();

        if (actividd.equals("")){
            activi.setError("Required");
        }
        else if (caloriesmaxi.equals("")){
            calomax.setError("Required");
        }
        else if (contextu.equals("")){
            contex.setError("Required");
        }
        else if (edadde.equals("")){
            edadd.setError("Required");
        }
        else if (generr.equals("")){
            gener.setError("Required");
        }
        else if (indicc.equals("")){
            indice.setError("Required");
        }
        else if (pessoo.equals("")){
            pesoo.setError("Required");
        }
        else if (tallaaa.equals("")){
            tallaa.setError("Required");
        }
    }
}