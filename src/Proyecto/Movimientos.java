package Proyecto;

import java.util.ArrayList;
import java.util.HashMap;


public class Movimientos 
{
    public final HashMap<Pieza, Movimiento> movimientos = new HashMap<>();
    public ArrayList<String> listado_movimientos = new ArrayList<>();
    
  
    
    public Movimientos()
    {
        
    }
    
    public String getUltimoMovimiento()
    {
        return listado_movimientos.get(listado_movimientos.size()-1);
    }
    
    public void anadirMovimiento(Pieza pieza, Movimiento movimiento)
    {
        listado_movimientos.add(pieza.tipoPieza()+" "+movimiento);
    }
     
}
