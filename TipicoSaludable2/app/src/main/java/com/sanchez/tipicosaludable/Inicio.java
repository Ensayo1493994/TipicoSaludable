package com.sanchez.tipicosaludable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class Inicio extends Fragment {
    int size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        GridView gridView = (GridView) vista.findViewById(R.id.ultimoconsumo);
<<<<<<< HEAD
        TextView textView = vista.findViewById(R.id.txtnoconsumo);


        if (ActividadDetalle.ultimoconsumo.size()>0){
            AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(getContext(),ActividadDetalle.ultimoconsumo);
            gridView.setAdapter(adaptador);

        }
        else {
            textView.setText("No has consumido nada hoy");

        }

=======
        AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(getContext(),ActividadDetalle.ultimoconsumo);
        gridView.setAdapter(adaptador);
>>>>>>> b5f75df2261ddf00357cb2254e864f2dee4bd14a


        return vista;
    }

}
