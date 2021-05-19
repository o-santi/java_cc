import java.util.Random;

public class Pacotinho {

    private Repositorio repositorio;
    
    private Figurinha[] figurinhas;
	
    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
	this.repositorio = repo;
	int index = 0;
	figurinhas = new Figurinha[posicoesDesejadas.length];
	for (int figurinhaNumero : posicoesDesejadas) {
	    this.figurinhas[index] = new Figurinha(figurinhaNumero, "");
	    index++;
	}
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
	int tamanho = repo.getTotalFigurinhas();
	Random rand = new Random();
	figurinhas = new Figurinha[quantFigurinhas];

	for (int index = 0; index < quantFigurinhas; index++){
	    this.figurinhas[index] = new Figurinha(rand.nextInt(tamanho) + 1, "");
	}
    }

    public Figurinha[] getFigurinhas() {
	return figurinhas;
    }
}
