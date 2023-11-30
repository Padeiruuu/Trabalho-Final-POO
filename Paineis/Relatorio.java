package Paineis;

import javax.swing.*;
import java.awt.*;
import Dados.Atendimentos.CadastraAtendimento;
import Dados.Equipamentos.CadastraEquipamento;
import Dados.Equipes.CadastraEquipe;
import Dados.Eventos.CadastraEvento;

public class Relatorio {
    private JPanel painel = new JPanel();
    private JTextArea textArea = new JTextArea();
    private JScrollPane scroll = new JScrollPane(textArea);
    private JButton voltarButton = new JButton("Voltar");

    public JPanel getPainel() {
        return painel;
    }

    public Relatorio() {
        formatarPainel();
        adicionarListeners();
        atualizarRelatorio();
    }

    private void formatarPainel() {
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);

        painel.add(voltarButton);
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void adicionarListeners() {
        voltarButton.addActionListener(e -> voltarAplicacao());
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        painel.add(menuPrincipal.getPainel());
        painel.revalidate();
        painel.repaint();
    }

    private void atualizarRelatorio() {
        String relatorio = obterRelatorioGeral();
        textArea.setText(relatorio);
    }

    private String obterRelatorioGeral() {
        String relatorio =
            "Eventos:\n" +
            CadastraEvento.obterTextoEventos() +
            "\nEquipamentos:\n" +
            CadastraEquipamento.obterTextoEquipamentos() +
            "\nEquipes:\n" +
            CadastraEquipe.obterTextoEquipes() +
            "\nAtendimentos:\n" +
            CadastraAtendimento.todosDadosAtendimentos();
        if (CadastraEvento.getEventos().isEmpty() && CadastraEquipamento.getEquipamentos().isEmpty() && CadastraEquipe.getEquipes().isEmpty() && CadastraAtendimento.getAtendimentos().isEmpty()) {
            return "Nenhum dado cadastrado.";
        } else {
            return relatorio;
        }
    }
}