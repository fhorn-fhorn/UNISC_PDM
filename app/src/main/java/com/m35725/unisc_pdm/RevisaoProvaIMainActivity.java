package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.*;

public class RevisaoProvaIMainActivity extends AppCompatActivity {

    public final int MY_PERMISSIONS_REQUEST_CAMERA= 104;
    public final int RESULT_CODE_CAMERA= 2;

    Context actualContext;

    private ListView lstViewAlunosTela;
    private List<HashMap<String, Object>> lstViewAlunosCompleta;

    String[] de = {"foto", "matricula", "nome"};
    int[] para = {R.id.imgViewRevisaoProvaI_Foto, R.id.txtViewRevisaoProvaI_Matricula,
            R.id.txtViewRevisaoProvaI_Nome};

    String [] listaEstados = {"RS", "SC", "PR"};
    String [] listaCidadesRS = {"Porto Alegre", "Santa Cruz do Sul", "Rio Pardo"};
    String [] listaCidadesSC = {"Florianópolis", "Blumenau", "Joinvile"};
    String [] listaCidadesPR = {"Curitiba", "Foz do Iguaçu", "Londrina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisao_prova_imain);

        actualContext= this;

        //lstViewAlunos= new ArrayList<>();
        lstViewAlunosCompleta= new ArrayList<>();

        //=========================================================================================
        //SPINNER
        //
        Spinner spinnerEstados;
        spinnerEstados= findViewById(R.id.spinnerEstado);

        ArrayAdapter<String> adaptSpinnerEstados =  new ArrayAdapter(actualContext,
                android.R.layout.simple_list_item_1, listaEstados);
        spinnerEstados.setAdapter(adaptSpinnerEstados);

        //EVENTO DE SELEÇÃO DE ITEM NO SPINNER
        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Spinner spinnerCidades;
                spinnerCidades = findViewById(R.id.spinnerCidade);

                //Position= 0. Estado RS
                if( position == 0 ){
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptSpinnerCidadesRS = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, Arrays.asList(listaCidadesRS));
                    spinnerCidades.setAdapter(adaptSpinnerCidadesRS);
                }

                //Position= 1. Estado SC
                if( position == 1 ){
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptSpinnerCidadesSC =  new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, listaCidadesSC);
                    spinnerCidades.setAdapter(adaptSpinnerCidadesSC);
                }

                //Position= 2. Estado PR
                if( position == 2 ){
                    //Carrega as cidades do estado
                    ArrayAdapter<String> adaptSpinnerCidadesPR =  new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_list_item_1, listaCidadesPR);
                    spinnerCidades.setAdapter(adaptSpinnerCidadesPR);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                makeText(getApplicationContext(), "Nenhum estado selecionado!", LENGTH_SHORT).show();
            }

        });

    }

    public void btnAdicionarAluno(View view) {

        //Dados da ListView Completa
        ImageView imgViewFotoCapturada= (ImageView) findViewById(R.id.imgViewFoto);
        EditText tempMatricula = findViewById(R.id.edtTextMatricula);
        EditText tempNome = findViewById(R.id.edtTextNome);
        EditText tempEmail = findViewById(R.id.edtTextEmail);
        Spinner tempEstado = findViewById(R.id.spinnerEstado);
        Spinner tempCidade = findViewById(R.id.spinnerCidade);

        HashMap<String, Object> itensCompleta= new HashMap<String, Object>();
        //itensCompleta.put("foto", imgViewFotoCapturada.getDrawable());
        itensCompleta.put("foto", R.drawable.gre);
        itensCompleta.put("matricula", new String(tempMatricula.getText().toString()) );
        itensCompleta.put("nome", new String(tempNome.getText().toString()) );
        itensCompleta.put("email", new String(tempEmail.getText().toString()) );
        itensCompleta.put("estado", new String(tempEstado.getSelectedItem().toString()) );
        itensCompleta.put("cidade", new String(tempCidade.getSelectedItem().toString()) );
        lstViewAlunosCompleta.add(itensCompleta);

        tempMatricula.setText("");
        tempNome.setText("");
        tempEmail.setText("");
        tempEstado.setSelection(0, false);
        tempCidade.setSelection(0, false);

        MeuAdaptadorRevProvaI adapter= new MeuAdaptadorRevProvaI(this,
                lstViewAlunosCompleta, R.layout.lista_revisao_prova_1, de, para );
        lstViewAlunosTela= findViewById(R.id.lstViewRevProva1_Alunos);
        lstViewAlunosTela.setAdapter(adapter);
        lstViewAlunosTela.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "Position: " + Integer.toString(i), Toast.LENGTH_SHORT).show();
                HashMap<String, Object> item= lstViewAlunosCompleta.get(i);
                if ( item != null ) {
                    Intent intent= new Intent(RevisaoProvaIMainActivity.this, RevisaoProvaIDadosAlunosActivity.class);
                    if( item.containsKey("matricula") )
                        intent.putExtra("matricula", item.get("matricula").toString() );
                    if( item.containsKey("nome") )
                        intent.putExtra("nome", item.get("nome").toString() );
                    if( item.containsKey("email") )
                        intent.putExtra("email", item.get("email").toString() );
                    if( item.containsKey("cidade") )
                        intent.putExtra("cidade", item.get("cidade").toString() );
                    if( item.containsKey("estado") )
                        intent.putExtra("estado", item.get("estado").toString() );
                    startActivity(intent);
                }
            }
        });

    }

    public void btnCapturarFoto(View view) {
        //Toast.makeText(getApplicationContext(), "Capturar Foto!", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RESULT_CODE_CAMERA);
        } else {
            makeText(this, "CAMERA: Sem Permissão", LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //RETORNO "ACTION_IMAGE_CAPTURE"
        if (requestCode == RESULT_CODE_CAMERA && resultCode == RESULT_OK) {
            //Toast.makeText(this, "RESULT_CODE_CAMERA: OK", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imgViewImagemCapturada= (ImageView) findViewById(R.id.imgViewFoto);
            imgViewImagemCapturada.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            //MY_PERMISSIONS_REQUEST_CAMERA
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeText(this, "CAMERA: Permission granted", LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    makeText(this, "CAMERA: Permission denied", LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

}

class MeuAdaptadorRevProvaI extends SimpleAdapter {

    public MeuAdaptadorRevProvaI(RevisaoProvaIMainActivity revisaoProvaIMainActivity,
                                 List<HashMap<String, Object>> lstViewAlunosCompleta,
                                 int lista_revisao_prova_1, String[] de, int[] para) {
        super(revisaoProvaIMainActivity, lstViewAlunosCompleta, lista_revisao_prova_1, de, para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = super.getView(position, convertView, parent);

        return view;
    }

}
