package com.example.admin.myfirebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PrimeraActivity extends AppCompatActivity {
    Button btnsuarios, btndepor, btnhisto, btncomid, btnperfi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera);




        btnsuarios = findViewById(R.id.btncrudusu);
        btndepor = findViewById(R.id.btndeportes);
        btnhisto = findViewById(R.id.btnhistorial);
        btncomid = findViewById(R.id.btncomidas);
        btnperfi = findViewById(R.id.btnperfil);




        btnsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrimeraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btndepor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrimeraActivity.this,DeportesActivity.class);
                startActivity(intent);
            }
        });

        btnhisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeraActivity.this,HistorialActivity.class);
                startActivity(intent);
            }
        });
        btncomid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeraActivity.this,ComidasActivity.class);
                startActivity(intent);
            }
        });
        btnperfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeraActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

    }
}

