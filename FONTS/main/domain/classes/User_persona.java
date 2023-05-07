package main.domain.classes;

import java.util.*;
import main.domain.classes.enumerations.Type_user;

public class User_persona extends User {
    private String password;
    private double puntuaciopvp;
    private int streak;
    private int num_rondes_totals;
    private int num_partides_totals;
    private int num_partides_guanyades;
    protected ArrayList<Integer> llista_partides_no_acabades;
    protected ArrayList<Integer> llista_partides_acabades;

    /**
     * Constructor de la classe user_persona (amb password)
     * @param id Identificador de l'usuari_persona
     * @param nom Nom de l'usuari_persona
     * @param tipus_user Tipus d'usuari que és
     * @paarms password Contrasenya de l'usuari_persona
     */
    public User_persona(int id, String nom, Type_user tipus_user, String password) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, num_partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        
        this.password = password;
        this.puntuaciopvp = 0.0;
        this.streak = 0;
        this.num_rondes_totals = 0;
        this.num_partides_totals = 0;
        this.num_partides_guanyades = 0;
        this.llista_partides_no_acabades = new ArrayList<Integer>();
        this.llista_partides_acabades = new ArrayList<Integer>();
    }

    /**
     * Constructor de la classe User (sense password)
     * @param id Identificador de l'usuari_persona
     * @param nom Nom de l'usuari_persona
     * @param tipus_user Tipus d'usuari que és
     */
    public User_persona(int id, String nom, Type_user tipus_user) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, num_partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);

        this.password = null;
        this.puntuaciopvp = 0.0;
        this.streak = 0;
        this.num_rondes_totals = 0;
        this.num_partides_totals = 0;
        this.num_partides_guanyades = 0;
        this.llista_partides_no_acabades = new ArrayList<Integer>();
        this.llista_partides_acabades = new ArrayList<Integer>();
    }

    /**
     * Funció que retorna la constrassenya de tipus pvp de l'usuari_persona
     * @return password de l'usuari_persona
     */
    public String get_password() {
        return this.password;
    }

    /**
     * getter de la "ratxa" d'un usuari
     * @return el número de partides seguides que ha guanyat un usuari
     */
    public int get_streak() {
        return this.streak;
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
        return this.num_partides_guanyades;
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
     * Funció que retorna els ids de les partides acabades per part de l'usuari
     * @return Llista d'ids de les partides acabades
     */
    public List<Integer> get_ids_partides_acabades() {
        return llista_partides_acabades;
    }

    /**
     * Funció que retorna els ids de les partides no acabades per part de l'usuari
     * @return Llista d'ids de les partides no acabades
     */
    public List<Integer> get_ids_partides_actives() {
        return llista_partides_no_acabades;
    }

    /**
     * Funció que retorna la puntuació de tipus pvp de l'usuari_persona
     * @return puntuaciopvp de l'usuari
     */
    public double get_puntuaciopvp() {
        return this.puntuaciopvp;
    }

    /**
     * Funció que retorna certes estadístiques de l'usuari com les seves puntuacions, partides guanyades, totals,...
     * @return vector que conté informació sobre estadístiques de l'usuari
     */
    public Vector<Double> get_estadistiques() {
        Vector<Double> vstats = new Vector<Double>();
        vstats.add(super.get_puntuacioF());
        vstats.add(super.get_puntuacioN());
        vstats.add(super.get_puntuacioD());
        vstats.add(get_puntuaciopvp());
        vstats.add((double)super.get_num_partides_actuals());
        vstats.add((double)super.get_num_partides_acabades());
        vstats.add((double) get_partides_totals());
        vstats.add((double) get_partides_guanyades());
        vstats.add((double) get_streak());
        vstats.add((double) (get_rondes_totals()/get_partides_totals()));

        return vstats;
    }

    /**
     * Funció que serveix per veure si la contrassenya entrada per paràmetre és la mateixa que la de l'usuari_persona
     * @param password Contrassenya que volem validar
     * @return Booleà que indica si la constrassenya entrada per paràmetre és la mateixa que la de l'usuari_persona
     */
    public boolean validate_password(String password) {
        return this.password == password;
    }

    /**
     * Funció que serveix per realitzar modificacions quan una partida ha acabat: actualitzar la puntuació de l'usuari_persona si la dificultata és pvp
     * @param password Contrassenya nova a canviar
     */
    public void set_password(String password) {
        this.password = password;
    }

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
     * Funció que serveix per incrementar en 1 unitat les partides guanyades per part de l'usuari
     */
    public void incrementar_partides_guanyades() {
        this.num_partides_guanyades++;
    }

    /**
     * Funció que serveix per incrementar en 1 unitat el streak (ratxa) de l'usuari quan ha guanyat
     */
    public void incrementar_streak() {
        this.streak++;
    }

    /**
     * Funció que serveix per reiniciar el streak (ratxa) de l'usuari quan ha perdut
     */
    public void reiniciar_streak() {
        this.streak = 0;
    }

    /**
     * Funció que serveix per afegir una partida passada per paràmetre a la llista de partides no acabades de l'usuari
     * @param partida_nova Partida a afegir a la llista de partides no acabades de l'usuari
     */
    public void afegir_partida_nova(Partida p){
        int idp = p.get_id();
        this.llista_partides_no_acabades.add(idp);
        incrementar_partides_totals();
    }

    /**
     * Afegim una partida a la llista de partides acabades
     * @param p partida que volem afegir a la llista de partides acabades
     */
    public void afegeix_partida_acabada(Partida p) {
        int idp = p.get_id();
        this.llista_partides_acabades.add(idp);
    }

    /**
     * Funció que serveix per afegir una partida passada per paràmetre a la llista de partides no acabades de l'usuari
     * @param partida_nova Partida a afegir a la llista de partides no acabades de l'usuari
     */
    public void elimina_partida_no_acabada(Partida p){
        int idp = p.get_id();
        this.llista_partides_no_acabades.remove(idp);
    }

    /**
     * Funció que serveix per modificar la puntuació pvp de l'usuari_persona en qúestió segons els paràmetres entrats
     * @param punts_base Punts dels quals aprteix l'usuari al finalitzar una partida
     * @param win_bonus Punts de bonfificació extra si ha guanyat la partida
     * @param punts_penalitzacio_rondes Punts de penalització segons les rondes jugades a la partida
     */
    public void set_puntuacio_pvp(double punts) {
        this.puntuaciopvp += punts;
        if (this.puntuaciopvp < 0) this.puntuaciopvp = 0;
    }

    


    /* --- FUNCIONS DE LA SUPERCLASSE --- */


    public int get_id() {
        return super.get_id();
    }

    public String get_nom() {
        return super.get_nom();
    }

    public Type_user get_tipus_user() {
        return super.get_tipus_user();
    }

    public double get_puntuacioF() {
        return super.get_puntuacioF();
    }

    public double get_puntuacioN() {
        return super.get_puntuacioN();
    }

    public double get_puntuacioD() {
        return super.get_puntuacioD();
    }

    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {
        super.set_partida_acabada(partida_acabada, guanyat, dificultat);
    }

    public void set_puntuacio(Double punts, String dificultat) {
        super.set_puntuacio(punts, dificultat);
    }



}