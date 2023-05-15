package main.persistence;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;

import main.domain.classes.Record
import main.domain.classes.RecordDouble;
import main.domain.classes.RecordInteger;
import main.domain.classes.RecordLong;

public class ctrl_ranking {

    public void save_record(Record record) {
        Gson gson = new Gson();

        try {
            String dir = "../EXE/dades/records/";
            File directori = new File(dir);
            if (!directori.exists()) directori.mkdir();

            String archivo = "../EXE/dades/records/" + record.get_nom_record() + record.get_modalitat_record() + ".json";
            FileWriter writer = new FileWriter(archivo);

            // Escribimos el objeto en el archivo
            gson.toJson(record, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Record carrega_record(int nom_record, String dif) {
        Gson gson = new Gson();
        String archivo = "../EXE/dades/records/" + nom_record + dif + ".json";

        String contenido = "";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null){
                contenido += linea;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        if (!contenido.isEmpty()){;
            Record record = gson.fromJson(contenido,Record.class);
            return record;
        }
        else return null;

    }

}
