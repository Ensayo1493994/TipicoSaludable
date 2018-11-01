package com.example.admin.myfirebaseapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.myfirebaseapp.model.Persona;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class MainActivity extends AppCompatActivity {
    List<Persona> listPerson = new ArrayList<>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText nomp, corre, fecha;
    Button subirFoto;
    ListView listV_personas;
    List<Persona> personasA = new ArrayList<>();
    private static final int GALLERY_INTENT = 1;
    FirebaseDatabase firebaseDatabase;
    FirebaseApp firebaseApp;
    DatabaseReference databaseReference;
    StorageReference myStorage;

    Persona personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomp = findViewById(R.id.nombre_Persona);
        corre = findViewById(R.id.correo_Persona);
        fecha = findViewById(R.id.fecha_nazi);
        subirFoto = findViewById(R.id.btnfoto);
        listV_personas = findViewById(R.id.lista1);

        inicializarFirebase();
        listarDatos();


        subirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomp.setText(personaSelected.getNombre());
                corre.setText(personaSelected.getCorreo());
                fecha.setText(personaSelected.getFecha());
            }
        });


    }



    private void inicializarFirebase() {
        firebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
        myStorage = FirebaseStorage.getInstance().getReference();
    }




    private void listarDatos() {
        databaseReference.child("Datos_Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Persona p = objSnapshot.getValue(Persona.class);
                    listPerson.add(p);
                    arrayAdapterPersona = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==  GALLERY_INTENT && resultCode == RESULT_OK){
            final Uri uri = data.getData();
            final StorageReference filepath = myStorage.child("Admin_Fotos").child(uri.getLastPathSegment());
            UploadTask uploadTask = filepath.putFile(uri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this,"Se subió crack"+uri.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("error",taskSnapshot.getMetadata().getGeneration());
                }
            });

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL

                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e("asd",downloadUri.toString());
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });

        }
    } // imagen

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre = nomp.getText().toString();
        String correo = corre.getText().toString();
        String fechap = fecha.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_save:{
                if (nombre.equals("")){
                    validacion();

                }

                else if (correo.equals("")){
                    validacion();
                }
                else if (fechap.equals("")){
                    validacion();
                }
                else {
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setCorreo(correo);
                    p.setFecha(fechap);

                    databaseReference.child("Datos_Persona").child(p.getUid()).setValue(p);

                    Toast.makeText(this,"¡¡Se guardó correctamente!!",Toast.LENGTH_SHORT).show();
                    limpiarCajas();

                }
                break;
            }
            case R.id.icon_update:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                p.setNombre(nomp.getText().toString().trim());
                p.setCorreo(corre.getText().toString().trim());
                p.setFecha(fecha.getText().toString().trim());

                databaseReference.child("Usuarios").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Datos actualizados",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Persona p = new Persona();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Usuarios").child(p.getUid()).removeValue();
                Toast.makeText(this,"Se elimino un usuario",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomp.setText("");
        corre.setText("");
        fecha.setText("");

    }


    private void validacion() {
        String nombre = nomp.getText().toString();
        String correo = corre.getText().toString();
        String edadp = fecha.getText().toString();


        if (nombre.equals("")){
            nomp.setError("Required");
        }

        else if (correo.equals("")){
            corre.setError("Required");
        }
        else if (edadp.equals("")){
            fecha.setError("Required");
        }

    }
}