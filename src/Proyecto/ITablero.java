
package Proyecto;

public interface ITablero {
    boolean esMovimientoPosible(Movimiento mov, Pieza pieza);
    Pieza ejecutarMovimiento(Movimientos mov);
    void colocarPiezas();
    
}
