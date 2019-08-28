package com.m35725.unisc_pdm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class Aula3Questao1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula3_questao1);
        setTitle(R.string.ActiorBarTitleAula3Questao1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_poupanca_icon);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5e9c00")));
        //actionBar.setBackgroundDrawable(new ColorDrawable(R.color.Aula3Questao1ActionBarBackground));
    }
}
