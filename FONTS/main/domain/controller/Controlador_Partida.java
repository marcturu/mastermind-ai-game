package main.domain.controller;

//import java.time.*;
import java.util.HashMap;
import java.util.List;

import main.domain.classes.Partida;
import main.domain.classes.Ronda;
import main.domain.classes.Sequencia;
import main.domain.classes.User;
import main.domain.classes.enumerations.dificultats;

/**
 * Classe del Controlador de Partida
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Controlador_Partida {
    private HashMap<Integer, Partida> hashPartida;
    private Partida partida_actual;

    /**
     * Creadora de la classe controlador Partida
     */
    public Controlador_Partida (){
        this.partida_actual = null;
        this.hashPartida = new HashMap<Integer, Partida>();
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
    public Partida start_partida_nova(int id, User codemaker, User codebreaker, dificultats dif, boolean jugador1_es_codemaker){
        this.partida_actual = new Partida(id, codemaker, codebreaker, dif, jugador1_es_codemaker);//ha de incrementar el numero de partides de l'usuari
        hashPartida.put(id, partida_actual);
        return partida_actual;
    }

    /**
     * @pre Previament s'ha guardat la partida o actualitzat el seu valor dins de la llista.
     * S'assumeix que no hi ha cap partida jugant-se quan es carrega una nova
     * @post Es passa a jugar la partida que s'ha carregat
     * @param id_partida_nova
     * @throws MyException
     */
    public void carregar_partida(int id_partida_nova) throws Exception{
        partida_actual = hashPartida.get(id_partida_nova);
        if(partida_actual == null) {
            throw new Exception("La partida que vols carregar no existeix");
        }
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
    public User get_codebreaker_partida_actual() {
        return this.partida_actual.get_codebreaker();
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
    public void set_ajuda(){
        try{
            this.partida_actual.set_ajuda();
        }
        catch (Exception ex) {
            //System.out.println(ex.getMessage());
        }
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

    public Integer get_ultima_ronda_partida_actual() {
        return this.partida_actual.get_ultima_ronda();
    }

    public void set_seq_solucio(Sequencia seq_sol) {
        this.partida_actual.set_sequencia_solucio(seq_sol);
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

        //partida_actual.get_temps_usat(); //actualitzar el valor de temps_usat

        if (temps_excedit_partida_actual()) {//comprovem que no se'ns hagi acabat el temps
            tractament_partida_acabada();
        }
        if (get_ultima_ronda_partida_actual() + 1 <= get_num_rondes_max_partida_actual()) {//se'ns han acabat les rondes
            partida_actual.crea_nova_ronda();
        } else tractament_partida_acabada();

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
}