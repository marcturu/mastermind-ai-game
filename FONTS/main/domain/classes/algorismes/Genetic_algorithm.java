package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.domain.classes.Cromosoma;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.types.Pair;

/**
 * Classe de l'algorisme genetic que implementa l'interficie Maquina
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Genetic_algorithm implements Maquina{

    private static final int POPULATION_SIZE = 150;
    private static final double CROSSOVER_RATE = 0.5;
    private static final double MUTATION_RATE = 0.03;
    private static final double PERMUTATION_RATE = 0.03;
    private static final int MAX_GENERATIONS = 100;
    private static final int MAX_ELEGIBLE_POOL = 60;

    private dificultats dificultat;

    public void set_dificultat(dificultats dificultat) {
        this.dificultat = dificultat;
    }


    /**
     * Funcio per a comprovar si un cromosoma ja existeix a la poblacio
     * @param c cromosoma a comprovar 
     * @param population poblacio de cromosomes
     * @return  true si existeix, false altrament
     */
    private boolean exists(Cromosoma c, List<Cromosoma> population) {
        for(Cromosoma crom: population) {
            boolean equals = (c.get_codi().get(0) == crom.get_codi().get(0)) && (c.get_codi().get(1) == crom.get_codi().get(1)) && (c.get_codi().get(2) == crom.get_codi().get(2)) && (c.get_codi().get(3) == crom.get_codi().get(3));
            if(equals) return true;
        }
        return false;
    }

    /**
     * Funcio per inicialitzar la poblacio de la generacio
     * No hi hauran cromosomes repetits
     */
    private List<Cromosoma> init_poblacio() {
        List<Cromosoma> population = new ArrayList<Cromosoma>();
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            Cromosoma c = new Cromosoma(dificultat);
            while(exists(c, population)) {
                c = new Cromosoma(dificultat);
            }
            population.add(c);
        }
        return population;
    }

    /**
     * Calcul del fitness d'un cromosoma segons l'article:
     * https://lirias.kuleuven.be/bitstream/123456789/184247/2/Mastermind
     * Com més alt sigui el fitness més diferent serà el cromosoma de la resta
     * @param c cromosoma a calcular el fitness
     * @param cromosomes_previs llista de cromosomes seleccionats previament
     * @param codi_solucio codi solucio de la partida
     * @return l'invers del fitness del cromosoma
     */
    private double fitness(Cromosoma c, List<Cromosoma> cromosomes_previs, List<Integer> codi_solucio) {

        int constantA = 1;
        int constantB = 2;
        double fitness = 0.0;

        for(Cromosoma prev: cromosomes_previs) {
            Pair<Integer, Integer> p = c.get_result(prev.get_codi());
            Pair<Integer, Integer> p2 = prev.get_result(codi_solucio);
            fitness += (double)(constantA * (Math.abs(p.second() - p2.second()) + Math.abs(p.first() - p2.first())));
        }
        fitness += (double)(constantB * 4 * (cromosomes_previs.size() - 1));

        if(fitness == 0) return 1.0;

        return 1.0/fitness;
    }

    /**
     * Funcio per volcar les solucions dels cromosomes a la llista de solucions
     * @param solucions llista de cromosomes
     * @param sol llista de solucions
    */
    private void passa_de_cromosoma_a_solucio(List<Cromosoma> solucions, List<List<Integer>> sol) {
        for(Cromosoma c: solucions) {
            sol.add(c.get_codi());
        }
    }
    
    /**
     * Funcio per a fer la mutacio d'una poblacio
     * @param poblacio poblacio a mutar
     */
    private void muta_poblacio(List<Cromosoma> poblacio) {
        for(Cromosoma c: poblacio) {
            c.muta(MUTATION_RATE, dificultat);
        }
    }

    /**
     * Funcio per a fer la permutacio d'una poblacio
     * @param poblacio poblacio a permutar
     */
    private void permuta_poblacio(List<Cromosoma> poblacio) {
        for(Cromosoma c: poblacio) {
            c.permuta(PERMUTATION_RATE);
        }
    }

    /**
     * Funcio per a generar la nova poblacio, fent servir el metode de la ruleta, segons descrit a:
     * https://lirias.kuleuven.be/bitstream/123456789/184247/2/Mastermind
     * @param codi_solucio codi solucio de la partida
     * @param codis_previs llista de cromosomes seleccionats previament
     * @param possibles_pares pares per generar la nova poblacio
     * @return la nova poblacio
     */
    private List<Cromosoma> genera_nova_poblacio(List<Integer> codi_solucio, List<Cromosoma> codis_previs, List<Cromosoma> possibles_pares) {
        //creem nova poblacio random
        List<Cromosoma> new_poblacio = new ArrayList<>();
        Cromosoma pare = null;
        Cromosoma mare = null;
        Cromosoma fill1 = null;
        Cromosoma fill2 = null;

        //////////////////////////////////////////////
        //generem cromosomes de la seguent generacio//
        //////////////////////////////////////////////
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            mare = possibles_pares.get((2*i)%POPULATION_SIZE);
            pare = possibles_pares.get((2*i+1)%POPULATION_SIZE);

            if(true) {
                Pair<Cromosoma, Cromosoma> fills = mare.one_point_crossover(pare, dificultat);
                fill1 = fills.first();
                fill2 = fills.second();
            }else {
                fill1 = mare;
                fill2 = pare;
            }
            new_poblacio.add(fill1);
            new_poblacio.add(fill2);

        }

        /////////////////////////////////////////////////////////
        //Fem barreja, mutacio i permutacio de la nova poblacio//
        /////////////////////////////////////////////////////////
        muta_poblacio(new_poblacio);
        permuta_poblacio(new_poblacio);

        for(int i =0; i < POPULATION_SIZE; ++i) {
            if(exists(new_poblacio.get(i), new_poblacio)) {
                new_poblacio.set(i, new Cromosoma(dificultat));
            }
        }
        return new_poblacio;
    }

    /**
     * Funcio per a generar el pool de pares a partir dels elegibles de la generacio anterior i els millors pares de la generacio actual segons fitness
     * @param elegibles_ant elegibles de la generacio anterior
     * @param possibles_pares millors pares de la generacio actual
     * @param solucions llista de cromosomes provats
     * @param codi_solucio codi solucio de la partida
     * @return pool de pares
     */
    private List<Cromosoma> genera_pares(List<Cromosoma> elegibles_ant, List<Cromosoma> possibles_pares, List<Cromosoma> solucions, List<Integer> codi_solucio) {
        List<Cromosoma> pares = new ArrayList<>();
        //afegim tots els elegibles de la generacio anterior
        for(Cromosoma c:elegibles_ant) pares.add(c);

        double[] fitness_array = new double[POPULATION_SIZE];

        double total_fitness = 0;

        //////////////////////////////////////////////////////////////////
        //Ordenem els pares per que els millors tinguin mes probabilitat//
        //////////////////////////////////////////////////////////////////
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            fitness_array[i] = fitness(possibles_pares.get(i),solucions, codi_solucio);
            total_fitness += fitness_array[i];
        }
        for(int i = 0; i < POPULATION_SIZE; ++i) fitness_array[i] /= total_fitness;
        boolean swap;
		Cromosoma tempInd;
		double tempFit;
		do{		
			swap = false;
			for(int i = 1; i < POPULATION_SIZE; i++){
				if(fitness_array[i] > fitness_array[i-1]){
					tempInd = possibles_pares.get(i);
					tempFit = fitness_array[i];
                    possibles_pares.set(i, possibles_pares.get(i-1));
					fitness_array[i] = fitness_array[i-1];

                    possibles_pares.set(i-1, tempInd);
                    fitness_array[i-1] = tempFit;
					swap = true;
				}
			}
		}while(swap);
        while(pares.size() < POPULATION_SIZE) {
            double numRandom = Math.random(); // percentatge de la poblacio que pot ser elegida
            int indexRandom = (int)(Math.random()*(possibles_pares.size()*numRandom));//elegim un random dins del percentatge elegit
            if(!exists(possibles_pares.get(indexRandom), pares))
                pares.add(possibles_pares.get(indexRandom));
            possibles_pares.remove(indexRandom);
        }
        return pares;
    }

    /**
     * Funcio per a resoldre el problema
     * @param l solucio de la partida
     * @return llista d'intents fins arribar a la solucio
     */
    public List<List<Integer>> solve(List<Integer> solution) {   
        List<List<Integer>> sol = new ArrayList<>();
        List<Cromosoma> solucions = new ArrayList<>();
        
        List<Integer> guess = Arrays.asList(1, 1, 2, 3);        
        Cromosoma last_guess = new Cromosoma(dificultat);
        last_guess.set_codi(guess);
        solucions.add(last_guess);
        
        int iter = 1;
        while (last_guess.get_result(solution).second() != 4) {  
            List<Cromosoma> elegibles = new ArrayList<>();
            int h = 1;
            List<Cromosoma> poblacio = init_poblacio();//codis aleatòris
            List<Cromosoma> possibles_pares = new ArrayList<>();
            while(h < MAX_GENERATIONS && elegibles.size() < MAX_ELEGIBLE_POOL) {
                //generem nova poblacio
                
                if(h == 1) possibles_pares = poblacio;

                List<Cromosoma> new_poblacio = genera_nova_poblacio(solution, solucions, possibles_pares);
                
                for(Cromosoma individu : new_poblacio) {
                    boolean valid = true;
                    ////////////////////////////////////////////////////
                    //triem quins cromosomes son elegibles com a guess//
                    ////////////////////////////////////////////////////
                    for(Cromosoma c: solucions){
                        //Mirem els resultats si l'anterior cromosoma es la solucio i també el resutat de l'anterior cromosoma  
                        Pair<Integer, Integer> result_if_c_is_solution = individu.get_result(c.get_codi());
                        Pair<Integer, Integer> result_of_c = c.get_result(solution);
                        if(result_if_c_is_solution.first() != result_of_c.first() || result_if_c_is_solution.second() != result_of_c.second()) valid = false;
                    } 
                    if(valid && !exists(individu, elegibles)) elegibles.add(individu); 
                }
                h++;
                poblacio = new_poblacio;
                possibles_pares = genera_pares(elegibles, new_poblacio, solucions, solution);
            }
            
            //////////////////////////////////////////////////////
            //triem el guess més semblant a la resta d'elegibles//
            //////////////////////////////////////////////////////
            Pair<Integer, Integer> pins;
            last_guess = elegibles.get(0);
            int min_similaritat = Integer.MAX_VALUE, similaritat = 0;
            
            for(Cromosoma c: elegibles) {
                similaritat = 0;
                for(Cromosoma c2: elegibles) {
                    if(c != c2) {
                        pins = c.get_result(c2.get_codi());
                        similaritat +=  pins.first() + pins.second();
                    }
                }
                if(similaritat < min_similaritat) {
                    min_similaritat = similaritat;
                    last_guess = c;
                }
            }
            solucions.add(last_guess);
            
            ++iter;
        }
        passa_de_cromosoma_a_solucio(solucions, sol);
        return sol;
    }

    /**
     * Creadora de l'algorisme genètic
     * @param dif dificultat de la partida
     */
    public Genetic_algorithm(dificultats dif) {
        dificultat = dif;
    }
}