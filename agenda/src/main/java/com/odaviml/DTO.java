package com.odaviml;

public class DTO {
    private String nome, telefone, tipo, email, rua, bairro;
    private int codigo;

    public DTO(int codigo, String nome, String telefone, String tipo, String email, String rua, String bairro) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setRua(String rua) {
        this.rua = rua;
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
    public String getEmail() {
        return email;
    }
    public String getBairro() {
        return bairro;
    }
    public String getRua() {
        return rua;
    }
}
