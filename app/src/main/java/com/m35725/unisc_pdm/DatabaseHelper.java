package com.m35725.unisc_pdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DADOS = "Aula9_Revenda";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context){
        super(context, NOME_BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE carro (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, modelo TEXT, ano TEXT, valor DOUBLE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS carro");
    }

}
