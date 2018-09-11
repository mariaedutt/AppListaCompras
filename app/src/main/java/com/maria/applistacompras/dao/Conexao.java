package com.maria.applistacompras.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "appLista";
    private static final int VERSAO_BANCO = 2;

    public Conexao(Context contexto){
        super(contexto, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS categorias (id INTEGER NOT NULL PRIMARY KEY , nome TEXT NOT NULL )" );
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS produtos (id INTEGER NOT NULL PRIMARY KEY , nome TEXT NOT NULL , quantidade DOUBLE , codCategoria INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antiga, int nova) {

        if (antiga == 1 && nova == 2){
            sqLiteDatabase.execSQL("DROP TABLE produtos");
            onCreate(sqLiteDatabase);


        }

    }
}
