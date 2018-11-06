package ia;

import java.util.TimerTask;

public class Car extends TimerTask implements Constantes{
    
    public Laberinto laberinto;
    public Celda auto;
    public int id;
    
    
    public Car(Laberinto laberinto, int id, int x, int y) {

         this.laberinto = laberinto;
         auto = new Celda(x, y,'C', id,0);
         laberinto.celdas[auto.x][auto.y].tipo = 'C';
                                    }
    
public char previousType = 'R';

public void moverAuto1(){   
    if(auto.carID == 1){
        if(auto.carID == 1 && auto.x == 18)
            moverAutoAbajo(15);
        if(auto.carID == 1 && auto.y == 15)
            moverAutoIzquierda(12);
        if(auto.carID == 1 && auto.x == 12)
            moverAutoArriba(10);
        if(auto.carID == 1 && auto.y == 10)
            moverAutoDerecha(18);
                        }
}

public void moverAuto2(){   
    if(auto.carID == 2)
        if(auto.carID == 2 && auto.y == 10)
            moverAutoDerecha(12);
        if (auto.carID == 2 && auto.x == 12)
            moverAutoArriba(5);
        if (auto.carID == 2 && auto.y == 5)
            moverAutoIzquierda(6);
        if (auto.carID == 2 && auto.x == 6)
            moverAutoAbajo(11);
    
                        }

public void moverAuto3(){
    
    if(auto.carID == 3){
        if(auto.carID == 3 && auto.y == alturaMundoVirtual-6)
            moverAutoIzquierda(anchuraMundoVirtual-11);
        if(auto.carID == 3 && auto.x == anchuraMundoVirtual-11)
             moverAutoArriba(alturaMundoVirtual-16);
        if(auto.carID == 3 && auto.y == alturaMundoVirtual-16)
            moverAutoDerecha(anchuraMundoVirtual-5);
        if(auto.carID == 3 && auto.x == anchuraMundoVirtual-5)
            moverAutoAbajo(alturaMundoVirtual-6);
           
                        }
}


public void moverAutoIzquierda(int celdaDestino){
    if(auto.x > 0 && auto.x > celdaDestino && laberinto.celdas[auto.x-1][auto.y].tipo != 'P'){
        laberinto.celdas[auto.x][auto.y].tipo = previousType;
        auto.x = auto.x - 1;
        previousType = laberinto.celdas[auto.x][auto.y].tipo;
        laberinto.celdas[auto.x][auto.y].tipo= 'C';        
        laberinto.celdas[auto.x][auto.y].indexCarSprite = 1;   
    }
                                                }

public void moverAutoAbajo(int celdaDestino){
    if(auto.y < alturaMundoVirtual && auto.y < celdaDestino && laberinto.celdas[auto.x][auto.y+1].tipo != 'P'){
        laberinto.celdas[auto.x][auto.y].tipo = previousType;
        auto.y = auto.y + 1;
        previousType = laberinto.celdas[auto.x][auto.y].tipo;
        laberinto.celdas[auto.x][auto.y].tipo= 'C';
        laberinto.celdas[auto.x][auto.y].indexCarSprite = 3;
    }
}

public void moverAutoDerecha(int celdaDestino){
    if(auto.x < anchuraMundoVirtual && auto.x < celdaDestino && laberinto.celdas[auto.x+1][auto.y].tipo != 'P'){
        laberinto.celdas[auto.x][auto.y].tipo = previousType;
        auto.x = auto.x + 1;
        previousType = laberinto.celdas[auto.x][auto.y].tipo;
        laberinto.celdas[auto.x][auto.y].tipo= 'C';
        laberinto.celdas[auto.x][auto.y].indexCarSprite = 2;
        
    }  
}

public void moverAutoArriba(int celdaDestino){
    if(auto.y > 0 && auto.y > celdaDestino && laberinto.celdas[auto.x][auto.y-1].tipo != 'P'){
        laberinto.celdas[auto.x][auto.y].tipo = previousType;
        auto.y = auto.y - 1;
        previousType = laberinto.celdas[auto.x][auto.y].tipo;
        laberinto.celdas[auto.x][auto.y].tipo= 'C';
        laberinto.celdas[auto.x][auto.y].indexCarSprite = 0;        
    }
   
}


@Override
public void run() {
    moverAuto1();
    moverAuto2();
    moverAuto3();
    laberinto.lienzoPadre.repaint();
}
}
