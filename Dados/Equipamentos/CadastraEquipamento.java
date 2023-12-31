package Dados.Equipamentos;
import java.util.ArrayList;
import java.util.Comparator;

public class CadastraEquipamento{
    private static ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public static boolean cadastrarEquipamento(Equipamento equipamento) {
        for(Equipamento e : equipamentos) {
            if (e.getId() == equipamento.getId()) {
                return false;
            }
        }
        equipamentos.add(equipamento);
        ordenarEquipamentos();
        return true;
    }

    private static void ordenarEquipamentos() {
        equipamentos.sort(Comparator.comparing(Equipamento::getId));
    }

    public static String obterTextoEquipamentos() {
        StringBuilder texto = new StringBuilder();
        for (Equipamento equipamento : equipamentos) {
            texto.append(equipamento.toString()).append("\n");
        }
        return texto.toString();
    }
}