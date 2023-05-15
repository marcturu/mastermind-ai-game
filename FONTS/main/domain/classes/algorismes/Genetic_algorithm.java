package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.domain.classes.Cromosoma;
import main.domain.classes.enumerations.dificultats;

public class Genetic_algorithm implements Maquina{

    private static final int POPULATION_SIZE = 50;
    private static final double MUTATION_RATE = 0.03;
    private static final double PERMUTATION_RATE = 0.03;
    private static final int MAX_GENERATIONS = 100; //maxim de generacions que es faran, ns si hauria de ser el num_rondes de la partida
    private static final int TARGET_FITNESS = 40; //cas de tindre 4 negres
    private static final double ELITISM_RATE = 0.4; //percentatge de la poblacio que es mantindra

    private dificultats dificultat;

    /**
     * Funcio per inicialitzar la poblacio de la generacio
     */
    private List<Cromosoma> initPoblacio(List<Integer> sol) {
        List<Cromosoma> population = new ArrayList<Cromosoma>();
        int[] aux_sol = new int[4];
        for(int i = 0; i < 4; ++i) {
            aux_sol[i] = sol.get(i);
        }

        for(int i = 0; i < POPULATION_SIZE; ++i) {
            population.add(new Cromosoma(aux_sol, dificultat));
        }
        return population;
    }

    /**
     * Funcio per a triar el pare. Fem servir un procés elitista, on es trien dels millors cromosomes
     * @param poblacio
     * @return
     */
    private Cromosoma tria_pare(List<Cromosoma> poblacio) {
        int index = (int)(Math.random() * (poblacio.size() * ELITISM_RATE)); // agafem cromosoma random en un percentatge de la poblacio
        Collections.sort(poblacio, Collections.reverseOrder());
        return poblacio.get(index);
    }
    
    public List<List<Integer>> solve(List<Integer> l) {
        int generacio = 1;
        List<List<Integer>> sol = new ArrayList<>();
        int[] guess = {1, 1, 2, 3}; //primer guess, normalment aquest es el que dona millor resultat
        Cromosoma last_guess = new Cromosoma(guess, dificultat);
        sol.add(Arrays.asList(1, 1, 2, 3));
        if(last_guess.get_fitness() == TARGET_FITNESS) { //en cas d'encertar a la primera
            sol.add(last_guess.get_codi_list());
            return sol;
        }
        List<Cromosoma> poblacio = initPoblacio(l);
        while (generacio < MAX_GENERATIONS) {
            ++generacio;
            List<Cromosoma> new_poblacio = new ArrayList<Cromosoma>();
            for(int i = 0; i < POPULATION_SIZE; ++i) {
                Cromosoma pare1 = tria_pare(poblacio);
                Cromosoma pare2 = tria_pare(poblacio);
                Cromosoma fill = pare1.crossover(pare2, dificultat);
                fill.muta(MUTATION_RATE, dificultat);
                fill.permuta(PERMUTATION_RATE);
                if(fill.get_fitness() >= last_guess.get_fitness()) {
                    new_poblacio.add(fill);
                }
            }

            poblacio = new_poblacio;
            Collections.sort(poblacio, Collections.reverseOrder()); //el que tingui major fitness anira a la posicio 0
            last_guess = poblacio.get(0);
            sol.add(last_guess.get_codi_list());

            //comprovem si hem encertat
            if(last_guess.get_fitness() == TARGET_FITNESS) {
                return sol;
            }
        }

        return sol;
    }

    public Genetic_algorithm(dificultats dif) {
        dificultat = dif;
    }
}