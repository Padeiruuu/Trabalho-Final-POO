package Dados.Atendimentos;
import java.util.ArrayList;
import java.util.Comparator;

public class CadastraAtendimento {
    private static ArrayList<Atendimento> atendimentos = new ArrayList<>();

    public static boolean cadastrarAtendimento(Atendimento atendimento) {
        for(Atendimento a : atendimentos) {
            if (a.getCod() == atendimento.getCod()) {
                return false;
            }
            else if(a.getEvento().getCodigo().equals(atendimento.getEvento().getCodigo())){
                return false;
            }
        }
        atendimentos.add(atendimento);
        ordenarAtendimentos();
        return true;
    }

    private static void ordenarAtendimentos() {
        atendimentos.sort(Comparator.comparing(Atendimento::getCod));
    }

    public static String obterTextoAtendimentos() {
        StringBuilder texto = new StringBuilder();
        for (Atendimento atendimento : atendimentos) {
            texto.append(atendimento.toString()).append("\n");
        }
        return texto.toString();
    }
}
