package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;
    private int proximoIdEvento = 1;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public Evento adicionarEvento(String nome, String descricao, int valor, int qntIngresso, LocalDate data) {
        String idGerado = "EVE" + String.format("%03d", proximoIdEvento++);
        Evento novoEvento = new Evento(nome, descricao, valor, qntIngresso, idGerado, data);
        eventos.add(novoEvento);
        return novoEvento;
    }

    public Optional<Evento> buscarEventoPorId(String idEvento) {
        for (Evento evento : eventos) {
            if (evento.getIdEvento().equals(idEvento)) {
                return Optional.of(evento);
            }
        }
        return Optional.empty();
    }

    public void consultarDetalhes(String nome) {
        Evento e = encontrarEvento(nome);
        int totalVendidos = e.getGerenciaIngresso().getTotalIngressosVendidos();
        int vendidosNormais = e.getGerenciaIngresso().getIngressosVendidosNormais();
        int vendidosEspeciais = e.getGerenciaIngresso().getIngressosVendidosEspeciais();

        System.out.println("\n==============================================");
        System.out.println("CONSULTA DE DETALHES DO EVENTO: " + e.getNome());
        System.out.println("==============================================");

        System.out.println("C) INGRESSOS VENDIDOS POR TIPO E PERCENTUAIS (sobre o total vendido):");
        System.out.println("   Total de Ingressos Vendidos: " + totalVendidos);

        if (totalVendidos > 0) {
            double percNormais = (double) vendidosNormais / totalVendidos * 100;
            double percEspeciais = (double) vendidosEspeciais / totalVendidos * 100;

            System.out.printf("   - Ingressos Normais Vendidos: %d (%.2f%%)\n", vendidosNormais, percNormais);
            System.out.printf("   - Ingressos Especiais Vendidos: %d (%.2f%%)\n", vendidosEspeciais, percEspeciais);
            System.out.printf("   - Ingressos Normais Vendidos: %d (%.2f%%)\n", vendidosNormais);
            System.out.printf("   - Ingressos Especiais Vendidos: %d (%.2f%%)\n", vendidosEspeciais);
        } else {
            System.out.println("   Nenhum ingresso foi vendido ainda.");
        }

        System.out.println("\nD) PERCENTUAL DE OCUPAÇÃO TOTAL (Vendidos vs Lotação Máxima):");
        double percOcupacao = 0.0;
        if (e.getQntIngresso() > 0) {
            percOcupacao = (double) totalVendidos / e.getQntIngresso() * 100;
        }
        System.out.printf("   Ocupação: %d ingressos vendidos / %d lotação (%.2f%%)\n", totalVendidos, e.getQntIngresso(), percOcupacao);

        System.out.println("==============================================");
    }
}
