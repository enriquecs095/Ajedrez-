
package Proyecto;


import java.util.ArrayList;
import java.util.Iterator;


public class Peon extends Pieza{

    Movimientos mov = new Movimientos();
    int fila_actual;
    int columna_actual;
    char[] filas = {'0','1','2','3','4','5','6','7','8'};
    char[] columnas = {'a','b','c','d','e','f','g','h'};
    Posicion[] result = new Posicion[64];
    ArrayList<Posicion> resultado = new ArrayList<>();
    Color color;
   
    
    public Peon(Posicion pos, Color col){
        super(pos,col);
        this.fila_actual = pos.getFila();
        this.columna_actual = pos.getColumna();
        this.color = col;     
    }
    
    public void MostrarTodas()
    {
        for (Posicion palabra : resultado) {
            System.out.print(palabra+" ");
        }
        System.out.println();
        resultado.clear();
    }    

    @Override
    public boolean esMovimientoPosible(Posicion nuevoDestino) {
        
        boolean esposible = false;

        Iterator<Posicion> iterador = resultado.iterator();
        while (iterador.hasNext()){
            Posicion pal = iterador.next();
            if (pal.columna==nuevoDestino.columna && pal.fila==nuevoDestino.fila){
                esposible=true;
            }
        }
        resultado.clear();
        //Habría que comprobar aquí que hay fichas por medio o eso se haría en otro sitio?
        return esposible;

    }
    @Override
    public String tipoPieza(){
        return "Peon";
    }
    
    @Override
    public void actualizarPosicion(Posicion nuevaPosicion) {
        posicion.setColumna(nuevaPosicion.columna);
        posicion.setFila(nuevaPosicion.fila);
    }
    
    @Override
    public String toString(){
        return "Peon "+color.name();
    }

    @Override
    public Movimientos getMovimientosPosibles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
