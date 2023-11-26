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
        eventos.add(evento);
        ordenarEventos();
        return true;
    }
    private static void ordenarEventos() {
        eventos.sort(Comparator.comparing(Evento::getCodigo));
    }
    public static String obterTextoEventos() {
        StringBuilder texto = new StringBuilder();
        for (Evento evento : eventos) {
            texto.append(evento.toString()).append("\n");
        }
        return texto.toString();
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }
}