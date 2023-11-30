package Dados.Eventos;

public class Ciclone extends Evento{
    private double velocidade;
    private double precipitacao;

    public Ciclone(String codigo, String data, double latitude, double longitude, double velocidade, double precipitacao) {
        super(codigo, data, latitude, longitude);
        this.velocidade = velocidade;
        this.precipitacao = precipitacao;
    }

    public String toString() {
        return "Ciclone\nCódigo: " + super.getCodigo() + "\nData: " + super.getData() + "\nLatitude: " + super.getLatitude() + "\nLongitude: " + super.getLongitude() + "\nVelocidade: " + velocidade + "km/h" + "\nPrecipitação: " + precipitacao + "mm\n";
    }

    public double getVelocidade() {
        return velocidade;
    }
}
