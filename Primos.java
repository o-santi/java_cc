import java.util.Arrays;

/*
  Leonardo Santiago
  DRE 120036072
 */

public class Primos{

    public static boolean testarPrimoSemCrivo(int possivel_primo){
	// método mais burro possivel:
	// como sabemos que 1 sempre divide o número,
	// começamos direto do 2
	int base = 2;
	while (base * base <= possivel_primo) {
	    if (possivel_primo % base == 0) {
		return false;
	    }
	    base++;
	}
	return true;
    }

    public static int[] obterPrimos(int n) {
	int primos_count = 0;
	boolean[] primos = new boolean[n + 1];
	for (int i = 2; i <= n; i++) {
	    if (testarPrimoSemCrivo(i)) {
		primos[i] = true;
		primos_count++;
	    }
	}
	int[] primos_array = new int[primos_count];
	int count = 0;
	for (int i = 2; i < primos.length; i++) {
	    if (primos[i]) {
		primos_array[count] = i;
		count++;
	    }
	}
	
	return primos_array;
    }
    
    public static int[] obterPrimosViaCrivo(int n) {
	boolean[] primos = new boolean[n + 1];
	// ignoramos os dois primeiros indexes
	// (0 e 1) pra poder iniciar do 2, primeiro primo
	// alem disso, somamos 1 pra ter a equivalencia
	// primos[2] -> 2 e primos[3] -> 3 ... etc
	Arrays.fill(primos, true);
	// false: numero composto
	// true : primo
	int primos_index = 2;
	int primos_count = 0;
	while (primos_index <= n) {
	    if (primos[primos_index]) {
		primos_count++;
		for (int i= 2*primos_index; i < primos.length; i += primos_index){
		    primos[i] = false; 
		}
	    }
	    primos_index++;
	}
	int[] primos_array = new int[primos_count];
	int count = 0;
	for (int i = 2; i < primos.length; i++){
	    if (primos[i]) {
		primos_array[count] = i;
		count++;
	    }
	}
	return primos_array;
    }
    
    public static void main(String[] args) {
	for (int n = 10; n <= 10_000; n *= 10) {
	    long inicio = System.currentTimeMillis();
	    int[] primos = obterPrimos(n);
	    double duracao = (System.currentTimeMillis() - inicio) / 1000.0; 
	    System.out.printf("Há %d primos no intervalo [1, %d]. Levou %.6f segundos sem o crivo\n",
			      primos.length, n, duracao);
	}
	System.out.println("");
	// print só pra deixar o output bonitinho
	for (int n = 10; n <= 10_000; n *= 10) {
	    long inicio = System.currentTimeMillis();
	    int[] primos = obterPrimosViaCrivo(n);
	    double duracao = (System.currentTimeMillis() - inicio) / 1000.0; 
	    System.out.printf("Há %d primos no intervalo [1, %d]. Levou %.6f segundos utilizando o crivo\n",
			      primos.length, n, duracao);
	}
    }
}
