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
 * Classe de testeig de Record.java
 * @author Ferran Solanes (ferran.solanes@estudiantat.upc.edu)
 */

public class TestRecord {
    /**
     * Objecte de la prova: Test de la constructora de Record
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Record.
     * Operativa: Creem un nou Record amb el paràmetre “min_rondes” i comprovem que el valor de nom_record sigui el mateix.
     */
    @Test
    public void test_constructora_record(){
        Record r = new Record("min_rondes");
        assertEquals("Mateix nom record", r.get_nom_record(), "min_rondes");
        assertNull("El record no té un usuari que l'ha batut", r.get_nom_usuari());
        assertNull("El record no té punts", r.get_punts());
    }



    /**
     * Objecte de la prova: Test de la funció check_if_record.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou record, i proposem candidats amb diferents puntuacions.
     */
    @Test
    public void test_check_if_record(){
        Record record = new Record("Test Record");
        assertTrue(record.check_if_record(100, "Ferran"));
        assertEquals("Ferran", record.get_nom_usuari());
        assertEquals(100, record.get_punts());
        assertTrue(record.check_if_record(200, "Jordi"));
        assertEquals("Jordi", record.get_nom_usuari());
        assertEquals(200, record.get_punts());
        assertTrue(record.check_if_record(150, "Marc"));
        assertEquals("Jordi", record.get_nom_usuari());
        assertEquals(200, record.get_punts());
    }
}