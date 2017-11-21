package com.example.julian.sistemaaulas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlterarDados extends AppCompatActivity {
    EditText Nome;
    EditText Email;
    EditText DataNasc;
    EditText Senha;
    String IdUsuario;
    BdControle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);

        IdUsuario = this.getIntent().getStringExtra("idusuario");
        controle = new BdControle(getBaseContext());

        Nome = (EditText)findViewById(R.id.edtNomeCad);
        Email = (EditText)findViewById(R.id.edtEmail);
        DataNasc = (EditText)findViewById(R.id.edtData);
        Senha = (EditText)findViewById(R.id.edtSenhaCad);

        Button alterar = (Button)findViewById(R.id.btnConfirmar);
        Button excluir = (Button)findViewById(R.id.btnexcluir);
        Button enviarMsg = (Button)findViewById(R.id.btnEnviar);

        Cursor cursor = controle.carregaDadosPorID(Integer.parseInt(IdUsuario));
        Nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        Email.setText(cursor.getString(cursor.getColumnIndexOrThrow("email")));
        DataNasc.setText(cursor.getString(cursor.getColumnIndexOrThrow("dataNasc")));
        Senha.setText(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        
        alterar.setOnClickListener(onClickAlterar());
        excluir.setOnClickListener(onClickExcluir());

    }
    

    private View.OnClickListener onClickExcluir() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controle.deletaUsuario(Integer.parseInt(IdUsuario));
                Intent intent = new Intent(AlterarDados.this,ListaUsuariosCadastrados.class);
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener onClickAlterar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controle.alterarDados(Integer.parseInt(IdUsuario),
                        Nome.getText().toString(),Email.getText().toString(),
                        DataNasc.getText().toString(), Senha.getText().toString());
                Intent intent = new Intent(AlterarDados.this,ListaUsuariosCadastrados.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
