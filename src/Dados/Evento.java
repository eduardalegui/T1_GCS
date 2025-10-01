package Dados;

public class Evento {
    private String nome;
    private String descricao;
    private int valor;
    private int qntIngresso;
    private int idEvento;

    public Evento(String nome, String descricao, int valor, int qntVagas, int idEvento) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qntVagas = qntVagas;
        this.idEvento = idEvento;
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

    public int getQntVagas() {
        return qntVagas;
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

    public void setQntVagas(int qntVagas) {
        this.qntVagas = qntVagas;
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
               ", qntVagas=" + qntVagas +
               ", idEvento=" + idEvento +
               '}' + '\n';
    }
}
