package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;
import java.util.HashMap;
import java.util.ArrayList;

public class Biblioteca {

    /** quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
        que o livro possa ser emprestado */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /** quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    private class UsuarioInfo {
	public long cpf;
	public String nome;
	public String endereco;
	public Usuario usuarioCadastrado;
	public ArrayList<Livro> livrosEmprestados; // quant de livros que um usuario tem emprestado

	public UsuarioInfo(Usuario user) {
	    this.cpf = user.getCpf();
	    this.nome = user.getNome();
	    this.endereco = user.getEndereco();
	    this.usuarioCadastrado = user;
	    this.livrosEmprestados = new ArrayList<Livro>(); 
	}
    } // inner class pra guardar informações sobre o usuario
    
    private HashMap<Long, UsuarioInfo> usuariosCadastrados; // hash map que guarda os usuarios cadastrados
    // cpfByUsuarioInfo

    private HashMap<Livro, Integer> acervoDeLivros; // hash map que guarda os livros e suas quantidades

    public Biblioteca() {
	this.usuariosCadastrados = new HashMap<Long, UsuarioInfo>();
	this.acervoDeLivros = new HashMap<Livro, Integer>();
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
	long cpfDoUsuario = usuario.getCpf();
	if (!usuariosCadastrados.containsKey(cpfDoUsuario)){ // se o usuario não está cadastrado
	    UsuarioInfo info = new UsuarioInfo(usuario);
	    usuariosCadastrados.put(cpfDoUsuario, info);
	}
	else {
	    UsuarioInfo info = usuariosCadastrados.get(cpfDoUsuario);
	    info.nome = usuario.getNome();
	    info.endereco = usuario.getEndereco();
	    info.usuarioCadastrado = usuario;
	    usuariosCadastrados.put(cpfDoUsuario, info);
	}
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
	return usuariosCadastrados.get(cpf).usuarioCadastrado;
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public int getTotalDeUsuariosCadastrados() {
        return this.usuariosCadastrados.size();  // ToDo IMPLEMENT ME!!!
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
	int quantAnterior = acervoDeLivros.getOrDefault(livro, 0);
	acervoDeLivros.put(livro, quantAnterior + quantidade);
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     *
     * @return true, se o empréstimo foi bem-sucedido;
     *         false, se o livro não está disponível para empréstimo
     *         (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     *                      isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     *                      controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     *
     * @throws UsuarioNaoCadastradoException se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
	if (!usuariosCadastrados.containsKey(usuario.getCpf())){
	    throw new UsuarioNaoCadastradoException();
	}
	UsuarioInfo userInfo = usuariosCadastrados.get(usuario.getCpf());
	int quantLivrosEmprestados = userInfo.livrosEmprestados.size();
	if (quantLivrosEmprestados >= MAX_LIVROS_DEVIDOS){
	    throw new LimiteEmprestimosExcedidoException();
	}
	int quantCopiasNoAcervo = acervoDeLivros.getOrDefault(livro, 0);
	if (quantCopiasNoAcervo >= MIN_COPIAS_PARA_PODER_EMPRESTAR) {
	    acervoDeLivros.put(livro, quantCopiasNoAcervo - 1);
	    userInfo.livrosEmprestados.add(livro);
	    usuariosCadastrados.put(usuario.getCpf(), userInfo);
	    return true;
	}
	else {
	    return false;
	}
	
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     *
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
	UsuarioInfo userInfo = usuariosCadastrados.get(usuario.getCpf());
	ArrayList emprestados = userInfo.livrosEmprestados;
	if (!emprestados.contains(livro)) { // se o usuario não tem o livro devolvido
	    throw new DevolucaoInvalidaException();
	}
	int quantLivro = acervoDeLivros.get(livro);
	acervoDeLivros.put(livro, quantLivro + 1);
	emprestados.remove(livro);
	userInfo.livrosEmprestados = emprestados;
	usuariosCadastrados.put(usuario.getCpf(), userInfo);
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     *         ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
	if (usuariosCadastrados.containsKey(usuario.getCpf())) {
	    UsuarioInfo userInfo = usuariosCadastrados.get(usuario.getCpf());
	    return userInfo.livrosEmprestados.size();
	}
	else {
	    return 0;
	}
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     *         não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {
	int livrosNaEstante = 0;
	for (int quantLivro : acervoDeLivros.values()) {
	    livrosNaEstante += quantLivro;
	}
	return livrosNaEstante;
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
	return acervoDeLivros.getOrDefault(livro, 0);
    }
}
