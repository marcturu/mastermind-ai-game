package main.domain.classes;

import main.domain.classes.enumerations.colors;

/**
 * Classe Sequencia
 * @author Jordi Baranda (jordi.baranda@estudiantat.upc.edu)
 */
public class Ronda  {

    private int id_partida;
    private int num_ronda;
    private Sequencia_intentada sequencia_intentada;
    private Sequencia_verificacio sequencia_verificacio;

    /**
     * Constructor de la classe Ronda
     * @param int id_partida Identificador de la partida que s'esta jugant
     * @param int num_ronda Identificador de la ronda que s'esta jugant
     */
    public Ronda(int id_partida, int num_ronda){
        this.id_partida = id_partida;
        this.num_ronda = num_ronda;
        this.sequencia_verificacio = new Sequencia_verificacio();
        this.sequencia_intentada = new Sequencia_intentada();
    }
    /**
     * Funció que retorna la Sequencia Intentada
     * @return Classe Sequencia
     */
    public Sequencia_intentada get_seq_intentada() {
        return sequencia_intentada;
    }

    /**
     * Funció que retorna la Sequencia de Verificacio
     * @return Classe Sequencia
     */
    public Sequencia_verificacio get_seq_verificacio() {
        return sequencia_verificacio;
    }

    /**
     * Funció que retorna el Identificador de la Partida
     * @return Int id_partida
     */
    public int get_id_partida() {
        return id_partida;
    }

    /**
     * Funció que retorna el Identificador de la Ronda
     * @return Int num_ronda
     */
    public int get_num_ronda() {
        return num_ronda;
    }

    /**
     * Funció que modifica la Sequencia Intentada de la classe
     * @param Sequencia sequencia_intentada
     */
    public void set_intentada(Sequencia_intentada sequencia_intentada) {
        this.sequencia_intentada = sequencia_intentada;
    }

    /**
     * Funció que modifica la Sequencia de Verificacio de la classe
     * @param Sequencia sequencia_verificacio
     */
    public void set_verificacio(Sequencia_verificacio sequencia_verificacio){
        this.sequencia_verificacio = sequencia_verificacio; 
    }

    /**
     * Funció que retorna un boolea que ens informa si la sequencia de verificacio es tot negre, i per tan s'ha encertat la solucio
     * @return boolean True si s'ha la Sequencia de Verificacio es tot Negre
     */
    public boolean check_sequencia_encertada(){
        colors[] array = sequencia_verificacio.get_array();
        int n = 0;
        for (int i = 0; i < array.length; ++i ){
            if (array[i].get_id_color() == 10) ++n;
        }
        return n == 4;
    }

}