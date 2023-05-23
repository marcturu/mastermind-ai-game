package main.domain.controller;

import java.util.HashMap;
import java.util.List;
//import java.util.Vector;

import main.domain.classes.Partida;
import main.domain.classes.Ranking;
import main.domain.classes.Record;
import main.domain.classes.RecordInteger;
import main.domain.classes.Ronda;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.types.Pair;

import main.persistence.*;

/**
 * Classe del Controlador de Domin
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class Controlador_Domini {
    private User Usuari;
    private User Usuari2;
    //private User UsuariProves;
    private Record Record;
    private HashMap<String, Integer> hashUsers;
    private HashMap<Pair<String,String>, Record> hashRecord;
    private HashMap<String, Ranking> hashRanking;
    private HashMap<Integer, Partida> hashPartida;
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
        this.hashUsers = ctrl_list_user.carrega_list_user();
        this.hashRecord = new HashMap<Pair<String,String>, Record>();
        this.hashRanking = new HashMap<String,Ranking>();


        //get_CtrlDomini();
        inicialitza_rankings();

        //inicialitzem els records per a totes les modalitats(facil, normal, dificil, pvp)
        crea_records();

        //registrem els dos usuaris maquina
        registra_maquines();


    }
/*
    public static Controlador_Domini get_CtrlDomini() {
        if (singletonObject == null) singletonObject = new Controlador_Domini();
        return singletonObject;
    }

    public Controlador_Partida get_Ctrl_Partida() {
        return CtrlPartida;
    }*/


    /**
     * Funcio per a registrar a un user_persona
     * @param nom nom de l'usuari
     * @param password password de l'usuari
     */
    public void inicialitzaUserPersona(String nom, String password){
        Usuari usuari = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password);
        hashUsers.putIfAbsent(nom, hashUsers.size() + 1);
        ctrl_list_user.save_list_users(hashUsers);
        ctrl_user.save_users(usuari);
    }

    /**
     * Pre: Es rep un nom d'usuari d'usuari i un password
     * Post: Es crea el usuari amb els paràmetres entrats i els altres que li falten i s'afageix al map.

     * @param nom nom de l'usuari
     * @param password password de l'usuari
     * @throws Exception si ja existeix un usuari amb el mateix nom
     */
    public void inicialitzaUserPersona2(String nom, String password) throws Exception {
        if (hashUsers.containsKey(nom)) {
            throw new Exception("Error: Usuario2 ya registrado");
        }
        Usuari2 = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password);
        ctrl_user.save_users(Usuari2);
        hashUsers.putIfAbsent(nom, hashUsers.size() + 1);
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
    public void registra_UserMaquina_genetic() {
        Integer id = hashUsers.get("Genetic");
        if(id == null) {
            User maq = new User_maquina(hashUsers.size() + 1, "Genetic", Type_user.user_maquina, true);
            ctrl_user.save_users(maq);
            hashUsers.putIfAbsent("Genetic", hashUsers.size() + 1);
        }
    }

    /**
     * Funció per a registrar l'usuari Five-Guess, que fa servir l'algorisme de five-guess com a codebreaker.
     */
    public void registra_UserMaquina_fiveguess() {
        Integer id = hashUsers.get("Five-Guess");
        if(id == null) {
            User maq = new User_maquina(hashUsers.size() + 1, "Five-Guess", Type_user.user_maquina, false);
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
        else if (user.get_password() == password) {
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

    /**
     * Comprova si l'usuari1 és de tipus maquina. Si no ho és, 
     * @throws User1NoPotSerMaquina
     */
   /* public void set_jugador1(String nom_user) throws Exception {
        if ((hashUsers.get(nom_user)).get_tipus_user() == Type_user.user_maquina) {
            throw new Exception ("L'usuari1 no pot ser de tipus màquina");
        }
        //else if (validate_password_Usuari1_by_user_name(nom_user)) Usuari = hashUsers.get(nom_user);
    }

    /**
     * Funcio per a posar el usuari amb nom = nom_usuari com a jugador2.
     * @param nom_user
     */
   /* public void set_jugador2(String nom_user)  {
        //if (get_tipus_user_by_nom_user(nom_user) == Type_user.user_persona && !validate_password_Usuari2_by_user_name(nom_user)) throw new Exception ("Usuari2 no té el mateix password");
        Usuari2 = hashUsers.get(nom_user);
    }*/

    /**
     * Funcio per a saber quin es el nom de l'usuari que ha batut el record
     * @param nom_record nom del record que es vol consultar.
     * @param modalitat (facil, normal, dificil)
     * @return nom de l'usuari que ha batut el record.
     */
    public String get_nom_usuari_del_record(String nom_record, String modalitat) {
        return hashRecord.get(new Pair<>(nom_record, modalitat)).get_nom_usuari();
    }

    /**
     * Funcio per a saber quin es el valor amb el que s'ha batut el record amb nom_record
     * @param nom_record nom del record que es vol consultar.
     * @param modalitat (facil, normal, dificil)
     * @return valor del record
     */
    public Object get_punts(String nom_record, String modalitat) {
        return hashRecord.get(new Pair<>(nom_record, modalitat)).get_valor();
    }

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
     * Modificadora del nom de l'usuari amb sessió activa
     * @param nom
     */
    public void set_nom_Usuari(String nom) {
        this.Usuari.set_nom(nom);
    }

    /**
     * Consultora del tipus d'usuari de l'usuari amb sessió activa
     * @return tipus d'usuari de l'usuari amb sessió activa
     */
    public Type_user get_tipus_user_Usuari1() {
        return this.Usuari.get_tipus_user();
    }

    /**
     * Cosultora del tipus d'usuari del contrincant
     * @return tipus d'usuari contrincant
     */
    public Type_user get_tipus_user_Usuari2() {
        return this.Usuari2.get_tipus_user();
    }

    /*public Type_user get_tipus_user_by_nom_user(String nom_user) {
        //UsuariProves = get_user_by_username(nom_user);

        return hashUsers.get(nom_user).get_tipus_user();
    }*/

    /*
    public boolean get_password_Usuari1() {
        return this.Usuari.get_password();
    }

    public boolean validate_password_Usuari1_by_user_name(String user_name) {
        UsuariProves = hashUsers.get(user_name);
        return Usuari.get_password() == UsuariProves.get_password();
    }

    public boolean validate_password_Usuari2_by_user_name(String user_name) {
        UsuariProves = hashUsers.get(user_name);
        return Usuari2.get_password() == UsuariProves.get_password();
    } */


   /* public int get_rondes_totals_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_rondes_totals();
    }*/

    /**
     * Funcio per a incrementar les rondes totals jugades per l'usuari amb sessió activa
     */
    public void incrementar_rondes_totals_Usuari1() {
        this.Usuari.incrementar_rondes_totals();
    }

   /* public int get_partides_totals_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_totals();
    }*/

    /**
     * Funcio per a incrementar les partides totals jugades per l'usuari amb sessió activa
     */
    public void incrementar_partides_totals_Usuari1() {
        this.Usuari.incrementar_partides_totals();
    }

    /*public void incrementar_partides_totals_Usuari2() {
        this.Usuari2.incrementar_partides_totals();
    }*/


    /*public double get_puntuacioF_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioF();
    }*/

   /* public double get_puntuacioN_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioN();
    }*/

   /* public double get_puntuacioD_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioD();
    }
*/
    /**
     * @throws MaquinaNoTePuntsPvsP
     *Demana els punts PvsP del Usuari2, es llença MaquinaNoTePuntsPvsP si l'Uusari2 és de tipus user_maquina
     */

/*
    public void set_puntuacio_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        Usuari.set_puntuacio();
    } */

  /*  public int get_partides_guanyades_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_guanyades();
    }

    public int get_partides_acabades_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_num_partides_acabades();
    }

    public int get_partides_actuals_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_num_partides_actuals();
    }

    public Vector<Double> get_estadistiques_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_estadistiques();
    }*/

    /**
     * Funcio per a consultar la sequencia solucio d'una partida
     * @return sequencia solucio de la partida actual
     */
    public Sequencia_intentada get_seq_solucio() {
        return CtrlPartida.get_seq_solucio_partida_actual();
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
        return Usuari2.get_solve_maquina(solucio);
    }

    /**
     * Funcio per a rebre el seguent guess de l'algorisme
     * @return seguent guess de l'algorisme
     */
    public List<Integer> get_seguent_guess_maquina() {
        return CtrlPartida.get_guess_maquina();
    }

    /**
     * Funcio per a posar la solucio rebuda per la maquina dins de la partida actual
     * @param sol solucio donada per l'algorisme
     */
    public void set_solucio_partida_actual(List<List<Integer>> sol) {
        CtrlPartida.set_solucio_partida_actual(sol);
    }

    /**
     * Funcio per a començar una nova partida entre User1 i User2, amb dificultat dif i el rol de cada jugador
     * @param id id de la partida
     * @param User1 Usuari amb la sessio activa(ha de ser user_persona)
     * @param User2 Usuari amb el que es vol jugar
     * @param dif dificultat de la partida
     * @param jugador1_es_codemaker indica si el jugador1 es codemaker
     */
    private void start_partida_nova(int id, User User1, User User2, dificultats dif, boolean jugador1_es_codemaker) {
        Partida partida_nova = new Partida(id, User1, User2, dif, jugador1_es_codemaker); 
        hashPartida.put(id, partida_nova);
        CtrlPartida.set_partida_actual(partida_nova);
    }

    /**
     * Funcio per començar una nova partida
     * @param dif dificultat de la nova partida
     * @param jugador1_es_codemaker indica is el jugador1 es codemaker
     * @throws Exception
     */
    public void inicialitza_partida_nova(dificultats dif, boolean jugador1_es_codemaker) throws Exception {
        if (Usuari.get_num_partides_actuals() == 10)
            throw new Exception("Masses partides actives per part d'algun dels dos jugadors");
        else {
            start_partida_nova(ids_partides, Usuari, Usuari2, dif, jugador1_es_codemaker);
            afegir_partida_nova_users(Usuari, CtrlPartida.get_partida_actual());
            ++ids_partides;
        }
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
        else if(Usuari2.get_num_partides_actuals() == 10)
            throw new Exception("Masses partides actives User2");
        else {
            start_partida_nova(ids_partides, Usuari, Usuari2, dif, jugador1_es_codemaker);
            afegir_partida_nova_users(Usuari, CtrlPartida.get_partida_actual());
            ++ids_partides;
        }
    }

    /**
     * Funcio per afegir partida_nova a l'usuari donat
     * @param Usuari usuari al que se li vol afegir la partida
     * @param partida_nova partida que es vol afegir
     * @throws Exception si l'usuari ja te 10 partides actives
     */
    private void afegir_partida_nova_users(User Usuari, Partida partida_nova) throws Exception{
        Usuari.afegir_partida_nova(partida_nova);
    }

    /*
    public void afegir_partida_nova_Usuari(Partida partida_nova) throws Exception {
        if (this.Usuari.get_partides_actuals_Usuari() == 10)
            throw new Exception("Masses partides actives per part de l'usuari");
        else this.Usuari.afegir_partida_nova(partida_nova);
    } */

    /**
     * Funcio per a eliminar un usuari de la llista d'usuaris
     * @param usuari usuari que es vol eliminar
     */
    public void elimina_Usuari_hashUsers(String nom) {
        int value = hashUsers.get(nom);
        hashUsers.remove(nom, value);
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
    public void set_seq_solucio(Sequencia_intentada sol) {
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

    //public User get_user_by_username(String username) {
     //   return hashUsers.get(username);
   // }

    /**
     * Consultora d'un record segons el seu nom i la seva modalitat
     * @param nom_record nom del record que volem
     * @param modalitat (facil, normal, dificil)
     * @return
     */
    public Record get_record_by_nom_record(String nom_record, String modalitat) {
        return hashRecord.get(new Pair<>(nom_record, modalitat));
    }
    /**
     * consultora dels punts d'un record amb una modalitat concreta
     * @param nom_record nom del record a consultar
     * @param modalitat (facil, normal, dificil)
     * @return retorna un Object amb els punts/streak/segons
     */
    public Object get_punts_record_by_nom_record(String nom_record, String modalitat) {
        return hashRecord.get(new Pair<>(nom_record, modalitat)).get_valor();
    }

    /**
     * Funcio per a consultar el nom de l'usuari que ha batut el record amb nom_record i modalitat
     * @param nom_record nom del record que es vol consultar
     * @param modalitat (facil, normal, dificil)
     * @return nom de l'usuari que ha batut el record
     */
    public String get_nom_usuari_by_nom_record(String nom_record, String modalitat) {
        return hashRecord.get(new Pair<>(nom_record, modalitat)).get_nom_usuari();
    }

    /**
     * Funcio per obtindre un ranking segons el seu nom("facil", "normal", "dificil", "pvp")
     * @param ranking_name nom del ranking que es vol consultar
     * @return ranking amb nom = ranking_name
     */
    public Ranking get_ranking_by_ranking_name(String ranking_name) {
        return hashRanking.get(ranking_name);
    }

    /**
     * Funcio per a crear els records de punts amb modalitats facil, normal i dificil
     */
    private void crea_records_punts() {
        String nom_record = "record_punts";
        String modalitat = "facil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);


        modalitat = "dificil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);
    }

    /**
     * Funcio per a crear els records de ratxes amb modalitats facil, normal i dificil
     */
    private void crea_records_streak() {
        String nom_record = "record_streak";
        String modalitat = "facil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);

        modalitat = "dificil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);
    }    

    /**
     * Funcio per a crear els records de temps amb modalitats facil, normal i dificil
     */
    private void crea_records_temps() {
        String nom_record = "record_temps";
        String modalitat = "facil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);

        modalitat = "normal";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);

        modalitat = "dificil";
        Record = new RecordInteger(nom_record, modalitat);
        hashRecord.putIfAbsent(new Pair<>(nom_record, modalitat), Record);
        ctrl_record.save_record(Record);
    }    

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
     * Funcio per a inicialitzar els rankings
     */
    public void inicialitza_rankings() {
        Ranking = new Ranking("facil");
        hashRanking.put("facil", Ranking);
        ctrl_ranking.save(Ranking);

        Ranking = new Ranking("normal");
        hashRanking.put("normal", Ranking);
        ctrl_ranking.save(Ranking);

        Ranking = new Ranking("dificil");
        hashRanking.put("dificl", Ranking);
        ctrl_ranking.save(Ranking);

        Ranking = new Ranking("pvp");
        hashRanking.put("pvp", Ranking);
        ctrl_ranking.save(Ranking);
    }

    /**
     * Funcio per a jugar una ronda amb la sequencia intentada seq_int
     * @param seq_int sequencia intentada
     */
    public void jugar_ronda_intentada(Sequencia_intentada seq_int){
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
    public void jugar_ronda_verificacio(Sequencia_verificacio seq_ver){
        CtrlPartida.set_sequencia_verificacio(seq_ver);
        boolean partida_acabada = CtrlPartida.comprova_resultat();
        if(partida_acabada) {
            CtrlPartida.tractament_victoria();
            ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
        }

        if(CtrlPartida.temps_excedit_partida_actual()){
            CtrlPartida.tractament_partida_acabada();
        }

        if (CtrlPartida.get_partida_acabada()) actualitza_ranking();
    }

    /**
     * Funcio per veure si la partida bat algun record
     */
    private void comprova_records() {

        //Falta adaptar-ho a persistència

        String dif = CtrlPartida.get_dificultat().get_dificultat(); //agafem la dificultat de la partida que s'ha fet

        for(Record r:hashRecord.values()) {
            if(dif == r.get_modalitat_record()) {
                if(r.get_nom_record().equals("record_punts")) {
                    r.actualitza(CtrlPartida.get_partida_actual().get_puntuacio(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
                }
                else if(r.get_nom_record().equals("record_streak")) {
                    r.actualitza(CtrlPartida.get_codebreaker_partida_actual().get_streak(), CtrlPartida.get_codebreaker_partida_actual().get_nom());
                }
                else if(r.get_nom_record().equals("record_temps")) {
                    r.actualitza(CtrlPartida.get_partida_actual().get_temps_partida(), CtrlPartida.get_codebreaker_partida_actual().get_nom());;
                }
            }
        }
    }

    /**
     * Funcio per a actualitzar el ranking de la dificultat de la partida que s'ha fet
     */
    public void actualitza_ranking() {
        System.out.println("\n\n\nranking\n\n\n");
        double punts_u = 0.0;
        User aux = CtrlPartida.get_codebreaker_partida_actual();
        String nom_u = aux.get_nom();
        
        switch ((CtrlPartida.get_dificultat()).get_dificultat()) {
            case "facil":
                punts_u = aux.get_puntuacioF();
                //(hashRanking.get("facil")).nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.carrega_ranking("facil").nova_partida_ranking(punts_u, nom_u);
                //S'ha de fer ctrl_ranking.save(ranking_carregat); ?
                break;
            case "normal":
                punts_u = aux.get_puntuacioN();
                //(hashRanking.get("normal")).nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.carrega_ranking("normal").nova_partida_ranking(punts_u, nom_u);
                break;
            case "dificil":
                punts_u = aux.get_puntuacioD();
                //(hashRanking.get("dificil")).nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.carrega_ranking("dificil").nova_partida_ranking(punts_u, nom_u);
                break;
            default:
                try {
                    punts_u = aux.get_puntuaciopvp();
                }
                catch (Exception ex){
                    //System.out.println(ex.getMessage());
                }
                //(hashRanking.get("pvp")).nova_partida_ranking(punts_u, nom_u);
                ctrl_ranking.carrega_ranking("pvp").nova_partida_ranking(punts_u, nom_u);
                break;
        }
        comprova_records();       
    }

    /**
     * Funcio per a carregar la partida amb id = "id_partida_activa"
     * @param id_partida_activa id de la partida a carregar
     * @throws Exception en cas que no existeixi la partida amb aquest id
     */
    public void jugar_partides_antigues(int id_partida_activa) throws Exception{
        //Falta una funció d'aquest tipus per carregar la partida: CtrlPartida.juga_partida_antiga(id_partida_activa);
        Partida partida_actual = ctrl_pers_partida.carrega_partida(id_partida_activa);
        if(partida_actual == null) {
            throw new Exception("La partida que vols carregar no existeix");
        }
        CtrlPartida.set_partida_actual(partida_actual);
    }

    /**
     * Funcio per a consultar els ids de les partides actives de l'usuari
     * @return llista d'ids de les partides actives de l'usuari
     */
    public List<Integer> get_ids_partides_actives_Usuari1() {
        return Usuari.get_ids_partides_actives();
    }

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
        actualitza_ranking();
    }

    /**
     * Funcio per a guardar una partida a mitges. Es sobreescriu la partida que estava mapejada a el id "id_par"
     */
    public void guardar_partida_a_mitges(){
        ctrl_pers_partida.save_partida(CtrlPartida.get_partida_actual());
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

    /**
     * Funcio per a consultar el nom de l'usuari principal
     * @return nom de l'usuari principal
     */
    public String get_nom_user1(){
        return Usuari.get_nom();

    }
}