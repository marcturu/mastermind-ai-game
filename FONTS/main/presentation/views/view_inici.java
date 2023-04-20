package main.presentation.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.presentation.*;
import main.presentation.controller.Controlador_Presentacio;

public class view_inici extends JFrame{
    private final Controlador_Presentacio ctrlPresentacio;
    private final panel_inici inici;
    private final panel_login login;
    private final panel_register register;
    private final panel_menu_principal menu_principal;
    private final panel_record record;
    private final panel_ranking ranking;
    private final panel_user user;
    private final panel_estadistiques_user estadistiques_user;
    private final panel_historial_user historial_user;
    private final panel_config_partida config_partida;
    private final panel_carregar_partida carregar_partida;
    private final panel_partida partida;
    private final panel_dificultats_ranking dificultats_ranking;
    
    private final JButton b_enrere = new JButton("Salir");

    private void init_config() {

        //init de pestanya
        setBounds(500, 300, 500, 300);
        setResizable(true);
        this.setTitle("MASTER MIND PROP");

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
        
        
    }
    
    public view_inici(Controlador_Presentacio ctrlPresentacio) {
        //init de parametres
        this.ctrlPresentacio = ctrlPresentacio;
        inici = new panel_inici(ctrlPresentacio);
        login = new panel_login(ctrlPresentacio);
        register = new panel_register(ctrlPresentacio);
        menu_principal = new panel_menu_principal(ctrlPresentacio);
        record = new panel_record(ctrlPresentacio);
        ranking = new panel_ranking(ctrlPresentacio);
        user = new panel_user(ctrlPresentacio);
        estadistiques_user = new panel_estadistiques_user(ctrlPresentacio);
        historial_user = new panel_historial_user(ctrlPresentacio);
        config_partida = new panel_config_partida(ctrlPresentacio);
        carregar_partida = new panel_carregar_partida(ctrlPresentacio);
        partida = new panel_partida(ctrlPresentacio);
        dificultats_ranking = new panel_dificultats_ranking(ctrlPresentacio);

        init_config();

        this.setContentPane(inici);
        this.invalidate();
        this.validate();


    } 

    public void canvia_a_inici() {
        this.setContentPane(inici);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_login() {
        this.setContentPane(login);
        this.invalidate();
        this.validate();
    }

    

    public void canvia_a_register() {
        this.setContentPane(register);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_menu_principal() {
        this.setContentPane(menu_principal);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_record() {
        this.setContentPane(record);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_ranking() {
        this.setContentPane(ranking);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_user() {
        this.setContentPane(user);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_partida() {
        this.setContentPane(partida);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_dificultats_ranking() {
        this.setContentPane(dificultats_ranking);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_estadistiques_user() {
        this.setContentPane(estadistiques_user);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_historial_user() {
        this.setContentPane(historial_user);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_config_partida() {
        this.setContentPane(config_partida);
        this.invalidate();
        this.validate();
    }

    public void canvia_a_carregar_partida() {
        this.setContentPane(carregar_partida);
        this.invalidate();
        this.validate();
    }
}
