package Dados;

import java.time.LocalDate;

import Gerenciamento.GerenciaIngresso;

public class  Evento {
    private String nome;
    private String descricao;
    private int valor;
    private int qntIngresso;
    private String idEvento;
    private LocalDate data;
    private GerenciaIngresso gerenciaIngresso;

    public Evento(String nome, String descricao, int valor, int qntIngresso, String idEvento, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qntIngresso = qntIngresso;
        this.idEvento = idEvento;
        this.data = data;
        gerenciaIngresso = new GerenciaIngresso();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }

    public int getQntIngresso() {
        return qntIngresso;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public LocalDate getData(){
        return data;
    }

    public GerenciaIngresso getGerenciaIngresso() { return gerenciaIngresso; }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setQntIngresso(int qntIngresso) {
        this.qntIngresso = qntIngresso;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public Ingresso emitirNovoIngresso(Participante participante, boolean isEspecial) {
        System.out.println("F) Chamando método emitir ingressos...");

        // Verifica se a lotação máxima foi atingida
        if (this.gerenciaIngresso.getTotalIngressosVendidos() >= this.qntIngresso) {
            System.out.println("   [ERRO] Lotação máxima do evento atingida. Não é possível emitir mais ingressos.");
            return null;
        }

        // Delega a criação do ingresso à GerenciaIngresso
        return this.gerenciaIngresso.emitirIngresso(this, participante, isEspecial);
    }

    public void consultarDetalhes() {
        int totalVendidos = gerenciaIngresso.getTotalIngressosVendidos();
        int vendidosNormais = gerenciaIngresso.getIngressosVendidosNormais();
        int vendidosEspeciais = gerenciaIngresso.getIngressosVendidosEspeciais();

        System.out.println("\n==============================================");
        System.out.println("CONSULTA DE DETALHES DO EVENTO: " + this.nome);
        System.out.println("==============================================");

        System.out.println("C) INGRESSOS VENDIDOS POR TIPO E PERCENTUAIS (sobre o total vendido):");
        System.out.println("   Total de Ingressos Vendidos: " + totalVendidos);

        if (totalVendidos > 0) {
            double percNormais = (double) vendidosNormais / totalVendidos * 100;
            double percEspeciais = (double) vendidosEspeciais / totalVendidos * 100;

            System.out.printf("   - Ingressos Normais Vendidos: %d (%.2f%%)\n", vendidosNormais, percNormais);
            System.out.printf("   - Ingressos Especiais Vendidos: %d (%.2f%%)\n", vendidosEspeciais, percEspeciais);
        } else {
            System.out.println("   Nenhum ingresso foi vendido ainda.");
        }

        System.out.println("\nD) PERCENTUAL DE OCUPAÇÃO TOTAL (Vendidos vs Lotação Máxima):");
        double percOcupacao = 0.0;
        if (this.qntIngresso > 0) {
            percOcupacao = (double) totalVendidos / this.qntIngresso * 100;
        }
        System.out.printf("   Ocupação: %d ingressos vendidos / %d lotação (%.2f%%)\n", totalVendidos, this.qntIngresso, percOcupacao);

        System.out.println("==============================================");
    }

        @Override
        public String toString () {
            return "Evento{" +
                    "nome=\'" + nome + '\'' +
                    ", descricao=\'" + descricao + '\'' +
                    ", valor=" + valor +
                    ", qntIngresso=" + qntIngresso +
                    ", idEvento=" + idEvento +
                    '}' + '\n';
        }
    }
}
