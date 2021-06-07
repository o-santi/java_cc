package modelo;

public class Livro {

    private final String titulo;

    private final String autor;

    private final int anoDePublicacao;
    
    public Livro(String titulo, String autor, int anoDePublicacao) {
	this.titulo = titulo;
	this.autor = autor;
	this.anoDePublicacao = anoDePublicacao;
    }

    public String getTitulo() {
        return this.titulo;  // ToDo IMPLEMENT ME!!!
    }

    public String getAutor() {
        return this.autor;  // ToDo IMPLEMENT ME!!!
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;  // ToDo IMPLEMENT ME!!!
    }
}
