package main.domain.classes;

import java.util.ArrayList;
import java.util.List;

import main.domain.classes.enumerations.type_seq;
import main.domain.classes.enumerations.colors;

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


    /**
     * Funció que serveix per modificar el array de la Sequencia de Verificacio, fa comprobacions de que sigui correcta i en cas contrari salta una Excepcio
     * @param colors[] array Sequencia de verificacio que volem assignar
     * @param colors[] solucio Sequencia de Solucio per a poder fer les comprobacions
     * @param colors[] intentada Sequencia Intendada per poder fer comprobacions
     */
    public void set_array_verificacio(colors[] array, colors[] solucio, colors[] intentada) throws Exception{
        if (array.length != 4) throw new Exception("El tamany de la sequencia no es 4");
        if (!valida_sequencia(array,solucio,intentada)) throw new Exception("Sequencia de Verficacio incorrecte");
        else{
            this.array = array;
        }
    }

    /**
     * Funcio privada que fa les verificacions del array de verifcacion i comprobar que es pot assignar
     * @param colors[] array Sequencia de verificacio que volem assignar
     * @param colors[] solucio Sequencia de Solucio per a poder fer les comprobacions
     * @param colors[] intentada Sequencia Intendada per poder fer comprobacions
     * @return si la sequencia de verificacio és correcte
     */
    private boolean valida_sequencia(colors[] sequencia_verificacio, colors[] solucio, colors[] sequencia_intentada) {
        int res_ver = 0;
        int blanc_ver = 0, blanc_calc = 0; //espigues de color encertat
        int negre_ver = 0, negre_calc = 0; //espigues de color i posicio encertades

        for (int i = 0; i < sequencia_verificacio.length; ++i) {
           if (sequencia_verificacio[i] == colors.BLANC) ++blanc_ver;
           else if (sequencia_verificacio[i] == colors.NEGRE) ++negre_ver;
           else if (sequencia_verificacio[i] == colors.NULL) ++res_ver;
        }

        if ((res_ver + negre_ver + blanc_ver) != 4) return false;

        for (int i = 0; i < 4; ++i){
            if (sequencia_intentada[i] == solucio[i]) {
                ++negre_calc;
            }
            else {
                boolean done = false;
                for (int j = 0; j < 4 && !done; ++j){
                    if (sequencia_intentada[i] == solucio[j]){
                        ++blanc_calc;
                        done = true;
                    }
                }  
            }
        }

        return blanc_ver == blanc_calc && negre_ver == negre_calc;
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


