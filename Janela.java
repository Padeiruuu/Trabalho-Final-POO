import javax.swing.*;

import Dados.*;

public class Janela extends JFrame{
    private Menu menu;
    
    public Janela(){
        menu = new Menu();
        this.add(menu.getPainel());
        this.setTitle("Trabalho Final - POO");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setVisible(true);
    }
}
