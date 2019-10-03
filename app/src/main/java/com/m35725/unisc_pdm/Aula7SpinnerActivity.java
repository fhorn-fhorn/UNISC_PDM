package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Aula7SpinnerActivity extends AppCompatActivity {

    private Spinner spinnerCores;

    private String[] nomeCores = {"gray", "white", "green", "blue", "red"};

    private Context actualContext;

    String defaultTextForSpinner = "text here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_spinner);

        actualContext= this;

        spinnerCores= findViewById(R.id.spinnerAula7Cores);

        spinnerCores.setAdapter( new MyAdapter(this, android.R.layout.simple_list_item_1, nomeCores));

    }

    public class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int textViewResourceId, String[] objects){
            super(context, textViewResourceId, objects);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent){
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            //Muda a cor de fundo
            tv.setBackgroundColor( Color.parseColor(nomeCores[position]) );
            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent){
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            return getCustomView(position, convertView, parent);
        }

    }










    /*
    public class CustomSpinnerAdapter extends ArrayAdapter<String>{

        Context context;
        String[] objects;
        String firstElement;
        boolean isFirstTime;

        public CustomSpinnerAdapter(Context context, int textViewResourceId, String[] objects, String defaultText) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.objects = objects;
            this.isFirstTime = true;
            setDefaultText(defaultText);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(isFirstTime) {
                objects[0] = firstElement;
                isFirstTime = false;
            }
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            notifyDataSetChanged();
            return getCustomView(position, convertView, parent);
        }

        public void setDefaultText(String defaultText) {
            this.firstElement = objects[0];
            objects[0] = defaultText;
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView label = (TextView) row.findViewById(R.id.spinnerAula7Cores);
            label.setText(objects[position]);

            return row;
        }

    }
    */
}
