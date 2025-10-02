package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;
import java.util.UUID;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public void cadastrarEvento(Evento evento){
        
    }

    public void atribuirCodigoUnico(Evento evento){
        UUID id = UUID.randomUUID();
        String uuidSemHifen = id.toString().replace("-","");
        String idCurto = uuidSemHifen.substring(uuidSemHifen.length()- 5);
        evento.setIdEvento(idCurto);
    }
}