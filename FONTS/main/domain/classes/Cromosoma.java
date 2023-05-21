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

public class Cromosoma {
    private List<Integer> codi = new ArrayList<>(4);

    /**
     * Constructora de la classe Cromosoma
     */
    public Cromosoma(dificultats dificultat) {  
        for(int i = 0; i < 4; ++i) {
            int num_random = (int)(Math.random() * (dificultat.get_num_colors() + 1));//el +1 es perque Math.random() pertany a [0.0,1.0)
            if(num_random < 1) num_random = 1;

            if(codi.size() < 4) codi.add(num_random);
            else codi.set(i, num_random);
        }
    }

    /**
     * Consultora del codi d'un cromosoma
     * @return el codi del cromosoma
     */
    public List<Integer> get_codi() {
        return codi;
    }

    /**
     * Modificadora del codi d'un cromosoma
     * @param new_codi nou codi que volem assignar al cromosoma
     */
    public void set_codi(List<Integer> new_codi) {
        for(int i = 0; i < 4; ++i) {
            codi.set(i, new_codi.get(i));
        }
    }

    /**
     * Funcio per a fer el crossover de un sol punt entre dos cromosomes
     * @param cromosoma2 cromosoma amb el que farem l'encreuament
     * @return retorna un cromosoma fill dels dos cromosomes
     */
    public Cromosoma one_point_crossover(Cromosoma cromosoma2, dificultats dificultat) {
        Cromosoma fill = new Cromosoma(dificultat);
        for(int i = 0; i < 4; ++i) {
            if(Math.random() > 0.5) fill.codi.set(i, codi.get(i));
            else fill.codi.set(i, cromosoma2.codi.get(i));
        }
        return fill;
    }

    /**
     * Funcio per a fer el crossover de un sol punt entre dos cromosomes
     * @param cromosoma2 cromosoma amb el que farem l'encreuament
     * @return retorna un cromosoma fill dels dos cromosomes
     */
    public Cromosoma two_point_crossover(Cromosoma cromosoma2, dificultats dificultat) {
        Cromosoma fill = new Cromosoma(dificultat);
        int random1 = (int)(Math.random() * 3);
        int random2 = (int)(Math.random() * 3);
        while(random1 == random2) random2 = (int)(Math.random() * 3);

        if(random1 > random2) {
            int aux = random1;
            random1 = random2;
            random2 = aux;
        }

        for(int i = 0; i<4; ++i) {
            if(i <= random1 && i < random2) fill.codi.set(i, this.codi.get(i));
			if(i > random1 && i <= random2) fill.codi.set(i, cromosoma2.codi.get(i));
			if(i > random1 && i > random2) fill.codi.set(i, this.codi.get(i));
        }
        return fill;
    }


    /**
     * Funcio per a fer la mutacio d'un cromosoma
     * @param mutation_rate double que representa la probabilitat de mutacio
     */
    public void muta(double mutation_rate, dificultats dificultat) {
        int randomIndex = (int)(Math.random() * 3);
        int randomColor = (int)(Math.random() * dificultat.get_num_colors());
        if(randomColor < 1) randomColor = 1; //el 0 es null, no es pot posar
        if(Math.random() < mutation_rate) {
            Cromosoma test = new Cromosoma(dificultat);
            test.codi.set(randomIndex, randomColor);
        }
        
    }

    /**
     * Funcio per a fer la permutacio d'un cromosoma
     * @param permutation_rate double que representa la probabilitat de permuta
     */
    public void permuta(double permutation_rate) {
        if(Math.random() < permutation_rate) {
            int index1 = (int)(Math.random() * 3); //valor entre 0 i 3 (size = 4)
            int index2 = (int)(Math.random() * 3);
            while(index1 == index2) index2 = (int)(Math.random() * 3); //valor entre 0 i 3 (size = 4)

            int aux = codi.get(index1);
            codi.set(index1, codi.get(index2));
            codi.set(index2, aux);
        }
    }

    /**
     * Funcio per a avaluar el fitness d'un cromosoma, compara el cromosoma amb un altre com si aquell fos la solucio
     * @return int que representa el fitness del cromosoma
     */
    public Pair<Integer, Integer> get_result(List<Integer> sol) {
        colors[] arr_sol = {colors.get_color_by_id(sol.get(0)), colors.get_color_by_id(sol.get(1)), colors.get_color_by_id(sol.get(2)), colors.get_color_by_id(sol.get(3))};
        colors[] arr_guess = {colors.get_color_by_id(codi.get(0)), colors.get_color_by_id(codi.get(1)), colors.get_color_by_id(codi.get(2)), colors.get_color_by_id(codi.get(3))};

        return Sequencia_verificacio.get_verificacio(arr_sol, arr_guess);
    }
}
