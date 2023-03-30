package main.domain.classes;

import java.util.*;

public class User_maquina extends User {
    private boolean genetic_algorithm; //0 = five_guess; 1 = genetic;

    public User_maquina(int id, String nom, boolean genetic_algorithm) {
        super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        this.genetic_algorithm = genetic_algorithm;
    }

    public boolean is_genetic() {
        return genetic_algorithm;
    }
}