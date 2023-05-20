package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.domain.classes.enumerations.dificultats;

//import main.domain.classes.Sequencia;
//import main.domain.classes.Sequencia_verificacio;
//import main.domain.classes.enumerations.colors;

public class Five_guess_algorithm implements Maquina{

    private int codeLength = 4;
    private dificultats dificultat;


//    public static void main(String[] args) {
//        List<Integer> solution = Arrays.asList(1, 3, 5, 1); // Aquí se define la solución
//        printList(solution);
//        System.out.println("this is the solution, lets see how we got there:");
//
//        List<List<Integer>> guesses = solve_t(solution);
//        for (List<Integer> guess : guesses) {
//            System.out.println(guess.toString());
//        }
//    }

    /**
     * Creadora de la clase Five_guess_algorithm
     *
     * @param dificultat Dificultad de la partida
     */
    public Five_guess_algorithm(dificultats dif) {
        dificultat = dif;
    }

    public List<List<Integer>> solve(List<Integer> solution) {
        List<List<Integer>> guesses = new ArrayList<>();


        Integer[] colors = get_colors();

        List<List<Integer>> possibleCodes = generateCodes(Arrays.asList(colors), solution.size());

        List<Integer> guess = Arrays.asList(1, 1, 2, 2); // Primera jugada recomendada por Five Guess

        List<Integer> result = getResult(guess, solution);

        guesses.add(guess);

        while (!guess.equals(solution)) {
            possibleCodes = filterCodes(possibleCodes, guess, result);

            guess = getBestGuess(possibleCodes);

            result = getResult(guess, solution);
            guesses.add(guess);
        }

        return guesses;
    }

    /**
     * Función que genera todos los códigos posibles
     *
     * @param colors Lista de colores, largada de la solución
     * @return Lista de códigos posibles
     */
    public List<List<Integer>> generateCodes(List<Integer> colors, int solutionLength) {
        int numColors = colors.size();
        int numCodes = (int) Math.pow(numColors, solutionLength);
        List<List<Integer>> allCodes = new ArrayList<>();

        // generar todos los códigos posibles usando nested loops
        for (int i = 0; i < numCodes; i++) {
            int quotient = i;
            List<Integer> code = new ArrayList<>();
            for (int j = solutionLength - 1; j >= 0; j--) {
                int remainder = quotient % numColors;
                code.add(colors.get(remainder));
                quotient /= numColors;
            }
            allCodes.add(code);
        }

        return allCodes;
    }

    /**
     * Función que devuelve el resultado (blancas y negras) de una posible solucion
     *
     * @param colors intento y solucion
     * @return lista el resultado (blancas y negras) de una posible solucion
     */
    private List<Integer> getResult(List<Integer> guess, List<Integer> solution) {
        List<Integer> result = new ArrayList<>();
        int correct = 0;
        int misplaced = 0;

        for (int i = 0; i < guess.size(); i++) {
            if (guess.get(i).equals(solution.get(i))) {
                correct++;
            } else if (solution.contains(guess.get(i))) {
                misplaced++;
            }
        }

        result.add(correct);
        result.add(misplaced);

        return result;
    }

//    private static List<Integer> getResultActualitzat(List<Integer> guess, List<Integer> solution) {
//
//        Sequencia_verificacio sequencia = new Sequencia_verificacio();
//
//        colors[] guess_transformed = new colors[guess.size()];
//
//        for(int i = 0; i < guess.size(); ++i) {
//            guess_transformed[i] = colors.get_color_by_id(guess.get(i));
//        }
//
//        colors[] solution_transformed = new colors[guess.size()];
//
//        for(int i = 0; i < guess.size(); ++i) {
//            solution_transformed[i] = colors.get_color_by_id(guess.get(i));
//        }
//
//        Pair<Integer,Integer> result_pair = sequencia.get_verificacio(guess_transformed, solution_transformed);
//
//        List<Integer> result = new ArrayList<>();
//
//        result.add(result_pair[0]);
//        result.add(result_pair[1]);
//
//        return result;
//    }

    /**
     * Función que filtra de todos los codigos posibles los que tienen el mismo resultado que la intentada
     *
     * @param colors Lista de posibles codigos, sequencia intentada y resultado de la intentada
     * @return Lista de codigos posibles y filtrados
     */
    private List<List<Integer>> filterCodes(List<List<Integer>> possibleCodes, List<Integer> guess, List<Integer> result) {
        List<List<Integer>> filteredCodes = new ArrayList<>();

        for (List<Integer> code : possibleCodes) {
            List<Integer> codeResult = getResult(guess, code);
            if (codeResult.equals(result)) {
                filteredCodes.add(code);
            }
        }

        return filteredCodes;
    }

    /**
     * Función que devuelve la mejor solucion posible dada una lista de posibles soluciones
     *
     * @param una lista de posibles soluciones
     * @return la mejor solucion posible dada una lista de posibles soluciones
     */
    public List<Integer> getBestGuess(List<List<Integer>> possibleCodes) {
        int minMax = Integer.MAX_VALUE;
        List<Integer> bestGuess = null;

        for (List<Integer> code : possibleCodes) {
            // Calcula el nombre maxim de posiblitats que queden despres d'aquesta intentada
            int maxRemaining = Integer.MIN_VALUE;
            for (int i = 0; i < possibleCodes.size(); i++) {
                List<Integer> possibleCode = possibleCodes.get(i);
                int[] score = getScore(code, possibleCode);
                if (!Arrays.equals(score, new int[] { codeLength, 0 })) {
                    int remaining = 0;
                    for (List<Integer> nextPossibleCode : possibleCodes) {
                        if (!Arrays.equals(score, getScore(code, nextPossibleCode))) {
                            remaining++;
                        }
                    }
                    maxRemaining = Math.max(maxRemaining, remaining);
                }
            }
            // Esculleix la quess que te menys posibilitats que queden maximes
            if (maxRemaining < minMax) {
                minMax = maxRemaining;
                bestGuess = code;
            }
        }

        return bestGuess;
    }

    /**
     * Función que devuelve el resultado (blancas y negras) de una posible solucion
     *
     * @param colors intento y solucion
     * @return lista el resultado (blancas y negras) de una posible solucion
     */
    private int[] getScore(List<Integer> guess, List<Integer> secretCode) {
        int[] score = new int[2];
        boolean[] usedSecret = new boolean[codeLength];
        boolean[] usedGuess = new boolean[codeLength];
        // Matches exactes
        for (int i = 0; i < codeLength; i++) {
            if (guess.get(i).equals(secretCode.get(i))) {
                score[0]++;
                usedSecret[i] = true;
                usedGuess[i] = true;
            }
        }
        // Matches parcials
        for (int i = 0; i < codeLength; i++) {
            if (!usedGuess[i]) {
                for (int j = 0; j < codeLength; j++) {
                    if (!usedSecret[j] && guess.get(i).equals(secretCode.get(j))) {
                        score[1]++;
                        usedSecret[j] = true;
                        break;
                    }
                }
            }
        }
        return score;
    }

    /**
     * Devuelve una lista de colores
     *
     * @param numero colores que se quieren
     * @return lista el resultado (blancas y negras) de una posible solucion
     */
    private Integer[] get_colors(){
        int num_colors = this.dificultat.get_num_colors();

        Integer[] colors = new Integer[num_colors];
        for(int i = 0; i < num_colors; ++i){
            colors[i] = i+1;
        }
        return colors;
    }


}