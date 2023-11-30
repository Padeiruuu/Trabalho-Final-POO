package Dados.Eventos;
import java.util.ArrayList;
import java.util.Comparator;

public class CadastraEvento {
    private static ArrayList<Evento> eventos = new ArrayList<>();

    public static boolean cadastrarEvento(Evento evento) {
        for(Evento e : eventos) {
            if (e.getCodigo().equals(evento.getCodigo())) {
                return false;
            }
        }
        if(evento.getLatitude() < -90 || evento.getLatitude() > 90 || evento.getLongitude() < -180 || evento.getLongitude() > 180) {
            return false;
        }
        if(evento instanceof Ciclone) {
            if(((Ciclone) evento).getVelocidade() < 0) {
                return false;
            }
        }
        if(evento instanceof Seca) {
            if(((Seca) evento).getEstiagem() < 0) {
                return false;
            }
        }
        if(evento instanceof Terremoto) {
            if(((Terremoto) evento).getMagnitude() < 0 && ((Terremoto) evento).getMagnitude() > 10) {
                return false;
            }
        }
        eventos.add(evento);
        ordenarEventos();
        return true;
    }
    private static void ordenarEventos() {
        eventos.sort(Comparator.comparing(Evento::getCodigo));
    }
    public static String obterTextoEventos() {
        if(eventos.isEmpty()) {
            return "Não há eventos cadastrados.";
        }
        StringBuilder texto = new StringBuilder();
        for (Evento evento : eventos) {
            texto.append(evento.toString()).append("\n");
        }
        return texto.toString();
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }

    public static Evento getEvento(String codigo) {
        for(Evento evento : eventos) {
            if (evento.getCodigo().equals(codigo)) {
                return evento;
            }
        }
        return null;
    }
}