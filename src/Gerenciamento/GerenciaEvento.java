package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;
    private int proximoIdEvento = 1;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public Evento adicionarEvento(String nome, String descricao, int valor, int qntIngresso, LocalDate data) {
        String idGerado = "EVE" + String.format("%03d", proximoIdEvento++);
        Evento novoEvento = new Evento(nome, descricao, valor, qntIngresso, idGerado, data);
        eventos.add(novoEvento);
        return novoEvento;
    }


}
