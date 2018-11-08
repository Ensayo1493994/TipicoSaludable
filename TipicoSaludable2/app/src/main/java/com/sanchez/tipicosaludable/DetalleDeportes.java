package com.sanchez.tipicosaludable;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleDeportes extends AppCompatActivity implements View.OnClickListener {
    TextView crono,caloriras_depor,duracion_depor;
    ImageView playbuttom,stopbuttom,rewindbuttom,imgdeporte;
    boolean isOn = false;
    int min=0,seg=0,mili=0;
    Handler h = new Handler();
    Thread cronos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_deportes);
        crono = findViewById(R.id.crono);
        playbuttom = findViewById(R.id.playbuttom);
        stopbuttom = findViewById(R.id.stopbuttom);
        rewindbuttom = findViewById(R.id.rewindbuttom);
        //en lo de abajo es que tenes que agrgar lo de la base de datos la imagen, duracion y calorias de los deportes
        imgdeporte = findViewById(R.id.imgdeporte);
        caloriras_depor = findViewById(R.id.calorias_depor);
        duracion_depor = findViewById(R.id.duracion_depor);

        playbuttom.setOnClickListener(this);
        stopbuttom.setOnClickListener(this);
        rewindbuttom.setOnClickListener(this);


        cronos = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (isOn){
                        try{
                            Thread.sleep(1);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        mili++;
                        if (mili==999){
                            seg++;
                            mili=0;
                        }
                        if (seg==59){
                            min++;
                            seg=0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m = "", s = "", mi = "";
                                if (mili<10) {
                                    m = "00" + mili;
                                }else if (mili<100){
                                    m="0"+mili;
                                }else {
                                    m=""+mili;
                                }
                                if (seg<10){
                                    s ="0"+ seg;
                                }else {
                                    s ="" +seg;
                                }if (min<10){
                                    mi="0"+min;
                                }else {
                                    mi=""+min;
                                }
                                crono.setText(mi+":"+s+":"+m);
                            }
                        });

                    }
                }

            }
        });
        cronos.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playbuttom:
                isOn=true;
                break;
            case R.id.stopbuttom:
                isOn=false;
                break;
            case R.id.rewindbuttom:
                isOn=false;
                mili=0;
                seg=0;
                min=0;
                crono.setText("00:00:000");
                break;
        }

    }
}