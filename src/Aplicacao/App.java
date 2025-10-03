package Aplicacao;

// Importações
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;
import Gerenciamento.GerenciaEvento;

public class App {
    // Atributos para redirecionamento de I/O
    private Scanner in = new Scanner(System.in);  // Entrada padrão
    private final String nomeArquivoEntrada = "dadosin.txt";  // Arquivo de entrada
    private final String nomeArquivoSaida = "dadosout.txt";   // Arquivo de saída
    private GerenciaEvento gerenciaEvento;

    public App() {
        //redirecionaEntrada();    // Redireciona entrada para arquivo
        //redirecionaSaida();      // Redireciona saída para arquivo
        gerenciaEvento = new GerenciaEvento(); // Inicializa gerenciador
    }

    // Método principal da aplicação
    public void executar() {
        int opcao;
        do {
            System.out.println("=== Sistema de Gerenciamento de Eventos ===");
            System.out.println("1 - Consultar detalhes de evento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!in.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número.");
                in.next(); // consome entrada inválida
                System.out.print("Escolha uma opção: ");
            }

            opcao = in.nextInt();
            in.nextLine(); // consome quebra de linha

            switch (opcao) {
                case 1:
                    gerenciaEvento.consultarEvento(); // ✅ chamada corrigida
                    break;
                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("----------------------------------");
        } while (opcao != 0);
    }

    // Redireciona entrada para arquivo
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            in = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        in.useLocale(Locale.ENGLISH);
    }

    // Redireciona saída para arquivo
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }
}