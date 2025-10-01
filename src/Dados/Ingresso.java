package Dados;

public class Ingresso {
    private Evento evento;
    private boolean especial = true;
    private String codigo;
    private int idIngresso;
    
    public Ingresso(Evento evento, boolean especial, String codigo, int idIngresso) {
        this.evento = evento;
        this.especial = especial;
        this.codigo = codigo;
        this.idIngresso = idIngresso;
    }
    public Evento getEvento() {
        return evento;
    }

    public boolean isEspecial() {
        return especial;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getIdIngresso() {
        return idIngresso;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
               "eventoId=" + (evento != null ? evento.getIdEvento() : "N/A") +
               ", especial=" + especial +
               ", codigo=\'" + codigo + "\'" +
               ", idIngresso=" + idIngresso +
               '\n' + "}";
    }
}
