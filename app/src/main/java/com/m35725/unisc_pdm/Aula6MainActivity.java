package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class Aula6MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula6_main);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.meumenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemListas:
                //Toast.makeText(this, "Menu Escolhido!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Aula6MainActivity.this, Aula6ExemploActivity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }


    /*
    private ListView meuListView;
    private Spinner meuSpinner;

    String [] values = {"Luz","Água","Telefone","Internet","Aluguel","Academia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula6_main);

        meuSpinner= findViewById(R.id.meuSpinner);
        meuListView= findViewById(R.id.meuListView);

        ArrayAdapter<String> arraySpinner= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);

        meuSpinner.setAdapter(arraySpinner);

        meuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText( getApplicationContext(), values[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText( getApplicationContext(), "Nada selecionado!", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adaptList= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Arrays.asList(values));

        meuListView.setAdapter(adaptList);

    }

    public void ExemploClick(View view){
        Intent intent= new Intent(Aula6MainActivity.this, Aula6ExemploActivity.class);
        startActivity(intent);
    }
    */
}
