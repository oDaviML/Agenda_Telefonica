package com.mycompany.agenda;
import java.util.Scanner;

public class Agenda {

    public static void main(String[] args) {
        System.out.println("Selecione a operação:");
        System.out.println("Adicionar - AD");
        System.out.println("Excluir - EX");
        System.out.println("Atualizar - AT");
        System.out.println("Listar - LT");

        String op;
        Scanner input = new Scanner(System.in);

        op = input.next();

        switch (op) {
            case "AD":
                System.out.println("Selecionado Adicionar");
                break;
            case "EX":
                System.out.println("Selecionado Exlcuir");
                break;
            case "AT":
                System.out.println("Selecionado Atualizar");
                break;
            case "LT":
                System.out.println("Selecionado Listar");
                break;
            default:
                System.out.println("Inválido");
                break;
        }
        
    }
}
