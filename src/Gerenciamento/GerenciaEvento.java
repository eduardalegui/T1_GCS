package Gerenciamento;

import Dados.Evento;

import java.time.LocalDate;
import java.util.ArrayList;

public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
    }

    //#8 feature adicional - Cancelar evento com pelo menos 15 dias de antecedência.
    public boolean cancelarEvento(String nome) {
        //chamar o método listarEventos antes de chamar esse.

        //o método que é chamado abaixo vai ser implementado na branch da issue #3 (Procurar evento específico)
        Evento eventoRemover = encontrarEvento(nome);

        if (eventoRemover == null) {
            return false;
        } else {
            LocalDate hoje = LocalDate.now();
            LocalDate dataEvento = eventoRemover.getData();

            if (dataEvento.isAfter(hoje.plusDays(14))) {
                eventos.remove(eventoRemover);
                //fazer no app (caso true): System.out.println("Evento cancelado com sucesso.");
                return true;
            } else {
                //fazer no app (caso false): System.out.println("Não foi possível realizar o cancelamento. O evento está ocorrerá em menos de 15 dias.");
                return false;
            }
        }
    }
}