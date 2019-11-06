package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAula1Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula1MainActivity.class);
        startActivity(intent);
    }

    public void btnAula2Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula2MainActivity.class);
        startActivity(intent);
    }

    public void btnAula3Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula3MainActivity.class);
        startActivity(intent);
    }

    public void btnAula4Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula4MainActivity.class);
        startActivity(intent);
    }

    public void btnAula6Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula6MainActivity.class);
        startActivity(intent);
    }

    public void btnAula7Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula7MainActivity.class);
        startActivity(intent);
    }

    public void btnAulaRevisaoProvaI(View view) {
        Intent intent= new Intent(MainActivity.this, RevisaoProvaIMainActivity.class);
        startActivity(intent);
    }

    public void btnDesafio(View view) {
        Intent intent= new Intent(MainActivity.this, DesafioMainActivity.class);
        startActivity(intent);
    }

    public void btnAula8Click(View view) {
        Intent intent= new Intent(MainActivity.this, Aula8CEPActivity.class);
        startActivity(intent);
    }

    public void btnAula9Click(View view) {

        SharedPreferences userLocalSettings = getSharedPreferences("UserLocalSettings",
                MODE_PRIVATE);

        String userEmail= userLocalSettings.getString("userEmail", "");
        String userPassword = userLocalSettings.getString("userPassword", "");

        //Se nao tem usuário e senha, abre a tela de login
        if( userEmail.isEmpty() && userPassword.isEmpty() ){
            Intent intent= new Intent(MainActivity.this, Aula9LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this, Aula9MainActivity.class);
            startActivity(intent);
        }
    }
}
