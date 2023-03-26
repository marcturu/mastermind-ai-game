package main.domain.classes;

import java.util.*;

public class User_persona extends User {

    private String password;
    private int rondes_totals;
    private int partides_totals;
    private List llista_partides_no_acabades = new List<Partida>;
    private List llista_partides_acabades = new List<Partida>;

    public User_persona(int id, String nom, Type_user tipus_user, String password) {
        super(id, nom, tipus_user);
        this.password = password;
        this.rondes_totals = 0;
        this.partides_totals = 0;
        this.llista_partides_no_acabades = null;
        this.llista_partides_acabades = null;
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

    public String[] get_estadistiques() {

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

    public void afegir_partida_acabada(Partida partida_acabada) {
        llista_partides_acabades.add(partida_acabada);
        llista_partides_no_acabades.remove(partida_acabada);
    }


}