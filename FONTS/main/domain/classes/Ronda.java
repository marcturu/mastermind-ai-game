package main.domain.classes;

import java.util.*;


public static class Ronda  {

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

    public int set_intentada(Vector sequencia_intentada,int num_colors) {
        return sequencia_intentada.set_array_intentada(sequencia_intentada,num_colors);
    }

    public int set_verificacio(Vector sequencia_verificacio, Vector solucio)
    {
        Vector seq_ver = calcula_verificacio(sequencia_verificacio);
        Vector seq_comp = valida_sequencia(solucio,sequencia_intentada);
        if (seq_ver == seq_comp) {
            this.sequencia_verificacio = sequencia_verificacio;
            return 1;
        }
        return -1;
    }
}