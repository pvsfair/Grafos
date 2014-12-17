package Grafo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

/**
 *
 * @author Kae
 */
public class Grafo {

    private ArrayList<Vertice> vertices;
    private int[][] matAdj = null;
    private Color[] vetorCores = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED, Color.YELLOW, Color.PINK};
    private boolean direcionado = false;

    public Grafo() {
        this.vertices = new ArrayList();
    }

    private Grafo(Grafo gr) {
        for (Vertice vert : gr.getVertices()) {
            this.vertices.add(new Vertice(vert));
        }
    }

    public static void colorirGrafo(Grafo gr) {
        for (Vertice vert : gr.getVertices()) {
            int i = 0;
            for (Aresta aresta : vert.getArestas()) {
                if (aresta.getvDestino().getCor() == gr.vetorCores[i]) {
                    i++;
                }
            }
            vert.setCor(gr.vetorCores[i]);
        }
    }

    public static ArrayList<Vertice> ordTop(Grafo gr) {
        //Grafo grafo = new Grafo(gr);

        ArrayList<Vertice> order = new ArrayList<Vertice>();
        if (Grafo.checaCilco(gr)) {
            System.out.println("Nao e possivel obter uma ordenacao topologica, pois este grafo possui ciclo(s)");
        }
        for (Vertice v : gr.vertices) {
            if (!v.isMarcado()) {
                DFS(v, order);
            }
        }
        for (Vertice v : gr.vertices) {
            v.setMarcado(false);
        }
        return order;

    }

    private static void DFS(Vertice v, ArrayList<Vertice> l) {
        v.setMarcado(true);

        for (Aresta aresta : v.getArestas()) {
            if (!aresta.getvDestino().isMarcado()) {
                DFS(aresta.getvDestino(), l);
            }
        }

        l.add(0, v);
    }

    public static void desenhaGrafo(final Grafo gr) {

        JGraphModelAdapter<String, DefaultEdge> jgAdapter;

        ListenableGraph<String, DefaultEdge> g;
        if (gr.direcionado) {
            g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        } else {
            g = new ListenableUndirectedGraph<String, DefaultEdge>(
                    DefaultEdge.class);
        }

        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);

        jgraph.setPreferredSize(new Dimension(500, 500));

        for (Vertice vert : gr.getVertices()) {
            g.addVertex(vert.getRotulo());
            Grafo.positionVertexAt(vert.getRotulo(), (int) (Math.random() * 480),
                    (int) (Math.random() * 480), vert.getCor(), jgAdapter);
        }
        for (Vertice vert : gr.getVertices()) {
            for (Aresta ar : vert.getArestas()) {
                g.addEdge(ar.getvOrigem().getRotulo(), ar.getvDestino().getRotulo());
            }
        }

        JFrame frame = new JFrame();
        frame.getContentPane().add(jgraph);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private static void positionVertexAt(Object vertex, int x, int y, Color cor, JGraphModelAdapter<String, DefaultEdge> jgAdapter) {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();

//        GraphConstants.setMoveable(attr, false);
        GraphConstants.setDisconnectable(attr, false);
        GraphConstants.setEditable(attr, false);
        GraphConstants.setSizeable(attr, false);

        GraphConstants.setBackground(attr, cor);
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds
                = new Rectangle2D.Double(
                        x,
                        y,
                        bounds.getHeight(),
                        bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
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
            grafo.direcionado = true;
            for (int i = 0; i < nVert; i++) {
                for (int j = 0; j < nVert; j++) {
                    if (i == j) {
                    } else {
                        int retorno = Integer.parseInt(JOptionPane.showInputDialog(null, "Peso entre os vertices " + (i + 1) + "-" + (j + 1) + ":"));
                        if (retorno != 0) {
                            Aresta nova = new Aresta(grafo.vertices.get(i), grafo.vertices.get(j), retorno); //criando aresta
                            grafo.vertices.get(i).getArestas().add(nova);  //Adicionando aresta nova
                        }
                    }
                }
            }
        } else {  //se não for direcionado
            grafo.direcionado = false;
            for (int i = 0; i < nVert; i++) {
                for (int j = 0; j < nVert; j++) {
                    if (i == j) {
                    } else if (i < j) {
                        int retorno = Integer.parseInt(JOptionPane.showInputDialog(null, "Peso entre os vertices " + (i + 1) + "-" + (j + 1) + ":"));
                        if (retorno != 0) {
                            Aresta nova = new Aresta(grafo.vertices.get(i), grafo.vertices.get(j), retorno); //criando aresta
                            grafo.vertices.get(i).getArestas().add(nova);  //Adicionando aresta nova
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
//        System.out.println("vO = " + vOrigem.getRotulo() + " | vA = " + vAtual.getRotulo() + " | Aresta = " + aPassada.toString());
        if (vOrigem.equals(vAtual)) {
            return true;
        }
        boolean a = false;
        for (Aresta aresta : vAtual.getArestas()) {
            if (!aresta.getvDestino().equals(aPassada.getvOrigem())) {
                a = visitarVizinho(vOrigem, aresta.getvDestino(), aresta);
            }
        }
        return a;
    }

    public static Grafo retornaCilco(Grafo gr) {
        Grafo sailda = new Grafo();
        for (Vertice vertice : gr.getVertices()) {
            sailda = new Grafo();
            sailda.vertices.add(vertice);
            for (int i = 0; i < vertice.getArestas().size(); i++) {
                sailda = achaCilco(vertice, vertice.getArestas().get(i).getvDestino(), vertice.getArestas().get(i), sailda);
                if (sailda != null) {
                    return sailda;
                }
            }
        }
        return sailda;
    }

    private static Grafo achaCilco(Vertice vOrigem, Vertice vAtual, Aresta aPassada, Grafo gNovo) {
        System.out.println("vO = " + vOrigem.getRotulo() + " | vA = " + vAtual.getRotulo() + " | Aresta = " + aPassada.toString());
        if (vOrigem.equals(vAtual)) {
            gNovo.buscaVertice(aPassada.getvOrigem().getRotulo()).addAresta(aPassada);
            gNovo.buscaVertice(vAtual.getRotulo()).addAresta(aPassada.retornaInverso());
            return gNovo;
        }
        Grafo g = null;
        for (Aresta aresta : vAtual.getArestas()) {
            if (!aresta.getvDestino().equals(aPassada.getvOrigem())) {
                Vertice vNovo = new Vertice(vAtual.getRotulo());
                vNovo.addAresta(aresta.retornaInverso());
                gNovo.vertices.add(vNovo);
                gNovo.buscaVertice(aPassada.getvOrigem().getRotulo()).addAresta(aresta);
                g = achaCilco(vOrigem, aresta.getvDestino(), aresta, gNovo);
            }

        }
        return g;
    }

    public Vertice buscaVertice(String rotulo) {
        for (Vertice vertice : this.getVertices()) {
            if (vertice.getRotulo().equals(rotulo)) {
                return vertice;
            }
        }

        return null;

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
