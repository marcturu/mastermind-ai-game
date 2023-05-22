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

import main.domain.classes.Ranking;
import main.domain.classes.enumerations.dificultats;

public class ctrl_ranking {

    /**
     * Funció que serveix per a guardar un objecte Ranking a l'ubicació ../EXE/dades/rankings/ amb el .json identificat per la dificultat d'aquell Ranking
     * @param ranking Ranking a guardar
     */
    public void save_ranking(Ranking ranking) {
        Gson gson = new Gson();

        try {
            String archivo = "../EXE/dades/rankings/" + ranking.get_rank_difficultat() + ".json";
            File file = new File(archivo);
            file.getParentFile().mkdirs(); // Crea la estructura de carpetas necesaria
            FileWriter writer = new FileWriter(file);

            // Escribimos el objeto en el archivo
            gson.toJson(ranking, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funció que permet carregar un determinat Ranking de l'ubicació ../EXE/dades/ranking/ amb el .json identificat per la dificultat d'aquell Ranking
     * @param dificultat Dificultat que identifica el ranking
     * @return ranking Ranking identificat per la dificultat
     */
    public Ranking carrega_ranking(String dificultat) {
        Gson gson = new Gson();
        String archivo = "../EXE/dades/rankings/" + dificultat + ".json";

        if (Files.exists(Paths.get(archivo))){

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

        }
        return null;

    }

}