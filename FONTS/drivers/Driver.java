package drivers;

/*import main.domain.classes.*;
import main.domain.controller.Controlador_Domini;
*/
import java.util.List;
import java.util.Scanner;

import main.domain.classes.Partida;
import main.domain.classes.Ronda;
import main.domain.classes.enumerations.colors;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.enumerations.type_seq;
import main.domain.controller.Controlador_Domini;

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
                        while (username.length() == 0) username = in.nextLine();
                        System.out.println("Introdueix Contrasenya");
                        String password = in.nextLine();
                        while (password.length() == 0) username = in.nextLine();
                        System.out.println("Usuari No Existeix");
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
                        while (password.length() == 0) username = in.nextLine();
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
                        while (username.length() == 0) username = in.nextLine();
                        System.out.println("Introdueix Contrasenya");
                        String password = in.nextLine();
                        while (password.length() == 0) username = in.nextLine();
                        System.out.println("Usuari No Existeix");
                        break;
                    }
                    case "2":
                    case "register": {
                        ok = true;
                        System.out.println("Introdueix el teu Nou Username2");
                        String username = in.nextLine();
                        while (username.length() == 0) username = in.nextLine();
                        System.out.println("Introdueix la teva Nova Contrasenya");
                        String password = in.nextLine();
                        while (password.length() == 0) username = in.nextLine();
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
                String Rol_partida = in.nextLine();
                while (Rol_partida.length() == 0) Rol_partida = in.nextLine();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                String dif_partida = in.nextLine();
                while (dif_partida.length() == 0) dif_partida = in.nextLine();
                int rol = Integer.parseInt(Rol_partida);
                int dif = Integer.parseInt(dif_partida);
                try{
                    //domini.inicialitza_partida(parseInt(dif_partida),parseInt(Rol_partida));
                    jugar_partida_maquina(dif);
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
                System.out.println("Introdueix: \n" + "0 - User1 CodeBreaker"+ "1 - User1 CodeMaker");
                String Rol_partida = in.nextLine();
                while (Rol_partida.length() == 0) Rol_partida = in.nextLine();
                System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
                String dif_partida = in.nextLine();
                while (dif_partida.length() == 0) dif_partida = in.nextLine();
                int rol = Integer.parseInt(Rol_partida);
                int dif = Integer.parseInt(dif_partida);
                try {
                    //domini.inicialitza_partida_nova_pvp(parseInt(dif_partida),parseInt(Rol_partida));
                    jugar_partida_pvp(dif);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
    }

    private void print_colors(int num_colors){
        if (num_colors == 4){
            System.out.println("Introdueix: vermell, verd, blau, groc\n");
        } else if (num_colors == 6) {
            System.out.println("Introdueix: vermell, verd, blau, groc, magenta, cian\n");
        } else if (num_colors == 8) {
            System.out.println("Introdueix: vermell, verd, blau, groc, magenta, cian, marro, gris\n");
        } else if (num_colors == 0){
            System.out.println("Introdueix: blanc, negre o null");
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

    private colors crea_array_ver(String input) throws Exception {
        switch (input){
            case "blanc":{
                return colors.BLANC;
            }
            case "negre":{
                return colors.NEGRE;
            }
            case "null":{
                return colors.NULL;
            }
            default:{
                throw new Exception("Color Incorrecte");
            }
        }
    }

    private void jugar_partida_maquina(int dif_partida){
        int ronda_actual;
        dificultats dif = dificultats.NORMAL ;
        if (dif_partida == 1) dif = dificultats.FACIL;
        if (dif_partida == 3) dif = dificultats.DIFICIL;
        boolean ajuda; // = domini.get_ajuda_partida();
        boolean jug_1_cm = true; // domini.jugador_1_codemaker();
        if (jug_1_cm){
            System.out.println("\n"+"Introdueix la solucio (De mida 4)");
            print_colors(dif.get_num_colors());
            colors[] color = new colors[4];
            for (int i = 0; i < 4; ++i) {
                try {
                    String input = in.nextLine();
                    while (input.length() == 0) input = in.nextLine();
                    color[i] = crea_array_color(input);
                } catch (Exception ex) {
                    --i;
                    System.out.println(ex.getMessage());
                }
            }
            //domini.set_solucio(color);
        }
    }

    private void jugar_partida_pvp(int dif_partida){
        int ronda_actual = 1; // = domini.get_num_ronda_actual();
        dificultats dif = dificultats.NORMAL;
        if (dif_partida == 1) dif = dificultats.FACIL;
        if (dif_partida == 3) dif = dificultats.DIFICIL;
        boolean ajuda; // = domini.get_ajuda_partida();
        if (ronda_actual != 1) {
            System.out.println("\n" + "CodeMaker Introdueix la solucio (De mida 4)");
            print_colors(dif.get_num_colors());
            colors[] color = new colors[4];
            for (int i = 0; i < 4; ++i) {
                try {
                    String input = in.nextLine();
                    while (input.length() == 0) input = in.nextLine();
                    color[i] = crea_array_color(input);
                } catch (Exception ex) {
                    --i;
                    System.out.println(ex.getMessage());
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        while (ronda_actual <= dif.get_num_max_rondes()){
            colors[] seq_int = new colors[4];
            colors[] seq_ver = new colors[4];
            System.out.println("CodeBreaker Introdueix la Sequencia Intentada");
            print_colors(dif.get_num_colors());

            for (int i = 0; i < 4; ++i) {
                try {
                    String input = in.nextLine();
                    while (input.length() == 0) input = in.nextLine();
                    seq_int[i] = crea_array_color(input);
                } catch (Exception ex) {
                    --i;
                    System.out.println(ex.getMessage());
                }
            }

            System.out.println("CodeMaker Introdueix la Sequencia Verificacio");
            print_colors(0);
            for (int i = 0; i < 4; ++i) {
                try {
                    String input = in.nextLine();
                    while (input.length() == 0) input = in.nextLine();
                    seq_ver[i] = crea_array_ver(input);
                } catch (Exception ex) {
                    --i;
                    System.out.println(ex.getMessage());
                }
            }
            try {
                //domini.jugar_ronda(seq_int,seq_ver);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }



    }
/*
    private void imprimeix_tauler(List<Ronda> partida){
        int size = partida.size();
        for (int i = size; i >= 0; --i){
            System.out.print("Ronda " + i);
            Ronda ronda = partida.get(i);
            colors[] intentada = ronda.get_seq_intentada().get_array();
            colors[] verficacio = ronda.get_seq_verificacio().get_array();
            System.out.println("\nSequencia Verficacio: " + verficacio[0].get_nom_color() + " " + verficacio[1].get_nom_color() +  " " + verficacio[2].get_nom_color() + " " + verficacio[3].get_nom_color());
            System.out.println("  Sequencia Intentada: " + intentada[0].get_nom_color() + " " + intentada[1].get_nom_color() +  " " + intentada[2].get_nom_color() + " " + intentada[3].get_nom_color() + "\n");

        }
    }

    private boolean id_ok(List<Integer> ids, int id){
        //int idd = Integer.parseInt(id);
        for (int i = 0; i < ids.size(); ++i){
            if (ids.get(i) == id) return true;
        }
        return false;
    }

    private void jugar_partides_antigues(){
        List<Integer> ids_partida; // = domini.get_ids_partides_actives_Usuari1();
        System.out.println("IDs de les partides no acabades:");
        for (int i = 0; i < ids_partida.size(); ++i){
            System.out.println(ids_partida.get(i));
        }
        System.out.println("Selecciona la partidas que vols continuar:");
        String id = in.nextLine();
        while (id.length() == 0) id = in.nextLine();
        int idd = Integer.parseInt(id);
        while (!id_ok(ids_partida,idd)){
            id = in.nextLine();
            while (id.length() == 0) id = in.nextLine();
        }
        Controlador_Domini.jugar_partides_antigues(idd);
    }

    private void veure_partides_antigues(){
        //List<int> ids_partides_acabdes = domini.get_ids_partides_acabades_Usuari1
        System.out.println("IDs de les partides acabades:");
        for (int i = 0; i < ids_partida.size(); ++i){
            System.out.println(ids_partida[i]);
        }
        System.out.println("Selecciona la partidas que vols veure:");
        String id = in.nextLine();
        while (id.length() == 0) id = in.nextLine();
        int idd = Integer.parseInt(id);
        while (!id_ok(ids_partida,idd)){
            id = in.nextLine();
            while (id.length() == 0) id = in.nextLine();
        }
        Partida part = domini.get_partida(idd);
        System.out.println("Partida amb id: " + part.get_id());
        for (int i = part.get_num_rondes_max(); i > 0; --i){
            System.out.println("Ronda: " + i);
            System.out.print("\n Sequencia de Verficacio: " + part.imprmeix_sequencia(type_seq.verificacio));
            System.out.print("\n Sequencia de Intentada : " + part.imprmeix_sequencia(type_seq.intentada));
        }

    }

    private void veure_ranking(){
        System.out.println("Selecciona dificultat per visualitzar el ranking, introdueix: " +
                "\n'facil' - Visualitar ranking dificultat Facil" +
                "\n'normal' - Visualitar ranking dificultat Normal" +
                "\n'dificil' - Visualitar ranking dificultat dificil" +
                "\n'pvp' - Visualitar ranking Player Vs Player");
        String dif = in.nextLine();
        while (dif.length() == 0) dif = in.nextLine();

    }*/


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
                    driver.jugar_partides_antigues();
                    break;
                }
                case "3":
                case "antigues":{
                    driver.veure_partides_antigues();
                    break;
                }
                case "4":
                case "ranking":{
                    driver.veure_ranking();
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