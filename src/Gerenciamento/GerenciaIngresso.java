package Gerenciamento;
import Dados.Ingresso;
import java.util.ArrayList;
import Dados.Evento;
import Dados.Participante;
import java.util.List;
import java.util.Random;

public class GerenciaIngresso {
    private ArrayList<Ingresso> ingressosVendidos;
    private Random random;


    public GerenciaIngresso() {
        this.ingressosVendidos = new ArrayList<>();
        this.random = new Random();
    }

        public Ingresso emitirIngresso (Evento evento, Participante participante, boolean isEspecial){

            String codigo = gerarCodigoIngresso(evento);
            int idIngresso = gerarIdIngresso();

            Ingresso novoIngresso = new Ingresso(evento, isEspecial, codigo, idIngresso, participante);
            this.ingressosVendidos.add(novoIngresso);

            if (participante.getIngresso() == null) {
                participante.setIngresso(novoIngresso);
            }

            System.out.println("SUCESSO - Ingresso " + codigo + " emitido para " + participante.getNome());
            return novoIngresso;
        }
        
