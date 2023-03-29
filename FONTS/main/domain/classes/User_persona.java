package main.domain.classes;

import java.util.*;

public class User_persona extends User {

    private String password;
    private int rondes_totals;
    private int partides_totals;
    private int partides_guanyades;
    private List<Partida> llista_partides_no_acabades;
    private List<Partida> llista_partides_acabades;

    public User_persona(int id, String nom, String password) {
        super(id, nom);
        this.password = password;
        this.rondes_totals = 0;
        this.partides_totals = 0;
        this.partides_guanyades = 0;
        this.llista_partides_no_acabades = new LinkedList<Partida>;
        this.llista_partides_acabades = new LinkedList<Partida>;
    }

    public int get_partides_acabades() {
        return llista_partides_acabades.size();
    }

    public int get_partides_actuals() {
        return llista_partides_no_acabades.size();
    }

    public int get_rondes_totals() {
        return rondes_totals;
    }

    public int get_partides_totals() {
        return partides_totals;
    }

    public int get_partides_guanyades() {
        return partides_guanyades;
    }

    public Vector<int> get_estadistiques() {
        Vector<int> vstats = new Vector<int>();
            vstats.add(get_partides_acabades());
            vstats.add(get_partides_actuals());
            vstats.add(get_rondes_totals());
            vstats.add(get_partides_totals());
            vstats.add(get_partides_guanyades()/get_partides_totals());
            return vstats;
    }

    public void incrementar_rondes_totals() {
        rondes_totals++;
    }

    public void incrementar_partides_totals() {
        partides_totals++;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public boolean validate_password(String password) {
        return this.password == password;
    }

    public void afegir_partida_nova(Partida partida_nova) {
        llista_partides_no_acabades.add(partida_nova);
        incrementar_partides_totals();
    }

    public void set_partida_acabada(Partida partida_acabada, boolean guanyat) {
        llista_partides_acabades.add(partida_acabada);
        llista_partides_no_acabades.remove(partida_acabada);
        if (guanyat) partides_guanyades++;
    }


}