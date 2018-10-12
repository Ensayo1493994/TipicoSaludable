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
 * Created by lenovo on 3/09/2018.
 */

public class AdaptadorUltimoConsumo extends BaseAdapter {
    private Context context;

    ArrayList<UltimoConsumo> list;


    public AdaptadorUltimoConsumo(Context context, ArrayList<UltimoConsumo> list)

    {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {



        return list.size();
    }

    @Override
    public UltimoConsumo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

        final UltimoConsumo item = getItem(position);
        //imagenComida.setImageResource(item.getIdDrawable());

        Glide.with(imagenComida.getContext()).load(item.getIdDrawable()).into(imagenComida);
        nombreComida.setText(item.getNombre());

        return view;
    }
}
