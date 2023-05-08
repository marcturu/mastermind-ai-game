package test;

import main.domain.classes.User;
import main.domain.classes.User_persona;
import main.domain.classes.Partida;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;

import java.util.*;
import static org.junit.Assert.assertEquals;

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
     * Operativa: Creem un nou User amb els paràmetre “id”, "nom" i "tipus_user" i comprovem que els valors de id, nom i tipus_user siguin els mateixos, igual que els no introduïts.
     */
    @Test
    public void test_constructora_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", u.get_id(), 1);
        assertEquals("Mateix nom", u.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", u.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixa puntuació facil", 0.0, u.get_puntuacioF(), 1.0);
        assertEquals("Mateixa puntuació normal", 0.0, u.get_puntuacioN(), 1.0);
        assertEquals("Mateixa puntuació dificil", 0.0, u.get_puntuacioD(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_id.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor id és el mateix que l’introduït.
     */
    @Test
    public void test_get_id() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", u.get_id(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del nom és el mateix que l’introduït.
     */
    @Test
    public void test_get_nom() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix nom", u.get_nom(), "Marc");
    }

    /**
     * Objecte de la prova: Test de la funció get_tipus_user.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres corresponents, comprovem que el contingut del valor del user_persona és el mateix que l’introduït.
     */
    @Test
    public void test_get_tipus_user() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix tipus d'usuari", u.get_tipus_user(), Type_user.user_persona);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioF.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioF és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioF() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioF(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioN.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioN és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioN() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioN(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioD.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacioD és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioD() {
        User u = new User(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, u.get_puntuacioD(), 1.0);
    }

    /**
     * Objecte de la prova: Test de la funció set_partida_acabada.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després de fer una petita comprovació de que la partida s'afageix bé a la llista de partides no acabades, acabem la partida amb victòria de l'usuari u1 i comprovem com s'ha eliminat la partida de la llista de partides no acabades i s'ha afegit a la llista d'acabades, al mateix temps que es comprova que l'usuari u1 l'ha guanyat.
     */
    @Test
    public void test_set_partida_acabada() throws Exception{
        User_persona u1 = new User_persona(3, "JUAN", Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User_persona u2 = new User_persona(1, "FERRI", Type_user.user_persona) ;
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_prova = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_prova);
        assertEquals(u1.get_num_partides_actuals(), 1);
        u1.set_partida_acabada(partida_prova, true, dificultats.FACIL.get_dificultat());
        assertEquals(u1.get_num_partides_acabades(), 1);
        assertEquals(u1.get_num_partides_actuals(), 0);
        assertEquals(u1.get_partides_guanyades(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció set_puntuacio.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor de les puntuacioncs és l'esperat després de realitzar els canvis fets al test.
     */
    @Test
    public void test_set_puntuacio() {
        User u = new User(1, "Marc", Type_user.user_persona);
        u.set_puntuacio(50, "facil");
        assertEquals(u.get_puntuacioF(), 50.0, 0.5);
        u.set_puntuacio(50, "normal");
        assertEquals(u.get_puntuacioN(), 50.0, 0.5);
        u.set_puntuacio(50, "dificil");
        assertEquals(u.get_puntuacioD(), 50.0, 0.5);
        //Si és negatiu, és 0:
        User u2 = new User(2, "Ferran", Type_user.user_persona);
        u2.set_puntuacio(-50, "dificil");
        assertEquals(u2.get_puntuacioD(), 0.0, 0.5);
    }


    /* EL TEST D'ABAIX ÉS EL QUE HA QUEDAT DE LA 1A ENTREGA */

    /**
     * Objecte de la prova: Test de la funció actualitza_partida_actual.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem dos nous users amb els paràmetres i una partida de prova pel test. Després la afegim a la llista de partides no acabades i, per tant, noves, per després comprovar que efectivament hi ha 1 partida en la llista de partides no acabades i totals.
     */
    /*
    @Test
    public void test_actualitza_partida_actual() throws Exception{
        User u1 = new User(3, "JUAN",Type_user.user_persona);
        assertEquals(u1.get_partides_guanyades(), 0);
        User u2 = new User(1, "FERRI", Type_user.user_persona);
        //Dificultat dif = new Dificultat(1, "facil", 4, 6, 12);
        Partida partida_nova1 = new Partida(1, u1, u2, dificultats.FACIL, true);
        u1.afegir_partida_nova(partida_nova1);
        u1.actualitza_partida_actual(1, partida_nova1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        assertEquals("partida_nova3 segueix sent la mateixa però actualitzada", u1.get_ids_partides_actives(), list);
    } */

}