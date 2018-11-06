package ia;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TimerTask;
public class BusquedaInformada extends TimerTask implements Constantes{
public Laberinto laberinto;
public ArrayList<Estado> destinos;
public Queue<Estado> colaEstados;
public ArrayList<Estado> historial;
public ArrayList<Character> pasos;
public int index_pasos;
public Estado inicial;
public Estado objetivo;
public Estado temp;
public boolean exito;
public BusquedaInformada(Laberinto laberinto) {
this.laberinto=laberinto;
colaEstados=new PriorityQueue<>();
historial=new ArrayList<>();
pasos=new ArrayList<>();
index_pasos=0;
exito=false;
}
public void buscar(Estado inicial,Estado objetivo) {

inicial.prioridad=distancia(inicial.x,inicial.y,laberinto.lienzoPadre.jugador.celdaMovimiento.x,laberinto.lienzoPadre.jugador.celdaMovimiento.y);

colaEstados.add(inicial);
historial.add(inicial);
if ( inicial.equals(objetivo)) exito=true;
while ( !colaEstados.isEmpty() && !exito ){
temp=colaEstados.poll();
moverArriba(temp);
moverAbajo(temp);
moverIzquierda(temp);
moverDerecha(temp);
}
if ( exito ) System.out.println("Ruta calculada");
else System.out.println("La ruta no pudo calcularse");
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
if ( laberinto.celdas[e.x][e.y-1].tipo != 'O' ) {
Estado arriba=new Estado(e.x,e.y-1,'U',e);
arriba.prioridad=distancia(arriba.x,arriba.y,
laberinto.lienzoPadre.jugador.celdaMovimiento.x,
laberinto.lienzoPadre.jugador.celdaMovimiento.y);
if ( !historial.contains(arriba)) {
colaEstados.add(arriba);
historial.add(arriba);
if ( arriba.equals(objetivo)) {
objetivo=arriba;
exito=true;
}
}
}
}
}
private void moverAbajo(Estado e) {
if ( e.y+1 < alturaMundoVirtual ) {
if ( laberinto.celdas[e.x][e.y+1].tipo != 'O' ) {
Estado abajo=new Estado(e.x,e.y+1,'D',e);
abajo.prioridad=distancia(abajo.x,abajo.y,
laberinto.lienzoPadre.jugador.celdaMovimiento.x,
laberinto.lienzoPadre.jugador.celdaMovimiento.y);
if ( !historial.contains(abajo)) {
colaEstados.add(abajo);
historial.add(abajo);
//laberinto.celdas[e.x][e.y+1].tipo=’A’;
if ( abajo.equals(objetivo)) {
//laberinto.celdas[e.x][e.y+1].tipo=’P’;
objetivo=abajo;
exito=true;
}
}
}
}
}
private void moverIzquierda(Estado e) {
if ( e.x > 0 ) {
if ( laberinto.celdas[e.x-1][e.y].tipo != 'O' ) {
Estado izquierda=new Estado(e.x-1,e.y,'L',e);
izquierda.prioridad=distancia(izquierda.x,izquierda.y,
laberinto.lienzoPadre.jugador.celdaMovimiento.x,
laberinto.lienzoPadre.jugador.celdaMovimiento.y);
if ( !historial.contains(izquierda)) {
colaEstados.add(izquierda);
historial.add(izquierda);
if ( izquierda.equals(objetivo)) {
objetivo=izquierda;
exito=true;
}
}
}
}
}
private void moverDerecha(Estado e) {
if ( e.x < anchuraMundoVirtual-1 ) {
if ( laberinto.celdas[e.x+1][e.y].tipo != 'O' ) {
Estado derecha=new Estado(e.x+1,e.y,'R',e);
derecha.prioridad=distancia(derecha.x,derecha.y,
laberinto.lienzoPadre.jugador.celdaMovimiento.x,
laberinto.lienzoPadre.jugador.celdaMovimiento.y);
if ( !historial.contains(derecha)){
colaEstados.add(derecha);
historial.add(derecha);
if ( derecha.equals(objetivo)) {
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
public synchronized void run() {
if ( index_pasos >= 0 ) {
switch(pasos.get(index_pasos)) {
case 'D':
laberinto.lienzoPadre.jugador.moverCeldaAbajo();
break;
case 'U':
laberinto.lienzoPadre.jugador.moverCeldaArriba();
break;
case 'R':
laberinto.lienzoPadre.jugador.moverCeldaDerecha();
break;
case 'L': laberinto.lienzoPadre.jugador.moverCeldaIzquierda();
break;
}
laberinto.lienzoPadre.repaint();
index_pasos--;
}else {
this.cancel();
}
}
}