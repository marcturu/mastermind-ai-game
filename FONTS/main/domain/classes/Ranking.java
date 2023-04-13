package main.domain.classes;

import java.util.*;
import java.time.*;
import main.domain.classes.types.Pair;

//import main.domain.classes.Partida;

/**
 * Classe Ranking
 * @author ferran solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Ranking {
    protected LinkedList<Pair<Pair<Double, String>, LocalDate>> rank; //Double: ratio, String: username, LocalDate: data de finalització partida


    public Ranking() {
        rank = new LinkedList<Pair<Pair<Double, String>, LocalDate>>();
    }

    public List<Pair<Pair<Double, String>, LocalDate>> get_rank() {
        return rank;
    }

    public void nova_partida_ranking(double punts_usuari, String username_jugador) {
        if(rank.isEmpty()) {
            rank.add(new Pair<>(new Pair<>(punts_usuari, username_jugador), LocalDate.now()));
            return;
        }
        for(int i = 0; i < rank.size(); ++i) {
            if(punts_usuari > rank.get(i).first().first()) {
                rank.add(i, new Pair<>(new Pair<>(punts_usuari, username_jugador), LocalDate.now()));
                return;
            }
        }
        rank.add(new Pair<>(new Pair<>(punts_usuari, username_jugador), LocalDate.now()));

    }
}