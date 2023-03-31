package test;

import main.domain.classes.Ranking;
import javafx.util.Pair;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;
import java.util.*;

/**
 * Classe de testeig de Ranking
 * @author ferran solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class RankingTest {

    private Ranking ranking;

    /**
     * Abans de cada test creen un nou ranking usant @Before
     */
    @Before
    public void setUp() {
        ranking = new Ranking();
    }

    /**
     * Objecte de la prova: Test d'insertar usuaris al ranking quan aquest esta buit.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Coneixem l'estructura interna i el tractament de les dades
     * Operativa: Afegim una puntuació i username al ranking. Com està buit s'hauria
     * d'inserir sense cap problema i a la primera posició.
     */
    @Test
    public void test_nova_partida_amb_ranking_buit() {
        double punts = 5.5;
        String username = "user1";
        ranking.nova_partida(punts, username);
        List<Pair<Pair<Double, String>, LocalDate>> expected = new ArrayList<>();
        expected.add(new Pair<>(punts, username), LocalDate.now());
        assertEquals(expected, ranking.get_rank());
    }

    /**
     * Objecte de la prova: Test d'insertar usuaris al ranking quan aquest no esta buit.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Coneixem l'estructura interna i el tractament de la funció
     * Operativa: Afegim una puntuació i username al ranking, i després afegim un altre username
     * amb puntuació més baixa. El que té puntuació més baixa hauria d'anar a la segona posició,
     * mentre que el que té la més alta hauria d'anar a la 1a posició.
     */
    @Test
    public void test_nova_partida_amb_ranking_no_buit() {
        double punts1 = 5.5;
        double punts2 = 6.0;

        String username1 = "user1";
        String username2 = "user2";

        ranking.nova_partida(punts1, username1);
        ranking.nova_partida(punts2, username2);

        List<Pair<Pair<Double, String>, LocalDate>> expected = new ArrayList<>();

        expected.add(new Pair<>(punts2, username2), LocalDate.now());
        expected.add(new Pair<>(punts1, username1), LocalDate.now());
        assertEquals(expected, ranking.get_rank());
    }
}
