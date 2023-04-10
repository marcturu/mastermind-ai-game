package main.domain.classes.exceptions;

public class MyException extends Exception{
    
    private String errorString;

    public MyException(String excepString) {

        super("RUNTIME EXCEPTION: " + excepString);
        errorString = excepString;
    }

    public String toString() {
        return "RUNTIME EXCEPTION: " + errorString;
    }
}
