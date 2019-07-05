
package Proyecto;

public interface IJugador {
    void preguntarNombre();
    void preguntarColorPiezas();
    
    Movimientos pedirMovimiento();
    boolean puedeMover();
    void anotarPiezaPerdida(Pieza pieza);
}
