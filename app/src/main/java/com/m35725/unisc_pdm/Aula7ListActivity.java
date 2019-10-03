package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.*;

public class Aula7ListActivity extends AppCompatActivity {

    private ListView lstViewTabelaBrasileiroSerieA;
    //private SimpleAdapter adapter;
    private MeuAdaptador adapter;
    private List<HashMap<String, Object>> tabelaBrasileiroSeriaA;
    Context actualContext;

    String[] de = {"imagemClube", "nomeClube", "pontosClube"};
    int[] para = {R.id.imgViewAula7ImagemClube, R.id.imgViewAula7NomeClube,
            R.id.imgViewAula7PontosClube};

    int[] imagemClubes = {R.drawable.inter, R.drawable.sao, R.drawable.pal, R.drawable.fla,
            R.drawable.gre, R.drawable.cam, R.drawable.cru, R.drawable.san, R.drawable.flu,
            R.drawable.cor, R.drawable.ame, R.drawable.vit, R.drawable.bah, R.drawable.cap,
            R.drawable.bot, R.drawable.vas, R.drawable.spt, R.drawable.cea, R.drawable.cha,
            R.drawable.par};

    String[] nomeClubes = {"Internacional", "São Paulo", "Palmeiras", "Flamengo", "Grêmio",
            "Atlético-MG", "Cruzeiro", "Santos", "Fluminense", "Corinthians", "América-MG",
            "Vitória", "Bahia", "Athlético-PR", "Botafogo", "Vasco", "Sport", "Ceará",
            "Chapecoense", "Paraná Clube"};

    int[] pontosclubes = {49,49,46,44,41,38,33,31,31,30,30,29,28,27,26,24,24,24,22,16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_list);

        actualContext= this;

        tabelaBrasileiroSeriaA= new ArrayList<>();

        HashMap<String, Object> itens;
        for( int i=0; i < nomeClubes.length; i++ ){
            itens= new HashMap<String, Object>();
            itens.put("imagemClube", imagemClubes[i]);
            itens.put("nomeClube", nomeClubes[i]);
            itens.put("pontosClube", pontosclubes[i]);
            tabelaBrasileiroSeriaA.add(itens);
        }

        adapter= new MeuAdaptador(this, tabelaBrasileiroSeriaA, R.layout.lista_campeonato, de, para );
        lstViewTabelaBrasileiroSerieA= findViewById(R.id.lstViewTabelaBrasileiroSerieA);
        lstViewTabelaBrasileiroSerieA.setAdapter(adapter);

        lstViewTabelaBrasileiroSerieA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, Object> item= tabelaBrasileiroSeriaA.get(i);
                Toast.makeText(actualContext, (String)item.get("nomeClube"), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
