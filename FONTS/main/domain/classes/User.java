package main.domain.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//import main.domain.classes.Ranking;
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


    // INICIALITZADOR CLASSE USER GLOBAL
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

    public int get_id() {
        return this.id;
    }

    public String get_nom() {
        return this.nom;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public Type_user get_tipus_user() {
        return this.tipus_user;
    }

    public int get_rondes_totals() {
        return this.num_rondes_totals;
    }

    public void incrementar_rondes_totals() {
        this.num_rondes_totals++;
    }

    public int get_partides_totals() {
        return this.num_partides_totals;
    }

    public void incrementar_partides_totals() {
        this.num_partides_totals++;
    }

    public double get_puntuacioF() {
        return this.puntuacioF;
    }

    public double get_puntuacioN() {
        return this.puntuacioN;
    }

    public double get_puntuacioD() {
        return this.puntuacioD;
    }

    public void set_puntuacio(int punts_base, int win_bonus, int punts_penalitzacio_rondes, String dificultat) {
        switch (dificultat) {
            case "1":
            case "facil": {
                this.puntuacioF += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
                if (this.puntuacioF < 0) this.puntuacioF = 0;
                break;
            }
            case "2":
            case "normal": {
                this.puntuacioN += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
                if (this.puntuacioN < 0) this.puntuacioN = 0;
                break;
            }
            case "3":
            case "dificil": {
                this.puntuacioD += (punts_base * win_bonus) - (punts_penalitzacio_rondes * 5);
                if (this.puntuacioD < 0) this.puntuacioD = 0;
                break;
            }
        }
    }

    public int get_partides_guanyades() {
        return this.partides_guanyades;
    }

    public int get_num_partides_acabades() {
        return llista_partides_acabades.size();
    }

    public int get_num_partides_actuals() {
        return llista_partides_no_acabades.size();
    }

    public List<Integer> get_ids_partides_actives() {
        ArrayList<Integer> llista = new ArrayList<Integer>();
        for (int i = 0; i < llista_partides_no_acabades.size(); i++) {
            llista.add((llista_partides_no_acabades.get(0)).get_id());
        }
        return llista;
    }

    public List<Integer> get_ids_partides_acabades() {
        ArrayList<Integer> llista = new ArrayList<Integer>();
        for (int i = 0; i < llista_partides_acabades.size(); i++) {
            llista.add((llista_partides_acabades.get(i)).get_id());
        }
        return llista;
    }

    public Vector<Double> get_estadistiques() {
        return null;
    }

    public void afegir_partida_nova(Partida partida_nova){
        llista_partides_no_acabades.add(partida_nova);
        incrementar_partides_totals();
    }

    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {
        
        int punts_base = 50;
        int win_bonus = 1;
        int punts_penalitzacio_rondes = partida_acabada.get_ultima_ronda();
        if (guanyat) {
            partides_guanyades++;
            win_bonus = 5;
        }
        if (dificultat != "PvsP") set_puntuacio(punts_base, win_bonus, punts_penalitzacio_rondes, dificultat);

        //Crida a ranking (F, N, D, PvsP) per actualitzar-lo


        llista_partides_acabades.add(partida_acabada);
        llista_partides_no_acabades.remove(partida_acabada);
    }

    public Partida get_partida_acabada(int id_partida) throws Exception{
        for (int i = 0; i < get_num_partides_acabades(); i++) {
            if ((llista_partides_acabades.get(i)).get_id() == id_partida) return llista_partides_acabades.get(i);
        }
        throw new Exception("No esta la partida");
    }

    public double get_puntuacioPvsP() throws Exception{
        return 0.0;
    }

    public List<List<Integer>> get_solve_maquina(List<Integer> sol) {
        return null;
    }

    /*
    //USER_PERSONA
    public void set_password(String password) {}

    public boolean validate_password(String password) {}



    public void set_puntuacio_PvsP(int punts_base, int win_bonus, int punts_penalitzacio_rondes) {}

    //USER_MAQUINA
    public boolean is_genetic() {}
    */

}