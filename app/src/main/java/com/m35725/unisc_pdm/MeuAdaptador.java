package com.m35725.unisc_pdm;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.*;

class MeuAdaptador extends SimpleAdapter {

    public MeuAdaptador(Aula7ListActivity aula7ListActivity, List<HashMap<String, Object>> tabelaBrasileiroSeriaA, int lista_campeonato, String[] de, int[] para) {
        super(aula7ListActivity, tabelaBrasileiroSeriaA, lista_campeonato, de, para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = super.getView(position, convertView, parent);

        if( (position % 2) == 0 ){
            view.setBackgroundColor(0xFF0000FF);
        }else{
            view.setBackgroundColor(0xFF8080FF);
        }

        //G6 = Amarelo
        if( (position >=0) && (position <=5) ){
            view.setBackgroundColor(0xFFFFFF00);
        }

        //Z4 = Vermelho
        if( position >= 16 ){
            view.setBackgroundColor(0xFFFF0000);
        }

        return view;
    }

}


