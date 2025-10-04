package Aplicacao;

import Gerenciamento.GerenciaEvento;
import Dados.Evento;
import Dados.Participante;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private Scanner in;  // Removido "final" para permitir redirecionamento
    private final GerenciaEvento gerenciaEvento;

    // Sem HashMap: lista simples e busca linear por CPF
    private final List<Participante> participantes;

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE; // "yyyy-MM-dd"

    public App() {
        Locale.setDefault(new Locale("pt", "BR"));
        redirecionaEntrada();  // agora de fato inicializa 'in'
        gerenciaEvento = new GerenciaEvento();
        participantes = new ArrayList<>();
    }

    public void executar() {
        int op;
        do {
            mostrarMenu();
            op = lerInt("Escolha uma opção: ");

            switch (op) {
                case 1 -> cadastrarEvento();
                case 2 -> gerenciaEvento.listarEventos();
                case 3 -> procurarEvento();
                case 4 -> consultarDetalhesEvento();
                case 5 -> emitirIngresso();
                case 6 -> registrarEntrada();
                case 7 -> listarAusentes();
                case 8 -> buscarEventosMesAno();
                case 9 -> gerenciaEvento.listarEventosOrdenadosPorValor();
                case 10 -> cancelarEvento();
                case 0 -> System.out.println("Programa encerrado");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            if (op != 0) pause();
        } while (op != 0);
    }

    // ===== MENU =====
    private void mostrarMenu() {
        System.out.println("\n================= MENU EVENTOS =================");
        System.out.println("1) Cadastrar evento");
        System.out.println("2) Listar eventos");
        System.out.println("3) Procurar evento por nome");
        System.out.println("4) Consultar detalhes do evento");
        System.out.println("5) Emitir ingresso (normal/especial)");
        System.out.println("6) Registrar entrada de participante");
        System.out.println("7) Listar ausentes do evento");
        System.out.println("8) Buscar eventos por mês/ano");
        System.out.println("9) Listar eventos ordenados por valor");
        System.out.println("10) Cancelar evento (≥ 15 dias de antecedência)");
        System.out.println("0) Sair");
        System.out.println("===============================================\n");
    }

    // ===== OPÇÕES =====
    private void cadastrarEvento() {
        System.out.println("\n===== CADASTRAR EVENTO =====");

        String nome = lerLinha("Nome do evento: ");
        String descricao = lerLinha("Descrição: ");

        int valor = lerInt("Valor do ingresso (inteiro, em R$): ");
        int qnt = lerInt("Quantidade total de ingressos: ");

        LocalDate data = lerData("Data (yyyy-MM-dd): ");

        Evento e = new Evento(nome, descricao, valor, qnt, null, data);

        boolean ok = gerenciaEvento.cadastrarEvento(e);
        if (ok) {
            System.out.println("SUCESSO - Evento cadastrado. ID gerado: " + e.getIdEvento());
        } else {
            System.out.println("FALHA - Parâmetros inválidos ou data hoje/anterior.");
        }
    }

    private void procurarEvento() {
        System.out.println("\n===== PROCURAR EVENTO =====");
        String nome = lerLinha("Digite um trecho do nome: ");
        gerenciaEvento.procurarEvento(nome);
    }

    private void consultarDetalhesEvento() {
        System.out.println("\n===== CONSULTAR DETALHES =====");
        String nome = lerLinha("Nome (ou parte): ");
        gerenciaEvento.consultarDetalhes(nome);
    }

    private void emitirIngresso() {
        System.out.println("\n===== EMITIR INGRESSO =====");
        String nomeEvento = lerLinha("Evento (nome ou parte): ");
        Evento e = gerenciaEvento.encontrarEvento(nomeEvento);

        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        String nome = lerLinha("Nome do participante: ");
        String cpf = lerLinha("CPF do participante (apenas números): ");
        int idade = lerInt("Idade do participante: ");

        boolean especial = lerOpcaoSimNao("Ingresso especial (15% do total) [s/n]? ");

        Participante p = buscarParticipantePorCpf(cpf);
        if (p == null) {
            p = new Participante(null, nome, cpf, idade);
            participantes.add(p);
        } else {
            p.setNome(nome);
            p.setIdade(idade);
        }

        e.getGerenciaIngresso().emitirIngresso(e, p, especial);
    }

    private void registrarEntrada() {
        System.out.println("\n===== REGISTRAR ENTRADA =====");
        String nomeEvento = lerLinha("Evento (nome ou parte): ");
        Evento e = gerenciaEvento.encontrarEvento(nomeEvento);

        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        String cpf = lerLinha("CPF do participante: ");
        Participante p = buscarParticipantePorCpf(cpf);

        if (p == null || p.getIngresso() == null) {
            System.out.println("Participante não encontrado ou sem ingresso emitido.");
            return;
        }

        e.getGerenciaIngresso().registrarEntrada(p);
    }

    private void listarAusentes() {
        System.out.println("\n===== LISTAR AUSENTES =====");
        String nomeEvento = lerLinha("Evento (nome ou parte): ");
        Evento e = gerenciaEvento.encontrarEvento(nomeEvento);

        if (e == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        var ausentes = e.getGerenciaIngresso().getAusentes();
        if (ausentes.isEmpty()) {
            System.out.println("Não há ausentes (ou não há ingressos emitidos).");
        } else {
            System.out.println("Ausentes:");
            ausentes.forEach(a ->
                System.out.println("- " + a.getNome() + " | CPF: " + a.getCpf())
            );
        }
    }

    private void buscarEventosMesAno() {
        System.out.println("\n===== BUSCAR EVENTOS POR MÊS/ANO =====");
        int mes = lerInt("Mês (1-12): ");
        int ano = lerInt("Ano (ex.: 2025): ");
        gerenciaEvento.buscarEventos(mes, ano);
    }

    private void cancelarEvento() {
        System.out.println("\n===== CANCELAR EVENTO =====");
        String nomeEvento = lerLinha("Evento (nome ou parte): ");
        boolean ok = gerenciaEvento.cancelarEvento(nomeEvento);
        if (ok) {
            System.out.println("Evento cancelado com sucesso (cumpriu a regra de 15 dias).");
        } else {
            System.out.println("Não foi possível cancelar: nome inexistente ou faltam menos de 15 dias.");
        }
    }

    // ===== UTILITÁRIOS =====
    private Participante buscarParticipantePorCpf(String cpf) {
        for (Participante p : participantes) {
            if (p.getCpf() != null && p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    private String lerLinha(String prompt) {
        System.out.print(prompt);
        String s = in.nextLine();
        while (s == null || s.trim().isEmpty()) {
            System.out.print(prompt);
            s = in.nextLine();
        }
        return s.trim();
    }

    private int lerInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String raw = in.nextLine();
                return Integer.parseInt(raw.trim());
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private LocalDate lerData(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = in.nextLine().trim();
            try {
                return LocalDate.parse(entrada, ISO);
            } catch (DateTimeParseException ex) {
                System.out.println("Data inválida. Use o formato exato yyyy-MM-dd (ex.: 2025-10-31).");
            }
        }
    }

    private boolean lerOpcaoSimNao(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("s") || s.equals("sim")) return true;
            if (s.equals("n") || s.equals("nao") || s.equals("não")) return false;
            System.out.println("Responda com 's' ou 'n'.");
        }
    }

    private void pause() {
        System.out.print("\nPressione ENTER para continuar...");
        if (in.hasNextLine()) in.nextLine();
    }

    // Redireciona entrada para arquivo (se existir)
    private void redirecionaEntrada() {
        try {
            File arquivo = new File("dadosin.txt");
            if (arquivo.exists()) {
                in = new Scanner(arquivo, Charset.forName("UTF-8"));
                System.out.println("Entrada redirecionada de dadosin.txt");
            } else {
                in = new Scanner(System.in);
                System.out.println("Arquivo dadosin.txt não encontrado. Usando entrada padrão.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao redirecionar entrada: " + e.getMessage());
            in = new Scanner(System.in);
        }

        Locale.setDefault(new Locale("pt", "BR"));
        in.useLocale(Locale.getDefault());
    }
}
