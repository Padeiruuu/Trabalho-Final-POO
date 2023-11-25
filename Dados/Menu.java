package Dados;
import javax.swing.*;

import java.awt.*;

public class Menu {
    private JPanel painel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JButton eventosButton = new JButton("Eventos");
    private JButton equipamentosButton = new JButton("Equipamentos");
    private JButton fecharButton = new JButton("Fechar");

    public Menu() {
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
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 5, 5));
        menuPanel.add(eventosButton);
        menuPanel.add(equipamentosButton);
        menuPanel.add(fecharButton);
        painel.add(menuPanel, "Menu");
        cardLayout.show(painel, "Menu");
    }

    private void adicionarListeners() {
        eventosButton.addActionListener(e -> cardLayout.show(painel, "Eventos"));
        equipamentosButton.addActionListener(e -> cardLayout.show(painel, "Equipamentos"));
        fecharButton.addActionListener(e -> System.exit(0));
    }

    public void setPainel(JPanel painel) {
        this.painel = painel;
    }
}