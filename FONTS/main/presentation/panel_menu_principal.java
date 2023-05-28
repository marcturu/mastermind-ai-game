package main.presentation;

import main.presentation.controller.Controlador_Presentacio;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que conté el panell del menú principal
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_menu_principal extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JButton b_jugar = new JButton("JUGAR");
    private final JButton b_carrega_partida = new JButton("CARREGA PARTIDA");
    private final JButton b_record = new JButton("RECORD");
    private final JButton b_ranking = new JButton("RANKING");
    private final JButton b_user = new JButton("USER");
    private final JButton b_enrere = new JButton("Back");

    
    /**
     * Funció que inicialitza els botons i els afegeix al panell
     */
    private void set_up_ui() {
        b_jugar.setBounds(300, 90, 150, 20);
        add(b_jugar);
        
        b_carrega_partida.setBounds(300, 120, 150, 20);
        add(b_carrega_partida);
        
        b_record.setBounds(300, 150, 150, 20);
        add(b_record);
        
        b_ranking.setBounds(300, 180, 150, 20);
        add(b_ranking);
        
        b_user.setBounds(300, 210, 150, 20);
        add(b_user);
        
        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
    }

    /**
     * Funció que assigna els listeners als botons
     */
    private void set_up_listeners() {
        b_jugar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_config_partida_rol();
            }
        });

        b_carrega_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_carregar_partida();
            }
        });

        b_record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_record();
            }
        });

        b_ranking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_ranking();
            }
        });

        b_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_user();            
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_inici();
            }
        });
    }

    /**
     * Constructora que inicialitza el panell
     * @param ctrlPresentacio
     */
    public panel_menu_principal(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
}