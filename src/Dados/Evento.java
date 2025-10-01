package Dados;

import Gerenciamento.GerenciaIngresso;

public class Evento {
    private String nome;
    private String descricao;
    private int valor;
    private int qntIngresso;
    private int idEvento;
    private GerenciaIngresso gerenciaIngresso;

    public Evento(String nome, String descricao, int valor, int qntIngresso, int idEvento) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qntIngresso = qntIngresso;
        this.idEvento = idEvento;
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

    public int getIdEvento() {
        return idEvento;
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

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
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
