package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public void procurarEvento(String nome) {
        boolean encontrado = false;

        for (Evento e : eventos) {
            if (e.getNome().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println("Evento encontrado:");
                System.out.println("ID: " + e.getIdEvento());
                System.out.println("Nome: " + e.getNome());
                System.out.println("Descrição: " + e.getDescricao());
                System.out.println("Data: " + e.getData());
                System.out.println("Valor: " + e.getValor());
                System.out.println("Quantidade de ingressos: " + e.getQntIngresso());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum evento encontrado com o nome informado.");
        }
    }
}