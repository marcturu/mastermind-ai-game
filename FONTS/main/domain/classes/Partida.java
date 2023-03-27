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
    private Duration temps_usat;
    private List llista_rondes;
    private Sequencia sequancia_solucio;

    public Partida(int id, User cm, User cb, dificultats dif){

    }
    public int get_id() {
        return indentificador;
    }
    public User get_codemaker(){
        if(jugador1_es_codemaker) return jugador1;
        else return jugador2;
    }

    public User get_codebreaer(){
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

    public void set_jugador1_es_codemaker(bool es_codemaker) {
        this.jugador1_es_codemaker = es_codemaker;
    }

    public boolean temps_excedit(){

    }

    public int get_num_colors() {
        return num_colors;
    }

    public int get_num_rondes_max() {
        return num_rondes_max;
    }

    public List get_llista_rondes(){

    }

    public void crea_nova_ronda(){

    }
}