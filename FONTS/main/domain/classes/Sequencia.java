package main.domain.classes;

import java.util.*;

public class Sequencia {
    private colors[4] array;p```''
    private type_seq tipus;

    public Sequencia(type_seq) {
        array = null;
        tipus = null;
    }

    //getters
    public Vector get_array() {
        return array;
    }

    public Type_seq getTipus() {
        return tipus;
    }
    //setters
    public int set_array_intentada(color[] array, int num_colors) {
        int size = array.length;
        if (size != 4) return -1;
        else {
            for (int i = 0; i < size; ++i) {
                int aux = array[i];
                if (aux >= 1 && aux <= num_colors) {
                    this.array[i] = array[i];
                } else return -1;
            }
        }
        return -1;
    }

    public int set_array_verificacio(colors[] array) {
        if (valida_sequencia(verificacio,solucio,intentada)) {
            this.array = array;
            return 1;
        }
        return -1;
    }

    public Vector calcula_verificacio(Vector sequencia_verificacio) {


        public boolean valida_sequencia(colors[] sequencia_verificacio, colors[] solucio, colors[] sequencia_intentada) {
            int res_ver = 0, res_calc = 0;
            int blanc_ver = 0, blanc_calc = 0; //espigues de color encertat
            int negre_ver = 0, negre_calc = 0; //espigues de color i posicio encertades

            for (int i = 0; i < sequencia_verificacio.length; ++i) {
                if (sequencia_verificacio[i].get_id_color() == 7) ++blanc_ver;
                else if (sequencia_verificacio[i].get_id_color() == 8) ++negre_ver;
                else ++res_ver;
            }

            int i = 0;
            while (i < 4) {
                boolean find = false;
                int j = 0;
                while (j < 4) {
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


