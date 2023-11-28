package Paineis;
import javax.swing.*;

import java.awt.*;

public class MenuCadastros {
    private JPanel painel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JButton eventosButton = new JButton("Eventos");
    private JButton equipamentosButton = new JButton("Equipamentos");
    private JButton atendimentosButton = new JButton("Atendimentos");
    private JButton equipesButton = new JButton("Equipes");
    private JButton vinculoButton = new JButton("Vincular Equipamentos");
    private JButton voltarButton = new JButton("Voltar");
    private JPanel menuPanel = new JPanel();

    public MenuCadastros() {
        formatarPainel();
        adicionarListeners();
    }

    public JPanel getPainel() {
        return painel;
    }

    private void formatarPainel() {
        painel.setLayout(cardLayout);
        FormularioEventos formularioEventos = new FormularioEventos();
        painel.add(formularioEventos.getPainel(), "Eventos");
        FormularioEquipamentos formularioEquipamentos = new FormularioEquipamentos();
        painel.add(formularioEquipamentos.getPainel(), "Equipamentos");
        FormularioAtendimentos formularioAtendimentos = new FormularioAtendimentos();
        painel.add(formularioAtendimentos.getPainel(), "Atendimentos");
        FormularioEquipes formularioEquipes = new FormularioEquipes();
        painel.add(formularioEquipes.getPainel(), "Equipes");
        VinculoEquipamentoEquipe vínculoEquipamentoEquipe = new VinculoEquipamentoEquipe();
        painel.add(vínculoEquipamentoEquipe.getPainel(), "Vinculo");
        menuPanel.setLayout(new GridLayout(6, 1, 5, 5));
        menuPanel.add(eventosButton);
        menuPanel.add(equipamentosButton);
        menuPanel.add(equipesButton);
        menuPanel.add(atendimentosButton);
        menuPanel.add(vinculoButton);
        menuPanel.add(voltarButton);
        painel.add(menuPanel, "Menu");
        cardLayout.show(painel, "Menu");
    }

    private void adicionarListeners() {
        eventosButton.addActionListener(e -> cardLayout.show(painel, "Eventos"));
        equipamentosButton.addActionListener(e -> cardLayout.show(painel, "Equipamentos"));
        atendimentosButton.addActionListener(e -> cardLayout.show(painel, "Atendimentos"));
        equipesButton.addActionListener(e -> cardLayout.show(painel, "Equipes"));
        vinculoButton.addActionListener(e -> cardLayout.show(painel, "Vinculo"));
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
}