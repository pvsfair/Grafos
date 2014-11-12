/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArvoreGeradora;

import java.util.ArrayList;

/**
 *
 * @author Kae
 */
public class Prim {
     public static void algoritmoPrim(int[][] matrizAdj) {
        ArrayList<Integer> T = new ArrayList<>();
        ArrayList<Integer> V = new ArrayList<>();
        ArrayList<Integer> Tmin = new ArrayList<>();
        int min = 1000000;
        for (int i = 0; i < matrizAdj.length; i++) {
            V.add(i);
        }

        /*algoritmo prim
         for (int i = 0; i < matrizAdj.length; i++) {
         min = 10000;
         for (int j = 0; j < matrizAdj.length; j++) {
         if (i < j) {
         for (int k = 0; k < matrizAdj.length; k++) {
         if(matrizAdj[j][k]!= 0){
         if (min > matrizAdj[j][k]) {
         min = matrizAdj[j][k];
         int x = i;
         int y = j;
         System.out.println("De " + x + " -->" + y + " com peso: " + min);
         }
         }
         }
         }
         }
         }*/
        int size = V.size();
        int vertice = 0;
        T.add(vertice);
        try {
            while (V.size() != 0) {
                for (int i = 0; i < matrizAdj.length; i++) {
                    for (int j = 0; j < matrizAdj.length; j++) {
                        if (matrizAdj[i][j] != 0) {
                            if (matrizAdj[i][j] < min) {
                                System.out.println("i:" + i + " j: " + j);
                                min = matrizAdj[i][j];
                                vertice = i;
                                System.out.println("vertice:" + vertice + " Min:" + min);
                            }
                        }
                        T.add(vertice);
                    }

                }

            }
        } catch (Exception e) {
            System.out.println("foda-se");
        }

    }
}
