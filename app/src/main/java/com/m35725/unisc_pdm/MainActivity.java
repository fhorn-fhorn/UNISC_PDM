package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
}
