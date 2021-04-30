public class Livro extends Produto {

    private String nome;
    
    private String editora;
    
    public Livro(String nome, String editora) {
	super("Livro normal", "SEM IMAGEM");
	this.nome = nome;
	this.editora = editora;	
    }

    private int numeroDePaginas() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    public String getTrechoEmDestaque() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public String getAutor() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public int getAnoDePublicacao() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }
    
    @Override
    public String toString() {
	return "Livro: " + this.nome;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Livro other = (Livro) obj;
	if (this.nome == other.nome && this.editora == other.editora && this.precoEmReais() == other.precoEmReais())
	    return true;
	return false;
    }
}
