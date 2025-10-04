package Gerenciamento;

import Dados.Evento;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import java.lang.reflect.Array;
import java.util.UUID;

public class GerenciaEvento {
    private List<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
    }

    public void consultarDetalhes(String nome) {
        Evento e = encontrarEvento(nome);
        int totalVendidos = e.getGerenciaIngresso().getTotalIngressosVendidos();
        int vendidosNormais = e.getGerenciaIngresso().getIngressosVendidosNormais();
        int vendidosEspeciais = e.getGerenciaIngresso().getIngressosVendidosEspeciais();

        System.out.println("\n==============================================");
        System.out.println("CONSULTA DE DETALHES DO EVENTO: " + e.getNome());
        System.out.println("==============================================");
      
        consultarDetalhesEvento(e);

        System.out.println("C) INGRESSOS VENDIDOS POR TIPO E PERCENTUAIS (sobre o total vendido):");
        System.out.println("   Total de Ingressos Vendidos: " + totalVendidos);

        if (totalVendidos > 0) {
            double percNormais = (double) vendidosNormais / totalVendidos * 100;
            double percEspeciais = (double) vendidosEspeciais / totalVendidos * 100;

            System.out.printf("   - Ingressos Normais Vendidos: %d (%.2f%%)\n", vendidosNormais, percNormais);
            System.out.printf("   - Ingressos Especiais Vendidos: %d (%.2f%%)\n", vendidosEspeciais, percEspeciais);
            System.out.printf("   - Ingressos Normais Vendidos: %d (%.2f%%)\n", vendidosNormais);
            System.out.printf("   - Ingressos Especiais Vendidos: %d (%.2f%%)\n", vendidosEspeciais);
        } else {
            System.out.println("   Nenhum ingresso foi vendido ainda.");
        }

        System.out.println("\nD) PERCENTUAL DE OCUPAÇÃO TOTAL (Vendidos vs Lotação Máxima):");
        double percOcupacao = 0.0;
        if (e.getQntIngresso() > 0) {
            percOcupacao = (double) totalVendidos / e.getQntIngresso() * 100;
        }
        System.out.printf("   Ocupação: %d ingressos vendidos / %d lotação (%.2f%%)\n", totalVendidos, e.getQntIngresso(), percOcupacao);

        System.out.println("==============================================");
    }
    public int calcularIngressoEspecial(Evento evento){ //15% dos ingressos são especiais
        int total = evento.getQntIngresso(); 
        int especial = (int) (total * 0.15); //casting para int, pois o resultado é double
        return especial;
    }

    public Evento consultarDetalhesEvento(Evento escolhido){
        if (escolhido == null) { //se o evento não for encontrado
            System.out.println("Evento não encontrado.");
        }
        System.out.println("Nome: " + escolhido.getNome());
        System.out.println("Descrição: " + escolhido.getDescricao());
        System.out.println("Valor: " + escolhido.getValor());
        System.out.println("Numero de ingressos: " + (escolhido.getQntIngresso() - calcularIngressoEspecial(escolhido)));
        System.out.println("Numero de ingressos especiais: " + calcularIngressoEspecial(escolhido));
        System.out.println("Data: " + escolhido.getData());
        /*
        A opção para emissão de um ingresso fica disponível após o usuário consultar os detalhes,
        mas não ocorre automaticamente
        */
        return escolhido;
    }

    public List<Evento> getEventos() {
        return eventos;
        }
    public boolean cadastrarEvento(Evento evento) {
        if (!validarParametros(evento)) {
            return false;
        }
        if (hojeOuAnterior(evento)) {
            return false;
        }

        eventos.add(evento);
        atribuirCodigoUnico(evento);
        return true;
    }

    public void atribuirCodigoUnico(Evento evento) {
        UUID id = UUID.randomUUID();
        String uuidSemHifen = id.toString().replace("-", "");
        String idCurto = uuidSemHifen.substring(uuidSemHifen.length() - 5);
        evento.setIdEvento(idCurto);
    }

    public boolean hojeOuAnterior(Evento evento) {
        LocalDate dataEvento = evento.getData();
        LocalDate hoje = LocalDate.now();
        return dataEvento.isBefore(hoje) || dataEvento.isEqual(hoje);
    }

    public boolean validarParametros(Evento evento) {
        if (evento.getNome() == null || evento.getDescricao() == null ||
            evento.getQntIngresso() <= 0 || evento.getValor() < 0 || evento.getData() == null) {
            return false;
        }
        return true;
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

    public void listarEventosOrdenadosPorValor() {
        eventos.sort((e1, e2) -> Integer.compare(e1.getValor(), e2.getValor()));

        System.out.println("Eventos ordenados por valor do ingresso:");
        for (Evento evento : eventos) {
            System.out.println("Nome: " + evento.getNome() + " | Valor: R$" + evento.getValor());
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
    // Método responsável por listar todos os eventos cadastrados no sistema
    public void listarEventos() {
        // Verifica se a lista de eventos está vazia
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado."); // Mensagem exibida caso não haja eventos
            return; // Sai do método, já que não tem nada para listar
        }

        // Caso existam eventos, imprime um cabeçalho
        System.out.println("=== Lista de eventos cadastrados ===");

        // Percorre todos os eventos dentro do ArrayList<Evento>
        for (Evento e : eventos) {
            // Para cada evento, exibe seus dados utilizando os getters da classe Evento
            System.out.println("ID: " + e.getIdEvento());          // Mostra o identificador único
            System.out.println("Nome: " + e.getNome());            // Mostra o nome do evento
            System.out.println("Descrição: " + e.getDescricao());  // Mostra a descrição
            System.out.println("Data: " + e.getData());            // Mostra a data do evento
            System.out.println("Valor: R$ " + e.getValor());       // Mostra o valor do ingresso
            System.out.println("Quantidade de ingressos: " + e.getQntIngresso()); // Mostra a quantidade de ingressos disponíveis
            System.out.println("-----------------------------------"); // Linha de separação para organização
        }
    }
}
