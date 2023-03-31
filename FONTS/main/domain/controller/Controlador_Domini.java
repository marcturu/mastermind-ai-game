package main.domain.controllers;

import main.domain.classes.Partida;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.Ronda;
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
        this.usuari = null;
        this.CtrlPartida = new Controlador_Partida();
        this.hashUsers = new hashUsers<>();
        this.CtrlPartida =
        this.hashUsers = new HashMap<String,User>();

    }

    public Controlador_Partida get_Ctrl_Partida() {
        return CtrlPartida;
    }

    /**
     * @param id
     * @param nom
     * @param tipus_user
     * Crea una partida nova amb els parametres entrats. Aquests parametres han d'estar conprovats abans de cridar a la funció.
     */

    public Controlador_Domini(){
        this.Usuari = null;
        this.Record = null;
        this.Ranking = null;

    }

    //Pre: Es rep un nom d'usuari d'usuari i un tipus d'usuari (de moment no es té en compte ni la contrassenya, si és persona, ni el tipus d'alogorisme, si és màquina)
    //Post: Es crea el usuari amb el nom i tipus rebut com a parametre i s'afageix al map.
    public void iniciaInstanciaUser(String nom, Type_user tipus_user) { //quan es treballi amb log in es passarà també la contrasenya.
        List<Partida> llista_partides_no_acabades;
        List<Partida> llista_partides_acabades;
        if (string.equals("User_persona")) Usuari = new User_persona(hashUsers.size()+1, nom, tipus_user, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        else Usuari = new User_maquina(hashUsers.size()+1, nom, tipus_user, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        hashUsers.put(string, Usuari);
    }

    public static Controlador_Domini get_CtrlDomini {
        if (singletonObject == null) singletonObject = new Controlador_Domini();
        return singletonObject;
    }
    public void creacioRecord(String nom_record) {
        this.Record = new Record(nom_record);
    }

    public boolean es_record(int punts, String nom_usuari) {
        this.Record.check_if_record(punts, nom_usuari);
    }

    public void elimina_usuari(User usuari) {
        hashUsers.remove(usuari.get_nom(), usuari);
    }







}