import javax.swing.*;

import Dados.*;

public class Janela extends JFrame{
    private MenuPrincipal menuPrincipal;
    
    public Janela(){
        menuPrincipal = new MenuPrincipal();
        this.add(menuPrincipal.getPainel());
        this.setTitle("Trabalho Final - POO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setVisible(true);
    }
}