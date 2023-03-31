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
    private User_persona Usuari_perosna;
    private User_maquina Usuari_maquina;
    private Record Record;
    private Ranking Ranking;
    private Controlador_Partida CtrlPartida;
    private HashMap<string,User> hashUsers;

    public Controlador_Domini() {
        this.usuari = null;
        this.CtrlPartida = new Controlador_Partida();
        this.hashUsers = new hashUsers<>();

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

    // CREADORA SI ÉS USER_PERSONA
    public void creacioUsuari(int id, String nom, Type_user tipus_user, String password, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        this.Usuari_persona = new User_persona(id, string, tipus_user, password, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        //hashUsers.put(nom, Usuari);
    }

    // CREADORA SI ÉS USER_MAQUINA
    public void creacioUsuari(int id, String nom, Type_user tipus_user, boolean genetic_algorithm, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        this.Usuari_maquina = new User_maquina(id, string, tipus_user, genetic_algorithm, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        //hashUsers.put(nom, Usuari);
    }

    public void creacioUsuari(int id, String nom, Type_user tipus_user) {
        this.Usuari = new User(id, string, tipus_user);
        hashUsers.put(nom, Usuari);
    }

    public void creacioRecord(String nom_record) {
        this.Record = new Record(nom_record);
    }

    public boolean es_record(int punts, String nom_usuari) {
        this.Record.check_if_record(punts, nom_usuari);
    }





}