package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Aula4MainActivity extends AppCompatActivity {

    public final int RESULT_CODE_SELECIONAR_CONTATO= 0;
    public final int RESULT_CODE_SELECIONAR_CONTATO2= 1;
    public final int RESULT_CODE_CALL= 2;
    public final int RESULT_CODE_CAMERA= 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula4_main);
    }

    public void btnAula4ContatosClick(View view) {
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, RESULT_CODE_SELECIONAR_CONTATO);
    }

    public void btnAula4Contatos2Click(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, RESULT_CODE_SELECIONAR_CONTATO2);
    }

    public void btnAula4CallClick(View view) {
        Uri uri= Uri.parse("tel: 9998877665");
        Intent intent= new Intent(Intent.ACTION_CALL, uri);
        startActivity(intent);
    }

    public void btnAula4WebClick(View view) {
        Uri uri= Uri.parse("http://www.unisc.br");
        Intent intent= new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void btnAula4PicItClick(View view) {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESULT_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //RETORNO "ACTION_IMAGE_CAPTURE"
        if (requestCode == RESULT_CODE_CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "RESULT_CODE_CAMERA: OK", Toast.LENGTH_SHORT).show();
        }
        
        //RETORNO "ACTION_PICK"
        if (requestCode == RESULT_CODE_SELECIONAR_CONTATO2 && resultCode == RESULT_OK) {
            Uri contactData = data.getData();

            Cursor phones = getContentResolver()
                    .query(contactData,
                            null,
                            null,
                            null,
                            null);
            String name = "", phoneNumber = "";

            while (phones.moveToNext()) {
                name = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));

            }
            phones.close();

            TextView txtViewNomeContato = (TextView) findViewById(R.id.txtViewNomeContato);
            txtViewNomeContato.setText(name);

            TextView txtViewTelefoneContato = (TextView) findViewById(R.id.txtViewTelefoneContato);
            txtViewTelefoneContato.setText(phoneNumber);

        } else {
            //RETORNO "ACTION_PICK"
            if (requestCode == RESULT_CODE_SELECIONAR_CONTATO && resultCode == RESULT_OK) {
                Uri uri = data.getData();
                //Cursor c = managedQuery(uri,null,null,null,null);
                Cursor c = getContentResolver().query(uri, null, null, null, null);
                c.moveToNext();
                int nameCol = c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
                int idCol = c.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
                String nome = c.getString(nameCol);
                String id = c.getString(idCol);
                c.close();

                Cursor phones = getContentResolver().query
                        (ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                                        " = " + id, null, null);
                phones.moveToNext();
                String phoneNumber = phones.getString
                        (phones.getColumnIndexOrThrow
                                (ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.close();

                TextView txtViewNomeContato = (TextView) findViewById(R.id.txtViewNomeContato);
                txtViewNomeContato.setText(nome);

                TextView txtViewTelefoneContato = (TextView) findViewById(R.id.txtViewTelefoneContato);
                txtViewTelefoneContato.setText(phoneNumber);

            } else {
                if (requestCode == RESULT_CODE_CAMERA && resultCode == RESULT_OK) {
                    //retorno da camera
                }
            }

        }

    }



}
