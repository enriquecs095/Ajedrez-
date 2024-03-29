package Proyecto;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
 

public class Tablero implements ITablero
{
    private Pieza pieza;
    private final Posicion posicion = new Posicion(0,0);
    private final Peon[] peon = new Peon[16];
    private final Alfil[] alfil = new Alfil[4];
    private final Torre[] torre = new Torre[4];
    private final Caballo[] caballo = new Caballo[4];
    private final Rey[] rey = new Rey[2];
    private final Reyna[] reyna = new Reyna[2];
    public final HashMap<String, Pieza> estado = new HashMap<>();
    public final HashMap<String, Pieza> piezas_negras = new HashMap<>();
    public final HashMap<String, Pieza> piezas_blancas = new HashMap<>();
 
    public Tablero()
    {
        for(int i=0; i<8; i++){
            peon[i] = new Peon(new Posicion(1,i), Proyecto.Color.negra);
            peon[i+8] = new Peon(new Posicion(6,i), Proyecto.Color.blanca);
        }        
        caballo[0] = new Caballo(new Posicion(0,1), Proyecto.Color.negra);
        caballo[1] = new Caballo(new Posicion(0,6), Proyecto.Color.negra);
        caballo[2] = new Caballo(new Posicion(7,1), Proyecto.Color.blanca);
        caballo[3] = new Caballo(new Posicion(7,6), Proyecto.Color.blanca);
       
        alfil[0] = new Alfil(new Posicion(0,2), Proyecto.Color.negra);
        alfil[1] = new Alfil(new Posicion(0,5), Proyecto.Color.negra);
        alfil[2] = new Alfil(new Posicion(7,2), Proyecto.Color.blanca);
        alfil[3] = new Alfil(new Posicion(7,5), Proyecto.Color.blanca);
       
        torre[0] = new Torre(new Posicion(0,0), Proyecto.Color.negra);
        torre[1] = new Torre(new Posicion(0,7), Proyecto.Color.negra);
        torre[2] = new Torre(new Posicion(7,0), Proyecto.Color.blanca);
        torre[3] = new Torre(new Posicion(7,7), Proyecto.Color.blanca);
       
        rey[0] = new Rey(new Posicion(0,4), Proyecto.Color.negra);
        rey[1] = new Rey(new Posicion(7,4), Proyecto.Color.blanca);
       
        reyna[0] = new Reyna(new Posicion(0,3), Proyecto.Color.negra);
        reyna[1] = new Reyna(new Posicion(7,3), Proyecto.Color.blanca);
    }
   
    @Override
    public boolean esMovimientoPosible(Movimiento mov, Pieza pieza)
    {
        boolean esposible=false;
        Iterator<Movimiento> iterador = this.getMovimientosPosibles(pieza).iterator();
        while (iterador.hasNext()){
            Movimiento m = iterador.next();
            if (m.posDestino.fila==mov.posDestino.fila && m.posDestino.columna==mov.posDestino.columna){
                esposible=true;
            }
        }
        return esposible;
    }
   
    public ArrayList<Movimiento> getMovimientosPosibles(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
       
        if (pieza.tipoPieza().equals("Peon")){
            resultado = getMovimientosPeon(pieza);
        }
       
        if (pieza.tipoPieza().equals("Torre"))
            resultado = getMovimientosTorre(pieza);
       
        if (pieza.tipoPieza().equals("Caballo"))
        {
            resultado = getMovimientosCaballo(pieza);
        }
       
        if (pieza.tipoPieza().equals("Reyna"))
        {
            resultado = getMovimientosReyna(pieza);
        }
       
        if (pieza.tipoPieza().equals("Alfil"))
        {
            resultado = getMovimientosAlfil(pieza);
        }
       
        if (pieza.tipoPieza().equals("Rey"))
        {
            resultado = getMovimientosRey(pieza);  
        }
        return resultado;
    }
    private boolean hayPieza(Posicion nueva)
    {
        return (estado.get(nueva.toString()) != null);
    }
    @Override
    public Pieza ejecutarMovimiento(Movimientos mov)
    {

        return pieza;
    }
   
    @Override
    public void colocarPiezas()
    {
        for(int i=0; i<8; i++){
            estado.put(new Posicion(1,i).toString(), peon[i]);
            piezas_negras.put(new Posicion(1,i).toString(), peon[i]);
            estado.put(new Posicion(6,i).toString(), peon[i+8]);
            piezas_blancas.put(new Posicion(6,i).toString(), peon[i+8]);
        }
        estado.put((new Posicion(0,1)).toString(), caballo[0]);
        piezas_negras.put((new Posicion(0,1)).toString(), caballo[0]);
        estado.put((new Posicion(0,6)).toString(), caballo[1]);
        piezas_negras.put((new Posicion(0,6)).toString(), caballo[1]);
        estado.put((new Posicion(7,1)).toString(), caballo[2]);  
        piezas_blancas.put((new Posicion(7,1)).toString(), caballo[2]);  
        estado.put((new Posicion(7,6)).toString(), caballo[3]);
        piezas_blancas.put((new Posicion(7,6)).toString(), caballo[3]);
       
        // Colocamos los alfiles
        estado.put((new Posicion(0,2)).toString(), alfil[0]);
        piezas_negras.put((new Posicion(0,2)).toString(), alfil[0]);
        estado.put((new Posicion(0,5)).toString(), alfil[1]);
        piezas_negras.put((new Posicion(0,5)).toString(), alfil[1]);
        estado.put((new Posicion(7,2)).toString(), alfil[2]);
        piezas_blancas.put((new Posicion(7,2)).toString(), alfil[2]);
        estado.put((new Posicion(7,5)).toString(), alfil[3]);
        piezas_blancas.put((new Posicion(7,5)).toString(), alfil[3]);
               
        estado.put((new Posicion(0,0)).toString(), torre[0]);
        piezas_negras.put((new Posicion(0,0)).toString(), torre[0]);
        estado.put((new Posicion(0,7)).toString(), torre[1]);
        piezas_negras.put((new Posicion(0,7)).toString(), torre[1]);
        estado.put((new Posicion(7,0)).toString(), torre[2]);  
        piezas_blancas.put((new Posicion(7,0)).toString(), torre[2]);  
        estado.put((new Posicion(7,7)).toString(), torre[3]);
        piezas_blancas.put((new Posicion(7,7)).toString(), torre[3]);
       
        estado.put((new Posicion(0,4)).toString(), rey[0]);
        piezas_negras.put((new Posicion(0,4)).toString(), rey[0]);
        estado.put((new Posicion(7,4)).toString(), rey[1]);
        piezas_blancas.put((new Posicion(7,4)).toString(), rey[1]);
       
        estado.put((new Posicion(0,3)).toString(), reyna[0]);
        piezas_negras.put((new Posicion(0,3)).toString(), reyna[0]);
        estado.put((new Posicion(7,3)).toString(), reyna[1]);
        piezas_blancas.put((new Posicion(7,3)).toString(), reyna[1]);
    }
   

    public Pieza comprobarPosicion(Posicion posicion)
    {
        return estado.get(posicion.toString());
    }
 
    public void actualizarEstado(Posicion anterior, Posicion actual){
       
        if(estado.get(anterior.toString()).color == Proyecto.Color.negra)
        {
            piezas_negras.put(actual.toString(), piezas_negras.get(anterior.toString()));
            piezas_negras.remove(anterior.toString());
        }
        else if(estado.get(anterior.toString()).color == Proyecto.Color.blanca)
        {
            piezas_blancas.put(actual.toString(), piezas_blancas.get(anterior.toString()));
            piezas_blancas.remove(anterior.toString());
        }
        estado.put(actual.toString(), estado.get(anterior.toString()));
        estado.remove(anterior.toString());
    }
   
    public ArrayList<Movimiento> getMovimientosPosiblesBlancas()
    {
        ArrayList<Movimiento> resultado = new ArrayList<>();
       
        Pieza p;
        for (int i=0; i<7 ;i++)
        {
            for (int j=0; j<7; j++)
            {
                if (hayPieza((new Posicion(i,j))))
                {
                    p = this.estado.get(new Posicion(i,j).toString());
                    if(p.color == Color.blanca)
                    {
                        resultado.addAll(getMovimientosPosibles(p));
                    }
                }
            }
        }
        return resultado;
    }
   
    public ArrayList<Movimiento> getMovimientosPosiblesNegras()
    {
        ArrayList<Movimiento> resultado = new ArrayList<>();
       
        Pieza p;
        for (int i=0; i<7 ;i++)
        {
            for (int j=0; j<7; j++)
            {
                if (hayPieza((new Posicion(i,j))))
                {
                    p = this.estado.get(new Posicion(i,j).toString());
                    if(p.color == Color.blanca)
                    {
                        resultado.addAll(getMovimientosPosibles(p));
                    }
                }
            }
        }
        return resultado;
    }
   
    public boolean comprobarJaque(Pieza pieza)
    {
        boolean jaque = false;
        ArrayList<Movimiento> mov=new ArrayList<>();
       
        if (pieza.tipoPieza().equals("Torre"))
        {
            mov = getMovimientosTorre(pieza);
           
            for (int i=0; i<mov.size(); i++)
            {
                if (hayPieza(mov.get(i).posDestino))
                {
                    if ( (pieza.color != this.estado.get(mov.get(i).posDestino.toString()).color) && ( this.estado.get(mov.get(i).posDestino.toString()).tipoPieza().toString().equals("Rey") ) )
                    {
                        jaque = true;
                        comprobarJaqueMate(this.estado.get(mov.get(i).posDestino.toString()),pieza);
                    }
                }
            }
        }
       
        if (pieza.tipoPieza().equals("Alfil"))
        {
            mov = getMovimientosAlfil(pieza);
           
            for (int i=0; i<mov.size(); i++)
            {
                if (hayPieza(mov.get(i).posDestino))
                {
                    if ( (pieza.color != this.estado.get(mov.get(i).posDestino.toString()).color) && ( this.estado.get(mov.get(i).posDestino.toString()).tipoPieza().toString().equals("Rey") ) )
                    {
                        jaque = true;
                        comprobarJaqueMate(this.estado.get(mov.get(i).posDestino.toString()),pieza);
                    }
                }
            }
        }
        if (pieza.tipoPieza().equals("Peon"))
        {
            mov = getMovimientosPeon(pieza);
           
            for (int i=0; i<mov.size(); i++)
            {
                if (hayPieza(mov.get(i).posDestino))
                {
                    if ( (pieza.color != this.estado.get(mov.get(i).posDestino.toString()).color) && ( this.estado.get(mov.get(i).posDestino.toString()).tipoPieza().toString().equals("Rey") ) )
                    {
                        jaque = true;
                        comprobarJaqueMate(this.estado.get(mov.get(i).posDestino.toString()),pieza);
                    }
                }
            }
        }    
       
        if (pieza.tipoPieza().equals("Reyna"))
        {
            mov = getMovimientosReyna(pieza);
           
            for (int i=0; i<mov.size(); i++)
            {
                if (hayPieza(mov.get(i).posDestino))
                {
                    if ( (pieza.color != this.estado.get(mov.get(i).posDestino.toString()).color) && ( this.estado.get(mov.get(i).posDestino.toString()).tipoPieza().toString().equals("Rey") ) )
                    {
                        jaque = true;
                        comprobarJaqueMate(this.estado.get(mov.get(i).posDestino.toString()),pieza);
                    }
                }
            }
        }
       
        if (pieza.tipoPieza().equals("Caballo"))
        {
            mov = getMovimientosCaballo(pieza);
           
            for (int i=0; i<mov.size(); i++)
            {
                if (hayPieza(mov.get(i).posDestino))
                {
                    if ( (pieza.color != this.estado.get(mov.get(i).posDestino.toString()).color) && ( this.estado.get(mov.get(i).posDestino.toString()).tipoPieza().toString().equals("Rey") ) )
                    {
                        jaque = true;
                        comprobarJaqueMate(this.estado.get(mov.get(i).posDestino.toString()),pieza);
                    }
                }
            }
        }
       
        return jaque;
    }
   
    public boolean comprobarJaqueMate(Pieza pieza_en_jaque, Pieza pieza_amenaza)
    {
        ArrayList<Movimiento> resultado = new ArrayList<>();
        ArrayList<Movimiento> movRey = new ArrayList<>();
        ArrayList<Movimiento> comprobacion = new ArrayList<>();
        
        if (pieza_en_jaque.color == Color.negra)
        {
            resultado = getMovimientosPosiblesBlancas();
            comprobacion = getMovimientosPosiblesNegras();
        }
        else
        {
            resultado = getMovimientosPosiblesNegras();
            comprobacion = getMovimientosPosiblesBlancas();
        }
        int fila_aux = pieza_en_jaque.posicion.getFila();
        int col_aux = pieza_en_jaque.posicion.getColumna();

       
       
        movRey = getMovimientosPosibles(pieza_en_jaque);
        
        int i = 0;
        int j = 0;
        boolean jaqueMate = false;
        boolean jaqueMateInt;
       
        while(i<movRey.size())
        {
            j = 0;
            jaqueMateInt = false;
            while(j<resultado.size() && !jaqueMateInt)
            {
                jaqueMateInt = false;
                if (movRey.get(i).posDestino.toString().equals(resultado.get(j).posDestino.toString()))
                {
                    jaqueMateInt = true;
                }
                j++;
            }
            if (jaqueMateInt == true)
            {
                jaqueMate = true;
            }
            else
            {
                jaqueMate = false;
                break;
            }
            i++;
        }
        if (jaqueMate)
        {
            i=0;
            while(i<comprobacion.size() && jaqueMate)
            {
                if (pieza_amenaza.posicion.toString().equals(comprobacion.get(i).posDestino.toString()))
                {
                    jaqueMate = false;
                    //estado.get(anterior.toString()).color == ajedrez.Color.negra)
                    if (estado.get(comprobacion.get(i).posActual.toString()).tipoPieza().toString().equals("Rey"))
                    {
                    }
                }
                i++;
            }
            if (pieza_amenaza.tipoPieza().toString().equals("Torre"))
            {
                if (pieza_amenaza.posicion.getColumna() == pieza_en_jaque.posicion.getColumna())
                {
                    if (pieza_amenaza.posicion.getFila() > pieza_en_jaque.posicion.getFila())
                    {
                        int f_aux = pieza_amenaza.posicion.getFila();
                        while (f_aux > pieza_en_jaque.posicion.getFila())
                        {
                            f_aux--;
                            
                        }
                        
                    }
                }
            }
            if (pieza_amenaza.tipoPieza().toString().equals("Alfil"))
            {
                if (pieza_amenaza.posicion.getColumna() == pieza_en_jaque.posicion.getColumna())
                {
                     
                }
            }
            if (pieza_amenaza.tipoPieza().toString().equals("Reyna"))
            {
                if (pieza_amenaza.posicion.getColumna() == pieza_en_jaque.posicion.getColumna())
                {
                    
                }
            }
            
        }
        if (jaqueMate)
            Mensaje("Jaque mate");
        return jaqueMate;
    }
   
    public ArrayList<Movimiento> getMovimientosPeon(Pieza pieza)
    {
         ArrayList<Movimiento> resultado=new ArrayList<>();
         resultado.clear();
         if (pieza.tipoPieza().equals("Peon")){
            
           
            int f_aux = pieza.posicion.getFila();
            int c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            if((f_aux > 0)&& (pieza.color == Color.blanca))
            {
                if (f_aux == 6){
                    if(!hayPieza(new Posicion(f_aux - 2, c_aux))){
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux - 2, c_aux)));
                    }
                }
                f_aux=f_aux-1;
                if(!hayPieza(new Posicion(f_aux, c_aux))){
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    if(c_aux<7 && c_aux>0){
                        if(hayPieza(new Posicion(f_aux, c_aux+1)) && (pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                        if(hayPieza(new Posicion(f_aux, c_aux-1)) && (pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                    }
                    else if(c_aux==7){                        
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                    }
                    else if(c_aux==0){
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                    }
                }
            }
            else if((f_aux < 7) && (pieza.color == Color.negra))
            {
                if (f_aux == 1){
                    if(!hayPieza(new Posicion(f_aux + 2, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux + 2, c_aux)));
                }
                f_aux = f_aux + 1;
                if(!hayPieza(new Posicion(f_aux, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                if(c_aux<7 && c_aux>0){
                    if(hayPieza(new Posicion(f_aux, c_aux+1)) && (pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                    if(hayPieza(new Posicion(f_aux, c_aux-1)) && (pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                }
                else if(c_aux==7){                        
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                }
                else if(c_aux==0){
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                }
            }
        }
        return resultado;
    }
   
   
    public ArrayList<Movimiento> getMovimientosTorre(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
        if (pieza.tipoPieza().equals("Torre"))
        {
 
            int f_aux = pieza.posicion.getFila();
            int c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            boolean ficha = false;
            while((f_aux > 0) && !ficha){
                //Hacia atras
                f_aux--;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                       
                    }
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
            }
            ficha=false;
           
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((f_aux < 7) && !ficha){
               
                //Hacia delante
                f_aux++;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }                    
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
            }
            ficha=false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((c_aux > 0) && !ficha){
                // Hacia izquiera
                c_aux--;
                if(!hayPieza(new Posicion(f_aux, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                else
                {
                    ficha=true;
                }
            }
            ficha=false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((c_aux < 7) && !ficha){
                // Hacia derecha
                c_aux++;
                if(!hayPieza(new Posicion(f_aux, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                else
                {
                    ficha=true;
                }
            }  
        }
        return resultado;
    }
   
    public ArrayList<Movimiento> getMovimientosCaballo(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
       if (pieza.tipoPieza().equals("Caballo"))
        {
            boolean ficha = false;
            int f_aux = pieza.posicion.getFila();
            int c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            if ((f_aux - 1 >= 0)&&(c_aux + 2 <= 7))
            {
                f_aux = f_aux - 1;
                c_aux = c_aux + 2;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
 
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            // Segunda posiciones posibles
            if ((f_aux + 1 <= 7)&&(c_aux + 2 <= 7))
            {        
                f_aux = f_aux + 1;
                c_aux = c_aux + 2;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            // Tercera posiciones posibles
            if ((f_aux - 1 >= 0)&&(c_aux - 2 >= 0))
            {
                f_aux = f_aux - 1;
                c_aux = c_aux - 2;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
       
            // Cuarta posicion posible
            if ((f_aux + 1 <= 7)&&(c_aux - 2 >= 0))
            {        
                f_aux = f_aux + 1;
                c_aux = c_aux - 2;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
           
            // Quinta posicion posible
            if ((f_aux + 2 <= 7)&&(c_aux + 1 <= 7))
            {        
                f_aux = f_aux + 2;
                c_aux = c_aux + 1;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            // Sexta posiciones posibles
            if ((f_aux + 2 <= 7)&&(c_aux - 1 >= 0))
            {        
                f_aux = f_aux + 2;
                c_aux = c_aux - 1;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
       
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            // Septima posiciones posibles
            if ((f_aux - 2 >= 0)&&(c_aux + 1 <= 7))
            {        
                f_aux = f_aux - 2;
                c_aux = c_aux + 1;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
       
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna(); //Obtenemos la posición dentro del array
            // Octava posiciones posibles
            if ((f_aux - 2 >= 0)&&(c_aux - 1 >= 0))
            {        
                f_aux = f_aux - 2;
                c_aux = c_aux - 1;
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    }
            }
        }
       return resultado;
    }
   
    public ArrayList<Movimiento> getMovimientosReyna(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
        if (pieza.tipoPieza().equals("Reyna"))
        {
            boolean ficha = false;
            int f_aux = pieza.posicion.getFila();
            int c_aux = pieza.posicion.getColumna();      
                while ((f_aux >= 0) && (c_aux <= 7) && !ficha) {
                    f_aux--;
                    c_aux++;
                    if (( !(f_aux < 0) )  && ( !(c_aux >7) ))
                    {
                        if(!hayPieza(new Posicion(f_aux, c_aux)))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        else if(hayPieza(new Posicion(f_aux, c_aux)))
                        {
                            if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                                resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                            ficha=true;
                        }
                        else
                        {
                            ficha=true;
                        }   
                    }   
                }
            //Esquina inferior derecha
            //Partimos del punto inicial para volver a mirar
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while ((f_aux <= 7) && (c_aux <= 7) && !ficha) {
                f_aux++;
                c_aux++;
                if (( !(f_aux > 7) )  && ( !(c_aux > 7) ))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((f_aux <= 7) && (c_aux >= 0) && !ficha){
                //Esquina superior izquierda
                f_aux++;
                c_aux--;
                if (( !(f_aux > 7) )  && ( !(c_aux < 0) ))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((f_aux >= 0) && (c_aux >= 0) && !ficha){
                //Esquina inferior izquierda
                f_aux--;
                c_aux--;
                if (( !(f_aux < 0) )  && ( !(c_aux < 0) ))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while(f_aux >= 0 && !ficha){
                //Hacia atras
                f_aux--;
                if (!(f_aux<0))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
        ficha = false;
        f_aux = pieza.posicion.getFila();
        c_aux = pieza.posicion.getColumna();
        while(f_aux <= 7 && !ficha){
            //Hacia delante
            f_aux++;
                if (!(f_aux>7))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
        }
        ficha = false;
        f_aux = pieza.posicion.getFila();
        c_aux = pieza.posicion.getColumna();
        while(c_aux >= 0 && !ficha){
            // Hacia izquiera
            c_aux--;
                if (!(c_aux<0))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
        }
        ficha = false;
        f_aux = pieza.posicion.getFila();
        c_aux = pieza.posicion.getColumna();
        while(c_aux <= 7 && !ficha){
            // Hacia derecha
            c_aux++;
                if (!(c_aux>7))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
        }
        }
        return resultado;
    }
   
    public ArrayList<Movimiento> getMovimientosRey(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
       
        if (pieza.tipoPieza().equals("Rey"))
        {
            int f_aux = pieza.posicion.getFila();
            int c_aux = pieza.posicion.getColumna();
            if((f_aux > 0) && (f_aux < 7) && (c_aux > 0) && (c_aux < 7)){
            //posicion media
                if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
                if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                       
                    }
                 if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                       
                    }
                if(!hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux-1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                       
                    }
                if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
                if(!hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux-1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                       
                    }
                if(!hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux+1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                       
                    }
               
                if(!hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux+1).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                       
                    }
               
        }
        else if((c_aux -1 < 0) && (f_aux-1 <0)){
         //esquina superior izquierda
            if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                       
                    }
           
        }
        else if((c_aux - 1 < 0) && (f_aux + 1 > 7)){
         //esquina inferior izquierda
            if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                       
                    }
           
        }
        else if((c_aux +1 > 7) && (f_aux +1 > 7))
        {
            //esquina inferior derecha
            if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux -1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux -1, c_aux)));
                       
                    }
           
           
        }
        else if((c_aux +1 > 7) && (f_aux -1 < 0)){
            //esquina superior derecha
            if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                       
                    }
           
           
        }
        else if(c_aux - 1 < 0 ){
            //Lateral izquierdo
            if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                       
                    }
           
           
        }
        else if(c_aux + 1 > 7){
            //Lateral derecho
            if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                       
                    }
           
 
           
        }
        else if(f_aux + 1 > 7){
            //Inferior
            if(!hayPieza(new Posicion(f_aux-1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux-1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux-1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux-1, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
           
        }
        else if(f_aux - 1 < 0){
            //Superior
            if(!hayPieza(new Posicion(f_aux+1, c_aux)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux+1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux-1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux-1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux-1)));
                       
                    }
            if(!hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                else if(hayPieza(new Posicion(f_aux+1, c_aux+1)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux+1, c_aux+1).toString()).color))
                           resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux+1, c_aux+1)));
                       
                    }
           
           
        }        
        }
        return resultado;
    }
    public ArrayList<Movimiento> getMovimientosAlfil(Pieza pieza)
    {
        ArrayList<Movimiento> resultado=new ArrayList<>();
        resultado.clear();
            if (pieza.tipoPieza().equals("Alfil"))
        {
            boolean ficha = false;
                int f_aux = pieza.posicion.getFila();
                int c_aux = pieza.posicion.getColumna();      
                while ((f_aux >= 0) && (c_aux <= 7) && !ficha) {
                    
                    f_aux--;
                    c_aux++;
                    if (( !(f_aux < 0) )  && ( !(c_aux > 7) )) 
                        if(!hayPieza(new Posicion(f_aux, c_aux)))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        else if(hayPieza(new Posicion(f_aux, c_aux)))
                        {
                            if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                                resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                            ficha=true;
                        }
                        else
                        {
                            ficha=true;
                        }
                    }
            //Esquina inferior derecha
            //Partimos del punto inicial para volver a mirar
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while ((f_aux <= 7) && (c_aux <= 7) && !ficha) {
                f_aux++;
                c_aux++;
                if (( !(f_aux > 7) )  && ( !(c_aux > 7) )) 
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((f_aux <= 7) && (c_aux >= 0) && !ficha){
                //Esquina superior izquierda
                f_aux++;
                c_aux--;
                if (( !(c_aux < 0) )  && ( !(f_aux > 7) )) 
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
            ficha = false;
            f_aux = pieza.posicion.getFila();
            c_aux = pieza.posicion.getColumna();
            while((f_aux >= 0) && (c_aux >= 0) && !ficha){
                //Esquina inferior izquierda
                f_aux--;
                c_aux--;
                if (( !(f_aux < 0) )  && ( !(c_aux < 0) ))
                {
                    if(!hayPieza(new Posicion(f_aux, c_aux)))
                        resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                    else if(hayPieza(new Posicion(f_aux, c_aux)))
                    {
                        if((pieza.color != this.estado.get(new Posicion(f_aux, c_aux).toString()).color))
                            resultado.add(new Movimiento(pieza.color, pieza.posicion, new Posicion(f_aux, c_aux)));
                        ficha=true;
                    }
                    else
                    {
                        ficha=true;
                    }
                }
            }
        }
            return resultado;
    }
 
    
    void Mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
}
