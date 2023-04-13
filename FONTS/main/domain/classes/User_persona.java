package main.domain.classes;

import java.util.*;
import main.domain.classes.enumerations.Type_user;


public class User_persona extends User {
    private String password;
    private double puntuacioPvsP;

    //Creació User_persona amb password
    public User_persona(int id, String nom, Type_user tipus_user, String password) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        
        this.password = password;
        this.puntuacioPvsP = 0.0;
    }

    //Creació User_persona sense password
    public User_persona(int id, String nom, Type_user tipus_user) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        this.password = null;
        this.puntuacioPvsP = 0.0;
    }

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

    public double get_puntuacioPvsP() {
        return this.puntuacioPvsP;
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

    public String get_password() {
        return this.password;
    }

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

    public void set_nom(String nom) {
        super.set_nom(nom);
    }

    public void set_password(String password) {
        this.password = password;
    }

    public void set_puntuacio_PvsP(int punts_base, int win_bonus, int punts_penalitzacio_rondes) {
        this.puntuacioPvsP += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
        if (this.puntuacioPvsP < 0) this.puntuacioPvsP = 0;
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

    public boolean validate_password(String password) {
        return this.password == password;
    }




}