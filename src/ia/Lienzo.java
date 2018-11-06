package ia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JOptionPane;



public class Lienzo extends Canvas implements Constantes{
//para pintar el lienzo
public Laberinto laberinto;
//Para animacion basica
public Car auto, auto2, auto3, auto4;
public Bus bus;
public Peaton peaton1, peaton2, peaton3;
public Timer lanzadorTareas;
public Jugador jugador;


public Lienzo(){
        laberinto = new Laberinto(this);
        jugador = new Jugador(laberinto, 1, 1);
        //color de fondo
        //this.setBackground(Color.orange);
        //dimensiones del lienzo
        this.setSize(laberinto.anchuraLaberinto,laberinto.alturaLaberinto);
        
        auto = new Car(laberinto,1, 18, 10);
        auto2 = new Car(laberinto,2, 6, 10);
        auto3 = new Car(laberinto,3, anchuraMundoVirtual-5, alturaMundoVirtual-6);
        bus = new Bus(laberinto,anchuraMundoVirtual-(pasajerosAlInicio+1), 0);
        peaton1 = new Peaton(laberinto, 1, 5,alturaMundoVirtual-5);
        peaton2 = new Peaton(laberinto, 2, 13,1);
        peaton3 = new Peaton(laberinto, 3, anchuraMundoVirtual-12,alturaMundoVirtual-5);
//        
          
        //escuchador eventos de teclado
        addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
        jugador.moverCelda(e);
        repaint();
        }
        });
        //Amplitud
//        BusquedaRutaAmplitud amplitud = new BusquedaRutaAmplitud(this);
//        long t1=System.nanoTime();
//        amplitud.buscar();
//        amplitud.calcularRuta();
//        long t2=System.nanoTime();
//        double seg=(t2-t1)/1000000000.0;
//        System.out.println("Tiempo calculo ruta anchura:"+seg+" seg");
        //System.out.println(amplitud.pasos);
        
        //Amplitud Multiobjetivo
//        BusquedaAmplitudMultiObjetivo amplitudMulti = new BusquedaAmplitudMultiObjetivo(this);
//        long t5=System.nanoTime();
//        amplitudMulti.buscar();
//        amplitud.calcularRuta();
//        long t6=System.nanoTime();
//        double seg3=(t6-t5)/1000000000.0;
//        System.out.println("Tiempo calculo ruta amplitud multiobjetivo:"+seg+" seg");
        
        
        //Profundidad
//        BusquedaRutaProfundidad profundidad = new BusquedaRutaProfundidad(this);
//        long t3=System.nanoTime();
//        profundidad.buscar();
//        profundidad.calcularRuta();
//        long t4=System.nanoTime();
//        double seg2 = (t2-t1)/1000000000.0;
//        System.out.println("Tiempo calculo ruta profundidad:"+seg+" seg");
        //System.out.println(profundidad.pasos);
        
        
        jugador.inteligencia.destinos.add(new Estado(3,alturaMundoVirtual-3,'N',null));
        jugador.inteligencia.destinos.add(new Estado(21,3,'N',null));
        jugador.inteligencia.destinos.add(new Estado(33,18,'N',null));
        jugador.inteligencia.destinos.add(new Estado(1,1,'N',null));
//        laberinto.lienzoPadre.jugador.inteligencia.calcularRuta();

        
            lanzadorTareas = new Timer();
            lanzadorTareas.scheduleAtFixedRate(jugador.inteligencia, 0, 200); 
//            lanzadorTareas.scheduleAtFixedRate(profundidad, 0, 500); 
//            lanzadorTareas.scheduleAtFixedRate(amplitudMulti, 0, 500); 
            lanzadorTareas.scheduleAtFixedRate(bus,800,800);
            lanzadorTareas.scheduleAtFixedRate(auto,0,300);
            lanzadorTareas.scheduleAtFixedRate(auto2,0,300);
            lanzadorTareas.scheduleAtFixedRate(auto3,0,300);
            lanzadorTareas.scheduleAtFixedRate(peaton1,0,600);
            lanzadorTareas.scheduleAtFixedRate(peaton2,0,550);
            lanzadorTareas.scheduleAtFixedRate(peaton3,0,600);
            
        

        }



//metodo llamada la primera que se pinta
@Override
public void update(Graphics g) {
    laberinto.paintComponent(g);
  for(int i=0; i < anchuraMundoVirtual ; i++)
        for ( int j=0 ; j < alturaMundoVirtual; j++)
           laberinto.celdas[i][j].paintComponent(g);
    
    for(int i = 1; i < jugador.cartasPorEntregar+1; i++)
        g.drawImage(laberinto.carta, (jugador.celdaMovimiento.x*32)+16*i+24, (jugador.celdaMovimiento.y*32)+8, null);
        System.out.println("x: "+jugador.celdaMovimiento.x);

                     }
    

@Override
    public void paint(Graphics g) {
    update(g);
}

private void activarCelda(MouseEvent evt) {
    for(int i=0; i < anchuraMundoVirtual; i++)
       for ( int j=0 ; j < alturaMundoVirtual ; j++)
          if ( laberinto.celdas[i][j].celdaSeleccionada(evt.getX(),evt.getY()) ) {
            //Para saber si se pulso
            if((evt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
                System.out.println("Boton derecho - Poner obstaculo");
                laberinto.celdas[i][j].tipo='S';
            }else {
                System.out.println("Boton izquierdo - Poner adversario");
                laberinto.celdas[i][j].tipo='R';
                                                    }
                                }   
                                }
                                }
                                
