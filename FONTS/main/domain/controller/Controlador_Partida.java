package main.domain.controllers;

import main.domain.classes.Partida;

import java.util.*;

/**
 * Classe del Controlador de Partida
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class controlador_partida {
    private Partida partida_actual;

    public controlador_partida (){
        this.partida_actual = null;
    }

    public void start_partida_nova(int id, User codemaker, User codebreaker, dificultat dif) throws exception{
        if(this.partida_actual == null) {
            throw new Exception("Ja tens una partida començada amb id: " + this.partida_actual.get_id());
        }else {
            this.partida_actual = new Partida(id, codemaker, codebreaker, dif);
            this.partida_actual.crea_nova_ronda();
        }
    }

    public User get_codemaker_partida_actual() {
        return this.partida_actual.get_codemaker();
    }

    public User get_codebraker_partida_actual() {
        return this.partida_actual.get_codebraker();
    }

    public int get_id_partida_actual() {
        return this.partida_actual.get_id();
    }

    public boolean get_ajuda_partida() {
        return this.partida_actual.get_ajuda();
    }

    public void set_ajuda() throws exception{
        this.partida_actual.set_ajuda();
    }

    public boolean temps_excedit_partida_actual() {
        return this.partida_actual.temps_excedit();
    }

    public int get_num_colors_partida_actual() {
        return this.partida_actual.get_num_colors();
    }

    public int get_num_rondes_max_partida_actual() {
        return this.partida_actual.get_num_rondes_max;
    }

    public List get_llista_rondes_partida_actual() {
        return this.partida_actual.get_llista_rondes();
    }

}