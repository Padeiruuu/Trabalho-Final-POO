package Dados.Eventos;

public class Terremoto extends Evento{
    private double magnitude;

    public Terremoto(String codigo, String data, double latitude, double longitude, double magnitude) {
        super(codigo, data, latitude, longitude);
        this.magnitude = magnitude;
    }

    public String toString() {
        return "Terremoto\nCódigo: " + super.getCodigo() + "\nData: " + super.getData() + "\nLatitude: " + super.getLatitude() + "\nLongitude: " + super.getLongitude() + "\nMagnitude " + magnitude + " em escala Richter\n";
    }
}
