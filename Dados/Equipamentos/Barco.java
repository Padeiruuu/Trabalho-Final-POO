package Dados.Equipamentos;

public class Barco extends Equipamento{
    private int capacidade;

    public Barco(int id, String nome, double custoDia, String equipe, int capacidade) {
        super(id, nome, custoDia, equipe);
        this.capacidade = capacidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String toString() {
        return "Barco\nID: " + super.getId() + "\nNome: " + super.getNome() + "\nCusto por dia: R$" + super.getCustoDia() + "\nCapacidade: " + capacidade + "\n";
    }
}