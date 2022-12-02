package lab9p1_kelvinmelgar;

/**
 *
 * @author kelvi
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.security.SecureRandom;

public class Lab9P1_KelvinMelgar {

    static SecureRandom rand = new SecureRandom();
    static Scanner sc = new Scanner(System.in);
    
    static Game g = new Game();
    
    public static void main(String[] args) {
        
        int opcion;
        
        do{
            opcion = menu();
            
            switch(opcion){
                
                case 1 -> {
                    int[][] tableroInicial = llenarTablero(new int[10][10]);
                    ArrayList<String> coordenas = new ArrayList<>();

                    for (int i = 0; i < tableroInicial.length; i++) {

                        for (int j = 0; j < tableroInicial.length; j++) {

                            if(tableroInicial[i][j] == 1){
                                coordenas.add(String.format("%d:%d", i, j));
                            }
                            
                        }
                    }
                    
                    System.out.print("Ingrese el numero de rondas: ");
                    int n_rond = sc.nextInt();
                    
                    System.out.println("IMPRESION INICIAL NO CUENTA");
                    System.out.println("Coordenas de celulcas vivas Inicial: ");
                    System.out.println(coordenas);
                    //imprime la matriz
                    g.Print(coordenas);
                    
                    
                    //establece el tablero inicial
                    g.setTablero(tableroInicial);
                    
                    //inicia el juego
                    g.jugar(n_rond);
                   
                    break;
                }
                
                case 2 ->{
                    System.out.println("Saliendo...");
                    break;
                }
                
                default ->
                    System.out.println("Opcion invalida...");
                    
            }
            
        }while(opcion != 2);
        
    }
    
    
    public static int menu(){
        int opcion;
        
        String menu = """
                         <-      Laboratorio 9    -->
                         
                          1. Game of life.
                      
                          2. Salir
                        """;
        
        System.out.println(menu);
        System.out.print("Ingrese una opcion: ");
        opcion = sc.nextInt();
        
        return opcion;
    }
    
    public static int[][] llenarTablero(int[][] x){
        
        for (int i = 0; i < x.length; i++) {
            
            for (int j = 0; j < x[0].length; j++) {
                
                if(i == 0 || j == 0 || x.length - i == 1 || x[0].length - j == 1)
                    x[i][j] = 0;
                else
                    x[i][j] = rand.nextInt(2);
                
            }
        }
        
        return x;
    }
    
}
