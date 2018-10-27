package com.sanchez.tipicosaludable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Sánchez on 3/08/2018.
 */

public class AdapterCard extends BaseAdapter {

    private Context context;
    private List<Integer> Images;
    public AdapterCard(Context context, List<Integer>images){
        this.context =context;
        Images = images;
    }

    @Override
    public int getCount() {
        return Images.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
            view.setBackgroundResource(Images.get(i));
        return view;
    }
}
