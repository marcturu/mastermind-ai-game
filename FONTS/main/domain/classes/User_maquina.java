package main.domain.classes;

import main.domain.classes.algorismes.*;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;
import java.util.List;

public class User_maquina extends User {
    private boolean genetic_algorithm;

    private dificultats dificultat;

    /**
     * Constructor de la classe user_maquina
     * @param id Identificador de l'usuari_maquina
     * @param nom Nom de l'usuari_maquina
     * @param tipus_user Tipus d'usuari que és
     * @paarms algorithm Tipus d'algoritme que és (true = genetic; false = five_guess)
     */
    public User_maquina(int id, String nom, Type_user tipus_user, boolean algorithm) {
        //super(id, nom, tipus_user, num_rondes_totals, num_partides_totals, puntuacio, partides_guanyades, llista_partides_no_acabades, llista_partides_acabades);
        super(id, nom, tipus_user);
        this.genetic_algorithm = algorithm;
    }

    /**
     * Funció que serveix per veure de quin tipus d'algoritme és l'usuari maquina
     * @return Si l'algoritme de l'usuari màquina és genetic (true) o no (false)
     */
    public boolean is_genetic() {
        return this.genetic_algorithm;
    }

    /**
     * Funció que serveix per cridar a la resolució amb l'algoritme per part de l'usuari màquina
     * @param Llista d'integers a buscar la solució
     * @return Llista de llista d'integer amb la resolució de la seqüència
     */
    public List<List<Integer>> get_solve_maquina(List<Integer> solucio) {
        System.out.println("USER SOLVE MAQIUINA");

        if (genetic_algorithm){
            Genetic_algorithm ga = new Genetic_algorithm(dificultat);
            return ga.solve(solucio);
        }else{
            Five_guess_algorithm fga = new Five_guess_algorithm(dificultat);
            return fga.solve(solucio);
        }

    }

    /* --- FUNCIONS DE LA SUPERCLASSE --- */

    public int get_id() {
        return super.get_id();
    }

    public String get_nom() {
        return super.get_nom();
    }

    public Type_user get_tipus_user() {
        return super.get_tipus_user();
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

    public void set_partida_acabada(Partida partida_acabada, boolean guanyat, String dificultat) {
        super.set_partida_acabada(partida_acabada, guanyat, dificultat);
    }

    public void set_puntuacio(Double punts, String dificultat) {
        super.set_puntuacio(punts, dificultat);
    }

    /* --- Excepcions de funcions exclusives d'User_persona ---  */

    public double get_puntuaciopvp() throws Exception {
        throw new Exception("El user maquina no té punts pvp");
    }

    public void set_dificultat_algoritme(dificultats dificultat){
        this.dificultat = dificultat;
    }


}