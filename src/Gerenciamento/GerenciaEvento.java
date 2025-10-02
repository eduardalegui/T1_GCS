package Gerenciamento;
import Dados.Evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public boolean cadastrarEvento(Evento evento){
        if (!validarParametros(evento)) {
            return false;
        }
        if (hojeOuAnterior(evento)) {
            return false;
        }
    }

    public void atribuirCodigoUnico(Evento evento){
        UUID id = UUID.randomUUID();
        String uuidSemHifen = id.toString().replace("-","");
        String idCurto = uuidSemHifen.substring(uuidSemHifen.length()- 5);
        evento.setIdEvento(idCurto);
    }

    public boolean hojeOuAnterior(Evento evento) {
        LocalDate dataEvento = evento.getData();
        LocalDate hoje = LocalDate.now();

        return dataEvento.isBefore(hoje) || dataEvento.isEqual(hoje);
    }

    public boolean validarParametros(Evento evento){
        if (evento.getNome() == null || evento.getDescricao() == null || evento.getQntIngresso() <= 0 || evento.getValor() < 0 || evento.getData() == null) {
            return false;
        }
        return true;
    }
}