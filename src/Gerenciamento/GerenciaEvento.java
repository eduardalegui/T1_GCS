package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public boolean cancelarEvento(String nome){
        Evento eventoRemover = procurarEventoEspecifico(nome);
        
        if(eventoRemover == null){
            return false;
        } else{
            eventos.remove(eventoRemover);
        }
    }
}