package com.m35725.unisc_pdm;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

public class Aula8ListViewAdaptador extends SimpleAdapter {

    public Aula8ListViewAdaptador(Aula8CEPActivity aula8CEPActivity,
                                  List<HashMap<String, String>> infoWeather,
                                  int lista_weather, String[] de, int[] para) {
        super(aula8CEPActivity, infoWeather, lista_weather, de, para);
    }
    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = super.getView(position, convertView, parent);

        return view;
    }
    */
}
