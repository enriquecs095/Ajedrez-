

package Proyecto;

public class Movimiento {
    public Proyecto.Color color;
    public Posicion posActual;
    public Posicion posDestino;
    public Movimiento(){
        
    }
    public Movimiento(Color color, Posicion posicion, Posicion posicion0) {
        this.color=color;
        this.posActual=posicion;
        this.posDestino=posicion0;
    }
    
    @Override
    public String toString(){
        return "("+this.color+", "+this.posActual+", "+this.posDestino+")";
    }
}
