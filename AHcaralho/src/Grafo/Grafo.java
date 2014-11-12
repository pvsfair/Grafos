
package Grafo;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Kae
 */
public class Grafo {

    private ArrayList<Vertice> vertices;
    private int[][] matAdj;

    public Grafo() {
        this.vertices = new ArrayList();
    }

    public static Grafo criaGrafo() {
        Grafo grafo = new Grafo();
        int nVert = Integer.parseInt(JOptionPane.showInputDialog(null, "Defina a quantidade de vértices que possui o Grafo: "));
        if (nVert > 0) {
            grafo.vertices.ensureCapacity(nVert);
            for (int i = 0; i < nVert; i++) {
                Integer nome = i + 1;
                Vertice novo = new Vertice(nome.toString());
                grafo.vertices.add(novo);
            }
        } else {
            System.out.println("Valor Inválido");
        }
        System.out.println();
        String resposta = JOptionPane.showInputDialog(null, "Esse grafo é direcionado?<s/n>");
        if (resposta.equalsIgnoreCase("s")) {  //se for direcionado
            for (int i = 0; i < nVert; i++) {
                for (int j = 0; j < nVert; j++) {
                    if (i == j) {
                    } else {
                        int retorno = Integer.parseInt(JOptionPane.showInputDialog(null, "Peso entre os vertices " + (i + 1) + "-" + (j + 1) + ":"));
                        if (retorno != 0) {
                            grafo.vertices.get(i).addAdj(grafo.vertices.get(j));  //Add vertice adjacente
                            Aresta nova = new Aresta(grafo.vertices.get(i), grafo.vertices.get(j), retorno); //criando aresta
                            grafo.vertices.get(i).getArestas().add(nova);  //Adicionando aresta nova
                        }
                    }
                }
            }
        } else {  //se não for direcionado
            for (int i = 0; i < nVert; i++) {
                for (int j = 0; j < nVert; j++) {
                    if (i == j) {
                    } else if (i < j) {
                        int retorno = Integer.parseInt(JOptionPane.showInputDialog(null, "Peso entre os vertices " + (i + 1) + "-" + (j + 1) + ":"));
                        if (retorno != 0) {
                            grafo.vertices.get(i).addAdj(grafo.vertices.get(j));  //Add vertice adjacente
                            Aresta nova = new Aresta(grafo.vertices.get(i), grafo.vertices.get(j), retorno); //criando aresta
                            grafo.vertices.get(i).getArestas().add(nova);  //Adicionando aresta nova
                            grafo.vertices.get(j).addAdj(grafo.vertices.get(i));  //Add vertice adjacente
                            nova = new Aresta(grafo.vertices.get(j), grafo.vertices.get(i), retorno);
                            grafo.vertices.get(j).getArestas().add(nova);  //Adicionando aresta nova
                        }
                    }
                }
            }
        }

        return grafo;
    }

    public static int[][] geraMatriz(Grafo gr) {
        System.out.println("Insira sua matriz adjacente:");
        int size = gr.getVertices().size();
        int[][] matriz = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int peso = 0;
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    for (Aresta a : gr.getVertices().get(i).getArestas()) {
                        if (a.getvDestino().getRotulo().equals("" + (j + 1))) {
                            peso = a.getPeso();
                        }
                    }
                    matriz[i][j] = peso;
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

    public static boolean checaConexo(Grafo gr) {
        for (Vertice vertice : gr.vertices) {
            System.out.println(vertice.getRotulo());
            if (vertice.getArestas().isEmpty()) {
                System.out.println("O grafo não é conexo.");
                return false;
            }
        }
        System.out.println("O grafo é conexo");
        return true;
    }

    public static boolean checaCilco(Grafo gr) {
        boolean ret = false;
        for (Vertice vertice : gr.getVertices()) {
            for (int i = 0; i < vertice.getArestas().size(); i++) {
                ret = visitarVizinho(vertice, vertice.getArestas().get(i).getvDestino(), vertice.getArestas().get(i));
                if (ret) {
                    return ret;
                }
            }
        }
        return ret;
    }

    private static boolean visitarVizinho(Vertice vOrigem, Vertice vAtual, Aresta aPassada) {
        System.out.println("vO = " + vOrigem.getRotulo() + " | vA = " + vAtual.getRotulo() + " | Aresta = " + aPassada.toString());
        if (vOrigem.equals(vAtual)) {
            return true;
        }
        for (Aresta aresta : vAtual.getArestas()) {
            if (!aresta.getvDestino().equals(aPassada.getvOrigem())) {
                return visitarVizinho(vOrigem, aresta.getvDestino(), aresta);
            }
        }
        return false;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public int[][] getMatAdj() {
        return matAdj;
    }

    public void setMatAdj(int[][] matAdj) {
        this.matAdj = matAdj;
    }

}
