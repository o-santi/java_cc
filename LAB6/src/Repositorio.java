import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Colecionavel> todasAsFigurinhas;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas) {
        todasAsFigurinhas = new ArrayList<>(quantFigurinhas + 1);
	todasAsFigurinhas.add(null);
        for (int i = 1; i <= quantFigurinhas; i++) {
            Figurinha fig = new Figurinha(i+1, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todasAsFigurinhas.add(fig);
        }
    }

    public int getTotalFigurinhas() {
        return this.todasAsFigurinhas.size() - 1;
    }

    public List<Colecionavel> getTodasFigurinhas() {
	return this.todasAsFigurinhas;
    }
}
