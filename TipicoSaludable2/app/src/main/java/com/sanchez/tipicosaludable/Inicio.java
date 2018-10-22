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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        GridView gridView = (GridView) vista.findViewById(R.id.ultimoconsumo);
        AdaptadorUltimoConsumo adaptador = new AdaptadorUltimoConsumo(getContext(),ActividadDetalle.ultimoconsumo);
        gridView.setAdapter(adaptador);


        return vista;
    }

}
