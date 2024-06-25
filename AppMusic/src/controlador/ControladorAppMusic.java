package controlador;

import java.io.IOException;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;
import java.util.Set;

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

	
	private static ControladorAppMusic unicaInstancia;
	
	private IAdaptadorUsuario adaptadorUsuario;
	private IAdaptadorCancion adaptadorCancion;
	private IAdaptadorPlaylist adaptadorPlaylist;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	
	private CargadorCanciones cargadorCanciones;
	
	private Usuario usuarioActual;//TODO Ver esto bien 
	
	//TODO: donde deberia ir el player e instanciarse?
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
	
	
	public void registrarUsuario(String nombre, String password, String email, String fechaNacimiento) {
		//TODO: Se deberia controlar mismo nombre de usuario? Fecha DAte?
		Usuario usuario = new Usuario(nombre, password, email, fechaNacimiento);
		adaptadorUsuario.crearUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);
	}
	
	//TODO: Login gestionar con github neceistamos adaptadores?
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
				//TODO UsuarioActual  que pasa con eso?
				return (ghuser.getLogin().equals(nombre) && github.isCredentialValid());
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Cancion> buscarCancion(String titulo, String interprete, String estilo, boolean favorita){
		
		List<Cancion> canciones = catalogoCanciones.getCanciones();
		
		//canciones.contains(canciones);
		
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
		return true;
	}
	
	public Playlist crearPlaylist(String nombre, List<Cancion> canciones) {
		
		Playlist playlist = usuarioActual.getPlaylists().stream()
														.filter(p -> p.getNombre().contains(nombre))
														.findFirst()
														.orElse(null);
		
		//Set<Playlist> playlists = usuarioActual.getPlaylists();
		
		
		//No existe por lo tanto se pude crear
		if(playlist == null) {
			playlist = new Playlist(nombre);
			playlist.setPlaylist(canciones);
			//Guardar en persistencia
			adaptadorPlaylist.crearPlaylist(playlist);
		}
		
		return playlist;
		
	}
	//TODO como se eliminan las playlist
	public void eliminarCancionPlaylist(String nombre, List<Cancion> canciones) {
		
		Playlist playlist = usuarioActual.getPlaylists().stream()
														.filter(p -> p.getNombre().contains(nombre))
														.findFirst()
														.orElse(null);
		//No deberia ser null
		canciones.stream()
				 .forEach(c -> playlist.removeCancion(c));
		
		adaptadorPlaylist.modificarPlaylist(playlist);
		
	}
	
	public void anadirCancionPlaylist(String nombre, List<Cancion> canciones) {
		
		Playlist playlist = usuarioActual.getPlaylists().stream()
														.filter(p -> p.getNombre().contains(nombre))
														.findFirst()
														.orElse(null);
		
		canciones.stream()
		 		 .forEach(c -> playlist.addCancion(c));
		
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
	public void generarPDF(String ruta) {
		ruta = ruta + "\\" + usuarioActual.getNombre() + ".pdf";
		
		IAdaptadorPDF adaptadorPDF = new AdaptadorIText();
		
		adaptadorPDF.generarPDF(usuarioActual, ruta);
	}


	public void cargarCanciones(String nuevoArchivoCanciones) {		
		cargadorCanciones.setArchivoCanciones(nuevoArchivoCanciones);
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
		//TODO: ELIMINAR
		/*
		for(Cancion c : catalogoCanciones.getCanciones()) {
			System.out.println(c.getTitulo());
		}
		*/
		
	}
	
	public String getUsuarioActual() {
		return usuarioActual.getNombre();
	}

}
