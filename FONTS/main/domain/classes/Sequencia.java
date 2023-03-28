package main.domain.classes;

import java.util.*;
import java.io.*;

public class Sequencia {
    private colors[] array;
    private type_seq tipus;

    public Sequencia(type_seq tipus) {
        array = new colors[4];
        tipus = null;
    }

    //getters
    public colors[] get_array() {
        return array;
    }

    public type_seq getTipus() {
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
        int res_ver = 0, res_calc = 0;
        int blanc_ver = 0, blanc_calc = 0; //espigues de color encertat
        int negre_ver = 0, negre_calc = 0; //espigues de color i posicio encertades

        for (int i = 0; i < sequencia_verificacio.length; ++i) {
           if (sequencia_verificacio[i].get_id_color() == 9) ++blanc_ver;
           else if (sequencia_verificacio[i].get_id_color() == 10) ++negre_ver;
           else ++res_ver;
        }

        int i = 0;
        while (i < 4) {
            boolean find = false;
            int j = 0;
            while (j < 4 && !find) {
                if (solucio[i].get_id_color() == sequencia_intentada[i].get_id_color()) {
                if (i == j) ++negre_calc;
                else ++blanc_calc;
                find = true;
                }
                ++j;
            }
            ++i;
            if (!find) ++res_calc;
        }
        return res_ver == res_calc && blanc_ver == blanc_calc && negre_ver == negre_calc;
        }
    }


