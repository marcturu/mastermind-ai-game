package main.presentation;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.presentation.controller.Controlador_Presentacio;

/**
 * Classe per representar el panell de registre d'usuari
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class panel_register extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel txt_username = new JLabel("Entra nom usuari:");
    private final JTextArea username = new JTextArea("NOM USUARI");
    private final JLabel txt_password1 = new JLabel("Entra contrasenya:");
    private final JTextArea password1 = new JTextArea("CONTRASENYA");
    private final JLabel txt_password2 = new JLabel("Entra contrasenya:");
    private final JTextArea password2 = new JTextArea("CONTRASENYA");
    private final JButton b_confirmar = new JButton("CONFIRMAR");
    private final JButton b_enrere = new JButton("Back");


    private void set_up_ui() {
        txt_username.setBounds(50, 60, 200, 50);
        username.setBounds(250, 60, 200, 50);
        add(txt_username);
        add(username);

        txt_password1.setBounds(50, 120, 200, 50);
        password1.setBounds(250, 120, 200, 50);
        add(txt_password1);
        add(password1);

        txt_password2.setBounds(50, 180, 200, 50);
        password2.setBounds(250, 180, 200, 50);
        add(txt_password2);
        add(password2);

        b_confirmar.setBounds(300, 220, 150, 20);
        b_enrere.setBounds(20, 250, 150, 20);
        add(b_confirmar);
        add(b_enrere);
    }

    private boolean password_igual_confirmacio() {
        return password1.getText().equals(password2.getText());
    }

    private void actionPerformed_BotoConfirmar(ActionEvent event) {
        boolean es_pot_registrar = false;
        if(username.getText().equals("") || password1.getText().equals("") || password2.getText().equals("")) {
            String error = "Entra un nom d'usuari i una contrasenya amb la seva confirmacio";
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!password_igual_confirmacio()) {
            String error = "La contrasenya no coincideix amb la confirmacio";
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            if (!ctrlPresentacio.crida_a_register_domini(username.getText(), password1.getText())) {
                String error = "Ja existeix l'usuari";
                JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);

            }
            else es_pot_registrar = true;

            if (es_pot_registrar) {
                if (ctrlPresentacio.es_usuari1()) ctrlPresentacio.canvia_a_menu_principal();
                else ctrlPresentacio.canvia_a_config_partida_dificultat();
            }
        }

    }

    public panel_register(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();

        b_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String text = ((JButton) event.getSource()).getText();
                System.out.println("Has apretat: " + text);
                actionPerformed_BotoConfirmar(event);
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String text = ((JButton) event.getSource()).getText();
                System.out.println("Has apretat: " + text);
                ctrlPresentacio.canvia_a_inici();
            }

        });
    }
    
}
