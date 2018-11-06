package ia;

import java.util.ArrayList;
import java.util.Queue;
import java.util.TimerTask;
import java.util.PriorityQueue;
class BusquedaRutaAmplitud extends TimerTask implements Constantes{
    // 3 atributos para multi-objetivo
    public Jugador jugador;
    public ArrayList<Estado> destinos;
    public boolean parar;
    Lienzo lienzo;
    public Queue<Estado> colaEstados;
    ArrayList<Estado> historial;
    ArrayList<Character> pasos;
    int index_pasos;
    Estado inicial;
    Estado objetivo;
    Estado temp;
    boolean exito;
    
    public BusquedaRutaAmplitud(Lienzo lienzo, Jugador jugador) {
        
        this.lienzo = lienzo;
        colaEstados=new PriorityQueue<>();
        historial=new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos=0;
        this.jugador=jugador;
        destinos=new ArrayList<>();
        parar=false;

    }
    
    public boolean buscar(Estado inicial,Estado objetivo) {
        inicial.prioridad = distancia(inicial.x,inicial.y,lienzo.jugador.celdaMovimiento.x,lienzo.jugador.celdaMovimiento.y);
        index_pasos = 0;
        colaEstados.add(inicial);
        historial.add(inicial);
        this.objetivo = objetivo;
        exito=false;
                if ( inicial.equals(objetivo)) exito=true;
        
        while ( !colaEstados.isEmpty() && !exito ) {
            temp=colaEstados.poll();
//            colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        
        if ( exito ) {
            System.out.println("Ruta calculada");
            this.calcularRuta();
            return true;

        }
        else {
            System.out.println("La ruta no pudo calcularse");
            return false;
        }
        
    }
    //distancia adversario
public double distancia(int x1,int y1, int x2, int y2) {
double valor;
double parte1=Math.pow(Math.abs(x1-x2),2);
double parte2=Math.pow(Math.abs(y1-y2),2);
parte1+=parte2;
valor=Math.sqrt(parte1);
return valor;
}

    private void moverArriba(Estado e) {
        
        if ( e.y > 0 ) {
            if ( lienzo.laberinto.celdas[e.x][e.y-1].tipo != 'H' ) { 
                 Estado arriba=new Estado(e.x,e.y-1,'U',e);
                 arriba.prioridad = distancia(arriba.x,arriba.y,lienzo.jugador.celdaMovimiento.x,lienzo.jugador.celdaMovimiento.y);
                 if ( !historial.contains(arriba)) {
                    colaEstados.add(arriba);
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
        if ( e.y != alturaMundoVirtual - 1 ) {
            if ( lienzo.laberinto.celdas[e.x][e.y+1].tipo != 'H' ) {
                 Estado abajo=new Estado(e.x,e.y+1,'D',e);   
                 abajo.prioridad = distancia(abajo.x,abajo.y,lienzo.jugador.celdaMovimiento.x,lienzo.jugador.celdaMovimiento.y);
                 if ( !historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    //lienzo.laberinto.celdas[e.x][e.y+1].tipo='A';
                    if ( abajo.equals(objetivo)) {
                       // lienzo.laberinto.celdas[e.x][e.y+1].tipo='D';
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
                izquierda.prioridad = distancia(izquierda.x,izquierda.y,lienzo.jugador.celdaMovimiento.x,lienzo.jugador.celdaMovimiento.y);
                if ( !historial.contains(izquierda)) {
                   colaEstados.add(izquierda);
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
               derecha.prioridad = distancia(derecha.x,derecha.y,lienzo.jugador.celdaMovimiento.x,lienzo.jugador.celdaMovimiento.y);
               if ( !historial.contains(derecha)){
                 colaEstados.add(derecha);
                 historial.add(derecha);
               //  lienzo.laberinto.celdas[e.x+1][e.y].tipo='A';
                 if ( derecha.equals(objetivo)) {
                 //   lienzo.laberinto.celdas[e.x+1][e.y].tipo='D';
                     objetivo=derecha;
                     exito=true;
                 }
               }  
            }     
        }
    }    
    
    public void calcularRuta() {
        Estado predecesor = objetivo;
        do{
            pasos.add(0,predecesor.oper);
            predecesor=predecesor.predecesor;
        }while ( predecesor != null);
        index_pasos = pasos.size()-1;
        
    }

    @Override
    public void run() {
     //solo cuando quedan destinos donde ir
        if ( ! parar ) {
        //inicializacion de la busqueda
        colaEstados.clear();
        historial.clear();
        pasos.clear();
        Estado subinicial,subobjetivo;
        boolean resultado;
        do{
        //el estado inicial es donde estoy
        subinicial=new Estado(jugador.celdaMovimiento.x, jugador.celdaMovimiento.y,'N',null);
        //el estado final es a donde quiero ir
        subobjetivo=destinos.get(0);
        //busco ruta
        resultado=this.buscar(subinicial,subobjetivo);
        if ( subinicial.equals(subobjetivo))
        destinos.remove(subobjetivo);
        if ( destinos.isEmpty() ) {
        System.out.println("Se acabo a donde ir");
        this.cancel();
        }
        }while(!resultado && !destinos.isEmpty());
        if ( pasos.size() > 1 ) {
        switch(pasos.get(1)) {
        case 'D': lienzo.jugador.moverCeldaAbajo();break;
        case 'U': jugador.moverCeldaArriba(); break;
        case 'R': jugador.moverCeldaDerecha();break;
        case 'L': jugador.moverCeldaIzquierda();break;
        }
        lienzo.repaint();
        }
        }
    }
}


