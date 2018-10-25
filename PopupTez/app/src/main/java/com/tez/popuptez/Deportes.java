package com.tez.popuptez;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Deportes extends AppCompatActivity {


    Dialog epicDialog;
    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button btnFut1;
    Button btnFut2;

    Button btnNata1;
    Button btnNata2;

    Button btnCicli1;
    Button btnCicli2;

    Button btnPat1;
    Button btnPat2;

    Button btnBal1;
    Button btnBal2;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloFutbol;
    TextView mensajeFutbol;

    TextView tituloNatacion;
    TextView mensajeNatacion;

    TextView tituloCicli;
    TextView mensajeCicli;

    TextView tituloaPat;
    TextView mensajePat;

    TextView tituloBal;
    TextView mensajeBal;

    //cierra la alerta segun su layaut
    ImageView Xfutbol;
    ImageView Xnatacion;
    ImageView Xciclismo;
    ImageView Xpatinaje;
    ImageView Xbaloncesto;

    //Gif de deportes
    ImageView Titulo;
    ImageView GifFutbol;
    ImageView GifBaloncesto;
    ImageView GifNatacion;
    ImageView GifCiclismo;
    ImageView GifPatinaje;



    //abre una alerta segun su layaut
    ImageButton futbol;
    ImageButton natacion;
    ImageButton ciclismo;
    ImageButton patinaje;
    ImageButton baloncesto;

    public static int restante;
    int vciclismo,vbaloncesto,vfutbol,vpatinaje,vnatacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deportes);

        epicDialog = new Dialog(this);

        //Imaglogo actividad
        Titulo=(ImageView)findViewById(R.id.Titulo);
        String ImageTitulo ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/Deporte.png?alt=media&token=03311932-4882-4287-a346-ef6ea06a8b4b";
        Glide.with(this)
                .load(ImageTitulo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo);

        //ImagButtons
        futbol=(ImageButton)findViewById(R.id.futbol);

        String ImageFutbol ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44164443_116135682702532_4389934116745773056_n.jpg?_nc_cat=102&oh=cc2f5e27fb9abc5983932b993d1e8901&oe=5C4FF4EB";
        Glide.with(this)
                .load(ImageFutbol)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(futbol);

        natacion=(ImageButton)findViewById(R.id.natacion);

        String ImageNatacion ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44119841_116135566035877_8809757039753428992_n.jpg?_nc_cat=107&oh=a3fccfebd43312f71b72106628a38f9a&oe=5C59241B";
        Glide.with(this)
                .load(ImageNatacion)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(natacion);

        ciclismo=(ImageButton)findViewById(R.id.ciclismo);

        String ImageCiclismo ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44274916_116135519369215_5593136094817288192_n.jpg?_nc_cat=110&oh=b3402b0ab920f0a86adc04f13d89cc2b&oe=5C42219C";
        Glide.with(this)
                .load(ImageCiclismo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(ciclismo);


        patinaje=(ImageButton)findViewById(R.id.patinaje);

        String ImagePatinaje ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44228356_116239482692152_3177025529765167104_n.jpg?_nc_cat=101&oh=9a2e851c488b9e6f151bd436e98e0d36&oe=5C56E964";
        Glide.with(this)
                .load(ImagePatinaje)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(patinaje);
        baloncesto=(ImageButton)findViewById(R.id.baloncesto);

        String ImageBaloncesto ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44206405_116135739369193_2680578794120544256_n.jpg?_nc_cat=111&oh=0d51706267b29644151bb1efba2c1ac8&oe=5C4EF4FE";
        Glide.with(this)
                .load(ImageBaloncesto)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(baloncesto);

        futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFutbol();
            }
        });
        natacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNatacion();
            }
        });
        ciclismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCiclismo();
            }
        });
        patinaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPatinaje();
            }
        });
        baloncesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBaloncesto();
            }
        });

        restante=450;
        vciclismo=466;
        vnatacion=450;
        vpatinaje=300;
        vbaloncesto=272;
        vfutbol=260;
        recomendarDeportes();
    }
    //Shows
    public void recomendarDeportes(){

        if(restante>=1 && restante<=vfutbol){
            ShowFutbol();
        }
        else if (restante>vfutbol && restante<=vbaloncesto){
            ShowBaloncesto();
        }
        else if(restante>vbaloncesto && restante<=vpatinaje){
            ShowPatinaje();
        }
        else if (restante>vpatinaje && restante<=vnatacion){
            ShowNatacion();
        }
        else if (restante>vnatacion){
            ShowCiclismo();
        }

    }
    public void ShowFutbol() {
        epicDialog.setContentView(R.layout.futbol_alert);
        Xfutbol = (ImageView) epicDialog.findViewById(R.id.Xfutbol);
        btnFut1 = (Button) epicDialog.findViewById(R.id.btnFut1);
        btnFut2 = (Button) epicDialog.findViewById(R.id.btnFut2);
        tituloFutbol = (TextView) epicDialog.findViewById(R.id.tituloFutbol);
        mensajeFutbol = (TextView) epicDialog.findViewById(R.id.mensajeFutbol);

        GifFutbol = (ImageView)epicDialog.findViewById(R.id.GifFutbol);
        String url ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/futbol.gif?alt=media&token=49374665-0c56-4c63-b816-ecb8689bdb6f";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifFutbol);

        // Aceptar ----------------------------------------------------------------------------
        btnFut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnFut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xfutbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }
    public void ShowNatacion(){
        epicDialog.setContentView(R.layout.natacion_alert);
        Xnatacion = (ImageView) epicDialog.findViewById(R.id.Xnatacion);
        btnNata1 = (Button) epicDialog.findViewById(R.id.btnNata1);
        btnNata2 = (Button) epicDialog.findViewById(R.id.btnNata2);
        tituloNatacion = (TextView) epicDialog.findViewById(R.id.tituloNatacion);
        mensajeNatacion = (TextView) epicDialog.findViewById(R.id.mensajeNatacion);

        GifNatacion = (ImageView)epicDialog.findViewById(R.id.GifNatacion);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifNatacion);



        // Aceptar ----------------------------------------------------------------------------
        btnNata1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnNata2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xnatacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


    }
    public void ShowCiclismo(){
        epicDialog.setContentView(R.layout.ciclismo_alert);
        Xciclismo = (ImageView) epicDialog.findViewById(R.id.Xciclismo);
        btnCicli1 = (Button) epicDialog.findViewById(R.id.btnCicli1);
        btnCicli2 = (Button) epicDialog.findViewById(R.id.btnCicli2);
        tituloCicli = (TextView) epicDialog.findViewById(R.id.tituloCicli);
        mensajeCicli = (TextView) epicDialog.findViewById(R.id.mensajeCicli);


        GifCiclismo = (ImageView)epicDialog.findViewById(R.id.GifCiclismo);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifCiclismo);

        // Aceptar ----------------------------------------------------------------------------
        btnCicli1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCicli2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xciclismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


    }
    public void ShowPatinaje(){
        epicDialog.setContentView(R.layout.patinaje_alert);
        Xpatinaje = (ImageView) epicDialog.findViewById(R.id.Xpatinaje);
        btnPat1 = (Button) epicDialog.findViewById(R.id.btnPat1);
        btnPat2 = (Button) epicDialog.findViewById(R.id.btnPat2);
        tituloaPat = (TextView) epicDialog.findViewById(R.id.tituloPat);
        mensajePat = (TextView) epicDialog.findViewById(R.id.mensajePat);


        GifPatinaje = (ImageView)epicDialog.findViewById(R.id.GifPatinaje);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifPatinaje);

        // Aceptar ----------------------------------------------------------------------------
        btnPat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnPat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xpatinaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();


    }
    public void ShowBaloncesto(){
        epicDialog.setContentView(R.layout.baloncesto_alert);
        Xbaloncesto = (ImageView) epicDialog.findViewById(R.id.Xbaloncesto);
        btnBal1 = (Button) epicDialog.findViewById(R.id.btnBal1);
        btnBal2 = (Button) epicDialog.findViewById(R.id.btnBal2);
        tituloBal = (TextView) epicDialog.findViewById(R.id.tituloBal);
        mensajeBal = (TextView) epicDialog.findViewById(R.id.mensajeBal);


        GifBaloncesto = (ImageView)epicDialog.findViewById(R.id.GifBaloncesto);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifBaloncesto);

        // Aceptar ----------------------------------------------------------------------------
        btnBal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Deportes.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xbaloncesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
}
