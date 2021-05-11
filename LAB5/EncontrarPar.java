import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class EncontrarPar {

    static void encontrarPar(List<Integer> lista, int k) {
	HashSet<Integer> set = new HashSet<>();
	for (int i : lista) {
	    int conjugado = k - i;
	    if (set.contains(conjugado)) {
		System.out.printf("%d, %d\n", i, i-k);
	    }
	    set.add(i);
	}
    }
    
    public static void main(String[] argv) {
	List<Integer> listaTeste = Arrays.asList(1, 5, -10, 56, 44, 12, 18);
	encontrarPar(listaTeste, 34);
    }
}
