package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que conté el panell del menú principal
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_inici extends JPanel{
    private Controlador_Presentacio CtrlPresentacio;
    private JButton b_login = new JButton("LOGIN");
    private JButton b_register = new JButton("REGISTER");
    private JLabel titol_panel = new JLabel("MASTER MIND. PROP", 0);
    private JButton b_sortir = new JButton("SORTIR");

    
    /**
     * Funcio per a inicialitzar la UI del panell
     */
    private void set_up_ui() {
        titol_panel.setBounds(10, 5, 120, 30);
        add(titol_panel);


        b_login.setBounds(150, 50, 200, 20);
        add(b_login);

        b_register.setBounds(150, 100, 200, 20);
        add(b_register);

        b_sortir.setBounds(20, 250, 150, 20);
        add(b_sortir);
    }

    /**
     * Funcio per a inicialitzar els listeners del panell
     */
    private void set_up_listeners() {
        b_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.canvia_a_login();
            }
        });

        b_register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.canvia_a_register();
            }
        });

        b_sortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Funcio creadora del panell
     * @param CtrlPresentacio
     */
    public panel_inici(Controlador_Presentacio CtrlPresentacio) {
        this.CtrlPresentacio = CtrlPresentacio;
        setBounds(500, 300, 500, 300);
        set_up_ui();
        set_up_listeners();    
    }

    /**
     * Funció que fa visible el panell
     */
    public void fes_visible() {
        setVisible(true);
    }
}
