package drivers;

import main.domain.classes.algorismes.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.domain.classes.enumerations.dificultats;


public class driver_algorisme {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        driver_algorisme driver = new driver_algorisme();
        driver.print_colors();
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

        int num_proves = driver.pregunta_num_proves();

        switch(algorisme) {
            case 1: {
                driver.test_five_guess(dificultat, num_proves);
                break;
            }
            case 2: {
                driver.test_genetic(dificultat, num_proves);
                break;
            }
            case 3: {
                driver.test_both(dificultat, num_proves);
                break;
            }
        }
    }


    //adaptar a les dificultats quan estiguin implementades
    private void test_five_guess(int dificultat, int num_proves) {
        double total_guesses = (double)num_proves;
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
            default:
                dif = dificultats.NORMAL;
                break;
        }
        Five_guess_algorithm maq = new Five_guess_algorithm(dif);

        System.out.println("Algorisme five guess generat");

        int num_colors = dif.get_num_colors();

        Integer[] colores = get_colors(num_colors);

        List<List<Integer>> allCodes = generateRandomCodes(Arrays.asList(colores), 4, num_proves, dif);

        System.out.println("Codis random generats");

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


    private void test_genetic(int dificultat, int num_proves) {

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
        System.out.println("Generem els algorismes");
        Genetic_algorithm genetic = new Genetic_algorithm(dif);

        System.out.println("Algorisme genetic generat");

        int num_colors = dif.get_num_colors();
        int rondes = 0;
        Integer[] colors = get_colors(num_colors);

        List<List<Integer>> allCodes = generateRandomCodes(Arrays.asList(colors), 4, num_proves, dif);
        System.out.println("Solucions generades");

        for (List<Integer> new_solution : allCodes) {

            System.out.println("Solució: " + new_solution.toString());
            List<List<Integer>> solutions_generated = genetic.solve(new_solution);
            rondes+=solutions_generated.size();
            for(List<Integer> solution : solutions_generated) {
                System.out.println(solution.toString());
            }
        }
        double rondes_per_partida = (double)rondes/(double)num_proves;
        System.out.println("Ratio de rondes per partida: " + rondes_per_partida);
    }


    private void test_both(int dificultat, int num_proves){

        double total_points_5g = 0.0;
        double total_points_genetic = 0.0;
        double total_guesses = (double)num_proves;
        double current_guesses_fg = 0.0;
        double current_guesses_genetic = 0.0;

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
        System.out.println("Generem els algorismes");
        Genetic_algorithm genetic = new Genetic_algorithm(dif);

        System.out.println("Algorisme genetic generat");

        Five_guess_algorithm fiveguess = new Five_guess_algorithm(dif);

        System.out.println("Algorisme five guess generat");

        int num_colors = dif.get_num_colors();

        Integer[] colors = get_colors(num_colors);

        List<List<Integer>> allCodes = generateRandomCodes(Arrays.asList(colors), 4, num_proves, dif);
        System.out.println("Solucions generades");

        for (List<Integer> new_solution : allCodes) {
            List<List<Integer>> solutions_generated_5g = fiveguess.solve(new_solution);
            List<List<Integer>> solutions_generated_genetic = genetic.solve(new_solution);

            total_points_5g += solutions_generated_5g.size();
            current_guesses_fg++;
            
            if(solutions_generated_genetic != null) {
                total_points_genetic += solutions_generated_genetic.size();
                ++current_guesses_genetic;
            }

            System.out.println("CURRENT medium of points genetic : " + total_points_genetic/current_guesses_genetic);
            System.out.println("CURRENT medium of points five guess : " + total_points_5g/current_guesses_fg);
            System.out.println("----------------------- ");
        }

        System.out.println("Total points genetic: " + total_points_genetic/total_guesses);
        System.out.println("Total points five guess: " + total_points_5g/total_guesses);


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
        System.out.println("Compararlos: 3");

        int input = in.nextInt();

        while(input != 1 && input != 2 && input != 3){
            System.out.println("Introdueix un numero valid");
            input = in.nextInt();
        }

        return input;
    }

    private int pregunta_num_proves() {
        System.out.println("Quantes proves vols fer?");

        int input = in.nextInt();

        while(input < 1){
            System.out.println("Introdueix un numero valid");
            input = in.nextInt();
        }

        return input;
    }


    private static List<List<Integer>> generateRandomCodes(List<Integer> colors, int solutionLength, int num_proves, dificultats dificultat) {
        
        List<List<Integer>> allCodes = new ArrayList<>();
        //generem codis aleatoris que seran la solució
        for(int i = 0; i < num_proves; ++i) {
            List<Integer> code = new ArrayList<>();
            for(int j = 0; j < solutionLength; ++j) {
                code.add((int)(Math.random() * dificultat.get_num_colors()));
                if(code.get(j) < 1) code.set(j, 1);
            }
            allCodes.add(code);
        }

        return allCodes;
    }

    private Integer[] get_colors(int num_colors){
        Integer[] colors = new Integer[num_colors];

        for(int i = 0; i < num_colors; ++i){
            colors[i] = i+1;
        }
        return colors;
    }

    private void print_colors(){
        System.out.println("colors: vermell, verd, blau, groc, magenta, cian, marro, gris\n");
    }
}