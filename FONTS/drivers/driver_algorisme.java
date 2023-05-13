package drivers;

import main.domain.classes.Sequencia;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.algorismes.*;
import main.domain.classes.enumerations.colors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class driver_algorisme {

    private Scanner in = new Scanner(System.in);

    private void print_colors(){
        System.out.println("colors: vermell, verd, blau, groc, magenta, cian, marro, gris\n");
    }

    private colors crea_array_color(String input) throws Exception {
        switch (input){
            case "vermell":{
                return colors.VERMELL;
            }
            case "verd":{
                return colors.VERD;
            }
            case "blau":{
                return colors.BLAU;
            }
            case "groc":{
                return colors.GROC;
            }
            case "magenta":{
                return colors.MAGENTA;
            }
            case "cian":{
                return colors.CIAN;
            }
            case "marro":{
                return colors.MARRO;
            }
            case "gris":{
                return colors.GRIS;
            }
            case "blanc":{
                return colors.BLANC;
            }
            case "negre":{
                return colors.NEGRE;
            }
            default:{
                throw new Exception("Color Incorrecte");
            }
        }
    }

    public static void main(String[] args){
        driver_algorisme driver = new driver_algorisme();
        driver.print_colors();
        System.out.println("Introdueix Solucio:");
        colors[] solucio = new colors[4];
//        for (int i = 0; i < 4; ++i) {
//            try {
//                String input = in.nextLine();
//                while (input.length() == 0) input = in.nextLine();
//                arr_int[i] = crea_array_color(input);
//            } catch (Exception ex) {
//                --i;
//                System.out.println(ex.getMessage());
//            }
//        }

        Five_guess_algorithm maq = new Five_guess_algorithm();

        double total_guesses = 0;
        double current_guesses = 0;
        double failed = 0;
        double total_points = 0;


        Integer[] colores = {1, 2, 3, 4, 5, 6};

        List<List<Integer>> allCodes = generateCodes(Arrays.asList(colores), 4);
        total_guesses = allCodes.size();

        for (List<Integer> new_solution : allCodes) {

            List<List<Integer>> solutions_generated = maq.solve(new_solution);


            System.out.println("LAST SOLUTION: " + solutions_generated.get(solutions_generated.size()-1).toString() +" / REAL "+new_solution.toString());

            if(solutions_generated.get(solutions_generated.size()-1).toString() == new_solution.toString()) {
                failed++;
            }

            total_points += solutions_generated.size();
            current_guesses++;


            System.out.println("--------------------");
            System.out.println("New solution: " + new_solution.toString());
            System.out.println("CURRENT guesses: " + current_guesses +"/" + total_guesses);
            System.out.println("CURRENT medium of points: " + total_points/current_guesses);
            System.out.println("Percent of failed: " + failed/current_guesses*100);

        }
        System.out.println("Total points: " + total_points/total_guesses);


    }

    private static List<List<Integer>> generateCodes(List<Integer> colors, int solutionLength) {
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
}