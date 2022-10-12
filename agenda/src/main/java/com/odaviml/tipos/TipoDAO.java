package com.odaviml.tipos;

import java.util.ArrayList;
import java.util.List;

public class TipoDAO {
    private static List<String> tipoLista;
    static {
        tipoLista = new ArrayList<String>();
    }

    public static void inserirTipo(String tipo) {
        tipoLista.add(tipo);
    }

    static List<String> toRemove = new ArrayList();
    public static void removerTipo(String tipo) {
        for (String c: tipoLista){
            if (c.equals(tipo)){
                toRemove.add(c);
            }
        }
        tipoLista.removeAll(toRemove);
    }

    public static void editarTipo(String tipoAntigo, String tipoNovo) {
        for (String c: tipoLista){
            if (c.equals(tipoAntigo)){
                tipoLista.remove(c);
                tipoLista.add(tipoNovo);
            }
        }
    }

    public static String consultaPorNome(String nome) {
        for (String c: tipoLista){
            if (c.equals(nome)){
                return c;
            }
        }
        return null;
    }

    public static String getTipoLista() {
        return tipoLista.get(tipoLista.size()-1);
    }

    public static String getPrimeiroItem() {
        return tipoLista.get(0);
    }

}
