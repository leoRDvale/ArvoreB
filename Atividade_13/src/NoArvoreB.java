import java.util.List;

public class NoArvoreB {
    int grau;  // grau mínimo (t)
    int numChaves;
    Livro[] chaves;
    NoArvoreB[] filhos;
    boolean folha;

    public NoArvoreB(int t, boolean folha) {
        this.grau = t;
        this.folha = folha;
        this.chaves = new Livro[2 * t - 1];
        this.filhos = new NoArvoreB[2 * t];
        this.numChaves = 0;
    }

    public void inserirNaoCheio(Livro livro) {
        int i = numChaves - 1;

        if (folha) {
            while (i >= 0 && livro.isbn.compareTo(chaves[i].isbn) < 0) {
                chaves[i + 1] = chaves[i];
                i--;
            }
            chaves[i + 1] = livro;
            numChaves++;
        } else {
            while (i >= 0 && livro.isbn.compareTo(chaves[i].isbn) < 0) {
                i--;
            }
            i++;
            if (filhos[i].numChaves == 2 * grau - 1) {
                dividirFilho(i, filhos[i]);
                if (livro.isbn.compareTo(chaves[i].isbn) > 0) {
                    i++;
                }
            }
            filhos[i].inserirNaoCheio(livro);
        }
    }

    public void dividirFilho(int i, NoArvoreB y) {
        NoArvoreB z = new NoArvoreB(y.grau, y.folha);
        z.numChaves = grau - 1;

        for (int j = 0; j < grau - 1; j++) {
            z.chaves[j] = y.chaves[j + grau];
        }
        if (!y.folha) {
            for (int j = 0; j < grau; j++) {
                z.filhos[j] = y.filhos[j + grau];
            }
        }
        y.numChaves = grau - 1;

        for (int j = numChaves; j >= i + 1; j--) {
            filhos[j + 1] = filhos[j];
        }
        filhos[i + 1] = z;

        for (int j = numChaves - 1; j >= i; j--) {
            chaves[j + 1] = chaves[j];
        }
        chaves[i] = y.chaves[grau - 1];
        numChaves++;
    }

    public Livro buscar(String isbn) {
        int i = 0;
        while (i < numChaves && isbn.compareTo(chaves[i].isbn) > 0) {
            i++;
        }
        if (i < numChaves && chaves[i].isbn.equals(isbn)) {
            return chaves[i];
        }
        if (folha) return null;
        return filhos[i].buscar(isbn);
    }

    public void emOrdem(List<Livro> lista) {
        int i;
        for (i = 0; i < numChaves; i++) {
            if (!folha) filhos[i].emOrdem(lista);
            lista.add(chaves[i]);
        }
        if (!folha) filhos[i].emOrdem(lista);
    }

    public void print(String prefix) {
        System.out.print(prefix + "[");
        for (int i = 0; i < numChaves; i++) {
            System.out.print(chaves[i].isbn);
            if (i < numChaves - 1) System.out.print(" | ");
        }
        System.out.println("]");
        if (!folha) {
            for (int i = 0; i <= numChaves; i++) {
                filhos[i].print(prefix + "  ");
            }
        }
    }
}
