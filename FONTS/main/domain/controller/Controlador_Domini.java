package main.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import main.domain.classes.Partida;
import main.domain.classes.Ranking;
import main.domain.classes.Ranking_dificil;
import main.domain.classes.Ranking_facil;
import main.domain.classes.Ranking_normal;
import main.domain.classes.Ranking_pvp;
import main.domain.classes.Record;
import main.domain.classes.Ronda;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.colors;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.exceptions.MyException;

/**
 * Classe del Controlador de Domin
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class Controlador_Domini {
    private User Usuari;
    private User Usuari2;
    private User UsuariProves;
    private Record Record;
    private Ranking Ranking;
    private Controlador_Partida CtrlPartida;
    private HashMap<String, User> hashUsers;
    private HashMap<String, Record> hashRecord;
    private HashMap<String, Ranking> hashRanking;
    private static Controlador_Domini singletonObject;

    private int ids_partides = 1;

    public Controlador_Domini() {
        this.Usuari = null;
        this.Usuari2 = null;
        this.UsuariProves = null;
        this.Record = null;
        this.Ranking = null;
        this.CtrlPartida = new Controlador_Partida();
        this.hashUsers = new HashMap<String, User>();
        this.hashRecord = new HashMap<String, Record>();
        this.hashRanking = new HashMap<String,Ranking>();
    }

    public static Controlador_Domini get_CtrlDomini() {
        if (singletonObject == null) singletonObject = new Controlador_Domini();
        return singletonObject;
    }

    public Controlador_Partida get_Ctrl_Partida() {
        return CtrlPartida;
    }


    //Pre: Es rep un nom d'usuari d'usuari i un password
    //Post: Es crea el usuari amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitzaUserPersona(String nom, String password) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbsent(nom, Usuari);
    }

    //Pre: Es rep un nom d'usuari d'usuari i un password
    //Post: Es crea el usuari amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitzaUserPersona2(String nom, String password) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari2 = new User_persona(hashUsers.size() + 1, nom, Type_user.user_persona, password, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbsent(nom, Usuari2);
    }

    //Pre: Es rep un nom d'usuari.
    //Post: Es crea el usuari (maquina genetic) amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitza_UserMaquina_genetic(String nom) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari2 = new User_maquina(hashUsers.size() + 1, nom, Type_user.user_maquina, true, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbsent(nom, Usuari2);
    }

    //Pre: Es rep un nom d'usuari.
    //Post: Es crea el usuari (maquina five-guess) amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitza_UserMaquina_fiveguess(String nom) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari2 = new User_maquina(hashUsers.size() + 1, nom, Type_user.user_maquina, false, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbsent(nom, Usuari2);
    }

    /**
     * @throws User1NoPotSerMaquina
     *Comprova si l'usuari1 és de tipus muina. Si ho és, llença l'excepció
     */
    public void set_jugador1(String nom_user) throws MyException {
        if ((hashUsers.get(nom_user)).get_tipus_user() == Type_user.user_maquina) {
            throw new MyException ("L'usuari1 no pot ser de tipus màquina");
        }
        else if (validate_password_Usuari1_by_user_name(nom_user)) Usuari = hashUsers.get(nom_user);
    }

    /**
     * @throws MyException
     *Comprova si el password del Usuari2 es correspon amb el del nou usuari entrat. Si no, salta l'excepció
     */
    public void set_jugador2(String nom_user) throws MyException {
        if (get_tipus_user_by_nom_user(nom_user) == Type_user.user_persona && !validate_password_Usuari2_by_user_name(nom_user)) throw new Exception ("Usuari2 no té el mateix password");
        else Usuari2 = hashUsers.get(nom_user);
    }

    public void creacioRecord(String nom_record) {
        this.Record = new Record(nom_record);
    }

    public String get_nom_record() {
        return this.Record.get_nom_record();
    }

    public String get_nom_usuari_del_record() {
        return this.Record.get_nom_usuari();
    }

    public double get_punts() {
        return this.Record.get_punts();
    }

    /**
     * @throws java.lang.IllegalArgumentException
     */
    public boolean es_record(int punts, String nom_usuari) throws IllegalArgumentException {
        return this.Record.check_if_record(punts, nom_usuari);
    }

    public int get_id_Usuari1() {
        return this.Usuari.get_id();
    }

    public String get_nom_Usuari() {
        return this.Usuari.get_nom();
    }

    public void set_nom_Usuari(String nom) {
        this.Usuari.set_nom(nom);
    }

    public Type_user get_tipus_user_Usuari1() {
        return this.Usuari.get_tipus_user();
    }

    public Type_user get_tipus_user_Usuari2() {
        return this.Usuari2.get_tipus_user();
    }

    public Type_user get_tipus_user_by_nom_user(String nom_user) {
        UsuariProves = get_user_by_username(nom_user);
        return Usuari.get_tipus_user();
    }


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
    }


    public int get_rondes_totals_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_rondes_totals();
    }

    public void incrementar_rondes_totals_Usuari1() {
        this.Usuari.incrementar_rondes_totals();
    }

    public int get_partides_totals_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_totals();
    }

    public void incrementar_partides_totals_Usuari1() {
        this.Usuari.incrementar_partides_totals();
    }

    public void incrementar_partides_totals_Usuari2() {
        this.Usuari2.incrementar_partides_totals();
    }


    public double get_puntuacioF_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioF();
    }

    public double get_puntuacioN_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioN();
    }

    public double get_puntuacioD_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioD();
    }

    /**
     * @throws MaquinaNoTePuntsPvsP
     *Demana els punts PvsP del Usuari2, es llença MaquinaNoTePuntsPvsP si l'Uusari2 és de tipus user_maquina
     */


    public void set_puntuacio_by_nom_user(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        Usuari.set_puntuacio();
    }

    public int get_partides_guanyades_by_nom_user(String nom_user) {
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
    }


    public void inicialitza_partida_nova(dificultats dif, boolean jugador1_es_codemaker) throws MyException {
        if (Usuari.get_num_partides_actuals() == 10 || Usuari2.get_num_partides_actuals() == 10)
            throw new MyException("Masses partides actives per part d'algun dels dos jugadors");
        else {
            Partida partida_nova = CtrlPartida.start_partida_nova(ids_partides, Usuari, Usuari2, dif, jugador1_es_codemaker);
            afegir_partida_nova_users(Usuari, Usuari2, partida_nova);
            ++ids_partides;
        }
    }

    public void inicialitza_partida_nova_pvp(dificultats dif, boolean jugador1_es_codemaker) throws MyException {
        if (Usuari.get_num_partides_actuals() == 10 || Usuari2.get_num_partides_actuals() == 10)
            throw new MyException("Masses partides actives per part d'algun dels dos jugadors");
        else {
            Partida partida_nova = CtrlPartida.start_partida_nova(ids_partides, Usuari, Usuari2, dif, jugador1_es_codemaker);
            afegir_partida_nova_users(Usuari, Usuari2, partida_nova);
            ++ids_partides;
        }
    }

    public void afegir_partida_nova_users(User Usuari, User Usuari2, Partida partida_nova) throws MyException{
        Usuari.afegir_partida_nova(partida_nova);
        Usuari2.afegir_partida_nova(partida_nova);
    }

    /*
    public void afegir_partida_nova_Usuari(Partida partida_nova) throws Exception {
        if (this.Usuari.get_partides_actuals_Usuari() == 10)
            throw new Exception("Masses partides actives per part de l'usuari");
        else this.Usuari.afegir_partida_nova(partida_nova);
    } */

    public void elimina_Usuari_hashUsers(User usuari) {
        hashUsers.remove(usuari.get_nom(), usuari);
    }

    public List<Ronda> get_llista_rondes() {
        return CtrlPartida.get_llista_rondes_partida_actual();
    }

    public int get_num_colors() {
        return CtrlPartida.get_num_colors_partida_actual();
    }

    public int get_num_rondes() {
        return CtrlPartida.get_num_rondes_max_partida_actual();
    }

    public boolean temps_excedit_partida_actual() {
        return CtrlPartida.temps_excedit_partida_actual();
    }

    public int get_id_partida_actual() {
        return CtrlPartida.get_id_partida_actual();
    }

    public boolean get_ajuda_partida() {
        return CtrlPartida.get_ajuda_partida();
    }

    /**
     * @throws AjudaJaDemanada
     * Demana ajuda al sistema, es llença AjudaJaDemanada si ja ha demanat ajuda previament
     */
    public void set_ajuda() throws MyException{
        CtrlPartida.set_ajuda();
    }

    public User get_codemaker_partida_actual() {
        return CtrlPartida.get_codemaker_partida_actual();
    }

    public User get_codebraker_partida_actual() {
        return CtrlPartida.get_codebreaker_partida_actual();
    }

    public int get_num_ronda_actual() {
        return CtrlPartida.get_ultima_ronda_partida_actual();
    }

    public User get_user_by_username(String username) {
        return hashUsers.get(username);
    }

    public Record get_record_by_nom_record(String nom_record) {
        return hashRecord.get(nom_record);
    }

    public int get_punts_record_by_nom_record(String nom_record) {
        Record = hashRecord.get(nom_record);
        return Record.get_punts();
    }

    public String get_nom_usuari_by_nom_record(String nom_record) {
        Record = hashRecord.get(nom_record);
        return Record.get_nom_usuari();
    }

    public Ranking get_ranking_by_ranking_name(String ranking_name) {
        return hashRanking.get(ranking_name);
    }

    public void inicialitza_nou_record(String nom_record) {
        Record = new Record(nom_record);
        hashRecord.putIfAbsent(nom_record, Record);
    }

    public void inicialitza_rankings() {
        Ranking = new Ranking_facil();
        hashRanking.put("facil", Ranking);
        Ranking = new Ranking_normal();
        hashRanking.put("normal", Ranking);
        Ranking = new Ranking_dificil();
        hashRanking.put("dificl", Ranking);
        Ranking = new Ranking_pvp();
        hashRanking.put("PvsP", Ranking);
    }

    public void jugar_ronda(colors[] seq_int, colors[] seq_ver) {
        if (CtrlPartida.get_partida_acabada()) actualitza_ranking();
        else {
            CtrlPartida.jugar_ronda(seq_int, seq_ver);
        }
    }

    public void actualitza_ranking() {
        Pair<Double, Double> pair;
        double punts_u;
        double punts_u2;
        String nom_u = Usuari.get_nom();
        String nom_u2 = Usuari2.get_nom();
        switch ((CtrlPartida.get_dificultat()).get_dificultat()) {
            case "facil":
                punts_u = this.Usuari.get_puntuacioF();
                punts_u2 = this.Usuari2.get_puntuacioF();
                (hashRanking.get("facil")).nova_partida_ranking(punts_u, nom_u);
                (hashRanking.get("facil")).nova_partida_ranking(punts_u2, nom_u2);
                break;
            case "normal":
                punts_u = this.Usuari.get_puntuacioN();
                punts_u2 = this.Usuari2.get_puntuacioN();
                (hashRanking.get("normal")).nova_partida_ranking(punts_u, nom_u);
                (hashRanking.get("normal")).nova_partida_ranking(punts_u2, nom_u2);
                break;
            case "dificil":
                punts_u = this.Usuari.get_puntuacioD();
                punts_u2 = this.Usuari2.get_puntuacioD();
                (hashRanking.get("dificil")).nova_partida_ranking(punts_u, nom_u);
                (hashRanking.get("dificil")).nova_partida_ranking(punts_u2, nom_u2);
                break;
            default:
                punts_u = this.Usuari.get_puntuacioPvsP();
                punts_u2 = this.Usuari2.get_puntuacioPvsP();
                (hashRanking.get("pvp")).nova_partida_ranking(punts_u, nom_u);
                (hashRanking.get("pvp")).nova_partida_ranking(punts_u2, nom_u2);
                break;
        }
    }

    public void jugar_partides_antigues(int id_partida_activa) throws MyException{
        //Falta una funció d'aquest tipus per carregar la partida: CtrlPartida.juga_partida_antiga(id_partida_activa);
        CtrlPartida.carregar_partida(id_partida_activa);
    }

    public List<Integer> get_ids_partides_actives_Usuari1() {
        return Usuari.get_ids_partides_actives();
    }

    public List<Integer> get_ids_partides_acabades_Usuari1() {
        return Usuari.get_ids_partides_acabades();
    }
    //faltaa"!!!
    public Partida get_partida(int id){ return Usuari.get_partida_acabada(id);}
}