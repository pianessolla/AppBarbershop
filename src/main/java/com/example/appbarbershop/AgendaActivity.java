package com.example.appbarbershop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    private ListView lvAgenda;
    private List<Agendamento> lista;
    private ArrayAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        lista = new ArrayList<>();

        lvAgenda = findViewById(R.id.lvAgenda);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvAgenda.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        lista.clear();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("agendamentos").orderByChild("data");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Agendamento agendamento = new Agendamento();
                agendamento.id = snapshot.getKey();
                agendamento.nome = snapshot.child("nome").getValue(String.class);
                agendamento.telefone = snapshot.child("telefone").getValue(String.class);
                agendamento.cpf = snapshot.child("cpf").getValue(String.class);
                agendamento.data = snapshot.child("data").getValue(String.class);
                agendamento.hora = snapshot.child("hora").getValue(String.class);
                agendamento.cabelo = snapshot.child("cabelo").getValue(String.class);
                agendamento.barba = snapshot.child("barba").getValue(String.class);
                agendamento.outros = snapshot.child("outros").getValue(String.class);

                lista.add(agendamento);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        query.addChildEventListener(childEventListener);

    }

    @Override
    protected void onStop() {
        super.onStop();

        query.removeEventListener(childEventListener);
    }
}