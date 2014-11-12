/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArvoreGeradora;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kae
 */
public class Djikstra {
    public static void algoritmoDjikstra(int[][] MatrizAdj) {
        int size = MatrizAdj.length;
        int[] origem = new int[size];
        int[] dist = new int[size];

        for (int i = 0; i < size; i++) {
            origem[i] = -1;
            dist[i] = 1000000000;
        }

        origem[0] = 0;
        dist[0] = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (MatrizAdj[i][j] != 0) {
                    if (MatrizAdj[i][j] + dist[i] < dist[j] && origem[j] != i) {
                        origem[j] = i;
                        dist[j] = MatrizAdj[i][j] + dist[i];
                    }
                }
            }
        }

        int x = origem.length - 1;

        List<Integer> saida = new ArrayList<Integer>();
        System.out.println("Melhor caminho segundo algoritmo de Djikstra.");
        saida.add(x);
        do {
            x = origem[x];
            saida.add(x);
        } while (origem[x] != 0);
        saida.add(origem[x]);
        System.out.println("\n\n");
        for (int i = saida.size() - 1; i >= 0; i--) {

            System.out.print(saida.get(i) + "--");
        }

    }
}
