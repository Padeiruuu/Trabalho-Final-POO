package Paineis;
import javax.swing.*;

import Dados.Equipamentos.Barco;
import Dados.Equipamentos.CadastraEquipamento;
import Dados.Equipamentos.CaminhaoTanque;
import Dados.Equipamentos.Escavadeira;

import java.awt.*;
import java.util.InputMismatchException;

public class FormularioEquipamentos{
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JComboBox<String> tipoDeEquipamento = new JComboBox<>();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton limparButton = new JButton("Limpar");
    private JButton dadosButton = new JButton("Mostrar Dados");
    private JButton voltarButton = new JButton("Voltar");
    private JLabel tipoLabel = new JLabel("Selecione o Tipo de Equipamento:");
    private JLabel digiteID = new JLabel("Digite o ID:");
    private JLabel digiteNome = new JLabel("Digite o Nome:");
    private JLabel digiteCustoDia = new JLabel("Digite o Custo por Dia:");
    private JTextField id = new JTextField();
    private JTextField nome = new JTextField();
    private JTextField custoDia = new JTextField();
    private JLabel digiteCapacidade = new JLabel("Digite a Capacidade:");
    private JTextField capacidade = new JTextField();
    private JLabel digiteCombustivel = new JLabel("Selecione o Combustível:");
    private JComboBox<String> combustivel = new JComboBox<>();
    private JLabel digiteCarga = new JLabel("Digite a Carga:");
    private JTextField carga = new JTextField();

    public JPanel getPainel() {
        return painel;
    }

    public FormularioEquipamentos() {
        formatarPainel();
        tipoDeEquipamento.addItem("Barco");
        tipoDeEquipamento.addItem("Caminhão Tanque");
        tipoDeEquipamento.addItem("Escavadeira");
        tipoDeEquipamento.setSelectedItem(null);
        combustivel.addItem("Diesel");
        combustivel.addItem("Gasolina");
        combustivel.addItem("Alcool");
        combustivel.setSelectedItem(null);
        adicionarListeners();
    }

    private void formatarPainel() {
        painel1.removeAll();
        if (tipoDeEquipamento.getSelectedItem() == "Barco") {
            painel1.setLayout(new GridLayout(7, 2, 5, 5));
        } else if (tipoDeEquipamento.getSelectedItem() == "Caminhão Tanque") {
            painel1.setLayout(new GridLayout(7, 2, 5, 5));
        } else if (tipoDeEquipamento.getSelectedItem() == "Escavadeira") {
            painel1.setLayout(new GridLayout(8, 2, 5, 5));
        } else {
            painel1.setLayout(new GridLayout(6, 2, 5, 5));
        }
        painel.setLayout(new GridLayout(2, 1, 5, 5));
        painel2.setLayout(new GridLayout(1, 1, 5, 5));

        painel1.add(digiteID);
        painel1.add(id);

        painel1.add(digiteNome);
        painel1.add(nome);

        painel1.add(digiteCustoDia);
        painel1.add(custoDia);

        painel1.add(tipoLabel);
        painel1.add(tipoDeEquipamento);

        if (tipoDeEquipamento.getSelectedItem() == "Barco") {
            painel1.add(digiteCapacidade);
            painel1.add(capacidade);
        } else if (tipoDeEquipamento.getSelectedItem() == "Caminhão Tanque") {
            painel1.add(digiteCapacidade);
            painel1.add(capacidade);
        } else if (tipoDeEquipamento.getSelectedItem() == "Escavadeira") {
            painel1.add(digiteCombustivel);
            painel1.add(combustivel);
            painel1.add(digiteCarga);
            painel1.add(carga);
        }
        painel1.add(cadastrarButton);
        painel1.add(limparButton);
        painel1.add(dadosButton);
        painel1.add(voltarButton);
        
        painel2.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);

        painel.add(painel1);
        painel.add(painel2);

        painel.revalidate();
        painel.repaint();
    }

    private void selecionaTipo() {
        String tipo = (String) tipoDeEquipamento.getSelectedItem();
        if (tipo != null) {
            formatarPainel();
        }
    }

    private void adicionarListeners() {
        cadastrarButton.addActionListener(e -> cadastraEquipamento());
        limparButton.addActionListener(e -> limparCampos());
        dadosButton.addActionListener(e -> mostrarDadosCadastrados());
        voltarButton.addActionListener(e -> voltarAplicacao());
        tipoDeEquipamento.addActionListener(e -> selecionaTipo());
    }
    
    private void cadastraEquipamento(){
        try{
            String tipo = (String) tipoDeEquipamento.getSelectedItem();
            int id = Integer.parseInt(this.id.getText());
            String nome = this.nome.getText();
            double custoDia = Double.parseDouble(this.custoDia.getText());
            if(custoDia < 0) {
                campoDeMensagens.setText("Custo por dia inválido!");
                return;
            }
            if(tipo == null) {
                campoDeMensagens.setText("Selecione um tipo de equipamento!");
                return;
            }
            switch(tipo){
                case "Barco":
                    int capacidade = Integer.parseInt(this.capacidade.getText());
                    if(capacidade < 0){
                        campoDeMensagens.setText("Capacidade inválida!");
                        break;
                    }
                    if(CadastraEquipamento.cadastrarEquipamento(new Barco(id, nome, custoDia, capacidade)))
                        campoDeMensagens.setText("Barco cadastrado com sucesso!");
                    else{
                        campoDeMensagens.setText("Erro ao cadastrar equipamento!");
                    }
                    break;
                case "Caminhão Tanque":
                    double capacidadeTanque = Double.parseDouble(this.capacidade.getText());
                    if (capacidadeTanque < 0) {
                        campoDeMensagens.setText("Capacidade inválida!");
                        break;
                    }
                    if (CadastraEquipamento.cadastrarEquipamento(new CaminhaoTanque(id, nome, custoDia, capacidadeTanque)))
                        campoDeMensagens.setText("Caminhão Tanque cadastrado com sucesso!");
                    else {
                        campoDeMensagens.setText("Erro ao cadastrar equipamento!");
                    }
                    break;
                    case "Escavadeira":
                        String combustivel = (String) this.combustivel.getSelectedItem();
                    if(combustivel == null) {
                        campoDeMensagens.setText("Selecione um tipo de combustível!");
                        break;
                    }
                    double carga = Double.parseDouble(this.carga.getText());
                    if(carga < 0) {
                        campoDeMensagens.setText("Carga inválida!");
                        break;
                    }
                    if(CadastraEquipamento.cadastrarEquipamento(new Escavadeira(id, nome, custoDia, combustivel, carga)))
                        campoDeMensagens.setText("Escavadeira cadastrada com sucesso!");
                    else{
                        campoDeMensagens.setText("Erro ao cadastrar equipamento!");
                    }
                    break;
                default:
                    campoDeMensagens.setText("Selecione um tipo de equipamento!");
                    break;
            }
        } catch (NumberFormatException n){
            campoDeMensagens.setText("Dados inválidos! Verifique os campos novamente.");
        } catch (InputMismatchException e) {
            campoDeMensagens.setText("Dados inválidos! Verifique os campos novamente.");
        } catch (Exception e){
            campoDeMensagens.setText("Erro ao cadastrar equipamento!");
        }
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuCadastros menuCadastros = new MenuCadastros();
        painel.add(menuCadastros.getPainel());
        painel.revalidate();
        painel.repaint();
    }

    private void mostrarDadosCadastrados() {
        campoDeMensagens.setText(CadastraEquipamento.obterTextoEquipamentos());
    }

    private void limparCampos() {
        campoDeMensagens.setText("");
        id.setText("");
        nome.setText("");
        custoDia.setText("");
        tipoDeEquipamento.setSelectedItem(null);
        formatarPainel();
    }
}