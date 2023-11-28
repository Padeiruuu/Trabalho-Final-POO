package Paineis;
import javax.swing.*;

import Dados.Equipamentos.CadastraEquipamento;
import Dados.Equipamentos.Equipamento;
import Dados.Equipes.CadastraEquipe;
import Dados.Equipes.Equipe;
import java.awt.GridLayout;

public class VinculoEquipamentoEquipe {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JComboBox selectEquipamento = new JComboBox<>();
    private JComboBox<String> selectEquipe = new JComboBox<>();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton vincularButton = new JButton("Vincular");
    private JButton voltarButton = new JButton("Voltar");
    private JLabel selecioneEquipamento = new JLabel("Selecione o Equipamento:");
    private JLabel selecioneEquipe = new JLabel("Selecione a Equipe:");
    private JButton vinculosButton = new JButton("Mostrar equipamentos vinculados à equipe");
    private JButton limparButton = new JButton("Limpar");

    public JPanel getPainel() {
        return painel;
    }

    public VinculoEquipamentoEquipe() {
        formatarPainel();
        for(Equipamento equipamento : CadastraEquipamento.getEquipamentos()) {
            selectEquipamento.addItem(equipamento.getId());
        }
        selectEquipamento.setSelectedItem(null);
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

        painel1.add(selecioneEquipamento);
        painel1.add(selectEquipamento);

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
        if(selectEquipamento.getSelectedItem() == null && selectEquipe.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um equipamento e uma equipe!");
            return;
        }
        if(selectEquipe.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione uma equipe!");
            return;
        }
        if(selectEquipamento.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione um equipamento!");
            return;
        }
        int equipamentoId = (int) selectEquipamento.getSelectedItem();
        for (Equipamento equipamento : CadastraEquipamento.getEquipamentos()) {
            if (equipamento.getId() == equipamentoId) {
                for (Equipe equipe : CadastraEquipe.getEquipes()) {
                    if (equipe.getEquipamentos().contains(equipamento)) {
                        campoDeMensagens.setText("Equipamento já vinculado!");
                        selectEquipamento.setSelectedItem(null);
                        return;
                    }
                }
                String equipeSelecionada = (String) selectEquipe.getSelectedItem();
                for (Equipe equipe : CadastraEquipe.getEquipes()) {
                    if (equipe.getCodinome().equals(equipeSelecionada)) {
                        if(equipe.addEquipamento(equipamento));{
                            campoDeMensagens.setText("Equipamento vinculado com sucesso!");
                            selectEquipamento.setSelectedItem(null);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void mostrarVinculos() {
        if(selectEquipe.getSelectedItem() == null) {
            campoDeMensagens.setText("Selecione uma equipe!");
            return;
        }
        String equipeSelecionada = (String) selectEquipe.getSelectedItem();
        for (Equipe equipe : CadastraEquipe.getEquipes()) {
            if (equipe.getCodinome().equals(equipeSelecionada)) {
                if (!equipe.getEquipamentos().isEmpty()) {
                    StringBuilder mensagem = new StringBuilder("Equipamentos vinculados à equipe " + equipeSelecionada + ":\n");
                    for (Equipamento equipamento : equipe.getEquipamentos()) {
                        mensagem.append(equipamento.toString()).append("\n");
                    }
                    campoDeMensagens.setText(mensagem.toString());
                } else {
                    campoDeMensagens.setText("Nenhum equipamento vinculado à equipe " + equipeSelecionada + ".");
                }
                return;
            }
        }
    }

    private void adicionarListeners() {
        vincularButton.addActionListener(e -> vincular());
        voltarButton.addActionListener(e -> voltarAplicacao());
        vinculosButton.addActionListener(e -> mostrarVinculos());
        limparButton.addActionListener(e -> limparCampos());
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuCadastros MenuCadastros = new MenuCadastros();
        painel.add(MenuCadastros.getPainel());
        painel.revalidate();
        painel.repaint();
    }

    private void limparCampos() {
        selectEquipamento.setSelectedItem(null);
        selectEquipe.setSelectedItem(null);
    }
}
