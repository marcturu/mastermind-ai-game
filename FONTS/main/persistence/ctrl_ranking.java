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

import main.domain.classes.Ranking;
import main.domain.classes.enumerations.dificultats;

public class ctrl_ranking {

    public void save_ranking(Ranking ranking) {
        Gson gson = new Gson();

        try {
            String dir = "../EXE/dades/rankings/";
            File directori = new File(dir);
            if (!directori.exists()) directori.mkdir();

            String archivo = "../EXE/dades/rankings/" + ranking.get_rank_difficultat() + ".json";
            FileWriter writer = new FileWriter(archivo);

            // Escribimos el objeto en el archivo
            gson.toJson(ranking, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Ranking carrega_ranking(String dificultat) {
        Gson gson = new Gson();
        String archivo = "../EXE/dades/rankings/" + dificultat ".json";

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
            Ranking ranking = gson.fromJson(contenido,Ranking.class);
            return ranking;
        }
        else return null;

    }

}

}

