import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ArvoreB arvore = new ArvoreB(2);

        while (true) {
            int op = JOptionPane.showInputDialog(null, "--- MENU ---\n" +
                    "1. Inserir livro\n" +
                    "2. Buscar por ISBN\n" +
                    "3. Listar livros por título\n" +
                    "4. Exibir árvore B\n" +
                    "0. Sair\n").charAt(0) - '0';

            switch (op) {
                case 1:
                    String titulo = JOptionPane.showInputDialog("Título: ");
                    String autor = JOptionPane.showInputDialog("Autor: ");
                    String isbn = JOptionPane.showInputDialog("ISBN: ");
                    arvore.inserir(new Livro(titulo, autor, isbn));
                    break;

                case 2:
                    String busca = JOptionPane.showInputDialog("Digite ISBN: ");
                    Livro l = arvore.buscar(busca);
                    System.out.println(l != null ? l : "Livro não encontrado.");
                    break;

                case 3:
                    arvore.listarPorTitulo();
                    break;

                case 4:
                    arvore.exibirArvore();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


}

