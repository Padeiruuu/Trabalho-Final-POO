package Paineis;
import java.awt.GridLayout;

import javax.swing.*;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.CadastraAtendimento;
import Dados.Equipes.CadastraEquipe;
import Dados.Equipes.Equipe;

public class VinculoAtendimentoEquipe {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JComboBox selectAtendimento = new JComboBox<>();
    private JComboBox selectEquipe = new JComboBox<>();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton vincularButton = new JButton("Vincular");
    private JButton voltarButton = new JButton("Voltar");
    private JLabel selecioneAtendimento = new JLabel("Selecione o Atendimento:");
    private JLabel selecioneEquipe = new JLabel("Selecione a Equipe:");
    private JButton vinculosButton = new JButton("Mostrar atendimentos vinculados às equipes");
    private JButton limparButton = new JButton("Limpar");

    public JPanel getPainel() {
        return painel;
    }

    public VinculoAtendimentoEquipe() {
        formatarPainel();
        for(Atendimento atendimento : CadastraAtendimento.getAtendimentos()) {
            selectAtendimento.addItem(atendimento.getCod());
        }
        selectAtendimento.setSelectedItem(null);
        for(Equipe equipe : CadastraEquipe.getEquipes()) {
            selectEquipe.addItem(equipe.getCodinome());
        }
        selectEquipe.setSelectedItem(null);
        adicionarListeners();
    }

    private void formatarPainel() {
        painel1.removeAll();
        painel2.setLayout(new GridLayout(1, 1, 5, 5));
        painel1.setLayout(new GridLayout(4, 2, 5, 5));
        painel.setLayout(new GridLayout(2, 1, 5, 5));

        painel1.add(selecioneEquipe);
        painel1.add(selectEquipe);

        painel1.add(selecioneAtendimento);
        painel1.add(selectAtendimento);

        painel1.add(vincularButton);
        painel1.add(vinculosButton);
        painel1.add(limparButton);
        painel1.add(voltarButton);

        painel2.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);

        painel.add(painel1);
        painel.add(painel2);
    }

    private void vincular() {
        if(selectAtendimento.getSelectedItem() == null && selectEquipe.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um atendimento e uma equipe!");
            return;
        }
        if(selectEquipe.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione uma equipe!");
            return;
        }
        if(selectAtendimento.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um atendimento!");
            return;
        }
        int cod = (int) selectAtendimento.getSelectedItem();
        
    }

    private void adicionarListeners() {
        vincularButton.addActionListener(e -> vincular());
        vinculosButton.addActionListener(e -> mostrarVinculos());
        voltarButton.addActionListener(e -> voltarAplicacao());
        limparButton.addActionListener(e -> limparCampos());
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        painel.add(menuPrincipal.getPainel());
        painel.revalidate();
        painel.repaint();
    } 

    private void limparCampos() {
        selectAtendimento.setSelectedItem(null);
        selectEquipe.setSelectedItem(null);
        campoDeMensagens.setText("");
    }
}
