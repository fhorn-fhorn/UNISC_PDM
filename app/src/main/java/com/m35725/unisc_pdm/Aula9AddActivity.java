package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Aula9AddActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText edtTextModelo, edtTextAno, edtTextValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_add);

        edtTextModelo= (EditText) findViewById(R.id.EdtViewAula9AddModelo);
        edtTextAno= (EditText) findViewById(R.id.EdtViewAula9AddAno);
        edtTextValor= (EditText) findViewById(R.id.EdtViewAula9AddValor);

        helper= new DatabaseHelper(this);

    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

    public void btnAula9AddSalvarOnClick(View view) {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("modelo", edtTextModelo.getText().toString());
        values.put("ano", edtTextAno.getText().toString());
        values.put("valor", Double.parseDouble(edtTextValor.getText().toString()));
        long resultado= db.insert("carro", null, values);
        if( resultado != -1 ){
            Toast.makeText(this, "Registro salvo com sucesso (id:" +
                    String.valueOf(resultado) + ").", Toast.LENGTH_SHORT).show();
            edtTextModelo.setText(null);
            edtTextAno.setText(null);
            edtTextValor.setText(null);
        }else{
            Toast.makeText(this, "Erro ao salvar registro!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
