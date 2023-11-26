package Dados;
import javax.swing.*;

import java.awt.*;

public class MenuPrincipal {
    private JPanel painel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JButton cadastrosButton = new JButton("Cadastrar");
    private JButton equipeButton = new JButton("Vincular Equipamentos");
    private JButton atendimentoButton = new JButton("Vincular Atendimentos");
    private JButton atendimentosButton = new JButton("Atendimentos");
    private JButton relatoriosButton = new JButton("Relatórios");
    private JButton carregarButton = new JButton("Carregar Dados Iniciais");
    private JButton carregarButton2 = new JButton("Carregar Dados");
    private JButton salvarButton = new JButton("Salvar Dados");
    private JButton fecharButton = new JButton("Fechar");
    private JPanel menuPanel = new JPanel();

    public MenuPrincipal() {
        formatarPainel();
        adicionarListeners();
    }

    public JPanel getPainel() {
        return painel;
    }

    private void formatarPainel() {
        painel.setLayout(cardLayout);
        MenuCadastros menuCadastros = new MenuCadastros();
        painel.add(menuCadastros.getPainel(), "Cadastros");
        menuPanel.setLayout(new GridLayout(8, 1, 5, 5));
        menuPanel.add(cadastrosButton);
        menuPanel.add(equipeButton);
        menuPanel.add(atendimentoButton);
        menuPanel.add(atendimentosButton);
        menuPanel.add(relatoriosButton);
        menuPanel.add(carregarButton);
        menuPanel.add(carregarButton2);
        menuPanel.add(salvarButton);
        menuPanel.add(fecharButton);
        painel.add(menuPanel, "Menu");
        cardLayout.show(painel, "Menu");
    }

    private void adicionarListeners() {
        cadastrosButton.addActionListener(e -> cardLayout.show(painel, "Cadastros"));
        equipeButton.addActionListener(e -> cardLayout.show(painel, "Equipes"));
        atendimentoButton.addActionListener(e -> cardLayout.show(painel, "Atendimentos"));
        atendimentosButton.addActionListener(e -> cardLayout.show(painel, "Atendimentos"));
        relatoriosButton.addActionListener(e -> cardLayout.show(painel, "Relatórios"));
        carregarButton.addActionListener(e -> cardLayout.show(painel, "Carregar"));
        carregarButton2.addActionListener(e -> cardLayout.show(painel, "Carregar"));
        salvarButton.addActionListener(e -> cardLayout.show(painel, "Salvar"));
        fecharButton.addActionListener(e -> System.exit(0));
    }
}