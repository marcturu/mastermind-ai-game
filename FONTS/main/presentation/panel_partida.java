package main.presentation;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import main.presentation.classes.*;
import main.presentation.controller.Controlador_Presentacio;
public class panel_partida extends JPanel{
    private Controlador_Presentacio CtrlPresentacio;
    private  JButton[] buttons_intentada = new button[4];
    private  JButton[] buttons_verificacio = new JButton[4];
    private final JButton b_help = new JButton("Ajuda");
    private final JButton b_try = new JButton("Try");
    private final JButton b_guardar_partida = new JButton("Guardar partida");
    private final JButton b_eliminar_partida = new JButton ("Eliminar partida");

    private void add_intentada(){
        int x = 10;
        for (int i = 0; i < 4; ++i){
            buttons_intentada[i] = new button(1,false, 11/*CtrlPresentacio.get_num_colors()*/);
            buttons_intentada[i].setBounds(x, 10, 25, 25);
            add(buttons_intentada[i]);
            x += 30;
        }
    }

    private void set_up_ui(){
        b_try.setBounds(260, 0, 25, 25);
        add(b_try);
        b_help.setBounds(290, 0, 25, 25);
        add(b_help);
        b_guardar_partida.setBounds(320, 0, 25, 25);
        add(b_guardar_partida);
        b_eliminar_partida.setBounds(350, 0, 25, 25);
        add(b_eliminar_partida);
        add_intentada();
    }

    /**
     * Funcio per a inicialitzar els listeners del panell
     */
    private void set_up_listeners() {
        b_try.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        b_help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //CtrlPresentacio.ctrlDomini.set_ajuda_partida();
                //penalitzar user
            }
        });

        b_guardar_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //CtrlPresentacio.ctrlDomini.guardar_partida_a_mitges();
                CtrlPresentacio.canvia_a_menu_principal();
            }
        });

        b_eliminar_partida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //elimina partida
                CtrlPresentacio.canvia_a_menu_principal();
            }
        });
    }

    public panel_partida(Controlador_Presentacio CtrlPresentacio){
        this.CtrlPresentacio = CtrlPresentacio;
        setLayout(null);
        setBounds(500, 300, 700, 500);
        set_up_ui();
        set_up_listeners();
    }

}
