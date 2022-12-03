
package lab9p1_kelvinmelgar;

/**
 *
 * @author kelvi
 */

import java.util.ArrayList;
public class Game {
    
    ArrayList<String> coordenadas = new ArrayList<>();
    int[][] tablero;
    int[][] nextTablero;
    
    Game(){
        
    }
    
    public void setTablero(int[][] tablero){
        this.tablero = tablero;
        
    }
    
    public void setnextTablero(int[][] nextTablero){
        this.nextTablero = nextTablero;
    }
    
    public void setCoordenadas(ArrayList<String> coordenadas){
        this.coordenadas = coordenadas;
    }
    
    public int[][] getTablero(){
        return tablero;
    }
    
    public int[][] getNextTablero(){
        return nextTablero;
    }
    
    public ArrayList<String> getCoordenadas(){
        return coordenadas;
    }
    
    
    //metodos para el juego
    public void jugar(int x){
        
        for (int i = 0; i < x; i++) {
            
            System.out.println("Ronda #" + (i+1) );
            
            System.out.println("-------------------------------------------");
            
            nextGen();
            
            System.out.println("Coordenadas de celulas vivas: ");
            
            //imprime filas de coordenadas por si la la lista es muy larga
            for (int j = 0; j < this.getCoordenadas().size(); j++) {
                
                if(j > 0 && j%5 == 0)
                    System.out.println();
                System.out.print("("+this.getCoordenadas().get(j)+") ");
                
            }
            System.out.println("\n");
            
            
            Print(this.getCoordenadas());
            
            
            System.out.println("-------------------------------------------");

        }
        
        
    }
    
    
    public void nextGen(){
        
        int[][] tablero = this.getTablero();
        //asigno a nextTablero los valores del tablero para modificarlos solamente
        int[][] nextTablero = tablero;
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                //no toma los valores del alrededor
                if(!(i == 0 || j == 0 || tablero.length - i == 1 || tablero[0].length - j == 1)){
                    
                    //llama al metodo de la clase misma para evaluar las celulas vivas
                    //para que se vea un poco mas organizado :)
                    int celulasV = this.evaluarCelula(i, j);
                    
                    if(tablero[i][j] == 1){
                        if(celulasV < 2)
                            nextTablero[i][j] = 0;
                        if(celulasV > 3)
                            nextTablero[i][j] = 0;
                    } else{
                        if(celulasV == 3)
                            nextTablero[i][j] = 1;
                    }
                    
                }
            }
            
        }
        
        
        //actualiza la lista de coordenadas de celulas vivas
        ArrayList<String> temp = new ArrayList<>();
        
        for (int i = 0; i < nextTablero.length; i++) {
            
            for (int j = 0; j < nextTablero.length; j++) {
                
                if(nextTablero[i][j] == 1){
                    temp.add(String.format("%d:%d", i, j));
                }
                
            }
        }
        
        //setea las variables de la clase
        this.setCoordenadas(temp);
        int[][] tem = tablero;
        this.setTablero(nextTablero);
        this.setnextTablero(tem);
        
        
        
        
        
    }// fin de nextGen
    
    
    public void Print(ArrayList<String> k) {
        
        //matriz para las coordenadas donde estan las celulas vivas
        int[][] temp = new int[10][10];

        for (int i = 0; i < k.size(); i++) {
            //obtiene las coordenadas del indice
            String[] coord = k.get(i).split(":");

            int x = Integer.parseInt(coord[0]);
            int y = Integer.parseInt(coord[1]);
            
            //asigna en esa coordenada 1
            temp[x][y] = 1;
        }
        
        //imprime la matriz
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                System.out.print(temp[i][j] + " ");
            }
            
            System.out.println();
        }
    }

    
    public int evaluarCelula(int i, int j) {

        //contador de celulas vivas al rededor
        int celulasV = 0;
        
        //Recorre alredor de la celula actual;
        for (int k = 0; k < 3; k++) {

            for (int l = 0; l < 3; l++) {

                switch (k) {
                    //fila abajo del elemento
                    case 0: {
                        switch (l) {
                            case 0 ->  {
                                //elemento a la izquierda
                                if(this.getTablero()[i - 1][j - 1] == 1)
                                    celulasV++;
                                break;
                            }

                            case 1 ->  {
                                //elemento en la misma posicion de y del acrual
                                if(this.getTablero()[i - 1][j] == 1)
                                    celulasV++;
                                break;
                            }

                            case 2 ->  {
                                //elemento a la derecha del actual
                                if(this.getTablero()[i - 1][j + 1] == 1)
                                    celulasV++;
                                break;
                            }
                        }

                        break;
                    }

                    //misma fila
                    case 1: {
                        switch (l) {
                            case 0 ->  {
                                if(this.getTablero()[i][j - 1] == 1)
                                    celulasV++;
                                break;
                            }

                            case 2 ->  {
                                if(this.getTablero()[i][j + 1] == 1)
                                    celulasV++;
                                break;
                            }
                        }

                        break;
                    }

                    //fila arriba
                    case 2: {
                        switch (l) {
                            case 0 ->  {
                                if(this.getTablero()[i + 1][j - 1] == 1)
                                    celulasV++;
                                break;
                            }

                            case 1 ->  {
                                if(this.getTablero()[i + 1][j] == 1)
                                    celulasV++;
                                break;
                            }

                            case 2 ->  {
                                if(this.getTablero()[i + 1][j + 1] == 1)
                                    celulasV++;
                                break;
                            }
                        }

                        break;
                    }

                }

            }

        }// fin del recorrido
        
        return celulasV;
    }
}
