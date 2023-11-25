package Dados.Equipamentos;

public class Escavadeira extends Equipamento{
    private String combustivel;

    private double carga;

    public Escavadeira(int id, String nome, double custoDia, String combustivel, double carga) {
        super(id, nome, custoDia);
        this.combustivel = combustivel;
        this.carga = carga;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public double getCarga() {
        return carga;
    }

    public String toString() {
        return "Escavadeira\nID: " + super.getId() + "\nNome: " + super.getNome() + "\nCusto por dia: " + super.getCustoDia() + "\nCombustível: " + combustivel + "\nCarga: " + carga + "\n";
    }
}

