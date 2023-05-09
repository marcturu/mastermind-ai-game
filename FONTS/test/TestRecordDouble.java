package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import main.domain.classes.RecordDouble;

public class TestRecordDouble {
    private RecordDouble record_points;

    @Test
    public void test_constructora_record_double() {
        record_points = new RecordDouble("record_points", "facil");
        assertEquals(record_points.get_nom_record(), "record_points");
        assertNull(record_points.get_nom_usuari());
        assertEquals(record_points.get_modalitat_record(), "facil");
        assertEquals(record_points.get_valor(), (Double)0.0);
    }

    @Test
    public void test_actualitza() {
        record_points = new RecordDouble("record_points", "dificil");
        
        record_points.actualitza(50.0, "ferran");
        
        assertEquals(record_points.get_nom_usuari(), "ferran");
        assertEquals(record_points.get_valor(), (Double)50.0);

        record_points.actualitza(60.0, "jordi");

        assertEquals(record_points.get_nom_usuari(), "jordi");
        assertEquals(record_points.get_valor(), (Double)60.0);

        record_points.actualitza(2.0, "juan");

        assertEquals(record_points.get_nom_usuari(), "jordi");
        assertEquals(record_points.get_valor(), (Double)60.0);



    }
}
