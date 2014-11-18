package main;

import Grafo.Grafo;
import Grafo.Vertice;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kae
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grafo grafo = Grafo.criaGrafo();
        int[][] geraMatriz = Grafo.geraMatriz(grafo);
        imprimeMatriz(geraMatriz);
        System.out.println(Grafo.checaConexo(grafo));
        if (Grafo.checaCilco(grafo)) {
            System.out.println("tem cilco");
        } else {
            System.out.println("nao tem cilco");
        }
    }

    public static void imprimeMatriz(int[][] matriz) {
        System.out.println("Size: " + matriz.length);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print("   " + matriz[i][j]);
            }
            System.out.println();
        }
    }
//    public static void visitarVizinhos(int vertice, int verticesVisitados[], int grafo[][]) {
//        verticesVisitados[vertice] = 1;
//        for (int i = 0; i < grafo.length; i++) {            
//            if (grafo[vertice][i] != 0 && verticesVisitados[i] == 0) {               
//                visitarVizinhos(i, verticesVisitados, grafo);
//                vertice++;
//            }
//        }
//    }
//
//    public static boolean alcancavel(int vertice1, int vertice2, int grafo[][]) {
//        int verticesVisitados[] = new int[grafo.length];
//        for (int i = 0; i < grafo.length; i++) {
//            verticesVisitados[i] = 0;
//        }
//        boolean alcanca = false;
//        visitarVizinhos(vertice1, verticesVisitados, grafo);
//        for (int i : verticesVisitados) {
//            System.out.print(i + ",");
//        }
//        alcanca = (verticesVisitados[vertice2] == 2) ? true : false;
//
//        return alcanca;
//    }

}
