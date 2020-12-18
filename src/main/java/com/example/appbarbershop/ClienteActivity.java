package com.example.appbarbershop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ClienteActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btnCadastrar, btnJaSouCliente;
    private ImageButton saida;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        etEmail = findViewById(R.id.etEmail2);
        etSenha = findViewById(R.id.etSenha2);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnJaSouCliente = findViewById(R.id.btnJaSouCliente);
        saida = findViewById(R.id.saida);

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = auth.getCurrentUser();

                if(user!=null){
                    Intent intent = new Intent(ClienteActivity.this, AgendamentoActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ClienteActivity.this, "Erro no login!", Toast.LENGTH_LONG).show();
                }
            }
        };

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        btnJaSouCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });

        saida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cadastrar(){
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()){
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(ClienteActivity.this, "E-mail e/ou senha Inválido(s)", Toast.LENGTH_LONG).show();
                    }
                    else {
                        user = auth.getCurrentUser();
                        Toast.makeText(ClienteActivity.this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ClienteActivity.this, AgendamentoActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    private void logar() {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()) {
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(ClienteActivity.this, "E-mail e/ou senha Inválido(s)", Toast.LENGTH_LONG).show();

                    } else {
                        Intent intent = new Intent(ClienteActivity.this, AgendamentoActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }
}