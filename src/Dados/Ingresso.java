package Dados;

public class Ingresso {
    private Evento evento;
    private boolean especial;
    private String codigo; // Ex: "121-001" ou "121-076E" precisa ser sequencial e ter um codigo do evento, além de um sufixo E para especial
    private int sequencial;
    private Participante participante;
    private boolean presente;

    public Ingresso(Evento evento, boolean especial, int sequencial, Participante participante) {
        this.evento = evento;
        this.especial = especial;
        this.sequencial = sequencial;
        this.participante = participante;
        this.codigo = gerarCodiogo();
        this.presente = false;
    }

    //apenas getters necessarios e sem setters para manter a integridade do ingresso
    public Evento getEvento() {
        return evento;
    }

    public boolean isEspecial() {
        return especial;
    }

    public String getCodigo() {
        return codigo;
    }

    public Participante getParticipante(){
        return participante;
    }

    public boolean isPresente() {
        return presente;
    }

    public void marcarPresenca() {
        this.presente = true;
    }

    public String gerarCodiogo(){
        return especial ? String.format("%s-%dE", evento.getIdEvento(), sequencial) : String.format("%s-%d", evento.getIdEvento(), sequencial);
    }

}
