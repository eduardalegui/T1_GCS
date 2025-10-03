package Gerenciamento;

import Dados.Evento;
import java.util.ArrayList;

public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
    }

    // Método para procurar um evento pelo nome e retornar todos atributos dele

    public void procurarEvento(String nome) {
        Evento e = encontrarEvento(nome);
        if (e != null) {
            System.out.println("Evento encontrado:");
            System.out.println("ID: " + e.getIdEvento());
            System.out.println("Nome: " + e.getNome());
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("Data: " + e.getData());
            System.out.println("Valor: " + e.getValor());
            System.out.println("Quantidade de ingressos: " + e.getQntIngresso());
        } else {
            System.out.println("Nenhum evento encontrado com o nome informado.");
        }
    }

    // Método para encontrar o evento e retornar ele como objeto

    public Evento encontrarEvento(String nome) {
        for (Evento e : eventos) {
            if (e.getNome().toLowerCase().contains(nome.toLowerCase())) {
                return e;
            }
        }

        return null;
    }
}