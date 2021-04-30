/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
import java.util.ArrayList; // import the ArrayList class

public class Loja {

    private ArrayList<Integer> estoqueQuantidades;
    private ArrayList<Produto> estoqueIndices;
    
    private ArrayList<Usuario> usuarios;
    
    private static final Loja instanciaUnica = new Loja();

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    private Loja() {
	this.estoqueQuantidades = new ArrayList<Integer>();
	this.estoqueIndices = new ArrayList<Produto>();
	this.usuarios = new ArrayList<Usuario>();
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
	int indice = testarProdutoEmEstoque(produto);
	if (indice == -1) {
	    this.estoqueIndices.add(produto);
	    this.estoqueQuantidades.add(quantidadeAIncluir);
	}
	else {
	    int valor = this.estoqueQuantidades.get(indice);
	    this.estoqueQuantidades.set(indice, valor +  quantidadeAIncluir);
	}
    }

    public void cadastrarUsuario(Usuario usuario) {
	boolean usuarioCadastrado = testarUsuarioCadastrado(usuario);
	if (!usuarioCadastrado) {
	    this.usuarios.add(usuario);
	}
    }

    private boolean testarUsuarioCadastrado(Usuario usuario) {
	for (Usuario userCandidato : this.usuarios) {
	    if (userCandidato.equals(usuario)) {
		return true;
	    }
	}
	return false;
    }

    private int testarProdutoEmEstoque(Produto produto) {
	for (int i = 0; i < this.estoqueIndices.size(); i++) {
	    Produto candidato = this.estoqueIndices.get(i);
	    if (candidato.equals(produto)) {
		return i;
	    }
	}
	return -1;
	
    }
    
    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {
	boolean usuarioCadastrado = testarUsuarioCadastrado(usuario);
	if (!usuarioCadastrado)
	    return null;
	int quantidadeEmEstoque = informarQuantidadeEmEstoque(produto);
	if (quantidadeDesejada > quantidadeEmEstoque) {
	    return null;
	}
	int produtoIndex = testarProdutoEmEstoque(produto);
	this.estoqueQuantidades.set(produtoIndex, quantidadeEmEstoque - quantidadeDesejada);
	return new Recibo(quantidadeDesejada * produto.precoEmReais(), usuario, quantidadeDesejada, produto);
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
	int indice = testarProdutoEmEstoque(produto);
	if (indice == -1)
	    return indice;
	return this.estoqueQuantidades.get(indice);
    }

    public void resetEstoque() {
	this.estoqueIndices = new ArrayList<Produto>();
	this.estoqueQuantidades = new ArrayList<Integer>();
    }
}
