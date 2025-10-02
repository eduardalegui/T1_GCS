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