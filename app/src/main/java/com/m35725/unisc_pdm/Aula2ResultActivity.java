package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aula2ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula2_result);
        Intent intent= getIntent();
        String tempFahrenheit= intent.getStringExtra("tempFahrenheit");
        TextView tempF;
        tempF = findViewById(R.id.txtViewFarenheitResult);
        tempF.setText(tempFahrenheit + " °F");
        Toast.makeText(this, "" + tempFahrenheit + " °F", Toast.LENGTH_SHORT).show();
    }
}
