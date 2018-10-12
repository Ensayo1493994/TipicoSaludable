package com.sanchez.tipicosaludable;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class Fragment_Detalle extends Fragment {

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private  Comida itemDetallado;

    private int consumo=0;
    public static int Calorias_consumidas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment__detalle, container, false);
        //itemDetallado = Comida.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        itemDetallado = (Comida) Comida.getItem(getActivity().getIntent().getIntExtra(EXTRA_PARAM_ID,0));
        final ImageView imagenExtendida = vista.findViewById(R.id.imagen_extendida);
        final TextView informacion = (TextView) vista.findViewById(R.id.informacion);
        final  Button btnconsumodealimeto = vista.findViewById(R.id.btnconsumodealimento);
        btnconsumodealimeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calorias_consumidas=Calorias_consumidas+(consumo+ Integer.parseInt(informacion.getText().toString()));
            }
        });
        informacion.setText(""+itemDetallado.getCalorias());
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);



        return vista;
    }

}
