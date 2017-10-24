package com.example.julian.sistemaaulas;

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
}
