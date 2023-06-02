package main.presentation.controller;

import main.presentation.views.*;

import java.util.Collections;
import java.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

import main.domain.classes.enumerations.dificultats;
import main.domain.classes.types.Pair;

import main.domain.controller.*;

public class Controlador_Presentacio {
    private Controlador_Domini ctrlDomini;
    private view_inici viewInicial;

    private boolean j1_cm;
    private boolean j2_user;
    private boolean j2_maquina_genetica;
    private dificultats dificultat = dificultats.NORMAL;
    private boolean login_user2;
    private String dificultat_ranking;
    private List<List<Integer>> intents_anteriors;
    private List<List<Integer>> verificacions_anteriors;

    private boolean intent;

    public static void main(String[] args){
        Controlador_Presentacio ctrlPresentacio = new Controlador_Presentacio();
        ctrlPresentacio.canvia_a_inici();
    }

    public List<Integer> set_try(List<Integer> entrada) throws Exception {
        List<Integer> list = new ArrayList<>(Collections.nCopies(8, 0));
        if (ctrlDomini.get_seq_solucio() == null) {
            if (j1_cm && !j2_user) {
                ctrlDomini.set_seq_solucio(entrada);
                List<Integer> list_int = ctrlDomini.get_seguent_guess_maquina();
                list.subList(0, 4).clear(); // Eliminar elementos existentes en la sublista
                list.addAll(0, list_int);
                return list;

            }
            else if (j2_user){
                System.out.println("seT_sol" + entrada);
                ctrlDomini.set_seq_solucio(entrada);
                System.out.println("despres" + entrada);
                return list;
            }
            else {
                ctrlDomini.genera_solucio_partida(dificultat);
                ctrlDomini.jugar_ronda_intentada(entrada);
                list.subList(0, 4).clear(); // Eliminar elementos existentes en la sublista
                list.addAll(0, entrada);
                List<Integer> verificacio_maquina = ctrlDomini.get_verificacio();

                list.subList(4, 8).clear(); // Eliminar elementos existentes en la sublista
                list.addAll(4, verificacio_maquina);
                ctrlDomini.jugar_ronda_verificacio(verificacio_maquina);
                return list;
            }
        }
        else {
            //la solució ja està posada en principi
            if (j2_user) {
                if (intent) {
                    ctrlDomini.jugar_ronda_intentada(entrada);
                    list.subList(0, 4).clear(); // Eliminar elementos existentes en la sublista
                    list.addAll(0,entrada);
                    intent = false;
                    return list;
                } else {
                    ctrlDomini.jugar_ronda_verificacio(entrada);
                    list.subList(4, 8).clear(); // Eliminar elementos existentes en la sublista
                    list.addAll(4,entrada);
                    intent = true;
                    if (ctrlDomini.partida_acabada()) {
                        resetAtributes();
                        if (guanya_partida(entrada)) {
                            String partida_acabada = "Partida pvp acabada amb victòria del codebreaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            String partida_acabada = "Partida pvp acabada amb victòria del codemaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    return list;
                }
            } else { //juguem vs maquina
                if (j1_cm) {
                    System.out.println("try_set_ver");
                    ctrlDomini.jugar_ronda_verificacio(entrada);

                    if(!ctrlDomini.partida_acabada()){
                        List<Integer> list_int = ctrlDomini.get_seguent_guess_maquina();
                        System.out.println("abans_canvia_lis" + list);
                        list.subList(0, 4).clear(); // Eliminar elementos existentes en la sublista
                        list.addAll(0,list_int);
                        System.out.println("sub_list1_try" + list);
                        list.subList(4, 8).clear(); // Eliminar elementos existentes en la sublista
                        list.addAll(4,entrada);
                        System.out.println("sub_list2_try" + list);
                    }
                    else {
                        if (guanya_partida(entrada)) {
                            String partida_acabada = "Partida acabada amb victòria del codebreaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            String partida_acabada = "Partida acabada amb victòria del codemaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                        resetAtributes();
                    }
                    return list;
                } else {
                    ctrlDomini.jugar_ronda_intentada(entrada);
                    List<Integer> verificacio_maquina = ctrlDomini.get_verificacio();
                    ctrlDomini.jugar_ronda_verificacio(verificacio_maquina);
                    list.subList(0, 4).clear(); // Eliminar elementos existentes en la sublista
                    list.addAll(0,entrada);
                    list.subList(4, 8).clear(); // Eliminar elementos existentes en la sublista
                    list.addAll(4,verificacio_maquina);
                    if (ctrlDomini.partida_acabada()) {
                        if (guanya_partida(verificacio_maquina)) {
                            String partida_acabada = "Partida acabada amb victòria del codebreaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                        else {
                            String partida_acabada = "Partida acabada amb victòria del codemaker!";
                            JOptionPane.showMessageDialog(null, partida_acabada, "", JOptionPane.PLAIN_MESSAGE);
                        }
                        resetAtributes();
                    }
                    return list;
                }
            }
        }
    }

    public boolean guanya_partida(List<Integer> entrada) {
        List<Integer> lista = new ArrayList<>();
        lista.add(10);
        lista.add(10);
        lista.add(10);
        lista.add(10);

        return entrada.equals(lista);
    }

          /* if (!j1_cm){
                ctrlDomini.jugar_ronda_intentada(entrada);
                if (!j2_user) {
                    ctrlDomini.get_verificacio();
                }
                else
                    ctrlDomini.jugar_ronda_verificacio(entrada);
            }
            else{
                if (!j2_user){
                    ctrlDomini.jugar_ronda_intentada(ctrlDomini.get_seguent_guess_maquina());
                }
                else {
                    ctrlDomini.jugar_ronda_intentada(entrada);
                }
               ctrlDomini.jugar_ronda_verificacio(entrada);
            }*/

    /**
     * Constructora de la classe
     */
    public Controlador_Presentacio() {
        ctrlDomini = new Controlador_Domini();
        viewInicial = new view_inici(this);


        viewInicial.setVisible(true);
        
        //configuracio per defecte
        j1_cm = false;
        j2_user = false;
        j2_maquina_genetica = false;
        intent = true;
        login_user2 = false;

        intents_anteriors = new ArrayList<>();
        verificacions_anteriors = new ArrayList<>();
    }

    public List<Integer> carrega_ids_partides_acabades() {
        return ctrlDomini.get_ids_partides_acabades_Usuari1();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_inici
     */
    public void canvia_a_inici() {
        viewInicial.canvia_a_inici();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_login
     */
    public void canvia_a_login() {
        viewInicial.canvia_a_login();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_register
     */
    public void canvia_a_register() {
        viewInicial.canvia_a_register();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_menu_principal
     */
    public void canvia_a_menu_principal() {
        viewInicial.canvia_a_menu_principal();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_record
     */
    public void canvia_a_record() {
        viewInicial.canvia_a_record();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_ranking
     */
    public void canvia_a_ranking(String dificultat) {
        if (dificultat!=null) setDificultat_ranking(dificultat);

        viewInicial.canvia_a_ranking();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_user
     */
    public void canvia_a_user() {
        viewInicial.canvia_a_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_estadistiques_user
     */
    public void canvia_a_estadistiques_user() {
        viewInicial.canvia_a_estadistiques_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_historial_user
     */
    public void canvia_a_historial_user() {
        viewInicial.canvia_a_historial_user();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_rol
     */
    public void canvia_a_config_partida_rol() {
        viewInicial.canvia_a_config_partida_rol();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_oponent
     */
    public void canvia_a_config_partida_oponent() {
        viewInicial.canvia_a_config_partida_oponent();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_maquina
     */
    public void canvia_a_config_partida_oponent_maquina() {
        viewInicial.canvia_a_config_partida_oponent_maquina();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_config_partida_dificultat
     */
    public void canvia_a_config_partida_dificultat() {
        viewInicial.canvia_a_config_partida_dificultat();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_carregar_partida
     */
    public void canvia_a_carregar_partida() {
        viewInicial.canvia_a_carregar_partida();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_partida
     */
    public void canvia_a_partida() {
        viewInicial.canvia_a_partida();
    }

    /**
     * Funcio per a canviar el contentPane del frame a panel_dificultats_ranking
     */
    public void canvia_a_dificultats_ranking() {
        viewInicial.canvia_a_dificultats_ranking();
    }

    /**
     * Funcio que crida al controlador de domini perque intenti fer login amb un usuari_persona
     * @param username Nom d'usuari_persona
     * @param password Contrasenya d'usuari_persona
     * @throws Exception Si l'usuari no existeix o la contrasenya es incorrecta
     */
    public void crida_a_login_domini(String username, String password) throws Exception{
        try{
            if(login_user2) {
                ctrlDomini.loginUsuari2(username, password);}
            else {
                ctrlDomini.loginUsuari1(username, password);
            }
        }catch(Exception e) {
            mostra_error(e.getMessage());
            throw new Exception("Error: Password erroni");
        }
    }

    /**
     * Funcio que crida al controlador de domini perque registri un usuari_persona
     * @param username Nom d'usuari_persona
     * @param password Contrasenya d'usuari_persona
     * @throws Exception Si l'usuari ja existeix
     */
    public boolean crida_a_register_domini(String username, String password){
        try{
            if (login_user2) {
                if (!ctrlDomini.inicialitzaUserPersona2(username, password)) return false;
                else return true;
            }
            else {
                if (!ctrlDomini.inicialitzaUserPersona(username, password)) return false;
                else return true;
            }
        }catch(Exception e) {
            mostra_error(e.getMessage());
            return false;
        }
    }

    /**
     * Funcio que crida al controlador de domini perque retorni una llista de parelles (id_partida, info_patida) de les partides actives
     * @return Llista de parelles (id_partida, info_partida)
     */
    public List<Pair<Integer, String>> carrega_partides_no_acabades() {
        List<Pair<Integer, String>> llista_info = new ArrayList<>();
        List<Integer> ids_partides = ctrlDomini.get_ids_partides_actives_Usuari1();
        for(Integer id: ids_partides) {
            String info_partida = ctrlDomini.get_info_partida(id);
            llista_info.add(new Pair<>(id, info_partida));
        }
        return llista_info;
    }

    public boolean partida_acabada(){
        return ctrlDomini.partida_acabada();
    }

    /**
     * Funcio per a mostrar el tablero d'una partida acabada
     * @param index index de la partida acabada que volem carregar
     */
    public void carrega_partida_acabada(int index) {
        //tornem a carregar les partides(en el mateix ordre)
        List<Pair<Integer, String>> llista_info = get_llista_partides_acabades();
        try{
        ctrlDomini.jugar_partides_antigues(llista_info.get(index).first());
        }catch(Exception e) {
            mostra_error(e.getMessage());
        }
    }

    public void carrega_partida_actual(int index) {
        //tornem a carregar les partides(en el mateix ordre)
        List<Pair<Integer, String>> llista_info = carrega_partides_no_acabades();
        try{
            ctrlDomini.jugar_partides_antigues(llista_info.get(index).first());
            set_atributs_partida();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int get_partides_no_acabades() {
        return ctrlDomini.get_num_partides_actuals();
    }

    private void set_atributs_partida(){

        intents_anteriors = ctrlDomini.get_intents_partida();
        verificacions_anteriors = ctrlDomini.get_verificacions_partida();

        List<Integer> ultim_intent = intents_anteriors.get(get_num_ronda_actual());


        System.out.println("INTENT ANTERIOR: " + ultim_intent +" = "+Arrays.asList(0, 0, 0, 0));



        if(ultim_intent.equals(Arrays.asList(0, 0, 0, 0))){
            intent = true;
            System.out.println("INTENT TRUE");
        }else{
            intent = false;
            System.out.println("INTENT FALSE");
        }

        j1_cm = ctrlDomini.get_jugador1_es_codemaker();
        dificultat = ctrlDomini.get_dificultat_partida();
        j2_user = ctrlDomini.get_tipus_user_Usuari2().toString()=="user_persona";

    }
    /**
     * Funcio que crida al controlador de domini perque retorni una llista de parelles (id_partida, info_patida) de les partides acabades
     * @return Llista de parelles (id_partida, info_partida)
     */
    public List<Pair<Integer, String>> get_llista_partides_acabades() {
        List<Pair<Integer, String>> llista_info = new ArrayList<>();
        List<Integer> ids_partides = ctrlDomini.get_ids_partides_acabades_Usuari1();
        System.out.println(ids_partides);
        for(Integer id: ids_partides) {
            String info_partida = ctrlDomini.get_info_partida(id);
            llista_info.add(new Pair<>(id, info_partida));
        }
        return llista_info;
    }

    /**
     * Funcio que crida al controlador de domini per rebre el numero de partides de l'usuari
     * @return numero de partides de l'usuari
     */
    public Integer get_total_games() {
        return ctrlDomini.get_partides_totals();
    }
    
    /**
     * Funcio que crida al controlador de domini per rebre el numero de partides guanyades de l'usuari
     * @return numero de partides guanyades de l'usuari
     */
    public Integer get_total_wins() {
        return ctrlDomini.get_partides_guanyades();
    }

    /**
     * Funcio que crida al controlador de domini per rebre el winrate de l'usuari
     * @return winrate de l'usuari
     */
    public Double get_winrate() {
        double victories = (double)ctrlDomini.get_partides_guanyades();
        double total = (double)ctrlDomini.get_partides_totals();
        return (100.0*victories)/total;
    }

    public int get_streakF() {
        return ctrlDomini.get_streakF();
    }

    public int get_streakN() {
        return ctrlDomini.get_streakN();
    }

    public int get_streakD() {
        return ctrlDomini.get_streakD();
    }



    /**
     * Funcio per indicar que l'usuari vol ser codemaker de la nova partida
     */
    public void jugador_vol_ser_cm() {
        j1_cm = true;
    }

    /**
     * Funcio per indicar que l'usuari vol ser codebreaker de la nova partida
     */
    public void jugador_vol_ser_cb() {
        j1_cm = false;
    }

    /**
     * Funcio per a consultar si el jugador que fa login es el principal o no
     * @return cert si el jugador que fa login es el principal, fals altrament
     */
    public boolean es_usuari1() {
        return !login_user2;
    }

    /**
     * Funcio per a consultar si el jugador que fa login es el secundari o no
     */
    public void acreditar_User2() {
        j2_user = true;
        login_user2= true;
        
    }

    /**
     * Funcio per indicar que l'usuari vol que el seu oponent sigui una maquina
     */
    public void user2_es_maquina() {
        j2_user = false;
    }

    /**
     * Funcio per assignar l'algorisme que volem per a la partida nova
     * @param algorisme algorisme que volem per a la partida nova
     */
    public void set_algorisme_partida(String algorisme) {
        if(algorisme.equals("Five-Guess")) j2_maquina_genetica = false;
        else j2_maquina_genetica = true;
    }

    /**
     * Funcio per consultar si el jugador principal es codemaker
     * @return cert si el jugador principal es codemaker, fals altrament
     */
    public boolean jugador1_es_codemaker() {
        return j1_cm;
    }


    /**
     * Funcio per a mostrar un missatge d'error
     * @param error
     */
    private void mostra_error(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Funcio per a crear la partida nova amb els parametres entrats previament
     */
    public void assigna_dificultat_facil() {
        dificultats dif = dificultats.FACIL;
        this.dificultat= dif;
        inicialitza_partida_nova();
    }

    /**
     * Funcio per a crear la partida nova amb els parametres entrats previament
     */
    public void assigna_dificultat_mitja() {
        dificultats dif = dificultats.NORMAL;
        this.dificultat= dif;
        inicialitza_partida_nova();
    }

    /**
     * Funcio per a crear la partida nova amb els parametres entrats previament
     */
    public void assigna_dificultat_dificil() {
        dificultats dif = dificultats.DIFICIL;
        this.dificultat= dif;
        inicialitza_partida_nova();
    }

    public void inicialitza_partida_nova() {
        try{
            if (j2_user){
                ctrlDomini.inicialitza_partida_nova_pvp(this.dificultat, j1_cm);
            }else{
                ctrlDomini.inicialitza_partida_nova(this.dificultat, j2_maquina_genetica, j1_cm);
            }

        } catch (Exception e) {
            mostra_error(e.getMessage());
        }
    }

    public String getDificultat_ranking() {
        return dificultat_ranking;
    }

    public int get_num_rondes(){return dificultat.get_num_max_rondes();}
    public int get_num_ronda_actual(){return ctrlDomini.get_num_ronda_actual();}

    public int get_num_colors(){return dificultat.get_num_colors();}

    public void setDificultat_ranking(String dificultat_ranking) {
        this.dificultat_ranking = dificultat_ranking;
    }

    public List<String> get_info_ranking() {
        return ctrlDomini.get_info_ranking(dificultat_ranking);
    }

    /**
     * Funcio per obtindre la informació de cada record
     * @return llista de strings amb la informació de cada record
     */
    public List<String> get_info_records() {
        return ctrlDomini.get_info_records();
    }

    public boolean exist_partida(){
        return ctrlDomini.exist_partida();
    }

    public List<Integer> get_solucio(){return ctrlDomini.get_seq_solucio();}

    public void guardar_partida_a_mitges(){
        ctrlDomini.guardar_partida_a_mitges();
        resetAtributes();
    }
    private void resetAtributes(){

        j1_cm = false;
        j2_user = false;
        j2_maquina_genetica = false;
        intent = true;
        login_user2 = false;
        intents_anteriors = null;
        verificacions_anteriors = null;

    }

    public boolean get_j1_cm(){
        return j1_cm;
    }

    public boolean get_j2_user(){
        return j2_user;
    }

    public boolean get_and_set_ajuda() {
        if (ctrlDomini.get_ajuda_partida()) {
            return true;
        } else {
            try {
                ctrlDomini.set_ajuda();
                return false;
            } catch (Exception e) {
                mostra_error(e.getMessage());
                return true;
            }
        }
    }
    public List<List<Integer>> get_intents_anteriors(){
        return intents_anteriors;
    }

    public List<List<Integer>> get_verificacions_anteriors(){
        return verificacions_anteriors;
    }
    public boolean get_es_intent(){return intent;}
}