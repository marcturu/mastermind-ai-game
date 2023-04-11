package main.domain.classes;

import java.util.*;
//import main.domain.classes.User;
//import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;


public class User_persona extends User {
    private String password;
    private double puntuacioPvsP;

    //Creació User_persona amb password
    public User_persona(int id, String nom, Type_user tipus_user, String password, int num_rondes_totals, int num_partides_totals, int puntuacioF, int puntuacioN, int puntuacioD, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        
        this.password = password;
        this.puntuacioPvsP = 0.0;
    }

    //Creació User_persona sense password
    public User_persona(int id, String nom, Type_user tipus_user, int num_rondes_totals, int num_partides_totals, int puntuacioF, int puntuacioN, int puntuacioD, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacioF, puntuacioN, puntuacioD, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        this.password = null;
        this.puntuacioPvsP = 0.0;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public boolean validate_password(String password) {
        return this.password == password;
    }

    public String get_password() {
        return this.password;
    }

    public double get_puntuacioPvsP() {
        return this.puntuacioPvsP;
    }

    public void set_puntuacio_PvsP(int punts_base, int win_bonus, int punts_penalitzacio_rondes) {
        this.puntuacioPvsP += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
        if (this.puntuacioPvsP < 0) this.puntuacioPvsP = 0;
    }

    public Vector<Double> get_estadistiques() {
        Vector<Double> vstats = new Vector<Double>();
        vstats.add(get_puntuacioF());
        vstats.add(get_puntuacioN());
        vstats.add(get_puntuacioD());
        vstats.add(get_puntuacioPvsP());
        vstats.add((double)get_num_partides_actuals());
        vstats.add((double)get_num_partides_acabades());
        vstats.add((double)get_partides_totals());
        vstats.add((double)get_partides_guanyades());
        vstats.add((double)get_rondes_totals());
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

}