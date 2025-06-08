public class Livro {
    String titulo;
    String autor;
    String isbn;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String toString() {
        return "[" + isbn + "] " + titulo + " - " + autor;


    }
}
