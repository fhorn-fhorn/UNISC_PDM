package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RevisaoProvaIDadosAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisao_prova_idados_alunos);

        Intent intent= getIntent();

        TextView tempMatricula;
        tempMatricula = findViewById(R.id.txtViewRevProvaI_DadosAluno_Matricula);
        tempMatricula.setText( (String)intent.getStringExtra("matricula") );

        TextView tempNome;
        tempNome = findViewById(R.id.txtViewRevProvaI_DadosAluno_Nome);
        tempNome.setText( (String)intent.getStringExtra("nome") );

        TextView tempEmail;
        tempEmail = findViewById(R.id.txtViewRevProvaI_DadosAluno_Email);
        tempEmail.setText( (String)intent.getStringExtra("email") );

        TextView tempCidade;
        tempCidade = findViewById(R.id.txtViewRevProvaI_DadosAluno_Cidade);
        tempCidade.setText( (String)intent.getStringExtra("cidade") );

        TextView tempEstado;
        tempEstado = findViewById(R.id.txtViewRevProvaI_DadosAluno_Estado);
        tempEstado.setText( (String)intent.getStringExtra("estado") );

    }
}
