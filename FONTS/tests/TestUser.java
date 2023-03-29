package test;

import main.domain.classes.User;
import main.domain.classes.enumerations.Type_user;

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
        User u = new User(1, "Marc", user_persona);
        assertEquals("Mateix id", u.get_id(), "1");
        assertEquals("Mateix nom", u.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", u.get_tipus_user(), user_persona);
    }

    /**
     * Objecte de la prova: Test de la funció get_id.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor id és el mateix que l’introduït (id).
     */

    @Test
    public void test_get_id() {
        User u = new User(1, "Marc", user_persona);
        assertEquals(u.get_id(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció get_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del nom és el mateix que l’introduït (nom).
     */

    @Test
    public void test_get_nom() {
        User u = new User(1, "Marc", user_persona);
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
        User u = new User(1, "Marc", user_persona);
        u.set_nom("Jordi");
        assertEquals(u.get_nom(), "Jordi");
    }

    /**
     * Objecte de la prova: Test de la funció get_nom.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del user_persona és el mateix que l’introduït (user_persona).
     */

    @Test
    public void test_get_tipus_user() {
        User u = new User(1, "Marc", user_persona);
        assertEquals(u.get_tipus_user(), user_persona);
    }


}