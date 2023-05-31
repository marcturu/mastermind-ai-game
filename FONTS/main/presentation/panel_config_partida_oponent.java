package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.presentation.controller.Controlador_Presentacio;

public class panel_config_partida_oponent extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel l_oponent = new JLabel("Selecciona el tipus d'oponent:");
    private final JButton b_jugador2 = new JButton("Jugador 2");
    private final JButton b_maquina = new JButton("Maquina");
    private final JButton b_enrere = new JButton("Enrere");
    
    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        l_oponent.setBounds(20, 20, 200, 20);
        add(l_oponent);

        b_jugador2.setBounds(20, 50, 150, 20);
        add(b_jugador2);

        b_maquina.setBounds(20, 80, 150, 20);
        add(b_maquina);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_jugador2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.acreditar_User2();
                ctrlPresentacio.canvia_a_login();
            }
        });   

        b_maquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.user2_es_maquina();//marquem que l'usuari2 es una maquina

                boolean jugador1_es_codemaker = ctrlPresentacio.jugador1_es_codemaker();
                if(jugador1_es_codemaker) {
                    ctrlPresentacio.canvia_a_config_partida_oponent_maquina();
                }
                else ctrlPresentacio.canvia_a_config_partida_dificultat();
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.canvia_a_config_partida_rol();
            }
        });
    }
    
    /**
     * Creadora del panel de configuracio de partida oponent
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_config_partida_oponent(Controlador_Presentacio ctrlPresentacio) {
        //init de parametres
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
}
