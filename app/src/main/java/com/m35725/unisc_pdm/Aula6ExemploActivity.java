package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

//public class Aula6ExemploActivity extends AppCompatActivity {
public class Aula6ExemploActivity extends ListActivity implements AdapterView.OnItemClickListener {

    String [] values = {"André","Bernardo","Carlos","Daniel","Diego","Eduardo","Edson","Fábio","Gustavo","Inácio","José","Klaus","Luis","Márcio","Nair" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula6_exemplo);

        ArrayAdapter<String> adaptList= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Arrays.asList(values));
        setListAdapter(adaptList);
        ListView listView= getListView();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        TextView textView= (TextView) view;
        String msg= "Mensagem: " + textView.getText();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(Aula6ExemploActivity.this, Aula6ConsultaActivity.class);
        intent.putExtra("NomePessoa", textView.getText());
        startActivity(intent);
    }

    /*
    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        // Get the list data adapter.
        ListAdapter listAdapter = listView.getAdapter();
        // Get user selected item object.
        Object selectItemObj = listAdapter.getItem(position);
        String itemText = (String)selectItemObj;

        // Create an AlertDialog to show.
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(itemText);
        alertDialog.show();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the activity layout xml file.
        setContentView(R.layout.activity_list_activity_example);

        // Create a list data which will be displayed in inner ListView.
        List<String> listData = new ArrayList<String>();
        listData.add("Audi");
        listData.add("Benz");
        listData.add("BMW");
        listData.add("Ford");
        listData.add("Honda");
        listData.add("Toyoto");

        // Create the ArrayAdapter use the item row layout and the list data.
        ArrayAdapter<String> listDataAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_activity_example_row, R.id.listRowTextView, listData);

        // Set this adapter to inner ListView object.
        this.setListAdapter(listDataAdapter);
    }

    // When user click list item, this method will be invoked.
    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        // Get the list data adapter.
        ListAdapter listAdapter = listView.getAdapter();
        // Get user selected item object.
        Object selectItemObj = listAdapter.getItem(position);
        String itemText = (String)selectItemObj;

        // Create an AlertDialog to show.
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(itemText);
        alertDialog.show();
    }




    */
}
