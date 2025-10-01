package Aplicacao;
// Importações;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class App {
    // Atributos para redirecionamento de I/O;
    private Scanner in = new Scanner(System.in);  // Atributo para entrada padrão (teclado);
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados;
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saída de dados;

    public App() {
        redirecionaEntrada();    // Redireciona Entrada para arquivos;
        redirecionaSaida();    // Redireciona Saída para arquivos;
    }

    // Responsável pela utilização e ordenação da aplicação correta dos métodos;
    // Chame esse método para executar o código na Main.java;
    public void executar() {

    }

    // Redireciona Entrada de dados para arquivos em vez de teclado;
    // Chame este método para redirecionar a leitura de dados para arquivos;
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            in = new Scanner(streamEntrada);   // Usa como entrada um arquivo;
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal;
        in.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal;
    }

    // Redireciona Saída de dados para arquivos em vez da tela (terminal);
    // Chame este método para redirecionar a escrita de dados para arquivos;
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saída um arquivo;
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal;
    }


}


/* // ideia de app 
package Aplicacao;

import Dados.Evento;
import Dados.Ingresso;
import Dados.Participante;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    private Scanner in = new Scanner(System.in);  // Usar entrada padrão (teclado) diretamente;
    private EventManager eventManager;

    public App() {
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal;
        in.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal;
        eventManager = new EventManager();
        eventManager.preencherDadosIniciais(); // Preenche com dados de exemplo
    }

    public void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    emitirIngresso();
                    break;
                case 4:
                    gerarRelatorioGeral(); // Relatório geral adaptado
                    break;
                case 0:
                    System.out.println("Saindo da aplicação. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("\n----------------------------------------\n");
        } while (opcao != 0);
        in.close();
    }

    private void exibirMenu() {
        System.out.println("===== Sistema de Gerenciamento de Eventos (Básico) =====");
        System.out.println("1. Cadastrar Novo Evento");
        System.out.println("2. Listar Todos os Eventos");
        System.out.println("3. Emitir Ingresso");
        System.out.println("4. Gerar Relatório Geral");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        while (!in.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            in.next(); // Consome a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int opcao = in.nextInt();
        in.nextLine(); // Consome a quebra de linha
        return opcao;
    }

    private void cadastrarEvento() {
        System.out.println("\n--- Cadastrar Novo Evento ---");
        System.out.print("Nome do Evento: ");
        String nome = in.nextLine();
        System.out.print("Descrição do Evento: ");
        String descricao = in.nextLine();
        System.out.print("Valor do Ingresso: ");
        int valorIngresso = lerInt();
        System.out.print("Quantidade de Vagas: ");
        int qntVagas = lerInt();

        try {
            Evento novoEvento = eventManager.cadastrarEvento(nome, descricao, valorIngresso, qntVagas);
            System.out.println("Evento cadastrado com sucesso!");
            System.out.println("ID do Evento: " + novoEvento.getIdEvento());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar evento: " + e.getMessage());
        }
    }

    private void listarEventos() {
        System.out.println("\n--- Eventos Cadastrados ---");
        List<Evento> eventos = eventManager.listarEventos();
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }

    private void emitirIngresso() {
        System.out.println("\n--- Emitir Ingresso ---");
        System.out.print("ID do Evento: ");
        int idEvento = lerInt();
        System.out.print("Ingresso Especial (true/false): ");
        boolean especial = in.nextBoolean();
        in.nextLine(); // Consome a quebra de linha
        System.out.print("Nome do Participante: ");
        String nomeParticipante = in.nextLine();
        System.out.print("CPF do Participante: ");
        String cpfParticipante = in.nextLine();
        System.out.print("Idade do Participante: ");
        int idadeParticipante = lerInt();

        try {
            Ingresso ingresso = eventManager.emitirIngresso(idEvento, especial, nomeParticipante, cpfParticipante, idadeParticipante);
            System.out.println("Ingresso emitido com sucesso!");
            System.out.println("Código do Ingresso: " + ingresso.getCodigo());
            System.out.println("Para: " + nomeParticipante);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao emitir ingresso: " + e.getMessage());
        }
    }

    private void gerarRelatorioGeral() {
        System.out.println("\n--- Gerar Relatório Geral ---");
        List<String> relatorio = eventManager.gerarRelatorioGeral();
        for (String item : relatorio) {
            System.out.println(item);
        }
    }

    private int lerInt() {
        while (!in.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            in.next(); // Consome a entrada inválida
            System.out.print("Digite novamente: ");
        }
        int valor = in.nextInt();
        in.nextLine(); // Consome a quebra de linha
        return valor;
    } 
}*/
