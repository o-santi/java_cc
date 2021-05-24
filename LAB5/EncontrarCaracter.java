import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class EncontrarCaracter {

    static void encontrarCaracterMaisFrequente(String string) {
	HashMap<Character, Integer> frequencia = new HashMap<>();
	for(int i=0; i < string.length(); i++){
	    char caracter = string.charAt(i);
	    frequencia.put(caracter, frequencia.getOrDefault(caracter, 0) + 1); // salvamos as frequencias num hash map
	}
	int maxValue = Collections.max(frequencia.values()); // achamos o max value do set
	for (Map.Entry<Character, Integer> caractere : frequencia.entrySet()) { // iteramos pelos valores
	    if (caractere.getValue() == maxValue) {
		System.out.printf("%c, %d\n", caractere.getKey(), maxValue); // printamos se o value do char é igual ao max value
	    }
	}
    }

    public static void main(String[] argv) {
	String string = "otorrinolaringologista";
	encontrarCaracterMaisFrequente(string);
    }
    
}
