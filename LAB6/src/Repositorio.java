import java.util.ArrayList;
import java.util.List;

public class Repositorio <T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";
    
    private List<T> todasAsFigurinhas;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas) {
        todasAsFigurinhas = new ArrayList<T>(quantFigurinhas + 1);
	todasAsFigurinhas.add(null);
        for (int i = 1; i < quantFigurinhas; i++) {
	    T fig = (T) new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todasAsFigurinhas.add(fig);
        }
    }
    
    public Repositorio(String classe, String sufixoUrlImagens, int quantFigurinhas) {
        todasAsFigurinhas = new ArrayList<T>(quantFigurinhas + 1);
	todasAsFigurinhas.add(null);
	switch (classe) {
	case "FIGURINHA":
	    for (int i = 1; i <= quantFigurinhas; i++) {
		todasAsFigurinhas.add((T) new Figurinha(i, sufixoUrlImagens));
	    }
	    break;
	case "SELO":
	    for (int i = 1; i <= quantFigurinhas; i++) {
		todasAsFigurinhas.add((T) new Selo(i, sufixoUrlImagens));
	    }
	    break;
	}
   }

    public int getTotalFigurinhas() {
        return this.todasAsFigurinhas.size() - 1;
    }

    public List<T> getTodasFigurinhas() {
	return this.todasAsFigurinhas;
    }

    public T getFigurinhaEspecifica(int index) {
	return this.todasAsFigurinhas.get(index);
    }
}
