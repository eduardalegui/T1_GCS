package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GerenciaEvento {
    private List<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
    }

    public int calcularIngressoEspecial(Evento evento){ //15% dos ingressos são especiais
        int total = evento.getQntIngresso(); 
        int especial = (int) (total * 0.15); //casting para int, pois o resultado é double
        return especial;
    }

    public Evento consultarDetalhesEvento(String nome){
        Evento escolhido = encontrarEvento(nome); //o método "encontraEvento" foi feito por outra pessoa
        if (escolhido == null) { //se o evento não for encontrado
            System.out.println("Evento não encontrado.");
        }
        System.out.println("Nome: " + escolhido.getNome());
        System.out.println("Descrição: " + escolhido.getDescricao());
        System.out.println("Valor: " + escolhido.getValor());
        System.out.println("Numero de ingressos: " + (escolhido.getQntIngresso() - calcularIngressoEspecial(escolhido)));
        System.out.println("Numero de ingressos especiais: " + calcularIngressoEspecial(escolhido));
        System.out.println("Data: " + escolhido.getData());
        /*
        A opção para emissão de um ingresso fica disponível após o usuário consultar os detalhes,
        mas não ocorre automaticamente
        */
        return escolhido;
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
