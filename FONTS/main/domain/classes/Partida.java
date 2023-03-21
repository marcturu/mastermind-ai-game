package main.domain.classes;

import java.util.*;

public class Partida {
    private int indentificador;
    private boolean ajuda;
    private int num_colors;
    private int temps_max;
    private boolean partida_acabada;
    private int num_rondes_max;
    private dificultat dificultat;
    private int temps_usat;
    private List llista_rondes;
    private boolean jugador1_es_codemaker;

    public Partida(int id, User cm, User cb, dificultat dif){

    }
    public int get_id() {
        return indentificador;
    }
    public User get_codemaker(){

    }

    public User get_codebreaer(){

    }

    public boolean get_ajuda() {
        return this.ajuda;
    }

    public void set_ajuda() {
        this.ajuda = !this.ajudaajuda;
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

    public void set_new_ronda(Ronda nova_ronda){

    }
}