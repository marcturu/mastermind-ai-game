package drivers;

import main.domain.classes.algorismes.*;
import main.domain.classes.enumerations.colors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.domain.classes.enumerations.dificultats;


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

    private int pregunta_dificultat() {
        System.out.println("Quina dificultat vols provar?");
        System.out.println("Facil: 1");
        System.out.println("Mitja: 2");
        System.out.println("Dificil: 3");

        int dificultat = in.nextInt();

        while(dificultat != 1 && dificultat != 2 && dificultat != 3){
            System.out.println("Introdueix un numero valid");
            dificultat = in.nextInt();
        }

        return dificultat;
    }

    private int pregunta_algorisme() {
        System.out.println("Quin algorisme vols provar?");
        System.out.println("Five Guess Algorithm: 1");
        System.out.println("Genetic algorithm: 2");

        int input = in.nextInt();

        while(input != 1 && input != 2){
            System.out.println("Introdueix un numero valid");
            input = in.nextInt();
        }

        return input;
    }


    //adaptar a les dificultats quan estiguin implementades
    private void test_five_guess(colors[] solucio, int dificultat) {
        double total_guesses = 0;
        double current_guesses = 0;
        double failed = 0;
        double total_points = 0;

        dificultats dif;
        switch(dificultat) {
            case 1:
                dif = dificultats.FACIL;
                break;

            case 2:
                dif = dificultats.NORMAL;
                break;
            case 3:
                dif = dificultats.DIFICIL;
                break;
        }
        Five_guess_algorithm maq = new Five_guess_algorithm();

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
    

    private void test_genetic(colors[] solucio, int dificultat) {

        dificultats dif;
        switch(dificultat) {
            case 1:
                dif = dificultats.FACIL;
                break;

            case 2:
                dif = dificultats.NORMAL;
                break;
            case 3:
                dif = dificultats.DIFICIL;
                break;
            default:
                dif = dificultats.NORMAL;
                break;
        }
        Genetic_algorithm genetic = new Genetic_algorithm(dif);
        
        System.out.println("Algorisme genetic generat");

        List<Integer> colors = new ArrayList<>();
        for(int i=0; i < (dif.get_num_colors()); ++i) {
            colors.add(i+1);
        }

        List<List<Integer>> allCodes = generateRandomCodes(colors, 4);
        System.out.println("Solucions generades");

        for (List<Integer> new_solution : allCodes) {
            
            System.out.println("Solució: " + new_solution.toString());
            List<List<Integer>> solutions_generated = genetic.solve(new_solution);

            for(int i = 0; i < solutions_generated.size(); ++i) {
                System.out.println("Generació " + (i+1) + ": " + solutions_generated.get(i).toString());
            }
        }
    }
    
    public static void main(String[] args){
        driver_algorisme driver = new driver_algorisme();
        driver.print_colors();
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
        
        int algorisme = driver.pregunta_algorisme();

        int dificultat = driver.pregunta_dificultat();


        switch(algorisme) {
            case 1: {
                driver.test_five_guess(solucio, dificultat);
                break;
            }
            case 2: {
                driver.test_genetic(solucio, dificultat);
                break;
            }
        }
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

    private static List<List<Integer>> generateRandomCodes(List<Integer> colors, int solutionLength) {
        
        List<List<Integer>> allCodes = new ArrayList<>();
        //generem codis aleatoris que seran la solució
        for(int i = 0; i < 1; ++i) {
            List<Integer> code = new ArrayList<>();
            for(int j = 0; j < solutionLength; ++j) {
                code.add((int)(Math.random() * colors.size()));
                if(code.get(j) < 1) code.set(j, 1);
            }
            allCodes.add(code);
        }

        return allCodes;
    }

}