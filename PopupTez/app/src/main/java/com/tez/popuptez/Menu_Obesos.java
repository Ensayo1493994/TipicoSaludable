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

public class Menu_Obesos extends AppCompatActivity {

    Dialog epicDialog;

    //botones (1=aceptar) y (2=cancelar) segun su layaut
    Button btnBra1;     Button btnBra2;

    Button btnSalt1;    Button btnSalt2;

    Button btnSen1;     Button btnSen2;

    Button btnTit1;     Button btnTit2;

    Button btnAbd1;     Button btnAbd2;

    Button btnBurp1;    Button btnBurp2;

    Button btnCmb1;     Button btnCmb2;

    Button btnImp1;     Button btnImp2;

    Button btnAero1;    Button btnAero2;

    Button btnCam1;     Button btnCam2;

    Button btnPilat1;   Button btnPilat2;

    Button btnMar1;     Button btnMar2;

    Button btnSpn1;     Button btnSpn2;

    Button btnSlp1;     Button btnSlp2;

    Button btnTrote1;   Button btnTrote2;

    Button btnYoga1;    Button btnYoga2;

    Button btnZum1;     Button btnZum2;

    Button btnFut1;     Button btnFut2;

    Button btnNata1;    Button btnNata2;

    Button btnCicli1;   Button btnCicli2;

    Button btnPat1;     Button btnPat2;

    Button btnBal1;     Button btnBal2;

    //para mostrar el titulo y texto de cada alerta
    TextView tituloBrazo;       TextView mensajeBrazo;

    TextView tituloSalto;       TextView mensajeSalto;

    TextView tituloSenta;       TextView mensajeSenta;

    TextView tituloTit;         TextView mensajeTit;

    TextView tituloAbd;         TextView mensajeAbd;

    TextView tituloBurp;        TextView mensajeBurp;

    TextView tituloCmb;         TextView mensajeCmb;

    TextView tituloImp;         TextView mensajeImp;

    TextView tituloAero;        TextView mensajeAero;

    TextView tituloCam;         TextView mensajeCam;

    TextView tituloPilat;       TextView mensajePilat;

    TextView tituloMarcha;      TextView mensajeMarcha;

    TextView tituloSpn;         TextView mensajeSpn;

    TextView tituloSlp;         TextView mensajeSlp;

    TextView tituloTrotar;      TextView mensajeTrotar;

    TextView tituloYoga;        TextView mensajeYoga;

    TextView tituloZumba;       TextView mensajeZumba;

    TextView tituloFutbol;      TextView mensajeFutbol;

    TextView tituloNatacion;    TextView mensajeNatacion;

    TextView tituloCicli;       TextView mensajeCicli;

    TextView tituloaPat;        TextView mensajePat;

    TextView tituloBal;         TextView mensajeBal;

    //cierra la alerta segun su layaut
    ImageView Xbrazo;
    ImageView Xsaltar;
    ImageView Xsentado;
    ImageView Xtitere;
    ImageView Xabd;
    ImageView Xburpes;
    ImageView Xclimber;
    ImageView Ximpulso;
    ImageView Xmarcha;
    ImageView Xaerobicos;
    ImageView Xcaminar;
    ImageView Xpilates;
    ImageView Xspinning;
    ImageView Xslep;
    ImageView Xtrotar;
    ImageView Xyoga;
    ImageView Xzumba;
    ImageView Xfutbol;
    ImageView Xnatacion;
    ImageView Xciclismo;
    ImageView Xpatinaje;
    ImageView Xbaloncesto;

    //Gif para ejercicios
    ImageView Titulo1;
    ImageView Titulo2;
    ImageView Titulo3;
    ImageView GifFlexion_Brazo;
    ImageView GifSaltos;
    ImageView GifSentadillas;
    ImageView GifTitere;
    ImageView GifAbd;
    ImageView GifBurpes;
    ImageView GifClimber;
    ImageView GifSalto_impulso;
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
    ImageView GifFutbol;
    ImageView GifBaloncesto;
    ImageView GifNatacion;
    ImageView GifCiclismo;
    ImageView GifPatinaje;

    //abre una alerta segun su layaut
    ImageButton Men_Brazo;
    ImageButton Men_saltos;
    ImageButton Men_sentadilla;
    ImageButton Men_titere;
    ImageButton Men_abd;
    ImageButton Men_burpes;
    ImageButton Men_climber;
    ImageButton Men_impulso;
    ImageButton Men_aerobicos;
    ImageButton Men_caminar;
    ImageButton Men_pilates;
    ImageButton Men_marchar;
    ImageButton Men_spinning;
    ImageButton Men_step;
    ImageButton Men_trotar;
    ImageButton Men_yoga;
    ImageButton Men_zumba;
    ImageButton Men_futbol;
    ImageButton Men_natacion;
    ImageButton Men_ciclismo;
    ImageButton Men_patinaje;
    ImageButton Men_baloncesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__obesos);

        epicDialog = new Dialog(this);

        // Iconos de de titulos--------------------------------------------------------------------------------------------------------------------------------------------
        Titulo1=(ImageView)findViewById(R.id.Titulo1);
        String ImageTitulo1 ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/Ejercicio.png?alt=media&token=9521d9e4-4cc4-47db-85df-310757c907b6";
        Glide.with(this)
                .load(ImageTitulo1)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo1);

        Titulo2=(ImageView)findViewById(R.id.Titulo2);
        String ImageTitulo2 ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/Actividad.png?alt=media&token=fbe52a9e-affd-439d-baa4-fd389fd7b9ab";
        Glide.with(this)
                .load(ImageTitulo2)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo2);

        Titulo3=(ImageView)findViewById(R.id.Titulo3);
        String ImageTitulo3 ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/Deporte.png?alt=media&token=03311932-4882-4287-a346-ef6ea06a8b4b";
        Glide.with(this)
                .load(ImageTitulo3)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Titulo3);


        //Imagenes para deportes---------------------------------------------------------------------------------------------------------------------------------------------
        Men_Brazo=(ImageButton)findViewById(R.id.Men_Brazo);
        String ImageBrazo ="https://firebasestorage.googleapis.com/v0/b/popuptez.appspot.com/o/brazo.png?alt=media&token=2dff3d1a-a385-4f9e-9b1a-121dd2c7ff20";
        Glide.with(this)
                .load(ImageBrazo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_Brazo);

        Men_saltos=(ImageButton)findViewById(R.id.Men_Saltos);
        String ImageSaltar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44166184_116269556022478_1994582558859329536_n.jpg?_nc_cat=101&oh=38c21736a4976fbc53d8a95b575d3a90&oe=5C430068";
        Glide.with(this)
                .load(ImageSaltar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_saltos);


        Men_sentadilla=(ImageButton)findViewById(R.id.Men_Sentadilla);
        String ImageSentado ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageSentado)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_sentadilla);

        Men_titere=(ImageButton)findViewById(R.id.Men_Titere);
        String ImageTitere ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageTitere)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_titere);

        Men_abd=(ImageButton)findViewById(R.id.Men_Abd);
        String ImageAbd ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageAbd)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_abd);


        Men_burpes=(ImageButton)findViewById(R.id.Men_Burpes);
        String ImageBurpes ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44091833_116135479369219_3933751711658672128_n.jpg?_nc_cat=106&oh=d32cd9cda89d26d4b906cc84557a7716&oe=5C58916D";
        Glide.with(this)
                .load(ImageBurpes)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_burpes);

        Men_climber=(ImageButton)findViewById(R.id.Men_Climber);
        String ImageClimber ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44216950_116269566022477_1465670466071953408_n.jpg?_nc_cat=103&oh=41ed4e14cbb4b9e3b0da14e1e450ec1d&oe=5C3F4B73";
        Glide.with(this)
                .load(ImageClimber)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_climber);

        Men_impulso=(ImageButton)findViewById(R.id.Men_Salto_Impulso);
        String ImageImpulso ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44329409_116269619355805_2198198450032476160_n.jpg?_nc_cat=110&oh=16fd5314c43a06deec2a48b323f0b46c&oe=5C533A91";
        Glide.with(this)
                .load(ImageImpulso)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_impulso);

        Men_marchar=(ImageButton)findViewById(R.id.Men_Marcha);
        String ImageMarchar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44096666_116277762688324_3260684603563704320_n.jpg?_nc_cat=104&oh=5fd9a608d2d550f0da56929cc22eb0e1&oe=5C532D4D";
        Glide.with(this)
                .load(ImageMarchar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_marchar);

        Men_zumba=(ImageButton)findViewById(R.id.Men_Zumba);
        String ImageZumba ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44096666_116277762688324_3260684603563704320_n.jpg?_nc_cat=104&oh=5fd9a608d2d550f0da56929cc22eb0e1&oe=5C532D4D";
        Glide.with(this)
                .load(ImageZumba)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_zumba);

        Men_pilates=(ImageButton)findViewById(R.id.Men_Pilates);
        String ImagePilates ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44096666_116277762688324_3260684603563704320_n.jpg?_nc_cat=104&oh=5fd9a608d2d550f0da56929cc22eb0e1&oe=5C532D4D";
        Glide.with(this)
                .load(ImagePilates)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_pilates);

        Men_yoga=(ImageButton)findViewById(R.id.Men_Yoga);
        String ImageYoga ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44126068_116135669369200_171724775452835840_n.jpg?_nc_cat=110&oh=dca02e36a1f53095ff153b3d5ee75d2f&oe=5C57073F";
        Glide.with(this)
                .load(ImageYoga)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_yoga);

        Men_step=(ImageButton)findViewById(R.id.Men_Step);
        String ImageStep ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44126068_116135669369200_171724775452835840_n.jpg?_nc_cat=110&oh=dca02e36a1f53095ff153b3d5ee75d2f&oe=5C57073F";
        Glide.with(this)
                .load(ImageStep)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_step);

        Men_caminar=(ImageButton)findViewById(R.id.Men_Caminar);
        String ImageCaminar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44252444_116269622689138_1666714947440082944_n.jpg?_nc_cat=102&oh=04e0343d6ad6e9b9333b1021c9dec7f7&oe=5C44E5E7";
        Glide.with(this)
                .load(ImageCaminar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_caminar);

        Men_trotar=(ImageButton)findViewById(R.id. Men_Trotar);
        String ImageTrotar ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44224980_116135596035874_7012991625051766784_n.jpg?_nc_cat=103&oh=2a09416022b020f9e3951204e81d7504&oe=5C477F5F";
        Glide.with(this)
                .load(ImageTrotar)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into( Men_trotar);

        Men_aerobicos=(ImageButton)findViewById(R.id. Men_Aerobicos);
        String ImageAerobicos ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44224980_116135596035874_7012991625051766784_n.jpg?_nc_cat=103&oh=2a09416022b020f9e3951204e81d7504&oe=5C477F5F";
        Glide.with(this)
                .load(ImageAerobicos)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into( Men_aerobicos);

        Men_spinning=(ImageButton)findViewById(R.id. Men_Spinninng);
        String ImageSpinning ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44074945_116135459369221_1830872136546254848_n.jpg?_nc_cat=111&oh=5cc5a966a67c6abe3f10e1ba9745a670&oe=5C4BCE22";
        Glide.with(this)
                .load(ImageSpinning)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into( Men_spinning);

        Men_futbol=(ImageButton)findViewById(R.id.Men_Futbol);

        String ImageFutbol ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44164443_116135682702532_4389934116745773056_n.jpg?_nc_cat=102&oh=cc2f5e27fb9abc5983932b993d1e8901&oe=5C4FF4EB";
        Glide.with(this)
                .load(ImageFutbol)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_futbol);

        Men_natacion=(ImageButton)findViewById(R.id.Men_Natacion);
        String ImageNatacion ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44119841_116135566035877_8809757039753428992_n.jpg?_nc_cat=107&oh=a3fccfebd43312f71b72106628a38f9a&oe=5C59241B";
        Glide.with(this)
                .load(ImageNatacion)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_natacion);

        Men_ciclismo=(ImageButton)findViewById(R.id.Men_Ciclismo);
        String ImageCiclismo ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44274916_116135519369215_5593136094817288192_n.jpg?_nc_cat=110&oh=b3402b0ab920f0a86adc04f13d89cc2b&oe=5C42219C";
        Glide.with(this)
                .load(ImageCiclismo)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_ciclismo);


        Men_patinaje=(ImageButton)findViewById(R.id.Men_Patinaje);
        String ImagePatinaje ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44228356_116239482692152_3177025529765167104_n.jpg?_nc_cat=101&oh=9a2e851c488b9e6f151bd436e98e0d36&oe=5C56E964";
        Glide.with(this)
                .load(ImagePatinaje)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_patinaje);

        Men_baloncesto=(ImageButton)findViewById(R.id.Men_Baloncesto);
        String ImageBaloncesto ="https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/44206405_116135739369193_2680578794120544256_n.jpg?_nc_cat=111&oh=0d51706267b29644151bb1efba2c1ac8&oe=5C4EF4FE";
        Glide.with(this)
                .load(ImageBaloncesto)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .into(Men_baloncesto);


        //ImagButtons--------------------------------------------------------------------------------------------------------------------------------------------
        Men_Brazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBrazo();
            }
        });
        Men_saltos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSaltos();
            }
        });
        Men_sentadilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSentadilla();
            }
        });
        Men_titere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTitetre();
            }
        });
        Men_abd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowABD();
            }
        });
        Men_burpes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBurpes();
            }
        });
        Men_climber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowClimber();
            }
        });
        Men_impulso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSaltoImpulso();
            }
        });
        Men_marchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMarchar();
            }
        });
        Men_zumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowZumba();
            }
        });
        Men_pilates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPilates();
            }
        });
        Men_yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowYoga();
            }
        });
        Men_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowStep();
            }
        });
        Men_caminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCaminar();
            }
        });
        Men_trotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTrotar();
            }
        });
        Men_aerobicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAerobicos();
            }
        });
        Men_spinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSpinning();
            }
        });
        Men_futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFutbol();
            }
        });
        Men_natacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNatacion();
            }
        });
        Men_ciclismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCiclismo();
            }
        });
        Men_patinaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPatinaje();
            }
        });
        Men_baloncesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBaloncesto();
            }
        });
        //End----------------------------------------------------------------------------------------------------------------------------------------------------
    }
    //Shows--------------------------------------------------------------------------------------------------------------------------------------------------
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBra2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
    public void ShowSaltos(){
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSalt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnTit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnAbd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBurp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCmb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnImp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnMar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnZum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnPilat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnYoga2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSlp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnTrote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnAero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnSpn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnFut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnNata2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnCicli2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnPat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Obesos.this, "!! Muy Bien ¡¡", Toast.LENGTH_SHORT).show();
                epicDialog.dismiss();
            }
        });

        // Cancelar ----------------------------------------------------------------------------
        btnBal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Menu_Obesos.this, "!! Muyy Maalll ¡¡", Toast.LENGTH_SHORT).show();
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
