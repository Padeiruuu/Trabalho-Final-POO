package Dados.Equipes;
import java.util.ArrayList;
import java.util.Comparator;

public class CadastraEquipe {
    private static ArrayList<Equipe> equipes = new ArrayList<>();

    public static boolean cadastrarEquipe(Equipe equipe) {
        for(Equipe e : equipes) {
            if (e.getCodinome().equals(equipe.getCodinome())) {
                return false;
            }
        }
        equipes.add(equipe);
        ordenarEquipes();
        return true;
    }

    private static void ordenarEquipes() {
        equipes.sort(Comparator.comparing(Equipe::getCodinome));
    }

    public static String obterTextoEquipes() {
        StringBuilder texto = new StringBuilder();
        for (Equipe equipe : equipes) {
            texto.append(equipe.toString()).append("\n");
        }
        return texto.toString();
    }
}
