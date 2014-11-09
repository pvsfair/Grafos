void DFS(int ini) {
    bool existeini = false;
    int i, vet[max];
    for (i = 0; i < max; i++) {
        vet[i] = 0;
    }
    i = 0;
    while ((i < max)&&(existeini == false)) {
        if ((ini < max)&&((mat[ini][i] == 1) || (mat[i][ini] == 1))) {
            existeini = true;
        }
        i++;
    }
    if (existeini == true) {
        percursoDFS(ini, vet);
    }
}

void possuiCicloDFS(int ini, int *vet, bool *possui) {
    int i;
    if (*possui == false) {
        if (*(vet + ini) == 1) {
            *possui = true;
        } else {
            *(vet + ini) = 1;
            for (i = 0; i < max; i++) {
                if (mat[ini][i] == 1) {
                    if ((*(vet + i) == 0) || (*(vet + i) == 1)) {
                        possuiCicloDFS(i, vet, possui);
                    }
                }
            }
        }
        *(vet + ini) = 2;
    }
}

bool possuiCiclo() {
    bool existevertice = false;
    bool possui = false;
    int i, j, k, vet[max];
    for (i = 0; i < max; i++) {
        j = 0;
        existevertice = false;
        while ((j < max)&&(existevertice == false)&&(possui == false)) {
            if (mat[i][j] == 1) {
                for (k = 0; k < max; k++) {
                    vet[k] = 0;
                }
                possuiCicloDFS(i, &vet[0], &possui);
                existevertice = true;
            }
            j++;
        }
    }
    return possui;
}

void preencheGrau(int *vet) {
    int i, j;
    for (i = 0; i < max; i++) {
        *(vet + i) = 0;
    }
    for (i = 0; i < max; i++) {
        for (j = 0; j < max; j++) {
            if (mat[j][i] == 1) {
                *(vet + i) = *(vet + i) + 1;
            }
        }
    }
}

void OrdTopologica() {
    bool existeini = false;
    int i, j, x, vetgrau[max];
    Fila<int> fila;
    if (!possuiCiclo()) {
        preencheGrau(&vetgrau[0]);
        for (i = 0; i < max; i++) {
            if (vetgrau[i] == 0) {
                j = 0;
                while ((j < max)&&(existeini == false)) {
                    if (mat[i][j] == 1) {
                        fila.Enfileira(i);
                        existeini = true;
                    }
                    j++;
                }
            }
        }
        while (!fila.IsEmpty()) {
            x = fila.Desenfileira();
            printf("%d", x);
            fprintf(fout, "%d", x);
            for (i = 0; i < max; i++) {
                if (mat[x][i] == 1) {
                    vetgrau[i]--;
                    if (vetgrau[i] == 0) {
                        fila.Enfileira(i);
                    }
                }
            }
            if (!fila.IsEmpty()) {
                printf(",");
                fprintf(fout, ",");
            }
        }
    } else {
        printf("o grafo possui ciclos!");
        fprintf(fout, "o grafo possui ciclos!");
    }
}