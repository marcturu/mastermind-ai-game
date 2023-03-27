package main.domain.classes;

import java.util.*;


public class Ronda  {

    private int id_partida;
    private int num_ronda;
    private Sequencia sequencia_intentada;
    private Sequencia sequencia_verificacio;

    public Ronda(int id_partida, int num_ronda){
        this.id_partida = id_partida;
        this.num_ronda = num_ronda;
        this.sequencia_verificacio = new Sequencia();
        th
    }

    public Sequencia get_seq_intentada() {
        return sequencia_intentada;
    }

    public Sequencia get_seq_verificacio() {
        return sequencia_verificacio;
    }

    public int get_id_partida() {
        return id_partida;
    }

    public int get_num_ronda() {
        return num_ronda;
    }

    public int set_intentada(colors[] sequencia_intentada,int num_colors) {
        return this.sequencia_intentada.set_array_intentada(sequencia_intentada,num_colors);
    }

    public int set_verificacio(colors[] sequencia_verificacio, colors[] solucio)
    {
        return this.sequencia_verificacio.set_array_verificacio(sequencia_verificacio, solucio, sequencia_intentada.get_array());
    }
}