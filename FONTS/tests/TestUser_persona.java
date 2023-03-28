package test;

import main.domain.classes.User_persona;

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
     *Objecte de la prova: Test de la constructora User_persona
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetre “id”, "nom", "tipus_user", "password", "rondes_totals", "partides_totals", "llista_partides_no_acabades" i "llista_partides_acabades" i comprovem que els valors de id, nom i tipus_user siguin els mateixos.
     */

    @Test
    public void test_constructora_user_persona() {
        Userpersona up = new User(1, "Marc", "user_persona", "password123");
        assertEquals("Mateix id", up.get_id(), "1");
        assertEquals("Mateix nom", up.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", up.get_tipus_user(), "user_persona");
        assertEquals("Mateix password", up.validate_password("password123"), 1);
        assertEquals("Mateixes rondes totals", up.get_rondes_totals(), 0);
        assertEquals("Mateixes partides totals", up.get_partides_totals(), 0);
        assertEquals("Mateixa llista partides no acabades", llista_partides_no_acabades.size(), 0);
        assertEquals("Mateixa llista partides  acabades", llista_partides_acabades.size(), 0);

    }
}