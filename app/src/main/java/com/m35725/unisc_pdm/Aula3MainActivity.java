package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula3MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula3_main);
    }

    public void btnAula3Questao1Click(View view) {
        Intent intent= new Intent(Aula3MainActivity.this, Aula3Questao1Activity.class);
        startActivity(intent);
    }

    public void btnAula3Questao2Click(View view) {
        Intent intent= new Intent(Aula3MainActivity.this, Aula3Questao2Activity.class);
        startActivity(intent);
    }

    public void btnAula3Questao3Click(View view) {
        Intent intent= new Intent(Aula3MainActivity.this, Aula3Questao3MainActivity.class);
        startActivity(intent);
    }

    public void btnAula3Questao4Click(View view) {
        Intent intent= new Intent(Aula3MainActivity.this, Aula3Questao4Activity.class);
        startActivity(intent);
    }
}
