package main.domain.classes.exceptions;

public class Message_Exception extends Exception{
    private String mensaje;

    public Message_Exception(String mensaje){
        super("Error: " + mensaje);
        this.mensaje = mensaje;
    }

    public String tooString(){
        return "Error: " + mensaje;
    }

}