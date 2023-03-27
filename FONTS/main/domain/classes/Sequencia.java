package main.domain.classes;

import java.util.*;

public class Sequencia {
    private Vector array = new vector<colors>(4);
    private Type_seq tipus = new Typeseq();

   /* public Sequencia(Vector color, Type_seq tipus){
        this.array = color;
        this.tipus = tipus;
    }*/

    public Sequencia() {
        array = null;
        tipus = null;
    }

    public int set_array_intentada(java.util.Vector array, int num_colors) {
        //añadir que no se puede repetir color
        int size = array.size();
        if (size != 4 || array.isEmpty) return -1;
        else {
            for (int i = 0; i < size; ++i) {
                int aux = array.elementAt(i);
                if (aux >= 1 && aux <= num_colors) {
                    this.array.add(i, aux);
                } else return -1;
            }
        }
        return -1;
    }

    public void set_array_verificacio(Vector array) {
        this.array = array;
    }

    public void set_tipus(Type_seq tipus) {
        this.tipus = tipus;
    }

    public Vector get_array() {
        return array;
    }

    public Type_seq getTipus() {
        return tipus;
    }

    public Vector calcula_verificacio(Vector sequencia_verificacio) {
        int res = 0;
        int blanc = 0; //espigues de color encertat
        int negre = 0; //espigues de color i posicio encertades

        for (int i = 0; i < sequencia_verificacio.size(); ++i) {
            if (sequencia_verificacio.elementAt(i) == 1) ++blanc;
            else if (sequencia_verificacio.elementAt(i) == 2) ++negre;
            else ++res;
        }
        Vector aux = new Vector<int>();
        aux.add(0, res);
        aux.add(1, blanc);
        aux.add(2, negre);
        return aux;
    }

    public boolean valida_sequencia(Vector solucio, Vector sequencia_intentada) {
        int res = 0;
        int blanc = 0;
        int negre = 0;

        int i = 0;
        while (i < 4) {
            boolean find = false;
            int j = 0;
            while (j < 4) {
                if (solucio.elementAt(i) == sequencia_intentada.elementAt(j)) {
                    if (i == j) ++negre;
                    else ++blanc;
                    find = true;
                }
                ++j;
            }
            ++i;
            if (!find) ++res;
        }
        Vector aux = new Vector<int>();
        aux.add(0, res);
        aux.add(1, blanc);
        aux.add(2, negre);
        return aux;
    }
}


