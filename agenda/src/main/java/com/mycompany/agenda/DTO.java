package com.mycompany.agenda;

public class DTO {
    private String nome;
    private Integer codigo, telefone;

    public DTO() {
        
    }

    public DTO(Integer codigo, String nome, Integer telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public Integer getTelefone() {
        return telefone;
    }
    
}
