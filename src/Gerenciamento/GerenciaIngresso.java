package Gerenciamento;
import Dados.Ingresso;
import Dados.Participante;
import java.util.ArrayList;
import Dados.Evento;
import Dados.Participante;
import java.util.List;
import java.util.Random;

public class GerenciaIngresso {
    private ArrayList<Ingresso> gerenciaIngresso; 
    private ArrayList<Participante> participantesComIngresso; // Criação do ArrayList de participantes com ingresso
    private ArrayList<Participante> participantesPresentes;  // Criação do ArrayList de participantes presentes

    public GerenciaIngresso (){
        gerenciaIngresso = new ArrayList<>(); 
        participantesComIngresso = new ArrayList<>(); // Inicialização do ArrayList de participantes com ingresso
        participantesPresentes = new ArrayList<>(); // Inicialização do ArrayList de participantes presentes
    }
  
    public int getTotalIngressosVendidos(){
        return gerenciaIngresso.size();
    }

    public int getIngressosVendidosNormais(){
        return ingressosComuns.size();
    }

    public int getIngressosVendidosEspeciais(){
        return ingressosEspeciais.size();
    }

    
    public void registrarEntrada(Participante p) {
        adicionaParticipantesComIngresso(); // Chama o método para preencher o ArrayList de participantes com ingresso
        if(p.getIngresso() != null && participantesComIngresso.contains(p)) { // Verifica se o ingresso não é vazio e se ele contém o participante passado por parâmetro 
            for(int i = 0; i < participantesPresentes.size(); i++) { // For para percorrer o ArrayList de participantes presentes
                if(participantesPresentes.get(i).getIngresso().getIdIngresso() == p.getIngresso().getIdIngresso()) { // Verifica se o participante já apresenteu esse ingresso anteriormente
                    System.out.println("Esse ingresso não é mais válido"); // Mensagem para dizer que a pessoa já mostrou o ingresso
                    return; // Sai do método
                }
            }
            System.out.println("Entrada registrada"); // Mensagem padrão para quando a pessoa é registrada no evento
            participantesPresentes.add(p); // Adiciona o participante no ArrayList de participantes presentes
        }
    }

    public ArrayList<Participante> getAusentes() { // Método que retorna o ArrayList de participantes ausentes
        adicionaParticipantesComIngresso(); // Chama o método para preencher o ArrayList de participantes com ingresso
        ArrayList<Participante> ausentes = new ArrayList<>(); // Cria e inicializa o ArrayList de participantes ausentes
        boolean ausente = true; // Variável booleana para verificar se o participante 
        for(int i = 0; i < participantesComIngresso.size(); i++) { // Percorre o ArrayList de participantes com ingresso
            for(int j = 0; j < participantesPresentes.size(); j++) { // Percorre o ArrayList de participantes presentes
                if(participantesComIngresso.get(i).getCpf() == participantesPresentes.get(j).getCpf()) { // Verifica se o participante está na lista de presentes
                    ausente = false; // O participante está na lista de presentes
                    break; // Sai do for atual e volta para o anterior
                }
            }
            if(ausente) { // Verifica se participante é ausente
                ausentes.add(participantesComIngresso.get(i)); // Adiciona o participante na lista de ausentes
            }
            ausente = true; // Reinicializa a variável booleana
        }
        return ausentes; // Retorna o ArrayList de ausentes
    }

    public void adicionaParticipantesComIngresso(){ // Método para adicionar todos os participantes na lista de participantes com ingresso
        if(gerenciaIngresso.size() != participantesComIngresso.size()){ // Verifica se o tamanho dos dois Arrays é diferente
            for(int i = 0; i<gerenciaIngresso.size(); i++) { // Percorre o ArrayList de Ingressos
                Participante p = gerenciaIngresso.get(i).getParticipante(); // Pega o participante da lista de ingresso
                participantesComIngresso.add(p); // Adiciona o participante a lista de participantes com ingresso
            }
        }
    }
    
}

