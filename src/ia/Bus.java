package ia;

import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Bus extends TimerTask implements Constantes{
    
    public Laberinto laberinto;
    public Celda auto;
    public int pasajeros = pasajerosAlInicio; 
    
    
    public Bus(Laberinto laberinto, int x, int y) {

         this.laberinto = laberinto;
         auto = new Celda(x, y,'O', 0,0);
         laberinto.celdas[auto.x][auto.y].tipo = 'O';
                                    }
    
public void moverBus1(){ 
        if(pasajeros != 0){
        if(auto.y == 0)
            moverAutoIzquierda(0);
        if(auto.x == 0){
                moverAutoAbajo(alturaMundoVirtual-1);
        }
        if(auto.y == alturaMundoVirtual-1)
            moverAutoDerecha(anchuraMundoVirtual - 1);
        if(auto.x == anchuraMundoVirtual-1)
            moverAutoArriba(0);
                        
}
        else
            laberinto.celdas[auto.x][auto.y].tipo = 'r';
}



public void moverAutoIzquierda(int celdaDestino){
    if(auto.x > 0 && auto.x > celdaDestino && laberinto.celdas[auto.x-1][auto.y].tipo != 'P'){

        if(pasajeros == 0)
            laberinto.celdas[auto.x][auto.y].tipo = 'r';
        
       if(auto.x == anchuraMundoVirtual - 1){
            auto.x = auto.x - pasajeros - 1;               
         for(int i = 0; i < pasajeros; i++)
                laberinto.celdas[anchuraMundoVirtual - 1][auto.y+i+1].tipo= 'r';
        
       }
        else
            auto.x = auto.x - 1;
        laberinto.celdas[auto.x][auto.y].tipo = 'O';
        
        //Mover Pasajeros
        for(int i = 0; i < pasajeros; i++){
            laberinto.celdas[auto.x+i+1][auto.y].tipo= 'W';        
            laberinto.celdas[auto.x+i+2][auto.y].tipo= 'r';
        }
        
    
           
           //Eliminar Pasajero
    if(laberinto.celdas[auto.x][auto.y+1].tipo == 's'){
        pasajeros = pasajeros - 1;
        laberinto.celdas[auto.x+pasajeros+1][auto.y].tipo= 'r';
        }
    }
    
    
        
    
                                                }

public void moverAutoAbajo(int celdaDestino){
    if(auto.y < alturaMundoVirtual && auto.y < celdaDestino && laberinto.celdas[auto.x][auto.y+1].tipo != 'P'){
        if(pasajeros == 0)
            laberinto.celdas[auto.x][auto.y].tipo = 'R';
        
        if(auto.y == 0)
            auto.y = auto.y + pasajeros +1;
        else
            auto.y = auto.y + 1;
        laberinto.celdas[auto.x][auto.y].tipo= 'O';
        
        //Mover Pasajeros
         for(int i = 0; i < pasajeros; i++){
            laberinto.celdas[auto.x][auto.y-i-1].tipo= 'W';        
            laberinto.celdas[auto.x][auto.y-i-2].tipo= 'R';
        }
         if(auto.x == 0 && auto.y == pasajeros + 1){
         for(int i = 0; i < pasajeros; i++)
                laberinto.celdas[auto.x+i+1][0].tipo= 'R';
    }
    }
    
    //Eliminar Pasajero
    if(laberinto.celdas[auto.x+1][auto.y].tipo == 's'){
        pasajeros = pasajeros - 1;
        laberinto.celdas[auto.x][auto.y-pasajeros-1].tipo= 'R';
        }
        
    
     
    }


public void moverAutoDerecha(int celdaDestino){
    if(auto.x < anchuraMundoVirtual && auto.x < celdaDestino && laberinto.celdas[auto.x+1][auto.y].tipo != 'P'){
        if(pasajeros == 0)
            laberinto.celdas[auto.x][auto.y].tipo = 'r';
        
        if(auto.x == 0)
            auto.x = auto.x + pasajeros +1;
        else
            auto.x = auto.x + 1;
        laberinto.celdas[auto.x][auto.y].tipo = 'O';
        
        
        //Mover Pasajeros
         for(int i = 0; i < pasajeros; i++){
            laberinto.celdas[auto.x-i-1][auto.y].tipo= 'W';        
            laberinto.celdas[auto.x-i-2][auto.y].tipo= 'r';
        }
         if(auto.x == pasajeros + 1 && auto.y == alturaMundoVirtual-1){
         for(int i = 0; i < pasajeros; i++)
                laberinto.celdas[0][auto.y-i-1].tipo= 'R';
    }
    }
    
    //Eliminar Pasajero
    if(laberinto.celdas[auto.x][auto.y-1].tipo == 's'){
        pasajeros = pasajeros - 1;
        laberinto.celdas[auto.x-pasajeros-1][auto.y].tipo= 'r';
        }
    
        
    }
     
    


public void moverAutoArriba(int celdaDestino){
    
    if(auto.y > 0 && auto.y > celdaDestino && laberinto.celdas[auto.x][auto.y-1].tipo != 'P'){
        if(pasajeros == 0)
            laberinto.celdas[auto.x][auto.y].tipo = 'R';
        
        if(auto.y == alturaMundoVirtual - 1)
            auto.y = auto.y - pasajeros - 1;
        else
            auto.y = auto.y - 1;
        laberinto.celdas[auto.x][auto.y].tipo= 'O';
        
        //Mover Pasajeros
         for(int i = 0; i < pasajeros; i++){
            laberinto.celdas[auto.x][auto.y+i+1].tipo= 'W';        
            laberinto.celdas[auto.x][auto.y+i+2].tipo= 'R';
        }
         if(auto.x == anchuraMundoVirtual - 1  && auto.y == alturaMundoVirtual - pasajeros - 2){
         for(int i = 0; i < pasajeros; i++)
                laberinto.celdas[auto.x-i-1][alturaMundoVirtual - 1].tipo= 'R';
    }
    }
    
    //Eliminar Pasajero
    if(laberinto.celdas[auto.x-1][auto.y].tipo == 's'){
        pasajeros = pasajeros - 1;
        laberinto.celdas[auto.x][auto.y + pasajeros + 1].tipo = 'R';
        }
}


@Override
public void run() {
    moverBus1();
    laberinto.lienzoPadre.repaint();
}
}
