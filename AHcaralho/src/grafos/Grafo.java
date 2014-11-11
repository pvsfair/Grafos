/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import grafos.GUI.LeituraGrafo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

/**
 *
 * @author Kae
 */
public class Grafo {

    public static Graph criaGrafo() {
        int[] grafo = new int[2];
        Graph gr = new Graph();
        Scanner input = new Scanner(System.in);
//        LeituraGrafo GUI = new LeituraGrafo();
        do {
            System.out.println("Digite o grafo de entrada.");
            System.out.println("Representação- G=(N,M)");
            JOptionPane.showMessageDialog(null, "Digite o grafo de entrada.\nRepresentação - G=(N,M)\n", "Instruções", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null, "Atenção!.\nPara representar as arestas inexistentes no nosso\nprograma, identifique-as como tendo peso = 0.", "Instruções", JOptionPane.WARNING_MESSAGE);
            System.out.print("N - Vértices:");
            grafo[0] = Integer.parseInt(JOptionPane.showInputDialog(null, "N - Vértices\nInsira o número de Vértices:"));
            System.out.print("M- Arestas:");
            grafo[1] = Integer.parseInt(JOptionPane.showInputDialog(null, "M- Arestas\nInsira o número de Arestas:"));
            if (grafo[0] <= 0 || grafo[1] < 0) {
                System.out.println("Valor inválido. Digite números maiores que zero.");
            }
        } while (grafo[0] <= 0 || grafo[1] < 0);
        int[][] matriz = MatrizAdj.geraMatriz(grafo[0]);
        //teste se tem ciclo
        boolean retorno = alcancavel(0, 0, matriz);
        System.out.println("Tem cilco? \n" + retorno);

        //cria o desenho do grafo
        for (int i = 0; i < matriz.length; i++) {
            Node a = gr.addNode();
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    gr.addEdge(gr.getNode(i), gr.getNode(j));

                }
            }
        }

//        algoritmoDjikstra(matriz);
        return gr;

    }

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

    public static void visitarVizinhos(int vertice, int verticesVisitados[], int grafo[][]) {
        verticesVisitados[vertice] = 1;
        for (int i = 0; i < grafo.length; i++) {
            if (verticesVisitados[i] == vertice) {
                //paramos aqui
            } else if (grafo[vertice][i] != 0 && verticesVisitados[i] == 0) {
                visitarVizinhos(i, verticesVisitados, grafo);
            }
        }

    }

    public static boolean alcancavel(int vertice1, int vertice2, int grafo[][]) {
        int verticesVisitados[] = new int[grafo.length];
        for (int i = 0; i < grafo.length; i++) {
            verticesVisitados[i] = 0;
        }
        boolean alcanca = false;
        visitarVizinhos(vertice1, verticesVisitados, grafo);
        for (int i : verticesVisitados) {
            System.out.println(i);
        }
        alcanca = (verticesVisitados[vertice2] == 2) ? true : false;

        return alcanca;
    }
}
