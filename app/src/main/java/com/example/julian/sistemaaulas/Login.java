package com.example.julian.sistemaaulas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static final String preferencias="sistemaAulas";
    EditText edtNome;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtNome = (EditText)findViewById(R.id.edtNomeCad);
        Button avancar = (Button)findViewById(R.id.entrar);
        avancar.setOnClickListener(onClickEntrar());
        //Context context = Login.this;
        //context mode private arquivo criado so pode ser acessado pela propria aplicação
        SharedPreferences dados =
                Login.this.getPreferences(Context.MODE_PRIVATE);
        edtNome.setText(dados.getString("sistemaAulas",""));
    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView login = (TextView)findViewById(R.id.edtNomeCad);
                TextView senha = (TextView)findViewById(R.id.edtSenhaCad);
                String stringLogin = login.getText().toString();
                String stringSenha = senha.getText().toString();
                if(stringLogin.equals("julian") && stringSenha.equals("123")){
                    Intent intent = new Intent(Login.this,TelaInicial.class);
                    intent.putExtra("Nome", stringLogin);
                    startActivity(intent);
                }else
                    alert("Login e senha não conferem!");
            }
        };
    }

    public void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        CheckBox chkSalvar =
                (CheckBox)findViewById(R.id.checkBox);
        if(chkSalvar.isChecked()){
            SharedPreferences settings =
                    Login.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("sistemaAulas", edtNome.getText().toString());
            //Confirma a gravação dos dados
            editor.commit();
        }
    }

}
