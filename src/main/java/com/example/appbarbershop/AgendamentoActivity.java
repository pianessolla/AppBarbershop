package com.example.appbarbershop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgendamentoActivity extends AppCompatActivity {

    private EditText etNome, etTelefone, etCPF, etData, etHora;
    private Spinner spCabelo, spBarba, spOutros;
    private Button btEnviar;
    //private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etCPF = findViewById(R.id.etCPF);
        etData = findViewById(R.id.etData);
        etHora = findViewById(R.id.etHora);

        spCabelo = findViewById(R.id.spCabelo);
        spBarba = findViewById(R.id.spBarba);
        spOutros = findViewById(R.id.spOutros);

        btEnviar = findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
                //confirmar();
            }
        });

    }

    public void enviar(){
        String nome = etNome.getText().toString();
        String telefone = etTelefone.getText().toString();
        String cpf = etCPF.getText().toString();
        String data = etData.getText().toString();
        String hora = etHora.getText().toString();

        String cabelo = spCabelo.getSelectedItem().toString();
        String barba = spBarba.getSelectedItem().toString();
        String outros = spOutros.getSelectedItem().toString();

        if(!nome.isEmpty()){
            Agendamento agendamento = new Agendamento();
            agendamento.nome = nome;
            agendamento.telefone = telefone;
            agendamento.cpf = cpf;
         if (!data.isEmpty() && !hora.isEmpty()){
             agendamento.data = data;
             agendamento.hora = hora;
             agendamento.cabelo = cabelo;
             agendamento.barba = barba;
             agendamento.outros = outros;
         }

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            reference.child("agendamentos").push().setValue(agendamento);
            finish();

        }else {
            Toast.makeText(AgendamentoActivity.this, "Informe o nome para prosseguir", Toast.LENGTH_LONG).show();
        };


    }

//    //private void confirmar() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Confira seu agendamento:");
//        builder.setMessage(etData.toString() + etHora.toString() + spCabelo.toString() + spBarba.toString() + spOutros.toString());
//        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        alertDialog = builder.create();
//        alertDialog.show();
//    }


}