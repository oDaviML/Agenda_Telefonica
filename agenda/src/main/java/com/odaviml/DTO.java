package com.odaviml;

public class DTO {
    private String nome, tipo, email, rua, bairro, grupo;
    private Integer codigo;
    private Long telefone;

    public DTO () {}

    public DTO(Integer codigo, String nome, Long telefone, String tipo, String email, String rua, String bairro, String grupo) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
        this.email = email;
        this.rua = rua;
        this.bairro = bairro;
        this.grupo = grupo;
    }

    ////////////////////////////////////////////////////
    // Setters

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(Long telefone) {
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
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    ///////////////////////////////////////////////////
    // Getters

    public Integer getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public Long getTelefone() {
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
    public String getGrupo() {
        return grupo;
    }
}
