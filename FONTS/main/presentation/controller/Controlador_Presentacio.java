package main.presentation.controller;

import main.presentation.views.*;
import main.domain.controller.*;

public class Controlador_Presentacio {
    private Controlador_Domini ctrlDomini;
    private view_inici viewInicial;

    public Controlador_Presentacio() {
        ctrlDomini = new Controlador_Domini();
        viewInicial = new view_inici(this);
    }

    public void canvia_a_inici() {
        viewInicial.canvia_a_inici();
    }

    public void canvia_a_login() {
        viewInicial.canvia_a_login();
    }

    public void canvia_a_register() {
        viewInicial.canvia_a_register();
    }

    public void canvia_a_menu_principal() {
        viewInicial.canvia_a_menu_principal();
    }

    public void canvia_a_record() {
        viewInicial.canvia_a_record();
    }

    public void canvia_a_ranking() {
        viewInicial.canvia_a_ranking();
    }

    public void canvia_a_user() {
        viewInicial.canvia_a_user();
    }

    public void canvia_a_estadistiques_user() {
        viewInicial.canvia_a_estadistiques_user();
    }

    public void canvia_a_historial_user() {
        viewInicial.canvia_a_historial_user();
    }

    public void canvia_a_config_partida() {
        viewInicial.canvia_a_config_partida();
    }

    public void canvia_a_carregar_partida() {
        viewInicial.canvia_a_carregar_partida();
    }

    public void canvia_a_partida() {
        viewInicial.canvia_a_partida();
    }

    public void canvia_a_dificultats_ranking() {
        viewInicial.canvia_a_dificultats_ranking();
    }

    public void crida_a_login_domini(String username, String password) throws Exception{
        ctrlDomini.loginUsuari1(username, password);
    }

    public void crida_a_register_domini(String username, String password) throws Exception {
        ctrlDomini.register(username, password);
    }
    
}