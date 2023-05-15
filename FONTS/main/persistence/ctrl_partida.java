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

import main.domain.classes.Partida;

public class ctrl_partida {

    public void save_partida(Partida partida){
        Gson gson = new Gson();

        try {
            String dir = "../EXE/dades/partides/";
            File directori = new File(dir);
            if (!directori.exists()) directori.mkdir();

            String archivo = "../EXE/dades/partides/" + Integer.toString(partida.get_id()) + ".json";
            FileWriter writer = new FileWriter(archivo);

            // Escribimos el objeto en el archivo
            gson.toJson(partida, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Partida carrega_partida(int id){
        Gson gson = new Gson();
        String archivo = "../EXE/dades/partides/" + Integer.toString(id) + ".json";

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
        else return null;

    }
}