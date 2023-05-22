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

import main.domain.classes.Partida;

public class ctrl_partida {

    /**
     * Funció que serveix per a guardar un objecte Partida a l'ubicació ../EXE/dades/partides/ amb el .json identificat per l'id d'aquella Partida
     * @param partida Partida a guardar
     */
    public void save_partida(Partida partida){
        Gson gson = new Gson();

        try {
            String archivo = "../EXE/dades/partides/" + Integer.toString(partida.get_id()) + ".json";
            File file = new File(archivo);
            file.getParentFile().mkdirs(); // Crea la estructura de carpetas necesaria
            FileWriter writer = new FileWriter(file);

            // Escribimos el objeto en el archivo
            gson.toJson(partida, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funció que permet carregar una determinada Partida de l'ubicació ../EXE/dades/partides/ amb el .json identificat per l'id d'aquella Partida
     * @param id Id que identifica la partida
     * @return partida Partida identificada per l'id
     */
    public Partida carrega_partida(int id){
        Gson gson = new Gson();
        String archivo = "../EXE/dades/partides/" + Integer.toString(id) + ".json";

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

            if (!contenido.isEmpty()){
                Partida partida = gson.fromJson(contenido,Partida.class);
                return partida;
            }
        }
        return null;

    }
}