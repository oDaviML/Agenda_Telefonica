package com.odaviml;

public class DTO {
    private String nome, telefone, tipo;
    private Integer codigo;



    public DTO(Integer codigo, String nome, String telefone, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    
}
