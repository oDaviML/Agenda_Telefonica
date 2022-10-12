package com.odaviml;

public class Service {

    private Service() {}

    public static DTO adicionarContato(String nome, String telefone, String tipo, String email, String rua, String bairro, String grupo){
        return DAO.inserirContato(nome, telefone, tipo, email, rua, bairro, grupo);
    }

    public static void removerContato(Integer codigo){
        DAO.removerContato(codigo);
    }

    public static void editarContato(Integer codigo ,String nome, String telefone, String tipo, String email, String rua, String bairro, String grupo) {
        DAO.atualizarContato(codigo, nome, telefone, tipo, email, rua, bairro, grupo);
    }

    public static DTO consultaPorID(Integer codigo) {
        return DAO.consultaPorID(codigo);
    }
    public static DTO consultaPorNome(String nome) {
        return DAO.consultaPorNome(nome);
    }
    public static DTO consultaPorTelefone(String telefone) {
        return DAO.consultaPorNumero(telefone);
    }
}
