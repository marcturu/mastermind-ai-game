package test;
/**
 * Classe de testeig de Partida.java
 * @author Ferran Solanes
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

import main.domain.classes.Partida;
import main.domain.classes.Ronda;
import main.domain.classes.User;
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.enumerations.dificultats;

public class TestPartida {
    private Partida partida;

    @Before
    public void setUpPartida() {
        this.partida = new Partida(1, new User(1, "codemaker", Type_user.user_persona), new User(2, "codebreaker", Type_user.user_persona), dificultats.DIFICIL, true);
    }

    @Test
    public void testGetId() {
        assertEquals(partida.get_id(), 1);
    }

    @Test
    public void testGetCodemaker() {
        assertEquals("Els codemakers son iguals", partida.get_codemaker().get_nom(), "codemaker");
    }

    @Test
    public void testGetCodebreaker() {
        assertEquals("Els codebreakers son iguals", partida.get_codebreaker().get_nom(), "codebreaker");
    }

    @Test
    public void testGetAjuda() throws Exception{
        assertFalse(partida.get_ajuda());
        partida.set_ajuda();
        assertTrue(partida.get_ajuda());
    }

    @Test
    public void testSetAjuda() throws Exception{
        assertFalse(partida.get_ajuda());
        partida.set_ajuda();
        assertTrue(partida.get_ajuda());

        try {
            partida.set_ajuda(); // Se espera una excepción
        } catch (Exception e) {
            assertEquals("Ja has demanat ajuda un cop", e.getMessage());
        }
    }

    @Test
    public void testSetJugador1EsCodemaker() {
        assertTrue(partida.get_jugador1_es_codemaker());
        partida.set_jugador1_es_codemaker(false);
        assertFalse(partida.get_jugador1_es_codemaker());
    }

    @Test
    public void testDificultat() {
        assertEquals(partida.get_dificultat(), dificultats.DIFICIL);
    }

    @Test
    public void testTempsExcedit() throws InterruptedException{
        assertFalse(partida.temps_excedit());
        Thread.sleep(1500);//faria falta esperar 10 minuts perque s'excedis el temps...
        partida.get_temps_usat();//actualitza temps_usat
        assertFalse(partida.temps_excedit());
    }

    @Test
    public void testGetTempsUsat() throws InterruptedException{
        assertNotNull(partida.get_temps_usat());
        Thread.sleep(1000);
        Duration temps_passat = partida.get_temps_usat();
        assertTrue(temps_passat.getSeconds() >= 1 && temps_passat.getSeconds() <= 2);

    }

    @Test
    public void testGetters() {
        assertEquals("Mateixos numero de colors", partida.get_num_colors(), dificultats.DIFICIL.get_num_colors());
        assertEquals("Mateixos numero de rondes maximes", partida.get_num_rondes_max(), dificultats.DIFICIL.get_num_max_rondes());
        assertEquals("Ultima ronda es 0", partida.get_ultima_ronda(), 0);
        assertFalse("La partida no ha acabat", partida.get_partida_acabada());
        assertEquals("Llista rondes es nova", partida.get_llista_rondes(), new ArrayList<Ronda>());
    }

}

