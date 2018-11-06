package ia;

import java.util.Random;


public interface Constantes {    
        public final int anchuraCelda = 32;
        public final int alturaCelda = 32;
        public final int anchuraMundoVirtual = 41;
        public final int alturaMundoVirtual = 21;
        public final int pasajerosAlInicio = 6;
        public final int Cartas = 4;
        
        public final boolean busquedaSegura = false; // true  == Sólo por pasos de cebra
                                                     // false == Por cualquier lugar, incluídas las calles de vehículos.
        
        
        //Tipos de celdas
        
                public final char PLAYER = 'P';
                public final char ROAD = 'R';
                public final char STREET = 'S';
                public final char CAR = 'C';
                public final char HOUSE = 'H';
                public final char BUILDING = 'B';
                public final char WALKER = 'W';
                public final char MAILBOX = 'M';
                public final char CROSSWALKH = 'K';
                public final char BUS = 'O';
                public final char BUSSTOP = 's';
                public final char GRASS = 'G';
                
                default int numeroAleatorio(int minimo, int maximo) {
                    Random random = new Random();
                        int numero_aleatorio = random.nextInt((maximo - minimo) + 1) + minimo;
                        return numero_aleatorio;
}

                
}
