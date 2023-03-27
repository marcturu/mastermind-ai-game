package main.domain.controllers;

import main.domain.classes.Partida;
import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
import main.domain.classes.Ronda;
import main.domain.classes.Ranking;
import main.domain.classes.Record;

import java.util.*;
import java.time.*;

/**
 * Classe del Controlador de Partida
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class controlador_partida {
    private Partida partida_actual;


    private void tractament_victoria() {
        this.partida_actual.set_partida_acabada();

    }

    private void tractament_partida_acabada() {

    }

    public controlador_partida (){
        this.partida_actual = null;
    }

    public void start_partida_nova(int id, User codemaker, User codebreaker, dificultat dif) throws JaExisteixPartida{
        if(this.partida_actual == null) {
            throw new JaExisteixPartida("Ja tens una partida començada amb id: " + this.partida_actual.get_id());
        }else {
            this.partida_actual = new Partida(id, codemaker, codebreaker, dif);//ha de incrementar el numero de partides de l'usuari
        }
    }

    public User get_codemaker_partida_actual() {
        return this.partida_actual.get_codemaker();
    }

    public User get_codebraker_partida_actual() {
        return this.partida_actual.get_codebraker();
    }

    public int get_id_partida_actual() {
        return this.partida_actual.get_id();
    }

    public boolean get_ajuda_partida() {
        return this.partida_actual.get_ajuda();
    }

    public void set_ajuda() throws exception{
        this.partida_actual.set_ajuda();
    }

    public boolean temps_excedit_partida_actual() {
        return this.partida_actual.temps_excedit();
    }

    public int get_num_colors_partida_actual() {
        return this.partida_actual.get_num_colors();
    }

    public int get_num_rondes_max_partida_actual() {
        return this.partida_actual.get_num_rondes_max;
    }

    public List get_llista_rondes_partida_actual() {
        return this.partida_actual.get_llista_rondes();
    }

    public void jugar_partida() throws SensePartida, MalaSequencia{
        if(this.partida_actual == null) {
            throw new SensePartida("No hi ha cap partida per jugar, tria o comença una nova");
        }
        if (this.partida_actual.jugador1_es_codemaker) {
            User codemaker = jugador1;
            User codebreaker = jugador2;
        }else {
            User codebreaker = jugador1;
            User codemaker = jugador2;
        }

        for(int i = this.partida_actual.get_ultima_ronda_jugada(); i <= this.partida_actual.get_num_rondes_max; ++i) {
            if(i == 0 && this.partida_actual.get_seq_solucio() == null) { //cas que començem la partida, cal que el codemaker fagi la combinació
                Sequencia solucio = new Sequencia();
                //codemaker entra el codi de solució
                this.partida_actual.get_sequencia_solucio().set_sequencia(solucio);
                if(codebreaker.isOfType("user_persona"))codebreaker.incrementar_partides_totals();
            }
            Instant inici_ronda = Instant.now();
            Ronda ronda_actual = this.partida_actual.crea_nova_ronda();

            //codebreaker entra la seva sequencia intentada
            ronda_actual.set_intentada(seq_int);//si no es valida es llença excepció: MalaSequencia
            //si tot va bé el codemaker entra la seva solucio
            ronda_actual.set_verificacio(seq_ver);//si no es valida es llença excepció: MalaSequencia

            if(codebreaker.isOfType("user_persona")) codebreaker.incrementa_rondes_totals();

            if(ronda_actual.encerta_sequencia())  {
                tractament_victoria();
                exit;
            }

            //comprovem que no se'ns hagi acabat el temps
            Instant final_ronda = Instant.now();
            Duration temps_ronda = Duration.between(inici_ronda, final_ronda);
            partida_actual.get_temps_usat.minus(temps_ronda);
            if(temps_excedit_partida_actual()) tractament_partida_acabada();
        }
        tractament_partida_acabada();

    }

}