package main.domain.classes;

import java.util.*;

public class User_persona extends User {

    private String password;

    public User_persona(int id, String nom, String password) {
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