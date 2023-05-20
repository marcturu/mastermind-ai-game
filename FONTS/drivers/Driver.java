package drivers;

import main.domain.controller.Controlador_Domini;

import java.util.Scanner;

public class Driver {
    private Scanner in = null;

    private Controlador_Domini domini;

    private void print_login() {
        System.out.println("\n" + "(Introdueix: '1' o 'login') - Iniciar Sessio");
        System.out.println("(Introdueix: '2' o 'register') - Registrarte");
    }

    private void login(String input, int user_tip) {
        boolean ok = false;
        while (!ok) {
            if (user_tip == 1) {
                switch (input) {
                    case "1":
                    case "login": {
                        try {
                            System.out.println("Introdueix Username");
                            String username = in.nextLine();
                            while (username.length() == 0) username = in.nextLine();
                            System.out.println("Introdueix Contrasenya");
                            String password = in.nextLine();
                            while (password.length() == 0) username = in.nextLine();
                            domini.loginUsuari1(username,password);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        ok = true;
                        break;
                    }
                    case "2":
                    case "register": {
                        ok = true;
                        System.out.println("Introdueix el teu Nou Username");
                        String username = in.nextLine();
                        while (username.length() == 0) username = in.nextLine();
                        System.out.println("Introdueix la teva Nova Contrasenya");
                        String password = in.nextLine();
                        while (password.length() == 0) password = in.nextLine();
                        domini.inicialitzaUserPersona(username, password);
                        ok = true;
                        break;
                    }
                }
            } else {
                switch (input) {
                    case "1":
                    case "login": {
                        try {
                            System.out.println("Introdueix Username2");
                            String username = in.nextLine();
                            while (username.length() == 0) username = in.nextLine();
                            System.out.println("Introdueix Contrasenya");
                            String password = in.nextLine();
                            while (password.length() == 0) username = in.nextLine();
                            //System.out.println("Usuari No Existeix");
                           // domini.loginUsuari2(username, password);
                            ok = true;
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                    case "2":
                    case "register": {
                        try {
                            System.out.println("Introdueix el teu Nou Username2");
                            String username = in.nextLine();
                            while (username.length() == 0) username = in.nextLine();
                            System.out.println("Introdueix la teva Nova Contrasenya");
                            String password = in.nextLine();
                            while (password.length() == 0) password = in.nextLine();
                            domini.inicialitzaUserPersona2(username, password);
                            ok = true;
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                }
            }
            if (!ok) {
                print_login();
                input = in.nextLine();
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
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Driver driver = new Driver();
        driver.domini = new Controlador_Domini();
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
                    //driver.personalitza_partida();
                    break;
                }
                case "2":
                case "jugar":{
                    //driver.jugar_partides_antigues();
                    break;
                }
                case "3":
                case "antigues":{
                    //driver.veure_partides_antigues();
                    break;
                }
                case "4":
                case "ranking":{
                    //driver.veure_ranking();
                    break;
                }
                case "5":
                case "record":{
                    //driver.veure_record();
                    break;
                }
                case "6":
                case "stats":{
                   // driver.veure_stats();
                    break;
                }
            }
            input = driver.in.nextLine();
            driver.print_menu();
        }
        driver.in.close();
    }
}