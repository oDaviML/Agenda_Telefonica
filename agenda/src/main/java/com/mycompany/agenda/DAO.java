package com.mycompany.agenda;

import java.util.ArrayList;

public class DAO extends DTO{
    
    ArrayList<DTO> dtos = new ArrayList<>();   

    private void AdicionarContato(Integer codigo, String nome, Integer telefone) {
        DTO contato = new DTO(codigo, nome, telefone);
        dtos.add(contato);
    }

    private void RemoverContato(Integer codigo) {
        if (dtos.contains(codigo)) {
            dtos.remove(codigo);
        }
        
    }

    private void AtualizarContato(Integer cod) {
        //A implementar
    }
}
