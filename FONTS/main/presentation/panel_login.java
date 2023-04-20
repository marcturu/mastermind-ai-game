package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.presentation.controller.Controlador_Presentacio;

public class panel_login extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel txt_username = new JLabel("Entra nom usuari:");
    private final JTextArea username = new JTextArea("NOM USUARI");
    private final JLabel txt_password = new JLabel("Entra contrasenya:");
    private final JTextArea password = new JTextArea("CONTRASENYA");
    private final JButton b_confirmar = new JButton("CONFIRMAR");
    private final JButton b_enrere = new JButton("Back");

    private void set_up_ui() {
        txt_username.setBounds(50, 90, 200, 50);
        username.setBounds(250, 90, 200, 50);
        add(txt_username);
        add(username);

        txt_password.setBounds(50, 160, 200, 50);
        password.setBounds(250, 160, 200, 50);
        add(txt_password);
        add(password);

        b_confirmar.setBounds(300, 220, 150, 20);
        b_enrere.setBounds(20, 250, 150, 20);
        add(b_confirmar);
        add(b_enrere);
    }

    public panel_login(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();

        //falta afegir action listeners
    }

}
