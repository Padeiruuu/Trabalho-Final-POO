package Dados;
import javax.swing.*;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.CadastraAtendimento;
import Dados.Eventos.CadastraEvento;
import Dados.Eventos.Evento;

import java.awt.*;

public class FormularioAtendimentos {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JComboBox<String> selectEvento = new JComboBox<>();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton voltarButton = new JButton("Voltar");
    private JButton dadosButton = new JButton("Mostrar Dados");
    private JButton limparButton = new JButton("Limpar");
    private JLabel selecioneEvento = new JLabel("Selecione o evento:");
    private JLabel digiteID = new JLabel("Digite o ID:");
    private JTextField id = new JTextField();
    private JLabel digiteData = new JLabel("Digite a Data:");
    private JTextField data = new JTextField();
    private JLabel digiteDuracao = new JLabel("Digite a Duração:");
    private JTextField duracao = new JTextField();

    public JPanel getPainel() {
        return painel;
    }

    public FormularioAtendimentos() {
        formatarPainel();
        for(Evento evento : CadastraEvento.getEventos()) {
            selectEvento.addItem(evento.getCodigo());
        }
        selectEvento.setSelectedItem(null);
        adicionarListeners();
    }

    private void selecionaEvento() {
        for(Evento evento : CadastraEvento.getEventos()) {
            if (evento.getCodigo().equals(selectEvento.getSelectedItem())) {
                campoDeMensagens.setText(CadastraEvento.obterTextoEventos());
            }
        }
    }

    private void formatarPainel() {
        painel1.removeAll();
        painel2.setLayout(new GridLayout(1, 1, 5, 5));
        painel1.setLayout(new GridLayout(6, 2, 5, 5));
        painel.setLayout(new GridLayout(2, 1, 5, 5));
        painel1.add(selecioneEvento);
        painel1.add(selectEvento);
        painel1.add(digiteID);
        painel1.add(id);
        painel1.add(digiteData);
        painel1.add(data);
        painel1.add(digiteDuracao);
        painel1.add(duracao);
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

    private void adicionarListeners(){
        cadastrarButton.addActionListener(e -> cadastraAtendimento());
        limparButton.addActionListener(e -> limparCampos());
        dadosButton.addActionListener(e -> mostrarDadosCadastrados());
        voltarButton.addActionListener(e -> voltarAplicacao());
        selectEvento.addActionListener(e -> selecionaEvento());
    }

    private void cadastraAtendimento() {
        try {
            int id = Integer.parseInt(this.id.getText());
            String data = this.data.getText();
            int duracao = Integer.parseInt(this.duracao.getText());
            for(Evento evento : CadastraEvento.getEventos()) {
                if (evento.getCodigo().equals(selectEvento.getSelectedItem())) {
                    if (CadastraAtendimento.cadastrarAtendimento(new Atendimento(id, data, duracao, evento))) {
                        campoDeMensagens.setText("Atendimento cadastrado com sucesso!");
                    } else {
                        campoDeMensagens.setText("Atendimento já cadastrado!");
                    }
                }
            }
        }
        catch (NumberFormatException e) {
            campoDeMensagens.setText("Dados inválidos! Verifique os campos novamente.");
        }
        catch (Exception e) {
            campoDeMensagens.setText("Erro ao cadastrar atendimento!");
        }
    }

    public void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuCadastros menuCadastros = new MenuCadastros();
        painel.add(menuCadastros.getPainel());
        painel.revalidate();
        painel.repaint();
    }

    private void limparCampos() {
        id.setText("");
        data.setText("");
        duracao.setText("");
        campoDeMensagens.setText("");
        selectEvento.setSelectedItem(null);
        formatarPainel();
    }

    private void mostrarDadosCadastrados() {
        campoDeMensagens.setText(CadastraAtendimento.obterTextoAtendimentos());
    }
}
