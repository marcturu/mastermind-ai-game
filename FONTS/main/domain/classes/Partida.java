package main.domain.classes;

//import main.domain.classes.Sequencia;
import main.domain.classes.enumerations.dificultats;
// import main.domain.classes.exceptions.MyException;
//import main.domain.classes.enumerations.Type_user;
//import main.domain.classes.enumerations.colors;
import main.domain.classes.types.Pair;
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
    private int num_colors;
    private int temps_max;
    private int num_rondes_max;
    private boolean partida_acabada;
    private Instant temps_inici;
    private LinkedList<Ronda> llista_rondes;
    private Sequencia sequencia_solucio;


    /**
     * Constructor de la classe Partida
     * @param id Identificador de la partida
     * @param cm Usuari que actuarà com a codemaker
     * @param cb Usuari que actuarà com a codebreaker
     * @param dif Dificultat de la partida
     * @param jugador1_es_codemaker Booleà que indica si el jugador 1 actuarà com a codemaker o no
     */
    public Partida(int id, User cm, User cb, dificultats dif, boolean jugador1_es_codemaker) {
        this.indentificador = id;

        this.ultima_ronda_jugada = -1;
        this.jugador1_es_codemaker = jugador1_es_codemaker;
        this.ajuda = false;
        this.dificultat = dif;
        this.num_colors = dif.get_num_colors(); // INCORRECTO DEBERIA VENIR DE CONSTANTE
        this.temps_max = dif.get_temps_max();
        this.num_rondes_max = dif.get_num_max_rondes();
        this.partida_acabada = false;
        this.temps_inici = Instant.now();
        this.llista_rondes = new LinkedList<Ronda>();

        if (jugador1_es_codemaker) {
            this.jugador1 = cm;
            this.jugador2 = cb;
        } else {
            this.jugador1 = cb;
            this.jugador2 = cm;
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

    public boolean get_jugador1_es_codemaker() {
        return jugador1_es_codemaker;
    }

    /**
     * Funció que retorna si s'ha excedit el temps màxim de la partida
     * @return Booleà que indica si s'ha excedit el temps màxim o no
     */
    public boolean temps_excedit(){
        int temps_usat = (int) this.get_temps_usat().toSeconds();
        if(temps_usat > temps_max) return true;
        else return false;
    }

    /**
     * Funció que retorna el temps usat a la partida
     * @return Booleà que indica el temps usat a la partida
     */
    public Duration get_temps_usat(){
        Instant ara = Instant.now();
        Duration temps_usat = Duration.between(temps_inici, ara);
        return temps_usat;
    }

    /**
     * Funció que retorna el numero de colors de la partida
     * @return el numero de colors de la partida
     */
    public int get_num_colors() {
        return num_colors;
    }

    /**
     * Funció que retorna el nombre de rondes máximes de la partida
     * @return nombre de rondes máximes de la partida
     */
    public int get_num_rondes_max() {
        return num_rondes_max;
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

    public Sequencia get_solucio() {
        return this.sequencia_solucio;
    }

    /**
     * Funció que fa que el codebreaker guanyi la partida
     */
    public void codebreaker_guanya(){
        this.partida_acabada = true;
        if(jugador1_es_codemaker) {
            this.jugador1.set_partida_acabada(this, false, dificultat.get_dificultat());
            this.jugador2.set_partida_acabada(this, true, dificultat.get_dificultat());
        }
        else {
            this.jugador1.set_partida_acabada(this, true, dificultat.get_dificultat());
            this.jugador2.set_partida_acabada(this, false, dificultat.get_dificultat());
        }
    }

    /**
     * Funció que fa que el codemaker guanyi la partida
     */
    public void codemaker_guanya() {
        this.partida_acabada = true;
        if(jugador1_es_codemaker) {
            this.jugador1.set_partida_acabada(this, true, dificultat.get_dificultat());
            this.jugador2.set_partida_acabada(this, false, dificultat.get_dificultat());
        }
        else {
            this.jugador1.set_partida_acabada(this, false, dificultat.get_dificultat());
            this.jugador2.set_partida_acabada(this, true, dificultat.get_dificultat());
        }
    }

    /**
     * Funció que retorna la puntuacio del usuaris (facil)
     * @return la puntuacio del usuaris
     */
    public Pair<Double,Double> get_puntuacionsF_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioF(), jugador2.get_puntuacioF());
        return Pair;
    }

    /**
     * Funció que retorna la puntuacio del usuaris (normal)
     * @return la puntuacio del usuaris
     */
    public Pair<Double,Double> get_puntuacionsN_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioN(), jugador2.get_puntuacioN());
        return Pair;
    }

    /**
     * Funció que retorna la puntuacio del usuaris (dificil)
     * @return la puntuacio del usuaris
     */
    public Pair<Double,Double> get_puntuacioD_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioD(), jugador2.get_puntuacioD());
        return Pair;
    }

    /**
     * Funció que retorna la puntuacio del usuaris (PvsP)
     * @return la puntuacio del usuaris
     */
    public Pair<Double,Double> get_puntuacioPvsP_users() throws Exception{

        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioPvsP(), jugador2.get_puntuacioPvsP());
        return Pair;

    }

    /**
     * Funció que crea una nova sequencia intentada a la ronda actual
     * @param  sequencia intentada
     */
    public void set_seq_int_a_ronda_actual(Sequencia seq_int) {
        llista_rondes.get(ultima_ronda_jugada).set_intentada(seq_int);
    }

    /**
     * Funció que crea una nova sequencia verificada a la ronda actual
     * @param  sequencia verificada
     */
    public void set_seq_ver_a_ronda_actual(Sequencia seq_ver) {
        llista_rondes.get(ultima_ronda_jugada).set_verificacio(seq_ver);
    }

    /**
     * Funció per a facilitar els tests de la classe Ranking
     * @param ronda
     */
    public void set_ultima_ronda(int ronda) {
        this.ultima_ronda_jugada = ronda;
    }

    /**
     * Funció que seteja la sequencia solucio de la partida
     * @param solucio
     */
    public void set_sequencia_solucio(Sequencia solucio){
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
     * Funció que retorna si la ronda actual ha estat intentada correctament
     * @return booleà que indica si la ronda actual ha estat intentada correctament
     */
    public boolean ronda_te_intentada_correcte() {
        Ronda ultima_ronda = llista_rondes.get(ultima_ronda_jugada);
        return ultima_ronda.check_sequencia_encertada();
    }
}