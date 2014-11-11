/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    int conta = 0;
    int[] pre;

    public static Graph criaGrafo() {
        int[] grafo = new int[2];
        Graph gr = new Graph();
        Scanner input = new Scanner(System.in);
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
//<<<<<<< OURS

//        OrdTop ordTop = new OrdTop();
//        ordTop.Ord(matriz);
     
//=======
        System.out.println("Tem cilco? \n" + retorno);
//>>>>>>> THEIRS

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


        return gr;

    }

   

    

    public static void visitarVizinhos(int vertice, int verticesVisitados[], int grafo[][]) {
        verticesVisitados[vertice] = 1;
        for (int i = 0; i < grafo.length; i++) {
            System.out.println("");
            if (grafo[vertice][i] != 0 && verticesVisitados[i] == 0) {
                System.out.println("vertice "+ vertice);
                for (int v : verticesVisitados) {
                    System.out.print(v+",");
                }
                visitarVizinhos(i, verticesVisitados, grafo);
            }
        }

    }

    public static boolean alcancavel(int vertice1, int vertice2, int grafo[][]) {
        int verticesVisitados[] = new int[grafo.length];        
        boolean alcanca = false;
        visitarVizinhos(vertice1, verticesVisitados, grafo);
        
        for (int i : verticesVisitados) {
            System.out.println(i);
        }
        alcanca = (verticesVisitados[vertice2] == 2) ? true : false;
        System.out.println("Alcança: "+ alcanca);
        System.out.println("v1 "+ vertice1);
        System.out.println("v2 "+ vertice2);
        
        return alcanca;
    }
}
