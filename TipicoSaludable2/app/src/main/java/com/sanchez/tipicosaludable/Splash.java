package com.sanchez.tipicosaludable;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    FirebaseApp firebaseApp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    public static int encontrousuario=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();



        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Menu_Lateral.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(tarea,2000);

        inicializarfirebase();
        Query q = databaseReference.orderByChild("nombre").equalTo("johan");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                encontrousuario = encontrousuario+1;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if (encontrousuario ==1){
            //Intent intent = new Intent(Splash.this,Menu_Lateral.class);
            //startActivity(intent);
            Toast.makeText(this, "el usuario existe", Toast.LENGTH_SHORT).show();
        }else{
            //Intent intent = new Intent(Splash.this,CaloriasActivity.class);
            //startActivity(intent);
            Toast.makeText(this, "no encontro", Toast.LENGTH_SHORT).show();

        }


    }

    private void inicializarfirebase() {
        firebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Perfil");

    }
}
