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

public class ctrl_list_user {

    public void save_list_users(HashMap<String,Integer> hashUser){
        Gson gson = new Gson();

        try {
            String archivo = "../EXE/dades/list_user.json";
            File file = new File(archivo);
            file.getParentFile().mkdirs(); // Crea la estructura de carpetas necesaria
            FileWriter writer = new FileWriter(file);

            // Escribimos el objeto en el archivo
            gson.toJson(hashUser, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,Integer> carrega_list_user(){
        Gson gson = new Gson();
        String archivo = "../EXE/dades/list_user.json";

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
                Type type = new TypeToken<HashMap<String,Integer>>(){}.getType();
                HashMap<String,Integer> userHashMap = gson.fromJson(contenido,type);
                return userHashMap;
            }

        }
        return new HashMap<String, Integer>();

    }
}
