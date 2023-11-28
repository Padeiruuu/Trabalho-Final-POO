package Dados.Equipes;

import java.util.ArrayList;
import Dados.Atendimentos.*;
import Dados.Equipamentos.Equipamento;

public class Equipe {
    private String codinome;
    private int quantidadeMembros;
    private double latitude;
    private double longitude;
    private ArrayList<Equipamento> equipamentos;
    private ArrayList<Atendimento> atendimentos;

    public Equipe(String codinome, int quantidadeMembros, double latitude, double longitude, ArrayList<Equipamento> equipamentos, ArrayList<Atendimento> atendimentos) {
        this.codinome = codinome;
        this.quantidadeMembros = quantidadeMembros;
        this.latitude = latitude;
        this.longitude = longitude;
        this.equipamentos = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
    }

    public String toString() {
        return "Codinome: " + codinome + "\nQuantidade de Membros: " + quantidadeMembros + "\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n";
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

    public String getEquipamentosString() {
        String equipamentosString = "";
        for (int i = 0; i < equipamentos.size(); i++) {
            equipamentosString += equipamentos.get(i).toString();
        }
        return equipamentosString;
    }

    public String getAtendimentosString() {
        String atendimentosString = "";
        for (int i = 0; i < atendimentos.size(); i++) {
            atendimentosString += atendimentos.get(i).toString() + "\n";
        }
        return atendimentosString;
    }

    public boolean addEquipamento(Equipamento equipamento) {
        for (Equipamento e : equipamentos) {
            if (e.getId() == equipamento.getId()) {
                return false;
            }
        }

        return equipamentos.add(equipamento);
    }

    public boolean addAtendimento(Atendimento atendimento) {
        for (Atendimento a : atendimentos) {
            if (a.getCod() == atendimento.getCod()) {
                return false;
            }
        }
        return atendimentos.add(atendimento);
    }

    public ArrayList<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public double getCustoDia() {
        double custo = 0;
        for (Equipamento equipamento : equipamentos) {
            custo += equipamento.getCustoDia();
        }
        return custo;
    }
}
