package Dados;

import java.time.LocalDate;
import java.util.Scanner;

import Gerenciamento.GerenciaIngresso;

public class Evento {
    private String nome;
    private String descricao;
    private int valor;
    private int qntIngresso;
    private String idEvento;
    private LocalDate data;
    private GerenciaIngresso gerenciaIngresso;
    private int qntIngressosComuns;
    private int qntIngressosEspeciais;


    public Evento(String nome, String descricao, int valor, int qntIngresso, String idEvento, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qntIngresso = qntIngresso;
        this.idEvento = idEvento;
        this.data = data;
        gerenciaIngresso = new GerenciaIngresso();
        this.qntIngressosComuns = (int) Math.floor(qntIngresso * 0.75);
        this.qntIngressosEspeciais = (int) Math.floor(qntIngresso * 0.25);
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

    public void emitirIngresso() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do participante:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do participante:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a idade do participante:");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.println("O ingresso é especial? (s/n):");
        String especialInput = scanner.nextLine();
        boolean especial = especialInput.equalsIgnoreCase("s");

        boolean cpfJaUtilizado = false;

        for (Ingresso ingressoComum : gerenciaIngresso.getIngressosComuns()) {
            if (ingressoComum.getParticipante().getCpf().equals(cpf)) {
                cpfJaUtilizado = true;
                break;
            }
        }

        for (Ingresso ingressoEspecial : gerenciaIngresso.getIngressosEspeciais()) {
            if (ingressoEspecial.getParticipante().getCpf().equals(cpf)) {
                cpfJaUtilizado = true;
                break;
            }
        }

        if (cpfJaUtilizado){
            throw new Exception("CPF já utilizado em ingressos para este evento!");
        }

        Participante participante = new Participante(null, nome, cpf, idade);

        if (especial){
            if (gerenciaIngresso.getIngressosEspeciais().size() < qntIngressosEspeciais){
                Ingresso ingresso = new Ingresso(this, true, gerenciaIngresso.getIngressosEspeciais().size() + 1, participante);
                gerenciaIngresso.getIngressosEspeciais().add(ingresso);
                participante.setIngresso(ingresso);
                System.out.println("Ingresso especial emitido com sucesso! Código: " + ingresso.getCodigo());
            } else {
                throw new Exception("Não há mais ingressos especiais disponíveis para este evento.");
            }
        } else {
            if (gerenciaIngresso.getIngressosComuns().size() < qntIngressosComuns){
                Ingresso ingresso = new Ingresso(this, false, gerenciaIngresso.getIngressosComuns().size() + 1, participante);
                gerenciaIngresso.getIngressosComuns().add(ingresso);
                participante.setIngresso(ingresso);
                System.out.println("Ingresso comum emitido com sucesso! Código: " + ingresso.getCodigo());
            } else {
                throw new Exception("Não há mais ingressos comuns disponíveis para este evento.");
            }
        }
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome=\'" + nome + '\'' +
                ", descricao=\'" + descricao + '\'' +
                ", valor=" + valor +
                ", qntIngresso=" + qntIngresso +
                ", idEvento=" + idEvento +
                '}' + '\n';
    }
}
