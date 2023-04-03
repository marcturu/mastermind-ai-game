package drivers;

/*import main.domain.classes.*;
import main.domain.classes.enumerations.*;
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

    private void print_menu(){
        System.out.println("\n"+"(Introdueix: '1' o 'crear') - Crear Nova Partida");
        System.out.println("(Introdueix: '2' o 'jugar') - Jugar Partides NO Acabades");
        System.out.println("(Introdueix: '3' o 'antigues') - Veure Partides antigues");
        System.out.println("(Introdueix: '4' o 'ranking') - Veure Rankings");
        System.out.println("(Introdueix: '5' o 'record') - Veure Records");
        System.out.println("(Introdueix: '6' o 'stats') - Veure Estadistiques del Juagador");
        System.out.println("(Introdueix: '0' o 'tancar') - Tancar Joc");
    }

    private void personalitza_partida(){
        System.out.println("Creació Nova Partida");
        System.out.println("Introdueix: \n" + "1 - Jugar contra la maquina\n" + "2 - Jugador vs Jugador");
        String tipus_partida = in.nextLine();
        switch (tipus_partida){
            case "1":{
                System.out.println("Introdueix: \n" + "1 - Ser CodeMaker\n" + "2 - Ser CodeBreaker");
                String Rol_partida = in.nextLine();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                int dif_partida = in.nextInt();
                break;
            }
            case "2":{
                System.out.print("Introdueix User2");
                print_login();
                String input = in.nextLine();
                login(input,2);
                System.out.println("Introdueix: \n" + "1 - User1 CodeMaker\n" + "2 - User1 CodeBreaker");
                String Rol_partida = in.nextLine();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                int dif_partida = in.nextInt();
                break;
            }
        }
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