package com.odaviml;

import com.odaviml.grupos.GrupoDAO;
import com.odaviml.tipos.TipoDAO;

public class Service {

    private Service() {}

    ////////////////////////////////////////////////////////////////////////////////
    // DAO E DTO Contatos
    public static DTO adicionarContato(String nome, Long telefone, String tipo, String email, String rua, String bairro, String grupo){
        return DAO.inserirContato(nome, telefone, tipo, email, rua, bairro, grupo);
    }

    public static void removerContato(Integer codigo){
        DAO.removerContato(codigo);
    }

    public static void editarContato(Integer codigo ,String nome, Long telefone, String tipo, String email, String rua, String bairro, String grupo) {
        DAO.atualizarContato(codigo, nome, telefone, tipo, email, rua, bairro, grupo);
    }

    public static DTO consultaPorID(Integer codigo) {
        return DAO.consultaPorID(codigo);
    }

    public static DTO consultaPorNome(String nome) {
        return DAO.consultaPorNome(nome);
    }

    public static DTO consultaPorTelefone(Long telefone) {
        return DAO.consultaPorNumero(telefone);
    }


    ////////////////////////////////////////////////////////////////////////////////
    // Tipo

    public static void adicionarTipo(String tipo) {
        TipoDAO.inserirTipo(tipo);
    }

    public static void removerTipo(String tipo) {
        TipoDAO.removerTipo(tipo);
    }

    public static void editarTipo(String tipoAntigo, String tipoNovo) {
        TipoDAO.editarTipo(tipoAntigo, tipoNovo);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Grupo

    public static void adicionarGrupo(String tipo) {
        GrupoDAO.inserirGrupo(tipo);
    }

    public static void removerGrupo(String tipo) {
        GrupoDAO.removerGrupo(tipo);
    }

    public static void editarGrupo(String grupoAntigo, String grupoNovo) {
        GrupoDAO.editarGrupo(grupoAntigo, grupoNovo);
    }
}
