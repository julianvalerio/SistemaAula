package com.example.julian.sistemaaulas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        TextView edtValor = (TextView) findViewById(R.id.edtValor);
        Button btnFechar = (Button)findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(this);
        Bundle bundle= getIntent().getExtras();

        if(bundle.containsKey("Nome")){
            String valor = bundle.getString("Nome");
            edtValor.setText(valor+", Seja bem vindo!");
        }

        Button listar = (Button)findViewById(R.id.btnListar);
        listar.setOnClickListener(onClickListar());
    }

    private View.OnClickListener onClickListar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicial.this,ListaUsuariosCadastrados.class);
                startActivity(intent);
            }
        };
    }

    public void onClick(View v)
    {
        finish();
    }
}
