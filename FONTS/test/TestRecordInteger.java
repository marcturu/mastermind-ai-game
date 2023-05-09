package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.*;

import main.domain.classes.RecordInteger;

public class TestRecordInteger {
    private RecordInteger record_streak;

    @Test
    public void test_constructora_record_integer() {
        record_streak = new RecordInteger("record_streak", "facil");
        assertEquals(record_streak.get_nom_record(), "record_streak");
        assertNull(record_streak.get_nom_usuari());
        assertEquals(record_streak.get_modalitat_record(), "facil");
        assertEquals(record_streak.get_valor(), (Integer)0);
    }

    @Test
    public void test_actualitza() {
        record_streak = new RecordInteger("record_streak", "normal");
        record_streak.actualitza(3, "ferran");
        
        assertNotNull(record_streak.get_nom_usuari());
        assertEquals(record_streak.get_nom_usuari(), "ferran");
        assertEquals(record_streak.get_valor(), (Integer)3);

        record_streak.actualitza(5, "jordi");

        assertEquals(record_streak.get_nom_usuari(), "jordi");
        assertEquals(record_streak.get_valor(), (Integer)5);

        record_streak.actualitza(2, "juan");

        assertEquals(record_streak.get_nom_usuari(), "jordi");
        assertEquals(record_streak.get_valor(), (Integer)5);

    }
    
}
