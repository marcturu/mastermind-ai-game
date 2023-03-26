package main.domain.classes;

import java.util.*;

public class User_maquina extends User {
    private boolean genetic_algorithm; //0 = five_guess; 1 = genetic;

    public User_maquina(int id, String nom, Type_user tipus_user, boolean genetic_algorithm) {
        super(id, nom, tipus_user);
        this.genetic_algorithm = genetic_algorithm;
    }

    public boolean is_genetic() {
        return genetic_algorithm;
    }
}