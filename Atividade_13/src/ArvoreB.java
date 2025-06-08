import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArvoreB {

    NoArvoreB raiz;
    int grau;

    public ArvoreB(int grau) {
        this.grau = grau;
        this.raiz = new NoArvoreB(grau, true);
    }

    public void inserir(Livro livro) {
        NoArvoreB r = raiz;
        if (r.numChaves == 2 * grau - 1) {
            NoArvoreB s = new NoArvoreB(grau, false);
            s.filhos[0] = r;
            s.dividirFilho(0, r);
            int i = 0;
            if (livro.isbn.compareTo(s.chaves[0].isbn) > 0) i++;
            s.filhos[i].inserirNaoCheio(livro);
            raiz = s;
        } else {
            r.inserirNaoCheio(livro);
        }
    }

    public Livro buscar(String isbn) {
        return raiz.buscar(isbn);
    }

    public void listarPorTitulo() {
        List<Livro> lista = new ArrayList<>();
        raiz.emOrdem(lista);
        lista.sort(Comparator.comparing(l -> l.titulo));
        for (Livro l : lista) {
            JOptionPane.showMessageDialog(null, l.toString());

        }
    }

    public void exibirArvore() {
        raiz.print("");
    }
}

