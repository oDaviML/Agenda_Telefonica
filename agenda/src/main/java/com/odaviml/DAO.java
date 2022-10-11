package com.odaviml;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAO{
    
    private static ObservableList<DTO> observableListClientes;
    private static List<DTO> contatoLista;
    private static Integer codigo;
    static {
        contatoLista = new ArrayList<>();
        codigo = 1;
    }

    public static DTO inserirContato(String nome, String telefone, String tipo, String email, String rua, String bairro) {
        DTO contato = new DTO(codigo, nome, telefone, tipo, email, rua, bairro);
        contatoLista.add(contato);
        codigo++;
        observableListClientes = FXCollections.observableArrayList(contatoLista);
        return contato;
    }
    static List<DTO> toRemove = new ArrayList();
    public static void removerContato(Integer cdg) {
        for (DTO c: contatoLista){
            if (c.getCodigo().equals(cdg)){
                toRemove.add(c);
            }
        }
        contatoLista.removeAll(toRemove);
        observableListClientes = FXCollections.observableArrayList(contatoLista);
    }

    public static void atualizarContato(Integer codigo ,String nome, String telefone, String tipo, String email, String rua, String bairro) {
        for (DTO c: contatoLista){
            if (c.getCodigo().equals(codigo)){
                c.setNome(nome);
                c.setTelefone(telefone);
                c.setTipo(tipo);
                c.setEmail(email);
                c.setRua(rua);
                c.setBairro(bairro);
            }
        }
        observableListClientes = FXCollections.observableArrayList(contatoLista);
    }

    public static DTO consultarPorNome(String nome) {
        for (DTO c: contatoLista){
            if (c.getNome().equals(nome)){
                return c;
            }
        }
        return null;
    }

    public static DTO consultaPorID(Integer codigo) {
        for (DTO c: contatoLista){
            if (c.getCodigo().equals(codigo)){
                return c;
            }
        }
        return null;
    }

    public static Integer getCodigo() {
        return codigo;
    }

    public static ObservableList<DTO> getObservableListClientes() {
        return observableListClientes;
    }
}
