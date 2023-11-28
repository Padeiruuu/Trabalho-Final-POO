package Dados.Atendimentos;
import java.util.Queue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class CadastraAtendimento {
    private static Queue<Atendimento> atendimentos = new LinkedList<>();

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
        List<Atendimento> atendimentosList = new ArrayList<>(atendimentos);
        atendimentosList.sort(Comparator.comparing(Atendimento::getCod));
        atendimentos = new LinkedList<>(atendimentosList);
    }

    public static String obterTextoAtendimentos() {
        StringBuilder texto = new StringBuilder();
        for (Atendimento atendimento : atendimentos) {
            texto.append(atendimento.toString()).append("\n");
        }
        return texto.toString();
    }

    public static Queue<Atendimento> getAtendimentos() {
        return atendimentos;
    }
}
