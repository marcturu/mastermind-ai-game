package main.presentation;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import main.presentation.controller.Controlador_Presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Classe per representar el panell per carregar partides guardades
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */


public class panel_carregar_partida extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JScrollPane scrollPane = new JScrollPane();
    private JList<String> llista_partides = new JList<String>();
    private final JButton b_enrere = new JButton("Enrere");


    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        scrollPane.setBounds(20, 20, 200, 200);
        add(scrollPane);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);

        scrollPane.setViewportView(llista_partides);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        llista_partides.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // Agafem l'index seleccionat de la llista
                    int selectedIndex = llista_partides.getSelectedIndex();

                    // Carreguem la partida seleccionada
                    ctrlPresentacio.carrega_partida_actual(selectedIndex);

                    // Canviem a la vista de la partida
                    ctrlPresentacio.canvia_a_partida();
                }
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
     * Constructora del panell
     * @param CtrlPresentacio controlador de presentacio
     */
    public panel_carregar_partida(Controlador_Presentacio CtrlPresentacio) {
        ctrlPresentacio = CtrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
    
}
