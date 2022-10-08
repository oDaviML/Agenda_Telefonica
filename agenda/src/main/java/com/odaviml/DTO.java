package com.odaviml;

public class DTO {
    private String nome, telefone, tipo;
    private int codigo;



    public DTO(int codigo, String nome, String telefone, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public void setCodigo(int codigo) {
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

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return " " + getCodigo() + " " + getNome() + " " + getTelefone() + " " + getTipo();
    }
    
}
