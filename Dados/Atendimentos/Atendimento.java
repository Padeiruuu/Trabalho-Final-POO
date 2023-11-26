package Dados.Atendimentos;
import Dados.Eventos.Evento;

public class Atendimento {
    private int cod;
    private String dataInicio;
    private int duracao;
    private String status;
    private Evento evento;

    public Atendimento(int cod, String dataInicio, int duracao, Evento evento) {
        this.cod = cod;
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.status = "PENDENTE";
        this.evento = evento;
    }

    public double calculaCusto(){
        return 0;
    }

    public String toString() {
        return "Código: " + cod + "\nData de Início: " + dataInicio + "\nDuração: " + duracao + "\nEvento: " + evento.getCodigo() + "\nStatus: " + status;
    }

    public int getCod() {
        return cod;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
