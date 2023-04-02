package main.domain.controllers;

import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.Ranking;
import main.domain.classes.Record;
import main.domain.controller.Controlador.Partida;
import main.domain.classes.enumerations.Type_user;

import java.util.*;

/**
 * Classe del Controlador de Domin
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class Controlador_Domini {
    private User Usuari;
    private Record Record;
    private Ranking Ranking;
    private Controlador_Partida CtrlPartida;
    private HashMap<String,User> hashUsers;
    private static Controlador_Domini singletonObject;

    public Controlador_Domini() {
        this.Usuari = null;
        this.Record = null;
        this.Ranking = null;
        this.CtrlPartida = new Controlador_Partida();
        this.hashUsers = new hashUsers<>();
        this.hashUsers = new HashMap<String,User>();

    }

    public Controlador_Partida get_Ctrl_Partida() {
        return CtrlPartida;
    }


    //Pre: Es rep un nom d'usuari d'usuari i un password
    //Post: Es crea el usuari amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitzaUserPersona(String nom, String password) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari = new User_persona(hashUsers.size()+1, nom, Type_user.user_persona, password, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.put(nom, Usuari);
    }

    //Pre: Es rep un nom d'usuari.
    //Post: Es crea el usuari (maquina genetic) amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitza_UserMaquina_genetic(String nom) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari = new User_maquina(hashUsers.size()+1, nom, Type_user.user_persona, true, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.put(nom, Usuari);
    }

    //Pre: Es rep un nom d'usuari.
    //Post: Es crea el usuari (maquina five-guess) amb els paràmetres entrats i els altres que li falten i s'afageix al map.
    public void inicialitza_UserMaquina_fiveguess(String nom) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        Usuari = new User_maquina(hashUsers.size()+1, nom, Type_user.user_persona, false, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.put(nom, Usuari);
    }

    public static Controlador_Domini get_CtrlDomini {
        if (singletonObject == null) singletonObject = new Controlador_Domini();
        return singletonObject;
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
     *
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

    public int get_rondes_totals_Usuari() {
        return this.Usuari.get_rondes_totals();
    }

    public void incrementar_rondes_totals_Usuari() {
        this.Usuari.incrementar_rondes_totals();
    }

    public int get_partides_totals_Usuari() {
        return this.Usuari.get_partides_totals();
    }

    public void incrementar_partides_totals_Usuari() {
        this.Usuari.incrementar_partides_totals();
    }

    public double get_puntuacio_Usuari() {
        return this.Usuari.get_puntuacio();
    }

    public void set_puntuacio_Usuari() {
        this.Usuari.set_puntuacio();
    }

    public int get_partides_guanyades_Usuari() {
        return this.Usuari.get_partides_guanyades();
    }

    public int get_partides_acabades_Usuari() {
        return this.Usuari.get_partides_acabades();
    }

    public int get_partides_actuals_Usuari() {
        return this.Usuari.get_partides_actuals();
    }

    public vector<int> get_estadistiques_Usuari() {
        return this.Usuari.get_estadistiques();
    }

    /**
     * @throws java.lang.Exception
     */
    public void afegir_partida_nova_Usuari(Partida partida) throws Exception {
        this.Usuari.afegir_partida_nova(partida);
    }

    public void elimina_Usuari(User usuari) {
        hashUsers.remove(usuari.get_nom(), usuari);
    }







}