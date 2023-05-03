package main.domain.classes;

import java.util.ArrayList;
import java.util.List;

import main.domain.classes.enumerations.type_seq;
import main.domain.classes.enumerations.colors;
import main.domain.classes.types.Pair;

/**
 * Classe Sequencia
 * @author Jordi Baranda (jordi.baranda@estudiantat.upc.edu)
 */


public class Sequencia {
    protected colors[] array;

    /**
     * Constructor de la classe Sequencia
     */
    public Sequencia() {
        array = new colors[4];
        for(int i = 0; i < 4; ++i) {
            array[i] = colors.NULL;
        }
    }

    /**
     * Funció que retorna el array de colors
     * @return Array de colors
     */
    public colors[] get_array() {
        return array;
    }


    /**
     * Funció que serveix per modificar una posicio exacte del array de la Sequencia
     * @param int i Posicio que volem modificar
     * @param colors col Color que asignarem a aquesta posició
     */
    public void set_position(int i, colors col) {
        array[i] = col;
    }

    /**
     * Funcio per convertir el array en una llista de integers
     * @return el array en forma de llisat de integer
     */
    public List<Integer> toListInteger() {
        List<Integer> ret = new ArrayList<Integer>(4);
        for(int i = 0; i < array.length; ++i) {
            ret.add(i, array[i].get_id_color());
        }
        return ret;
    }
}


