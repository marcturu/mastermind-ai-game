package main.domain.classes;

import java.util.*;
import main.domain.classes.User;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;

public class User_maquina extends User {
    private boolean genetic_algorithm; //false = five_guess; true = genetic;

    //Creació User_maquina amb genetic_algorithm
    public User_maquina(int id, String nom, Type_user tipus_user, boolean genetic_algorithm, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        this.genetic_algorithm = genetic_algorithm;
    }

    //Creació User_maquina sense genetic_algorithm (no sabem quin tipus d'alorisme)
    public User_maquina(int id, String nom, Type_user tipus_user, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        this.genetic_algorithm = genetic_algorithm;
    }

    public boolean is_genetic() {
        return this.genetic_algorithm;
    }
}