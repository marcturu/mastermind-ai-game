package main.domain.classes;

import java.util.*;

public class User_maquina extends User {
    private boolean genetic_algorithm; //0 = five_guess; 1 = genetic;

    public User_maquina(int id, String nom, boolean genetic_algorithm) {
        super(id, nom);
        this.genetic_algorithm = genetic_algorithm;
    }

    public boolean is_genetic() {
        return genetic_algorithm;
    }
}