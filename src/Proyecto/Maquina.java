
package Proyecto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Maquina {
    Tablero tab;
    public Maquina(Tablero t)
    {
        this.tab = t;
    }
    
    public Movimiento hacerMovimiento(){
        
        Random rd = new Random();
        Posicion pos = new Posicion(1,1);
        Movimiento movimiento = new Movimiento();
        ArrayList<Movimiento> resultado=new ArrayList<>();
        boolean encontrado = false;
        
       

            while (!encontrado)
                {
         
                    List<Pieza> valuesList = new ArrayList<Pieza>(tab.piezas_negras.values());
                    int randomIndex = new Random().nextInt(valuesList.size());
                    Pieza randomValue = valuesList.get(randomIndex);
                    resultado = tab.getMovimientosPosibles(randomValue);
                    if (randomValue.tipoPieza().equals("Torre"))
                        System.out.println("Movimientos torre: "+resultado);
                    
                    if(resultado !=null)
                    {
                        Iterator<Movimiento> iterador = resultado.iterator();
                        Movimiento m = null;
                        System.out.println("size= " + resultado.size());
                        int aleatorio;
                        if (resultado.size()>0)
                            aleatorio = rd.nextInt(resultado.size())+1;
                        else
                            aleatorio = 0;
                        System.out.println("aleatorio= " + aleatorio);
                        
                        for(int i=0; i <= aleatorio-1; i++){
                            m = iterador.next();
                        }
                        System.out.println("m= " + m);
                            if (tab.esMovimientoPosible(m, tab.piezas_negras.get(randomValue.posicion.toString()))){
                                movimiento =new Movimiento(m.color,m.posActual,m.posDestino);
                                encontrado =true;
//                                break;
                            }

                        }
                    }               
//                }
             return movimiento;
            }

       

}
