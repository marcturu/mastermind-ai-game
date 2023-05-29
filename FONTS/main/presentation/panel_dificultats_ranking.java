package main.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.presentation.controller.Controlador_Presentacio;

public class panel_dificultats_ranking extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JButton b_facil = new JButton("Ranking Dificultat Facil");
    private JButton b_normal = new JButton("Ranking Dificultat Normal");
    private JButton b_dificil = new JButton("Ranking Dificultat Dificil");
    private JButton b_enrere = new JButton("Enrere");

    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        b_facil.setBounds(20, 20, 200, 20);
        add(b_facil);

        b_normal.setBounds(20, 50, 200, 20);
        add(b_normal);

        b_dificil.setBounds(20, 80, 200, 20);
        add(b_dificil);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_facil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_ranking("facil");
            }
        });

        b_normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_ranking("normal");
            }
        });

        b_dificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_ranking("dificil");
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });
    }

    /**
     * Creador del panell de dificultats per triar el ranking
     * @param CtrlPresentacio Controlador de presentació
     */
    public panel_dificultats_ranking(Controlador_Presentacio CtrlPresentacio) {
        ctrlPresentacio = CtrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
    
}
