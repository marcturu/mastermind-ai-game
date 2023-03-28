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
    private Instant temps_inici;
    private List<Ronda> llista_rondes;
    private Sequencia sequencia_solucio;

    public Partida(int id, User cm, User cb, dificultat dif, boolean jugador1_es_codemaker) {
        this.indentificador = id;

        this.ultima_ronda_jugada = 0;
        this.jugador1_es_codemaker = jugador1_es_codemaker;
        this.ajuda = false;
        this.dificultat = dif;
        this.num_colors = dif.get_num_colors(); // INCORRECTO DEBERIA VENIR DE CONSTANTE
        this.temps_max = dif.get_temps_max();
        this.num_rondes_max = dif.get_num_max_rondes();
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

    public Duration get_temps_usat(){
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

    public List<Ronda> get_llista_rondes(){
        return llista_rondes;
    }

    public void acabar_partida(){
        this.partida_acabada = true;
        this.jugador1.set_partida_acabada(this);
        this.jugador2.set_partida_acabada(this);

    }
    
    public void set_sequencia_solucio(solucio){
        this.sequencia_solucio = solucio;
    }

    public void crea_nova_ronda(){
        Ronda ronda = new Ronda(ultima_ronda_jugada+1, identificador);
        ++ultima_ronda_jugada;
        this.llista_rondes.add(ronda);
    }
}