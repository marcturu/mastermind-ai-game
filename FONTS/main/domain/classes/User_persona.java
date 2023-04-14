package main.domain.classes;

import java.util.*;
import main.domain.classes.enumerations.Type_user;

public class User_persona extends User {
    private String password;
    private double puntuacioPvsP;

    /**
     * Constructor de la classe user_persona (amb password)
     * @param id Identificador de l'usuari_persona
     * @param nom Nom de l'usuari_persona
     * @param tipus_user Tipus d'usuari que és
     * @paarms password Contrasenya de l'usuari_persona
     */
    public User_persona(int id, String nom, Type_user tipus_user, String password) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        
        this.password = password;
        this.puntuacioPvsP = 0.0;
    }

    /**
     * Constructor de la classe User (sense password)
     * @param id Identificador de l'usuari_persona
     * @param nom Nom de l'usuari_persona
     * @param tipus_user Tipus d'usuari que és
     */
    public User_persona(int id, String nom, Type_user tipus_user) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        this.password = null;
        this.puntuacioPvsP = 0.0;
    }

    /**
     * Funció que retorna la constrassenya de tipus PvsP de l'usuari_persona
     * @return password de l'usuari_persona
     */
    public String get_password() {
        return this.password;
    }

    /**
     * Funció que retorna la puntuació de tipus PvsP de l'usuari_persona
     * @return puntuacioPvsP de l'usuari
     */
    public double get_puntuacioPvsP() {
        return this.puntuacioPvsP;
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
        vstats.add(get_puntuacioPvsP());
        vstats.add((double)super.get_num_partides_actuals());
        vstats.add((double)super.get_num_partides_acabades());
        vstats.add((double)super.get_partides_totals());
        vstats.add((double)super.get_partides_guanyades());
        vstats.add((double)super.get_rondes_totals());
        return vstats;
    }

    /**
     * Funció que serveix per realitzar modificacions quan una partida ha acabat: actualitzar la puntuació de l'usuari_persona si la dificultata és PvsP
     * @param password Contrassenya nova a canviar
     */
    public void set_password(String password) {
        this.password = password;
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
     * Funció que serveix per modificar la puntuació PvsP de l'usuari_persona en qúestió segons els paràmetres entrats
     * @param punts_base Punts dels quals aprteix l'usuari al finalitzar una partida
     * @param win_bonus Punts de bonfificació extra si ha guanyat la partida
     * @param punts_penalitzacio_rondes Punts de penalització segons les rondes jugades a la partida
     */
    public void set_puntuacio_PvsP(int punts_base, int win_bonus, int punts_penalitzacio_rondes) {
        this.puntuacioPvsP += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
        if (this.puntuacioPvsP < 0) this.puntuacioPvsP = 0;
    }

    /**
     * Funció que serveix per realitzar modificacions quan una partida ha acabat: actualitzar la puntuació de l'usuari_persona si la dificultata és PvsP
     * @param partida_acabada Objecte Partida que ha finalitzat
     * @param guanyat Booleà que indica si l'usuari ha guanyat la partida acabada
     * @param dificultat Tipus de dificultat de la partida acabada
     */
    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {
        super.set_partida_acabada(partida_acabada, guanyat, dificultat);
        int punts_base = 50;
        int win_bonus = 1;
        int punts_penalitzacio_rondes = partida_acabada.get_ultima_ronda();
        if (guanyat) {
            partides_guanyades++;
            win_bonus = 5;
        }
        if (dificultat == "PvsP") set_puntuacio_PvsP(punts_base, win_bonus, punts_penalitzacio_rondes);
    }

    //Funcions de la superclasse
    public int get_id() {
        return super.get_id();
    }

    public String get_nom() {
        return super.get_nom();
    }

    public Type_user get_tipus_user() {
        return super.get_tipus_user();
    }

    public int get_rondes_totals() {
        return super.get_rondes_totals();
    }

    public int get_partides_totals() {
        return super.get_partides_totals();
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

    public int get_partides_guanyades() {
        return super.get_partides_guanyades();
    }

    public int get_num_partides_acabades() {
        return super.get_num_partides_acabades();
    }

    public int get_num_partides_actuals() {
        return super.get_num_partides_actuals();
    }

    public List<Integer> get_ids_partides_actives() {
        return super.get_ids_partides_actives();
    }

    public List<Integer> get_ids_partides_acabades() {
        return super.get_ids_partides_acabades();
    }

    public Partida get_partida_acabada(int id_partida) throws Exception{
        return super.get_partida_acabada(id_partida);
    }

    public void set_nom(String nom) {
        super.set_nom(nom);
    }

    public void incrementar_rondes_totals() {
        super.incrementar_rondes_totals();
    }

    public void incrementar_partides_totals() {
        super.incrementar_partides_totals();
    }

    public void afegir_partida_nova(Partida partida_nova){
        super.afegir_partida_nova(partida_nova);
    }




}