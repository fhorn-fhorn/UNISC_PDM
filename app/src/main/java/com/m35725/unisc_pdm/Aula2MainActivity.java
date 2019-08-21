package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Aula2MainActivity extends AppCompatActivity {

    final String TAG= "TAG_LOG_AULA2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula2_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public void btnConverterTemperatura(View view) {
        EditText tempC;
        tempC = findViewById(R.id.edtTemperaturaCelsius);
        String tempCelsius= tempC.getText().toString();
        if( tempCelsius.isEmpty() == false ) {
            double tempFahrenheit = Double.parseDouble(tempCelsius);
            tempFahrenheit= ((tempFahrenheit * 9) / 5) + 32;
            Intent intent= new Intent(Aula2MainActivity.this, Aula2ResultActivity.class);
            intent.putExtra("tempFahrenheit", "" + tempFahrenheit);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Digite a temperatura em Celsius", Toast.LENGTH_SHORT).show();
        }
    }
}
