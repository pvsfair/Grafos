package Grafo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kae
 */
public class Vertice {

    private String rotulo;
    private ArrayList<Aresta> arestas;
    private boolean marcado = false;
    private Color cor;

    public Vertice(String rotulo, ArrayList<Aresta> arestas) {
        this.rotulo = rotulo;
        this.arestas = arestas;
        cor = Color.LIGHT_GRAY;
    }

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.arestas = new ArrayList<Aresta>();
        cor = Color.LIGHT_GRAY;
    }
    public Vertice (Vertice vert){
        this.rotulo = vert.rotulo;
        this.cor = vert.cor;
        for (Aresta arest : vert.arestas) {
            this.arestas.add(arest);
        }
    }
    
    public void addAresta(Aresta nova){
        this.arestas.add(nova);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertice other = (Vertice) obj;
        if (!Objects.equals(this.rotulo, other.rotulo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rotulo;
    }
    
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
    
    
    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }


    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }
}
