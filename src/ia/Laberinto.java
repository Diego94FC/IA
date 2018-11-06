package ia;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Laberinto extends JComponent implements Constantes {
    public int anchuraLaberinto,alturaLaberinto;//dimensiones del laberinto
    public Celda[][] celdas;//las casillas n x m
    public Lienzo lienzoPadre;
    public BufferedImage carta; 
    public Jugador jugador;
    

    
    

    public Laberinto(Lienzo lienzoPadre) {
    this.lienzoPadre = lienzoPadre;
    celdas = new Celda[anchuraMundoVirtual][alturaMundoVirtual];
    
    //inicializar el array de celdas
        for(int i=0; i < anchuraMundoVirtual; i++)
            for ( int j=0 ; j < alturaMundoVirtual ; j++)
                celdas[i][j] = new Celda(i+(i*anchuraCelda), j+(j*alturaCelda), 'S',0,0);
    
    //ancho y largo del laberinto
    this.anchuraLaberinto = anchuraMundoVirtual*anchuraCelda;
    this.alturaLaberinto = alturaMundoVirtual*alturaCelda;
    this.setSize(anchuraLaberinto,alturaLaberinto);
    
    
    createHouses();    
    createStreets();
    createGrass();
    createPortals();
    createCrosswalks();
    

   
    // Jugador
//    celdaMovimiento = new Celda(1, 1, 'P',0,0);
//    celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
    
    
    //Paraderos
    celdas[9][1].tipo = 's';
    celdas[anchuraMundoVirtual-2][alturaMundoVirtual/2].tipo = 's';
    celdas[1][12].tipo = 's';
    celdas[19][alturaMundoVirtual-2].tipo = 's';
    
    
//    Pasajeros del Bus
//    for(int i = 0; i < pasajerosAlInicio; i++)
//        celdas[anchuraMundoVirtual-(i+1)][0].tipo = 'W';

     try {
       carta = ImageIO.read(new File("src\\ia\\imagenes\\Letter.png")); 
    
     } catch (IOException e) {
            System.out.println(e.toString());
            }
   
    }
//    public static boolean meta = false;
//    public char previousType = 'S';
//    public Celda celdaMovimiento;
 
    
    
    public void createStreets(){
     // Calles al borde del mapa   
     for(int i = 0; i < anchuraMundoVirtual; i++){
       for (int j = 0; j < alturaMundoVirtual; j++){
        //Calles horizontales 
        if(j == 0 || j == alturaMundoVirtual-1) 
          celdas[i][j].tipo = 'r';
        if (i == 0 || i == anchuraMundoVirtual-1)
            celdas[i][j].tipo = 'R';
       }
          }    
         // Calles horizontales
        for(int i = 0; i < anchuraMundoVirtual - 5; i++ ){
            celdas[i][5].tipo = 'r';
            celdas[i][10].tipo = 'r';
            celdas[i][15].tipo = 'r';
        }
        //Calles verticales
        for(int i = 1; i < alturaMundoVirtual - 1; i++ ){
            celdas[6][i].tipo = 'R';
            celdas[12][i].tipo = 'R';
            celdas[18][i].tipo = 'R';
            celdas[24][i].tipo = 'R';
            celdas[30][i].tipo = 'R';
            celdas[36][i].tipo = 'R';
            
        }
        
        
    }
    
    public void createGrass(){
        for(int i = 0; i < anchuraMundoVirtual; i++)
         for (int j = 1; j < alturaMundoVirtual -1 ; j++)
                if(i == anchuraMundoVirtual - 2 || i == anchuraMundoVirtual - 3 || i == anchuraMundoVirtual - 4 )
                    celdas[i][j].tipo = 'G';
        
                 celdas[anchuraMundoVirtual - 3][1].tipo = 'S';
                 celdas[anchuraMundoVirtual - 4][1].tipo = 'S';
                 celdas[anchuraMundoVirtual - 2][1].tipo = 'S';
                 celdas[anchuraMundoVirtual - 3][alturaMundoVirtual - 2].tipo = 'S';
                 celdas[anchuraMundoVirtual - 4][alturaMundoVirtual - 2].tipo = 'S';
                 celdas[anchuraMundoVirtual - 2][alturaMundoVirtual - 2].tipo = 'S';
    }
    
    public void createHouses(){
        
        
        
        for(int i = 0; i < anchuraMundoVirtual; i++){
         for (int j = 0; j < alturaMundoVirtual -1 ; j++){
     
                 if(j == 2 && (i == 2 || i == 8 || i == 14 || i == 20 || i == 26 || i == 32) ||
                         j == 7 && (i == 2 || i == 8 || i == 14 || i == 20 || i == 26 || i == 32) ||
                         j == 12 && (i == 2 || i == 8 || i == 14 || i == 20 || i == 26 || i == 32) ||
                         j == 17 && (i == 2 || i == 8 || i == 14 || i == 20 || i == 26 || i == 32)){
                 celdas[i][j].tipo = 'H';
                 celdas[i+1][j].tipo = 'H';
                 celdas[i+2][j].tipo = 'H';
                 celdas[i][j+1].tipo = 'H';
                 celdas[i+1][j+1].tipo = 'H';
                 celdas[i+2][j+1].tipo = 'H';
             }
                 
                 
                 
                }
         
        }
        }
    
    public void createPortals(){

    for(int i = 0; i < anchuraMundoVirtual - 7; i++){
        if(i == 3 || i == 9 || i == 15 || i == 21 || i == 27 || i == 33)
            celdas[i][3].tipo = 'M';
        if(i == 3 || i == 9 || i == 15 || i == 21 || i == 27 || i == 33)
            celdas[i][8].tipo = 'M';
        if(i == 3 || i == 9 || i == 15 || i == 21 || i == 27 || i == 33)
            celdas[i][13].tipo = 'M';
        if(i == 3 || i == 9 || i == 15 || i == 21 || i == 27 || i == 33)
            celdas[i][18].tipo = 'M';
    }
    }
    
    public void createCrosswalks(){
        for(int i = 5; i < anchuraMundoVirtual - 1; i++){
            if(i % 6 == 0 && i != 36){
                celdas[i][1].tipo = 'K';
                celdas[i][4].tipo = 'K';
                celdas[i][6].tipo = 'K';
                celdas[i][9].tipo = 'K';
                celdas[i][11].tipo = 'K';
                celdas[i][14].tipo = 'K';
                celdas[i][16].tipo = 'K';
                celdas[i][19].tipo = 'K';
                                       }   
                                        }
                                        
                for (int i = 4; i < alturaMundoVirtual - 3; i++){
                    if(i == 5 || i == 10 || i == 15)
                    celdas[1][i].tipo = 'K';
                                     }
                //Para el cruce al pasto
                celdas[36][1].tipo = 'K';
                celdas[36][alturaMundoVirtual - 2].tipo = 'K';
                celdas[36][(alturaMundoVirtual/2)+1].tipo = 'K';
                celdas[36][(alturaMundoVirtual/2)-1].tipo = 'K';
                                     }
    
    
//    public void moverCelda( KeyEvent evento ) {
//            switch( evento.getKeyCode() ) {
//            case KeyEvent.VK_UP:
//            System.out.println("Mover arriba");
//            moverCeldaArriba();
//            break;
//            case KeyEvent.VK_DOWN:
//            System.out.println("Mover abajo");
//            moverCeldaAbajo();
//            break;
//            case KeyEvent.VK_LEFT:
//            System.out.println("Mover izquierda");
//            moverCeldaIzquierda();
//            break;
//            case KeyEvent.VK_RIGHT:
//            System.out.println("Mover derecha");
//            moverCeldaDerecha();
//            break;
//            }
//            }
    
//    public void moverCeldaArriba(){
//        if (celdaMovimiento.y > 0 ) {
//         if(comprobarMovimiento(celdas[celdaMovimiento.x][celdaMovimiento.y-1]) && comprobarAutoCerca('U') )  { 
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
//            celdaMovimiento.y = celdaMovimiento.y-1;
//            previousType = celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
//            celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 3;
//
//                }
//                }
//                }
//    
//    public void moverCeldaAbajo(){
//        if (celdaMovimiento.y < 20) {
//          if(comprobarMovimiento(celdas[celdaMovimiento.x][celdaMovimiento.y+1]) && comprobarAutoCerca('D'))  { 
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
//            celdaMovimiento.y = celdaMovimiento.y+1;
//            previousType = celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
//            celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 0;
//
//                }
//                }
//                }
//    
//    public void moverCeldaDerecha(){
//        if (celdaMovimiento.x < anchuraMundoVirtual-1 && comprobarAutoCerca('R')) {
//          if(comprobarMovimiento(celdas[celdaMovimiento.x+1][celdaMovimiento.y]) )  { 
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
//            celdaMovimiento.x = celdaMovimiento.x+1;
//            previousType = celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
//            celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 2;
//
//                }
//                }
//                }
//    public void moverCeldaIzquierda(){
//        if (celdaMovimiento.x > 0 && comprobarAutoCerca('L')) {
//           if(comprobarMovimiento(celdas[celdaMovimiento.x-1][celdaMovimiento.y]))  { 
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = previousType;
//            celdaMovimiento.x = celdaMovimiento.x-1;
//            previousType = celdas[celdaMovimiento.x][celdaMovimiento.y].tipo;
//            celdas[celdaMovimiento.x][celdaMovimiento.y].tipo = 'P';
//            celdas[celdaMovimiento.x][celdaMovimiento.y].indexPlayerSprite = 1;
//
//                }
//                }
//                }
    
//    public boolean comprobarMovimiento(Celda next){
//        switch (next.tipo ){
//            case 'K':
//                return true;
//            case 'G':
//                return true;    
//            case 's':
//                return true;
//            case 'r':
//                return !busquedaSegura;
//            case 'R':
//                return !busquedaSegura;
//            case 'S':
//                return true;
//            case 'C':
//                return false;
//            case 'H':
//                return false;
//            case 'B':
//                return false;
//            case 'W':
//                return false;
//            case 'M':
//                meta = true;
//                cartasPorEntregar--;
//                if(cartasPorEntregar == 0)
//                    JOptionPane.showMessageDialog(null, "Todas las cartas han sido entregadas");
//                else
//                    JOptionPane.showMessageDialog(null, "Cartero en Portal, quedan "+cartasPorEntregar+" cartas por entregar");
//                return true;
//
//        }
//        return false; // OJO AQUI
//    }
    
//    public boolean comprobarAutoCerca(char movimiento){
//        switch (movimiento){
//            case 'U': //Up
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 1){
//                       if(celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C' || 
//                          celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C' ||
//                          celdas[celdaMovimiento.x-3][celdaMovimiento.y-1].tipo == 'C' )
//                         return false;
//                       else 
//                         return true;
//                  }
//                  
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 2 || celdaMovimiento.x == 1){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C')
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 3 || celdaMovimiento.x == 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  
//                  if(celdaMovimiento.x < anchuraMundoVirtual - 3 && celdaMovimiento.x > 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+3][celdaMovimiento.y-1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-2][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-3][celdaMovimiento.y-1].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                  }
//                  
//                  if(celdaMovimiento.x == 0){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || 
//                       celdas[celdaMovimiento.x+2][celdaMovimiento.y-1].tipo == 'C' ||
//                       celdas[celdaMovimiento.x+3][celdaMovimiento.y-1].tipo == 'C' )
//                      return false;
//                    else 
//                      return true;
//                  }
//                  
//                  
//            case 'D': //Down
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 1){
//                       if(celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || 
//                          celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C' ||
//                          celdas[celdaMovimiento.x-3][celdaMovimiento.y+1].tipo == 'C' )
//                         return false;
//                       else 
//                         return true;
//                  }
//                  
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 2 || celdaMovimiento.x == 1){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C')
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  if(celdaMovimiento.x == anchuraMundoVirtual - 3 || celdaMovimiento.x == 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  
//                  if(celdaMovimiento.x < anchuraMundoVirtual - 3 && celdaMovimiento.x > 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+3][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-2][celdaMovimiento.y+1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-3][celdaMovimiento.y+1].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                  }
//                  
//                  if(celdaMovimiento.x == 0){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || 
//                       celdas[celdaMovimiento.x+2][celdaMovimiento.y+1].tipo == 'C' ||
//                       celdas[celdaMovimiento.x+3][celdaMovimiento.y+1].tipo == 'C' )
//                      return false;
//                    else 
//                      return true;
//                  }      
//                  
//            case 'R': //Right
//                  if(celdaMovimiento.y == alturaMundoVirtual - 1){
//                       if(celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C' || 
//                          celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C' ||
//                          celdas[celdaMovimiento.x+1][celdaMovimiento.y-3].tipo == 'C' )
//                         return false;
//                       else 
//                         return true;
//                  }
//                  
//                  if(celdaMovimiento.y == alturaMundoVirtual - 2 || celdaMovimiento.y == 1){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C')
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  if(celdaMovimiento.y == alturaMundoVirtual - 3 || celdaMovimiento.y == 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C'
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  
//                  if(celdaMovimiento.y < alturaMundoVirtual - 3 && celdaMovimiento.y > 2){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C'
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y+3].tipo == 'C'
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y-2].tipo == 'C'      
//                      || celdas[celdaMovimiento.x+1][celdaMovimiento.y-3].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                  }
//                  
//                  if(celdaMovimiento.y == 0){
//                    if(celdas[celdaMovimiento.x+1][celdaMovimiento.y+1].tipo == 'C' || 
//                       celdas[celdaMovimiento.x+1][celdaMovimiento.y+2].tipo == 'C' ||
//                       celdas[celdaMovimiento.x+1][celdaMovimiento.y+3].tipo == 'C' )
//                      return false;
//                    else 
//                      return true;
//                  }        
//            
//        case 'L': //Left
//                  if(celdaMovimiento.y == alturaMundoVirtual - 1){
//                       if(celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C' || 
//                          celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C' ||
//                          celdas[celdaMovimiento.x-1][celdaMovimiento.y-3].tipo == 'C' )
//                         return false;
//                       else 
//                         return true;
//                  }
//                  
//                  if(celdaMovimiento.y == alturaMundoVirtual - 2 || celdaMovimiento.y == 1){
//                    if(celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C')
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  if(celdaMovimiento.y == alturaMundoVirtual - 3 || celdaMovimiento.y == 2){
//                    if(celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                                                                                           }
//                  
//                  if(celdaMovimiento.y < alturaMundoVirtual - 3 && celdaMovimiento.y > 2){
//                    if(celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y+3].tipo == 'C'
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-1].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-2].tipo == 'C'      
//                      || celdas[celdaMovimiento.x-1][celdaMovimiento.y-3].tipo == 'C'      )
//                      return false;
//                    else 
//                      return true;
//                  }
//                  
//                  if(celdaMovimiento.y == 0){
//                    if(celdas[celdaMovimiento.x-1][celdaMovimiento.y+1].tipo == 'C' || 
//                       celdas[celdaMovimiento.x-1][celdaMovimiento.y+2].tipo == 'C' ||
//                       celdas[celdaMovimiento.x-1][celdaMovimiento.y+3].tipo == 'C' )
//                      return false;
//                    else 
//                      return true;
//                  }
//        
//        
//        
//        
//        
//        }
//        return true;
//    }   
//    
    

    
@Override
public void update(Graphics g)  {
  

}
@Override
    public void paintComponent(Graphics g) {
    update(g);
}

}



