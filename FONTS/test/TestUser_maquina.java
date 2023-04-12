package test;

import main.domain.classes.User_maquina;
import main.domain.classes.enumerations.Type_user;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Classe de testeig de User_maquina.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser_maquina {

    /**
     *Objecte de la prova: Test de la constructora User_maquina (amb genetic_algorithm)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_maquina() {
        User_maquina um = new User_maquina(1, "Marc", Type_user.user_maquina, false);
        assertEquals("Mateix id", um.get_id(), 1);
        assertEquals("Mateix nom", um.get_nom(), "Marc");
        assertEquals("Mateix tipus", um.get_tipus_user(), Type_user.user_maquina);
        assertFalse("Mateix genetic algorithm", um.is_genetic());
        assertEquals("Mateixes rondes totals", um.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", um.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacio facil", um.get_puntuacioF(), 0, 0.5);
        assertEquals("Mateixa puntuacio normal", um.get_puntuacioN(), 0, 0.5);
        assertEquals("Mateixa puntuacio dificil", um.get_puntuacioD(), 0, 0.5);
        assertEquals("Mateixes partides guanyades", um.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", um.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", um.get_num_partides_acabades(), 0);
    }

    /**
     *Objecte de la prova: Test de la constructora User_maquina (sense genetic_algorithm)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_maquina2() {
        User_maquina um = new User_maquina(1, "Marc", Type_user.user_maquina, false);
        assertEquals("Mateix id", um.get_id(), 1);
        assertEquals("Mateix nom", um.get_nom(), "Marc");
        assertEquals("Mateix tipus", um.get_tipus_user(), Type_user.user_maquina);
        assertEquals("Mateixes rondes totals", um.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", um.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacio facil", um.get_puntuacioF(), 0, 0.5);
        assertEquals("Mateixa puntuacio normal", um.get_puntuacioN(), 0, 0.5);
        assertEquals("Mateixa puntuacio dificil", um.get_puntuacioD(), 0, 0.5);
        assertEquals("Mateixes partides guanyades", um.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", um.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", um.get_num_partides_acabades(), 0);
    }

    /**
     *Objecte de la prova: Test de la funció is_genetic
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User_maquina amb els paràmetres indicats i comprovem que, efectivament, el valor genetic_algorithm és true.
     */

    @Test
    public void test_is_genetic() {
        User_maquina um = new User_maquina(1, "Marc", Type_user.user_maquina, false);
        assertFalse(um.is_genetic());
    }

}