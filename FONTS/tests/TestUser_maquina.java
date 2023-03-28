package test;

import main.domain.classes.Record;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Classe de testeig de User_maquina.java
 * @author Marc Turu (marc.turu@estudiantat.upc.edu)
 */

public class TestUser_maquina {

    /**
     *Objecte de la prova: Test de la constructora User
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe User
     * Operativa: Creem un nou User amb els paràmetre “id”, "nom", "tipus_user" i genetic_algoritm i comprovem que els valors de id, nom i tipus_user siguin els mateixos.
     */

    @Test
    public void test_constructora_user_maquina() {
        User_maquina um = new User_maquina(1, "Marc", "user_maquina", 1);
        assertEquals("Mateix id", um.get_id(), "1");
        assertEquals("Mateix nom", um.get_nom(), "Marc");
        assertEquals("Mateix tipus_user", um.get_tipus_user(), "user_maquina");
        assertEquals("Mateix algorithm", um.is_genetic(), 1);
    }

    /**
     * Objecte de la prova: Test de la funció is_genetic().
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou user amb paràmetres, comprovem que el contingut del valor del genetic_algorithm és el mateix que l’introduït (nom).
     */

    @Test
    public void test_is_genetic() {
        User_maquina um = User_maquina(1, "Marc", "user_maquina", 1);
        assertEquals(u.is_genetic(), 1);
    }

}