package main.domain.classes;

import java.util.*;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;


public class User {
    protected int id;
    protected String nom;
    protected Type_user tipus_user;
    protected int num_rondes_totals;
    protected int num_partides_totals;
    protected double puntuacio; //en %
    protected int partides_guanyades;
    protected List<Partida> llista_partides_no_acabades;
    protected List<Partida> llista_partides_acabades;

    // INICIALITZADOR CLASSE USER GLOBAL
    public User(int id, String nom, Type_user tipus_user) {
        this.id = id;
        this.nom = nom;
        this.tipus_user = tipus_user;
        this.num_rondes_totals = 0;
        this.num_partides_totals = 0;
        this.puntuacio = 0.0;
        this.partides_guanyades = 0;
        this.llista_partides_no_acabades = new ArrayList<Partida>;
        this.llista_partides_acabades = new ArrayList<Partida>;
    }

    public int get_id() {
        return id;
    }

    public String get_nom() {
        return nom;
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public Type_user get_tipus_user() {
        return tipus_user;
    }

    public int get_rondes_totals() {
        return rondes_totals;
    }

    public void incrementar_rondes_totals() {
        rondes_totals++;
    }

    public int get_partides_totals() {
        return partides_totals;
    }

    public void incrementar_partides_totals() {
        partides_totals++;
    }

    public float get_puntuacio() {
        return puntuacio;
    }

    public void set_puntuacio() {
        puntuacio = (get_rondes_totals()/get_partides_totals())*100;
    }

    public int get_partides_guanyades() {
        return partides_guanyades;
    }

    public int get_partides_acabades() {
        return llista_partides_acabades.size();
    }

    public int get_partides_actuals() {
        return llista_partides_no_acabades.size();
    }

    public Vector<int> get_estadistiques() {
        Vector<int> vstats;
        vstats.add(get_puntuacio());
        vstats.add(get_partides_actuals());
        vstats.add(get_partides_acabades());
        vstats.add(get_partides_totals());
        vstats.add(get_partides_guanyades());
        vstats.add(get_rondes_totals());
        return vstats;
    }

    public void afegir_partida_nova(Partida partida_nova) throws Exception {
        if (llista_partides_no_acabades.size() == 10) throw new Exception("¡ Masses partides no acabades !");
        else {
            llista_partides_no_acabades.add(partida_nova);
            incrementar_partides_totals();
        }
    }

    public void set_partida_acabada(Partida partida_acabada, boolean guanyat) {
        llista_partides_acabades.add(partida_acabada);
        llista_partides_no_acabades.remove(partida_acabada);
        if (guanyat) partides_guanyades++;
    }

    /* No fa falta?
    // FUNCIONS ESPECIFICAMENT DE USER_PERSONA
    public void set_password(String password) {}

    public boolean validate_password(String password) {}

    //FUNCIONS ESPECIFICAMENT DE USER_MAQUINA
    public boolean is_genetic() {}
    */

}