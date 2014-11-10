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


    public void Ord(int[][] mat) {
        boolean existeIni = false;
        int j, x;
        int[] vetgrau = new int[mat.length];
        Queue<Integer> fila = new LinkedList<>();
        if (!possuiCiclo(mat)) {
            preencheGrau(vetgrau,mat);
            for (int i = 0; i < mat.length; i++) {
                if (vetgrau[i] == 0) {
                    j = 0;
                    while ((j < mat.length) && (existeIni == false)) {
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
                for (int i = 0; i < mat.length; i++) {
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

    public boolean possuiCiclo(int[][] mat) {
        boolean existeVertice = false;
        boolean possui = false;
        int i, j, k;
        int[] vet = new int[mat.length]; //onde tem mat.length vai ser a variavel max
        for (i = 0; i < mat.length; i++) {
            j = 0;
            existeVertice = false;
            while ((j < mat.length) && (existeVertice == false) && (possui == false)) {
                if (mat[i][j] == 1) {
                    for (k = 0; k < mat.length; k++) {
                        vet[k] = 0;
                    }
                    possuiCicloDFS(i, vet, possui,mat);
                    existeVertice = true;
                }
                j++;
            }
        }

        return possui;

    }

    public void possuiCicloDFS(int ini, int[] vet, boolean possui,int[][] mat) {
        if (possui == false) {
            if ((vet[0+ ini]) == 1) {   //criar variavel pra receber o indice inicial
                possui = true;
            } else {
                int soma = vet[0] + ini;
                soma = 1;
                for (int i = 0; i < mat.length; i++) {
                    if (mat[ini][i] == 1) {
                        if (((vet[0] + i) == 0) || ((vet[0] + i) == 1)) {
                            possuiCicloDFS(i, vet, possui,mat);
                        }
                    }
                }
            }
            vet[0 + ini] = 2;
        }
    }

    public void preencheGrau(int[] vet,int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            vet[i] = 0;
            
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[j][i] == 1) {
                    vet[0 + i] += 1;
                }
            }
        }
    }
}
