package test;

import main.domain.classes.Sequencia;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Classe de testeig de Sequencia.java
 * @author Jordi Baranda (jordi.baranda@estudiantat.upc.edu)
 */


public class TestSequencia {
    /**
     * Objecte de la prova: Test de la constructora de Sequencia
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Sequencia amb el paràmetre “type_seq” i comprovem els valors de la classe.
     */
    @Test
    public void test_constructora_sequencia(){
        //test sequencia tipus solucio
        Sequencia sol = new Sequencia(type_seq.solucio);
        assertEquals("Mateix tipus", sol.getTipus, type_seq.solucio);
        assertEquals("Array de mida 4", sol.get_array().length,4);
        assertNull("Array buit", sol.get_array());

        //test sequencia tipus intentada
        Sequencia intent = new Sequencia(type_seq.intentada);
        assertEquals("Mateix tipus", intent.getTipus, type_seq.intentada);
        assertEquals("Array de mida 4", intent.get_array().length,4);
        assertNull("Array buit", sol.get_array());

        //test sequencia tipus verificacio
        Sequencia verificacio = new Sequencia(type_seq.verificacio);
        assertEquals("Mateix tipus", verificacio.getTipus, type_seq.verificacio);
        assertEquals("Array de mida 4", verificacio.get_array().length,4);
        assertNull("Array buit", sol.get_array());
    }


    /**
     * Objecte de la prova: Test de la funcio get_array de Sequencia
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Sequencia amb el paràmetre “type_seq.intentada”, fem un set de un array i comprovem que el retorni bé.
     */
    @Test
    public void test_get_array(){
        Sequencia seq = new Sequencia(type_seq.intentada);
        colors[] array = {2,3,5,2}
        seq.set_array(array,6);
        assertEquals("Array identic",seq.get_array(),array);

    }

    /**
     * Objecte de la prova: Test de la funcio get_tipus de Sequencia
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Sequencia amb el paràmetre “type_seq.intentada”, i comprovemq que retorni be el tipus
     */
    @Test
    public void test_get_tipus(){
        Sequencia seq = new Sequencia(type_seq.intentada);
        assertEquals("Array identic",seq.get_tipus(),type_seq.intentada);

    }

    /**
     * Objecte de la prova: Test de la funcio set_array_verificacio de Sequencia
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Sequencia amb el paràmetre “type_seq.verificacio”, i comprovemq que retorni be el tipus
     */
    @Test
    public void test_set_array_verificacio(){
        Sequencia seq = new Sequencia(type_seq.intentada);
        colors[] ver = {0,9,9,10};
        colors[] sol = {1,3,4,5};
        colors[] inte = {1,5,3,2};
        seq.set_array_verificacio(ver,sol,inte);
        assertEquals("Set ben fet de verifcacio",seq.get_array(),ver);
    }
}