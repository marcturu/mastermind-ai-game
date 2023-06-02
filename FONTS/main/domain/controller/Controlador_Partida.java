package main.domain.controller;

//import java.time.*;
import java.util.List;

import main.domain.classes.Partida;
import main.domain.classes.Ronda;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.User;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.types.Pair;

/**
 * Classe del Controlador de Partida
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Controlador_Partida {
    private Partida partida_actual;


    /**
     * Creadora de la classe controlador Partida
     */
    public Controlador_Partida (){
        this.partida_actual = null;
    }


    /**
     * Aquesta funció serveix per fer el tractament en cas que el codebreaker encerti la sequencia. Avisa a partida_actual
     * perque aquesta avisi als usuaris del tractament pertinent
     */
    public void tractament_victoria() {
        this.partida_actual.codebreaker_guanya();
       // this.partida_actual = null; provoca fallades

    }

    /**
     * Aquesta funció serveix per fer el tractament en cas que al codebreaker se li acabin les rondes o el temps. Avisa a partida_actual
     * perque aquesta avisi als usuaris del tractament pertinent
     */
    public void tractament_partida_acabada() {
        this.partida_actual.codemaker_guanya();
        //this.partida_actual = null; provaca fallades
    }

    /**
     * Setter de la partida que es comença a jugar
     * @param partida_nova
     */
    public void set_partida_actual(Partida partida_nova) {
        this.partida_actual = partida_nova;
    }

    /**
     * @return User que te com a rol codemaker
     */
    public User get_codemaker_partida_actual() {
        return this.partida_actual.get_codemaker();
    }

    /**
     * @return User que te com a rol codebreaker
     */
    public User get_codebreaker_partida_actual() {
        return this.partida_actual.get_codebreaker();
    }

    /**
     * @return si el jugador1 es el codemaker de la partida
     */
    public boolean get_jugador1_es_codemaker_partida_actual() {
        return this.partida_actual.get_jugador1_es_codemaker();
    }

    /**
     * @return Id de la partida que s'esta jugant
     */
    public int get_id_partida_actual() {
        return this.partida_actual.get_id();
    }

    /**
     * @return si s'ha fet servir ajuda o no
     */
    public boolean get_ajuda_partida() {
        return this.partida_actual.get_ajuda();
    }

    /**
     * @throws AjudaJaDemanada
     * Demana ajuda al sistema, es llenca AjudaJaDemanada si ja ha demanat ajuda previament
     */
    public void set_ajuda(){
        try{
            this.partida_actual.set_ajuda();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**Setter de la matriu que ens retorna la maquina per guardar-ho a Controlador_Partida
     * @param sol
     */
    public void set_solucio_partida_actual(List<List<Integer>> sol) {
        partida_actual.set_solucio_maquina(sol);
    }

    /**
     * @return el guess de la maquina a la ronda "num_ronda".
     */
    public List<Integer> get_guess_maquina() {
        return partida_actual.get_next_guess_maquina();
    }

    /**
     * @return si s'ha excedit el temps limit que te la partida
     */
    public boolean temps_excedit_partida_actual() {
        return this.partida_actual.temps_excedit();
    }

    /**
     * @return el numero de colors que es poden fer servir a la partida
     */
    public int get_num_colors_partida_actual() {
        return this.partida_actual.get_num_colors();
    }

    /**
     * @return el numero maxim de rondes que es poden fer servir
     */
    public int get_num_rondes_max_partida_actual() {
        return this.partida_actual.get_num_rondes_max();
    }

    /**
     * @return llista de rondes que s'han jugat a una partida
     */
    public List<Ronda> get_llista_rondes_partida_actual() {
        return this.partida_actual.get_llista_rondes();
    }


    /**
     * @return el numero de la ultima ronda de la partida actual
     */
    public Integer get_ultima_ronda_partida_actual() {
        return this.partida_actual.get_ultima_ronda();
    }

    /**
     * @return la sequencia que es solucio de la partida que s'esta jugant
     */
    public Sequencia_intentada get_seq_solucio_partida_actual() {
        return this.partida_actual.get_solucio();
    }

    /**
     * Funcio per obtenir la sequencia de verificacio de la ultima ronda jugada
     * @return sequencia de verificacio
     */
    public List<Integer> get_seq_verificacio_ultima_ronda() {
        return this.partida_actual.get_seq_ver_de_ultima_ronda().toListInteger();
    }

    /**
     * Funcio per obtenir la sequencia intentada de la ultima ronda jugada
     * @return sequencia intentada
     */
    public Sequencia_intentada get_seq_intentada_ultima_ronda() {
        return this.partida_actual.get_seq_int_de_ultima_ronda();
    }

    /**
     * Funcio per settejar la sequencia solucio de la partida que s'esta jugant
     * @param sol sequencia solucio
     */
    public void set_seq_solucio_entrada_per_user(Sequencia_intentada sol) {
        this.partida_actual.set_sequencia_solucio(sol);
    }

    /**
     * Es genera una sequencia solucio random i es guarda dins de Partida
     * @param seq_sol
     */
    public void genera_solucio_partida(dificultats dif) {
        this.partida_actual.genera_sequencia_solucio_random(dif);
    }

    /**
     * Funcio per a que es crei una nova ronda
     */
    public void crea_nova_ronda() {
        partida_actual.crea_nova_ronda();
    }

    /**
     * Setter de la sequencia intentada a la ultima ronda jugada
     * @param seq_int sequencia que s'intenta
     */
    public void set_sequencia_intentada(Sequencia_intentada seq_int) {
        partida_actual.set_seq_int_a_ronda_actual(seq_int);
    }

    /**
     * Setter de la sequencia de verificacio a la ultima ronda jugada
     * @param seq_ver sequencia que verifica la sequencia intentada
     */
    public void set_sequencia_verificacio(Sequencia_verificacio seq_ver) {
        partida_actual.set_seq_ver_a_ronda_actual(seq_ver);
    }

    /**
     * Consultora de si en l'ultima ronda s'ha encertat la sequencia.
     * @return si s'ha encertat la sequencia
     */
    public boolean comprova_resultat() {
        return partida_actual.ronda_te_intentada_correcte();
    }

    /**
     * @return si l'ha partida s'ha acabat o no
     */
    public boolean get_partida_acabada() {
        return this.partida_actual.get_partida_acabada();
    }

    /**
     *
     * @return la dificultat de la partida actual
     */
    public dificultats get_dificultat() {
        return this.partida_actual.get_dificultat();
    }

    /**
     * @return la partida que s'esta jugant actualment
     */
    public Partida get_partida_actual(){
        return partida_actual;
    }

    public List<Integer> get_verificacio(){return partida_actual.get_verificacio();}

    public List<List<Integer>> get_intents_partida() {
        return partida_actual.get_intents_partida();
    }

    public List<List<Integer>> get_verificacions_partida() {
        return partida_actual.get_verificacions_partida();
    }
    //public boolean get_es_intent(){return partida_actual.get_es_intent();}

}
