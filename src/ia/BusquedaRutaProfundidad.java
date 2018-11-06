package ia;

import java.util.ArrayList;
import java.util.TimerTask;

class BusquedaRutaProfundidad extends TimerTask implements Constantes{
    
    Lienzo lienzo;
    ArrayList<Estado> colaEstados;
    ArrayList<Estado> historial;
    ArrayList<Character> pasos;
    int index_pasos;
    Estado inicial;
    Estado objetivo;
    Estado temp;
    boolean exito;
    
    public BusquedaRutaProfundidad(Lienzo lienzo) {
        
        this.lienzo=lienzo;
        colaEstados=new ArrayList<>();
        historial=new ArrayList<>();
        pasos=new ArrayList<>();
        index_pasos=0;
        //digo cual es el estado inicial y el final
        inicial=new Estado(1,1,'N',null);
        colaEstados.add(inicial);
        historial.add(inicial);
        
        objetivo=new Estado(9,3,'N',null);
        exito=false;
    }
    
    public void buscar() {
        
        if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ) {
            temp=colaEstados.get(0);
            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ) System.out.println("Ruta calculada");
        else System.out.println("La ruta no pudo calcularse");
        
    }
    
    private void moverArriba(Estado e) {
        
        if ( e.y > 0 ) {
            if ( lienzo.laberinto.celdas[e.x][e.y-1].tipo != 'H' ) { 
                 Estado arriba=new Estado(e.x,e.y-1,'U',e);
                 if ( !historial.contains(arriba)) {
                    colaEstados.add(0,arriba);
                    historial.add(arriba);
                    //lienzo.laberinto.celdas[e.x][e.y-1].tipo='A';
                    if ( arriba.equals(objetivo)) {
                        //lienzo.laberinto.celdas[e.x][e.y-1].tipo='D';
                        objetivo=arriba;
                        exito=true;
                    }
                    
                 }
            }     
        }
    }
    
    private void moverAbajo(Estado e) {
        System.out.println(e.toString());
        if ( e.y != alturaMundoVirtual - 1 ) {
            if ( lienzo.laberinto.celdas[e.x][e.y+1].tipo != 'H' ) {
                 Estado abajo=new Estado(e.x,e.y+1,'D',e);   
                 if ( !historial.contains(abajo)) {
                    colaEstados.add(0,abajo);
                    historial.add(abajo);
                    //lienzo.laberinto.celdas[e.x][e.y+1].tipo='A';
                    if ( abajo.equals(objetivo)) {
                        //lienzo.laberinto.celdas[e.x][e.y+1].tipo='D';
                        objetivo=abajo;
                        exito=true;
                    }
                 }   
            }     
        }
    }    
    
    private void moverIzquierda(Estado e) {
        
        if ( e.x > 0 ) {
            if ( lienzo.laberinto.celdas[e.x-1][e.y].tipo != 'H' ) {
                Estado izquierda=new Estado(e.x-1,e.y,'L',e);
                if ( !historial.contains(izquierda)) {
                   colaEstados.add(0,izquierda);
                   historial.add(izquierda);
                   //lienzo.laberinto.celdas[e.x-1][e.y].tipo='A';
                   if ( izquierda.equals(objetivo)) {
                       //lienzo.laberinto.celdas[e.x-1][e.y].tipo='D';
                       objetivo=izquierda;
                       exito=true;
                   }
                }   
            }    
        }
    }    
    
    private void moverDerecha(Estado e) {
        
        if ( e.x < anchuraMundoVirtual - 1 ) {
            if ( lienzo.laberinto.celdas[e.x+1][e.y].tipo != 'H' ) {
               Estado derecha=new Estado(e.x+1,e.y,'R',e); 
               if ( !historial.contains(derecha)){
                 colaEstados.add(0,derecha);
                 historial.add(derecha);
                 //lienzo.laberinto.celdas[e.x+1][e.y].tipo='A';
                 if ( derecha.equals(objetivo)) {
                    //lienzo.laberinto.celdas[e.x+1][e.y].tipo='D';
                     objetivo=derecha;
                     exito=true;
                 }
               }  
            }     
        }
    }    
    
    public void calcularRuta() {
        Estado predecesor=objetivo;
        do{
            pasos.add(predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos=pasos.size()-1;
        
    }

    @Override
    public void run() {
       if ( index_pasos >= 0 ) {
          switch(pasos.get(index_pasos)) {
             case 'D': lienzo.jugador.moverCeldaAbajo();break;
             case 'U': lienzo.jugador.moverCeldaArriba(); break;
             case 'R': lienzo.jugador.moverCeldaDerecha();break;
             case 'L': lienzo.jugador.moverCeldaIzquierda();break;
          }
          lienzo.repaint();  
          index_pasos--;
       }else {
          this.cancel();
       }
    }
}
