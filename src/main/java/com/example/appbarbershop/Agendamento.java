package com.example.appbarbershop;

public class Agendamento {

    public String id, nome, telefone, cpf, data, hora, cabelo, barba, outros;

    @Override
    public String toString() {
        return nome + "/n" + data + "/n" + cabelo ;
    }
}
