package com.sanchez.tipicosaludable;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class Deportes2 extends Fragment {
    Dialog epicDialog;
    //botones (1=aceptar) y (2=cancelar) segun su layaut

    Button btnBra1;
    Button btnBra2;

    Button btnSalt1;
    Button btnSalt2;

    Button btnSen1;
    Button btnSen2;

    Button btnTit1;
    Button btnTit2;

    Button btnAbd1;
    Button btnAbd2;

    Button btnBurp1;
    Button btnBurp2;

    Button btnCmb1;
    Button btnCmb2;

    Button btnImp1;
    Button btnImp2;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloBrazo;
    TextView mensajeBrazo;

    TextView tituloSalto;
    TextView mensajeSalto;

    TextView tituloSenta;
    TextView mensajeSenta;

    TextView tituloTit;
    TextView mensajeTit;

    TextView tituloAbd;
    TextView mensajeAbd;

    TextView tituloBurp;
    TextView mensajeBurp;

    TextView tituloCmb;
    TextView mensajeCmb;

    TextView tituloImp;
    TextView mensajeImp;

    //cierra la alerta segun su layaut
    ImageView Xbrazo;
    ImageView Xsaltar;
    ImageView Xsentado;
    ImageView Xtitere;
    ImageView Xabd;
    ImageView Xburpes;
    ImageView Xclimber;
    ImageView Ximpulso;

    //Gif para ejercicios
    ImageView Titulo;
    ImageView GifFlexion_Brazo;
    ImageView GifSaltos;
    ImageView GifSentadillas;
    ImageView GifTitere;
    ImageView GifAbd;
    ImageView GifBurpes;
    ImageView GifClimber;
    ImageView GifSalto_impulso;

    //abre una alerta segun su layaut
    ImageButton brazo;
    ImageButton saltar;
    ImageButton sentado;
    ImageButton titere;
    ImageButton abd;
    ImageButton burpes;
    ImageButton climber;
    ImageButton impulso;

    int vabd, vclimber, vburpees, vtitere, vsalto, vflexion, vsentadilla;
    public static int restante;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_deportes2, container, false);
        epicDialog = new Dialog(getContext());

        Titulo = (ImageView) vista.findViewById(R.id.Titulo);
        String ImageTitulo = "https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/Ejercicio.png?alt=media&token=9521d9e4-4cc4-47db-85df-310757c907b6";
        Glide.with(this)
                .load(ImageTitulo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo);

        brazo = (ImageButton) vista.findViewById(R.id.brazo);
        String ImageBrazo = "https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/brazo.png?alt=media&token=2dff3d1a-a385-4f9e-9b1a-121dd2c7ff20";
        Glide.with(this)
                .load(ImageBrazo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(brazo);

        saltar = (ImageButton) vista.findViewById(R.id.saltar);
        String ImageSaltar = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44166184_116269556022478_1994582558859329536_n.jpg?_nc_cat=101&oh=38c21736a4976fbc53d8a95b575d3a90&oe=5C430068";
        Glide.with(this)
                .load(ImageSaltar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(saltar);

        sentado = (ImageButton) vista.findViewById(R.id.sentado);
        String ImageSentado = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageSentado)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(sentado);

        titere = (ImageButton) vista.findViewById(R.id.titere);
        String ImageTitere = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageTitere)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(titere);

        abd = (ImageButton) vista.findViewById(R.id.abd);
        String ImageAbd = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageAbd)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(abd);


        burpes = (ImageButton) vista.findViewById(R.id.burpes);
        String ImageBurpes = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44091833_116135479369219_3933751711658672128_n.jpg?_nc_cat=106&oh=d32cd9cda89d26d4b906cc84557a7716&oe=5C58916D";
        Glide.with(this)
                .load(ImageBurpes)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(burpes);

        climber = (ImageButton) vista.findViewById(R.id.climber);
        String ImageClimber = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44216950_116269566022477_1465670466071953408_n.jpg?_nc_cat=103&oh=41ed4e14cbb4b9e3b0da14e1e450ec1d&oe=5C3F4B73";
        Glide.with(this)
                .load(ImageClimber)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(climber);

        impulso = (ImageButton) vista.findViewById(R.id.impulso);
        String ImageImpulso = "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageImpulso)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(impulso);


//--------------------------------------------------------------------------------------------------------------------------------
        brazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBrazo();
            }
        });
        saltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSaltar();
            }
        });
        sentado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSentadilla();
            }
        });
        titere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTitetre();
            }
        });
        abd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowABD();
            }
        });
        burpes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBurpes();
            }
        });
        climber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowClimber();
            }
        });
        impulso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSaltoImpulso();
            }
        });

        restante = 345;

        vabd = 500;
        vclimber = 300;
        vburpees = 298;
        vtitere = 240;
        vsalto = 209;
        vflexion = 171;
        vsentadilla = 153;
        recomendarEjercicios();
        return  vista;
    }


    public void recomendarEjercicios(){
        if (restante>=1 && restante<=vsentadilla){
            ShowSentadilla();
        }
        else if(restante>vsentadilla && restante<=vflexion){
            ShowBrazo();
        }
        else if(restante>vflexion && restante<=vsalto){
            ShowSaltoImpulso();
        }
        else if(restante>vsalto && restante<=vtitere){
            ShowTitetre();
        }
        else if(restante>vtitere && restante<=vburpees){
            ShowBurpes();
        }
        else if(restante>vburpees && restante<=vclimber){
            ShowClimber();
        }
        else if(restante>vclimber){
            ShowABD();
        }

    }
    public void ShowBrazo(){
        epicDialog.setContentView(R.layout.flexion_brazo_alert);
        Xbrazo = (ImageView) epicDialog.findViewById(R.id.Xbrazo);
        btnBra1 = (Button) epicDialog.findViewById(R.id.btnBra1);
        btnBra2= (Button) epicDialog.findViewById(R.id.btnBra2);
        tituloBrazo = (TextView) epicDialog.findViewById(R.id.tituloBrazo);
        mensajeBrazo = (TextView) epicDialog.findViewById(R.id.mensajeBrazo);

        GifFlexion_Brazo=(ImageView)epicDialog.findViewById(R.id.GifFlexion_Brazo);
        String url ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/gif_brazo.gif?alt=media&token=20d7c498-87b9-4fe8-87f4-832af0626eb0";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifFlexion_Brazo);

        // Aceptar ----------------------------------------------------------------------------
        btnBra1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBra2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xbrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowSaltar(){
        epicDialog.setContentView(R.layout.saltar_alert);
        Xsaltar = (ImageView) epicDialog.findViewById(R.id.Xsalto);
        btnSalt1 = (Button) epicDialog.findViewById(R.id.btnSalt1);
        btnSalt2= (Button) epicDialog.findViewById(R.id.btnSalt2);
        tituloSalto = (TextView) epicDialog.findViewById(R.id.tituloSalto);
        mensajeSalto = (TextView) epicDialog.findViewById(R.id.mensajeSalto);

        GifSaltos=(ImageView)epicDialog.findViewById(R.id.GifSaltos);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifSaltos);

        // Aceptar ----------------------------------------------------------------------------
        btnSalt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSalt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xsaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowSentadilla(){
        epicDialog.setContentView(R.layout.sentadilla_alert);
        Xsentado = (ImageView) epicDialog.findViewById(R.id.Xsentado);
        btnSen1 = (Button) epicDialog.findViewById(R.id.btnSen1);
        btnSen2= (Button) epicDialog.findViewById(R.id.btnSen2);
        tituloSenta = (TextView) epicDialog.findViewById(R.id.tituloSenta);
        mensajeSenta = (TextView) epicDialog.findViewById(R.id.mensajeSenta);

        GifSentadillas=(ImageView)epicDialog.findViewById(R.id.GifSentadillas);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifSentadillas);

        // Aceptar ----------------------------------------------------------------------------
        btnSen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xsentado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowTitetre(){
        epicDialog.setContentView(R.layout.titere_alert);
        Xtitere = (ImageView) epicDialog.findViewById(R.id.Xtitere);
        btnTit1 = (Button) epicDialog.findViewById(R.id.btnTit1);
        btnTit2= (Button) epicDialog.findViewById(R.id.btnTit2);
        tituloTit = (TextView) epicDialog.findViewById(R.id.tituloTit);
        mensajeTit = (TextView) epicDialog.findViewById(R.id.mensajeTit);

        GifTitere=(ImageView)epicDialog.findViewById(R.id.GifTitere);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifTitere);

        // Aceptar ----------------------------------------------------------------------------
        btnTit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnTit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xtitere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowABD(){
        epicDialog.setContentView(R.layout.abd_alert);
        Xabd = (ImageView) epicDialog.findViewById(R.id.Xabd);
        btnAbd1 = (Button) epicDialog.findViewById(R.id.btnAbd1);
        btnAbd2= (Button) epicDialog.findViewById(R.id.btnAbd2);
        tituloAbd = (TextView) epicDialog.findViewById(R.id.tituloAbd);
        mensajeAbd = (TextView) epicDialog.findViewById(R.id.mensajeAbd);

        GifAbd=(ImageView)epicDialog.findViewById(R.id.GifAbd);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifAbd);

        // Aceptar ----------------------------------------------------------------------------
        btnAbd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnAbd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xabd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowBurpes(){
        epicDialog.setContentView(R.layout.burbes_alert);
        Xburpes = (ImageView) epicDialog.findViewById(R.id.Xburpes);
        btnBurp1 = (Button) epicDialog.findViewById(R.id.btnBurp1);
        btnBurp2= (Button) epicDialog.findViewById(R.id.btnBurp2);
        tituloBurp = (TextView) epicDialog.findViewById(R.id.tituloBurp);
        mensajeBurp = (TextView) epicDialog.findViewById(R.id.mensajeBurp);

        GifBurpes=(ImageView)epicDialog.findViewById(R.id.GifBurpes);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifBurpes);



        // Aceptar ----------------------------------------------------------------------------
        btnBurp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBurp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xburpes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowClimber(){
        epicDialog.setContentView(R.layout.climber_alert);
        Xclimber = (ImageView) epicDialog.findViewById(R.id.Xclimber);
        btnCmb1= (Button) epicDialog.findViewById(R.id.btnCmb1);
        btnCmb2= (Button) epicDialog.findViewById(R.id.btnCmb2);
        tituloCmb = (TextView) epicDialog.findViewById(R.id.tituloCmb);
        mensajeCmb = (TextView) epicDialog.findViewById(R.id.mensajeCmb);

        GifClimber=(ImageView)epicDialog.findViewById(R.id.GifClimber);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifClimber);

        // Aceptar ----------------------------------------------------------------------------
        btnCmb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCmb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Xclimber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    public void ShowSaltoImpulso(){
        epicDialog.setContentView(R.layout.impulso_alert);
        Ximpulso = (ImageView) epicDialog.findViewById(R.id.Ximpulso);
        btnImp1 = (Button) epicDialog.findViewById(R.id.btnImp1);
        btnImp2= (Button) epicDialog.findViewById(R.id.btnImp2);
        tituloImp = (TextView) epicDialog.findViewById(R.id.tituloImp);
        mensajeImp = (TextView) epicDialog.findViewById(R.id.mensajeImp);

        GifSalto_impulso=(ImageView)epicDialog.findViewById(R.id.GifSalto_Impulso);
        String url ="https://upload-assets.vice.com/files/2016/08/17/1471460267Day84_Small.gif?resize=540:*";
        Glide.with(this)
                .load(url)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(GifSalto_impulso);

        // Aceptar ----------------------------------------------------------------------------
        btnImp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnImp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });
        //Cerrar-------------------------------------------------------------------------------
        Ximpulso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();

            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }
    }

