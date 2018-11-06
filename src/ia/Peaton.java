package ia;

import java.util.TimerTask;

public class Peaton extends TimerTask implements Constantes{
    
    public Laberinto laberinto;
    public Celda peaton;
    public int id;
    public char previoustypeWalker = 'S';
    int cont = 0;
    
    public Peaton(Laberinto laberinto, int id, int x, int y) {

         this.laberinto = laberinto;
         peaton = new Celda(x, y,'W',0,id);
         laberinto.celdas[peaton.x][peaton.y].tipo = 'W';
                                    }
public void moverPeaton1(){   
    if(peaton.walkerID == 1){
        if(peaton.walkerID == 1 && peaton.y == alturaMundoVirtual-5)
            moverPeatonIzquierda(1);
        if(peaton.walkerID == 1 && peaton.x == 1)
            moverPeatonArriba(alturaMundoVirtual-10);
        if(peaton.walkerID == 1 && peaton.y == alturaMundoVirtual-10)
            moverPeatonDerecha(5);
        if(peaton.walkerID == 1 && peaton.x == 5)
            moverPeatonAbajo(alturaMundoVirtual-5);
        
                        }
}

 public void moverPeaton2(){   
    if(peaton.walkerID == 2){
        if(peaton.walkerID == 2 && peaton.y == 1)
            moverPeatonDerecha(23);
        if(peaton.walkerID == 2 && peaton.x == 23)
            moverPeatonAbajo(4);
        if(peaton.walkerID == 2 && peaton.y == 4)
            moverPeatonIzquierda(12);
        if(peaton.walkerID == 2 && peaton.x == 13)
            moverPeatonArriba(1);
                       }
}
 public void moverPeaton3(){   
    if(peaton.walkerID == 3){
        if(peaton.walkerID == 3 && peaton.y == alturaMundoVirtual-5)
            moverPeatonIzquierda(anchuraMundoVirtual-22);
        if(peaton.walkerID == 3 && peaton.x == anchuraMundoVirtual-22)
            moverPeatonArriba(alturaMundoVirtual-10);
        if(peaton.walkerID == 3 && peaton.y == alturaMundoVirtual-10)
                moverPeatonDerecha(anchuraMundoVirtual-12);
        if(peaton.walkerID == 3 && peaton.x == anchuraMundoVirtual-12)
            moverPeatonAbajo(alturaMundoVirtual-5);
         
                       }
}


    
    public void moverPeatonIzquierda(int celdaDestino){
    if(peaton.x > 0 && peaton.x > celdaDestino && laberinto.celdas[peaton.x-1][peaton.y].tipo != 'P'){
        laberinto.celdas[peaton.x][peaton.y].tipo = previoustypeWalker;
        peaton.x = peaton.x - 1;
        previoustypeWalker = laberinto.celdas[peaton.x][peaton.y].tipo;  
        laberinto.celdas[peaton.x][peaton.y].tipo= 'W';        
        laberinto.celdas[peaton.x][peaton.y].indexWalkerSprite = 1;   
    }
    
}
public void moverPeatonAbajo(int celdaDestino){
    if(peaton.y < alturaMundoVirtual && peaton.y < celdaDestino && laberinto.celdas[peaton.x][peaton.y+1].tipo != 'P'){
        laberinto.celdas[peaton.x][peaton.y].tipo = previoustypeWalker;
        peaton.y = peaton.y + 1;
        previoustypeWalker = laberinto.celdas[peaton.x][peaton.y].tipo;  
        laberinto.celdas[peaton.x][peaton.y].tipo= 'W';
        
        if(peaton.y < celdaDestino){
            for(int i = 0; i < 9; i++){
            laberinto.celdas[peaton.x][peaton.y].indexWalkerSprite = cont;
            cont = cont+4;
            
            if(cont == 8)
                cont = 0;
        }
        }
        
        
    }
}

public void moverPeatonDerecha(int celdaDestino){
    if(peaton.x < anchuraMundoVirtual && peaton.x < celdaDestino && laberinto.celdas[peaton.x+1][peaton.y].tipo != 'P'){
        laberinto.celdas[peaton.x][peaton.y].tipo = previoustypeWalker;
        peaton.x = peaton.x + 1;
        previoustypeWalker = laberinto.celdas[peaton.x][peaton.y].tipo;  
        laberinto.celdas[peaton.x][peaton.y].tipo= 'W';
        laberinto.celdas[peaton.x][peaton.y].indexWalkerSprite = 2;
        
    }
}

public void moverPeatonArriba(int celdaDestino){
    if(peaton.y > 0 && peaton.y > celdaDestino && laberinto.celdas[peaton.x][peaton.y-1].tipo != 'P'){
        laberinto.celdas[peaton.x][peaton.y].tipo = previoustypeWalker;
        peaton.y = peaton.y - 1;
        previoustypeWalker = laberinto.celdas[peaton.x][peaton.y].tipo;
        laberinto.celdas[peaton.x][peaton.y].tipo= 'W';
        laberinto.celdas[peaton.x][peaton.y].indexWalkerSprite = 3;
        
    }
}
    

@Override
public void run() {
    moverPeaton1();
    moverPeaton2();
    moverPeaton3();
    laberinto.lienzoPadre.repaint();
}
}
