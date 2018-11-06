package ia;

import static ia.Constantes.busquedaSegura;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Jugador extends TimerTask implements Constantes{

    public Laberinto laberinto;
    public Celda celdaMovimiento;
    public static boolean meta = false;
    public char previousType = 'S';
    public int cartasPorEntregar = Cartas;
    public BusquedaRutaAmplitud inteligencia;
    
     public Jugador(Laberinto laberinto, int x, int y) {
        this.laberinto = laberinto;
        
        
        celdaMovimiento = new Celda(x, y, 'P',0,0);
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
        inteligencia = new BusquedaRutaAmplitud(laberinto.lienzoPadre, this);
}
     
public void moverCelda( KeyEvent evento ) {
            switch( evento.getKeyCode() ) {
            case KeyEvent.VK_UP:
            System.out.println("Mover arriba");
            moverCeldaArriba();
            break;
            case KeyEvent.VK_DOWN:
            System.out.println("Mover abajo");
            moverCeldaAbajo();
            break;
            case KeyEvent.VK_LEFT:
            System.out.println("Mover izquierda");
            moverCeldaIzquierda();
            break;
            case KeyEvent.VK_RIGHT:
            System.out.println("Mover derecha");
            moverCeldaDerecha();
            break;
            }
            }

public void moverCeldaArriba(){
        if (celdaMovimiento.y > 0 ) {
         if(comprobarMovimiento(laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y-1]) && comprobarAutoCerca('U') )  { 
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
            celdaMovimiento.y = celdaMovimiento.y-1;
            previousType = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 3;

                }
                }
                }
    
    public void moverCeldaAbajo(){
        if (celdaMovimiento.y < 20) {
          if(comprobarMovimiento(laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y+1]) && comprobarAutoCerca('D'))  { 
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
            celdaMovimiento.y = celdaMovimiento.y+1;
            previousType = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 0;

                }
                }
                }
    
    public void moverCeldaDerecha(){
        if (celdaMovimiento.x < anchuraMundoVirtual-1 && comprobarAutoCerca('R')) {
          if(comprobarMovimiento(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y]) )  { 
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
            celdaMovimiento.x = celdaMovimiento.x+1;
            previousType = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 2;

                }
                }
                }
    public void moverCeldaIzquierda(){
        if (celdaMovimiento.x > 0 && comprobarAutoCerca('L')) {
           if(comprobarMovimiento(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y]))  { 
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
            celdaMovimiento.x = celdaMovimiento.x-1;
            previousType = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
            laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 1;

                }
                }
                }
    
    
           public boolean comprobarMovimiento(Celda next){
        switch (next.tipo ){
            case 'K':
                return true;
            case 'G':
                return true;    
            case 's':
                return true;
            case 'r':
                return !busquedaSegura;
            case 'R':
                return !busquedaSegura;
            case 'S':
                return true;
            case 'C':
                return false;
            case 'H':
                return false;
            case 'B':
                return false;
            case 'W':
                return false;
            case 'M':
                meta = true;
                cartasPorEntregar--;
                if(cartasPorEntregar == 0)
                    JOptionPane.showMessageDialog(null, "Todas las cartas han sido entregadas");
                else
                    JOptionPane.showMessageDialog(null, "Cartero en Portal, quedan "+cartasPorEntregar+" cartas por entregar");
                return true;

        }
        return false; // OJO AQUI
    }    
           
            public boolean comprobarAutoCerca(char movimiento){
        switch (movimiento){
            case 'U': //Up
                  if(celdaMovimiento.x == anchuraMundoVirtual - 1){
                       if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C' || 
                          laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C' ||
                          laberinto.celdas[celdaMovimiento.x-3][celdaMovimiento.y-1].tipo == 'C' )
                         return false;
                       else 
                         return true;
                  }
                  
                  if(celdaMovimiento.x == anchuraMundoVirtual - 2 || celdaMovimiento.x == 1){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C')
                      return false;
                    else 
                      return true;
                                                                                           }
                  if(celdaMovimiento.x == anchuraMundoVirtual - 3 || celdaMovimiento.x == 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                                                                                           }
                  
                  if(celdaMovimiento.x < anchuraMundoVirtual - 3 && celdaMovimiento.x > 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+3][celdaMovimiento.y-1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-3][celdaMovimiento.y-1].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                  }
                  
                  if(celdaMovimiento.x == 0){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || 
                       laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C' ||
                       laberinto.celdas[celdaMovimiento.x+3][celdaMovimiento.y-1].tipo == 'C' )
                      return false;
                    else 
                      return true;
                  }
                  
                  
            case 'D': //Down
                  if(celdaMovimiento.x == anchuraMundoVirtual - 1){
                       if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || 
                          laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C' ||
                          laberinto.celdas[celdaMovimiento.x-3][celdaMovimiento.y+1].tipo == 'C' )
                         return false;
                       else 
                         return true;
                  }
                  
                  if(celdaMovimiento.x == anchuraMundoVirtual - 2 || celdaMovimiento.x == 1){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C')
                      return false;
                    else 
                      return true;
                                                                                           }
                  if(celdaMovimiento.x == anchuraMundoVirtual - 3 || celdaMovimiento.x == 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                                                                                           }
                  
                  if(celdaMovimiento.x < anchuraMundoVirtual - 3 && celdaMovimiento.x > 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+3][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-3][celdaMovimiento.y+1].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                  }
                  
                  if(celdaMovimiento.x == 0){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || 
                       laberinto.celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C' ||
                       laberinto.celdas[celdaMovimiento.x+3][celdaMovimiento.y+1].tipo == 'C' )
                      return false;
                    else 
                      return true;
                  }      
                  
            case 'R': //Right
                  if(celdaMovimiento.y == alturaMundoVirtual - 1){
                       if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || 
                          laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C' ||
                          laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-3].tipo == 'C' )
                         return false;
                       else 
                         return true;
                  }
                  
                  if(celdaMovimiento.y == alturaMundoVirtual - 2 || celdaMovimiento.y == 1){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C')
                      return false;
                    else 
                      return true;
                                                                                           }
                  if(celdaMovimiento.y == alturaMundoVirtual - 3 || celdaMovimiento.y == 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                                                                                           }
                  
                  if(celdaMovimiento.y < alturaMundoVirtual - 3 && celdaMovimiento.y > 2){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+3].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y-3].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                  }
                  
                  if(celdaMovimiento.y == 0){
                    if(laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || 
                       laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C' ||
                       laberinto.celdas[celdaMovimiento.x+1][celdaMovimiento.y+3].tipo == 'C' )
                      return false;
                    else 
                      return true;
                  }        
            
        case 'L': //Left
                  if(celdaMovimiento.y == alturaMundoVirtual - 1){
                       if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C' || 
                          laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C' ||
                          laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-3].tipo == 'C' )
                         return false;
                       else 
                         return true;
                  }
                  
                  if(celdaMovimiento.y == alturaMundoVirtual - 2 || celdaMovimiento.y == 1){
                    if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C')
                      return false;
                    else 
                      return true;
                                                                                           }
                  if(celdaMovimiento.y == alturaMundoVirtual - 3 || celdaMovimiento.y == 2){
                    if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                                                                                           }
                  
                  if(celdaMovimiento.y < alturaMundoVirtual - 3 && celdaMovimiento.y > 2){
                    if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+3].tipo == 'C'
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C'      
                      || laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y-3].tipo == 'C'      )
                      return false;
                    else 
                      return true;
                  }
                  
                  if(celdaMovimiento.y == 0){
                    if(laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || 
                       laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C' ||
                       laberinto.celdas[celdaMovimiento.x-1][celdaMovimiento.y+3].tipo == 'C' )
                      return false;
                    else 
                      return true;
                  }
        
        
        
        
        
        }
        return true;
    }   
    

@Override
public void run() {
    laberinto.lienzoPadre.repaint();
}
}

