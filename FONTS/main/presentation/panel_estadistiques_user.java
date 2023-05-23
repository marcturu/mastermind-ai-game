package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

/**
 * Classe del panell d'estadistiques de l'usuari
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_estadistiques_user extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JLabel l_panel = new JLabel("Estadistiques");
    private JLabel l_total_games = new JLabel("Partides jugades: " + ctrlPresentacio.get_total_games());
    private JLabel l_total_wins = new JLabel("Partides guanyades: " + ctrlPresentacio.get_total_wins());
    private JLabel l_winrate = new JLabel("Winrate: " + ctrlPresentacio.get_winrate() + "%");
    private JButton b_enrere = new JButton("Enrere");


    /**
     * Funcio per generar la UI del panel_estadistiques_user
     */
    private void set_up_ui() {
        l_panel.setBounds(20, 20, 200, 20);
        add(l_panel);

        l_total_games.setBounds(20, 50, 200, 20);
        add(l_total_games);

        l_total_wins.setBounds(20, 80, 200, 20);
        add(l_total_wins);

        l_winrate.setBounds(20, 110, 200, 20);
        add(l_winrate);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }


    /**
     * Funcio per a afegir els listeners al panel_estadistiques_user
     */
    private void set_up_listeners() {
        b_enrere.addActionListener(e -> ctrlPresentacio.canvia_a_user());
    }

    /**
     * Constructora del panell d'estadisitques de l'usuari
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_estadistiques_user(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }



}
