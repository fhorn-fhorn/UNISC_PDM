package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula9MainActivity extends AppCompatActivity {

    private DatabaseHelper  helper;
    private EditText        edtTextAno;
    private ListView        lstViewCarros;
    List<Map<String,String>>    carros;
    String[] de= { "id", "modelo", "ano", "valor" };
    int[] para= { R.id.txtViewAula9Listagem_Id, R.id.txtViewAula9Listagem_Modelo,
            R.id.txtViewAula9Listagem_Ano, R.id.txtViewAula9Listagem_Valor };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_main);

        edtTextAno= (EditText) findViewById(R.id.EdtViewAula9Ano);

        lstViewCarros= (ListView) findViewById(R.id.lstViewAula9Carros);
        lstViewCarros.setClickable(true);
        lstViewCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String idDados= String.valueOf(carros.get(i).get("id"));
                Intent intent= new Intent(Aula9MainActivity.this, Aula9UpdateDeleteActivity.class);
                intent.putExtra("idDados", idDados);
                startActivity(intent);
            }
        });

        helper= new DatabaseHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.aula9_menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemLogout:
                SharedPreferences userLocalSettings = getSharedPreferences("UserLocalSettings",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor= userLocalSettings.edit();
                editor.remove("userEmail");
                editor.remove("userPassword");
                editor.apply();
                editor.commit();
                Aula9MainActivity.this.finish();
                return true;
        }
        return true;
    }

    public void btnAula9BuscarOnClick(View view) {
        String busca= edtTextAno.getText().toString();
        String query= "";
        if( busca.isEmpty() ){
            query= "SELECT * FROM carro";
        }else{
            query= "SELECT * FROM carro WHERE ano=" + busca;
        }

        SQLiteDatabase db= helper.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        cursor.moveToFirst();
        carros= new ArrayList<Map<String,String>>();
        for( int i = 0; i < cursor.getCount(); i++ ){
            Map<String,String> item= new HashMap<String, String>();
            String id= cursor.getString(0);
            String modelo= cursor.getString(1);
            String ano= cursor.getString(2);
            Double valor= cursor.getDouble(3);
            item.put("id", id);
            item.put("modelo", "Modelo: " + modelo);
            item.put("ano", "Ano: " + ano);
            item.put("valor", String.format("R$ %.2f", valor));
            carros.add(item);
            cursor.moveToNext();
        }
        cursor.close();

        SimpleAdapter adaptador= new SimpleAdapter(this, carros,
                R.layout.aula9_listagem_carros, de, para );
        lstViewCarros.setAdapter(adaptador);

    }

    public void btnAula9AdicionarOnClick(View view) {
        Intent intent= new Intent(Aula9MainActivity.this, Aula9AddActivity.class);
        startActivity(intent);
    }
}
