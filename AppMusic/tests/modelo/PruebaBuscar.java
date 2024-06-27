package modelo;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaBuscar {

	
	private static List<Cancion> canciones;
	
	@BeforeClass
	public static void configurar() {
		canciones = new LinkedList<>();
		canciones.add(new Cancion("Billie Jean", "", "Pop", "Michael Jackson"));
		canciones.add(new Cancion("Beautiful", "", "Rap", "Eminem"));
		canciones.add(new Cancion("Town", "", "Musica Clasica", "Nobuo Uematsu"));
		
	}
	
	@Test
	public void buscarTitulo() {
		Buscador buscador = new BuscadorTitulo(new BuscadorSimple(canciones), "Bill");
		assertEquals("Billie Jean", buscador.buscarCancion().get(0).getTitulo());
	}

	@Test
	public void buscarInterprete() {
		Buscador buscador = new BuscadorInterprete(new BuscadorSimple(canciones), "nem");
		assertEquals("Eminem", buscador.buscarCancion().get(0).getInterprete());
	}
	
	@Test
	public void buscarTituloEInterprete() {
		Buscador buscador = new BuscadorInterprete(new BuscadorSimple(canciones), "Mic");
		buscador = new BuscadorTitulo(buscador, "B");
		List<Cancion> resultado = buscador.buscarCancion();
		assertEquals("Michael Jackson", resultado.get(0).getInterprete());
		assertEquals(1, resultado.size());
	}
}
