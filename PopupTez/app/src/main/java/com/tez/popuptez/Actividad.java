package com.tez.popuptez;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

public class Actividad extends AppCompatActivity {
    Dialog epicDialog;
    //Declaracion de vistas y variables
    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button btnAero1;
    Button btnAero2;

    Button btnCam1;
    Button btnCam2;

    Button btnPilat1;
    Button btnPilat2;

    Button btnMar1;
    Button btnMar2;

    Button btnSpn1;
    Button btnSpn2;

    Button btnSlp1;
    Button btnSlp2;

    Button btnTrote1;
    Button btnTrote2;

    Button btnYoga1;
    Button btnYoga2;

    Button btnZum1;
    Button btnZum2;

    //para mostrar el titulo y texto de cada alerta

    TextView tituloAero;
    TextView mensajeAero;

    TextView tituloCam;
    TextView mensajeCam;

    TextView tituloPilat;
    TextView mensajePilat;

    TextView tituloMarcha;
    TextView mensajeMarcha;

    TextView tituloSpn;
    TextView mensajeSpn;

    TextView tituloSlp;
    TextView mensajeSlp;

    TextView tituloTrotar;
    TextView mensajeTrotar;

    TextView tituloYoga;
    TextView mensajeYoga;

    TextView tituloZumba;
    TextView mensajeZumba;

    //cierra la alerta segun su layaut
    ImageView Xmarcha;
    ImageView Xaerobicos;
    ImageView Xcaminar;
    ImageView Xpilates;
    ImageView Xspinning;
    ImageView Xslep;
    ImageView Xtrotar;
    ImageView Xyoga;
    ImageView Xzumba;

    //Gif para activad fisica
    ImageView Titulo;
    ImageView GifMarcha;
    ImageView GifAerobicos;
    ImageView GifCaminar;
    ImageView GifPilates;
    ImageView GifSpinning;
    ImageView GifStep;
    ImageView GifTrotar;
    ImageView GifYoga;
    ImageView GifZumba;


    //abre una alerta segun su layaut
    ImageButton aerobicos;
    ImageButton caminar;
    ImageButton pilates;
    ImageButton marchar;
    ImageButton spinning;
    ImageButton step;
    ImageButton trotar;
    ImageButton yoga;
    ImageButton zumba;


    int vtrote,vspinning,vstep,vmarcha,vaerobicos,vyoga,vcaminar,vzumba,vpilates;
    public static int restante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad);

        epicDialog = new Dialog(this);

        Titulo=(ImageView)findViewById(R.id.Titulo);
        String ImageTitulo ="https://cdn6.aptoide.com/imgs/1/8/3/183a78d368593623e4059d33860b41ca_icon.png?w=240";
        Glide.with(this)
                .load(ImageTitulo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo);

        //ImagButtons
        marchar=(ImageButton)findViewById(R.id.marcha);
        zumba=(ImageButton)findViewById(R.id.zumba);
        String ImageZumba ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44096666_116277762688324_3260684603563704320_n.jpg?_nc_cat=104&oh=5fd9a608d2d550f0da56929cc22eb0e1&oe=5C532D4D";
        Glide.with(this)
                .load(ImageZumba)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(zumba);

        pilates=(ImageButton)findViewById(R.id.pilates);

        yoga=(ImageButton)findViewById(R.id.yoga);
        String ImageYoga ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44126068_116135669369200_171724775452835840_n.jpg?_nc_cat=110&oh=dca02e36a1f53095ff153b3d5ee75d2f&oe=5C57073F";
        Glide.with(this)
                .load(ImageYoga)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(yoga);

        step=(ImageButton)findViewById(R.id.step);

        caminar=(ImageButton)findViewById(R.id.caminar);
        String ImageCaminar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44252444_116269622689138_1666714947440082944_n.jpg?_nc_cat=102&oh=04e0343d6ad6e9b9333b1021c9dec7f7&oe=5C44E5E7";
        Glide.with(this)
                .load(ImageCaminar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(caminar);

        trotar=(ImageButton)findViewById(R.id.trotar);
        String ImageTrotar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44224980_116135596035874_7012991625051766784_n.jpg?_nc_cat=103&oh=2a09416022b020f9e3951204e81d7504&oe=5C477F5F";
        Glide.with(this)
                .load(ImageTrotar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(trotar);

        aerobicos=(ImageButton)findViewById(R.id.aerobicos);

        spinning=(ImageButton)findViewById(R.id.spininng);
        String ImageSpinning ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44074945_116135459369221_1830872136546254848_n.jpg?_nc_cat=111&oh=5cc5a966a67c6abe3f10e1ba9745a670&oe=5C4BCE22";
        Glide.with(this)
                .load(ImageSpinning)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(spinning);


        //ImgButtons--------------------------
        marchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMarchar();
            }
        });
        zumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowZumba();
            }
        });
        pilates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPilates();
            }
        });
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowYoga();
            }
        });
        step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowStep();
            }
        });
        caminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCaminar();
            }
        });
        trotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTrotar();
            }
        });
        aerobicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAerobicos();
            }
        });
        spinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSpinning();
            }
        });


        restante=110;


        vtrote=300;
        vspinning=270;
        vstep=250;
        vmarcha=230;
        vaerobicos=221;
        vyoga=203;
        vcaminar=150;
        vzumba=116;
        vpilates=96;
        recomendarActividad();
//ShowPopup
    }
    public void recomendarActividad(){

        if (restante>=1 && restante<=vpilates){
            ShowPilates();
        }
        else if (restante>vpilates && restante<=vzumba){
            ShowZumba();
        }
        else if(restante>vzumba && restante<=vcaminar){
            ShowCaminar();
        }
        else if(restante>vcaminar && restante<=vyoga){
            ShowYoga();
        }
        else if(restante>vyoga && restante<=vaerobicos){
            ShowAerobicos();
        }
        else if(restante>vaerobicos && restante<=vmarcha){
            ShowMarchar();
        }
        else if(restante>vmarcha && restante<=vstep){
            ShowStep();
        }
        else if(restante>vstep && restante<=vspinning){
            ShowSpinning();
        }
        else if(restante>vspinning){
            ShowTrotar();
        }



    }

    public void ShowMarchar(){
        epicDialog.setContentView(R.layout.marchar_alert);
        Xmarcha = (ImageView) epicDialog.findViewById(R.id.Xmarchar);
        btnMar1 = (Button) epicDialog.findViewById(R.id.btnMar1);
        btnMar2= (Button) epicDialog.findViewById(R.id.btnMar2);
        tituloMarcha = (TextView) epicDialog.findViewById(R.id.tituloMarchar);
        mensajeMarcha = (TextView) epicDialog.findViewById(R.id.mensajeMarchar);

        GifMarcha = (ImageView)epicDialog.findViewById(R.id.GifMarcha);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifMarcha);

        // Aceptar ----------------------------------------------------------------------------
        btnMar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnMar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xmarcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowZumba(){
        epicDialog.setContentView(R.layout.zumba_alert);
        Xzumba = (ImageView) epicDialog.findViewById(R.id.Xzumba);
        btnZum1 = (Button) epicDialog.findViewById(R.id.btnZum1);
        btnZum2= (Button) epicDialog.findViewById(R.id.btnZum2);
        tituloZumba = (TextView) epicDialog.findViewById(R.id.tituloZumba);
        mensajeZumba = (TextView) epicDialog.findViewById(R.id.mensajeZumba);

        GifZumba = (ImageView)epicDialog.findViewById(R.id.GifZumba);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifZumba);

        // Aceptar ----------------------------------------------------------------------------
        btnZum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnZum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xzumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowPilates(){
        epicDialog.setContentView(R.layout.pilates_alert);
        Xpilates = (ImageView) epicDialog.findViewById(R.id.Xpilates);
        btnPilat1 = (Button) epicDialog.findViewById(R.id.btnPilat1);
        btnPilat2= (Button) epicDialog.findViewById(R.id.btnPilat2);
        tituloPilat = (TextView) epicDialog.findViewById(R.id.tituloPilat);
        mensajePilat = (TextView) epicDialog.findViewById(R.id.mensajePilat);

        GifPilates = (ImageView)epicDialog.findViewById(R.id.GifPilates);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifPilates);

        // Aceptar ----------------------------------------------------------------------------
        btnPilat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnPilat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xpilates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowYoga(){
        epicDialog.setContentView(R.layout.yoga_alert);
        Xyoga = (ImageView) epicDialog.findViewById(R.id.Xyoga);
        btnYoga1= (Button) epicDialog.findViewById(R.id.btnYoga1);
        btnYoga2= (Button) epicDialog.findViewById(R.id.btnYoga2);
        tituloYoga = (TextView) epicDialog.findViewById(R.id.tituloYoga);
        mensajeYoga = (TextView) epicDialog.findViewById(R.id.mensajeYoga);

        GifYoga = (ImageView)epicDialog.findViewById(R.id.GifYoga);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifYoga);

        // Aceptar ----------------------------------------------------------------------------
        btnYoga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnYoga2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xyoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowStep(){
        epicDialog.setContentView(R.layout.step_alert);
        Xslep = (ImageView) epicDialog.findViewById(R.id.Xslep);
        btnSlp1 = (Button) epicDialog.findViewById(R.id.btnSlp1);
        btnSlp2= (Button) epicDialog.findViewById(R.id.btnSlp2);
        tituloSlp = (TextView) epicDialog.findViewById(R.id.tituloSlp);
        mensajeSlp = (TextView) epicDialog.findViewById(R.id.mensajeSlp);

        GifStep = (ImageView)epicDialog.findViewById(R.id.GifStep);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifStep);

        // Aceptar ----------------------------------------------------------------------------
        btnSlp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSlp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xslep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowCaminar(){
        epicDialog.setContentView(R.layout.caminar_alert);
        Xcaminar = (ImageView) epicDialog.findViewById(R.id.Xcaminar);
        btnCam1 = (Button) epicDialog.findViewById(R.id.btnCam1);
        btnCam2 = (Button) epicDialog.findViewById(R.id.btnCam2);
        tituloCam = (TextView) epicDialog.findViewById(R.id.tituloCam);
        mensajeCam = (TextView) epicDialog.findViewById(R.id.mensajeCam);

        GifCaminar = (ImageView)epicDialog.findViewById(R.id.GifCaminar);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifCaminar);


        // Aceptar ----------------------------------------------------------------------------
        btnCam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xcaminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowTrotar(){
        epicDialog.setContentView(R.layout.trotar_alert);
        Xtrotar = (ImageView) epicDialog.findViewById(R.id.Xtrotar);
        btnTrote1 = (Button) epicDialog.findViewById(R.id.btnTrote1);
        btnTrote2= (Button) epicDialog.findViewById(R.id.btnTrote2);
        tituloTrotar = (TextView) epicDialog.findViewById(R.id.tituloTrotar);
        mensajeTrotar = (TextView) epicDialog.findViewById(R.id.mensajeTrotar);

        GifTrotar = (ImageView)epicDialog.findViewById(R.id.GifTrotar);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifTrotar);

        // Aceptar ----------------------------------------------------------------------------
        btnTrote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnTrote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xtrotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowAerobicos(){
        epicDialog.setContentView(R.layout.aerobicos_alert);
        Xaerobicos = (ImageView) epicDialog.findViewById(R.id.Xaerobicos);
        btnAero1 = (Button) epicDialog.findViewById(R.id.btnAero1);
        btnAero2 = (Button) epicDialog.findViewById(R.id.btnAero2);
        tituloAero = (TextView) epicDialog.findViewById(R.id.tituloAero);
        mensajeAero = (TextView) epicDialog.findViewById(R.id.mensajeAero);

        GifAerobicos = (ImageView)epicDialog.findViewById(R.id.GifAerobicos);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifAerobicos);


        // Aceptar ----------------------------------------------------------------------------
        btnAero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnAero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xaerobicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowSpinning(){
        epicDialog.setContentView(R.layout.spinning_alert);
        Xspinning = (ImageView) epicDialog.findViewById(R.id.Xspinning);
        btnSpn1 = (Button) epicDialog.findViewById(R.id.btnSpn1);
        btnSpn2= (Button) epicDialog.findViewById(R.id.btnSpn2);
        tituloSpn = (TextView) epicDialog.findViewById(R.id.tituloSpn);
        mensajeSpn = (TextView) epicDialog.findViewById(R.id.mensajeSpn);

        GifSpinning = (ImageView)epicDialog.findViewById(R.id.GifSpinning);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifSpinning);

        // Aceptar ----------------------------------------------------------------------------
        btnSpn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSpn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Actividad.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xspinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
}
