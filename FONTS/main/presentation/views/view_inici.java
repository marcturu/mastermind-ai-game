package main.presentation.views;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.domain.classes.types.Pair;
import main.presentation.*;
import main.presentation.controller.Controlador_Presentacio;

public class view_inici extends JFrame{
    private final Controlador_Presentacio ctrlPresentacio;
    private final panel_inici inici;
    private panel_login login;
    private panel_register register;
    private final panel_menu_principal menu_principal;
    private panel_record record;
    private panel_ranking ranking;
    private final panel_user user;
    private panel_estadistiques_user estadistiques_user;
    private panel_historial_user historial_user;
    private final panel_config_partida_rol config_partida_rol;
    private final panel_config_partida_oponent config_partida_oponent;
    private final panel_config_partida_oponent_maquina config_partida_oponent_maquina;
    private final panel_config_partida_dificultat config_partida_dificultat;
    private panel_carregar_partida carregar_partida;
    private panel_partida partida;
    private final panel_dificultats_ranking dificultats_ranking;
    
    private final JButton b_enrere = new JButton("Salir");

    /**
     * Funcio per fer la configuracio de la view
     */
    private void init_config() {

        //init de pestanya
        setBounds(500, 300, 500, 300);
        setResizable(true);
        this.setTitle("MASTER MIND PROP");

        b_enrere.setBounds(20, 250, 150, 20);
        add(b_enrere);
        
        
    }
    /**
     * Creadora de la view principal on s'aniran posant els diferents panels
     * @param ctrlPresentacio controlador de presentacio
     */
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
        config_partida_rol = new panel_config_partida_rol(ctrlPresentacio);
        config_partida_oponent = new panel_config_partida_oponent(ctrlPresentacio);
        config_partida_oponent_maquina = new panel_config_partida_oponent_maquina(ctrlPresentacio);
        config_partida_dificultat = new panel_config_partida_dificultat(ctrlPresentacio);
        partida = new panel_partida(ctrlPresentacio);
        dificultats_ranking = new panel_dificultats_ranking(ctrlPresentacio);

        init_config();

        this.setContentPane(inici);
        this.invalidate();
        this.validate();


    } 

    /**
     * Funcio per canviar al panell d'inici
     */
    public void canvia_a_inici() {
        this.setContentPane(inici);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de login
     */
    public void canvia_a_login() {
        login = new panel_login(ctrlPresentacio);
        this.setContentPane(login);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de register
     */
    public void canvia_a_register() {
        register = new panel_register(ctrlPresentacio);
        this.setContentPane(register);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de menu principal
     */
    public void canvia_a_menu_principal() {
        this.setContentPane(menu_principal);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de record
     */
    public void canvia_a_record() {
        record = new panel_record(ctrlPresentacio);
        this.setContentPane(record);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de ranking
     */
    public void canvia_a_ranking() {
        ranking = new panel_ranking(ctrlPresentacio);
        this.setContentPane(ranking);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell de user
     */
    public void canvia_a_user() {
        this.setContentPane(user);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell per a jugar una partida
     */
    public void canvia_a_partida() {
        setSize(700,1000);
        partida = new panel_partida(ctrlPresentacio);
        this.setContentPane(partida);
        this.invalidate();
        this.validate();
    }

    /**
     * FUncio per canviar al panell de seleccio de dificultats del ranking
     */
    public void canvia_a_dificultats_ranking() {
        this.setContentPane(dificultats_ranking);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell d'estadistiques de l'usuari
     */
    public void canvia_a_estadistiques_user() {
        estadistiques_user = new panel_estadistiques_user(ctrlPresentacio);
        this.setContentPane(estadistiques_user);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell que mostra l'historial de l'usuari
     */
    public void canvia_a_historial_user() {
        historial_user = new panel_historial_user(ctrlPresentacio);
        this.setContentPane(historial_user);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell per decidir quin rol te l'usuari principal
     */
    public void canvia_a_config_partida_rol() {
        this.setContentPane(config_partida_rol);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell per decidir l'oponent de l'usuari principal
     */
    public void canvia_a_config_partida_oponent() {
        this.setContentPane(config_partida_oponent);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell per decidir la maquina contra la que es juga
     * A aquest panell només s'hi accedeix si es juga com a codemkaer contra una maquina
     */
    public void canvia_a_config_partida_oponent_maquina() {
        this.setContentPane(config_partida_oponent_maquina);
        this.invalidate();
        this.validate();
    }

    /**
     * Funcio per canviar al panell per decidir la dificultat de la partida
     */
    public void canvia_a_config_partida_dificultat() {
        this.setContentPane(config_partida_dificultat);
        this.invalidate();
        this.validate();
    }
    /**
     * Funcio per canviar al panell per mostrar les partides a carregar
     */
    public void canvia_a_carregar_partida() {
        carregar_partida = new panel_carregar_partida(ctrlPresentacio);
        this.setContentPane(carregar_partida);
        this.invalidate();
        this.validate();
    }
}
