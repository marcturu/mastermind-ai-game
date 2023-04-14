package main.domain.classes.algorismes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Five_guess_algorithm implements Maquina{

//    public static void main(String[] args) {
//        List<Integer> solution = Arrays.asList(6, 3, 1, 9); // Aquí se define la solución
//        printList(solution);
//        System.out.println("this is the solution, lets see how we got there:");
//
//        List<List<Integer>> guesses = solve(solution);
//        for (List<Integer> guess : guesses) {
//            System.out.println(guess.toString());
//        }
//    }

    public List<List<Integer>> solve(List<Integer> solution) {
        List<List<Integer>> guesses = new ArrayList<>();
        Integer[] colors = {1, 2, 3, 4, 5, 6, 8}; // Aquí se define el número de colores

        List<List<Integer>> possibleCodes = generateCodes(Arrays.asList(colors), solution.size());

        List<Integer> guess = Arrays.asList(1, 1, 2, 2); // Primera jugada recomendada por Five Guess

        List<Integer> result = getResult(guess, solution);

        guesses.add(guess);

        while (!guess.equals(solution)) {
            possibleCodes = filterCodes(possibleCodes, guess, result);

            guess = minimax(possibleCodes);

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
    public static List<List<Integer>> generateCodes(List<Integer> colors, int solutionLength) {
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
    private static List<Integer> getResult(List<Integer> guess, List<Integer> solution) {
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

    /**
     * Función que filtra de todos los codigos posibles los que tienen el mismo resultado que la intentada
     *
     * @param colors Lista de posibles codigos, sequencia intentada y resultado de la intentada
     * @return Lista de codigos posibles y filtrados
     */
    private static List<List<Integer>> filterCodes(List<List<Integer>> possibleCodes, List<Integer> guess, List<Integer> result) {
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
    private static List<Integer> minimax(List<List<Integer>> possibleCodes) {
        List<Integer> bestGuess = null;
        int minmax = Integer.MAX_VALUE;

        for (List<Integer> guess : possibleCodes) {
            int[] score = new int[5];

            for (List<Integer> code : possibleCodes) {
                score[getResult(guess, code).get(0)]++;
            }

            int maxScore = Arrays.stream(score).max().getAsInt();

            if (maxScore < minmax) {
                minmax = maxScore;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    public static void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}