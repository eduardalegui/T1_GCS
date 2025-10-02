package Gerenciamento;
import Dados.Ingresso;
import Dados.Participante;
import java.util.ArrayList;

public class GerenciaIngresso {
    private ArrayList<Ingresso> gerenciaIngresso;
    private ArrayList<Participante> participantesComIngresso;
    private ArrayList<Participante> participantesPresentes;  

    public GerenciaIngresso (){
        gerenciaIngresso = new ArrayList<>();
        participantesComIngresso = new ArrayList<>();
        participantesPresentes = new ArrayList<>();  
    }

    
    public void registrarEntrada(Participante p) {
        adicionaParticipantesComIngresso();
        if(p.getIngresso() != null && participantesComIngresso.contains(p)) {
            for(int i = 0; i < participantesPresentes.size(); i++) {
                if(participantesPresentes.get(i).getIngresso().getIdIngresso() == p.getIngresso().getIdIngresso()) {
                    System.out.println("Esse ingresso não é mais válido");
                    return;
                }
            }
            System.out.println("Entrada registrada");
            participantesPresentes.add(p); 
        }
    }

    public ArrayList<Participante> getAusentes() {
        adicionaParticipantesComIngresso();
        ArrayList<Participante> ausentes = new ArrayList<>(); 
        boolean ausente = true;
        for(int i = 0; i < participantesComIngresso.size(); i++) {
            for(int j = 0; j < participantesPresentes.size(); j++) {
                if(participantesComIngresso.get(i).getCpf() == participantesPresentes.get(j).getCpf()) {
                    ausente = false;
                    break;
                }
            }
            if(ausente) {
                ausentes.add(participantesComIngresso.get(i));
            }
            ausente = true;
        }
        return ausentes;
    }

    public void adicionaParticipantesComIngresso(){
        if(gerenciaIngresso.size() != participantesComIngresso.size()){
            for(int i = 0; i<gerenciaIngresso.size(); i++){
                Participante p = gerenciaIngresso.get(i).getParticipante();
                participantesComIngresso.add(p);
            }
        }
    }
    
}
