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
     * Valors estudiats:
     * Operativa: Creem un nou Record amb el paràmetre “min_rondes” i comprovem que el valor de nom_record sigui el mateix.
     */
    @Test
    public void test_constructora_record(){
        Record r = new Record("min_rondes");
        assestEquals("Mateix nom record", r.get_nom_record(), "min_rondes");
    }


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
     * Objecte de la prova: Test de la afegir usuari al record.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou record, i proposem un candidat a record. Com està buit s'haurian d'actualitzar els valors.
     */
    @Test
    public void test_batre_record(){
        Record r = new Record("min_rondes");
        assertTrue("Es un nou record", r.check_if_record(50, "ferran.solanes"));
        assertEquals("Mateix nom record", r.get_nom_record(), "min_rondes");
        assertNotNull("No està buit el nom de l'usuari que bat el record", r.get_nom_usuari());
        assertEquals("El record té un usuari que l'ha batut(ferran.solanes)", r.get_nom_usuari(), "ferran.solanes");
        assertNotNull("El record té punts", r.get_punts());
        assertEquals("El nou record de punts es 50", r.get_punts(), 50);
    }
}

    /**
     * Objecte de la prova: Test de la afegir usuari al record, però no l'ha batut.
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou record, i proposem un candidat a record. No batirà el record i per tant no s'haurien d'actualitzar els valors.
     */
    @Test
    public void test_no_batre_record(){
        Record r = new Record("min_rondes");

        r.check_if_record(50, "ferran.solanes");
        assertFalse("No batrà el record", r.check_if_record(30, "juan.clusellas"));
        //comprovem que juan.clusellas no bat el record
        assertNotNull("No està buit el nom de l'usuari que bat el record", r.get_nom_usuari());
        assertNotEquals("El record no passa a ser de juan.clusellas", r.get_nom_usuari(), "juan.clusellas");
        //comprovem que el record segueix sent 50.
        assertNotNull("El record té punts", r.get_punts());
        assertEquals("El record de punts no passa a ser 30", r.get_punts(), 30);
    }
}