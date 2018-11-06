package ia;

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Celda extends JComponent implements Constantes {
        //posicion x e y de la Celda
        public int x;
        public int y;
        public char tipo;
        public int carID, walkerID;
        public Rectangle rectanguloCelda;
        public BufferedImage player, mailbox , roadv, roadh, street, car, house, building, walker, crosswalkh, bus, stop, grass;
        public int indexPlayerSprite, indexCarSprite, indexWalkerSprite;
        public BufferedImage playerSprites[],imagenPlayerSprites, carSprites[],imagenCarSprites, walkerSprites[],imagenWalkerSprites;
        


//        public boolean celdaSeleccionada;
//        public Rectangle rectanguloCelda;
        
        //constructor
        public Celda(int x,int y, char tipo, int carID, int walkerID) {
        this.x=x;
        this.y=y;
        this.tipo = tipo;
        this.carID = carID;
        this.walkerID = walkerID;
        
        indexPlayerSprite = 0; 
        indexCarSprite = 0;
        indexWalkerSprite = 0;        
        rectanguloCelda = new Rectangle(x,y,anchuraCelda,alturaCelda);
            try {
            player = ImageIO.read(new File("src\\ia\\imagenes\\Player.png"));
            mailbox = ImageIO.read(new File("src\\ia\\imagenes\\Mailbox.png"));
            roadv = ImageIO.read(new File("src\\ia\\imagenes\\Road-v.png"));
            roadh = ImageIO.read(new File("src\\ia\\imagenes\\Road-h.png"));
            street = ImageIO.read(new File("src\\ia\\imagenes\\Street.png"));
            car = ImageIO.read(new File("src\\ia\\imagenes\\Car.png"));
            house = ImageIO.read(new File("src\\ia\\imagenes\\House.png"));
            building = ImageIO.read(new File("src\\ia\\imagenes\\Building.png"));
            walker = ImageIO.read(new File("src\\ia\\imagenes\\Walker.png"));
            crosswalkh = ImageIO.read(new File("src\\ia\\imagenes\\Crosswalk-h.png"));
            bus = ImageIO.read(new File("src\\ia\\imagenes\\Bus.png"));
            stop = ImageIO.read(new File("src\\ia\\imagenes\\Stop.png"));
            grass = ImageIO.read(new File("src\\ia\\imagenes\\Grass.png"));

            //gestion de sprites
            //cargo la imagen de grupo de imagenes
            imagenPlayerSprites = ImageIO.read(new File("src\\ia\\imagenes\\PlayerSprite.png"));
            imagenCarSprites = ImageIO.read(new File("src\\ia\\imagenes\\CarSprite.png"));
            imagenWalkerSprites = ImageIO.read(new File("src\\ia\\imagenes\\WalkerSprite.png"));
            
            //creo una array de 4 x 3
            playerSprites = new BufferedImage[4 * 3];
            //lo recorro separando las imagenes
            for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
            playerSprites[(i * 4) + j] =
            imagenPlayerSprites.getSubimage(i * anchuraCelda, j * alturaCelda, anchuraCelda, alturaCelda);
                                        }
                                        }
            
            //creo una array de 4 x 3
            carSprites = new BufferedImage[4 * 3];
            //lo recorro separando las imagenes
            for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
            carSprites[(i * 4) + j] =
            imagenCarSprites.getSubimage(i * anchuraCelda, j * alturaCelda, anchuraCelda, alturaCelda);
            }
            }
            
            //creo una array de 4 x 3
            walkerSprites = new BufferedImage[4 * 3];
            //lo recorro separando las imagenes
            for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
            walkerSprites[(i * 4) + j] =
            imagenWalkerSprites.getSubimage(i * anchuraCelda, j * alturaCelda, anchuraCelda, alturaCelda);
            }
            }
            
            
            
            } catch (IOException e) {
            System.out.println(e.toString());
            }
        
        }
//metodo para dibujar celda, hace uso de drawRect
@Override
   public void update(Graphics g) {
            switch(tipo) {
                case 'P': g.drawImage(playerSprites[indexPlayerSprite],x,y, null); 
                break;
                case 'M': g.drawImage(mailbox,x,y, null); 
                break;
                case 'R': g.drawImage(roadv,x,y, null);  //R = Vertical
                break;
                case 'r': g.drawImage(roadh,x,y, null); // r = Horizontal
                break;
                case 'S': g.drawImage(street,x,y, null); 
                break;
                case 'C': g.drawImage(carSprites[indexCarSprite],x,y, null); 
                break;
                case 'H': g.drawImage(house,x,y, null); 
                break;
                case 'B': g.drawImage(building,x,y, null); 
                break;
                case 'W': g.drawImage(walkerSprites[indexWalkerSprite],x,y, null); 
                break;
                case 'K': g.drawImage(crosswalkh,x,y, null); 
                break;
                case 'O': g.drawImage(bus,x,y, null); 
                break;
                case 's': g.drawImage(stop,x,y, null); 
                break;
                case 'G': g.drawImage(grass,x,y, null); 
                break;
            }
            }
   @Override
    public void paintComponent(Graphics g) {
    update(g);
                                           }

    //si el click esta sobre la celda
    //si el click esta sobre la celda
    public boolean celdaSeleccionada(int xp,int yp) {
        return rectanguloCelda.contains(new Point(xp,yp));
    }
}