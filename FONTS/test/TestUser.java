package test;

import main.domain.classes.User;
import main.domain.classes.User_persona;
import main.domain.classes.User_maquina;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;

import java.util.Vector;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Classe de testeig de User.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser {

    /**
     *Objecte de la prova: Test de la constructora User
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetre “id”, "nom" i "tipus_user" i comprovem que els valors de id, nom i tipus_user siguin els mateixos.
     */
    @Test
    public void test_constructora_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", u.get_id(), "1");
        assertEquals("Mateix nom", u.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", u.get_nom(), Type_user.user_persona);
        assertEquals("Mateixes rondes totals", u.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", u.get_partides_totals(), 0);
        assertEquals("Mateixa puntuació facil", 0.0, u.get_puntuacioF(), 1.0);
        assertEquals("Mateixa puntuació normal", 0.0, u.get_puntuacioN(), 1.0);
        assertEquals("Mateixa puntuació dificil", 0.0, u.get_puntuacioD(), 1.0);       
        assertEquals("Mateixes partides guanyades", u.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", u.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", u.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_id.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor id és el mateix que l’introduït (id).
     */
    @Test
    public void test_get_id() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_id(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del nom és el mateix que l’introduït (nom).
     */
    @Test
    public void test_get_nom() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_nom(), "Marc");
    }

    /**
     * Objecte de la prova: Test de la funció set_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, es crida al setter del nom i se li assigna un nou. Es comprovem que efectivament el contingut del valor del nom nou és el mateix que l’introduït (nom).
     */
    @Test
    public void test_set_nom() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.set_nom("Jordi");
        assertEquals(u.get_nom(), "Jordi");
    }

    /**
     * Objecte de la prova: Test de la funció get_tipus_user.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del user_persona és el mateix que l’introduït.
     */
    @Test
    public void test_get_tipus_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_tipus_user(), Type_user.user_persona);
    }

    /**
     * Objecte de la prova: Test de la funció get_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_rondes_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_rondes_totals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_rondes_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_rondes_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_rondes_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.incrementar_rondes_totals();
        assertEquals(u.get_rondes_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_partides_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_partides_totals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció incrementar_partides_totals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor num_partides_totals és l'esperat (1) després d'augmentar-lo en 1a unitat.
     */
    @Test
    public void test_incrementar_partides_totals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.incrementar_partides_totals();
        assertEquals(u.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioF.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacio és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioF() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioF(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioN.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacio és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioN() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioN(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioD.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacio és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioD() {
        User_persona u = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioD(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció set_puntuacio.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuació és l'esperat després de realitzar els canvis fets al test.
     */
    @Test
    public void test_set_puntuacio() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.set_puntuacio(50, 5, 10, "dificil");
        assertEquals(u.get_puntuacioD(), 200.0);
        u.set_puntuacio(50, 5, 10, "facil");
        assertEquals(u.get_puntuacioF(), 200.0);
        u.set_puntuacio(50, 5, 10, "normal");
        assertEquals(u.get_puntuacioN(), 200.0);

        //No pot ser negatiu:
        User u2 = new User(2, "Ferran", Type_user.user_persona);
        u.set_puntuacio(50, 1, 11*5, "dificil");
        assertEquals(u.get_puntuacioD(), 0.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_guanyades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor partides_guanyades és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     *            Després, creem una nova partida que es guanya i mirem com l'atribut partides_guanyades ara és 1. Després fem el mateix però perdent, per veure com l'usuari u1 seguiex tenint només 1 partida guanyada.
     */
    @Test
    public void test_get_partides_guanyades() throws Exception{
        User u1 = new User(3, "JUAN", Type_user.user_persona) ;
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //dificultats dif = new dificultats(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova);
        u1.set_partida_acabada(partida_prova, true, dificultats.FACIL.get_dificultat());
        assertEquals(u1.get_partides_guanyades(), 1);
        Partida partida_prova2 = new Partida(2, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova2);
        u1.set_partida_acabada(partida_prova2, false, dificultats.FACIL.get_dificultat());
        assertEquals(u1.get_partides_guanyades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_acabades.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides acabades correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_num_partides_acabades() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_partides_actuals.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el número de partides actuals (no acabades) correspon amb el que he inicialitzat la classe User (0).
     */
    @Test
    public void test_get_partides_actuals() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(u.get_num_partides_actuals(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_estadistiques.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que les estadístiques d'aquest usuari es corresponen amb les creades manualment pel test (tots els valors a 0, ja que encara no ha jugat aquest nou Usuari).
     */
    @Test
    public void test_get_estadistiques() {
        User u = new User(1, "Marc", Type_user.user_persona);
        //Vector<Double> stats_prova = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        Vector<Double> stats_prova = new Vector<Double>();
        for(int i = 0; i < 6; ++i) {
            stats_prova.add(0.0);
        }
        assertEquals(stats_prova, u.get_estadistiques());
    }

    /**
     * Objecte de la prova: Test de la funció afegir_partida_nova.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades i, per tant, noves, per després comprovar que efectivament hi ha 1 partida en la llista de partides no acabades i totals.
     */
    @Test
    public void test_afegir_partida_nova() throws Exception{
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        assertEquals(u1.get_partides_totals(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció set_partida_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després de fer una petita comprovació de que la partida s'afageix bé a la llista de partides no acabades, acabem la partida amb victòria de l'usuari u1 i comprovem com s'ha eliminat la partida de la llista de partides no acabades i s'ha afegit a la llista d'acabades, al mateix temps que es comprova que l'usuari u1 l'ha guanyat.
     */
    @Test
    public void test_set_partida_acabada() throws Exception{
        User u1 = new User(3, "JUAN", Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona) ;
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        u1.set_partida_acabada(partida_prova, true, dificultats.FACIL.get_dificultat());
        assertEquals(u1.get_num_partides_acabades(), 1);
        assertEquals(u1.get_num_partides_actuals(), 0);
        assertEquals(u1.get_partides_guanyades(), 1);
    }

}