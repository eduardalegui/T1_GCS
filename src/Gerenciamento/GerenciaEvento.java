package Gerenciamento;

import Dados.Evento;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class GerenciaEvento {
    private ArrayList<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
    }

    //#8 feature adicional - Cancelar evento com pelo menos 15 dias de antecedência.
    public boolean cancelarEvento(String nome) {
        //Chamar o método listarEventos antes de chamar esse.

        //O método que é chamado abaixo vai ser implementado na branch da issue #3 (Procurar evento específico).
        Evento eventoRemover = encontrarEvento(nome);

        if (eventoRemover == null) {
            return false;
        } else {
            LocalDate hoje = LocalDate.now();
            LocalDate dataEvento = eventoRemover.getData();

            if (dataEvento.isAfter(hoje.plusDays(14))) {
                eventos.remove(eventoRemover);
                //Fazer no app (caso true): System.out.println("Evento cancelado com sucesso.");
                return true;
            } else {
                //Fazer no app (caso false): System.out.println("Não foi possível realizar o cancelamento. O evento está ocorrerá em menos de 15 dias.");
                return false;
            }
        }
    }
  
    public void buscarEventos(int mes, int ano) {
        ArrayList<Evento> eventosNoMes = new ArrayList<>();
        for (Evento evento : eventos) { 
            if (evento.getData().getMonthValue() == mes && evento.getData().getYear() == ano) { //verifica se o evento é no mes e ano desejado
                eventosNoMes.add(evento); //adiciona na lista de eventos encontrados
            }
        }
       if (eventosNoMes.isEmpty()) { //verifica se há eventos
            System.out.println("Nenhum evento encontrado neste mês e ano."); //se não houver eventos
        }
        System.out.println("Eventos em " + mes + "/" + ano + ":");
        for (Evento evento : eventosNoMes) { //imprime os eventos encontrados
            System.out.println("Nome: " + evento.getNome() +
            ", Data: " + evento.getData() +
            ", ID: " + evento.getIdEvento() +
            ", Descrição: " + evento.getDescricao() +
            ", Valor: " + evento.getValor() +
            ", Quantidade de Ingressos: " + evento.getQntIngresso() +
            "\n");
        }
    }

    public boolean cadastrarEvento(Evento evento){
        if (!validarParametros(evento)) { //checagem do parametro recebido conforme a especificação
            return false;
        }
        if (hojeOuAnterior(evento)) {
            return false;
        }

        eventos.add(evento); //adiciona no array
        atribuirCodigoUnico(evento);

        return true;
    }

    public void atribuirCodigoUnico(Evento evento){
        UUID id = UUID.randomUUID();  //uso de uuid para geração de um id automaticamente
        String uuidSemHifen = id.toString().replace("-","");
        String idCurto = uuidSemHifen.substring(uuidSemHifen.length()- 5); //guardar apenas os 5 ultimos caracteres por conveniencia
        evento.setIdEvento(idCurto);
    }

    public boolean hojeOuAnterior(Evento evento) {
        LocalDate dataEvento = evento.getData(); //uso de LocalDate para usar seus metodos na checagem
        LocalDate hoje = LocalDate.now(); 

        return dataEvento.isBefore(hoje) || dataEvento.isEqual(hoje); //retorna true se for no passado ou hoje
    }

    public boolean validarParametros(Evento evento){ //checa a existencia ou coerência dos paramentros recebidos
        if (evento.getNome() == null || evento.getDescricao() == null || evento.getQntIngresso() <= 0 || evento.getValor() < 0 || evento.getData() == null) {
            return false;
        }
        return true;//se estiver tudo certo
    }
}