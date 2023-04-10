package main.domain.classes;

import main.domain.classes.enumerations.type_seq;
import main.domain.classes.enumerations.colors;


public class Sequencia {
    private colors[] array;
    private type_seq tipus;

    public Sequencia(type_seq tipus) {
        array = new colors[4];
        this.tipus = tipus;
    }

    //getters
    public colors[] get_array() {
        return array;
    }

    public type_seq get_tipus() {
        return tipus;
    }

    //setters
    public void set_array(colors[] array, int num_colors) throws Exception{
        int size = array.length;
        if (size != 4) throw new Exception("El tamany de la sequencia no es 4");
        else {
            for (int i = 0; i < size; ++i) {
                int aux = array[i].get_id_color();
                if (aux < 1 && aux > num_colors && array[i] == null) throw new Exception("Sequencia incorrecta");
                else {
                    this.array[i] = array[i];
                }
            }
        }
    }

    public void set_array_verificacio(colors[] array, colors[] solucio, colors[] intentada) throws Exception{
        if (array.length != 4) throw new Exception("El tamany de la sequencia no es 4");
        if (!valida_sequencia(array,solucio,intentada)) throw new Exception("Sequencia de Verficacio incorrecte");
        else{
            this.array = array;
        }
    }


    private boolean valida_sequencia(colors[] sequencia_verificacio, colors[] solucio, colors[] sequencia_intentada) {
        int res = 0;
        int blanc_ver = 0, blanc_calc = 0; //espigues de color encertat
        int negre_ver = 0, negre_calc = 0; //espigues de color i posicio encertades

        for (int i = 0; i < sequencia_verificacio.length; ++i) {
           if (sequencia_verificacio[i].get_id_color() == 9) ++blanc_ver;
           else if (sequencia_verificacio[i].get_id_color() == 10) ++negre_ver;
           else if (sequencia_verificacio[i].get_id_color() == 0) ++res;
        }
        if ((res + negre_ver + blanc_ver) != 4) return false;

        colors[] aux = sequencia_intentada;
        for (int i = 0; i < 4; ++i){
            if (solucio[i].get_id_color() == aux[i].get_id_color()) {
                ++negre_calc;
                aux[i] = colors.NULL;
            }
        }

        for (int i = 0; i < 4; ++i){
            for (int j = 0; j < 4; ++j){
                if (solucio[i].get_id_color() == aux[j].get_id_color()){
                    ++blanc_calc;
                    aux[j] = colors.NULL;
                }
            }
        }

        return blanc_ver == blanc_calc && negre_ver == negre_calc;
        }
    }


