package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.domain.classes.Cromosoma;

public class Genetic_algorithm implements Maquina{

    private static final int POPULATION_SIZE = 150;
    private static final double MUTATION_RATE = 0.05;
    private static final double PERMUTATION_RATE = 0.08;
    private static final int MAX_GENERATIONS = 400; //maxim de generacions que es faran, ns si hauria de ser el num_rondes de la partida
    private static final int TARGET_FITNESS = 40; //cas de tindre 4 negres
    private static final double ELITISM_RATE = 0.2; //percentatge de la poblacio que es mantindra

    /**
     * Funcio per inicialitzar la poblacio de la generacio
     */
    private List<Cromosoma> initPoblacio(List<Integer> sol) {
        List<Cromosoma> population = new ArrayList<Cromosoma>();
        int[] aux = new int[4];
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            for(int j = 0; j < 4; ++j) {
                aux[j] = sol.get(j);
            }
            population.add(new Cromosoma(aux));
        }
        return population;
    }

    /**
     * Funcio per a triar el pare. Fem servir un procés elitista, on es trien dels millors cromosomes
     * @param poblacio
     * @return
     */
    private Cromosoma tria_pare(List<Cromosoma> poblacio) {
        int index = (int)(Math.random() * (POPULATION_SIZE * ELITISM_RATE)); // agafem cromosoma random en el top 20%
        Collections.sort(poblacio, Collections.reverseOrder());
        return poblacio.get(index);
    }
    
    public List<List<Integer>> solve(List<Integer> l) {
        int generacio = 1;
        List<List<Integer>> sol = new ArrayList<>();
        int[] guess = {1, 1, 2, 3}; //primer guess, normalment aquest es el que dona millor resultat
        Cromosoma best_guess = new Cromosoma(guess);
        if(best_guess.get_fitness() == 40) { //en cas d'encertar a la primera
            sol.add(Arrays.asList(1, 1, 2, 3));
            return sol;
        }
        List<Cromosoma> poblacio = initPoblacio(l);
        while (generacio < MAX_GENERATIONS) {
            ++generacio;
            List<Cromosoma> new_poblacio = new ArrayList<Cromosoma>();
            for(int i = 0; i < POPULATION_SIZE; ++i) {
                Cromosoma pare1 = tria_pare(poblacio);
                Cromosoma pare2 = tria_pare(poblacio);
                Cromosoma fill = pare1.crossover(pare2);
                fill.muta(MUTATION_RATE);
                fill.permuta(PERMUTATION_RATE);
                new_poblacio.add(fill);
            }

            poblacio = new_poblacio;
            Collections.sort(poblacio, Collections.reverseOrder()); //el que tingui major fitness anira a la posicio 0
            sol.add(poblacio.get(0).get_codi_list());

            if(poblacio.get(0).get_fitness() == TARGET_FITNESS) return sol;
        }

        return sol;
    }

}