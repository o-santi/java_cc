import java.util.List;
import java.util.ArrayList;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
	List<Usuario> intersecao = new ArrayList<>();
	for (Usuario usuarioDaPrimeiraLista : lista1) {
	    for (Usuario usuarioDaSegundaLista : lista2) {
		if (usuarioDaPrimeiraLista.equals(usuarioDaSegundaLista)){
		    intersecao.add(usuarioDaPrimeiraLista);
		    break;
		}
	    }
	}

        return intersecao;
    }
}
