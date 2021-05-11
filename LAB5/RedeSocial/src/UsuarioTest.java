import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Array;

public class UsuarioTest {

    public Usuario usuarioIngenuo;

    public Usuario usuarioEsperto;

    public Usuario usuarioBinario;

    public List<Usuario> usuarios;

    public CalculadorIntersecaoIngenuo calcIngenuo;
    
    public CalculadorIntersecaoEsperto calcEsperto;

    public CalculadorIntersecaoViaBuscaBinaria calcBinario;

    @Before
    public void setUp() {

	calcIngenuo = new CalculadorIntersecaoIngenuo();
	calcEsperto = new CalculadorIntersecaoEsperto();
	calcBinario = new CalculadorIntersecaoViaBuscaBinaria();

	usuarioIngenuo = new Usuario(-1, calcIngenuo); // id's negativos para garantir
	usuarioEsperto = new Usuario(-2, calcEsperto); // que os usuarios de teste não são amigos um do outro
	usuarioBinario = new Usuario(-3, calcBinario);

	usuarios = new ArrayList<>();

	for (int i=0; i<60; i++){
	    usuarios.add(new Usuario(i, calcBinario));
	}
	
	for (int i = 0; i < 60; i++) {
	    if (i % 2 == 0) 
		usuarioIngenuo.setAmigo(usuarios.get(i));
	    if (i % 3 == 0)
		usuarioEsperto.setAmigo(usuarios.get(i));
	    if (i % 5 == 0)
		usuarioBinario.setAmigo(usuarios.get(i));
	}
    }
    
    @Test
    public void testaContagemCorreta() {
	assertEquals("1", usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioEsperto), 10);
	assertEquals("2", usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioIngenuo), 10);
	
	assertEquals("3", usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioBinario), 6);
	assertEquals("4", usuarioBinario.obterQuantidadeDeAmigosEmComum(usuarioIngenuo), 6);
	
	assertEquals("5", usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioBinario), 4);
	assertEquals("6", usuarioBinario.obterQuantidadeDeAmigosEmComum(usuarioEsperto), 4);

	assertEquals("7", usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioIngenuo), 30);
	assertEquals("8", usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioEsperto), 20);
	assertEquals("9", usuarioBinario.obterQuantidadeDeAmigosEmComum(usuarioBinario), 12);
    }

    @Test
    public void testaPerformanceIngenuo() {
	for (int i = 0; i < 50_000; i++) {
	    Usuario current = new Usuario(i, calcBinario);
	    usuarioIngenuo.setAmigo(current);
	    usuarioEsperto.setAmigo(current);
	    usuarioBinario.setAmigo(current);
	}
	long t1 = System.currentTimeMillis();
	usuarioIngenuo.obterQuantidadeDeAmigosEmComum(usuarioEsperto);
	System.out.printf("Ingenuo: %f segundos\n", (System.currentTimeMillis() - t1) / 1000.0);
	
	t1 = System.currentTimeMillis();
	usuarioEsperto.obterQuantidadeDeAmigosEmComum(usuarioBinario);
	System.out.printf("Esperto: %f segundos\n", (System.currentTimeMillis() - t1) / 1000.0);
	
	t1 = System.currentTimeMillis();
	usuarioBinario.obterQuantidadeDeAmigosEmComum(usuarioIngenuo);
	System.out.printf("Binario: %f segundos\n", (System.currentTimeMillis() - t1) / 1000.0);
    }
}
