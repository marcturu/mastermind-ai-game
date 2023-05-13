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
                            //System.out.println("Usuari No Existeix");
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
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

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Driver driver = new Driver();
        driver.domini = new Controlador_Domini();
        driver.print_login();
        driver.in = new Scanner(System.in);
        String input = driver.in.nextLine();
        while (true){
            driver.login(input, 1);
        }
        //driver.in.close();
    }
}