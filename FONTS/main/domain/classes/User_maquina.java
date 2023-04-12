package main.domain.classes;



//import main.domain.classes.User;
//import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;
import java.util.List;

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

    public int get_id() {
        return super.get_id();
    }

    public String get_nom() {
        return super.get_nom();
    }

    public Type_user get_tipus_user() {
        return super.get_tipus_user();
    }

    public int get_rondes_totals() {
        return super.get_rondes_totals();
    }

    public int get_partides_totals() {
        return super.get_partides_totals();
    }

    public double get_puntuacioF() {
        return super.get_puntuacioF();
    }

    public double get_puntuacioN() {
        return super.get_puntuacioN();
    }

    public double get_puntuacioD() {
        return super.get_puntuacioD();
    }

    public double get_puntuacioPvsP() throws Exception {
        throw new Exception("El user maquina no té punts PvsP");
    }

    public int get_partides_guanyades() {
        return super.get_partides_guanyades();
    }

    public int get_num_partides_acabades() {
        return super.get_num_partides_acabades();
    }

    public int get_num_partides_actuals() {
        return super.get_num_partides_actuals();
    }

    public List<Integer> get_ids_partides_actives() {
        return super.get_ids_partides_actives();
    }

    public List<Integer> get_ids_partides_acabades() {
        return super.get_ids_partides_acabades();
    }

    public Partida get_partida_acabada(int id_partida) throws Exception{
        return super.get_partida_acabada(id_partida);
    }

    //Ja mires que no sigui PvsP a la superclasse
    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {
        super.set_partida_acabada(partida_acabada, guanyat, dificultat);
    }

    public void incrementar_rondes_totals() {
        super.incrementar_rondes_totals();
    }

    public void incrementar_partides_totals() {
        super.incrementar_partides_totals();
    }

    public void afegir_partida_nova(Partida partida_nova) {
        super.afegir_partida_nova(partida_nova);
    }



}