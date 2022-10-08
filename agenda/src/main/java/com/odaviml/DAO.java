package com.odaviml;

import java.util.ArrayList;
import java.util.List;

public class DAO{
    
    private static List<DTO> contatoLista;
    private static int codigo;
    static {
        contatoLista = new ArrayList<>();
        codigo = 1;
    }

    public static DTO inserirContato(String nome, String telefone, String tipo) {
        DTO contato = new DTO(codigo, nome, telefone, tipo);
        contatoLista.add(contato);
        codigo++;
        return contato;
    }

    public void removerContato(Integer codigo) {
        if (contatoLista.contains(codigo)) {
            contatoLista.remove(codigo);
        }
        
    }

    public void atualizarContato(Integer cod) {
        //A implementar
    }
}
