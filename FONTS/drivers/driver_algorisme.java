package drivers;

import main.domain.classes.Sequencia;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.algorismes.*;
import main.domain.classes.enumerations.colors;

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
        for (int i = 0; i < 4; ++i) {
            try {
                String input = in.nextLine();
                while (input.length() == 0) input = in.nextLine();
                arr_int[i] = crea_array_color(input);
            } catch (Exception ex) {
                --i;
                System.out.println(ex.getMessage());
            }
        }

        Maquina maq = new Maquina();
        List<List<Integer>> = maq.solve(solucio);

        System.out.print();
    }
}