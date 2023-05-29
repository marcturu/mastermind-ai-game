package main.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.presentation.controller.Controlador_Presentacio;

public class panel_record extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private JScrollPane scrollPane = new JScrollPane();
    private JList<String> llista_records = new JList<String>();
    private final JButton b_enrere = new JButton("Enrere");

    /**
     * Funcio per fer la configuracio de la UI del panell
     */
    private void set_up_ui() {
        scrollPane.setBounds(20, 20, 200, 200);
        add(scrollPane);

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);

        scrollPane.setViewportView(llista_records);
    }

    /**
     * Funcio per a inicialitzar els listeners dels botons
     */
    private void set_up_listeners() {
        b_enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacio.canvia_a_menu_principal();
            }
        });
    }

    /**
     * Constructora de la classe
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_record(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        List<String> llista_info_records = ctrlPresentacio.get_info_records();
        llista_records.setListData(llista_info_records.toArray(new String[llista_info_records.size()]));
        set_up_ui();
        set_up_listeners();
    }
}
