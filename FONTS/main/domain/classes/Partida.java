package main.domain.classes;

import main.domain.classes.enumerations.dificultats;
import java.util.*;
import java.time.*;

public class Partida {
    private int indentificador;
    private User jugador1;
    private User jugador2;
    private int ultima_ronda_jugada;
    private boolean jugador1_es_codemaker;
    private boolean ajuda;
    private dificultats dificultat;
    private int num_colors;
    private int temps_max;
    private int num_rondes_max;
    private boolean partida_acabada;
<<<<<<< HEAD
    private Instant temps_inici;
=======
    private Duration temps_usat;
>>>>>>> 7d19e67fcc42d87e1fd5029b52ceb829c339c582
    private List llista_rondes;
    private Sequencia sequancia_solucio;

<<<<<<< HEAD
    public Partida(int id, User cm, User cb, dificultat dif, boolean jugador1_es_codemaker) {
        this.indentificador = id;

        this.ultima_ronda_jugada = 0;
        this.jugador1_es_codemaker = true; // TODO: juntar amb el controlador
        this.ajuda = false;
        this.dificultat = dif;
        this.num_colors = dif.get_num_colors();
        this.temps_max = dif.get_temps_max();
        this.num_rondes_max = dif.get_num_rondes_max();
        this.partida_acabada = false;
        this.temps_inici = Instant.now();
        this.llista_rondes = new ArrayList<Ronda>();

        if (jugador1_es_codemaker) {
            this.jugador1 = cm;
            this.jugador2 = cb;
        } else {
            this.jugador1 = cb;
            this.jugador2 = cm;
        }

    }
    public int get_id() {
        return indentificador;
    }
    public User get_codemaker(){
        if(jugador1_es_codemaker) return jugador1;
        else return jugador2;
    }

    public User get_codebreaker(){
        if(jugador1_es_codemaker) return jugador2;
        else return jugador1;
    }

    public boolean get_ajuda() {
        return this.ajuda;
    }

    public void set_ajuda() throws exception{
        if(this.ajuda == true) this.ajuda = !this.ajudaajuda;
        else {
            throw new Exception("Ja has demanat ajuda un cop");
        }
    }

    //ESTO ES INCORRECTO, DEBERIA ESTAR EN LA CREADORA
    public void set_jugador1_es_codemaker(bool es_codemaker) {
        this.jugador1_es_codemaker = es_codemaker;
    }

    public dificultat dificultat(){
        return dificultat;
    }

    public boolean temps_excedit(){
        temps_usat = (int) this.get_temps_usat().toSeconds();
        if(temps_usat > temps_max) return true;
        else return false;
    }

    public Instant get_temps_usat(){
        ara = Instant.now();
        temps_usat = Duration.between(temps_inici, ara);
        return temps_usat;
    }

    public int get_num_colors() {
        return num_colors;
    }

    public int get_num_rondes_max() {
        return num_rondes_max;
    }

    public List get_llista_rondes(){
        return llista_rondes;
    }

    public void crea_nova_ronda(){
        ronda = new Ronda(ultima_ronda_jugada+1, identificador);
        this.llista_rondes.add(ronda);
    }
}