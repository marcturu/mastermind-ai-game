package main.domain.classes;

import java.util.*;
import main.domain.classes.enumerations.Type_user;

public class User {
    private int id;
    private String nom;
    private Type_user tipus_user;

    public User(int id, String nom, Type_user tipus_user) {
        this.id = id;
        this.nom = nom;
        this.tipus_user = tipus_user;
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

    public void set_partida_acabada(Partida partida, boolean guanyat) {

    }

}