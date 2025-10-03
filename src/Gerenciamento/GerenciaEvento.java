package Gerenciamento;
import Dados.Evento;
import Dados.Ingresso;
import Dados.Participante;
import java.util.ArrayList;
import java.util.Scanner;


public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento(){
        eventos = new ArrayList<>();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    public Evento buscarEventoPorId(String idEvento) {
        for (Evento evento : eventos) {
            if (evento.getIdEvento().equals(idEvento)) {
                return evento;
            }
        }
        return null;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void emitirIngresso(String idEvento) throws Exception {
        Evento evento = buscarEventoPorId(idEvento);
        if (evento == null) {
            throw new Exception("Evento não encontrado!");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do participante:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do participante:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a idade do participante:");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.println("O ingresso é especial? (s/n):");
        String especialInput = scanner.nextLine();
        boolean especial = especialInput.equalsIgnoreCase("s");

        boolean cpfJaUtilizado = false;

        for (Ingresso ingressoComum : evento.getGerenciaIngresso().getIngressosComuns()) {
            if (ingressoComum.getParticipante().getCpf().equals(cpf)) {
                cpfJaUtilizado = true;
                break;
            }
        }

        for (Ingresso ingressoEspecial : evento.getGerenciaIngresso().getIngressosEspeciais()) {
            if (ingressoEspecial.getParticipante().getCpf().equals(cpf)) {
                cpfJaUtilizado = true;
                break;
            }
        }

        if (cpfJaUtilizado){
            throw new Exception("CPF já utilizado em ingressos para este evento!");
        }

        Participante participante = new Participante(null, nome, cpf, idade);

        if (especial){
            if (evento.getGerenciaIngresso().getIngressosEspeciais().size() < evento.getQntIngressosEspeciais()){
                Ingresso ingresso = new Ingresso(evento, true, evento.getGerenciaIngresso().getIngressosEspeciais().size() + 1, participante);
                evento.getGerenciaIngresso().getIngressosEspeciais().add(ingresso);
                participante.setIngresso(ingresso);
                System.out.println("Ingresso especial emitido com sucesso! Código: " + ingresso.getCodigo());
            } else {
                throw new Exception("Não há mais ingressos especiais disponíveis para este evento.");
            }
        } else {
            if (evento.getGerenciaIngresso().getIngressosComuns().size() < evento.getQntIngressosComuns()){
                Ingresso ingresso = new Ingresso(evento, false, evento.getGerenciaIngresso().getIngressosComuns().size() + 1, participante);
                evento.getGerenciaIngresso().getIngressosComuns().add(ingresso);
                participante.setIngresso(ingresso);
                System.out.println("Ingresso comum emitido com sucesso! Código: " + ingresso.getCodigo());
            } else {
                throw new Exception("Não há mais ingressos comuns disponíveis para este evento.");
            }
        }
    }
}