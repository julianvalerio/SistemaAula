package com.example.julian.sistemaaulas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    public static final String preferencias="sistemaAulas";
    private EditText edtNome;
    private EditText edtSenha;
    private Button avancar;
    private FirebaseAuth autenticacao;
    private Usuario usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNome = (EditText)findViewById(R.id.edtNomeCad);
        edtSenha = (EditText) findViewById(R.id.edtSenhaCad);
        avancar = (Button)findViewById(R.id.entrar);
        avancar.setOnClickListener(onClickEntrar());

        SharedPreferences dados =
                Login.this.getPreferences(Context.MODE_PRIVATE);
        edtNome.setText(dados.getString("sistemaAulas",""));
    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!edtNome.getText().toString().equals("") &&
                        !edtSenha.getText().toString().equals("")){
                    usuarios = new Usuario();
                    usuarios.setEmail(edtNome.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());
                    validarLogin();
                } else {
                    Toast.makeText(Login.this, "Preencha os campos de e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
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

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Login.this,ListaUsuariosCadastrados.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
