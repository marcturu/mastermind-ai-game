package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.enumerations.colors;

import main.domain.classes.types.Pair;

import main.domain.classes.Sequencia_verificacio;
//import main.domain.classes.Sequencia_verificacio;
//import main.domain.classes.enumerations.colors;

public class Five_guess_algorithm implements Maquina{

    private int codeLength = 4;
    private dificultats dificultat;

    public void set_dificultat(dificultats dificultat) {
        this.dificultat = dificultat;
    }


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


        Integer[] colors = getColors();

        List<List<Integer>> possibleCodes = generateCodes(Arrays.asList(colors), solution.size());

        List<Integer> guess = Arrays.asList(1, 1, 2, 2); // Primera jugada recomendada por Five Guess

        Integer[] score = getScore(guess, solution);
        List<Integer> result = Arrays.asList(score);

        guesses.add(guess);

        while (!guess.equals(solution)) {
            possibleCodes = filterCodes(possibleCodes, guess, result);

            guess = getBestGuess(possibleCodes);

            Integer[] resultat = getScore(guess, solution);
            result = Arrays.asList(resultat);
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
    private List<List<Integer>> generateCodes(List<Integer> colors, int solutionLength) {
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
     * Función que filtra de todos los codigos posibles los que tienen el mismo resultado que la intentada
     *
     * @param colors Lista de posibles codigos, sequencia intentada y resultado de la intentada
     * @return Lista de codigos posibles y filtrados
     */
    private List<List<Integer>> filterCodes(List<List<Integer>> possibleCodes, List<Integer> guess, List<Integer> result) {
        List<List<Integer>> filteredCodes = new ArrayList<>();

        for (List<Integer> code : possibleCodes) {

            List<Integer>codeResult = Arrays.asList(getScore(guess, code));

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
    private List<Integer> getBestGuess(List<List<Integer>> possibleCodes) {
        Integer minMax = Integer.MAX_VALUE;
        List<Integer> bestGuess = null;

        for (List<Integer> code : possibleCodes) {
            // Calcula el nombre maxim de posiblitats que queden despres d'aquesta intentada
            Integer maxRemaining = Integer.MIN_VALUE;
            for (Integer i = 0; i < possibleCodes.size(); i++) {
                List<Integer> possibleCode = possibleCodes.get(i);
                Integer[] score = getScore(code, possibleCode);
                if (!Arrays.equals(score, new Integer[] { codeLength, 0 })) {
                    Integer remaining = 0;
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
    private Integer[] getScore(List<Integer> guess, List<Integer> secretCode) {
        Integer[] score = new Integer[2];


        colors[] arr_sol = {colors.get_color_by_id(secretCode.get(0)), colors.get_color_by_id(secretCode.get(1)), colors.get_color_by_id(secretCode.get(2)), colors.get_color_by_id(secretCode.get(3))};
        colors[] arr_guess = {colors.get_color_by_id(guess.get(0)), colors.get_color_by_id(guess.get(1)), colors.get_color_by_id(guess.get(2)), colors.get_color_by_id(guess.get(3))};

        Pair<Integer, Integer> sol = Sequencia_verificacio.get_verificacio(arr_sol, arr_guess);

        score[0] = sol.first();
        score[1] = sol.second();
        return score;
    }

    /**
     * Devuelve una lista de colores dependen de la dificultad
     *
     * @param numero colores que se quieren
     * @return array de integers que representan los colores
     */
    private Integer[] getColors(){
        int num_colors = this.dificultat.get_num_colors();

        Integer[] colors = new Integer[num_colors];
        for(int i = 0; i < num_colors; ++i){
            colors[i] = i+1;
        }
        return colors;
    }


}