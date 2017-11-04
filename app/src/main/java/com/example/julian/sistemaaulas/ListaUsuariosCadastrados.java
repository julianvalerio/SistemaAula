package com.example.julian.sistemaaulas;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListaUsuariosCadastrados extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios_cadastrados);

        BdControle listarDados = new BdControle(getBaseContext());
        final Cursor cursor = listarDados.carregaDados();

        String[] idNome = new String[]{"_id", "nome"};
        int[]idElementos = new int[]{R.id.idUsr, R.id.nomeUsr};
        //mapear colunas de um cursor para componentes definidos em xml
        //cursor fornece acesso para leitura e escrita retornado por uma consulta no BD
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_lista_usuarios_cadastrados,cursor,idNome,idElementos, 0);//flag usada para determinar o comportamento do adaptador, observar modificacoes
        //nao necessario para usar cursor adapter ou loader
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                cursor.moveToPosition(position);

                // selected item
                String idusuario = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                // Launching new Activity on selecting single List Item
                Intent tela = new Intent(getApplicationContext(), AlterarDados.class);
                // sending data to new activity
                tela.putExtra("idusuario", idusuario);
                startActivity(tela);

            }
        });
    }
}
