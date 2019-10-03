package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula7MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_main);
    }

    public void btnAula7ListActivity(View view) {
        Intent intent= new Intent(Aula7MainActivity.this, Aula7ListActivity.class);
        startActivity(intent);
    }

    public void btnAula7Spinner(View view) {
        Intent intent= new Intent(Aula7MainActivity.this, Aula7SpinnerActivity.class);
        startActivity(intent);
    }
}
