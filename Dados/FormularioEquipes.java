package Dados;
import java.awt.GridLayout;

import javax.swing.*;

import Dados.Equipes.CadastraEquipe;
import Dados.Equipes.Equipe;

public class FormularioEquipes {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JLabel digiteCodinome = new JLabel("Digite o Codinome:");
    private JLabel digiteQuantidade = new JLabel("Digite a Quantidade de Membros:");
    private JLabel digiteLatitude = new JLabel("Digite a Latitude:");
    private JLabel digiteLongitude = new JLabel("Digite a Longitude:");
    private JTextField codinome = new JTextField();
    private JTextField quantidade = new JTextField();
    private JTextField latitude = new JTextField();
    private JTextField longitude = new JTextField();
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton limparButton = new JButton("Limpar");
    private JButton dadosButton = new JButton("Mostrar Dados");
    private JButton voltarButton = new JButton("Voltar");
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);

    public JPanel getPainel() {
        return painel;
    }

    public FormularioEquipes() {
        formatarPainel();
        adicionarListeners();
    }

    private void formatarPainel() {
        painel1.removeAll();
        painel2.setLayout(new GridLayout(1, 1, 5, 5));
        painel1.setLayout(new GridLayout(6, 2, 5, 5));
        painel.setLayout(new GridLayout(2, 1, 5, 5));
        painel1.add(digiteCodinome);
        painel1.add(codinome);
        painel1.add(digiteQuantidade);
        painel1.add(quantidade);
        painel1.add(digiteLatitude);
        painel1.add(latitude);
        painel1.add(digiteLongitude);
        painel1.add(longitude);
        painel1.add(cadastrarButton);
        painel1.add(limparButton);
        painel1.add(dadosButton);
        painel1.add(voltarButton);

        painel2.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);

        painel.add(painel1);
        painel.add(painel2);
    }

    private void adicionarListeners() {
        cadastrarButton.addActionListener(e -> cadastraEquipe());
        limparButton.addActionListener(e -> limparCampos());
        dadosButton.addActionListener(e -> mostrarDados());
        voltarButton.addActionListener(e -> voltarAplicacao());
    }

    private void cadastraEquipe() {
        try {
            String codinome = this.codinome.getText();
            int quantidade = Integer.parseInt(this.quantidade.getText());
            double latitude = Double.parseDouble(this.latitude.getText());
            double longitude = Double.parseDouble(this.longitude.getText());
            if (CadastraEquipe.cadastrarEquipe(new Equipe(codinome, quantidade, latitude, longitude))) {
                campoDeMensagens.setText("Equipe cadastrada com sucesso!");
            } else {
                campoDeMensagens.setText("Equipe já cadastrada!");
            }
        }
        catch (NumberFormatException e) {
            campoDeMensagens.setText("Dados inválidos! Verifique os campos novamente.");
        }
        catch (Exception e) {
            campoDeMensagens.setText("Erro ao cadastrar equipe!");
        }
    }

    private void limparCampos() {
        codinome.setText("");
        quantidade.setText("");
        latitude.setText("");
        longitude.setText("");
        campoDeMensagens.setText("");
        formatarPainel();
    }

    private void mostrarDados() {
        campoDeMensagens.setText(CadastraEquipe.obterTextoEquipes());
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuCadastros menuCadastros = new MenuCadastros();
        painel.add(menuCadastros.getPainel());
        painel.revalidate();
        painel.repaint();
    }
}
