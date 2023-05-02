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
    private colors[] array;
    private type_seq tipus;

    /**
     * Constructor de la classe Sequencia
     * @param type_seq tipus de Sequencia
     */
    public Sequencia(type_seq tipus) {
        array = new colors[4];
        for(int i = 0; i < 4; ++i) {
            array[i] = colors.NULL;
        }
        this.tipus = tipus;
    }

    /**
     * Funció que retorna el array de colors
     * @return Array de colors
     */
    public colors[] get_array() {
        return array;
    }

    /**
     * Funció que retorna el tipus de Sequencia
     * @return type_seq de la Sequencia
     */
    public type_seq get_tipus() {
        return tipus;
    }

    /**
     * Funció que serveix per modificar el array de la sequencia intentada i solucio, fent comprobacions de que sigui correcte aquest, en cas contrari retorna una Excepcio
     * @param color array Array de colors que volem asignar al array de la Sequencia
     * @param int num_colors Nombre maxim de colors que es pot fer servir
     */
    public void set_array(colors[] array, int num_colors) throws Exception{
        int size = array.length;
        if (size != 4) throw new Exception("El tamany de la sequencia no es 4");
        else {
            for (int i = 0; i < size; ++i) {
                int aux = array[i].get_id_color();
                if (aux < 1 || aux > num_colors || array[i] == null) throw new Exception("Sequencia incorrecta");
                else {
                    this.array[i] = array[i];
                }
            }
        }
    }

    /**
     * Funció que serveix per modificar una posicio exacte del array de la Sequencia
     * @param int i Posicio que volem modificar
     * @param colors col Color que asignarem a aquesta posició
     */
    public void set_position(int i, colors col) {
        array[i] = col;
    }

    private Pair<Integer, Integer> get_negres_blanques(colors[] verificacio_entrada) {
        int blanques = 0, negres = 0;
        for(int i = 0; i < 4; ++i) {
            if(verificacio_entrada[i] == colors.NEGRE) ++negres;
            else if(verificacio_entrada[i] == colors.BLANC) ++blanques;
        }

        return new Pair<>(blanques, negres);
    }


    /**
     * Funció que serveix per modificar el array de la Sequencia de Verificacio, fa comprobacions de que sigui correcta i en cas contrari salta una Excepcio
     * @param colors[] array Sequencia de verificacio que volem assignar
     * @param colors[] solucio Sequencia de Solucio per a poder fer les comprobacions
     * @param colors[] intentada Sequencia Intendada per poder fer comprobacions
     */
    public void set_array_verificacio(colors[] verificacio, Sequencia solucio, Sequencia intentada) throws Exception{
        if (array.length != 4) throw new Exception("El tamany de la sequencia no es 4");
        Pair<Integer, Integer> pair_correcte = intentada.get_verificacio(solucio.get_array());
        Pair<Integer, Integer> pair_entrat = get_negres_blanques(verificacio);
        if ((pair_correcte.first() != pair_entrat.first()) || (pair_correcte.second() != pair_entrat.second())) throw new Exception("Sequencia de Verficacio incorrecte");
        else{
            this.array = verificacio;
        }
    }

    /**
     * Funcio que retorna la verificacio d'una sequencia
     * @param solucio
     * @return espigues blanques i negres que ha fet la sequencia
     */
    public Pair<Integer,Integer> get_verificacio(colors[] arr_solucio) {
        Integer blanques = 0, negres = 0;
        Pair<Integer,Integer> result = new Pair<>(blanques, negres);
        
        for(int i = 0; i < 4; ++i) {
            if(arr_solucio[i] == array[i]) ++negres;
            else {
                boolean done = false;
                for(int j = 0; j < 4 && !done; ++j) {
                    if(array[i] == arr_solucio[j]) {
                        ++blanques;
                        done = true;
                    }
                }
            }
        }
        result.set_first(blanques);
        result.set_second(negres);
        return result;
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


