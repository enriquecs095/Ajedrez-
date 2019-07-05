package Proyecto;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String nombre) {
        super("El usuario " + nombre + " no fue encontrado");

    }

}
