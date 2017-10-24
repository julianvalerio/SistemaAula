package com.example.julian.sistemaaulas;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaUsuariosCadastrados extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios_cadastrados);

        BdControle listarDados = new BdControle(getBaseContext());
        Cursor cursor = listarDados.carregaDados();

        String[] idNome = new String[]{"_id", "nome"};
        int[]idElementos = new int[]{R.id.idUsr, R.id.nomeUsr};
        //mapear colunas de um cursor para componentes definidos em xml
        //cursor fornece acesso para leitura e escrita retornado por uma consulta no BD
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_lista_usuarios_cadastrados,cursor,idNome,idElementos, 0);//flag usada para determinar o comportamento do adaptador, observar modificacoes
        //nao necessario para usar cursor adapter ou loader
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }
}
