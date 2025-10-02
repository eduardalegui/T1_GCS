package Gerenciamento;

import Dados.Evento;
import java.util.List;
import java.util.Scanner;

public class ConsultaEvento {
    public static void consultar(List<Evento> eventos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do evento: ");
        String nome = scanner.nextLine();

        boolean encontrado = false;
        for (Evento evento : eventos) {
            if (evento.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Detalhes do Evento:");
                System.out.println("Nome: " + evento.getNome());
                System.out.println("Data: " + evento.getData());
                System.out.println("Local: " + evento.getLocal());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Evento não encontrado.");
        }
    }
}