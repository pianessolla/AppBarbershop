package com.example.appbarbershop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ColaboradorActivity extends AppCompatActivity {

    private EditText etEmail2, etSenha2;
    protected Button btnEntrar;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador);

        etEmail2 = findViewById(R.id.etEmail2);
        etSenha2 = findViewById(R.id.etSenha2);
        btnEntrar = findViewById(R.id.btnEntrar);

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = auth.getCurrentUser();

                if(user!=null){
                    Intent intent = new Intent(ColaboradorActivity.this, AgendaActivity.class);
                    startActivity(intent);

                }
            }
        };

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // entrar();
                Intent intent = new Intent(ColaboradorActivity.this, AgendaActivity.class);
                startActivity(intent);

            }
        });

    }

    private void entrar(){
        String email = etEmail2.getText().toString();
        String senha = etSenha2.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()){
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(ColaboradorActivity.this, "E-mail e/ou senha Inválido(s)", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}