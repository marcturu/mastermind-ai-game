package main.domain.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import main.domain.classes.enumerations.Type_user;

public class User {
    protected int id;
    protected String nom;
    protected Type_user tipus_user;
    protected int num_rondes_totals;
    protected int num_partides_totals;
    protected double puntuacioF;
    protected double puntuacioN;
    protected double puntuacioD;
    protected int partides_guanyades;
    protected ArrayList<Partida> llista_partides_no_acabades;
    protected ArrayList<Partida> llista_partides_acabades;


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
        this.num_rondes_totals = 0;
        this.num_partides_totals = 0;
        this.puntuacioF = 0.0;
        this.puntuacioN = 0.0;
        this.puntuacioD = 0.0;
        this.partides_guanyades = 0;
        this.llista_partides_no_acabades = new ArrayList<Partida>();
        this.llista_partides_acabades = new ArrayList<Partida>();
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
     * getter de la ratxa (redefinida a User_persona)
     * @return res
     */
    public int get_streak() {
        return 0;
    }

    /**
     * Funció que retorna el tipus de l'suari
     * @return El tipus d'usuari de l'usuari
     */
    public Type_user get_tipus_user() {
        return this.tipus_user;
    }

    /**
     * Funció que retorna les rondes totals jugades per l'usuari
     * @return Rondes totals jugades per l'usuari
     */
    public int get_rondes_totals() {
        return this.num_rondes_totals;
    }

    /**
     * Funció que retorna les partides totals jugades per l'usuari
     * @return Partides totals jugades per l'usuari
     */
    public int get_partides_totals() {
        return this.num_partides_totals;
    }

    /**
     * Funció que retorna les partides guanyades de l'usuari
     * @return Partides guanyades de l'usuari
     */
    public int get_partides_guanyades() {
        return this.partides_guanyades;
    }

    /**
     * Funció que retorna el número de partides acabades de l'usuari
     * @return Número de partides acabades de l'usuari
     */
    public int get_num_partides_acabades() {
        return llista_partides_acabades.size();
    }

    /**
     * Funció que retorna el número de partides no avabades de l'usuari
     * @return Número de partides no acabades de l'usuari
     */
    public int get_num_partides_actuals() {
        return llista_partides_no_acabades.size();
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
     * Funció que retorna els ids de les partides acabades per part de l'usuari
     * @return Llista d'ids de les partides acabades
     */
    public List<Integer> get_ids_partides_acabades() {
        ArrayList<Integer> llista = new ArrayList<Integer>();
        for (int i = 0; i < llista_partides_acabades.size(); i++) {
            llista.add((llista_partides_acabades.get(i)).get_id());
        }
        return llista;
    }

    /**
     * Funció que retorna els ids de les partides no acabades per part de l'usuari
     * @return Llista d'ids de les partides no acabades
     */
    public List<Integer> get_ids_partides_actives() {
        ArrayList<Integer> llista = new ArrayList<Integer>();
        for (int i = 0; i < llista_partides_no_acabades.size(); i++) {
            llista.add((llista_partides_no_acabades.get(i)).get_id());
        }
        return llista;
    }

    /**
     * Funció que retorna la partida acabada identificada per l'id passat per paràmetre que pertany a la llista de partides acabades
     * @param id_partida Id que identifica la partida a retornar
     * @return Objecte partida acabada identificat pel paràmetre d'entrada
     */
    public Partida get_partida_acabada(int id_partida) throws Exception{
        for (int i = 0; i < get_num_partides_acabades(); i++) {
            if ((llista_partides_acabades.get(i)).get_id() == id_partida) return llista_partides_acabades.get(i);
        }
        throw new Exception("No esta la partida");
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
    public double get_puntuacioPvsP() throws Exception{
        return 0.0;
    }

    /**
     * Funció que serveix perquè es pugui cridar a la resolució de la partida amb l'algoritme dins de user_maquina
     * @return null
     */
    public List<List<Integer>> get_solve_maquina(List<Integer> sol) {
        return null;
    }

    /**
     * Funció que serveix per actualitazr el nom de l'usuari
     * @param nom Nom nou per actualitzar
     */
    public void set_nom(String nom) {
        this.nom = nom;
    }

    /**
     * Funció que serveix per incrementar en 1 unitat les rondes totals jugades per part de l'usuari
     */
    public void incrementar_rondes_totals() {
        this.num_rondes_totals++;
    }

    /**
     * Funció que serveix per incrementar en 1 unitat les partides totals jugades per part de l'usuari
     */
    public void incrementar_partides_totals() {
        this.num_partides_totals++;
    }

    /**
     * Funció que serveix per realitzar modificacions quan una partida ha acabat: actualitzar la puntuació de l'usuari, actualitzar les llistes de aprtides acabades (+1) i no acabades (-1)
     * @param partida_acabada Objecte Partida que ha finalitzat
     * @param guanyat Booleà que indica si l'usuari ha guanyat la partida acabada
     * @param dificultat Tipus de dificultat de la partida acabada
     */
    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {

        double punts = partida_acabada.get_puntuacio();

        if (dificultat != "PvsP") set_puntuacio(punts, dificultat);

        //Crida a ranking (F, N, D, PvsP) per actualitzar-lo


        llista_partides_acabades.add(partida_acabada);
        llista_partides_no_acabades.remove(partida_acabada);
    }

    /**
     * Volem afegir una partida a la llista de partides acabades (només important per a user_persona)
     * Es defineix aqui tambe per si el jugador que la crida es un user_maquina
     * @param p
     */
    public void afegeix_partida_acabada(Partida p) {}

    /**
     * Funció que serveix per modificar les puntuacions fàcils, normals i difícils de l'usuari en qúestió segons els paràmetres entrats
     * @param punts els punts que retorna la partida
     * @param dificultat Tipus de dificultat de la partida jugada
     */
    public void set_puntuacio(Double punts, String dificultat) {
        switch (dificultat) {
            case "1":
            case "facil": {
                if(punts > this.puntuacioF) this.puntuacioF += punts;
                if (this.puntuacioF < 0) this.puntuacioF = 0;
                break;
            }
            case "2":
            case "normal": {
                if(punts > this.puntuacioN) this.puntuacioN += punts;
                if (this.puntuacioN < 0) this.puntuacioN = 0;
                break;
            }
            case "3":
            case "dificil": {
                if(punts > this.puntuacioD) this.puntuacioD += punts;
                if (this.puntuacioD < 0) this.puntuacioD = 0;
                break;
            }
        }
    }

    /**
     * Funció que serveix per afegir una partida passada per paràmetre a la llista de partides no acabades de l'usuari
     * @param partida_nova Partida a afegir a la llista de partides no acabades de l'usuari
     */
    public void afegir_partida_nova(Partida partida_nova){
        llista_partides_no_acabades.add(partida_nova);
        incrementar_partides_totals();
    }

    /**
     * Funció que serveix per afegir una partida passada per paràmetre a la llista de partides no acabades de l'usuari
     * @param id Id que identifica la partida actual a actualitzar
     * @param par Objecte partida a afegir a la llista de partides no acabades
     */
    public void actualitza_partida_actual(int id, Partida par) {
        for (int i = 0; i < get_num_partides_actuals(); i++) {
            if ((llista_partides_no_acabades.get(i)).get_id() == id) {
                llista_partides_no_acabades.remove(i);
                llista_partides_no_acabades.add(par);
            }
        }
    }

    public boolean validate_password(String password) {
        return false;
    }

}