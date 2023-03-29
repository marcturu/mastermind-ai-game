package main.domain.classes.algorismes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Five_guess_algorithm {

//public List<List<Integer>> solve(List<Integer> solution) {}


    public static void main(String[] args) {
        int[] solution = {4, 5, 1, 4}; // Aquí se define la solución
        printArray(solution);
        System.out.println("this is the solution, lets see how we got there:");


        List<int[]> guesses = fiveGuess(solution);
        for (int[] guess : guesses) {
            System.out.println(Arrays.toString(guess));
        }
    }

    public static List<int[]> fiveGuess(int[] solution) {
        List<int[]> guesses = new ArrayList<>();
        int[] colors = {1, 2, 3, 4, 5 ,6}; // Aquí se define el número de colores

        //printArray(colors);
        int[][] possibleCodes = generateCodes(colors, solution.length);

        //printArray(possibleCodes);

        int[] guess = {1, 1, 2, 2}; // Primera jugada recomendada por Five Guess

        //printArray(guess);

        int[] result = getResult(guess, solution);


        guesses.add(guess);

        while (!Arrays.equals(guess, solution)) {
            possibleCodes = filterCodes(possibleCodes, guess, result);

            //printArray(possibleCodes);

            guess = minimax(possibleCodes);

            result = getResult(guess, solution);
            guesses.add(guess);
        }

        return guesses;
    }

    public static int[][] generateCodes(int[] colors, int solutionLength) {
        int numColors = colors.length;
        int numCodes = (int) Math.pow(numColors, solutionLength);
        int[][] allCodes = new int[numCodes][solutionLength];

        // Generate all possible codes using nested loops
        for (int i = 0; i < numCodes; i++) {
            int quotient = i;
            for (int j = solutionLength - 1; j >= 0; j--) {
                int remainder = quotient % numColors;
                allCodes[i][j] = colors[remainder];
                quotient /= numColors;
            }
        }

        return allCodes;
    }

    private static int getArrayIndex(int[][] array, int[] value) {
        for (int i = 0; i < array.length; i++) {
            if (Arrays.equals(array[i], value)) {
                return i;
            }
        }

        return -1;
    }

    private static int[] getResult(int[] guess, int[] solution) {
        int[] result = new int[2];
        int correct = 0;
        int misplaced = 0;

        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == solution[i]) {
                correct++;
            } else if (contains(solution, guess[i])) {
                misplaced++;
            }
        }

        result[0] = correct;
        result[1] = misplaced;

        return result;
    }

    private static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }

        return false;
    }

    private static int[][] filterCodes(int[][] possibleCodes, int[] guess, int[] result) {
        List<int[]> filteredCodes = new ArrayList<>();

        for (int[] code : possibleCodes) {
            int[] codeResult = getResult(guess, code);
            if (Arrays.equals(codeResult, result)) {
                filteredCodes.add(code);
            }
        }

        return filteredCodes.toArray(new int[filteredCodes.size()][guess.length]);
    }


    private static int[] minimax(int[][] possibleCodes) {
        int[] bestGuess = null;
        int minmax = Integer.MAX_VALUE;

        for (int i = 0; i < possibleCodes.length; i++) {
            int[] guess = possibleCodes[i];
            int[] score = new int[5];

            for (int j = 0; j < possibleCodes.length; j++) {
                int[] code = possibleCodes[j];
                score[getResult(guess, code)[0]]++;
            }

            int maxScore = Arrays.stream(score).max().getAsInt();

            if (maxScore < minmax) {
                minmax = maxScore;
                bestGuess = guess;
            }
        }

        return bestGuess;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }





}