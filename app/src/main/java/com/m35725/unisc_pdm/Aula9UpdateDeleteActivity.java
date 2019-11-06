package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Aula9UpdateDeleteActivity extends AppCompatActivity {

    String idDados= "";
    private DatabaseHelper helper;
    private EditText edtTextModelo, edtTextAno, edtTextValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_update_delete);

        Bundle extras= getIntent().getExtras();
        idDados= extras.getString("idDados");

        edtTextModelo= (EditText) findViewById(R.id.EdtViewAula9UpdateDeleteModelo);
        edtTextAno= (EditText) findViewById(R.id.EdtViewAula9UpdateDeleteAno);
        edtTextValor= (EditText) findViewById(R.id.EdtViewAula9UpdateDeleteValor);

        helper= new DatabaseHelper(this);

        SQLiteDatabase db= helper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM carro WHERE id=" + idDados, null);
        cursor.moveToFirst();
        edtTextModelo.setText(cursor.getString(1));
        edtTextAno.setText(cursor.getString(2));
        edtTextValor.setText(String.valueOf(cursor.getDouble(3)));
        cursor.close();
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

    public void btnAula9UpdateDeleteExcluirOnClick(View view) {

        //Se nao tiver dados nos campos

        SQLiteDatabase db= helper.getWritableDatabase();
        String where[]= new String[]{idDados};
        long resultado= db.delete("carro","id=?", where);
        if( resultado != -1 ){
            Toast.makeText(this, "Registro excluído com sucesso (id:" +
                    String.valueOf(idDados) + ").", Toast.LENGTH_SHORT).show();
            edtTextModelo.setText(null);
            edtTextAno.setText(null);
            edtTextValor.setText(null);
        }else{
            Toast.makeText(this, "Erro ao excluir registro!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAula9UpdateDeleteAtualizarOnClick(View view) {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("modelo", edtTextModelo.getText().toString());
        values.put("ano", edtTextAno.getText().toString());
        values.put("valor", Double.parseDouble(edtTextValor.getText().toString()));
        String where[]= new String[]{idDados};
        long resultado= db.update("carro", values, "id=?", where);
        if( resultado != -1 ){
            Toast.makeText(this, "Registro atualizado com sucesso (id:" +
                    String.valueOf(idDados) + ").", Toast.LENGTH_SHORT).show();
            edtTextModelo.setText(null);
            edtTextAno.setText(null);
            edtTextValor.setText(null);
        }else{
            Toast.makeText(this, "Erro ao atualizar registro!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
