package main.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

public class panel_config_partida_oponent_maquina extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel l_maquina = new JLabel("Selecciona l'algorisme de la maquina:");
    private final JButton b_five_guess = new JButton("Five Guess");
    private final JButton b_genetic = new JButton("Genetic");
    private final JButton b_enrere = new JButton("Enrere");

    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        l_maquina.setBounds(20, 20, 200, 20);
        add(l_maquina);

        b_five_guess.setBounds(20, 50, 150, 20);
        add(b_five_guess);

        b_genetic.setBounds(30, 50, 150, 20);
        add(b_genetic);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_five_guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.set_algorisme_partida("Five-Guess");
                ctrlPresentacio.canvia_a_partida();
            }
        });

        b_genetic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.set_algorisme_partida("Genetic");
                ctrlPresentacio.canvia_a_partida();
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_config_partida_oponent();
            }
        });
    }

    /**
     * Creadora del panell de configuracio de partida amb oponent maquina
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_config_partida_oponent_maquina(Controlador_Presentacio ctrlPresentacio) {
        //init de parametres
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
}
