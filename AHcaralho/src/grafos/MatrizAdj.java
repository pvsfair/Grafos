package grafos;

import java.util.Scanner;



public class MatrizAdj {    
    
    public static int[][] geraMatriz(int size) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira sua matriz adjacente:");
        int[][] matriz = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else if (matriz[j][i] != 0) {
                } else {
                    System.out.println("Peso entre os vertices " + (i + 1) + "-" + (j + 1) + ":");
                    matriz[i][j] = input.nextInt();
                    matriz[j][i] = matriz[i][j];
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("   " + matriz[i][j]);
            }
            System.out.println("\n");
        }
        return matriz;

    }
     
  public static int [][] matrizBase()   {
      int[][] matriz = { 
          {0,9,1,8,0,0},
          {9,0,2,0,3,0},
          {1,2,0,6,4,0},
          {8,0,6,0,0,7},
          {0,3,4,0,0,5},
          {0,0,0,7,5,0}
      };
         imprimeMatriz(matriz);
         return matriz;
   
            
      
  }  
  public static void imprimeMatriz(int[][] matriz){
      for (int i = 0; i < matriz.length; i++) {
           for (int j = 0; j < matriz.length; j++) {
               System.out.print("   "+ matriz[i][j]);
           }
           System.out.println();
      }
  }
}
