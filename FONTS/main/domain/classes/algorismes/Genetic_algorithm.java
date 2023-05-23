package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private static final int MAX_GENERATIONS = 1000;
    private static final int MAX_ELEGIBLE_POOL = 500;

    private dificultats dificultat;


    /**
     * Funcio per a comprovar si un cromosoma ja existeix a la poblacio
     * @param c cromosoma a comprovar 
     * @param population poblacio de cromosomes
     * @return  true si existeix, false altrament
     */
    private boolean exists(Cromosoma c, List<Cromosoma> population) {
        for(Cromosoma crom: population) {
            if(c != crom && crom.get_codi().equals(c.get_codi())) return true;
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
     * @param c cromosoma a calcular el fitness
     * @param cromosomes_previs llista de cromosomes seleccionats previament
     * @param codi_solucio codi solucio de la partida
     * @return el fitness del cromosoma
     */
    private int fitness(Cromosoma c, List<Cromosoma> cromosomes_previs, List<Integer> codi_solucio) {

        int constantA = 1;
        int constantB = 2;
        int fitness = 0;

        for(Cromosoma prev: cromosomes_previs) {
            Pair<Integer, Integer> p = c.get_result(prev.get_codi());
            Pair<Integer, Integer> p2 = prev.get_result(codi_solucio);
            fitness += constantA * (Math.abs(p.first() - p2.first()) + Math.abs(p.second() - p2.second()));
        }
        fitness += constantB * 4 * (cromosomes_previs.size() - 1);

        return fitness;
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
     * Funcio per a barrejar els cromosomes dins d'una poblacio
     * @param poblacio poblacio a barrejar
     */
    private void barreja_poblacio(List<Cromosoma> poblacio) {
        Collections.shuffle(poblacio);
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
     */
    private List<Cromosoma> genera_nova_poblacio(List<Integer> codi_solucio, List<Cromosoma> codis_previs) {
        //creem nova poblacio random
        List<Cromosoma> new_poblacio = init_poblacio();
        Cromosoma pare = null;
        Cromosoma mare = null;
        Cromosoma fill = null;
        double[] fitness_array = new double[POPULATION_SIZE];

        double total_fitness = 0;

        ////////////////////////////////////////////////////////
        //Preparem els parametres per a la seleccio per ruleta//
        ////////////////////////////////////////////////////////
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            fitness_array[i] = fitness(new_poblacio.get(i),codis_previs, codi_solucio);
            total_fitness += fitness_array[i];
        }
        for(int i = 0; i < POPULATION_SIZE; ++i) fitness_array[i] /= total_fitness;

        //ordenem els cromosomes segons el seu fitness
        boolean swap = true;
		Cromosoma tempInd = new Cromosoma(dificultat);
		double tempFit;
		while(swap){		
			swap = false;
			for(int i = 1; i < POPULATION_SIZE; i++){
				if(fitness_array[i] > fitness_array[i-1]){	
					tempInd = new_poblacio.get(i);
					tempFit = fitness_array[i];
                    new_poblacio.set(i, new_poblacio.get(i-1));
					fitness_array[i] = fitness_array[i-1];
                    new_poblacio.set(i-1, tempInd);
				    fitness_array[i-1] = tempFit;
					swap = true;
				}
			}
		}

        //Normalitzem els valors de fitness
        total_fitness = 0;
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            total_fitness += fitness_array[i];
            fitness_array[i] = total_fitness;
        }
        fitness_array[POPULATION_SIZE - 1] = 1;

        //////////////////////////////////////////////////////////////////
        //seleccionem els cromosomes que passaran a la seguent generacio//
        //////////////////////////////////////////////////////////////////
        double randomNum;
        for(int i = 0; i < POPULATION_SIZE; ++i) {
            //triem mare amb la ruleta
            randomNum = Math.random();
            for(int j = 0; j < POPULATION_SIZE; ++j) {
                if(randomNum < fitness_array[j]) {
                    mare = new_poblacio.get(j);
                }
            }
        
            //triem pare amb la ruleta
            randomNum = Math.random();
            for(int j = 0; j < POPULATION_SIZE; ++j) {
                if(randomNum < fitness_array[j]) {
                    pare = new_poblacio.get(j);
                }
            }
            //fem one point crossover o two point crossover(50/50)
            if(Math.random() > CROSSOVER_RATE) {
                fill = mare.one_point_crossover(pare, dificultat);
                new_poblacio.set(i, fill);
            }else {
                fill = mare.two_point_crossover(pare, dificultat);
                new_poblacio.set(i, fill);
            }
        }

        for(int i =0; i < POPULATION_SIZE; ++i) {
            if(exists(new_poblacio.get(i), new_poblacio)) {
                new_poblacio.set(i, new Cromosoma(dificultat));
            }
        }
        /////////////////////////////////////////////////////////
        //Fem barreja, mutacio i permutacio de la nova poblacio//
        /////////////////////////////////////////////////////////
        barreja_poblacio(new_poblacio);
        muta_poblacio(new_poblacio);
        permuta_poblacio(new_poblacio);
        return new_poblacio;
    }

    /**
     * Funcio per a resoldre el problema
     * @param l solucio de la partida
     * @return llista d'intents fins arribar a la solucio
     */
    public List<List<Integer>> solve(List<Integer> solution) {
        int A = 1;
        
        List<List<Integer>> sol = new ArrayList<>();
        List<Cromosoma> solucions = new ArrayList<>();
        
        List<Integer> guess = Arrays.asList(1, 1, 2, 3);        
        Cromosoma last_guess = new Cromosoma(dificultat);
        last_guess.set_codi(guess);
        solucions.add(last_guess);
        List<Cromosoma> poblacio = init_poblacio();
        int iter = 1;
        while (last_guess.get_result(solution).second() != 4) {  
            List<Cromosoma> elegibles = new ArrayList<>();
            int h = 1;
            while(h <= MAX_GENERATIONS && elegibles.size() <= MAX_ELEGIBLE_POOL) {
                poblacio = genera_nova_poblacio(solution, solucions);
                for(int i = 0; i < POPULATION_SIZE; ++i){
                    int diferencia_negres = 0;
                    int diferencia_blanques = 0;

                    ////////////////////////////////////////////////////
                    //triem quins cromosomes son elegibles com a guess//
                    ////////////////////////////////////////////////////
                    for(int j = 1; j < solucions.size(); ++j){
                        
                        diferencia_negres += A * Math.abs(poblacio.get(i).get_result(solucions.get(j).get_codi()).second() - solucions.get(j).get_result(solution).second());
                    }			

                    for(int j = 1; j < solucions.size(); ++j){
                        diferencia_blanques += Math.abs(poblacio.get(i).get_result(solucions.get(j).get_codi()).first() - solucions.get(j).get_result(solution).first());
                    }      

                    if(diferencia_negres == 0 && diferencia_blanques == 0){

                        boolean exists = false;

                        for(Cromosoma c : elegibles){
                            if(c.get_codi().equals(poblacio.get(i).get_codi())) exists = true;
                        }

                        if(!exists) elegibles.add(poblacio.get(i));
                    }
                }
                h++;
            }
            if(elegibles.isEmpty()) {
                System.out.println("LIADA!!!");
                return null;
            }

            ////////////////////////////////////////////
            //triem el guess més semblant a la solucio//
            ////////////////////////////////////////////
            last_guess = elegibles.get(0);
            int max_similaritat = 0, similaritat = 0;

            for(Cromosoma c: elegibles) {
                for(Cromosoma c2: elegibles) {
                    if(c == c2) continue;
                    Pair<Integer, Integer> pins = c.get_result(c2.get_codi());
                    similaritat +=  pins.first() + pins.second();
                }
                if(similaritat > max_similaritat) {
                    max_similaritat = similaritat;
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