package main.domain.classes;

import java.util.*;

public class Partida {
    public int indentificador;
    public boolean ajuda;
    public int num_colors;
    public int temps_max;
    public boolean partida_acabada;
    public int num_rondes_max;
    public dificultat dificultat;
    public int temps_usat;
    public List llista_rondes;
    public boolean jugador1_es_codemaker;

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
        return ajuda;
    }

    public void set_ajuda() {
        ajuda = !ajuda;
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