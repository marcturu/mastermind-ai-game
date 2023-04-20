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

    
    
}