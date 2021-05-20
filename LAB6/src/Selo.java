import java.awt.*;

public class Selo implements Colecionavel {
    
    private double valorNominal;

    private Image imagem;
    
    private int posicao;
    
    private String pais;

    public Selo(double valorNominal, String pais, int posicao) {
	this.valorNominal = valorNominal;

	this.pais = pais;

	this.posicao = posicao;
    }

    public Selo(int posicao, String url) {
	this.posicao = posicao;
    }
    
    public double getValorNominal(){
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
