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
 * @author Juan Clusellas
 */

public class UnitTestPartida {

// TODO ACABAR TESTS
    @Test
    public void test_constructora_partida(){
        User cm = new User_persona(3, 'JUAN', 'PWD') ;
        User cb = new User_persona(1, 'FERRI', 'PWD2') ;
        Dificultat dif = new Dificultat(1, 'facil', 4, 6, 12);
        Partida p = new Partida(1, cm, cb);
        dificultat dif, boolean jugador1_es_codemaker)
    }



}