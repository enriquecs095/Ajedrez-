package Proyecto;

public class RepeatedUserException extends Exception {

    public RepeatedUserException(String usuario) {
        super("El usuario " + usuario + " ya esta registrado");
    }

}
