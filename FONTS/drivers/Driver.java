package drivers;

import main.domain.classes.enumerations.colors;
/*import main.domain.classes.*;
import main.domain.controller.Controlador_Domini;
*/
import java.util.*;

public class Driver {
    private Scanner in = null;

    //private Controlador_Domini domini;
    private void print_login(){
        System.out.println("\n"+"(Introdueix: '1' o 'login') - Iniciar Sessio");
        System.out.println("(Introdueix: '2' o 'register') - Registrarte");
    }

    private void login(String input, int user_tip){
        boolean ok = false;
        while (!ok){
            if (user_tip == 1) {
                switch (input) {
                    case "1":
                    case "login": {
                        System.out.println("Introdueix Username");
                        String username = in.nextLine();
                        System.out.println("Introdueix Contrasenya");
                        String password = in.nextLine();
                        System.out.println("Usuari No Existeix");
                        break;
                    }
                    case "2":
                    case "register": {
                        ok = true;
                        System.out.println("Introdueix el teu Nou Username");
                        String username = in.nextLine();
                        System.out.println("Introdueix la teva Nova Contrasenya");
                        String password = in.nextLine();
                        //domini.inicialitzaUserPersona(username,password);
                        break;
                    }
                }
            }
            else {
                switch (input) {
                    case "1":
                    case "login": {
                        System.out.println("Introdueix Username2");
                        String username = in.nextLine();
                        System.out.println("Introdueix Contrasenya");
                        String password = in.nextLine();
                        System.out.println("Usuari No Existeix");
                        break;
                    }
                    case "2":
                    case "register": {
                        ok = true;
                        System.out.println("Introdueix el teu Nou Username2");
                        String username = in.nextLine();
                        System.out.println("Introdueix la teva Nova Contrasenya");
                        String password = in.nextLine();
                        //domini.inicialitzaUserPersona2(username,password);
                        break;
                    }
                }
            }
            if (!ok){
                print_login();
                input = in.nextLine();
            }
        }
    }

    private void personalitza_partida(){
        System.out.println("Creació Nova Partida");
        System.out.println("Introdueix: \n" + "1 - Jugar contra la maquina\n" + "2 - Jugador vs Jugador");
        String tipus_partida = in.nextLine();
        switch (tipus_partida){
            case "1":{
                System.out.println("Introdueix: \n" + "0 - Ser CodeBreaker " + "1 - Ser CodeMaker\n");
                int Rol_partida = in.nextInt();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                int dif_partida = in.nextInt();
                try{
                    //domini.inicialitza_partida(dif_partida,Rol_partida);
                    jugar_partida_maquina();
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
            case "2":{
                System.out.print("Introdueix User2");
                print_login();
                String input = in.nextLine();
                login(input,2);
                System.out.println("Introdueix: \n" + "0 - User1 CodeBreaker"+ "1 - User1 CodeMaker\n");
                int Rol_partida = in.nextInt();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                int dif_partida = in.nextInt();
                try {
                    //domini.inicialitza_partida_nova_pvp(dif_partida,Rol_partida);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
    }

    private void print_colors(int num_colors){
        if (num_colors == 4){
            System.out.println("vermell, verd, blau, groc\n");
        } else if (num_colors == 6) {
            System.out.println("vermell, verd, blau, groc, magenta, cian\n");
        } else if (num_colors == 8) {
            System.out.println("vermell, verd, blau, groc, magenta, cian, marro, gris\n");
        }
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

    private void jugar_partida_maquina(){
        int ronda_actual;
        int num_colors = 6; // = domini.get_num_colors();
        int num_rondas_max; // = domini.get_num_rondes();
        boolean ajuda; // = domini.get_ajuda_partida();
        boolean jug_1_cm = true; // domini.jugador_1_codemaker();
        if (jug_1_cm){
            System.out.println("\n"+"Introdueix la solucio (De mida 4)");
            print_colors(num_colors);
            colors[] color = new colors[4];
            for (int i = 0; i < 4; ++i){
                String input = in.nextLine();
                try {
                    color[i] = crea_array_color(input);
                } catch (Exception ex){
                    --i;
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    private void print_menu(){
        System.out.println("\n"+"(Introdueix: '1' o 'crear') - Crear Nova Partida");
        System.out.println("(Introdueix: '2' o 'jugar') - Jugar Partides NO Acabades");
        System.out.println("(Introdueix: '3' o 'antigues') - Veure Partides antigues");
        System.out.println("(Introdueix: '4' o 'ranking') - Veure Rankings");
        System.out.println("(Introdueix: '5' o 'record') - Veure Records");
        System.out.println("(Introdueix: '6' o 'stats') - Veure Estadistiques del Juagador");
        System.out.println("(Introdueix: '0' o 'tancar') - Tancar Joc");
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        //driver.domini = new Controlador_Domini();
        System.out.println("Mastermind (PROP Grup 13.2)");
        driver.print_login();
        driver.in = new Scanner(System.in);
        String input = driver.in.nextLine();
        driver.login(input,1);
        driver.print_menu();
        input = driver.in.nextLine();
        while (!input.equals("0") && !input.equals("tancar")){
            switch (input){
                case "1":
                case "crear":{
                    driver.personalitza_partida();
                    break;
                }
                case "2":
                case "jugar":{
                    break;
                }
                case "3":
                case "antigues":{
                    break;
                }
                case "4":
                case "ranking":{
                    break;
                }
                case "5":
                case "record":{
                    break;
                }
                case "6":
                case "stats":{
                    break;
                }
            }
            driver.print_menu();
            input = driver.in.nextLine();
        }
        driver.in.close();
    }
}