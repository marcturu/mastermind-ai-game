package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.domain.classes.Ronda;
import main.domain.classes.Sequencia;
import main.domain.classes.Sequencia_intentada;
import main.domain.classes.Sequencia_verificacio;
import main.domain.classes.enumerations.colors;

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

    }

    /**
     * Objecte de la prova: Test de la get_seq_intentada de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, y fem un set de un array de colors,
     * despres creem una sequencia igual apart, i comproven que la sequencia que hauria de retornar es igual a la creada
     */
    @Test
    public void test_get_seq_intentada() throws Exception{
        Ronda ronda = new Ronda(1,1);
        Sequencia_intentada seq = new Sequencia_intentada();
        colors[] arr_col = {colors.VERD, colors.BLAU, colors.GROC, colors.MAGENTA};
        seq.set_array(arr_col, 6);
        ronda.set_intentada(seq);
        assertArrayEquals("Retorna la mateixa sequencia",ronda.get_seq_intentada().get_array(),seq.get_array());
    }


    /**
     * Objecte de la prova: Test de la get_seq_verificacio de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, y fem un set de un array de colors,
     * despres creem una sequencia igual apart, i comproven que la sequencia que hauria de retornar es igual a la creada     */
    @Test
    public void test_get_seq_verificacio() throws Exception{
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        Sequencia_intentada seq_int = new Sequencia_intentada();
        colors[] array = {colors.VERD,colors.BLAU,colors.GROC,colors.MAGENTA};
        seq_int.set_array(array, 6);
        ronda.set_intentada(seq_int);
        //creem la seq_verificacio
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        Sequencia_intentada seq_sol = new Sequencia_intentada();
        colors[] verificacio = {colors.NULL,colors.BLANC, colors.NEGRE, colors.NULL};
        colors[] sol = {colors.VERMELL,colors.BLAU,colors.VERMELL,colors.VERD};
        seq_ver.set_array_verificacio(verificacio, sol, seq_int.get_array());
        seq_sol.set_array(sol, 6);
        ronda.set_verificacio(seq_ver);
        //creem la sequencia de verificacio per poder comprovar
        
        assertArrayEquals("Retorna el mateix array",ronda.get_seq_verificacio().get_array(), verificacio);
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
    public void test_get_num_ronda() {
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
    public void test_set_intentada() throws Exception{
        Ronda ronda = new Ronda(1, 1);
        Sequencia_intentada seq_int = new Sequencia_intentada();
        colors[] array = {colors.VERD,colors.BLAU,colors.GROC,colors.MAGENTA};
        seq_int.set_array(array, 6);
        ronda.set_intentada(seq_int);
        assertArrayEquals("Comprovem si el set ha funcionat",ronda.get_seq_intentada().get_array(), array);
    }
    /**
     * Objecte de la prova: Test de la set_verificacio de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven que el set de la
     * sequencia verificacio sigui el correcte*/
    @Test
    public void test_set_verificacio() throws Exception{
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        Sequencia_intentada seq_int = new Sequencia_intentada();
        colors[] array = {colors.VERD,colors.BLAU,colors.GROC,colors.MAGENTA};
        seq_int.set_array(array, 6);
        ronda.set_intentada(seq_int);
        //creem la seq_verificacio
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        Sequencia_intentada seq_sol = new Sequencia_intentada();
        colors[] verificacio = {colors.NULL,colors.BLANC, colors.NEGRE, colors.NULL};
        colors[] sol = {colors.VERMELL,colors.BLAU,colors.VERMELL,colors.VERD};
        seq_ver.set_array_verificacio(verificacio, sol, seq_int.get_array());
        seq_sol.set_array(sol, 6);
        ronda.set_verificacio(seq_ver);
        assertArrayEquals("Comprovem si el set ha funcionat",ronda.get_seq_verificacio().get_array(),verificacio);
    }

    /**
     * Objecte de la prova: Test de la check_sequencia_encertada de Ronda
     * Fitxers de dades necessaris: Dades introduides manualment
     * Valors estudiats: Estrategia de caixa blanca. Coneixem els parametres que té la classe Sequencia.
     * Operativa: Creem un nova Ronda amb el paràmetre “id partida i num_ronda”, i comproven si la sequencia de
     * verificacio te 4 espigues negres, i per tant ha acabat la partida
     * */
    @Test
    public void test_check_sequencia_encertada() throws Exception{
        Ronda ronda = new Ronda(1,1);
        //creem la seq_intentada
        Sequencia_intentada seq_int = new Sequencia_intentada();
        colors[] array = {colors.VERD,colors.BLAU,colors.GROC,colors.MAGENTA};
        seq_int.set_array(array, 6);
        ronda.set_intentada(seq_int);
        //creem la seq_verificacio
        Sequencia_verificacio seq_ver = new Sequencia_verificacio();
        Sequencia_intentada seq_sol = new Sequencia_intentada();
        colors[] verificacio = {colors.NEGRE, colors.NEGRE, colors.NEGRE, colors.NEGRE};
        colors[] sol = {colors.VERD,colors.BLAU,colors.GROC,colors.MAGENTA};
        seq_ver.set_array_verificacio(verificacio, sol, seq_int.get_array());
        seq_sol.set_array(sol, 6);
        ronda.set_verificacio(seq_ver);
        assertTrue("Comprovem retorna true", ronda.check_sequencia_encertada());
    }

}