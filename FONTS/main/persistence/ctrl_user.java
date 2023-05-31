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
import main.domain.classes.enumerations.Type_user;
import main.domain.classes.User_maquina;
import main.domain.classes.User_persona;
public class ctrl_user {

    /**
     * Funció que serveix per a guardar un objecte User a l'ubicació ../EXE/dades/users/ amb el .json identificat per l'id d'aquell User
     * @param user Usuari a guardar
     */
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

    /**
     * Funció que permet carregar un determinat User de l'ubicació ../EXE/dades/users/ amb el .json identificat per l'id d'aquell User
     * @param id Id que identifica l'usuari
     * @return user User identificat per l'id
     */
    public User carrega_user(int id){
        Gson gson = new Gson();

        String archivo = "../EXE/dades/users/" + Integer.toString(id) + ".json";

        System.out.println("Carrega usuari " + id);

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

                System.out.println("Carrega usuari user " + id);
                User user = gson.fromJson(contenido,User.class);

                if (user.get_tipus_user().toString()=="user_persona"){
                    user = gson.fromJson(contenido,User_persona.class);
                }
                return user;
            }
        }

        return null;


    }
}
