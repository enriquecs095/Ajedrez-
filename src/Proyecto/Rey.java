
package Proyecto;


import java.util.ArrayList;
import java.util.Iterator;


public class Rey extends Pieza{
     Movimientos mov = new Movimientos();
    int fila_actual;
    int columna_actual;
    char[] filas = {'0', '1', '2', '3', '4', '5', '6', '7'};
    char[] columnas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    Posicion[] result = new Posicion[64];
    ArrayList<Posicion> resultado = new ArrayList<>();
    Color col;
    
    public Rey(Posicion pos, Color c){
        super(pos, c);
        this.fila_actual = pos.getFila();
        this.columna_actual = pos.getColumna();
        this.color = c;
    }
    public void MostrarTodas()
    {
        this.getMovimientosPosibles();
        System.out.println("MOVIMIENTOS POSIBLES");
        for (Posicion palabra : resultado) {
            System.out.print(palabra+" ");
        }
        System.out.println();
        resultado.clear();
    }
    @Override
    public Movimientos getMovimientosPosibles() {

        int f_aux = posicion.getFila();
        int c_aux = posicion.getColumna(); //Obtenemos la posición dentro del array
        
        if((f_aux > 0) && (f_aux < filas.length-1) && (c_aux > 0) && (c_aux < columnas.length-1)){
            
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux+1, c_aux));
            resultado.add(new Posicion(f_aux-1, c_aux));
            resultado.add(new Posicion(f_aux-1, c_aux-1));
            resultado.add(new Posicion(f_aux, c_aux-1));
            resultado.add(new Posicion(f_aux+1, c_aux-1));
            resultado.add(new Posicion(f_aux+1, c_aux+1));
            resultado.add(new Posicion(f_aux-1, c_aux+1));
        }
        else if((c_aux -1 < 0) && (f_aux-1 <0)){
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux+1, c_aux+1));
            resultado.add(new Posicion(f_aux+1, c_aux));
        }
        else if((c_aux - 1 < 0) && (f_aux + 1 > filas.length)){
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux-1, c_aux+1));
            resultado.add(new Posicion(f_aux-1, c_aux));
        }
        else if((c_aux +1 > columnas.length) && (f_aux +1 > filas.length))
        {
            resultado.add(new Posicion(f_aux, c_aux-1));
            resultado.add(new Posicion(f_aux-1, c_aux-1));
            resultado.add(new Posicion(f_aux -1, c_aux));
        }
        else if((c_aux +1 > columnas.length) && (f_aux -1 < 0)){
            resultado.add(new Posicion(f_aux+1, c_aux));
            resultado.add(new Posicion(f_aux, c_aux-1));
            resultado.add(new Posicion(f_aux+1, c_aux-1));
        }
        else if(c_aux - 1 < 0 ){
            //Lateral izquierdo
            resultado.add(new Posicion(f_aux+1, c_aux));
            resultado.add(new Posicion(f_aux-1, c_aux));
            resultado.add(new Posicion(f_aux+1, c_aux+1));
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux-1, c_aux+1));
        }
        else if(c_aux + 1 > columnas.length -1){
            resultado.add(new Posicion(f_aux+1, c_aux));
            resultado.add(new Posicion(f_aux-1, c_aux));
            resultado.add(new Posicion(f_aux+1, c_aux-1));
            resultado.add(new Posicion(f_aux, c_aux-1));
            resultado.add(new Posicion(f_aux-1, c_aux-1));
        }
        else if(f_aux + 1 > filas.length -1){
            resultado.add(new Posicion(f_aux-1, c_aux));
            resultado.add(new Posicion(f_aux-1, c_aux+1));
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux-1, c_aux-1));
            resultado.add(new Posicion(f_aux, c_aux-1));
        }
        else if(f_aux - 1 < 0){
            resultado.add(new Posicion(f_aux+1, c_aux));
            resultado.add(new Posicion(f_aux, c_aux-1));
            resultado.add(new Posicion(f_aux, c_aux+1));
            resultado.add(new Posicion(f_aux+1, c_aux-1));
            resultado.add(new Posicion(f_aux+1, c_aux+1));
        }
            
        return mov;
    }
    

    @Override
    public boolean esMovimientoPosible(Posicion nuevoDestino) {
      
        System.out.println("resultado0= "+resultado.toString());
        Movimientos movimientos = this.getMovimientosPosibles();
        ArrayList<Posicion> arrayLista = new ArrayList<>();
        boolean esposible = false;
        System.out.println("nuevoDestino.fila= "+nuevoDestino.fila);
        System.out.println("nuevoDestino.columna= "+nuevoDestino.columna);
        System.out.println("resultado= "+resultado.toString());
        System.out.println("nuevoDestino= "+nuevoDestino);

        Iterator<Posicion> iterador = resultado.iterator();
        while (iterador.hasNext()){
            Posicion pal = iterador.next();
            if (pal.columna==nuevoDestino.columna && pal.fila==nuevoDestino.fila){
                esposible=true;
            }
        }
        resultado.clear();
        return esposible;

    }

    @Override
    public String tipoPieza(){
        return "Rey";
    }
    @Override
    public String toString(){
        return "Rey "+color.name();
    }
}
