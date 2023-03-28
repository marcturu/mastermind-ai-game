package main.domain.controllers;

import main.domain.classes.Partida;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.Ronda;
import main.domain.classes.Ranking;
import main.domain.classes.Record;

import java.util.*;
import java.time.*;

/**
 * Classe del Controlador de Partida
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Controlador_Partida {
    private Partida partida_actual;


    private void tractament_victoria() {
        this.partida_actual.set_partida_acabada();

    }

    private void tractament_partida_acabada() {

    }

    public controlador_partida (){
        this.partida_actual = null;
    }

    public void start_partida_nova(int id, User codemaker, User codebreaker, dificultat dif, boolean jugador1_es_codemaker){
        this.partida_actual = new Partida(id, codemaker, codebreaker, dif, jugador1_es_codemaker);//ha de incrementar el numero de partides de l'usuari
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
        return this.partida_actual.get_num_rondes_max();
    }

    public List<Ronda> get_llista_rondes_partida_actual() {
        return this.partida_actual.get_llista_rondes();
    }

    /**
     * Funcionalitat que gestiona una ronda que se li passa com a parametre
     */
    public void jugar_ronda(Sequencia seq_int, Sequencia seq_ver) {

        partida_actual.crea_nova_ronda();
        partida_actual.set_seq_int_a_ronda_actual(seq_int);
        partida_actual.set_seq_ver_a_ronda_actual(seq_ver);
        boolean res = partida_actual.ronda_te_intentada_correcte();

        if (res) tractament_victoria();

        partida_actual.get_temps_usat(); //actualitzar el valor de temps_usat

        if (temps_excedit_partida_actual()) {//comprovem que no se'ns hagi acabat el temps
            tractament_partida_acabada();
        }
        if (get_ultima_ronda_partida_actual() + 1 <= get_num_rondes_max_partida_actual()) {//se'ns han acabat les rondes
            partida_actual.crea_nova_ronda();
        } else tractament_partida_acabada();

    }
}