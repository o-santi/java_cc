import java.util.Random;
import java.util.ArrayList;

public class Pacotinho <T extends Colecionavel> {

    private Repositorio<T> repositorio;
    
    private ArrayList<T> figurinhas;

    public Pacotinho(Repositorio<T> repo, int[] posicoesDesejadas) {
	this.repositorio = repo;
	int index = 0;
	figurinhas = new ArrayList<>(posicoesDesejadas.length);
	for (int figurinhaNumero : posicoesDesejadas) {
	    this.figurinhas.add(repo.getFigurinhaEspecifica(figurinhaNumero));
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
    public Pacotinho(Repositorio<T> repo, int quantFigurinhas) {
	int tamanho = repo.getTotalFigurinhas();
	Random rand = new Random();
	figurinhas = new ArrayList<>(quantFigurinhas);
	for (int index = 0; index < quantFigurinhas; index++){
	    this.figurinhas.add(repo.getFigurinhaEspecifica(rand.nextInt(tamanho) + 1));
	}
    }

    public ArrayList<T> getFigurinhas() {
	return this.figurinhas;
    }
}
