package test;

import main.domain.classes.Ronda;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Classe de testeig de Ronda.java
 * @author Jordi Baranda (jordi.baranda@estudiantat.upc.edu)
 */


public class TestRonda {
    /**
     * Objecte de la prova: Test de la constructora de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda” i comprovem els valors de la classe.
     */
    @Test
    public void test_constructora_Ronda(){
        Ronda ronda = new Ronda(1,1);
        assertEquals("Mateix id_partida",ronda.get_id_partida(),1);
        assertEquals("Mateix id_ronad",ronda.get_id_partida(),1);
        assertEquals("Sequencia Intentada creada",sequencia_intentada.get_tipus(),type_seq.intentada);
        assertEquals("Sequencia Verificacio creada",sequencia_verificacio.get_tipus(),type_seq.verificacio);


    }

    /**
     * Objecte de la prova: Test de la get_seq_intentada de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, y fem un set de un array de colors,
     * despres creem una sequencia igual apart, i comproven que la sequencia que hauria de retornar es igual a la creada
     */
    @Test
    public void test_get_seq_intentada(){
        Ronda ronda = new Ronda(1,1);
        colors[] array = {2,3,4,5};
        ronda.set_intentada(array,6);
        Sequencia seq = new Sequencia(type_seq.intentada);
        seq.set_array(array,6);
        assertEquals("Retorno una sequencia igual a la que hem creat apart",ronda.get_seq_intentada(),seq);
    }


    /**
     * Objecte de la prova: Test de la get_seq_verificacio de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, y fem un set de un array de colors,
     * despres creem una sequencia igual apart, i comproven que la sequencia que hauria de retornar es igual a la creada     */
    @Test
    public void test_get_seq_verificacio(){
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        colors[] array = {2,3,4,5};
        ronda.set_intentada(array,6);
        //creem la seq_verificacio
        colors[] verificacio = {0,9,10,0};
        colors[] sol = {1,3,1,2};
        ronda.set_verificacio(array,sol);
        //creem la sequencia de verificacio per poder comprovar
        Sequencia seq = new Sequencia(type_seq.verificacio);
        seq.set_array_verificacio(verificacio,sol,array);
        assertEquals("Retorno una sequencia igual a la que hem creat apart",ronda.get_seq_verificacio(),seq);
    }

    /**
     * Objecte de la prova: Test de la get_id_partida de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven que retoni be el id    */
    @Test
    public void test_get_id_partida() {
        Ronda ronda = new Ronda(1, 1);
        assertEquals("Mateic id_partida",ronda.get_id_partida(),1);
    }
    /**
     * Objecte de la prova: Test de la get_num_ronda de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven que retoni be el id    */
    @Test
    public void test_get_id_partida() {
        Ronda ronda = new Ronda(1, 1);
        assertEquals("Mateic num_ronda",ronda.get_num_ronda(),1);
    }

    /**
     * Objecte de la prova: Test de la set_intentada de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven que el set de la
     * sequencia intentada sigui el correcte*/
    @Test
    public void test_set_intentada() {
        Ronda ronda = new Ronda(1, 1);
        colors[] array = {2,3,4,5};
        ronda.set_intentada(array,6);
        assertEquals("Comprovem si el set ha funcionat",ronda.get_seq_intentada().get_array(),array);
    }
    /**
     * Objecte de la prova: Test de la set_verificacio de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven que el set de la
     * sequencia verificacio sigui el correcte*/
    @Test
    public void test_set_intentada() {
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        colors[] array = {2,3,4,5};
        ronda.set_intentada(array,6);
        //creem la seq_verificacio
        colors[] verificacio = {0,9,10,0};
        colors[] sol = {1,3,1,2};
        ronda.set_verificacio(array,sol);
        assertEquals("Comprovem si el set ha funcionat",ronda.get_seq_verificacio().get_array(),verificacio);
    }

    /**
     * Objecte de la prova: Test de la check_sequencia_encertada de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven si la sequencia de
     * verificacio te 4 espigues negres, i per tant ha acabat la partida
     * */
    @Test
    public void test_check_sequencia_encertada() {
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        colors[] array = {2,3,4,5};
        ronda.set_intentada(array,6);
        //creem la seq_verificacio
        colors[] verificacio = {10,10,10,10};
        colors[] sol = {2,3,4,5};
        ronda.set_verificacio(array,sol);
        assertTrue("Comprovem retorna true",ronda.check_sequencia_encertada());
    }

}