package com.m35725.unisc_pdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Aula4MainActivity extends AppCompatActivity {

    public final int RESULT_CODE_SELECIONAR_CONTATO= 0;
    public final int RESULT_CODE_SELECIONAR_CONTATO2= 1;
    public final int RESULT_CODE_CAMERA= 2;

    public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS= 101;
    public final int MY_PERMISSIONS_REQUEST_CALL_PHONE= 102;
    public final int MY_PERMISSIONS_REQUEST_INTERNET= 103;
    public final int MY_PERMISSIONS_REQUEST_CAMERA= 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula4_main);
    }

    public void btnAula4ContatosClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("content://com.android.contacts/contacts");
            Intent intent = new Intent(Intent.ACTION_PICK, uri);
            startActivityForResult(intent, RESULT_CODE_SELECIONAR_CONTATO);
        } else {
            Toast.makeText(this, "READ_CONTACTS: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    public void btnAula4Contatos2Click(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(intent, RESULT_CODE_SELECIONAR_CONTATO2);
        } else {
            Toast.makeText(this, "READ_CONTACTS: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    public void btnAula4CallActionCallClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:02151991222222"));
            startActivity(phoneIntent);
        } else {
            Toast.makeText(this, "CALL_PHONE: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    public void btnAula4CallActionDialClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse("tel:02151991181818"));
            startActivity(phoneIntent);
        } else {
            Toast.makeText(this, "CALL_PHONE: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    public void btnAula4WebClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            Uri uri= Uri.parse("http://www.unisc.br");
            Intent intent= new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Toast.makeText(this, "ACTION_VIEW: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_INTERNET);
        }
    }

    public void btnAula4PicItClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RESULT_CODE_CAMERA);
        } else {
            Toast.makeText(this, "CAMERA: Sem Permissão", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //RETORNO "ACTION_IMAGE_CAPTURE"
        if (requestCode == RESULT_CODE_CAMERA && resultCode == RESULT_OK) {
            Toast.makeText(this, "RESULT_CODE_CAMERA: OK", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imgViewImagemCapturada= (ImageView) findViewById(R.id.imgViewAula4ImagemCapturada);
            imgViewImagemCapturada.setImageBitmap(imageBitmap);
        }

        //RETORNO "RESULT_CODE_SELECIONAR_CONTATO2"
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
            //RETORNO "RESULT_CODE_SELECIONAR_CONTATO"
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            //MY_PERMISSIONS_REQUEST_READ_CONTACTS
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "READ_CONTACTS: Permission granted", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Toast.makeText(this, "READ_CONTACTS: Permission denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            //MY_PERMISSIONS_REQUEST_CALL_PHONE
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CALL_PHONE: Permission granted", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Toast.makeText(this, "CALL_PHONE: Permission denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            //MY_PERMISSIONS_REQUEST_INTERNET
            case MY_PERMISSIONS_REQUEST_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "INTERNET: Permission granted", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Toast.makeText(this, "INTERNET: Permission denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            //MY_PERMISSIONS_REQUEST_CAMERA
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CAMERA: Permission granted", Toast.LENGTH_SHORT).show();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Toast.makeText(this, "CAMERA: Permission denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
