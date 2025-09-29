package Dados;

public class Participante {
    private Ingresso ingresso;
    private String nome;
    private String cpf;
    private int idade;
    
}

//package Dados;

public class Participante {
    private Ingresso ingresso;
    private String nome;
    private String cpf;
    private int idade;
    
    public Participante(Ingresso ingresso, String nome, String cpf, int idade) {
        this.ingresso = ingresso;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Participante{" +
               "ingressoId=" + (ingresso != null ? ingresso.getIdIngresso() : "N/A") +
               ", nome=\'" + nome + "\'" +
               ", cpf=\'" + cpf + "\'" +
               ", idade=" + idade +
               "}";
    }
}
