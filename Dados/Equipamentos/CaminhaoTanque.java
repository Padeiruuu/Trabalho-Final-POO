package Dados.Equipamentos;

public class CaminhaoTanque extends Equipamento{
    private double capacidade;

    public CaminhaoTanque(int id, String nome, double custoDia, double capacidade) {
        super(id, nome, custoDia);
        this.capacidade = capacidade;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public String toString() {
        return "Caminhão Tanque\nID: " + super.getId() + "\nNome: " + super.getNome() + "\nCusto por dia: " + super.getCustoDia() + "\nCapacidade: " + capacidade + "\n";
    }
}