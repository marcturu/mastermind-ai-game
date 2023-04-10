package tests;

import main.domain.classes.User;
import main.domain.classes.User_persona;
import main.domain.classes.enumerations.Type_user;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

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
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password123", 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        assertEquals("Mateix id", up.get_id(), "1");
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateix password", up.validate_password("password123"), true);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacio", up.get_puntuacio(), 0);
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
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        assertEquals("Mateix id", up.get_id(), "1");
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus", up.get_tipus_user(), Type_user.user_persona);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixa puntuacio", up.get_puntuacio(), 0);
        assertEquals("Mateixes partides guanyades", up.get_partides_guanyades(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_actuals(), 0);
        assertEquals("Mateixa llista partides  acabades", up.get_num_partides_acabades(), 0);
    }

    /**
     *Objecte de la prova: Test de la funció set_password
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetres indicats. Després, creem un password i comprovem que el valor afegit en aquest set_password ha estat afegit correctement.
     */

    @Test
    public void test_set_password() {
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
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
        User_persona up = new User_persona(1, "Marc", Type_user.user_persona, "password_a_validar", 0, 0, 0, 0, llista_partides_no_acabades, llista_partides_acabades);
        assertEquals(up.validate_password("password_a_validar"), true);
    }

}