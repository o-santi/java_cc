public class Selo implements Colecionavel {
    
    private float valorNominal;

    private Image imagem;
    
    private int posicao;
    
    private String pais;

    public Selo(float valorNominal, String pais, Image imagem, int posicao) {
	this.valorNominal = valorNominal;

	this.pais = pais;

	this.imagem = imagem;

	this.posicao = posicao;
    }

    
    public float getValorNominal(){
	return this.valorNominal;
    }

    public String getPais() {
	return this.pais;
    }

    public Image getImagem() {
	return this.imagem;
    }

    public int getPosicao() {
	return this.posicao;
    }    
    
}
