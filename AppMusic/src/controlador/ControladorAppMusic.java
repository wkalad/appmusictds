package controlador;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import auxiliares.Player;
import cargador.CancionesEvent;
import cargador.CancionesListener;
import cargador.CargadorCanciones;
import modelo.*;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancion;
import persistencia.IAdaptadorPlaylist;
import persistencia.IAdaptadorUsuario;

public class ControladorAppMusic implements CancionesListener{

	
	private static double PRECIO = 20;
	
	private static ControladorAppMusic unicaInstancia;
	
	private IAdaptadorUsuario adaptadorUsuario;
	private IAdaptadorCancion adaptadorCancion;
	private IAdaptadorPlaylist adaptadorPlaylist;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	
	private CargadorCanciones cargadorCanciones;
	
	private Usuario usuarioActual;
	private Cancion cancionActual = null;
	private Player player;
	
	public static ControladorAppMusic getUnicaInstancia() {
		
		if(unicaInstancia == null) {
			unicaInstancia = new ControladorAppMusic();
		}
		return unicaInstancia;
	}
	
	private ControladorAppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		cargadorCanciones = new CargadorCanciones();
		cargadorCanciones.addCancionesListener(this);
		player = new Player();
	}
	
	
	public boolean registrarUsuario(String nombre, String password, String email, String fechaNacimiento) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		if(usuario == null) {
			usuario = new Usuario(nombre, password, email, fechaNacimiento);
			adaptadorUsuario.crearUsuario(usuario);
			catalogoUsuarios.addUsuario(usuario);
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean iniciarSesion(String nombre, String password) {
		
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		
		if(usuario != null && usuario.getPassword().equals(password)) {
			usuarioActual = usuario;
			return true;
		}
		
		return false;
		
	}
	
	public boolean iniciarSesionGithub(String nombre, String githubPropertiesPath) {
		
		try {
			GitHub github = GitHubBuilder.fromPropertyFile(githubPropertiesPath).build();

			if (github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();

				Usuario usuario = catalogoUsuarios.getUsuario(nombre);
				
				if(usuario == null) {
					usuario = new Usuario(nombre, "", "", "27/06/1997");
					adaptadorUsuario.crearUsuario(usuario);
					catalogoUsuarios.addUsuario(usuario);
				}				
				
				usuarioActual = usuario;				
				return (ghuser.getLogin().equals(nombre) && github.isCredentialValid());
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cerrarSesion() {
		usuarioActual = null;
		return true;
	}
	
	public List<Cancion> buscarCancion(String titulo, String interprete, String estilo, boolean favorita){
		
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		
		Buscador buscador = new BuscadorSimple(canciones);
		
		if(!titulo.isEmpty() || titulo != null) {
			buscador = new BuscadorTitulo(buscador, titulo);
		}
		if(!interprete.isEmpty() || interprete != null) {
			buscador = new BuscadorInterprete(buscador, interprete);
		}
		if(estilo != null) {
			buscador = new BuscadorEstilo(buscador, estilo);
		}
		if(favorita) {
			buscador = new BuscadorFavorito(buscador, usuarioActual);
		}
		
		return buscador.buscarCancion();
	}
	
	public List<Cancion> getCancionesRecientes(){
		return usuarioActual.getRecientes();
	}
	
	public boolean pagar() {
		usuarioActual.setPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return true;
	}
	
	public double getPrecioPremium() {
		return usuarioActual.getPrecioDescuento(PRECIO);
	}
	
	public boolean isPremium() {
		return usuarioActual.isPremium();
	}
	
	public boolean crearPlaylist(String nombre) {
		
		Playlist playlist = usuarioActual.getPlaylists().stream()
														.filter(p -> p.getNombre().equals(nombre))
														.findFirst()
														.orElse(null);
		
		
		if(playlist == null) {
			playlist = new Playlist(nombre);
			usuarioActual.addPlaylist(playlist);
			adaptadorPlaylist.crearPlaylist(playlist);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}
		return false;
		
	}
	
	public void eliminarPlaylist(String nombre) {
		Playlist playlist = usuarioActual.getPlaylists().stream()
										 .filter(p -> p.getNombre().equals(nombre))
										 .findFirst()
										 .orElse(null);
		
		if(playlist == null) {
			return;
		}
		
		usuarioActual.removePlaylist(playlist);
		adaptadorPlaylist.borrarPlaylist(playlist);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		
	}
	
	public void eliminarCancionPlaylist(String nombre, List<String> titulos) {
		
		Playlist playlist = usuarioActual.getPlaylists().stream()
														.filter(p -> p.getNombre().equals(nombre))
														.findFirst()
														.orElse(null);
		
		if(playlist == null) {
			return ;
		}
		
		for(String titulo : titulos) {
			Cancion cancion = catalogoCanciones.getCancion(titulo);
			playlist.removeCancion(cancion);
		}
		
		playlist.getCanciones().stream()
							   .forEach(c -> System.out.println(c.getTitulo()));
		
		adaptadorPlaylist.modificarPlaylist(playlist);
		
	}
	
	public void anadirCancionPlaylist(String nombre, List<String> titulos) {
		
		Playlist playlist = usuarioActual.getPlaylist(nombre);
		
		if(playlist == null) {
			return ;
		}
		for(String titulo : titulos) {
			Cancion cancion = catalogoCanciones.getCancion(titulo);
			playlist.addCancion(cancion);
		}
		
		adaptadorPlaylist.modificarPlaylist(playlist);
		
	}
	
	

	//Esto solo es para el usuario premium
	public List<Cancion> getTopTen(){
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		
		return canciones.stream()
						.sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed())
						.limit(10)
						.toList();
	}

	//Esto solo es para el usuario premium
	public void generarPDF() {
		
		//Crea el pdf en el directorio local
		String ruta = System.getProperty("user.dir") + "\\" + usuarioActual.getNombre() + ".pdf";
		
		//Como solo tenemos un tipo de API utilizamos esa directamente
		IAdaptadorPDF adaptadorPDF = new AdaptadorIText();
		
		adaptadorPDF.generarPDF(usuarioActual, ruta);
	}


	public void cargarCanciones(String nuevoArchivoCanciones) {		
		cargadorCanciones.setArchivoCanciones(nuevoArchivoCanciones);
	}

	public List<String> getEstilos(){
		return catalogoCanciones.getCanciones().stream()
											   .map(c -> c.getEstilo())
											   .toList();
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_APPMUSIC);
		}catch(DAOException e) {
			e.printStackTrace();
		}
		
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorPlaylist = factoria.getPlaylistDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}


	@Override
	public void nuevasCanciones(CancionesEvent cEvent) {
		
		List<Cancion> canciones = cEvent.getNuevasCanciones().getCancion().stream()
										.map(cc -> new Cancion(cc.getTitulo(), cc.getURL(), cc.getEstilo(), cc.getInterprete()))
										.toList();
		
		canciones.stream()
				 .forEach(c -> {
					 			adaptadorCancion.crearCancion(c);
					 			catalogoCanciones.addCancion(c);
				 			   });
	}
	
	public String getUsuarioActual() {
		return usuarioActual.getNombre();
	}
	
	public String getCancionActual() {
		return cancionActual.getTitulo();
	}

	
	public List<String> getPlaylists(){
		return usuarioActual.getPlaylists().stream()
										 .map(p -> p.getNombre())
										 .toList();
	}
	
	public List<Cancion> getCancionesPlaylists(String nombre){
		return usuarioActual.getPlaylists().stream().filter(p -> p.getNombre().equals(nombre)).flatMap(p -> p.getCanciones().stream()).toList();
	}
	
	public void reproducirCancion(String titulo) {
		Cancion cancion = catalogoCanciones.getCancion(titulo);
		cancionActual = cancion;
		if(player.isRepro()) {
			player.stop();
		}
		
		usuarioActual.addCancionReciente(cancion);
		cancion.addReproduccion();
		adaptadorCancion.modificarCancion(cancion);

		adaptadorUsuario.modificarUsuario(usuarioActual);
		
		player.play(cancion);
	}
	
	public void pausarCancion() {
		if(cancionActual != null) {
			player.pause();
		}	
	}
	
	public void pararCancion() {
		cancionActual = null;
		player.stop();
	}
	
	public boolean isCancionFavorita(Cancion cancion) {
		return cancion.isFavorita(usuarioActual);
	}
	
	
}
