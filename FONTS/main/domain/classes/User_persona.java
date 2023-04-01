package main.domain.classes;

import java.util.*;
import main.domain.classes.User;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;


public class User_persona extends User {
    private String password;

    //Creació User_persona amb password
    public User_persona(int id, String nom, Type_user tipus_user, String password, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        this.password = password;
    }

    //Creació User_persona sense password
    public User_persona(int id, String nom, Type_user tipus_user, int num_rondes_totals, int num_partides_totals, int puntuacio, int partides_guanyades, List<Partida> llista_partides_no_acabades, List<Partida> llista_partides_acabades) {
        super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        this.password = password;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public boolean validate_password(String password) {
        return this.password == password;
    }


}