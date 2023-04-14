package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import main.domain.classes.Sequencia;
import main.domain.classes.enumerations.colors;
import main.domain.classes.enumerations.type_seq;


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
        assertEquals("Mateix tipus", sol.get_tipus(), type_seq.solucio);
        assertEquals("Array de mida 4", sol.get_array().length,4);
        for (int i = 0; i < 4; ++i){
            assertEquals("Array buit", colors.NULL, sol.get_array()[i]);
        }

        //test sequencia tipus intentada
        Sequencia intent = new Sequencia(type_seq.intentada);
        assertEquals("Mateix tipus", intent.get_tipus(), type_seq.intentada);
        assertEquals("Array de mida 4", intent.get_array().length,4);
        for (int i = 0; i < 4; ++i){
            assertEquals("Array buit", colors.NULL,intent.get_array()[i]);
        }

        //test sequencia tipus verificacio
        Sequencia verificacio = new Sequencia(type_seq.verificacio);
        assertEquals("Mateix tipus", verificacio.get_tipus(), type_seq.verificacio);
        assertEquals("Array de mida 4", verificacio.get_array().length,4);
        for (int i = 0; i < 4; ++i){
            assertEquals("Array buit", colors.NULL, verificacio.get_array()[i]);
        }
    }


    /**
     * Objecte de la prova: Test de la funcio get_array de Sequencia
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Sequencia amb el paràmetre “type_seq.intentada”, fem un set de un array i comprovem que el retorni bé.
     */
    @Test
    public void test_get_array() throws Exception{
        Sequencia seq = new Sequencia(type_seq.intentada);
        colors[] array = {colors.VERMELL,colors.BLAU,colors.MAGENTA,colors.VERMELL};
        seq.set_array(array,6);
        assertArrayEquals("Array identic",seq.get_array(),array);

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
    public void test_set_array_verificacio() throws Exception{
        Sequencia seq = new Sequencia(type_seq.intentada);
        colors[] ver = {colors.NULL,colors.BLANC,colors.BLANC,colors.NEGRE};
        colors[] sol = {colors.VERMELL,colors.BLAU,colors.GROC,colors.MAGENTA};
        colors[] inte = {colors.VERMELL,colors.MAGENTA,colors.BLAU,colors.VERD};
        seq.set_array_verificacio(ver,sol,inte);
        assertArrayEquals("Set ben fet de verifcacio",seq.get_array(),ver);
    }
}