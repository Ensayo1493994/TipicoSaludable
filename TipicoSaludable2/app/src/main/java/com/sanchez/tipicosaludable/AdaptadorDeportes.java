package com.sanchez.tipicosaludable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by lenovo on 20/11/2018.
 */

public class AdaptadorDeportes extends BaseAdapter {
    private Context context;
    ArrayList<Deportes_firebase> list;

    public AdaptadorDeportes(Context context, ArrayList<Deportes_firebase> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Deportes_firebase getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenComida = (ImageView) view.findViewById(R.id.imagen_comida);
        TextView nombreComida = (TextView) view.findViewById(R.id.nombre_comida);

        final Deportes_firebase item = getItem(position);
        //imagenComida.setImageResource(item.getIdDrawable());

        Glide.with(imagenComida.getContext()).load(item.getImagen()).into(imagenComida);
        nombreComida.setText(item.getNombre());

        return view;
    }
}
