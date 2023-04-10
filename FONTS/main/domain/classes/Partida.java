package main.domain.classes;

import main.domain.classes.enumerations.dificultats;
import main.domain.classes.exceptions.MyException;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.colors;
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
    private List<Ronda> llista_rondes;
    private colors[] sequencia_solucio;

    public Partida(int id, User cm, User cb, dificultats dif, boolean jugador1_es_codemaker) {
        this.indentificador = id;

        this.ultima_ronda_jugada = 0;
        this.jugador1_es_codemaker = jugador1_es_codemaker;
        this.ajuda = false;
        this.dificultat = dif;
        this.num_colors = dif.get_num_colors(); // INCORRECTO DEBERIA VENIR DE CONSTANTE
        this.temps_max = dif.get_temps_max();
        this.num_rondes_max = dif.get_num_max_rondes();
        this.partida_acabada = false;
        this.temps_inici = Instant.now();
        this.llista_rondes = new ArrayList<Ronda>();

        if (jugador1_es_codemaker) {
            this.jugador1 = cm;
            this.jugador2 = cb;
        } else {
            this.jugador1 = cb;
            this.jugador2 = cm;
        }

    }
    public int get_id() {
        return indentificador;
    }
    public User get_codemaker(){
        if(jugador1_es_codemaker) return jugador1;
        else return jugador2;
    }

    public User get_codebreaker(){
        if(jugador1_es_codemaker) return jugador2;
        else return jugador1;
    }

    public boolean get_ajuda() {
        return this.ajuda;
    }

    public void set_ajuda() throws MyException{
        if(this.ajuda == true) this.ajuda = !this.ajuda;
        else {
            throw new MyException("Ja has demanat ajuda un cop");
        }
    }

    //ESTO ES INCORRECTO, DEBERIA ESTAR EN LA CREADORA
    public void set_jugador1_es_codemaker(boolean es_codemaker) {
        this.jugador1_es_codemaker = es_codemaker;
    }

    public dificultats dificultat(){
        return dificultat;
    }

    public boolean temps_excedit(){
        int temps_usat = (int) this.get_temps_usat().toSeconds();
        if(temps_usat > temps_max) return true;
        else return false;
    }

    public Duration get_temps_usat(){
        Instant ara = Instant.now();
        Duration temps_usat = Duration.between(temps_inici, ara);
        return temps_usat;
    }

    public int get_num_colors() {
        return num_colors;
    }

    public int get_num_rondes_max() {
        return num_rondes_max;
    }

    public int get_ultima_ronda() {
        return this.ultima_ronda_jugada;
    }

    public boolean get_partida_acabada() {
        return this.partida_acabada;
    }

    public List<Ronda> get_llista_rondes(){
        return llista_rondes;
    }

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

    public Pair<Double,Double> get_puntuacionsF_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioF(), jugador2.get_puntuacioF());
        return Pair;
    }

    public Pair<Double,Double> get_puntuacionsN_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioN(), jugador2.get_puntuacioN());
        return Pair;
    }

    public Pair<Double,Double> get_puntuacioD_users() {
        Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioD(), jugador2.get_puntuacioD());
        return Pair;
    }

    public Pair<Double,Double> get_puntuacioPvsP_users() throws Exception{
        if (jugador2.get_tipus_user() == Type_user.user_maquina) throw new Exception ("El jugador2 (maquina) no té ounts PvsP");
        else {
            Pair<Double,Double> Pair = new Pair<>(jugador1.get_puntuacioPvsP(), jugador2.get_puntuacioPvsP());
            return Pair;
        }
    }

    public void set_seq_int_a_ronda_actual(colors[] seq_int) {
        llista_rondes.get(ultima_ronda_jugada).set_intentada(seq_int, num_colors);
    }

    public void set_seq_ver_a_ronda_actual(colors[] seq_ver) {
        llista_rondes.get(ultima_ronda_jugada).set_verificacio(seq_ver, sequencia_solucio);
    }

    /**
     * Funció per a facilitar els tests de la classe Ranking
     * @param ronda
     */
    public void set_ultima_ronda(int ronda) {
        this.ultima_ronda_jugada = ronda;
    }

    public void set_sequencia_solucio(colors[] solucio){
        this.sequencia_solucio = solucio;
    }

    public void crea_nova_ronda(){
        Ronda ronda = new Ronda(this.indentificador, ultima_ronda_jugada+1);
        ++ultima_ronda_jugada;
        this.llista_rondes.add(ronda);
    }

    public boolean ronda_te_intentada_correcte() {
        Ronda ultima_ronda = llista_rondes.get(ultima_ronda_jugada);

        return ultima_ronda.check_sequencia_encertada();
    }
}