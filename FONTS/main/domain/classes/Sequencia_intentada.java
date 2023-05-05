package main.domain.classes;

import java.util.ArrayList;
import java.util.List;

import main.domain.classes.enumerations.colors;
import main.domain.classes.types.Pair;

public class Sequencia_intentada extends Sequencia {

    public Sequencia_intentada(){
        super();
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
}