package com.odaviml;

public class Service {

    private Service() {}

    public static DTO AdicionarContato(String nome, String telefone, String tipo, String email, String rua, String bairro){
        return DAO.inserirContato(nome, telefone, tipo, email, rua, bairro);
    }

    public static void removerContato(Integer codigo){
        DAO.removerContato(codigo);
    }
}
