package main.presentation;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.presentation.controller.Controlador_Presentacio;

public class panel_config_partida_rol extends JPanel {
    private Controlador_Presentacio ctrlPresentacio;
    private JLabel l_rol = new JLabel("SELECCIONA ROL: ");
    private JButton b_codemaker = new JButton("CODEMAKER");
    private JButton b_codebreaker = new JButton("CODEBREAKER");
    private JButton b_enrere = new JButton("BACK");

    /**
     * funcio per inicialitzar la UI del panell
     */
    private void set_up_ui() {
        l_rol.setBounds(50, 90, 200, 50);
        add(l_rol);

        b_codemaker.setBounds(50, 160, 150, 20);
        add(b_codemaker);

        b_codebreaker.setBounds(250, 160, 150, 20);
        add(b_codebreaker);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * funcio per inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_codemaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.jugador_vol_ser_cm();
                ctrlPresentacio.canvia_a_config_partida_oponent();
            }
        });

        b_codebreaker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ctrlPresentacio.jugador_vol_ser_cb();
                ctrlPresentacio.canvia_a_config_partida_oponent();
            }
        });
    }

    /**
     * Constructora del panell de configuracio de partida per triar el rol del jugador principal
     */
    public panel_config_partida_rol(Controlador_Presentacio CtrlPresentacio) {
        this.ctrlPresentacio = CtrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
}
