package com.sanchez.tipicosaludable;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Menu_Lateral extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    CircleImageView imageViewlogo1;
    TextView txtNombre1,txtCorreo1;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private GoogleApiClient googleApiClient;
    int dia, mes, año,diadespues;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new Inicio()).commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        imageViewlogo1 = view.findViewById(R.id.imageViewlogo1);
        txtNombre1 = view.findViewById(R.id.txtNombre1);
        txtCorreo1 = view.findViewById(R.id.txtCorreo1);



        firebaseAuth = FirebaseAuth.getInstance();

       firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            View view1 = navigationView.getHeaderView(0);
            txtNombre1.setText(user.getDisplayName());
            txtCorreo1.setText(user.getEmail());
            Glide.with(this).load(user.getPhotoUrl()).into(imageViewlogo1);

        }else {
            goLogin();
        }



        imageViewlogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(Menu_Lateral.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //---------------------------------------------- MENU PARA GENTE OBESA VALIDACION-----------------------------------
        if (CaloriasActivity.imc>=40){
            navigationView.getMenu().setGroupVisible(R.id.menunormal,false);

        }else{
            navigationView.getMenu().setGroupVisible(R.id.menugordos,false);
        }
        //---------------------------------------------- MENU PARA GENTE OBESA VALIDACION FIN-----------------------------------

        //------------------------------DESCOMENTAR LO DEL INICIO DE SESION AL HACR PUSH

       firebaseAuth = FirebaseAuth.getInstance();


        //-----------------------DIAS---------------------------


        /*final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        año = c.get(Calendar.YEAR);

        diadespues = dia+1;


        while (dia<diadespues){
            final Calendar c2 = Calendar.getInstance();
            dia = c2.get(Calendar.DAY_OF_MONTH);

            if (dia==diadespues){

                Toast.makeText(getApplicationContext(), "ya paso un dia", Toast.LENGTH_SHORT).show();

            }
        }*/

        firebaseAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(this);
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    View view = navigationView.getHeaderView(0);
                    imageViewlogo1 = view.findViewById(R.id.imageViewlogo1);
                    txtNombre1 = view.findViewById(R.id.txtNombre1);
                    setUserData(user);

                }else {
                    goLogin();
                }

            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


    }
    

    private void setUserData(FirebaseUser user) {
        txtNombre1.setText(user.getDisplayName());
        Glide.with(this).load(user.getPhotoUrl()).into(imageViewlogo1);

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView txtinfocalorias = dialog.findViewById(R.id.infopopup);
        txtinfocalorias.setText("Hoy has consumido "+ActividadDetalle.Calorias_consumidas+"calorias");
        dialog.show();*/
        Toast.makeText(this, "Hoy has consumido un total de "+ScrollingDetalle.Calorias_consumidas+" calorias hoy", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu__lateral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment fragment = null;
        Fragment fragment2 = null;
        Fragment fragment3 = null;
        Fragment fragment4 = null;
        Fragment fragment5 = null;


        if (id == R.id.nav_ejercicios) {
            fragment2 = new EjerciciosFirebase();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment2).commit();



            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            fragment = new Fragment_galeria();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

        }
        else if (id ==R.id.nav_inicio){
            fragment3 = new Inicio();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,  fragment3).commit();

        }
        else if (id == R.id.nav_acercade) {

            fragment3 = new AcercaDeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment3).commit();

            /*fragment5 = new AcercaDeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment5).commit();*/



        }else if (id == R.id.nav_deportesgordos) {
            fragment3 = new Lista_Ejercicios();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment3).commit();

        }
        else if(id == R.id.nav_actividad){
            fragment4 = new ActividadFisicaFirebase();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment4).commit();

        }else if(id == R.id.nav_deportes){
            fragment4 = new Deportesfirebase();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment4).commit();

        } else if (id == R.id.nav_cerrar_sesion) {

            firebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()) {
                        LoginManager.getInstance().logOut();
                        goLogin();
                    } else{
                        Toast.makeText(Menu_Lateral.this, "No se pudo cerrar la sesion", Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void goLogin() {

        Intent intent = new Intent(Menu_Lateral.this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }





    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);

        }
    }



}
