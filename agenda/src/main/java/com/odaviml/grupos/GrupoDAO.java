package com.odaviml.grupos;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {
    private static List<String> grupoLista;
    static {
        grupoLista = new ArrayList<String>();
    }

    public static void inserirGrupo(String grupo) {
        grupoLista.add(grupo);
    }

    static List<String> toRemove = new ArrayList();
    public static void removerGrupo(String grupo) {
        for (String c: grupoLista){
            if (c.equals(grupo)){
                toRemove.add(c);
            }
        }
        grupoLista.removeAll(toRemove);
    }

    public static void editarGrupo(String grupoAntigo, String grupoNovo) {
        for (String c: grupoLista){
            if (c.equals(grupoAntigo)){
                grupoLista.remove(c);
                grupoLista.add(grupoNovo);
            }
        }
    }

    public static String consultaPorNome(String nome) {
        for (String c: grupoLista){
            if (c.equals(nome)){
                return c;
            }
        }
        return null;
    }

    public static String getGrupoLista() {
        return grupoLista.get(grupoLista.size()-1);
    }

    public static String getPrimeiroItem() {
        return grupoLista.get(0);
    }

}
