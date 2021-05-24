import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashSet;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    private HashMap<String, Integer> hashtagCount;

    private HashMap<String, Usuario> usuarios; // email -> usuario

    private int quantTweets;

    public static int TAMANHO_MAXIMO_TUITES = 120;

    public TuiterLite() {
	this.hashtagCount = new HashMap<>();
	this.usuarios = new HashMap<>();
	this.quantTweets = 0;
    }
    
    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
	if (!usuarios.containsKey(email)) {
	    Usuario user = new Usuario(nome, email);
	    usuarios.put(email, user);
	    return user;
	}
	return null;
	// não cadastramos usuarios repetidos
    }

    private HashSet<String> lerHashtags(String texto) {
	HashSet<String> hashtags = new HashSet<>();
	String pattern = "(#\\w+)";
	Pattern r = Pattern.compile(pattern);
	Matcher matcher = r.matcher(texto);
	while (matcher.find()) {
	    hashtags.add(matcher.group(1));
	}
	return hashtags;
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
	if (texto.length() < TAMANHO_MAXIMO_TUITES && usuarios.containsKey(usuario.getEmail())) {
	    HashSet<String> hashtags = lerHashtags(texto);
	    Tuite tweet = new Tuite(usuario, texto, hashtags);
	    for (String hashtag : hashtags) {
		this.hashtagCount.put(hashtag, hashtagCount.getOrDefault(hashtag, 0) + 1);
	    }
	    this.quantTweets += 1;
	    return tweet;
	}
	return null;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
	if (quantTweets == 0) return null;
	
	Map.Entry<String, Integer> maisTweetado = null;
	for (Map.Entry<String, Integer> entry : hashtagCount.entrySet()) {
	    if (maisTweetado == null || entry.getValue().compareTo(maisTweetado.getValue()) > 0) {
		maisTweetado = entry;
	    }
	}
	return maisTweetado.getKey();
	    
	
    }
}
