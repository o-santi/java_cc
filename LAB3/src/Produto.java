public class Produto {

    private String descricao;

    private String urlDaImagem;

    private float precoEmReais;

    private Dimensoes Dimensao;

    public Produto(String descricao, String urlDaImagem) {
	this.descricao = descricao;
	this.urlDaImagem = urlDaImagem;	
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;  
    }

    public int getPesoEmGramas() {
        return 0; // ToDo IMPLEMENT ME!!!
    }

    public Dimensoes getDimensoes() {
        return this.Dimensao; 
    }

    public float precoEmReais() {
        return this.precoEmReais;
    }

    public void setPrecoEmReais(float preco) {
	if (preco > 0) {
	    this.precoEmReais = preco;
	}
    }

    public String getUrlDaImagem() {
        return urlDaImagem; 
    }
}
