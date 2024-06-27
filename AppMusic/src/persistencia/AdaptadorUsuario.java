package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import modelo.Descuento;
import modelo.Playlist;
import modelo.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuario implements IAdaptadorUsuario {
	
	private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorUsuario unicaInstancia = null;
	
    private AdaptadorUsuario() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
    
    
    public static AdaptadorUsuario getUnicaInstancia() {
        // Singleton
        if (unicaInstancia == null)
            return new AdaptadorUsuario();
        else
            return unicaInstancia;
    }
    
    
	@Override
	public void crearUsuario(Usuario usuario) {
		
		Entidad eUsuario = null;
		
		
		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		}catch(NullPointerException e) {}
		
		if(eUsuario != null) return ;
		
		//Creamos la tabla y registramos las propiedades
		
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
	            Arrays.asList(
	                new Propiedad("nombre", usuario.getNombre()),
	                new Propiedad("contrasena", usuario.getPassword()),
	                new Propiedad("fechaNacimiento", usuario.getFechaNacimiento()),
	                new Propiedad("email", usuario.getEmail()),
	                new Propiedad("premium", String.valueOf(usuario.isPremium())),
	                // Metodos privados para obtener "string" de "ids"
	                new Propiedad("playlists", obtenerIdPlaylists(usuario.getPlaylists())),
	                new Propiedad("recientes", obtenerIdCanciones(usuario.getRecientes()))
	                )));
		
		
		eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
		
		usuario.setId(eUsuario.getId());
		
	}
	
	@Override
	public Usuario recuperarUsuario(int id) {
		if (PoolDAO.getUnicaInstancia().contains(id))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(id);
		
		
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String password = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "contrasena");
		String fechaNacimiento = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento");
		String email = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		boolean premium = Boolean.parseBoolean(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));

		
		Set<Playlist> playlists = obtenerPlaylistsId(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "playlists"));
		List<Cancion> recientes = obtenerCancionesId(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, "recientes"));;
		
		Usuario usuario = new Usuario(nombre, password, email, fechaNacimiento);
		
		usuario.setPremium(premium);
		usuario.setId(id);
		
		usuario.setPlaylist(playlists);
		usuario.setRecientes(recientes);
		
		return usuario;
		
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> eUsuarios = servicioPersistencia.recuperarEntidades("usuario");
		
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		
		return usuarios;
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		
		for(Propiedad prop : eUsuario.getPropiedades()) {
			
			if(prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			}else if(prop.getNombre().equals("password")) {
				prop.setValor(usuario.getPassword());
			}else if(prop.getNombre().equals("fechaNacimiento")) {
				prop.setValor(usuario.getFechaNacimiento());
			}else if(prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			}else if(prop.getNombre().equals("premium")) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			}else if(prop.getNombre().equals("playlists")) {
				String playlists = obtenerIdPlaylists(usuario.getPlaylists());
				prop.setValor(playlists);
			}else if(prop.getNombre().equals("recientes")) {
				String recientes = obtenerIdCanciones(usuario.getRecientes());
				prop.setValor(recientes);
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
		
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		
		eUsuario =  servicioPersistencia.recuperarEntidad(usuario.getId());
		
		IAdaptadorPlaylist adaptadorPlaylist = AdaptadorPlaylist.getUnicaInstancia();
		for(Playlist playlist : usuario.getPlaylists()) {
			adaptadorPlaylist.borrarPlaylist(playlist);
		}
		
		servicioPersistencia.borrarEntidad(eUsuario);	
		
	}

	
	private String obtenerIdPlaylists(Set<Playlist> playlists) {
		return playlists.stream()
						.map(playlist -> playlist.getId())
						.map(id -> id.toString() + " ")
						.reduce("", String::concat);	
	}
	
	private String obtenerIdCanciones(List<Cancion> recientes) {
		return recientes.stream()
						.map(reciente -> reciente.getId())
						.map(id -> id.toString() + " ")
						.reduce("", String::concat);
	}
	
	private Set<Playlist> obtenerPlaylistsId(String idsPlaylists) {
		Set<Playlist> recientes = new HashSet<Playlist>();
		
		StringTokenizer strTok = new StringTokenizer(idsPlaylists, " ");
		
		IAdaptadorPlaylist adaptadorPlaylist = AdaptadorPlaylist.getUnicaInstancia();
		
		while(strTok.hasMoreTokens()) {
			recientes.add(adaptadorPlaylist.recuperarPlaylist(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return recientes;	
	}
	
	private List<Cancion> obtenerCancionesId(String idsCanciones) {
		List<Cancion> recientes = new LinkedList<Cancion>();
		
		StringTokenizer strTok = new StringTokenizer(idsCanciones, " ");
		
		IAdaptadorCancion adaptadorCancion = AdaptadorCancion.getUnicaInstancia();
		
		while(strTok.hasMoreTokens()) {
			recientes.add(adaptadorCancion.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return recientes;
	}
	
}
