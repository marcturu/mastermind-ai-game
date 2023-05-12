package main.domain.classes;

import java.util.ArrayList;
import java.util.List;

import main.domain.classes.enumerations.colors;
import main.domain.classes.enumerations.dificultats;

import main.domain.classes.types.Pair;

/**
 * Classe per a tindre les funcions de un cromosoma, classe necessaria per a poder fer el algorisme genètic
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Cromosoma implements Comparable<Cromosoma>{
    private int[] codi;
    private int[] solucio;
    private int fitness; //"punts" del codi

    /**
     * Constructora de la classe Cromosoma
     */
    public Cromosoma(int[] codi_sol) {
        codi = new int[4];
        for(int i = 0; i < 4; ++i) {
            codi[i] = (int)(Math.random() * dificultats.NORMAL.get_num_colors() + 1);
            if(codi[i] < 1) codi[i] = 1; //el 0 es null, no es pot posar
        }
        fitness = evaluateFitness();
        for(int i = 0; i < 4; ++i) {
            solucio[i] = (int)codi_sol[i];
        }
    }
    
    /**
     * Consultora del fitness d'un cromosoma
     * @return int que representa el fitness del cromosoma
     */
    public int get_fitness() {
        return fitness;
    }

    /**
     * Consultora del codi d'un cromosoma
     * @return el codi del cromosoma
     */
    public int[] get_codi() {
        return codi;
    }

    public List<Integer> get_codi_list() {
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < 4; ++i) {
            l.add(codi[i]);
        }
        return l;
    }

    /**
     * Modificadora del codi d'un cromosoma
     * @param new_codi nou codi que volem assignar al cromosoma
     */
    public void set_codi(int[] new_codi) {
        codi = new_codi;
    }

    /**
     * Funcio per a fer el crossover entre dos cromosomes
     * @param cromosoma2 cromosoma amb el que farem l'encreuament
     * @return retorna un cromosoma fill dels dos cromosomes
     */
    public Cromosoma crossover(Cromosoma cromosoma2) {
        Cromosoma fill = new Cromosoma(solucio);
        for(int i = 0; i < 4; ++i) {
            if(Math.random() > 0.5) fill.codi[i] = codi[i];
            else fill.codi[i] = cromosoma2.codi[i];
        }
        fill.fitness = fill.evaluateFitness();
        return fill;
    }

    /**
     * Funcio per a fer la mutacio d'un cromosoma
     * @param mutation_rate double que representa la probabilitat de mutacio
     */
    public void muta(double mutation_rate) {
        for(int i = 0; i < 4; ++i) {
            if(Math.random() < mutation_rate) {
                codi[i] = (int)(Math.random() * dificultats.NORMAL.get_num_colors() + 1);
                if(codi[i] < 1) codi[i] = 1; //el 0 es null, no es pot posar
            }
        }
    }

    public void permuta(double permutation_rate) {
        if(Math.random() < permutation_rate) {
            int index1 = (int)(Math.random() * 3); //valor entre 0 i 3 (size = 4)
            int index2 = (int)(Math.random() * 3);
            int aux = codi[index1];
            codi[index1] = codi[index2];
            codi[index2] = aux;
        }
    }

    /**
     * Funcio per a avaluar el fitness d'un cromosoma
     * @return int que representa el fitness del cromosoma
     */
    private int evaluateFitness() {

        colors[] arr_sol = {colors.get_color_by_id(solucio[0]), colors.get_color_by_id(solucio[1]), colors.get_color_by_id(solucio[2]), colors.get_color_by_id(solucio[3])};
        colors[] arr_guess = {colors.get_color_by_id(codi[0]), colors.get_color_by_id(codi[1]), colors.get_color_by_id(codi[2]), colors.get_color_by_id(codi[3])};

        Pair<Integer, Integer> result = Sequencia_verificacio.get_verificacio(arr_sol, arr_guess);

        return result.first() * 10 + result.second();
    }

    /**
     * Funcio per a comparar dos cromosomes
     * @param c1 cromosoma 1
     * @param c2 cromosoma 2
     * @return si c1 te un millor fitness que c2 retorna true, altrament false
     */
    public int compareTo(Cromosoma c2) {
        if(get_fitness() > c2.get_fitness()) return 1;
        else if (get_fitness() == c2.get_fitness()) return 0;
        else return -1;
    }
}
