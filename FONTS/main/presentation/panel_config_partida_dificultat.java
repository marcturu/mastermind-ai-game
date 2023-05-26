package main.presentation;

import main.presentation.controller.Controlador_Presentacio;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panel_config_partida_dificultat extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel l_dificultat = new JLabel("Selecciona el tipus de dificultat:");
    private final JButton b_facil = new JButton("Facil");
    private final JButton b_mitja = new JButton("Mitja");
    private final JButton b_dificil = new JButton("Dificil");

    private final JButton b_enrere = new JButton("Enrere");

    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        l_dificultat.setBounds(20, 20, 150, 20);
        add(l_dificultat);

        b_facil.setBounds(20, 50, 150, 20);
        add(b_facil);

        b_mitja.setBounds(20, 80, 150, 20);
        add(b_mitja);

        b_dificil.setBounds(20, 100, 150, 20);
        add(b_dificil);

        b_enrere.setBounds(20, 250, 200, 20);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_facil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.assigna_dificultat_facil();
                ctrlPresentacio.canvia_a_partida();
            }
        });

        b_mitja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.assigna_dificultat_mitja();
                ctrlPresentacio.canvia_a_partida();
            }
        });
        b_dificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.assigna_dificultat_dificil();
                ctrlPresentacio.canvia_a_partida();
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.canvia_a_config_partida_oponent_maquina();
            }
        });

    }

    /**
     * Creadora del panel de configuracio de partida dificultat
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_config_partida_dificultat(Controlador_Presentacio ctrlPresentacio) {
        //init de parametres
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }

    
}
