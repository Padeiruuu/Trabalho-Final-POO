package Dados.Equipamentos;

public class Equipamento{
    private int id;
    private String nome;
    private double custoDia;
    private String equipe;

    public Equipamento(int id, String nome, double custoDia, String equipe) {
        this.id = id;
        this.nome = nome;
        this.custoDia = custoDia;
        this.equipe = equipe;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getCustoDia() {
        return custoDia;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String codinome) {
        this.equipe = codinome;
    }
}
