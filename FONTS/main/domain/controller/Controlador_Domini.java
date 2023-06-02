package main.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import main.domain.classes.Partida;
import main.domain.classes.Ranking;
import main.domain.classes.Record;
import main.domain.classes.RecordInteger;
import main.domain.classes.RecordDouble;
import main.domain.classes.RecordLong;
import main.domain.classes.Ronda;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.types.Pair;
import main.domain.classes.enumerations.colors;

import main.persistence.*;
import java.util.*;
import java.time.*;

/**
 * Classe del Controlador de Domin
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class Controlador_Domini {
    private User Usuari;
    private User Usuari2;
    private Record Record;
    private Ranking Ranking;
    private HashMap<String, Integer> hashUsers;
    private Controlador_Partida CtrlPartida;
    private ctrl_list_user ctrl_list_user;
    private ctrl_user ctrl_user;
    private ctrl_partida ctrl_pers_partida;
    private ctrl_record ctrl_record;
    private ctrl_ranking ctrl_ranking;
    //private static Controlador_Domini singletonObject;

    private int ids_partides = 1;

    public Controlador_Domini() {
        //crides a altres controladors
        this.CtrlPartida = new Controlador_Partida();
        this.ctrl_list_user = new ctrl_list_user();
        this.ctrl_user = new ctrl_user();
        this.ctrl_pers_partida = new ctrl_partida();
        this.ctrl_record = new ctrl_record();
        this.ctrl_ranking = new ctrl_ranking();
        this.Usuari = null;
        this.Usuari2 = null;
        this.Record = null;
        this.Ranking = null;
        this.hashUsers = ctrl_list_user.carrega_list_user();

        ids_partides = get_num_partides();

        inicialitza_rankings();

        //inicialitzem els records per a totes les modalitats(facil, normal, dificil, .)
        crea_records();

        //registrem els dos usuaris maquina
        registra_maquines();
    }

    //LOGIN

    /**
     * Funcio per a registrar a un user_persona
     * @param nom nom de l'usuari
     * @param password password de l'usuari
     */
    public boolean inicialitzaUserPersona(String nom, String password){
        boolean inicio = false;
        User_persona usuari = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password);
        if (hashUsers.putIfAbsent(nom, hashUsers.size() + 1) != null) return inicio;
        else inicio = true;
        ctrl_list_user.save_list_users(hashUsers);
        ctrl_user.save_users(usuari);
        return inicio;
    }

    /**
     * Pre: Es rep un nom d'usuari d'usuari i un password
     * Post: Es crea el usuari amb els paràmetres entrats i els altres que li falten i s'afageix al map.

     * @param nom nom de l'usuari
     * @param password password de l'usuari
     * @throws Exception si ja existeix un usuari amb el mateix nom
     */
    public boolean inicialitzaUserPersona2(String nom, String password) throws Exception {
        if (hashUsers.containsKey(nom)) {
            //throw new Exception("Error: Usuario2 ya registrado");
            return false;
        }
        Usuari2 = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password);
        ctrl_user.save_users(Usuari2);
        hashUsers.putIfAbsent(nom, hashUsers.size() + 1);
        return true;
    }

    /**
     * Funcio per a registrar els dos usuaris que farà servir la maquina, amb els algorismes five-guess i genetic
     */
    private void registra_maquines() {
        registra_UserMaquina_genetic();
        registra_UserMaquina_fiveguess();
    }

    /**
     * Pre: No existeix la maquina amb nom "Genetic"
     * Post: Es crea el usuari (maquina genetic) amb els paràmetres que li falten i s'afageix al map.
     */
    private void registra_UserMaquina_genetic() {
        Integer id = hashUsers.get("Genetic");
        if(id == null) {
            User_maquina maq = new User_maquina(hashUsers.size() + 1, "Genetic", Type_user.user_maquina, true);
            ctrl_user.save_users(maq);
            hashUsers.putIfAbsent("Genetic", hashUsers.size() + 1);
        }
    }

    /**
     * Funció per a registrar l'usuari Five-Guess, que fa servir l'algorisme de five-guess com a codebreaker.
     */
    private void registra_UserMaquina_fiveguess() {
        Integer id = hashUsers.get("Five-Guess");
        if(id == null) {
            User_maquina maq = new User_maquina(hashUsers.size() + 1, "Five-Guess", Type_user.user_maquina, false);
            ctrl_user.save_users(maq);
            hashUsers.putIfAbsent("Five-Guess", hashUsers.size() + 1);
        }
    }
    /**
     * Funcio per a fer el login de l'usuari que inicia la sessio.
     * @param nom
     * @param passwordFileWriter writer = new FileWriter(archivo);
     * @throws Exception
     */
    public void loginUsuari1(String nom, String password) throws Exception {

        if (!hashUsers.containsKey(nom)) {
            throw new Exception("Error: L'Usuari1 no existeix");
        }
        User user = ctrl_user.carrega_user(hashUsers.get(nom));

        if (user.get_tipus_user() == Type_user.user_maquina) {
            throw new Exception("Error: La màquina no fa login");
        }
        else if (!user.get_password().equals(password)) {
            throw new Exception("Error: Password erroni");
        }
        else {
            Usuari = user;
        }
    }

    /**
     * Funcio per a fer e login del segon usuari en cas que es vulgui jugar pvp.
     * @param nom
     * @param password
     * @throws Exception
     */
    public void loginUsuari2(String nom, String password) throws Exception {
        if (!hashUsers.containsKey(nom)) {
            throw new Exception("Error: L'Usuari2 no existeix");
        }
        User user = ctrl_user.carrega_user(hashUsers.get(nom));
        if (hashUsers.get(nom) == hashUsers.get("Genetic") || hashUsers.get(nom) == hashUsers.get("Five-Guess")) {
            throw new Exception("Error: La màquina no es pot \"loguejar\"");
        }
        else if (user.get_password() == password) {
            throw new Exception("Error: Password erroni");
        }
        else if (Usuari.get_nom().equals(nom)) {
            throw new Exception("Error: L'Usuari2 no pot ser l'Usuari1");
        }
        else {
            Usuari2 = user;
        }
    }

    private void resetAtributes(){
        Usuari = ctrl_user.carrega_user(Usuari.get_id());

        this.Usuari2 = null;
        this.hashUsers = ctrl_list_user.carrega_list_user();
    }

    //JUGAR PARTIDA

    /**
     * Funcio per a començar una nova partida entre User1 i User2, amb dificultat dif i el rol de cada jugador
     * @param id id de la partida
     * @param User1 Usuari amb la sessio activa(ha de ser user_persona)
     * @param User2 Usuari amb el que es vol jugar
     * @param dif dificultat de la partida
     * @param jugador1_es_codemaker indica si el jugador1 es codemaker
     */
    private void start_partida_nova(int id, dificultats dif, boolean jugador1_es_codemaker) {
        Partida partida_nova = new Partida(id, Usuari, Usuari2, dif, jugador1_es_codemaker);
        CtrlPartida.set_partida_actual(partida_nova);
        Usuari.afegir_partida_nova(partida_nova);
        ctrl_pers_partida.save_partida(partida_nova);
        ctrl_user.save_users(Usuari);
        ctrl_user.save_users(Usuari2);
    }

    /**
     * Funcio per començar una nova partida
     * @param dif dificultat de la nova partida
     * @param jugador1_es_codemaker indica is el jugador1 es codemaker
     * @throws Exception
     */
    public void inicialitza_partida_nova(dificultats dif,boolean genetic, boolean jugador1_es_codemaker) throws Exception {
        if (Usuari.get_num_partides_actuals() == 10)
            throw new Exception("Masses partides actives per part d'algun dels dos jugadors");
        else {
            if (genetic) Usuari2 = (User_maquina) ctrl_user.carrega_user(hashUsers.get("Genetic"));
            else Usuari2 = (User_maquina) ctrl_user.carrega_user(hashUsers.get("Five-Guess"));

            Usuari2.set_dificultat_algoritme(dif);

            start_partida_nova(ids_partides, dif, jugador1_es_codemaker);
            ++ids_partides;
        }
    }

    public int get_num_partides_actuals() {
        return Usuari.get_num_partides_actuals();
    }

    /**
     * Funcio per a començar una nova partida pvp
     * @param dif dificultat de la nova partida
     * @param jugador1_es_codemaker indica is el jugador1 es codemaker
     * @throws Exception si hi ha massa partides actives per part d'algun dels dos jugadors
     */
    public void inicialitza_partida_nova_pvp(dificultats dif, boolean jugador1_es_codemaker) throws Exception {
        if (Usuari.get_num_partides_actuals() == 10)
            throw new Exception("Masses partides actives User1");
        else {
            start_partida_nova(ids_partides, dif, jugador1_es_codemaker);
            ++ids_partides;
        }
    }
    /**
     * Funcio per a consultar la sequencia solucio d'una partida
     * @return sequencia solucio de la partida actual
     */
    public List<Integer> get_seq_solucio() {
        List<Integer> list = new ArrayList<Integer>();

        Sequencia_intentada seq_sol = CtrlPartida.get_seq_solucio_partida_actual();
        if(seq_sol == null) return null;

        colors[] array = seq_sol.get_array();

        for (int i = 0; i < 4; ++i){
            list.add(array[i].get_id_color());
        }
        return list;
    }

    /**
     * Funcio per a consultar la sequencia de verificacio de la ronda actual
     * @return sequencia verificacio de la ronda actual
     */
    public List<Integer> get_seq_verificacio_ultima_ronda() {
        return CtrlPartida.get_seq_verificacio_ultima_ronda();
    }

    /**
     * Consultora de si l'usuari principal es codemaker
     * @return true si l'usuari principal es codemaker, false altrament
     */
    public boolean get_jugador1_es_codemaker() {
        return CtrlPartida.get_jugador1_es_codemaker_partida_actual();
    }

    /**
     * Funcio per a rebre la solucio que dona l'algorisme
     * @param solucio solucio de la partida
     * @return llista d'intents fins arribar a la solució
     */
    public List<List<Integer>> get_solve_maquina(List<Integer> solucio) {
        List<List<Integer>> solutions = Usuari2.get_solve_maquina(solucio);

        for (List<Integer> list : solutions) {
            System.out.println(list);
        }

        CtrlPartida.set_solucio_partida_actual(solutions);

        return solutions;

    }

    /**
     * Funcio per a rebre el seguent guess de l'algorisme
     * @return seguent guess de l'algorisme
     */
    public List<Integer> get_seguent_guess_maquina() {
        try {
            return CtrlPartida.get_guess_maquina();
        } catch (Exception e) {

            System.out.println(Arrays.toString(get_seq_solucio().toArray()));

            System.out.println("Error al get_guess_maquina");
            get_solve_maquina(get_seq_solucio());


            //for (List<Integer> list : listOfLists) {
            //    System.out.println(list);
            //}

            List<Integer> next_guess = CtrlPartida.get_guess_maquina();
            return next_guess;
        }

    }

    /**
     * Funcio per a posar la solucio rebuda per la maquina dins de la partida actual
     * @param sol solucio donada per l'algorisme
     */
    public void set_solucio_partida_actual(List<List<Integer>> sol) {
        CtrlPartida.set_solucio_partida_actual(sol);
    }

    /**
     * Funcio per a veure la llista de rondes que s'han jugat en la partida actual
     * @return llista de rondes de la partida actual
     */
    public List<Ronda> get_llista_rondes() {
        return CtrlPartida.get_llista_rondes_partida_actual();
    }

    /**
     * Funcio per a saber el numero de colors de la partida actual
     * @return numero de colors que es poden utilitzar en la partida actual
     */
    public int get_num_colors() {
        return CtrlPartida.get_num_colors_partida_actual();
    }

    /**
     * Funcio per a saber el numero de rondes maxim de la partida actual
     * @return numero de rondes de la partida actual
     */
    public int get_num_rondes() {
        return CtrlPartida.get_num_rondes_max_partida_actual();
    }

    /**
     * Funcio per saber si s'ha superat el temps màxim de la partida o no
     * @return true si s'ha superat el temps màxim de la partida, false altrament
     */
    public boolean temps_excedit_partida_actual() {
        return CtrlPartida.temps_excedit_partida_actual();
    }

    /**
     * Funcio per a rebre el id de la partida actual
     * @return id de la partida actual
     */
    public int get_id_partida_actual() {
        return CtrlPartida.get_id_partida_actual();
    }

    /**
     * Funcio per saber si s'ha demanat ajuda a la partida actual
     * @return si s'ha demanat ajuda a la partida actual
     */
    public boolean get_ajuda_partida() {
        return CtrlPartida.get_ajuda_partida();
    }

    /**
     * Demana ajuda al sistema, es llença AjudaJaDemanada si ja ha demanat ajuda previament
     * @throws AjudaJaDemanada
     */
    public void set_ajuda() throws Exception{
        CtrlPartida.set_ajuda();
    }

    /**
     * Funcio per a posar la sequencia solucio a la partida actual
     * @param sol solucio a posar a la partida actual
     */
    public void set_seq_solucio(List<Integer> entrada) throws Exception{
        Sequencia_intentada sol = new Sequencia_intentada();
        colors[] col = new colors[4];
        for (int i = 0; i < 4; ++i) col[i] = colors.get_color_by_id(entrada.get(i));
        sol.set_array(col,CtrlPartida.get_num_colors_partida_actual());
        CtrlPartida.set_seq_solucio_entrada_per_user(sol);
    }

    /**
     * Funcio per al cas que la solucio no la posem nosaltres, sino la maquina.
     * @param dif dificultat de la partida
     */
    public void genera_solucio_partida(dificultats dif) {
        CtrlPartida.genera_solucio_partida(dif);
    }

    /**
     * Funcio per a consultar qui es el codemaker de la partida actual
     * @return codemaker de la partida actual
     */
    public User get_codemaker_partida_actual() {
        return CtrlPartida.get_codemaker_partida_actual();
    }

    /**
     * Funcio per a consultar qui es el codebreaker de la partida actual
     * @return codebreaker de la partida actual
     */
    public User get_codebraker_partida_actual() {
        return CtrlPartida.get_codebreaker_partida_actual();
    }

    /**
     * Funcio per a consultar la ronda per la que es va en la partida actual
     * @return ronda actual de la partida
     */
    public int get_num_ronda_actual() {
        return CtrlPartida.get_ultima_ronda_partida_actual();
    }

    /**
     * Funcio per a jugar una ronda amb la sequencia intentada seq_int
     * @param seq_int sequencia intentada
     */
    public void jugar_ronda_intentada(List<Integer> entrada) throws Exception{
        Sequencia_intentada seq_int = new Sequencia_intentada();
        colors[] col = new colors[4];
        for (int i = 0; i < 4; ++i) col[i] = colors.get_color_by_id(entrada.get(i));
        seq_int.set_array(col,CtrlPartida.get_num_colors_partida_actual());
        CtrlPartida.crea_nova_ronda();
        CtrlPartida.set_sequencia_intentada(seq_int);
        if(CtrlPartida.temps_excedit_partida_actual()){
            CtrlPartida.tractament_partida_acabada();
        }
    }

    /**
     * Funcio per a jugar una ronda amb la sequencia verificacio seq_ver
     * @param seq_ver sequencia verificacio
     */
    public void jugar_ronda_verificacio(List<Integer> entrada) throws Exception{
        System.out.println(entrada);
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        colors[] col = new colors[4];
        for (int i = 0; i < 4; ++i) col[i] = colors.get_color_by_id(entrada.get(i));
        seq_ver.set_array_verificacio(col, CtrlPartida.get_seq_solucio_partida_actual().get_array(), CtrlPartida.get_seq_intentada_ultima_ronda().get_array());
        CtrlPartida.set_sequencia_verificacio(seq_ver);
        boolean partida_acabada = CtrlPartida.comprova_resultat();
        if(partida_acabada) {
            tractament_partida_victoria();
            if(CtrlPartida.get_codebreaker_partida_actual() == Usuari) {
                Usuari.set_partida_acabada(get_partida_actual(), true, get_partida_actual().get_dificultat().get_dificultat());
            }
            else {
                Usuari.set_partida_acabada(get_partida_actual(), false, get_partida_actual().get_dificultat().get_dificultat());
            }
            ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
        }

        if(CtrlPartida.temps_excedit_partida_actual()){ //o numero de rondes....
            tractament_partida_acabada();
        }
    }

    public boolean exist_partida(){
        return CtrlPartida.get_partida_actual() == null;
    }

    public List<Integer> get_verificacio(){
        return CtrlPartida.get_verificacio();
    }

    /**
     * Funcio per a consultar si la partida actual ja està acabada o no
     * @return true si la partida actual ja està acabada, false altrament
     */
    public boolean partida_acabada(){
        return CtrlPartida.get_partida_acabada();
    }

    /**
     * Funcio per a tractar una partida acabada
     */
    public void tractament_partida_acabada(){
        CtrlPartida.tractament_partida_acabada();
        ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
        ctrl_user.save_users(Usuari);
        ctrl_user.save_users(Usuari2);
        actualitza_ranking();
        resetAtributes();

    }

    public void tractament_partida_victoria(){
        CtrlPartida.tractament_victoria();
        ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
        ctrl_user.save_users(Usuari);
        ctrl_user.save_users(Usuari2);
        actualitza_ranking();
        resetAtributes();

    }

    /**
     * Funcio per a guardar una partida a mitges. Es sobreescriu la partida que estava mapejada a el id "id_par"
     */
    public void guardar_partida_a_mitges(){
        CtrlPartida.get_partida_actual().set_temps_final_partida(Instant.now());
        ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
        ctrl_user.save_users(Usuari);
        //falla al tornar a guardar partida a mitges(dos cops)
        ctrl_user.save_users(Usuari2);
        resetAtributes();

    }

    /**
     * Funcio per a consultar la dificultat de la partida actual
     * @return dificultat de la partida actual
     */
    public dificultats get_dificultat_partida(){
        return CtrlPartida.get_dificultat();
    }

    /**
     * Funcio per a obtenir la partida que s'esta jugant actualment
     * @return partida actual
     */
    public Partida get_partida_actual(){
        return CtrlPartida.get_partida_actual();
    }

    //USER

    /**
     * Consultora de l'id de l'usuari amb sessió activa
     * @return id de l'usuari amb sessió activa
     */
    public int get_id_Usuari1() {
        return this.Usuari.get_id();
    }

    /**
     * Consultora del nom de l'usuari amb sessió activa
     * @return nom de l'usuari amb sessió activa
     */
    public String get_nom_Usuari() {
        return this.Usuari.get_nom();
    }
    /**
     * Consultora del tipus d'usuari de l'usuari amb sessió activa
     * @return tipus d'usuari de l'usuari amb sessió activa
     */
    public Type_user get_tipus_user_Usuari1() {
        return this.Usuari.get_tipus_user();
    }

    /**
     * Funcio per consultar el numero de partides que ha jugat l'usuari amb sessió activa
     * @return numero de partides jugades per l'usuari amb sessió activa
     */
    public int get_partides_totals() {
        if (Usuari == null) {
            return 0;}
        return Usuari.get_partides_totals();
    }

    /**
     * Funcio per a consultar les partides que ha guanyat l'usuari amb sessió activa
     * @return partides guanyades per l'usuari amb sessió activa
     */
    public int get_partides_guanyades() {
        if (Usuari == null) {
            return 0;}
        return Usuari.get_partides_guanyades();
    }

    /**
     * Cosultora del tipus d'usuari del contrincant
     * @return tipus d'usuari contrincant
     */
    public Type_user get_tipus_user_Usuari2() {
        return this.Usuari2.get_tipus_user();
    }
    /**
     * Funcio per a incrementar les rondes totals jugades per l'usuari amb sessió activa
     */
    public void incrementar_rondes_totals_Usuari1() {
        this.Usuari.incrementar_rondes_totals();
    }

    /**
     * Funcio per a incrementar les partides totals jugades per l'usuari amb sessió activa
     */
    public void incrementar_partides_totals_Usuari1() {
        this.Usuari.incrementar_partides_totals();
    }

    /**
     * Funcio per a consultar el nom de l'usuari principal
     * @return nom de l'usuari principal
     */
    public String get_nom_user1(){
        return Usuari.get_nom();

    }

    //JUGAR PARTIDAS NO ACABADES

    /**
     * Funcio per a consultar els ids de les partides actives de l'usuari
     * @return llista d'ids de les partides actives de l'usuari
     */
    public List<Integer> get_ids_partides_actives_Usuari1() {
        return Usuari.get_ids_partides_actives();
    }

    /**
     * Funcio per a carregar la partida amb id = "id_partida_activa"
     * @param id_partida_activa id de la partida a carregar
     * @throws Exception en cas que no existeixi la partida amb aquest id
     */
    public void jugar_partides_antigues(int id_partida_activa) throws Exception{
        //Falta una funció d'aquest tipus per carregar la partida: CtrlPartida.juga_partida_antiga(id_partida_activa);
        Partida partida_actual = ctrl_pers_partida.carrega_partida(id_partida_activa);
        CtrlPartida.set_partida_actual(partida_actual);
        if(get_jugador1_es_codemaker()){
            Usuari2 = ctrl_user.carrega_user(partida_actual.get_jugador2().get_id());
        }else{
            Usuari2 = ctrl_user.carrega_user(partida_actual.get_jugador1().get_id());
        }

        //set times correctly (not the best way but it fits our model)
        partida_actual.set_temps_inici_partida(Instant.now().minus(partida_actual.get_temps_usat()));
        partida_actual.set_temps_final_partida(null);

        if(partida_actual == null) {
            throw new Exception("La partida que vols carregar no existeix");
        }
        CtrlPartida.set_partida_actual(partida_actual);
    }

    /**
     * Funcio que retorna la informacio d'una partida per a quan es vol carregar
     * @param id_partida id de la partida de la que es vol consultar la informacio
     * @return informacio de la partida amb id = id_partida
     */
    public String get_info_partida(int id_partida) {
        Partida p = ctrl_pers_partida.carrega_partida(id_partida);
        String rol;
        if (p.get_jugador1_es_codemaker()) rol = "CM";
        else rol = "CB";
        String ret = "Ultima ronda: " + p.get_ultima_ronda() + " | Dificultat: " + p.get_dificultat().get_dificultat() + " | Rol: " + rol + " | Temps usat: " + duration_to_string(p.get_temps_usat());
        return ret;
    }

    //VEURE PARTIDA ACABADA
    /**
     * Funcio per a consultar els ids de les partides acabades de l'usuari
     * @return llista d'ids de les partides acabades de l'usuari
     */
    public List<Integer> get_ids_partides_acabades_Usuari1() {
        return Usuari.get_ids_partides_acabades();
    }

    /**
     * Funcio per a consultar una partida donat el seu id
     * @param id id de la partida que es vol consultar
     * @return partida amb id = id
     */
    public Partida get_partida(int id){
        return ctrl_pers_partida.carrega_partida((id));
    }

    //RANKING

    /**
     * Funcio per a inicialitzar els rankings
     */
    public void inicialitza_rankings() {

        ctrl_ranking.carrega_ranking("facil");

        ctrl_ranking.carrega_ranking("normal");

        ctrl_ranking.carrega_ranking("dificil");

        ctrl_ranking.carrega_ranking("pvp");
    }

    /**
     * Funcio per a actualitzar el ranking de la dificultat de la partida que s'ha fet
     */
    public void actualitza_ranking() {
        double punts_u = 0.0;
        User aux = CtrlPartida.get_codebreaker_partida_actual();
        String nom_u = aux.get_nom();

        switch ((CtrlPartida.get_dificultat()).get_dificultat()) {
            case "facil":
                punts_u = aux.get_puntuacioF();
                Ranking = ctrl_ranking.carrega_ranking("facil");
                Ranking.nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.save_ranking(Ranking);
                break;
            case "normal":
                punts_u = aux.get_puntuacioN();
                Ranking = ctrl_ranking.carrega_ranking("normal");
                Ranking.nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.save_ranking(Ranking);
                break;
            case "dificil":
                punts_u = aux.get_puntuacioD();
                Ranking = ctrl_ranking.carrega_ranking("dificil");
                Ranking.nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.save_ranking(Ranking);
                break;
            default:
                try {
                    punts_u = aux.get_puntuaciopvp();
                }
                catch (Exception ex){
                    //System.out.println(ex.getMessage());
                }
                Ranking = ctrl_ranking.carrega_ranking("pvp");
                Ranking.nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.save_ranking(Ranking);
                break;
        }
        comprova_records();
    }

    /**
     * Funcio per obtindre la sortida de la capa de presentacio per mostrar el ranking
     * @param dif dificultat del ranking que es vol consultar
     * @return llista de strings amb la informacio del ranking
     */
    public List<String> get_info_ranking(String dif) {
        Ranking = ctrl_ranking.carrega_ranking(dif);
        List<String> llista_info_ranking = new ArrayList<>();
        List<Pair<Pair<Double, String>, LocalDate>> rank = Ranking.get_rank();
        for(int i = 0; i < rank.size(); ++i) {
            Pair<Pair<Double, String>, LocalDate> aux = rank.get(i);
            llista_info_ranking.add(aux.first().first() + " " + aux.first().second() + " " + aux.second());
        }
        return llista_info_ranking;
    }

    //RECORD

    /**
     * inicialitza el record amb nom = "nom_record" per a cada modalitat(facil, normal, dificil, pvp)
     * @param nom_record nom del record que es vol crear
     */
    private void crea_records() {
        //Creem els records de punts
        crea_records_punts();
        //creem els records de ratxes
        crea_records_streak();
        //creem els records de temps
        crea_records_temps();
    }

    /**
     * Funcio per a crear els records de punts amb modalitats facil, normal i dificil
     */
    private void crea_records_punts() {
        String nom_record = "record_punts";
        String modalitat = "facil";
        Record = new RecordDouble(nom_record, modalitat);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordDouble(nom_record, modalitat);
        ctrl_record.save_record(Record);


        modalitat = "dificil";
        Record = new RecordDouble(nom_record, modalitat);
        ctrl_record.save_record(Record);
    }

    /**
     * Funcio per a crear els records de ratxes amb modalitats facil, normal i dificil
     */
    private void crea_records_streak() {
        String nom_record = "record_streak";
        String modalitat = "facil";
        Record = new RecordInteger(nom_record, modalitat);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordInteger(nom_record, modalitat);
        ctrl_record.save_record(Record);

        modalitat = "dificil";
        Record = new RecordInteger(nom_record, modalitat);
        ctrl_record.save_record(Record);
    }

    /**
     * Funcio per a crear els records de temps amb modalitats facil, normal i dificil
     */
    private void crea_records_temps() {
        String nom_record = "record_temps";
        String modalitat = "facil";
        Record = new RecordLong(nom_record, modalitat);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordLong(nom_record, modalitat);
        ctrl_record.save_record(Record);

        modalitat = "dificil";
        Record = new RecordLong(nom_record, modalitat);
        ctrl_record.save_record(Record);
    }


    /**
     * Funcio per veure si la partida bat algun record
     */
    private void comprova_records() {

        String dif = CtrlPartida.get_dificultat().get_dificultat(); //agafem la dificultat de la partida que s'ha fet
        
        Record = ctrl_record.carrega_record("record_punts", dif);

        Record.actualitza(CtrlPartida.get_partida_actual().get_puntuacio(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
        
        ctrl_record.save_record(Record);
       
        Record = ctrl_record.carrega_record("record_streak", dif);
        switch (dif) {
            case "facil":
                Record.actualitza(CtrlPartida.get_codebreaker_partida_actual().get_streakF(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
                break;
            case "normal":
                Record.actualitza(CtrlPartida.get_codebreaker_partida_actual().get_streakN(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
                break;
            case "dificil":
                Record.actualitza(CtrlPartida.get_codebreaker_partida_actual().get_streakD(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
                break;
        }
        ctrl_record.save_record(Record);
        
        Record = ctrl_record.carrega_record("record_temps", dif);


        Record.actualitza(CtrlPartida.get_partida_actual().get_temps_partida(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
        ctrl_record.save_record(Record);
    }

    /**
     * Consultora d'un record segons el seu nom i la seva modalitat
     * @param nom_record nom del record que volem
     * @param modalitat (facil, normal, dificil)
     * @return
     */
    public Record get_record_by_nom_record(String nom_record, String modalitat) {
        return ctrl_record.carrega_record(nom_record,modalitat);
    }
    /**
     * consultora dels punts d'un record amb una modalitat concreta
     * @param nom_record nom del record a consultar
     * @param modalitat (facil, normal, dificil)
     * @return retorna un Object amb els punts/streak/segons
     */
    public Object get_punts_record_by_nom_record(String nom_record, String modalitat) {
        if (ctrl_record.carrega_record(nom_record,modalitat) == null) return 0;
        else return ctrl_record.carrega_record(nom_record,modalitat).get_valor();
    }

    /**
     * Funcio per a consultar el nom de l'usuari que ha batut el record amb nom_record i modalitat
     * @param nom_record nom del record que es vol consultar
     * @param modalitat (facil, normal, dificil)
     * @return nom de l'usuari que ha batut el record
     */
    public String get_nom_usuari_by_nom_record(String nom_record, String modalitat) {
        return ctrl_record.carrega_record(nom_record,modalitat).get_nom_usuari();
    }

    /**
     * Funcio per passar a la capa de presentació la informació dels records
     * @return llista de strings amb la informacio dels records
     */
    public List<String> get_info_records() {
        List<String> info_a_retornar = new ArrayList<>();
        //Records de punts
        String info = "Record de punts en dificultat facil: " + get_punts_record_by_nom_record("record_punts", "facil") + " de " + get_nom_usuari_by_nom_record("record_punts", "facil");
        info_a_retornar.add(info);

        info = "Record de punts en dificultat normal: " + get_punts_record_by_nom_record("record_punts", "normal") + " de " + get_nom_usuari_by_nom_record("record_punts", "normal");
        info_a_retornar.add(info);

        info = "Record de punts en dificultat dificil: " + get_punts_record_by_nom_record("record_punts", "dificil") + " de " + get_nom_usuari_by_nom_record("record_punts", "dificil");
        info_a_retornar.add(info);

        //Records de streak
        info = "Record de streak en dificultat facil: " + get_punts_record_by_nom_record("record_streak", "facil") + " de " + get_nom_usuari_by_nom_record("record_streak", "facil");
        info_a_retornar.add(info);

        info = "Record de streak en dificultat normal: " + get_punts_record_by_nom_record("record_streak", "normal") + " de " + get_nom_usuari_by_nom_record("record_streak", "normal");
        info_a_retornar.add(info);

        info = "Record de streak en dificultat dificil: " + get_punts_record_by_nom_record("record_streak", "dificil") + " de " + get_nom_usuari_by_nom_record("record_streak", "dificil");
        info_a_retornar.add(info);

        //Records de temps
        info = "Record de temps en dificultat facil: " + get_punts_record_by_nom_record("record_temps", "facil") + " de " + get_nom_usuari_by_nom_record("record_temps", "facil");
        info_a_retornar.add(info);

        info = "Record de temps en dificultat normal: " + get_punts_record_by_nom_record("record_temps", "normal") + " de " + get_nom_usuari_by_nom_record("record_temps", "normal");
        info_a_retornar.add(info);

        info = "Record de temps en dificultat dificil: " + get_punts_record_by_nom_record("record_temps", "dificil") + " de " + get_nom_usuari_by_nom_record("record_temps", "dificil");
        info_a_retornar.add(info);

        return info_a_retornar;
    }

    //STATS

    public int get_streakF() {
        if (this.Usuari == null) return 0;
        else return Usuari.get_streakF();
    }

    public int get_streakN() {
        if (this.Usuari == null) return 0;
        else return Usuari.get_streakN();
    }

    public int get_streakD() {
        if (this.Usuari == null) return 0;
        else return Usuari.get_streakD();
    }


    public User getUsuari() {
        return Usuari;
    }

    //UTILS
    private int get_num_partides(){
        for(int i=1; i<1000; ++i){
            if(ctrl_pers_partida.carrega_partida(i)==null) {
                System.out.println("num partides: " + i);
                return i;
            }
        }
        return 1;
    }

    private String duration_to_string (Duration duration){
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        // Format the duration as HH:MM:SS
        String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return formattedDuration;
    }

    public List<List<Integer>> get_intents_partida() {
        return CtrlPartida.get_intents_partida();
    }

    public List<List<Integer>> get_verificacions_partida() {
        return CtrlPartida.get_verificacions_partida();
    }
}