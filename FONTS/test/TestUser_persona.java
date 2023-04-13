package test;


import main.domain.classes.User_persona;
import main.domain.classes.enumerations.Type_user;

import java.util.Vector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Classe de testeig de User_persona.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser_persona {

    /**
     *Objecte de la prova: Test de la constructora User_persona (amb password)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_persona() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password123");
        assertEquals("Mateix id", up.get_id(), 1);
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateix password", up.validate_password("password123"), true);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacioF", up.get_puntuacioF(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioN", up.get_puntuacioN(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioD", up.get_puntuacioD(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioPvsP", up.get_puntuacioPvsP(), 0.0, 0.5);
        assertEquals("Mateixes partides guanyades", up.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_acabades(), 0);
    }

    /**
     *Objecte de la prova: Test de la constructora User_persona (sense password)
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats i comprovem que els valors introduïts i els que haurien de tenir valor 0 o null siguin els mateixos.
     */

    @Test
    public void test_constructora_user_persona2() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals("Mateix id", up.get_id(), 1);
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacioF", up.get_puntuacioF(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioN", up.get_puntuacioN(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioD", up.get_puntuacioD(), 0.0, 0.5);
        assertEquals("Mateixa puntuacioPvsP", up.get_puntuacioPvsP(), 0.0, 0.5);
        assertEquals("Mateixes partides guanyades", up.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides no acabades", up.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_acabades(), 0);
    }

    /**
     * Objecte de la prova: Test de la funció get_puntuacioPvsP.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor puntuacio és el mateix amb el qual l'hem inicialitzat al crear la classe User.
     */
    @Test
    public void test_get_puntuacioPvsP() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        assertEquals(0.0, up.get_puntuacioPvsP(), 1.0);
    }

    /**
     *Objecte de la prova: Test de la funció get_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el password introduït és el que correspon.
     */

    @Test
    public void test_get_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password_a_validar");
        assertEquals(up.get_password(), "password_a_validar");
    }

    /**
     * Objecte de la prova: Test de la funció get_estadistiques.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que les estadístiques d'aquest usuari es corresponen amb les creades manualment pel test (tots els valors a 0, ja que encara no ha jugat aquest nou Usuari).
     */
    @Test
    public void test_get_estadistiques() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        //Vector<Double> stats_prova = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        Vector<Double> stats_prova = new Vector<Double>();
        for(int i = 0; i < 9; ++i) {
            stats_prova.add(0.0);
        }
        assertEquals(stats_prova, up.get_estadistiques());
    }

    /**
     *Objecte de la prova: Test de la funció set_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després, creem un password i comprovem que el valor afegit en aquest set_password ha estat afegit correctement.
     */

    @Test
    public void test_set_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.set_password("password_afegit");
        assertEquals(up.validate_password("password_afegit"), true);
    }

    /**
     *Objecte de la prova: Test de la funció validate_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després verifiquem que el password introduït és el que correspon.
     */

    @Test
    public void test_validate_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password_a_validar");
        assertEquals(up.validate_password("password_a_validar"), true);
    }

    /**
     *Objecte de la prova: Test de la funció set_puntuacio_PvsP
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després, creem un password i comprovem que el valor afegit en aquest set_password ha estat afegit correctement.
     */

    @Test
    public void test_set_puntuacio_PvsP() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona);
        up.set_puntuacio_PvsP(50, 5, 10);
        assertEquals(up.get_puntuacioPvsP(), 200.0, 0.5);

        //No pot ser negatiu :
        User_persona up2 = new User_persona(2, "Ferran", Type_user.user_persona);
        up2.set_puntuacio_PvsP(50, 1, 11*5);
        assertEquals(up2.get_puntuacioPvsP(), 0.0, 0.5);
    }

}