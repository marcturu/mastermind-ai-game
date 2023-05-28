package main.presentation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.presentation.controller.Controlador_Presentacio;

/**
 * Classe que conté el panell del menú principal 
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
*/

public class panel_login extends JPanel{
    private Controlador_Presentacio ctrlPresentacio;
    private final JLabel txt_username = new JLabel("Entra nom usuari:");
    private final JTextArea username = new JTextArea("NOM USUARI");
    private final JLabel txt_password = new JLabel("Entra contrasenya:");
    private final JTextArea password = new JTextArea("CONTRASENYA");
    private final JButton b_confirmar = new JButton("CONFIRMAR");
    private final JButton b_enrere = new JButton("Back");

    /**
     * funcio per inicialitzar la UI del panell
     */
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

    
    /**
     * funcio per a comprobar que s'han entrat els camps necessaris
     * @return true si s'han entrat els camps necessaris, false altrament
     */
    private boolean input_entrat() {
        if(password.getText().equals("") || username.getText().equals("")) {
            System.out.println("Has d'entrar un username i una password\n");
            return false;
        }
        return true;
    }

    /**
     * funcio per inicialitzar els listeners del panell
     */
    private void set_up_listeners() {
        b_confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = ((JButton) e.getSource()).getText();
                System.out.println("Has apretat: " + text);
                if(input_entrat()) {
                    try{
                    ctrlPresentacio.crida_a_login_domini(username.getText(), password.getText());
                    if(ctrlPresentacio.es_usuari1())ctrlPresentacio.canvia_a_menu_principal();
                    else ctrlPresentacio.canvia_a_config_partida_dificultat();
                    }catch(Exception ex) {
                        System.out.println("L'usuari no existeix, registrat o comprova que hagis entrat bé les credencials");
                    }
                }
            }
        });

        b_enrere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = ((JButton) e.getSource()).getText();
                System.out.println("Has apretat: " + text);
                ctrlPresentacio.canvia_a_inici();
            }

        });
    }

    /**
     * creadora del panell de login
     * @param ctrlPresentacio controlador de presentacio
     */
    public panel_login(Controlador_Presentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        set_up_ui();
        set_up_listeners();
    }
}
