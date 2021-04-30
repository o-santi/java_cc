public class Recibo {

    private float valor;

    private Usuario comprador;

    private int quantidade;

    private Produto produto;
    
    public Recibo(float valor, Usuario comprador, int quantidade, Produto produto) {
	this.valor = valor;
	this.comprador = comprador;
	this.quantidade = quantidade;
	this.produto = produto;
    }

    public float getValorTotalDaCompra() {
        return this.valor;
    }

    public Usuario getUsuario() {
        return this.comprador;
    }

    @Override
    public String toString() {
	return "Recibo no valor de R$" + String.format("%.2f", this.valor) +" para " + this.comprador.getNome() +
	    " referente à compra de " + this.quantidade + " unidades de " + this.produto.toString();
	    
    }
}
