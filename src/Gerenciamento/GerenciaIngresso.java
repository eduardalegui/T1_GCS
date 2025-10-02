package Gerenciamento;
import Dados.Ingresso;
import java.util.ArrayList;
import java.util.List;

public class GerenciaIngresso {
    private ArrayList<Ingresso> ingressosComuns;
    private ArrayList<Ingresso> ingressosEspeciais;

    public GerenciaIngresso() {
        this.ingressosComuns = new ArrayList<Ingresso>();
        this.ingressosEspeciais = new ArrayList<Ingresso>();
    }

    public List<Ingresso> getIngressosComuns() {
        return ingressosComuns;
    }
    public List<Ingresso> getIngressosEspeciais() {
        return ingressosEspeciais;
    }
}
