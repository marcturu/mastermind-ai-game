package main.domain.controller;

import main.domain.classes.Partida;
import main.domain.classes.User;
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


    public Controlador_Partida (){
        this.partida_actual = null;
    }


    /**
     * Aquesta funció serveix per fer el tractament en cas que el codebreaker encerti la sequencia. Avisa a partida_actual
     * perque aquesta avisi als usuaris del tractament pertinent
     */
    private void tractament_victoria() {
        this.partida_actual.codebreaker_guanya();
        this.partida_actual = null;

    }

    /**
     * Aquesta funció serveix per fer el tractament en cas que al codebreaker se li acabin les rondes o el temps. Avisa a partida_actual
     * perque aquesta avisi als usuaris del tractament pertinent
     */
    private void tractament_partida_acabada() {
        this.partida_actual.codemaker_guanya();
        this.partida_actual = null;
    }

    /**
     * @param id
     * @param codemaker
     * @param codebreaker
     * @param dif
     * @param jugador1_es_codemaker
     * Crea una partida nova amb els parametres entrats. Aquests parametres han d'estar conprovats abans de cridar a la funció.
     */
    public void start_partida_nova(int id, User codemaker, User codebreaker, dificultat dif, boolean jugador1_es_codemaker){
        this.partida_actual = new Partida(id, codemaker, codebreaker, dif, jugador1_es_codemaker);//ha de incrementar el numero de partides de l'usuari
    }

    /**
     * @return User que té com a rol codemaker
     */
    public User get_codemaker_partida_actual() {
        return this.partida_actual.get_codemaker();
    }

    /**
     * @return User que té com a rol codebreaker
     */
    public User get_codebraker_partida_actual() {
        return this.partida_actual.get_codebraker();
    }

    /**
     * @return Id de la partida que s'està jugant
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
     * Demana ajuda al sistema, es llença AjudaJaDemanada si ja ha demanat ajuda previament
     */
    public void set_ajuda() throws AjudaJaDemanada{
        this.partida_actual.set_ajuda();
    }

    /**
     * @return si s'ha excedit el temps limit que té la partida
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
     * @param seq_int sequencia que ha entrar el codebreaker
     * @param seq_ver sequencia que ha entrat el codemaker
     * Funcionalitat que gestiona una ronda. Se li passen dos sequencies que son valides i correctes i es fa el tractament d'aquestes.
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