package tests;

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

package test.domain.classes;

import org.junit.Test;
import static org.junit.Assert.*;
import main.domain.classes.*;
import main.domain.classes.enumerations.dificultats;
import main.domain.classes.exceptions.MyException;

public class TestPartida {

    @Test
    public void testGetId() {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertEquals(partida.get_id(), 1);
    }

    @Test
    public void testGetCodemaker() {
        User codemaker = new User(9, "cm", Type_user.user_persona);
        User codebreaker = new User(10, "cb", Type_user.user_persona);
        Partida partida = new Partida(1, codemaker, codebreaker, dificultats.DIFICIL, true);
        assertEquals(partida.get_codemaker(), codemaker);
    }

    @Test
    public void testGetCodebreaker() {
        User codemaker = new User(9, "cm", Type_user.user_persona);
        User codebreaker = new User(10, "cb", Type_user.user_persona);
        Partida partida = new Partida(1, codemaker, codebreaker, dificultats.DIFICIL, true);
        assertEquals(partida.get_codebreaker(), codebreaker);
    }

    @Test
    public void testGetAjuda() throws MyException {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertFalse(partida.get_ajuda());
        partida.set_ajuda();
        assertTrue(partida.get_ajuda());
    }

    @Test(expected = MyException.class)
    public void testSetAjuda() throws MyException {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertFalse(partida.get_ajuda());
        partida.set_ajuda();
        assertTrue(partida.get_ajuda());
        partida.set_ajuda(); // Se espera una excepción
    }

    @Test
    public void testSetJugador1EsCodemaker() {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertTrue(partida.get_jugador1_es_codemaker());
        partida.set_jugador1_es_codemaker(false);
        assertFalse(partida.get_jugador1_es_codemaker());
    }

    @Test
    public void testDificultat() {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertEquals(partida.dificultat(), dificultats.DIFICIL);
    }

    @Test
    public void testTempsExcedit() {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertFalse(partida.temps_excedit());
    }

    @Test
    public void testGetTempsUsat() {
        Partida partida = new Partida(1, new User(7, "cm", Type_user.user_persona), new User(5, "cb", Type_user.user_persona), dificultats.DIFICIL, true);
        assertNotNull(partida.get_temps_usat());
    }
}
