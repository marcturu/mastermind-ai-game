package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.*;

import main.domain.classes.RecordLong;

public class TestRecordLong {
    private RecordLong record_temps;

    @Test
    public void test_constructora_record_integer() {
        record_temps = new RecordLong("record_temps", "facil");
        assertEquals(record_temps.get_nom_record(), "record_temps");
        assertNull(record_temps.get_nom_usuari());
        assertEquals(record_temps.get_modalitat_record(), "facil");
        assertEquals(record_temps.get_valor(), (Long)0L);
    }

    @Test
    public void test_actualitza() {
        record_temps = new RecordLong("record_temps", "normal");
        record_temps.actualitza(3, "ferran");
        
        assertNotNull(record_temps.get_nom_usuari());
        assertEquals(record_temps.get_nom_usuari(), "ferran");
        assertEquals(record_temps.get_valor(), (Long)300L);

        record_temps.actualitza(5, "jordi");

        assertEquals(record_temps.get_nom_usuari(), "jordi");
        assertEquals(record_temps.get_valor(), (Long)250L);

        record_temps.actualitza(2, "juan");

        assertEquals(record_temps.get_nom_usuari(), "jordi");
        assertEquals(record_temps.get_valor(), (Long)500L);

    }
}
