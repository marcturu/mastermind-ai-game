package main.domain.classes;



//import main.domain.classes.User;
//import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;

public class User_maquina extends User {
    private boolean genetic_algorithm; //false = five_guess; true = genetic;

    //Creació User_maquina amb genetic_algorithm
    public User_maquina(int id, String nom, Type_user tipus_user, boolean genetic_algorithm) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        this.genetic_algorithm = genetic_algorithm;
    }

    //Creació User_maquina sense genetic_algorithm (no sabem quin tipus d'alorisme)
    /* 
    public User_maquina(int id, String nom, Type_user tipus_user) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        
        //this.genetic_algorithm = (?);
    }
    */
    public boolean is_genetic() {
        return this.genetic_algorithm;
    }

    public double get_puntuacioPvsP() throws Exception{
        throw new Exception ("El user maquina no té punts PvsP");
    }
}