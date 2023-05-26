package drivers;

import main.domain.controller.Controlador_Domini;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.enumerations.colors;
import main.domain.classes.types.Pair;
import main.domain.classes.Sequencia;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.Sequencia_verificacio;


import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.time.*;
import java.util.ArrayList;
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
                            domini.loginUsuari2(username, password);
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

    private void personalitza_partida(){
        System.out.println("Creació Nova Partida");
        System.out.println("Introdueix: \n" + "1 - Jugar contra la maquina\n" + "2 - Jugador vs Jugador");
        String tipus_partida = in.nextLine();
        while (tipus_partida.length() == 0) tipus_partida = in.nextLine();
        switch (tipus_partida){
            case "1":{
                String dif_partida = pregunta_dificultat();
                boolean rol = pregunta_rol();

                System.out.println("dificultat i rol ben entrats\n");
                boolean algoritme_genetic = pregunta_genetic();
                switch (dif_partida){
                    case "1":{
                        try {
                            System.out.println("nova partida vs maquina iniciada\n");
                            domini.inicialitza_partida_nova(dificultats.FACIL, algoritme_genetic, rol);
                            jugar_partida_maquina();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());

                        }
                        break;
                    }
                    case "2":{
                        try {
                            System.out.println("nova partida vs maquina iniciada\n");
                            domini.inicialitza_partida_nova(dificultats.NORMAL, algoritme_genetic, rol);
                            System.out.println("Entrem a jugar partida\n");
                            jugar_partida_maquina();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                    case "3":{
                        try {
                            System.out.println("nova partida vs maquina iniciada\n");
                            domini.inicialitza_partida_nova(dificultats.DIFICIL, algoritme_genetic, rol);
                            jugar_partida_maquina();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                }
                break;
            }
            case "2":{
                System.out.print("Introdueix User2");
                print_login();
                String input = in.nextLine();
                login(input,2);
                boolean rol = pregunta_rol();
                String dif_partida = pregunta_dificultat();
                switch (dif_partida){
                    case "1":{
                        try {
                            domini.inicialitza_partida_nova_pvp(dificultats.FACIL,rol);
                            jugar_partida_pvp();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());

                        }
                        break;
                    }
                    case "2":{
                        try {
                            domini.inicialitza_partida_nova_pvp(dificultats.NORMAL,rol);
                            System.out.println("Partida inicialitzada\n");

                            jugar_partida_pvp();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                    case "3":{
                        try {
                            domini.inicialitza_partida_nova_pvp(dificultats.DIFICIL,rol);
                            jugar_partida_pvp();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    private boolean pregunta_rol() {
        System.out.println("Introdueix: \n" + "1 - Ser CodeMaker " + "0 - Ser CodeBreaker\n");

        String Rol_partida = in.nextLine();
        if ((Rol_partida.equals("True") || Rol_partida.equals("1")) || (Rol_partida.equals("False") || Rol_partida.equals("0")) ){
            switch(Rol_partida) {
                case "True":
                case "1":
                    return true;
                case "False":
                case "0":
                    return false;
            }
        }
        return pregunta_rol();
    }

    private String pregunta_dificultat() {
        System.out.println("Selecciona Dificultat: \n" + "1 - Dificultat Facil\n" + "2 - Dificultat Normal\n" + "3 - Dificultat Dificil");
        String dif_partida = in.nextLine();
        if (dif_partida.equals("1") || dif_partida.equals("2") || dif_partida.equals("3")) return dif_partida;
        return pregunta_dificultat();
    }

    private boolean pregunta_genetic() {
        System.out.println("Selecciona Algorisme de la maquina: \n" + "0- Algorisme Five-Guess\n" + "1- Algorisme Genetic\n");
        Integer is_genetic = in.nextInt();
        if(is_genetic == 0) return false;
        else if(is_genetic == 1) return true;
        else return pregunta_genetic();
    }
    private void jugar_partida_pvp() {
        int ronda_actual = domini.get_num_ronda_actual();
        dificultats dif = domini.get_dificultat_partida();
        boolean ajuda = domini.get_ajuda_partida();
        boolean acabar = false;
        if (ronda_actual == -1) {
            codemaker_entra_solucio(dif);
        }
        while (ronda_actual <= dif.get_num_max_rondes() && !domini.partida_acabada() && !acabar) {
            System.out.println("Dessitges acabar la partida? \nIntrodueix: 'acabar' per finalitzarla." + "\nEn cas contari pulsa enter per conitnuar");
            String input = in.nextLine();
            switch (input){
                case "acabar":{
                    acabar = true;
                    break;
                }
                default: break;
            }
            if (!acabar){
                ++ronda_actual;
                System.out.println("Ronda Actual: " + ronda_actual);
                if (ronda_actual >= 5 && !ajuda) {
                    ajuda = vols_ajuda();
                    if (ajuda){
                        try {
                            domini.set_ajuda();
                            donar_ajuda();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                Sequencia_intentada seq_int = codebreaker_entra_intentada(dif);
                Sequencia_verificacio seq_ver = codemaker_entra_verificacio(seq_int);
                try {
                    domini.jugar_ronda_intentada(seq_int);
                    domini.jugar_ronda_verificacio(seq_ver);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                if (ronda_actual == dif.get_num_max_rondes()) domini.tractament_partida_acabada();
            }
            else {
                domini.tractament_partida_acabada();
                System.out.println("Sequencia solucio: {" + domini.get_seq_solucio().get_array()[0].get_nom_color() + "," + domini.get_seq_solucio().get_array()[1].get_nom_color() + "," + domini.get_seq_solucio().get_array()[2].get_nom_color() + "," + domini.get_seq_solucio().get_array()[3].get_nom_color() + "}\n");            }
        }
        if (domini.partida_acabada() && ronda_actual < dif.get_num_max_rondes() && !acabar){
            System.out.println("CodeBreaker Guanya la Partida\n");
        }else System.out.println("CodeMaker Guanya la Partida\n");
    }
    private void jugar_partida_maquina(){
        int ronda_actual = domini.get_num_ronda_actual();
        boolean ajuda = domini.get_ajuda_partida();
        dificultats dif = domini.get_dificultat_partida();
        boolean jug_1_cm = domini.get_jugador1_es_codemaker();
        boolean acabar = false;
        boolean pause = false;
        if(ronda_actual == 0) {
            if (jug_1_cm){
                codemaker_entra_solucio(dif);
            }else {
                genera_sequencia_solucio_random(dif);
            }
        }

        if(domini.get_seq_solucio() == null) {
            List<Integer> solucio = new ArrayList<Integer>(4);
            solucio = domini.get_seq_solucio().toListInteger();
            List<List<Integer>> resultat = domini.get_solve_maquina(solucio);
            domini.set_solucio_partida_actual(resultat);
        }

        while (ronda_actual <= dif.get_num_max_rondes() && !domini.partida_acabada() && !acabar && !pause) {
            System.out.println("Dessitges continuar jugant? \n Introdueix: 'acabar' per finalitzarla o 'pausa' per pausar-la" + "\nEn cas contari pulsa enter per conitnuar");
            String input = in.nextLine();
            switch (input){
                case "acabar":{
                    acabar = true;
                    break;
                }
                case "pausa":{
                    pause = true;
                    break;
                }
                default: break;
            }

            if (!acabar && !pause){
                ++ronda_actual;
                System.out.println("Ronda Actual: " + ronda_actual);
                if (ronda_actual >= 5 && !ajuda && jug_1_cm) {
                    ajuda = vols_ajuda();

                    if (ajuda){
                        try {
                            domini.set_ajuda();
                            donar_ajuda();
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                Sequencia_intentada seq_int = new Sequencia_intentada();
                Sequencia_verificacio seq_ver = new Sequencia_verificacio();
                if(jug_1_cm) {
                    seq_int = maquina_entra_intentada(dif);
                    seq_ver = codemaker_entra_verificacio(seq_int);
                }else {
                    seq_int = codebreaker_entra_intentada(dif);
                    seq_ver = maquina_entra_verificacio(seq_int);
                }
                try {
                    domini.jugar_ronda_intentada(seq_int);
                    domini.jugar_ronda_verificacio(seq_ver);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                if (ronda_actual == 10) domini.tractament_partida_acabada();
            }
            else {
                if (acabar){
                    System.out.println("Sequencia solucio: {" + domini.get_seq_solucio().get_array()[0].get_nom_color() + "," + domini.get_seq_solucio().get_array()[1].get_nom_color() + "," + domini.get_seq_solucio().get_array()[2].get_nom_color() + "," + domini.get_seq_solucio().get_array()[3].get_nom_color() + "}\n");
                    domini.tractament_partida_acabada();
                }
                else if(pause) domini.guardar_partida_a_mitges();
            }

        }
        if (domini.partida_acabada() && ronda_actual < dif.get_num_max_rondes() && !acabar){
            System.out.println("CodeBreaker Guanya la Partida\n");
        }else if (acabar)System.out.println("CodeMaker Guanya la Partida\n");
    }
    private Sequencia_intentada codemaker_entra_solucio(dificultats dif){
        System.out.println("\n" + "CodeMaker Introdueix la solucio (De mida 4)");
        print_colors(dif.get_num_colors());
        Sequencia_intentada solucio = new Sequencia_intentada();
        colors[] arr_sol = new colors[4];
        for (int i = 0; i < 4; ++i) {
            try {
                String input = in.nextLine();
                while (input.length() == 0) input = in.nextLine();
                arr_sol[i] = crea_array_color(input);
            } catch (Exception ex) {
                --i;
                System.out.println(ex.getMessage());
            }
        }
        try {
            solucio.set_array(arr_sol, dif.get_num_colors());
        }catch(Exception e) {
            System.out.println(e.getMessage());

        }
        domini.set_seq_solucio(solucio);
        System.out.print("\033[H\033[2J");
        System.out.flush();

        return solucio;
    }

    private void genera_sequencia_solucio_random(dificultats dif) {
        domini.genera_solucio_partida(dif);
    }

    private Sequencia_intentada codebreaker_entra_intentada(dificultats dif ) {
        Sequencia_intentada seq_int = new Sequencia_intentada();

        colors[] arr_int = new colors[4];

        System.out.println("CodeBreaker Introdueix la Sequencia Intentada");
        print_colors(dif.get_num_colors());

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
        try {
            seq_int.set_array(arr_int, dif.get_num_colors());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sequencia intentada: {" + arr_int[0] + "," + arr_int[1] + "," + arr_int[2] + "," + arr_int[3] + "}\n");
        return seq_int;
    }

    private Sequencia_intentada maquina_entra_intentada(dificultats dif) {

        List<Integer> list_int= domini.get_seguent_guess_maquina();
        Sequencia_intentada seq_int = de_list_a_seq(list_int, dif);

        System.out.println("Sequencia intentada per la maquina: {" + seq_int.get_array()[0].get_nom_color() + "," + seq_int.get_array()[1].get_nom_color() + "," + seq_int.get_array()[2].get_nom_color() + "," + seq_int.get_array()[3].get_nom_color() + "}\n");
        return seq_int;
    }

    private Sequencia_verificacio codemaker_entra_verificacio(Sequencia seq_int) {
        System.out.println("CodeMaker Introdueix la Sequencia Verificacio");
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        colors[] arr_ver = new colors[4];

        print_colors(0);
        for (int i = 0; i < 4; ++i) {
            try {
                String input = in.nextLine();
                while (input.length() == 0) input = in.nextLine();
                arr_ver[i] = crea_array_ver(input);
            } catch (Exception ex) {
                --i;
                System.out.println(ex.getMessage());
            }
        }
        try {
            seq_ver.set_array_verificacio(arr_ver, domini.get_seq_solucio().get_array(), seq_int.get_array());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            codemaker_entra_verificacio(seq_int);
        }

        System.out.println("Sequencia verificacio: {" + arr_ver[0] + "," + arr_ver[1] + "," + arr_ver[2] + "," + arr_ver[3] + "}\n");
        return seq_ver;
    }

    private Sequencia_verificacio maquina_entra_verificacio(Sequencia_intentada seq_int) {
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        Sequencia_intentada seq_sol = domini.get_seq_solucio();

        Pair<Integer, Integer> resultat= negres_blanques(seq_int, seq_sol);

        seq_ver = crea_seq_ver_random(resultat.first(), resultat.second());

        System.out.println("Sequencia verificacio de la maquina: {" + seq_ver.get_array()[0] + "," + seq_ver.get_array()[1] + "," + seq_ver.get_array()[2] + "," + seq_ver.get_array()[3] + "}\n");
        return seq_ver;
    }
    private Pair<Integer, Integer> negres_blanques(Sequencia_intentada seq_int,Sequencia_intentada seq_sol) {
        int negres = 0;
        int blanques = 0;
        Pair<Integer, Integer> res = new Pair<>(negres, blanques);

        for (int i = 0; i < 4; ++i){
            if (seq_int.get_array()[i] == seq_sol.get_array()[i]) {
                ++negres;
                res.set_first(negres);
            }
            else {
                boolean done = false;
                for (int j = 0; j < 4 && !done; ++j){
                    if (seq_int.get_array()[i] == seq_sol.get_array()[j]){
                        ++blanques;
                        res.set_second(blanques);
                        done = true;
                    }
                }
            }
        }

        return res;
    }
    private Sequencia_intentada de_list_a_seq(List<Integer> llista, dificultats dif) {
        colors[] aux = new colors[4];
        for(int i = 0; i < 4; ++i) {
            aux[i] = colors.get_color_by_id(llista.get(i));
        }
        Sequencia_intentada ret = new Sequencia_intentada();
        try{
            ret.set_array(aux, dif.get_num_colors());
        }catch(Exception e) {
            System.out.println(e.getMessage());
            maquina_entra_intentada(dif);
        }

        return ret;
    }

    private Sequencia_verificacio crea_seq_ver_random(Integer negres, Integer blanques) {
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        while(negres > 0) {
            int i = (new Random()).nextInt(4);
            if(seq_ver.get_array()[i] == colors.NULL) {
                --negres;
                seq_ver.set_position(i, colors.NEGRE);
            }
        }
        while(blanques > 0) {
            int i = (new Random()).nextInt(4);
            if(seq_ver.get_array()[i] != colors.NEGRE && seq_ver.get_array()[i] != colors.BLANC) {
                --blanques;
                seq_ver.set_position(i, colors.BLANC);
            }
        }

        return seq_ver;
    }

    private boolean vols_ajuda() {
        System.out.println("Demanar ajuda? \n Introdueix: true or false");
        boolean ret = in.nextBoolean();
        return ret;
    }

    private void donar_ajuda(){
        System.out.println("Introdueix la posicio de la solucio vols saber?");
        String input = in.nextLine();
        while (input.length() == 0) input = in.nextLine();
        int pos = Integer.parseInt(input);
        Sequencia_intentada sol = domini.get_seq_solucio();
        System.out.println("El color de la solucio en la posicio " + pos + " es " + sol.get_array()[pos].get_nom_color() + "\n");
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