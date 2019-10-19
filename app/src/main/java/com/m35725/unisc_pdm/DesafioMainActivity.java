package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.*;

public class DesafioMainActivity extends AppCompatActivity {

    private Spinner spinnerOperacoes;
    private String[] nomeOperacoes = {"+", "-", "*", "/"};

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    List<HashMap<String, String>> listaOperacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio_main);

        //Spinner de operações
        spinnerOperacoes= findViewById(R.id.spnDesafioOperacoes);
        ArrayAdapter<String> adapterOperacoes= new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nomeOperacoes);
        spinnerOperacoes.setAdapter( adapterOperacoes );

        //Lista de operações executadas
        listaOperacoes= new ArrayList<>();

        //Lista de grupos e itens
        expandableListDetail = new HashMap<String, List<String>>();

        expandableListView = (ExpandableListView) findViewById(R.id.expLstViewDesafioListaOperacoes);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle,
                expandableListDetail, listaOperacoes);
        expandableListView.setAdapter(expandableListAdapter);
        /*
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        */
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                parent.smoothScrollToPosition(groupPosition);

                if (parent.isGroupExpanded(groupPosition)) {
                    ImageView imgView = v.findViewById(R.id.imgViewDesafio_Icone);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.seta_para_baixo));
                } else {
                    ImageView imgView = v.findViewById(R.id.imgViewDesafio_Icone);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.seta_para_cima));
                }
                return false;
            }
        });
    }

    public void btnDesafioCalcular(View view) {

        EditText edtOperador1= findViewById(R.id.edtDesafioOperador1);
        EditText edtOperador2= findViewById(R.id.edtDesafioOperador2);

        //edtOperador1 vazio
        if( edtOperador1.getText().toString().isEmpty() ){
            makeText(this, "Operador 1: vazio!", LENGTH_SHORT).show();
            return;
        }

        //edtOperador2 vazio
        if( edtOperador2.getText().toString().isEmpty() ){
            makeText(this, "Operador 2: vazio!", LENGTH_SHORT).show();
            return;
        }

        //Testa se edtOperador1 é um número
        float operador1;
        try{
            //Operador 1
            operador1= Float.parseFloat( edtOperador1.getText().toString() );
        }catch(NumberFormatException e){
            makeText(this, "Operador 1: número inválido!", LENGTH_SHORT).show();
            return;
        }

        //Testa se edtOperador2 é um número
        float operador2;
        try{
            //Operador 2
            operador2= Float.parseFloat( edtOperador2.getText().toString() );
        }catch(NumberFormatException e){
            makeText(this, "Operador 2: número inválido!", LENGTH_SHORT).show();
            return;
        }

        float resultFinal= 0;
        EditText edtResultado= findViewById(R.id.edtDesafioResultado);
        //Operação
        String oper= (String) spinnerOperacoes.getSelectedItem();

        //Soma
        if( oper.equals("+") ){
            resultFinal= operador1 + operador2;
        }

        //Subtração
        if( oper.equals("-") ){
            resultFinal= operador1 - operador2;
        }

        //Multiplicacao
        if( oper.equals("*") ){
            resultFinal= operador1 * operador2;
        }

        //Divisão
        if( oper.equals("/") && (operador2 != 0)){
            resultFinal= operador1 / operador2;
        }

        //Escreve resultado
        if( (resultFinal % 1) == 0)
            //Float não tem parte decimal
            edtResultado.setText( String.valueOf( Math.round(resultFinal) ));
        else
            //Float tem parte decimal
            edtResultado.setText( String.valueOf(resultFinal) );

        //Total de linhas da ListView
        ExpandableListView tempExpListView;
        tempExpListView= findViewById(R.id.expLstViewDesafioListaOperacoes);
        /*
        Toast.makeText(this, "Linhas: " + tempExpListView.getAdapter().getCount(),
            Toast.LENGTH_SHORT).show();
        */
        //Grupo / Item
        List<String> tempGroup = new ArrayList<String>();
        tempGroup.add( Integer.toString(tempExpListView.getAdapter().getCount()) );
        expandableListDetail.put(Integer.toString(tempExpListView.getAdapter().getCount()), tempGroup);
        //Operacao
        HashMap<String, String> operExec= new HashMap<String, String>();
        operExec.put("operador1", edtOperador1.getText().toString() );
        operExec.put("operador2", edtOperador2.getText().toString() );
        operExec.put("operacao", spinnerOperacoes.getSelectedItem().toString() );
        operExec.put("resultado", edtResultado.getText().toString() );
        operExec.put("dataHora", "18/10/2019 15:40");
        listaOperacoes.add(operExec);

        expandableListView = (ExpandableListView) findViewById(R.id.expLstViewDesafioListaOperacoes);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle,
                expandableListDetail, listaOperacoes);
        expandableListView.setAdapter(expandableListAdapter);

        //Links de referência:
        //https://www.journaldev.com/9942/android-expandablelistview-example-tutorial
        //https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

    }

}

