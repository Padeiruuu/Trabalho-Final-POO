package Dados.Atendimentos;
import java.util.Queue;

import Dados.Equipes.CadastraEquipe;
import Dados.Equipes.Equipe;
import Dados.Eventos.CadastraEvento;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class CadastraAtendimento {
    private static Queue<Atendimento> atendimentos = new LinkedList<>();
    private static ArrayList<Equipe> equipes = CadastraEquipe.getEquipes();

    public static boolean cadastrarAtendimento(Atendimento atendimento) {
        for(Atendimento a : atendimentos) {
            if (a.getCod() == atendimento.getCod()) {
                return false;
            }
            else if(a.getEvento().equals(atendimento.getEvento())){
                return false;
            }
        }
        atendimentos.add(atendimento);
        vincularAtendimentos();
        ordenarAtendimentos();
        return true;
    }

    private static void ordenarAtendimentos() {
        List<Atendimento> atendimentosList = new ArrayList<>(atendimentos);
        atendimentosList.sort(Comparator.comparing(Atendimento::getCod));
        atendimentos = new LinkedList<>(atendimentosList);
    }

    public static String obterTextoAtendimentos() {
        if(atendimentos.isEmpty()) {
            return "Não há atendimentos cadastrados.";
        }
        StringBuilder texto = new StringBuilder();
        for (Atendimento atendimento : atendimentos) {
            texto.append(atendimento.toString()).append("\n");
        }
        return texto.toString();
    }
    
    public static String todosDadosAtendimentos() {
        StringBuilder texto = new StringBuilder();
        if (atendimentos.isEmpty()) {
            return "Não há atendimentos cadastrados.";
        }
        for (Atendimento atendimento : atendimentos) {
            texto.append(atendimento.toString());
    
            if (atendimento.getEquipe() != null) {
                texto.append("Equipe: ").append(atendimento.getEquipe().toString());
                texto.append("Equipamentos da Equipe: \n").append(atendimento.getEquipe().getEquipamentosString());
                String custoFormatado = String.format("Custo do Atendimento: R$%.2f\n", atendimento.calculaCusto());
                texto.append(custoFormatado);
            }
            texto.append("\n");
        }
        return texto.toString();
    }

    public static Queue<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public static void vincularAtendimentos() {
        ArrayList<Atendimento> atendimentosNaoVinculados = new ArrayList<>(atendimentos);
        Queue<Atendimento> atendimentosPendentes = new LinkedList<>(atendimentos);
    
        while (!atendimentosPendentes.isEmpty()) {
            Atendimento atendimento = atendimentosPendentes.poll();
            if(!atendimento.getStatus().equals("EXECUTANDO") && !atendimento.getStatus().equals("FINALIZADO")){
                Equipe equipeDisponivel = encontrarEquipeDisponivel(atendimento);
                if (equipeDisponivel != null) {
                    atendimento.setStatus("EXECUTANDO");
                    atendimento.setEquipe(equipeDisponivel);
                    equipeDisponivel.addAtendimento(atendimento);
                } else {
                    atendimentosNaoVinculados.add(atendimento);
                }
            }
        }
    }
    
    private static Equipe encontrarEquipeDisponivel(Atendimento atendimento) {
        boolean equipeNaDistancia = false;
        for (Equipe equipe : equipes) {  
            if (getDistancia(atendimento, equipe) <= 5000) {
                equipeNaDistancia = true;
                if (equipe.getAtendimentos().isEmpty()) {
                    return equipe;
                } else {
                    for (Atendimento atendimentoEquipe : equipe.getAtendimentos()) {
                        if (!atendimentoEquipe.getStatus().equals("EXECUTANDO")) {
                            return equipe;
                        } 
                    }
                }
            }
        }
        if(!equipeNaDistancia){
            atendimento.setStatus("CANCELADO");
        }
        return null;
    }

    public static boolean alterarSituacaoAtendimento(int codigo, String status) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getCod() == codigo) {
                if (!atendimento.getStatus().equals("FINALIZADO")) {
                    atendimento.setStatus(status);
                    return true;
                }
            }
        }
        return false;
    }

    public static double getDistancia(Atendimento atendimento, Equipe equipe){
        double latEq = Math.toRadians(equipe.getLatitude());
        double lonEq = Math.toRadians(equipe.getLongitude());
        double latEv = Math.toRadians(CadastraEvento.getEvento(atendimento.getEvento()).getLatitude());
        double lonEv = Math.toRadians(CadastraEvento.getEvento(atendimento.getEvento()).getLongitude());
        double lon = lonEv - lonEq;
        double lat = latEv - latEq;
        double dif = Math.pow(Math.sin(lat / 2), 2) + Math.cos(latEq) * Math.cos(latEv) * Math.pow(Math.sin(lon / 2), 2);
        double distancia = 2 * Math.asin(Math.sqrt(dif)) * 6371;
        return distancia;
    }
}

