package Dados.Equipamentos;

public class Barco extends Equipamento{
    private int capacidade;

    public Barco(int id, String nome, double custoDia, int capacidade) {
        super(id, nome, custoDia);
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String toString() {
        return "Barco\nID: " + super.getId() + "\nNome: " + super.getNome() + "\nCusto por dia: " + super.getCustoDia() + "\nCapacidade: " + capacidade + "\n";
    }
}