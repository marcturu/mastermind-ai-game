package main.persistence;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;

import main.domain.classes.Record;
import main.domain.classes.RecordDouble;
import main.domain.classes.RecordInteger;
import main.domain.classes.RecordLong;

public class ctrl_record {

    /**
     * Funció que serveix per a guardar un objecte Record a l'ubicació ../EXE/dades/records/ amb el .json identificat pel nom i modalitat (dificultat) d'aquell Record
     * @param record Record a guardar
     */
    public void save_record(Record record) {
        Gson gson = new Gson();

        try {
            String archivo = "../EXE/dades/records/" + record.get_nom_record() + record.get_modalitat_record() + ".json";
            File file = new File(archivo);
            file.getParentFile().mkdirs(); // Crea la estructura de carpetas necesaria
            FileWriter writer = new FileWriter(file);

            // Escribimos el objeto en el archivo
            gson.toJson(record, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funció que permet carregar un determinat Record de l'ubicació ../EXE/dades/records/ amb el .json identificat pel nom i dificultat d'aquell Record
     * @param nom_record Nom que identifica al record (1/2)
     * @param dif Dificultat que identifica al record (2/2)
     * @return record Record identificat per nom_record+dif
     */
    public Record carrega_record(String nom_record, String dif) {
        Gson gson = new Gson();
        String archivo = "../EXE/dades/records/" + nom_record + dif + ".json";

        if (Files.exists(Paths.get(archivo))) {
            String contenido = "";
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido += linea;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!contenido.isEmpty()) {
                if (nom_record.equals("record_punts")) {
                    RecordDouble record = gson.fromJson(contenido, RecordDouble.class);
                    return record;
                } else if (nom_record.equals("record_streak")) {
                    RecordInteger record = gson.fromJson(contenido, RecordInteger.class);             
                    return record;
                } else if (nom_record.equals("record_temps")) {
                    RecordLong record = gson.fromJson(contenido, RecordLong.class);
                    return record;
                }
            }
        }
        if (nom_record.equals("record_punts")) {
            System.out.println("2 punts");
            RecordDouble recordD = new RecordDouble(nom_record, dif);
            save_record(recordD);
            return recordD;

        } else if (nom_record.equals("record_streak")) {
            System.out.println("2 streak");
            RecordInteger recordI = new RecordInteger(nom_record, dif);
            save_record(recordI);
            return recordI;

        } else {
            System.out.println("2 temps");
            RecordLong recordL = new RecordLong(nom_record, dif);
            save_record(recordL);
            return recordL;

        }
    }

}
