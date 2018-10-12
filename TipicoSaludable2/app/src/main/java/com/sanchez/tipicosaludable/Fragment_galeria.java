package com.sanchez.tipicosaludable;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class Fragment_galeria extends Fragment implements AdapterView.OnItemClickListener {
    public  static  int n;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_galeria, container, false);
        // Inflate the layout for this fragment
        GridView gridView = (GridView) vista.findViewById(R.id.grid);
        AdaptadorComida adaptador = new AdaptadorComida(getContext());

        gridView .setAdapter(adaptador);
        gridView.setOnItemClickListener(this);


        return vista;
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Comida item = (Comida) adapterView.getItemAtPosition(position);
        n =item.getIdDrawable();

        Activity activity = getActivity();
        Intent intent = new Intent(getContext(), ActividadDetalle.class);
        intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());

/*        Fragment fragment = null;
        fragment = new Fragment_Detalle();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();*/
        //fragment.getActivity().putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, new Pair<View, String>(view.findViewById(R.id.imagen_comida),ActividadDetalle.VIEW_NAME_HEADER_IMAGE)

            );
            ActivityCompat.startActivity(activity, intent, activityOptionsCompat.toBundle());
        }else
            startActivity(intent);
    }
}
