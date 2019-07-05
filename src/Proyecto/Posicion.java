package Proyecto;

public class Posicion {
    public int fila;
    public int columna;
    
    public Posicion(int f, int c){
        fila=f;
        columna=c;
    }
    
   
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    @Override
    public String toString(){
        char[] c = {'a','b','c','d','e','f','g','h'};
        char[] f = {'8','7','6','5','4','3','2','1'};
        String s = new StringBuilder().append(c[columna]).append(f[fila]).toString();
        return s;
    }
    
}
