package Dados.Equipes;

public class Equipe {
    private String codinome;
    private int quantidadeMembros;
    private double latitude;
    private double longitude;

    public Equipe(String codinome, int quantidadeMembros, double latitude, double longitude) {
        this.codinome = codinome;
        this.quantidadeMembros = quantidadeMembros;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return "Codinome: " + codinome + "\nQuantidade de Membros: " + quantidadeMembros + "\nLatitude: " + latitude + "\nLongitude: " + longitude;
    }

    public String getCodinome() {
        return codinome;
    }

    public int getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
