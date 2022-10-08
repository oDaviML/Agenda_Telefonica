package com.odaviml;

public class Service {

    private Service() {}

    public static DTO AdicionarContato(String nome, String telefone, String tipo){
        return DAO.inserirContato(nome, telefone, tipo);
    }

    public static DTO removerContato(){
        return null;
    }
}
