package Dados.Atendimentos;
import Dados.Equipes.Equipe;
import Dados.Eventos.Evento;

public class Atendimento {
    private int cod;
    private String dataInicio;
    private int duracao;
    private String status;
    private Evento evento;
    private Equipe equipe;

    public Atendimento(int cod, String dataInicio, int duracao, Evento evento, Equipe equipe) {
        this.cod = cod;
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.status = "PENDENTE";
        this.evento = evento;
        this.equipe = equipe;
    }

    public double calculaCusto(){
        double custoEquipe = duracao * 250 * equipe.getQuantidadeMembros();
        double custoEquipamentos = duracao * equipe.getCustoDia();
        double custoDeslocamento = CadastraAtendimento.getDistancia(this,equipe) * (100 * equipe.getQuantidadeMembros() + 0.10 * equipe.getCustoDia());
        return custoEquipe + custoEquipamentos + custoDeslocamento;
    }

    public String toString() {
        return "Código: " + cod + "\nData de Início: " + dataInicio + "\nDuração: " + duracao + " dias\nEvento: " + evento.getCodigo() + "\nStatus: " + status + "\n";
    }

    public int getCod() {
        return cod;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getStatus() {
        return status;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
