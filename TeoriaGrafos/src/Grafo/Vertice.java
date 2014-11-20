package Grafo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Kae
 */
public class Vertice {

    private String rotulo;
    private ArrayList<Aresta> arestas;
    private boolean marcado=false;

    public Vertice(String rotulo, ArrayList<Aresta> arestas) {
        this.rotulo = rotulo;
        this.arestas = arestas;
    }

    public Vertice(String rotulo) {
        this.rotulo = rotulo;
        this.arestas = new ArrayList<Aresta>();
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

    public void addAdj(Vertice adj) {

    }
}
