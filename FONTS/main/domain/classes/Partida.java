package main.domain.classes;

import main.domain.classes.enumerations.dificultats;
import main.domain.classes.enumerations.*;
// import main.domain.classes.exceptions.MyException;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.types.Pair;
//import main.domain.classes.enumerations.colors;
//import main.domain.classes.types.Pair;
import java.util.*;
import java.time.*;

public class Partida {
    private int indentificador;
    private User jugador1;
    private User jugador2;
    private int ultima_ronda_jugada;
    private boolean jugador1_es_codemaker;
    private boolean ajuda;
    private dificultats dificultat;
    private boolean partida_acabada;
    private Instant temps_inici;
    private Instant temps_final_partida;
    private LinkedList<Ronda> llista_rondes;
    private Sequencia_intentada sequencia_solucio;
    private List<List<Integer>> solucio_maquina; //solucio que ha donat una maquina.


    /**
     * Constructor de la classe Partida
     * @param id Identificador de la partida
     * @param user1 Usuari que actuarà com a codemaker
     * @param user2 Usuari que actuarà com a codebreaker
     * @param dif Dificultat de la partida
     * @param jugador1_es_codemaker Booleà que indica si el jugador 1 actuarà com a codemaker o no
     */
    public Partida(int id, User user1, User user2, dificultats dif, boolean jugador1_es_codemaker) {
        this.indentificador = id;

        this.ultima_ronda_jugada = 0;
        this.jugador1_es_codemaker = jugador1_es_codemaker;
        this.ajuda = false;
        this.dificultat = dif;
        this.partida_acabada = false;
        this.temps_inici = Instant.now();
        this.llista_rondes = new LinkedList<Ronda>();
        this.solucio_maquina = new ArrayList<>();

        if (jugador1_es_codemaker) {
            this.jugador1 = user1;
            this.jugador2 = user2;
        } else {
            this.jugador1 = user2;
            this.jugador2 = user1;
        }

    /**
     * Funció que retorna l'identificador de la partida
     * @return L'identificador de la partida
     */
    }
    public int get_id() {
        return indentificador;
    }

    /**
     * Funció que retorna l'usuari que actua com a codemaker
     * @return L'usuari que actua com a codemaker
     */
    public User get_codemaker(){
        if(jugador1_es_codemaker) return jugador1;
        else return jugador2;
    }

    /**
     * Funció que retorna l'usuari que actua com a codebreaker
     * @return L'usuari que actua com a codebreaker
     */
    public User get_codebreaker(){
        if(jugador1_es_codemaker) return jugador2;
        else return jugador1;
    }

    /**
     * Funció que retorna si s'està donant ajuda durant la partida
     * @return Booleà que indica si s'està donant ajuda o no
     */
    public boolean get_ajuda() {
        return this.ajuda;
    }

    /**
     * Funció que activa o desactiva l'ajuda durant la partida
     * @throws MyException Si ja s'ha demanat ajuda abans
     */
    public void set_ajuda() throws Exception{
        if(this.ajuda == false) this.ajuda = !this.ajuda;
        else {
            throw new Exception("Ja has demanat ajuda un cop");
        }
    }

    //ESTO ES INCORRECTO, DEBERIA ESTAR EN LA CREADORA
    /**
     * Funció que estableix si el jugador 1 actua com a codemaker o no
     * @param es_codemaker Booleà que indica si el jugador 1 actua com a codemaker o no
     */
    public void set_jugador1_es_codemaker(boolean es_codemaker) {
        this.jugador1_es_codemaker = es_codemaker;
    }

    /**
     * Funció que retorna la dificultat de la partida
     * @return La dificultat de la partida
     */
    public dificultats get_dificultat(){
        return dificultat;
    }

    /**
     * getter del boolea per saber si el jugador1 es el codemaker
     * @return si el jugador1 es codemaker o no.
     */
    public boolean get_jugador1_es_codemaker() {
        return jugador1_es_codemaker;
    }

    public User get_jugador1() {
        return jugador1;
    }

    public User get_jugador2() {
        return jugador2;
    }

    /**
     * Consultora per a saber si la partida es pvp
     * @return si la partida es pvp
     */
    public boolean es_partida_pvp() {
        return (jugador2.get_tipus_user()==Type_user.user_persona);
    }

    /**
     * Funcio per a consultar el temps de la partida en segons.
     * @return el temps que ha durat la partida
     */
    public Long get_temps_partida() {
        return Duration.between(temps_inici, temps_final_partida).toSeconds();
    }

    /**
     * Funció que retorna si s'ha excedit el temps màxim de la partida
     * @return Booleà que indica si s'ha excedit el temps màxim o no
     */
    public boolean temps_excedit(){
        int temps_usat = (int) this.get_temps_usat().toSeconds();
        if(temps_usat > dificultat.get_temps_max()) {
            set_temps_final_partida(Instant.now());
            return true;
        }
        else return false;
    }

    /**
     * Funció que retorna el temps usat a la partida
     * @return Booleà que indica el temps usat a la partida
     */
    public Duration get_temps_usat(){
        Instant ara = Instant.now();
        if(temps_final_partida != null) return Duration.between(temps_inici, temps_final_partida);
        else return Duration.between(temps_inici, ara);
    }

    /**
     * Funció que retorna el numero de colors de la partida
     * @return el numero de colors de la partida
     */
    public int get_num_colors() {
        return dificultat.get_num_colors();
    }

    /**
     * Funció que retorna el nombre de rondes máximes de la partida
     * @return nombre de rondes máximes de la partida
     */
    public int get_num_rondes_max() {
        return dificultat.get_num_max_rondes();
    }

    /**
     * Funció que retorna la ultima ronda jugada
     * @return la ultima ronda jugada
     */
    public int get_ultima_ronda() {
        return this.ultima_ronda_jugada;
    }

    /**
     * Funció que retorna si la partida ha acabat o no
     * @return Booleà que indica si la partida ha acabat o no
     */
    public boolean get_partida_acabada() {
        return this.partida_acabada;
    }

    /**
     * Funció que retorna la llista de rondes de la partida
     * @return Llista de rondes de la partida
     */
    public List<Ronda> get_llista_rondes(){
        return llista_rondes;
    }

    public Sequencia_intentada get_solucio() {
        return this.sequencia_solucio;
    }

    /**
     * Funció que fa que el codebreaker guanyi la partida
     */
    public void codebreaker_guanya(){
        this.partida_acabada = true;
        set_temps_final_partida(Instant.now());
        this.jugador1.afegeix_partida_acabada(this);
        if(jugador1_es_codemaker) {
            this.jugador2.set_partida_acabada(this, true, dificultat.get_dificultat());
        }
        else {
            this.jugador1.set_partida_acabada(this, true, dificultat.get_dificultat());
        }
    }

    /**
     * Funció que fa que el codemaker guanyi la partida
     */
    public void codemaker_guanya() {
        this.partida_acabada = true;
        set_temps_final_partida(Instant.now());
        this.jugador1.afegeix_partida_acabada(this);
        if(jugador1_es_codemaker) {  
            this.jugador2.set_partida_acabada(this, false, dificultat.get_dificultat());
        }
        else {
            this.jugador1.set_partida_acabada(this, false, dificultat.get_dificultat());
        }
    }
    
    /**
     * Funció que retorna la puntuacio del usuaris (facil)
     * @return la puntuacio del usuaris
     */
    /* 
    public Pair<Double,Double> get_puntuacionsF_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioF(), jugador2.get_puntuacioF());
        return Pair;
    }*/
    
    /**
     * Funció que retorna la puntuacio del usuaris (normal)
     * @return la puntuacio del usuaris
     */
    /*
    public Pair<Double,Double> get_puntuacionsN_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioN(), jugador2.get_puntuacioN());
        return Pair;
    }*/

    /**
     * Funció que retorna la puntuacio del usuaris (dificil)
     * @return la puntuacio del usuaris
     */
    /*
    public Pair<Double,Double> get_puntuacioD_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioD(), jugador2.get_puntuacioD());
        return Pair;
    }*/

    /**
     * Funció que retorna la puntuacio del usuaris (PvsP)
     * @return la puntuacio del usuaris
     */
    /*
    public Pair<Double,Double> get_puntuacioPvsP_users() throws Exception{

        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioPvsP(), jugador2.get_puntuacioPvsP());
        return Pair;

    }*/
    
    /**
     * Funció que crea una nova sequencia intentada a la ronda actual
     * @param  sequencia intentada
     */
    public void set_seq_int_a_ronda_actual(Sequencia_intentada seq_int) {
        llista_rondes.get(ultima_ronda_jugada).set_intentada(seq_int);
    }

    /**
     * Funció que crea una nova sequencia verificada a la ronda actual
     * @param  sequencia verificada
     */
    public void set_seq_ver_a_ronda_actual(Sequencia_verificacio seq_ver) {
        llista_rondes.get(ultima_ronda_jugada).set_verificacio(seq_ver);
    }
    
    /**
     * Consultora de la ultima sequencia de verificacio
     * @return la ultima sequencia de verificacio que s'ha entrat
     */
    public Sequencia_verificacio get_seq_ver_de_ultima_ronda() {
        return llista_rondes.get(ultima_ronda_jugada).get_seq_verificacio();
    }

    /**
     * Consultora de la ultima sequencia intentada
     * @return la ultima sequencia que s'ha intentat
     */
    public Sequencia_intentada get_seq_int_de_ultima_ronda() {
        return llista_rondes.get(ultima_ronda_jugada).get_seq_intentada();
    }
    
    /**
     * Funció per a facilitar els tests de la classe Ranking
     * @param ronda
     */
    public void set_ultima_ronda(int ronda) {
        this.ultima_ronda_jugada = ronda;
    }

    /**
     * setter de la matriu amb les solucions que dona l'algorisme
     * @param sol solucio que ha creat l'algorisme
     */
    public void set_solucio_maquina(List<List<Integer>> sol) {
        this.solucio_maquina = sol;
    }

    /**
     * getter del guess de la ultima ronda jugada
     * @return retorna l'intent de la maquina de la ultima ronda
     */
    public List<Integer> get_next_guess_maquina() {

        List<Integer> ret =  this.solucio_maquina.get(ultima_ronda_jugada);
        ++ultima_ronda_jugada;
        return ret;
    }

    /**
     * Funció que seteja la sequencia solucio de la partida
     * @param solucio llista de intents fins arribar a la solucio que fa la maquina
     */
    public void set_sequencia_solucio(Sequencia_intentada solucio){
        this.sequencia_solucio = solucio;
    }

    /**
     * Funció que crea una nova ronda
     */
    public void crea_nova_ronda(){
        Ronda ronda = new Ronda(this.indentificador, ultima_ronda_jugada+1);
        ++ultima_ronda_jugada;
        this.llista_rondes.add(ronda);
    }

    /**
     * Funcio que genera una sequencia solucio random
     * @param dif
     */

    public void genera_sequencia_solucio_random(dificultats dif) {
        Sequencia_intentada solucio = new Sequencia_intentada();
        colors[] arr_sol = new colors[4];
        for(int i = 0; i < 4; ++i) {
            int random_color_id = (new Random()).nextInt(dif.get_num_colors()-2) + 1;// NULL, BLANC, NEGRE no es poden fer servir
            arr_sol[i] = colors.get_color_by_id(random_color_id);
        }
        try {
            solucio.set_array(arr_sol, dif.get_num_colors());
        }catch(Exception e) {
            genera_sequencia_solucio_random(dif);
        }
        this.sequencia_solucio = solucio;
    }

    /**
     * Funció que retorna si la ronda actual ha estat intentada correctament
     * @return boolea que indica si la ronda actual ha estat intentada correctament
     */
    public boolean ronda_te_intentada_correcte() {
        Ronda ultima_ronda = llista_rondes.get(ultima_ronda_jugada);
        return ultima_ronda.check_sequencia_encertada();
    }

    /**
     * Funcio per a calcular els punts d'una partida
     * @return
     */
    public Double get_puntuacio() {
        double punts_aconseguits = (partida_acabada? 50.0:0.0) + ((dificultat.get_num_max_rondes() - ultima_ronda_jugada)*2.0) - (ajuda ? 10.0:0.0);
        double punts_max = 50.0 + (dificultat.get_num_max_rondes() - ultima_ronda_jugada)*2.0;
        return punts_aconseguits/punts_max;
    }

    public List<Integer> get_verificacio() {
        Pair<Integer,Integer> ver = Sequencia_verificacio.get_verificacio(sequencia_solucio.get_array(),get_seq_int_de_ultima_ronda().get_array());
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        int negres = ver.second();
        int blanques = ver.first();
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
        return seq_ver.toListInteger();
    }

    public void set_temps_final_partida(Instant temps_final_partida){
        this.temps_final_partida = temps_final_partida;
    }

    public void set_temps_inici_partida(Instant temps_inici){
        this.temps_inici = temps_inici;
    }

}