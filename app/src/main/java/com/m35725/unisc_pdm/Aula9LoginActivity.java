package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Aula9LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_login);
    }

    public void btnAula9LOGINOnclick(View view) {

        EditText edtTextUsuario= findViewById(R.id.edtTextAula9LoginUsuario);
        EditText edtTextSenha= findViewById(R.id.edtTextAula9LoginSenha);

        SharedPreferences userLocalSettings = getSharedPreferences("UserLocalSettings",
                MODE_PRIVATE);
        SharedPreferences.Editor editor= userLocalSettings.edit();
        editor.putString("userEmail", edtTextUsuario.getText().toString() );
        editor.putString("userPassword", edtTextSenha.getText().toString() );
        editor.apply();
        editor.commit();

        Aula9LoginActivity.this.finish();

    }
}
