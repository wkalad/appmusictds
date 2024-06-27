package modelo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaUsuario {

	private static Usuario usuario;
	private static Playlist playlist;
	
	@BeforeClass
	public static void configurar() {
		String nombre = "paco";
		String email = "paco@um.es";
		String pass = "paco";
		String fechaN = "27/06/1997";
		usuario = new Usuario(nombre, pass, email, fechaN);
		playlist = new Playlist("Playlist1");
	}
	
	@Test
	public void testGetNombre() {
		String resultado = "paco";
		assertEquals(resultado, usuario.getNombre());
	}

	@Test
	public void testIsPremium() {
		assertEquals(false, usuario.isPremium());
		usuario.realizarPago();
		assertEquals(true, usuario.isPremium());
	}

	@Test
	public void testAddPlaylist() {
		usuario.addPlaylist(playlist);
		assertEquals(1, usuario.getPlaylists().size());
		assertEquals(true, usuario.getPlaylists().contains(playlist));
	}
	
	@Test(expected = NullPointerException.class)
	public void testUsuarioNull() {
		Usuario user = null;
		assertNull(user.getEmail());
	}

	
}
