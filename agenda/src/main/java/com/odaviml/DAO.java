package com.odaviml;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAO{
    
    private static ObservableList<DTO> observableListClientes;
    private static List<DTO> contatoLista;
    private static int codigo;
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

    public void removerContato(int codigo) {
        if (contatoLista.contains(codigo)) {
            contatoLista.remove(codigo);
        }
        
    }

    public static ObservableList<DTO> getObservableListClientes() {
        return observableListClientes;
    }

    public static List<DTO> getContatoLista() {
        return contatoLista;
    }
}
