package Paineis;
import java.awt.GridLayout;

import javax.swing.*;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.CadastraAtendimento;

public class DadosAtendimentos {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton voltarButton = new JButton("Voltar");
    private JButton limparButton = new JButton("Limpar");
    private JButton dadoButton = new JButton("Mostrar Dados do Atendimento Selecionado");
    private JButton dadosButton = new JButton("Mostrar Dados de todos os Atendimentos");
    private JComboBox selectAtendimento = new JComboBox<>();
    private JLabel selecioneAtendimento = new JLabel("Selecione o Atendimento:");
    private JLabel mudarStatus = new JLabel("Mudar Status:");
    private JComboBox status = new JComboBox<>();
    private JButton mudarButton = new JButton("Mudar");
    private JLabel confirmar = new JLabel("Confirmar Mudança");

    public JPanel getPainel() {
        return painel;
    }

    public DadosAtendimentos() {
        formatarPainel();
        for(Atendimento atendimento : CadastraAtendimento.getAtendimentos()) {
            selectAtendimento.addItem(atendimento.getCod());
        }
        selectAtendimento.setSelectedItem(null);
        status.addItem("FINALIZADO");
        status.addItem("CANCELADO");
        status.setSelectedItem(null);
        adicionarListeners();
    }

    private void formatarPainel(){
        painel1.removeAll();
        painel2.setLayout(new GridLayout(1, 1, 5, 5));
        painel1.setLayout(new GridLayout(5, 2, 5, 5));
        painel.setLayout(new GridLayout(2, 1, 5, 5));
        painel1.add(selecioneAtendimento);
        painel1.add(selectAtendimento);
        painel1.add(mudarStatus);
        painel1.add(status);
        painel1.add(confirmar);
        painel1.add(mudarButton);
        painel1.add(dadoButton);
        painel1.add(dadosButton);
        painel1.add(limparButton);
        painel1.add(voltarButton);
        painel2.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);
        painel.add(painel1);
        painel.add(painel2);
    }

    private void adicionarListeners() { 
        voltarButton.addActionListener(e -> voltarAplicacao());
        limparButton.addActionListener(e -> limparCampos());
        dadosButton.addActionListener(e -> mostrarDados());
        dadoButton.addActionListener(e -> mostrarDado());
        mudarButton.addActionListener(e -> mudarStatus());
    }

    private void mudarStatus() {
        if(selectAtendimento.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um atendimento!");
            return;
        }
        if (status.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um status!");
            return;
        }
        for (Atendimento atendimento : CadastraAtendimento.getAtendimentos()) {
            if (Integer.valueOf(atendimento.getCod()).equals(selectAtendimento.getSelectedItem())) {
                if(CadastraAtendimento.alterarSituacaoAtendimento((int) selectAtendimento.getSelectedItem(), (String) status.getSelectedItem())){
                    campoDeMensagens.setText("Status alterado com sucesso!");
                    CadastraAtendimento.vincularAtendimentos();
                    return;
                }
            }
        }
    }

    private void mostrarDado() {
        if(selectAtendimento.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um atendimento!");
            return;
        }
        for (Atendimento atendimento : CadastraAtendimento.getAtendimentos()) {
            if (Integer.valueOf(atendimento.getCod()).equals(selectAtendimento.getSelectedItem())) {
                campoDeMensagens.setText(atendimento.toString());
            }
        }
    }

    private void mostrarDados() {
        if(CadastraAtendimento.getAtendimentos().isEmpty()) {
            campoDeMensagens.setText("Nenhum atendimento cadastrado!");
            return;
        }
        campoDeMensagens.setText(CadastraAtendimento.todosDadosAtendimentos());
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
        status.setSelectedItem(null);
        campoDeMensagens.setText("");
        formatarPainel();
    }
}
