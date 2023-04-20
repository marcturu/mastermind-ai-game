package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panel_inici extends JPanel{
    private Controlador_Presentacio CtrlPresentacio;
    private JButton b_login = new JButton("LOGIN");
    private JButton b_register = new JButton("REGISTER");
    private JLabel titol_panel = new JLabel("MASTER MIND. PROP", 0);
    private JButton b_sortir = new JButton("SORTIR");


    public void fes_visible() {
        setVisible(true);
    }

    public panel_inici(Controlador_Presentacio CtrlPresentacio) {
        this.CtrlPresentacio = CtrlPresentacio;
        setBounds(500, 300, 500, 300);
        titol_panel.setBounds(10, 5, 120, 30);
        add(titol_panel);


        b_login.setBounds(150, 50, 200, 20);
        add(b_login);

        b_register.setBounds(150, 100, 200, 20);
        add(b_register);

        b_sortir.setBounds(20, 250, 150, 20);
        add(b_sortir);


        ActionListener login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has apretat: " + ((JButton)e.getSource()).getText() + "\n");
                CtrlPresentacio.canvia_a_login();
            }
        };

        ActionListener register = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has apretat: " + ((JButton)e.getSource()).getText() + "\n");
                CtrlPresentacio.canvia_a_register();
            }
        };
        
        ActionListener sortir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };        

        b_login.addActionListener(login);
        b_register.addActionListener(register);
        b_sortir.addActionListener(sortir);      
    }
}
