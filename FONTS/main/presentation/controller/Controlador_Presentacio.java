package main.presentation.controller;

import main.presentation.views.*;

import java.util.List;
import main.domain.classes.types.Pair;

import main.domain.controller.*;

public class Controlador_Presentacio {
    private Controlador_Domini ctrlDomini;
    private view_inici viewInicial;

    private boolean j1_cm;
    private boolean j2_user;
    private boolean j2_maquina_genetica;
    private int dificultat;

    /**
     * Constructora de la classe
     */
    public Controlador_Presentacio() {
        ctrlDomini = new Controlador_Domini();
        viewInicial = new view_inici(this);
        
        //configuracio per defecte
        j1_cm = false;
        j2_user = false;
        j2_maquina_genetica = false;
        dificultat = 2;
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_inici
     */
    public void canvia_a_inici() {
        viewInicial.canvia_a_inici();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_login
     */
    public void canvia_a_login() {
        viewInicial.canvia_a_login();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_register
     */
    public void canvia_a_register() {
        viewInicial.canvia_a_register();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_menu_principal
     */
    public void canvia_a_menu_principal() {
        viewInicial.canvia_a_menu_principal();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_record
     */
    public void canvia_a_record() {
        viewInicial.canvia_a_record();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_ranking
     */
    public void canvia_a_ranking() {
        viewInicial.canvia_a_ranking();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_user
     */
    public void canvia_a_user() {
        viewInicial.canvia_a_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_estadistiques_user
     */
    public void canvia_a_estadistiques_user() {
        viewInicial.canvia_a_estadistiques_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_historial_user
     */
    public void canvia_a_historial_user() {
        viewInicial.canvia_a_historial_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_rol
     */
    public void canvia_a_config_partida_rol() {
        viewInicial.canvia_a_config_partida_rol();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_oponent
     */
    public void canvia_a_config_partida_oponent() {
        viewInicial.canvia_a_config_partida_oponent();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_maquina
     */
    public void canvia_a_config_partida_oponent_maquina() {
        viewInicial.canvia_a_config_partida_oponent_maquina();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_dificultat
     */
    public void canvia_a_config_partida_dificultat() {
        viewInicial.canvia_a_config_partida_dificultat();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_carregar_partida
     */
    public void canvia_a_carregar_partida() {
        viewInicial.canvia_a_carregar_partida();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_partida
     */
    public void canvia_a_partida() {
        viewInicial.canvia_a_partida();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_dificultats_ranking
     */
    public void canvia_a_dificultats_ranking() {
        viewInicial.canvia_a_dificultats_ranking();
    }

    /**
     * Funcio que crida al controlador de domini perque intenti fer login amb un usuari_persona
     * @param username Nom d'usuari_persona
     * @param password Contrasenya d'usuari_persona
     * @throws Exception Si l'usuari no existeix o la contrasenya es incorrecta
     */
    public void crida_a_login_domini(String username, String password) throws Exception{
        ctrlDomini.loginUsuari1(username, password);
    }

    /**
     * Funcio que crida al controlador de domini perque registri un usuari_persona
     * @param username Nom d'usuari_persona
     * @param password Contrasenya d'usuari_persona
     * @throws Exception Si l'usuari ja existeix
     */
    public void crida_a_register_domini(String username, String password) throws Exception {
        ctrlDomini.inicialitzaUserPersona(username, password);
    }

    /**
     * Funcio que crida al controlador de domini perque retorni una llista de parelles (id_partida, info_patida)
     * @return Llista de parelles (id_partida, info_partida)
     */
    public List<Pair<Integer, String>> carrega_partides_no_acabades() {
        ctrlDomini.carrega_partides_no_acabades();
    }

    /**
     * Funcio que crida al controlador de domini per rebre el numero de partides de l'usuari
     * @return numero de partides de l'usuari
     */
    public Integer get_total_games() {
        return ctrlDomini.get_partides_totals();
    }
    
    /**
     * Funcio que crida al controlador de domini per rebre el numero de partides guanyades de l'usuari
     * @return numero de partides guanyades de l'usuari
     */
    public Integer get_total_wins() {
        return ctrlDomini.get_partides_guanyades();
    }

    /**
     * Funcio que crida al controlador de domini per rebre el winrate de l'usuari
     * @return winrate de l'usuari
     */
    public Double get_winrate() {
        double victories = (double)ctrlDomini.get_partides_guanyades();
        double total = (double)ctrlDomini.get_partides_totals();
        return (100.0*victories)/total;
    }

    /**
     * Funcio per indicar que l'usuari vol ser codemaker de la nova partida
     */
    public void jugador_vol_ser_cm() {
        j1_cm = true;
    }

    /**
     * Funcio per indicar que l'usuari vol ser codebreaker de la nova partida
     */
    public void jugador_vol_ser_cb() {
        j1_cm = false;
    }

    /**
     * Funcio per consultar si el jugador principal es codemaker
     * @return cert si el jugador principal es codemaker, fals altrament
     */
    public boolean jugador1_es_codemaker() {
        return j1_cm;
    }

    public void set_algorisme_partida(String nom_algorisme) {
        ctrlDomini.set_algorisme_partida(nom_algorisme);
    }
}