package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Aula6ConsultaActivity extends AppCompatActivity {

    String [] listaEstados = {"RS", "SC", "PR"};
    String [] listaCidadesRS = {"Porto Alegre", "Santa Cruz do Sul", "Rio Pardo"};
    String [] listaCidadesSC = {"Florianópolis", "Blumenau", "Joinvile"};
    String [] listaCidadesPR = {"Curitiba", "Foz do Iguaçu", "Londrina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula6_consulta);

        //=========================================================================================
        //NOME DA PESSOA
        //
        Intent intent= getIntent();
        String nomePessoa= intent.getStringExtra("NomePessoa");

        TextView txtViewNomePessoa;
        txtViewNomePessoa = findViewById(R.id.aula6ConsultaTxtViewNomePessoa);
        txtViewNomePessoa.setText(nomePessoa);
        //Toast.makeText(this, "Nome: " + nomePessoa, Toast.LENGTH_SHORT).show();

        //=========================================================================================
        //SPINNER
        //
        Spinner spinnerEstados;
        spinnerEstados= findViewById(R.id.aula6ConsultaSpinnerEstados);

        ArrayAdapter<String> adaptSpinnerEstados = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaEstados);
        spinnerEstados.setAdapter(adaptSpinnerEstados);

        //EVENTO DE SELEÇÃO DE ITEM NO SPINNER
        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //ListView
                ListView lstViewCidades;
                lstViewCidades= findViewById(R.id.aula6ConsultaListViewCidades);

                //Position= 0. Estado RS
                if( position == 0 ){
                    //Mensagem
                    Toast.makeText(getApplicationContext(), "Estado: " + listaEstados[position], Toast.LENGTH_SHORT).show();
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptListaCidadesRS = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, Arrays.asList(listaCidadesRS));
                    lstViewCidades.setAdapter(adaptListaCidadesRS);
                }

                //Position= 1. Estado SC
                if( position == 1 ){
                    //Mensagem
                    Toast.makeText(getApplicationContext(), "Estado: " + listaEstados[position], Toast.LENGTH_SHORT).show();
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptListaCidadesSC = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, Arrays.asList(listaCidadesSC));
                    lstViewCidades.setAdapter(adaptListaCidadesSC);
                }

                //Position= 2. Estado PR
                if( position == 2 ){
                    //Mensagem
                    Toast.makeText(getApplicationContext(), "Estado: " + listaEstados[position], Toast.LENGTH_SHORT).show();
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptListaCidadesPR = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, Arrays.asList(listaCidadesPR));
                    lstViewCidades.setAdapter(adaptListaCidadesPR);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Nenhum estado selecionado!", Toast.LENGTH_SHORT).show();
            }

        });




    }
}
