package test;

import main.domain.classes.Record;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

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
        assertEquals("El record no té punts", -1, r.get_punts());
    }

    /**
     *Objecte de la prova: Test de la funció get_nom_record
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia de caixa blanca. Conceixem els parametres que té la classe Record
     * Operativa: Creem un nou Record amb un cert nom. Després verifiquem que aquest nom introduït és el que correspon.
     */

    @Test
    public void test_get_nom_record() {
        Record r = new Record("min_rondes");;
        assertEquals("Mateix nom record", r.get_nom_record(), "min_rondes");
    }

    /**
     *Objecte de la prova: Test de la funció get_nom_usuari
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Creem un nou Record amb un cert nom. Fem que un usuari sigui el que té aquest record amb uns certs punts. Comprovem que, efectivament, aquest record té com a nom d'usuari que l'ha batut el que hem proposat.
     */

    @Test
    public void test_get_nom_usuari() {
        Record r = new Record("RecordX");;
        assertTrue("Es un record",r.check_if_record(10, "Jordi"));
        assertEquals("El nom d'usuari es Jordi","Jordi", r.get_nom_usuari());
    }

    /**
     *Objecte de la prova: Test de la funció get_punts
     * Fitxers de dades necessaris: Dades introduïdes manualment.
     * Valors estudiats: Estrategia caixa gris. Sabem l'estructura de la classe, però no quin sera el comportament de la funció.
     * Operativa: Seguint una estructura similar al test anterior, creem un nou Record amb un cert nom. Fem que un usuari sigui el que té aquest record amb uns certs punts. Comprovem que, efectivament, aquest record (que té l'usuari introduit) té els punts proposats.
     */

    @Test
    public void test_get_punts() {
        Record r = new Record("RecordY");;
        assertTrue("Es un record",r.check_if_record(20, "Juan"));
        assertEquals("Ha fet 20 punts",20, r.get_punts());
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
        assertTrue("Es un record",record.check_if_record(100, "Ferran"));
        assertEquals("El nom d'usuari es Ferran","Ferran", record.get_nom_usuari());
        assertEquals("Ha fet 100 punts",100, record.get_punts());
        assertTrue("Jordi bat el record amb 200 punts",record.check_if_record(200, "Jordi"));
        assertEquals("L'usuari Jordi te el record ara","Jordi", record.get_nom_usuari());
        assertEquals("Els punts del record son 200",200, record.get_punts());
        assertFalse("El marc no bat el record amb 150 punts",record.check_if_record(150, "Marc"));
        assertEquals("El record el segueix tenint el Jordi","Jordi", record.get_nom_usuari());
        assertEquals("El record esta en 200 punts encara",200, record.get_punts());
    }
}