package Dados;

public class Ingresso {
    private String codigo; // Ex: "121-001" ou "121-076E" precisa ser sequencial e ter um codigo do evento, além de um sufixo E para especial
    private int sequencial;
    private int tipo; // 1=normal, 2=especial
    private boolean presente; //true=presente, false=ausente
    private int codigoEvento; //Não necessita de uma instância de Evento, pois o codigo do evento é unico e pode ser usado para buscar o evento em uma lista

    public Ingresso(int sequencial, int tipo, int codigoEvento) {
        this.sequencial = sequencial; //para manter uma sequencia dentro de um arraylist pode ser passado como parametro (arraylist.size()+1)
        this.tipo = tipo;
        this.presente = false;
        this.codigoEvento = codigoEvento;
        this.codigo = gerarCodigo();
    }

    //apenas getters necessarios e sem setters para manter a integridade do ingresso
    public int getTipo() {
        return tipo;
    }

    public boolean isPresente() {
        return presente;
    }

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        String tipoStr = (tipo == 1) ? "Normal" : (tipo == 2) ? "Especial" : "Desconhecido";
        String presencaStr = presente ? "Presente" : "Ausente";
        return String.format(
                "===========================\n" +
            "Ingresso:\n" +
            "  Código: %s\n" +
            "  Sequencial: %d\n" +
            "  Tipo: %s\n" +
            "  Presença: %s\n" +
            "  Código do Evento: %d" +
                "===========================\n" +
            codigo, sequencial, tipoStr, presencaStr, codigoEvento
        );
    }

    private String gerarCodigo(){
        if(this.tipo == 1){
            return String.format("%d-%d", this.codigoEvento, this.sequencial);
        } else {
            return String.format("%d-%dE", this.codigoEvento, this.sequencial);
        }
    }

    public void marcarPresenca(){
        this.presente = true;
    };
}