package main.presentation;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;

import main.presentation.classes.*;
import main.presentation.controller.Controlador_Presentacio;
public class panel_partida extends JPanel{
    private Controlador_Presentacio CtrlPresentacio;
    private  JButton[][] buttons_intentada = new JButton[10][4];
    private  JButton[][] buttons_verificacio = new JButton[10][4];
    private button[] buttons_col = new button[4];
    private final JButton b_help = new JButton("Ajuda");
    private final JButton b_try = new JButton("Try");
    private final JButton b_guardar_partida = new JButton("Guardar partida");
    private final JButton b_eliminar_partida = new JButton ("Eliminar partida");

    private void add_panel_rondes(){
        for (int i = 0; i < buttons_intentada.length; ++i){
            int x = 10;
            int y = 900;
            for (int j = 0; j < buttons_intentada[0].length; ++j){
                buttons_intentada[i][j] = new JButton();
                buttons_intentada[i][j].setBounds(x, y, 75, 75);
                buttons_intentada[i][j].setBackground(null);
                add(buttons_intentada[i][j]);
                x += 30;
            }
            y -= 30;
        }

        for (int i = 0; i < buttons_verificacio.length; ++i){
            int x = 170;
            int y = 900;
            for (int j = 0; j < buttons_verificacio[0].length; ++j){
                buttons_verificacio[i][j] = new JButton();

                buttons_verificacio[i][j].setBounds(x, y, 75, 75);
                buttons_verificacio[i][j].setBackground(null);
                add(buttons_verificacio[i][j]);
                x += 30;
            }
            y -= 30;
        }
    }

    private void add_buttons_colors(){

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
        add_panel_rondes();
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
        set_up_ui();
        set_up_listeners();
    }

}
