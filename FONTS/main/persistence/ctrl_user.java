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

import main.domain.classes.User;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
public class ctrl_user {

    public void save_users(User user){
        Gson gson = new Gson();
        try {
            String archivo = "../EXE/dades/users/" + Integer.toString(user.get_id()) + ".json";
            File file = new File(archivo);
            file.getParentFile().mkdirs(); // Crea la estructura de carpetas necesaria
            FileWriter writer = new FileWriter(file);

            // Escribimos el objeto en el archivo
            gson.toJson(user, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User carrega_user(int id){
        Gson gson = new Gson();
        String archivo = "../EXE/dades/users/" + Integer.toString(id) + ".json";

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
                User user = gson.fromJson(contenido,User.class);
                return user;
            }
        }
        return null;

    }
}
