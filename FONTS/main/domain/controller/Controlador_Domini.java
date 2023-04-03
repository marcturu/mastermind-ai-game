package main.domain.controllers;

import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.Ranking;
import main.domain.classes.Record;
import main.domain.controller.Controlador.Partida;
import main.domain.classes.enumerations.Type_user;

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
    private Controlador_Partida CtrlPartida;
    private HashMap<String, User> hashUsers;
    private HashMap<String, Record> hashRecord;
    private HashMap<String, Ranking> hashRanking;
    private static Controlador_Domini singletonObject;

    public Controlador_Domini() {
        this.Usuari = null;
        this.Usuari2 = null;
        this.Record = null;
        this.Ranking = null;
        this.CtrlPartida = new Controlador_Partida();
        this.hashUsers = new HashMap<String, User>();
        this.hashRecord = new HashMap<String, Record>();
        this.hashRanking = new HashMap<String,Ranking>()
    }

    public static Controlador_Domini get_CtrlDomini {
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
        Usuari2 = new User_maquina(hashUsers.size() + 1, nom, Type_user.user_persona, true, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbsent(nom, Usuari2);
    }

    //Pre: Es rep un nom d'usuari.
    //Post: Es crea el usuari (maquina five-guess) amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitza_UserMaquina_fiveguess(String nom) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari2 = new User_maquina(hashUsers.size() + 1, nom, Type_user.user_persona, false, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.putIfAbasent(nom, Usuari2);
    }

    public void creacioRecord(String nom_record) {
        this.Record = new Record(nom_record);
    }

    public String get_nom_record() {
        return this.Record.get_nom_record();
    }

    public String get_nom_usuari() {
        return this.Record.get_nom_usuari();
    }

    public double get_punts() {
        return this.Record.get_punts();
    }

    /**
     * @throws java.lang.IllegalArgumentException
     */

    public boolean es_record(int punts, String nom_usuari) throws IllegalArgumentException {
        this.Record.check_if_record(punts, nom_usuari);
    }

    public int get_id() {
        return this.Usuari.get_id();
    }

    public int get_nom_Usuari() {
        return this.Usuari.get_nom();
    }

    public void set_nom_Usuari(String nom) {
        this.Usuari.set_nom(nom);
    }

    public Type_user get_tipus_user() {
        return this.Usuari.get_tipus_user();
    }

    public int get_rondes_totals_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_rondes_totals();
    }

    public void incrementar_rondes_totals_Usuari() {
        this.Usuari.incrementar_rondes_totals();
    }

    public int get_partides_totals_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_totals();
    }

    public void incrementar_partides_totals_Usuari() {
        this.Usuari.incrementar_partides_totals();
    }

    public double get_puntuacioF_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioF();
    }

    public double get_puntuacioN_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioN();
    }

    public double get_puntuacioD_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioD();
    }

    public double get_puntuacioPvsP_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_puntuacioPvsP();
    }

    public void set_puntuacio_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        Usuari.set_puntuacio();
    }

    public int get_partides_guanyades_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_guanyades();
    }

    public int get_partides_acabades_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_acabades();
    }

    public int get_partides_actuals_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_partides_actuals();
    }

    public vector<int> get_estadistiques_Usuari(String nom_user) {
        Usuari = get_user_by_username(nom_user);
        return Usuari.get_estadistiques();
    }

    public void inicialitza_partida_nova(int id, User codemaker, User codebreaker, dificultat dif, boolean jugador1_es_codemaker) throws Exception {
        if (codebreaker.get_partides_actuals() == 10 || codemaker.get_partides_actuals() == 10)
            throw new Exception("Masses partides actives per part d'algun dels dos jugadors");
        else {
            Partida partida_nova = start_partida_nova(id, codemaker, codebreaker, dif, jugador1_es_codemaker);
            afegir_partida_nova_users(codemaker, codebreaker, partida_nova);
        }

    }

    public void afegir_partida_nova_users(User codemaker, User codebreaker, Partida partida_nova) {
        codemaker.afegir_partida_nova(partida_nova);
        codebreaker.afegir_partida_nova(partida_nova);
    }

    public void afegir_partida_nova_Usuari(Partida partida_nova) throws Exception {
        if (this.Usuari.get_partides_actuals_Usuari() == 10)
            throw new Exception("Masses partides actives per part de l'usuari");
        else this.Usuari.afegir_partida_nova(partida_nova);
    }

    public void elimina_Usuari(User usuari) {
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
    public void set_ajuda() throws AjudaJaDemanada{
        CtrlPartida.set_ajuda();
    }

    public User get_codemaker_partida_actual() {
        return CtrlPartida.get_codemaker_partida_actual();
    }

    public User get_codebraker_partida_actual() {
        return CtrlPartida.get_codebreaker_partida-actual();
    }

    public void jugar_ronda(Sequencia seq_int, Sequencia seq_ver) {
        CtrlPartida.jugar_ronda(seq_int, seq_ver);
    }

    public void tractament_victoria() {
        CtrlPartida.tractament_victoria();
    }

    public void tractament_partida_acabada() {
        CtrlPartida.tractament_partida_acabada();
    }

    public User get_user_by_username(String username) {
        return hashUsers.get(username);
    }

    public Record get_record_by_name(String nom_record) {
        return hashRecord.get(nom_record);
    }

    public int get_punts_record(String nom_record) {
        Record = get_record_by_name(nom_record);
        return Record.get_punts();
    }

    public String get_nom_usuari_record(String nom_record) {
        Record = get_record_by_name(nom_record);
        return Record.get_nom_usuari()
    }

    public Ranking get_ranking_by_name(String ranking_name) {
        return hashRanking.get(ranking_name);
    }

    public void inicialitza_nou_record(String nom_record) {
        Record = new Record(nom_record);
        hashRecord.putIfAbsent(nom_record, Record);
    }

    public void inicialitza_rankings() {
        Ranking = new Ranking("facil");
        hashRecord.put("facil", Ranking);
        Ranking = new Ranking("normal");
        hashRecord.put("normal", Ranking);
        Ranking = new Ranking("dificil");
        hashRecord.put("dificl", Ranking);
        Ranking = new Ranking("PvsP");
        hashRecord.put("PvsP", Ranking);
    }

}