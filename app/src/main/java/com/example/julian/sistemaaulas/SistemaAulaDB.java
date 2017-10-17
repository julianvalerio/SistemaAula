package com.example.julian.sistemaaulas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by JULIAN on 01/10/2017.
 */

public class SistemaAulaDB extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "sistemaAula.db";
    private static final String TABELA = "usuarios";
    private static final String ID = "_id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String DATA_NASC = "dataNasc";
    private static final String SENHA = "senha";
    private static final int VERSAO = 1;
    //context usado para abrir ou criar DB, factory usado para criar objetos
    public SistemaAulaDB(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario" +
                "(_id integer primary key autoincrement, " +
                "nome text not null, email text not null,  " +
                "dataNasc text not null, senha text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String insereDado(String nome, String email, String dataNasc, String senha){
        long resultado;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("dataNasc", dataNasc);
        valores.put("senha", senha);
        //caso um parametro nao seja passado, atribui NULL
        resultado = db.insert("usuario", null, valores);
        db.close();
        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


}
