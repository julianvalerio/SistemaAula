package com.example.julian.sistemaaulas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastrese extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrese);
        Button cadastrar = (Button)findViewById(R.id.btnConfirmar);
        cadastrar.setOnClickListener(onClickCadastrar());
    }

    private View.OnClickListener onClickCadastrar() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //acessar recursos (activity, intent, broadcast, abrir bd...)
                SistemaAulaDB cadastra = new SistemaAulaDB(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.edtNomeCad);
                EditText email = (EditText)findViewById((R.id.edtEmail));
                EditText dataNasc = (EditText)findViewById(R.id.edtData);
                EditText senha = (EditText)findViewById(R.id.edtSenhaCad);
                String nomeString = nome.getText().toString();
                String emailString = email.getText().toString();
                String dataNascString = dataNasc.getText().toString();
                String senhaString = senha.getText().toString();
                String resultado;


                resultado = cadastra.insereDado(nomeString,
                        emailString,dataNascString,senhaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        };

    }
}
