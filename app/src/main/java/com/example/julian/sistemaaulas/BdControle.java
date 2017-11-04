package com.example.julian.sistemaaulas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by JULIAN on 24/10/2017.
 */

public class BdControle{
    private SQLiteDatabase bd;
    private SistemaAulaDB banco;

    public BdControle(Context context){
        banco = new SistemaAulaDB(context);
        //informa ao android que o banco sera usado para l e
        bd = banco.getWritableDatabase();
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {"_id","nome"};
        bd = banco.getReadableDatabase();
        //table, columns, selection, selectionArgs, groupBy, having, orderby, limit
        cursor = bd.query("usuario", campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();//coloca cursor no primeiro elemento retornado
        }
        bd.close();
        return cursor;
    }

    public Cursor carregaDadosPorID(int id){
        Cursor cursor;
        String[] campos = {"_id","nome","email","dataNasc", "senha"};
        String dados = "_id" + "=" + id;
        bd = banco.getReadableDatabase();
        cursor = bd.query("usuario",campos,dados, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        bd.close();
        return cursor;
    }

    public void alterarDados(int id, String nome, String email, String dataNasc, String senha){
        ContentValues valores;
        String dados;

        bd = banco.getWritableDatabase();

        dados = "_id" + "=" + id;

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("dataNasc", dataNasc);
        valores.put("senha", senha);

        bd.update("usuario",valores,dados,null);
        bd.close();
    }

    public void deletaUsuario(int id){
        String dados = "_id" + "=" + id;
        bd = banco.getReadableDatabase();
        bd.delete("usuario",dados,null);
        bd.close();
    }

}
