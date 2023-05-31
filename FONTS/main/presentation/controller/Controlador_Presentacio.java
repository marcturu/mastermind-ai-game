package main.presentation.controller;

import main.presentation.views.*;

import java.util.ArrayList;
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
    private dificultats dificultat;
    private boolean login_user2;

    private String dificultat_ranking;

    public static void main(String[] args){
        Controlador_Presentacio ctrlPresentacio = new Controlador_Presentacio();
        ctrlPresentacio.canvia_a_inici();
    }



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
        dificultat = dificultats.NORMAL;;

        login_user2 = false;
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
    public void crida_a_register_domini(String username, String password){
        try{
            if(login_user2) ctrlDomini.inicialitzaUserPersona2(username, password);
            else {
                ctrlDomini.inicialitzaUserPersona(username, password);
            }
        }catch(Exception e) {
            mostra_error(e.getMessage());
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
        }catch(Exception e) {
            mostra_error(e.getMessage());
        }
    }

    /**
     * Funcio que crida al controlador de domini perque retorni una llista de parelles (id_partida, info_patida) de les partides acabades
     * @return Llista de parelles (id_partida, info_partida)
     */
    public List<Pair<Integer, String>> get_llista_partides_acabades() {
        List<Pair<Integer, String>> llista_info = new ArrayList<>();
        List<Integer> ids_partides = ctrlDomini.get_ids_partides_acabades_Usuari1();
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
}