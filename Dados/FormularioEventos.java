package Dados;
import javax.swing.*;

import Dados.Eventos.CadastraEvento;
import Dados.Eventos.Ciclone;
import Dados.Eventos.Seca;
import Dados.Eventos.Terremoto;

import java.awt.*;

public class FormularioEventos {
    private JTextArea campoDeMensagens = new JTextArea();
    private JPanel painel = new JPanel();
    private JPanel painel1 = new JPanel();
    private JPanel painel2 = new JPanel();
    private JLabel digiteCodigo = new JLabel("Digite o Código:");
    private JLabel digiteData = new JLabel("Digite a Data:");
    private JLabel digiteLongitude = new JLabel("Digite a Longitude:");
    private JLabel digiteLatitude = new JLabel("Digite a Latitude:");
    private JTextField codigo = new JTextField();
    private JTextField data = new JTextField();
    private JTextField longitude = new JTextField();
    private JTextField latitude = new JTextField();
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton limparButton = new JButton("Limpar");
    private JButton dadosButton = new JButton("Mostrar Dados");
    private JButton VoltarButton = new JButton("Voltar");
    private JLabel tipoLabel = new JLabel("Selecione o tipo de evento:");
    private JComboBox<String> selectType = new JComboBox<>();
    private JLabel digiteMagnitude;
    private JTextField magnitude = new JTextField();
    private JLabel digiteEstiagem;
    private JTextField estiagem = new JTextField();
    private JLabel digiteVelocidade;
    private JTextField velocidade = new JTextField();
    private JLabel digitePrecipitacao;
    private JTextField precipitacao = new JTextField();
    private JScrollPane scroll = new JScrollPane(campoDeMensagens);

    public JPanel getPainel() {
        return painel;
    }

    public FormularioEventos() {
        formatarPainel();
        selectType.addItem("Ciclone");
        selectType.addItem("Terremoto");
        selectType.addItem("Seca");
        selectType.setSelectedItem(null);
        adicionarListeners();
    }

    private void formatarPainel() {
        painel1.removeAll();
        if (selectType.getSelectedItem() == "Ciclone") {
            painel1.setLayout(new GridLayout(9, 2, 5, 5));
        } else if (selectType.getSelectedItem() == "Terremoto") {
            painel1.setLayout(new GridLayout(8, 2, 5, 5));
        } else if (selectType.getSelectedItem() == "Seca") {
            painel1.setLayout(new GridLayout(8, 2, 5, 5));
        } else {
            painel1.setLayout(new GridLayout(7, 2, 5, 5));
        }
        painel.setLayout(new GridLayout(2, 1, 5, 5));
        painel2.setLayout(new GridLayout(1, 1, 5, 5));

        painel1.add(digiteCodigo);
        painel1.add(codigo);

        painel1.add(digiteData);
        painel1.add(data);

        painel1.add(digiteLongitude);
        painel1.add(longitude);

        painel1.add(digiteLatitude);
        painel1.add(latitude);

        painel1.add(tipoLabel);

        painel1.add(selectType);

        if (selectType.getSelectedItem() == "Ciclone") {
            digiteVelocidade = new JLabel("Digite a velocidade do ciclone:");
            painel1.add(digiteVelocidade);
            painel1.add(velocidade);
            digitePrecipitacao = new JLabel("Digite o nível de precipitação:");
            painel1.add(digitePrecipitacao);
            painel1.add(precipitacao);
        } else if (selectType.getSelectedItem() == "Terremoto") {
            digiteMagnitude = new JLabel("Digite a magnitude do terremoto:");
            painel1.add(digiteMagnitude);
            painel1.add(magnitude);
        } else if (selectType.getSelectedItem() == "Seca") {
            digiteEstiagem = new JLabel("Digite o nível de estiagem:");
            painel1.add(digiteEstiagem);
            painel1.add(estiagem);
        }

        painel1.add(cadastrarButton);
        painel1.add(limparButton);

        painel1.add(dadosButton);
        painel1.add(VoltarButton);

        painel2.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        campoDeMensagens.setEditable(false);

        painel.add(painel1);
        painel.add(painel2);

        painel.revalidate();
        painel.repaint();
        }

    private void adicionarListeners() {
        cadastrarButton.addActionListener(e -> cadastraEvento());
        limparButton.addActionListener(e -> limparCampos());
        dadosButton.addActionListener(e -> mostrarDadosCadastrados());
        VoltarButton.addActionListener(e -> voltarAplicacao());
        selectType.addActionListener(e -> selecionaTipo());
    }

    private void selecionaTipo() {
        String tipo = (String) selectType.getSelectedItem();
        if (tipo != null) {
            formatarPainel();
        }
    }

    private void cadastraEvento() {
        try {
            String tipo = (String) selectType.getSelectedItem();
            String codigoEvento = codigo.getText();
            String dataEvento = data.getText();
            double latitudeEvento = Double.parseDouble(latitude.getText());
            double longitudeEvento = Double.parseDouble(longitude.getText());
            switch(tipo){
                case "Ciclone":
                    double velocidadeCiclone = Double.parseDouble(velocidade.getText());
                    double precipitacaoCiclone = Double.parseDouble(precipitacao.getText());
                    if(CadastraEvento.cadastrarEvento(new Ciclone(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, velocidadeCiclone, precipitacaoCiclone))) {
                        campoDeMensagens.setText("Ciclone cadastrado com sucesso!");
                    } else {
                        campoDeMensagens.setText("Código já cadastrado!");
                    }
                    break;
                case "Terremoto":
                    double magnitudeTerremoto = Double.parseDouble(magnitude.getText());
                    if(CadastraEvento.cadastrarEvento(new Terremoto(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, magnitudeTerremoto))) {
                        campoDeMensagens.setText("Terremoto cadastrado com sucesso!");
                    } else {
                        campoDeMensagens.setText("Código já cadastrado!");
                    }
                    break;
                case "Seca":
                    int estiagemSeca = Integer.parseInt(estiagem.getText());
                    if(CadastraEvento.cadastrarEvento(new Seca(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, estiagemSeca))) {
                        campoDeMensagens.setText("Seca cadastrada com sucesso!");
                    } else {
                        campoDeMensagens.setText("Código já cadastrado!");
                    }
                    break;
                default:
                    campoDeMensagens.setText("Selecione um tipo de evento!");
                    break;
            }
        } catch (NumberFormatException n) {
            campoDeMensagens.setText("Dados inválidos! Verifique os campos novamente.");
        } catch (Exception e){
            campoDeMensagens.setText("Erro ao cadastrar equipamento!");
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

    private void mostrarDadosCadastrados() {
        campoDeMensagens.setText(CadastraEvento.obterTextoEventos());
    }

    private void limparCampos() {
        campoDeMensagens.setText("");
        data.setText("");
        codigo.setText("");
        longitude.setText("");
        latitude.setText("");
        selectType.setSelectedItem(null);
        formatarPainel();
    }
}