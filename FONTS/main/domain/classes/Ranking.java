package main.domain.classes;

import java.utils.*;
import java.time.*;

import main.domain.classes.Partida;

/**
 * Classe Ranking
 * @author ferran solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class Ranking {
    private List<pair<pair<int,String>, LocalDate>> rank; //int: rondes, String: username, LocalDate: data de finalització partida


    public Ranking() {
        rank = null;
    }

    public List<pair<pair<int, String>, LocalDate>> get_rank() {
        return rank;
    }

    public void nova_partida(Partida partida_acabada, String username_jugador) {
        int ronda_final = partida_acabada.get_ultima_ronda();
        if(rank.is_empty()) {
            rank.add(partida_acabada);
            return;
        }
        for(int i = 0; i < rank.size; ++i) {
            if(ronda_final < rank.get(i).first().first()) {
                rank.add(i, pair<pair<ronda_final, username_jugador>, LocalDate.now()>);
            }
        }
    }
}