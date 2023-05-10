package main.persistence;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import main.domain.classes.User;
import main.domain.classes.User_persona;
import main.domain.classes.User_maquina;

public class ctrl_user {

    public void save_list_users(HashMap<String,User> hashUser){
        Gson gson = new Gson();

        try {
            String dir = "../EXE/dades";
            File directori = new File(dir);
            if (!directori.exists()) directori.mkdir();

            String archivo = "../EXE/dades/list_user.json";
            FileWriter writer = new FileWriter(archivo);

            // Escribimos el objeto en el archivo
            gson.toJson(hashUser, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
