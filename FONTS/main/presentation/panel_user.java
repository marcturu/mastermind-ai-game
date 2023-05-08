package main.presentation;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que conté el panell del menú principal
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_user extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JButton b_estadistiques = new JButton("ESTADISTIQUES");
    private JButton b_historial = new JButton("HISTORIAL");
    private JButton b_logout = new JButton("LOGOUT");
    private JButton b_enrere = new JButton("ENRERE");

    /**
     * Funcio per a inicialitzar la UI del panell
     */
    private void set_up_ui() {
        b_estadistiques.setBounds(150, 50, 200, 20);
        add(b_estadistiques);

        b_historial.setBounds(150, 100, 200, 20);
        add(b_historial);

        b_logout.setBounds(150, 150, 200, 20);
        add(b_logout);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * Funcio per a inicialitzar els listeners del panell
     */
    private void set_up_listeners() {
        b_estadistiques.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_estadistiques_user();
            }
        });

        b_historial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_historial_user();
            }
        });

        b_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });
    }

    /**
     * Funcio per a inicialitzar la UI del panell
     */
    public panel_user(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }

    /**
     * Funcio per a fer visible el panell
     */
    public void fes_visible() {
        setVisible(true);
    }
}
