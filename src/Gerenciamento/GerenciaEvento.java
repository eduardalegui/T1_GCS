package Gerenciamento;
import Dados.Evento;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GerenciaEvento {
    private List<Evento> eventos;

    public GerenciaEvento() {
        eventos = new ArrayList<>();
        eventos.add(new Evento(
            "Show do Coldplay",
            "Concerto internacional",
            250,
            1000,
            "EVT001",
            LocalDate.of(2025, 11, 10),
            "Estádio Beira-Rio"
        ));
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
