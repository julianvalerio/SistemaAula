package com.example.julian.sistemaaulas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Cadastrese extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private Usuario usuarios;
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

                usuarios = new Usuario();
                usuarios.setNome(nomeString);
                usuarios.setEmail(emailString);
                usuarios.setDataNasc(dataNascString);
                usuarios.setSenha(senhaString);

                resultado = cadastra.insereDado(nomeString,
                        emailString,dataNascString,senhaString);

                //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                salvarUsuario();
            }
        };

    }

    private void salvarUsuario(){
        autenticacao =
                ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword
                (usuarios.getEmail(), usuarios.getSenha()
                ).addOnCompleteListener(Cadastrese.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                if (task.isSuccessful()){
                    Toast.makeText(Cadastrese.this, "Usuário Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    String idUsuario = Codificador.codificarTexto(usuarios.getEmail());
                    usuarios.setId(idUsuario);
                    usuarios.salvar();
                    Intent it = new Intent(Cadastrese.this,Login.class);
                    startActivity(it);
                    finish();
                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Email invalido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Email ja cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastrese.this,"Erro "+ excecao,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
