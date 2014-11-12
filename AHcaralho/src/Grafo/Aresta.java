package Grafo;

/**
 *
 * @author Kae
 */
public class Aresta {
    private Vertice vOrigem,vDestino;
    private int peso;
    private boolean percorrido=false;

    public Aresta(Vertice vOrigem, Vertice vChegada, int peso) {
        this.vOrigem = vOrigem;
        this.vDestino = vChegada;
        this.peso = peso;
    }   
    
    public Vertice getvOrigem() {
        return vOrigem;
    }

    @Override
    public String toString() {
        return vOrigem.getRotulo() + " - " + vDestino.getRotulo();
    }

    public void setvOrigem(Vertice vOrigem) {
        this.vOrigem = vOrigem;
    }

    public Vertice getvDestino() {
        return vDestino;
    }

    public void setvDestino(Vertice vDestino) {
        this.vDestino = vDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
