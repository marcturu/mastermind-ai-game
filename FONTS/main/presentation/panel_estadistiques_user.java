package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

import java.util.Vector;

/**
 * Classe del panell d'estadistiques de l'usuari
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_estadistiques_user extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JLabel l_panel = new JLabel("Estadistiques");
    private JLabel l_total_games = new JLabel("");
    private JLabel l_total_wins = new JLabel("");
    private JLabel l_streakF = new JLabel("");
    private JLabel l_streakN = new JLabel("");
    private JLabel l_streakD = new JLabel("");
    private JLabel l_winrate = new JLabel("");
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

        l_streakF.setBounds(20, 140, 200, 20);
        add(l_streakF);

        l_streakN.setBounds(20, 170, 200, 20);
        add(l_streakN);

        l_streakD.setBounds(20, 200, 200, 20);
        add(l_streakD);

        b_enrere.setBounds(20, 300, 150, 20);
        add(b_enrere);
    }


    /**
     * Funcio per a afegir els listeners al panel_estadistiques_user
     */
    private void set_up_listeners() {
        b_enrere.addActionListener(e -> ctrlPresentacio.canvia_a_user());
    }


    private void set_up_labels(){

        l_total_games.setText("Partides jugades: " + ctrlPresentacio.get_total_games());
        l_total_wins.setText("Partides guanyades: " + ctrlPresentacio.get_total_wins());
        l_winrate.setText("Winrate: " + ctrlPresentacio.get_winrate() + "%");
        l_streakF.setText("Ratxa partides fàcils: " + ctrlPresentacio.get_streakF());
        l_streakN.setText("Ratxa partides normals " + ctrlPresentacio.get_streakN());
        l_streakD.setText("Ratxa partides difícils: " + ctrlPresentacio.get_streakD());

    }

    /**
     * Constructora del panell d'estadisitques de l'usuari
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_estadistiques_user(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_labels();
        set_up_ui();
        set_up_listeners();
    }



}
