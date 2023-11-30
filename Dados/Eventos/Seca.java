package Dados.Eventos;

public class Seca extends Evento{
    private int estiagem;
    public Seca(String codigo, String data, double latitude, double longitude, int estiagem) {
        super(codigo, data, latitude, longitude);
        this.estiagem = estiagem;
    }

    public String toString() {
        return "Seca\nCódigo: " + super.getCodigo() + "\nData: " + super.getData() + "\nLatitude: " + super.getLatitude() + "\nLongitude: " + super.getLongitude() + "\nEstiagem: " + estiagem + "\n";
    }

    public int getEstiagem() {
        return estiagem;
    }
}
