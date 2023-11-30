package Paineis;
import javax.swing.*;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.CadastraAtendimento;
import Dados.Equipamentos.Barco;
import Dados.Equipamentos.CadastraEquipamento;
import Dados.Equipamentos.CaminhaoTanque;
import Dados.Equipamentos.Equipamento;
import Dados.Equipamentos.Escavadeira;
import Dados.Equipes.CadastraEquipe;
import Dados.Equipes.Equipe;
import Dados.Eventos.CadastraEvento;
import Dados.Eventos.Ciclone;
import Dados.Eventos.Seca;
import Dados.Eventos.Terremoto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class CarregaDadosIniciais {
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JTextArea campoDeMensagens = new JTextArea();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);
    private JButton voltarButton = new JButton("Voltar");
    private JLabel digiteNome = new JLabel("Digite o nome do arquivo:");
    private JTextField nome = new JTextField();
    private JButton carregarButton = new JButton("Carregar");

    public JPanel getPainel() {
        return painel;
    }

    public CarregaDadosIniciais() {
        formatarPainel();
        adicionarListeners();
    }

    private void formatarPainel() {
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);

        painel.add(voltarButton);
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    void abreJanela() {
        JFrame frame = new JFrame("Carregar Dados Iniciais");
        frame.setSize(300, 150);
        frame.add(painel1);
        painel1.setLayout(new GridLayout(3, 1, 5, 5));
        painel1.add(digiteNome);
        painel1.add(nome);
        painel1.add(carregarButton);
        frame.setVisible(true);
    }

    private void adicionarListeners() {
        voltarButton.addActionListener(e -> voltarAplicacao());
        carregarButton.addActionListener(e -> carregarDados());
    }

    private void carregarDados() {
        try (BufferedReader entrada = new BufferedReader(new FileReader(nome.getText() + ".CSV"))) {
            String linha = entrada.readLine();
            while (linha != null){
                if (linha != null && linha.equals("cod;dataInicio;duracao;status;codigo")) {
                    while ((linha = entrada.readLine()) != null) {
                        String[] dados = linha.split(";");
                        CadastraAtendimento.cadastrarAtendimento(new Atendimento(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), dados[3], dados[4], null));
                    }
                }
                else if(linha != null && linha.equals("codinome;quantidade;latitude;longitude")) {
                    while ((linha = entrada.readLine()) != null) {
                        String[] dados = linha.split(";");
                        CadastraEquipe.cadastrarEquipe(new Equipe(dados[0], Integer.parseInt(dados[1]), Double.parseDouble(dados[2]), (Double.parseDouble(dados[3])), null, null));
                    }
                }
                else if(linha != null && linha.equals("codigo;data;latitude;longitude;tipo;velocidade_magnitude_estiagem;precipitacao")) {
                    while ((linha = entrada.readLine()) != null) {
                        String[] dados = linha.split(";");
                        switch (dados[4]) {
                            case "1":
                                CadastraEvento.cadastrarEvento(new Ciclone(dados[0], dados[1], Double.parseDouble(dados[2]), Double.parseDouble(dados[3]), Double.parseDouble(dados[5]), Double.parseDouble(dados[6])));
                                break;
                            case "2":
                                CadastraEvento.cadastrarEvento(new Terremoto(dados[0], dados[1], Double.parseDouble(dados[2]), Double.parseDouble(dados[3]), Double.parseDouble(dados[5])));
                                break;
                            case "3":
                                CadastraEvento.cadastrarEvento(new Seca(dados[0], dados[1], Double.parseDouble(dados[2]), Double.parseDouble(dados[3]), Integer.parseInt(dados[5])));
                                break;
                            default:
                                break;
                        }
                    }
                }
                else if(linha != null && linha.equals("identificador;nome;custodiario;codinome;tipo;capacidade_combustivel;carga")) {
                    while ((linha = entrada.readLine()) != null) {
                        String[] dados = linha.split(";");
                        switch (dados[4]) {
                            case "1":
                                Barco barco = new Barco(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), dados[3],Integer.parseInt(dados[5]));
                                CadastraEquipamento.cadastrarEquipamento(barco);
                                vincular(dados[3], barco);
                                break;
                            case "2":
                                CaminhaoTanque caminhaoTanque = new CaminhaoTanque(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), dados[3],Double.parseDouble(dados[5]));
                                CadastraEquipamento.cadastrarEquipamento(caminhaoTanque);
                                vincular(dados[3], caminhaoTanque);
                                break;
                            case "3":
                                Escavadeira escavadeira = new Escavadeira(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]), dados[3],dados[5], Double.parseDouble(dados[6]));
                                CadastraEquipamento.cadastrarEquipamento(escavadeira);
                                vincular(dados[3], escavadeira);
                                break;
                            default:
                                break;
                        }
                    }
                }
                else {
                    campoDeMensagens.setText("Arquivo inválido!");
                    return;
                }
            }
        }
        catch (IOException e) {
            campoDeMensagens.setText("Arquivo não encontrado!");
        }
        catch (NumberFormatException e) {
            campoDeMensagens.setText("Dados inválidos!");
        }
        catch (InputMismatchException e) {
            campoDeMensagens.setText("Dados inválidos!");
        }
    }

    private void voltarAplicacao() {
        painel.removeAll();
        painel.setLayout(new GridLayout(1, 1, 5, 5));
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        painel.add(menuPrincipal.getPainel());
        painel.revalidate();
        painel.repaint();
    }

    private void vincular(String codinome, Equipamento equipamento) {
        if(codinome == null) {
            campoDeMensagens.setText("Dados inválidos!");
            return;
        }
        for (Equipe equipe : CadastraEquipe.getEquipes()) {
            if (equipe.getEquipamentos().contains(equipamento)) {
                campoDeMensagens.setText("Equipamento já vinculado!");
                return;
            }
        }
        String equipeSelecionada = codinome;
        for (Equipe equipe : CadastraEquipe.getEquipes()) {
            if (equipe.getCodinome().equals(equipeSelecionada)) {
                if(equipe.addEquipamento(equipamento));{
                    campoDeMensagens.setText("Equipamento vinculado com sucesso!");
                    return;
                }
            }
        }
    }
}