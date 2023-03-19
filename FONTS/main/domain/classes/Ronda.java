package main.domain.classes;

import java.util.*;


public static class Ronda  {

    private int id_ronda;
    private int id_partida;
    private int num_ronda;
    private Sequencia sequencia_intentada = new Sequencia();
    private Sequencia sequencia_verificacio = new Sequencia();

    public Ronda(int ronda_num, int partida_id){

    }

    public Sequencia get_seq_intentada() {
        return sequencia_intentada;
    }

    public Sequencia get_seq_verificacio() {
        return sequencia_verificacio;
    }

    public int get_id_ronda() {
        return id_ronda;
    }

    public int get_id_partida() {
        return id_partida;
    }

    public int get_num_ronda() {
        return num_ronda;
    }

    public void set_intentada(Sequencia sequencia_intentada) {
        this.sequencia_intentada = sequencia_intentada;
    }

    public void set_verificacio(Sequencia sequencia_verificacio) {
        this.sequencia_verificacio = sequencia_verificacio;
    }
}