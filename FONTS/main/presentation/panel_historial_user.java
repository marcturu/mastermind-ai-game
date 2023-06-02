package main.presentation;

import main.presentation.controller.Controlador_Presentacio;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.List;
import java.util.ArrayList;
import main.domain.classes.types.Pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per representar el panell que mostra el historial de partides d'un usuari
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_historial_user extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JScrollPane scrollPane = new JScrollPane();
    private JList<String> llista_info = new JList<String>();
    private JButton b_confirmar = new JButton("CONFIRMAR");
    private JButton b_enrere = new JButton("ENRERE");

    
    /**
     * funcio per inicialitzar la UI del panell
     */
    private void set_up_ui() {
        scrollPane.setBounds(50, 50, 400, 200);
        add(scrollPane);

        llista_info.setBounds(50, 50, 400, 200);
        //afegim la llista al viewport del scrollPane
        scrollPane.setViewportView(llista_info);

        b_confirmar.setBounds(300, 250, 150, 20);
        add(b_confirmar);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);

        carrega_llista();
    }

    /**
     * funcio per a inicialitzar els listeners dels components
     */
    private void set_up_listeners() {
        
        b_confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //funcio per carregar la partida que esta a l'index seleccionat
                ctrlPresentacio.carrega_partida_acabada(llista_info.getSelectedIndex());
                
                ctrlPresentacio.canvia_a_partida();
            }
        });
        
        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_user();
            }
        });

    }
    
    /**
     * Constructora de la classe panel_historial_user
     * @param ctrlPresentacio
     */
    public panel_historial_user(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }

    /**
     * funcio per carregar la llista de partides al panell
     */
    public void carrega_llista() {
        List<Pair<Integer, String>>llista = ctrlPresentacio.get_llista_partides_acabades();
        List<String> llista_string = new ArrayList<String>();
        for (Pair<Integer, String> p : llista) {
            llista_string.add(p.second());
        }
        llista_info.setListData(llista_string.toArray(new String[0]));
    }
}
