package main.domain.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.domain.classes.enumerations.Type_user;

public class User {
    protected int id;
    protected String nom;
    protected Type_user tipus_user;
    protected double puntuacioF;
    protected double puntuacioN;
    protected double puntuacioD;

    /**
     * Constructor de la classe User
     * @param id Identificador de l'usuari
     * @param nom Nom de l'usuari
     * @param tipus_user Tipus d'usuari que és
     */
    public User(int id, String nom, Type_user tipus_user) {
        this.id = id;
        this.nom = nom;
        this.tipus_user = tipus_user;
        this.puntuacioF = 0.0;
        this.puntuacioN = 0.0;
        this.puntuacioD = 0.0;
    }

    /**
     * Funció que retorna l'identificador de l'ususari
     * @return L'identificador de l'usuari
     */
    public int get_id() {
        return this.id;
    }

    /**
     * Funció que retorna el nom de l'usuari
     * @return L'identificador de l'usuari
     */
    public String get_nom() {
        return this.nom;
    }

    /**
     * Funció que retorna el tipus de l'suari
     * @return El tipus d'usuari de l'usuari
     */
    public Type_user get_tipus_user() {
        return this.tipus_user;
    }

    /**
     * Funció que retorna la puntuació de tipus fàcil de l'usuari
     * @return puntuacioF de l'usuari
     */
    public double get_puntuacioF() {
        return this.puntuacioF;
    }

    /**
     * Funció que retorna la puntuació de tipus normal de l'usuari
     * @return puntuacioN de l'usuari
     */
    public double get_puntuacioN() {
        return this.puntuacioN;
    }

    /**
     * Funció que retorna la puntuació de tipus difícil de l'usuari
     * @return puntuacioD de l'usuari
     */
    public double get_puntuacioD() {
        return this.puntuacioD;
    }

    /**
     * Funció que serveix per realitzar modificacions quan una partida ha acabat: actualitzar la puntuació de l'usuari, actualitzar les llistes de partides acabades (+1) i no acabades (-1)
     * @param partida_acabada Objecte Partida que ha finalitzat
     * @param guanyat Booleà que indica si l'usuari ha guanyat la partida acabada
     * @param dificultat Tipus de dificultat de la partida acabada
     */
    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {

        double punts = partida_acabada.get_puntuacio();

        if (partida_acabada.jugador2.get_tipus_user() == Type_user.user_persona) set_puntuacio_pvp(punts); //Això vol dir que la partida és pvp
        else set_puntuacio(punts, dificultat);

        if (guanyat) incrementar_partides_guanyades();
        afegeix_partida_acabada(partida_acabada);
        elimina_partida_no_acabada(partida_acabada);
    }

    /**
     * Funció que serveix per modificar les puntuacions fàcils, normals i difícils de l'usuari en qúestió segons els paràmetres entrats
     * @param punts els punts que retorna la partida
     * @param dificultat Tipus de dificultat de la partida jugada
     */
    public void set_puntuacio(Double punts, String dificultat) {
        switch (dificultat) {
            case "1":
            case "facil": {
                this.puntuacioF += punts;
                if (this.puntuacioF < 0) this.puntuacioF = 0;
                break;
            }
            case "2":
            case "normal": {
                this.puntuacioN += punts;
                if (this.puntuacioN < 0) this.puntuacioN = 0;
                break;
            }
            case "3":
            case "dificil": {
                this.puntuacioD += punts;
                if (this.puntuacioD < 0) this.puntuacioD = 0;
                break;
            }
        }
    }

    /**
     * Funció que serveix per afegir una partida passada per paràmetre a la llista de partides no acabades de l'usuari
     * @param id Id que identifica la partida actual a actualitzar
     * @param par Objecte partida a afegir a la llista de partides no acabades
     */
    /* Com que ara només es guarden els ids, no cal efr aquesta funció, no?
    public void actualitza_partida_actual(int id, Partida par) {
        for (int i = 0; i < get_num_partides_actuals(); i++) {
            if ((llista_partides_no_acabades.get(i)).get_id() == id) {
                llista_partides_no_acabades.remove(i);
                llista_partides_no_acabades.add(par);
            }
        }
    } */

    /* --- FUNCIONS DE LES SUBCLASSES --- */

    /**
     * getter del password (redefinida a User_persona)
     * @return res
     */
    public String get_password() {
        return null;
    }

    /**
     * getter de la ratxa (redefinida a User_persona)
     * @return res
     */
    public int get_streak() {
        return 0;
    }

    /**
     * getter de les rondes totals (redefinida a User_persona)
     * @return res
     */
    public int get_rondes_totals() {
        return 0;
    }

    /**
     * getter de les partides totals (redefinida a User_persona)
     * @return res
     */
    public int get_partides_totals() {
        return 0;
    }

    /**
     * getter de les partides guanyades (redefinida a User_persona)
     * @return res
     */
    public int get_partides_guanyades() {
        return 0;
    }

    /**
     * getter del número de partides acabades (redefinida a User_persona)
     * @return res
     */
    public int get_num_partides_acabades() {
        return 0;
    }

    /**
     * getter del número de partides no acabades (redefinida a User_persona)
     * @return res
     */
    public int get_num_partides_actuals() {
        return 0;
    }

    /**
     * getter del número de la llista de ids de les partides acabades (redefinida a User_persona)
     * @return res
     */
    public List<Integer> get_ids_partides_acabades() {
        return null;
    }

    /**
     * getter del número de la llista de ids de les partides no acabades (redefinida a User_persona)
     * @return res
     */
    public List<Integer> get_ids_partides_actives() {
        return null;
    }

    /**
     * Funció que serveix perquè el user_persona pugui retornar les estadístiques
     * @return null
     */
    public Vector<Double> get_estadistiques() {
        return null;
    }

    /**
     * Funció que serveix perquè el user_persona pugui retornar la puntuació de tipus PvsP
     * @return 0.0
     */
    public double get_puntuaciopvp() throws Exception{
        return 0.0;
    }

    /**
     * Funció que serveix per comprovar si un password és el mateix qu eel del usuari (redefinida a User_persona)
     * @return false
     */
    public boolean validate_password(String password) {
        return false;
    }

    /**
     * Redefinida a User_persona
     */
    public void set_password(String password) {}

    /**
     * Redefinida a User_persona
     * @param Nom nou per actualitzar
     */
    public void set_nom(String nom) {
    }

    /**
     * Funció que serveix per incrementar en 1 unitat les rondes totals jugades per part de l'usuari (redefinida a User_persona)
     */
    public void incrementar_rondes_totals() {
    }

    /**
     * Funció que serveix per incrementar en 1 unitat les partides totals jugades per part de l'usuari (redefinida a User_persona)
     */
    public void incrementar_partides_totals() {
    }

    /**
     * Funció que serveix per incrementar en 1 unitat les partides guanyades per part de l'usuari (redefinida a User_persona)
     */
    public void incrementar_partides_guanyades() {
    }

    /**
     * Volem afegir una partida a la llista de partides noves (només important per a user_persona)
     * Es defineix aqui tambe per si el jugador que la crida es un user_maquina
     * @param idp Id que identifica la partida nova
     */
    public void afegir_partida_nova(Partida p){
    }

    /**
     * Volem afegir una partida a la llista de partides acabades (només important per a user_persona)
     * Es defineix aqui tambe per si el jugador que la crida es un user_maquina
     * @param p
     */
    public void afegeix_partida_acabada(Partida p) {}


    /**
     * Volem elimimnar una partida no acabada de la llista de partides no acabades (només important per a user_persona)
     * Es defineix aqui tambe per si el jugador que la crida es un user_maquina
     * @param p
     */
    public void elimina_partida_no_acabada(Partida p) {}


    /**
     * Redefinida a User_persona
     * Es defineix aqui tambe per si el jugador que la crida es un user_maquina
     * @param punts
     */
    public void set_puntuacio_pvp(double punts) {}



}