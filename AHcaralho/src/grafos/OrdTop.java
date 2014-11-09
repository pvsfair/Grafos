/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Kae
 */
public class OrdTop {

    private int[][] mat;

    public void Ord() {
        boolean existeIni = false;
        int j, x;
        int[] vetgrau = new int[10];
        Queue<Integer> fila = new LinkedList<>();
        if (!possuiCiclo()) {
            preencheGrau(vetgrau);
            for (int i = 0; i < 10; i++) {
                if (vetgrau[i] == 0) {
                    j = 0;
                    while ((j < 10) && (existeIni == false)) {
                        if (mat[i][j] == 1) {
                            fila.add(i);
                            existeIni = true;
                        }
                        j++;
                    }
                }
            }
            while (!fila.isEmpty()) {
                x = fila.remove();
                System.out.print(x);
                for (int i = 0; i < 10; i++) {
                    if (mat[x][i] == 1) {
                        vetgrau[i]--;
                        if (vetgrau[i] == 0) {
                            fila.remove(i);
                        }
                    }
                }
                if (!fila.isEmpty()) {
                    System.out.print(",");
                }
            }
        }else{
            System.out.println("O grafo possui ciclos");
        }

    }

    public boolean possuiCiclo() {
        boolean existeVertice = false;
        boolean possui = false;
        int i, j, k;
        int[] vet = new int[10]; //onde tem 10 vai ser a variavel max
        for (i = 0; i < 10; i++) {
            j = 0;
            existeVertice = false;
            while ((j < 10) && (existeVertice == false) && (possui == false)) {
                if (mat[i][j] == 1) {
                    for (k = 0; k < 10; k++) {
                        vet[k] = 0;
                    }
                    possuiCicloDFS(i, vet, possui);
                    existeVertice = true;
                }
                j++;
            }
        }

        return possui;

    }

    public void possuiCicloDFS(int ini, int[] vet, boolean possui) {
        if (possui == false) {
            if ((vet[0] + ini) == 1) {
                possui = true;
            } else {
                int soma = vet[0] + ini;
                soma = 1;
                for (int i = 0; i < 10; i++) {
                    if (mat[ini][i] == 1) {
                        if (((vet[0] + i) == 0) || ((vet[0] + i) == 1)) {
                            possuiCicloDFS(i, vet, possui);
                        }
                    }
                }
            }
            vet[0 + ini] = 2;
        }
    }

    public void preencheGrau(int[] vet) {
        for (int i = 0; i < 10; i++) {
            vet[i] = 0;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mat[j][i] == 1) {
                    vet[0 + i] += 1;
                }
            }
        }
    }
}
