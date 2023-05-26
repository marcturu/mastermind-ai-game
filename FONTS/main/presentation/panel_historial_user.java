package main.presentation;

import main.presentation.controller.Controlador_Presentacio;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    /**
     * funcio per a inicialitzar els listeners dels components
     */
    private void set_up_listeners() {
        
        b_confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //funcio per carregar la partida que esta a l'index seleccionat
                ctrlPresentacio.carrega_partida(llista_info.getSelectedIndex());
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
     * @param llista
     * REVISAR!!!!
     */
    public void carrega_llista(String[] llista) {
        llista_info.setListData(llista);
    }

}
