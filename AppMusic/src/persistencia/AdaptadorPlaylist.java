package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import modelo.Playlist;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorPlaylist implements IAdaptadorPlaylist{
	
	
	private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorPlaylist unicaInstancia = null;
	
    private AdaptadorPlaylist() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
	
	
    public static AdaptadorPlaylist getUnicaInstancia() {
        // Singleton
        if (unicaInstancia == null)
            return new AdaptadorPlaylist();
        else
            return unicaInstancia;
    }
	
	
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		Entidad ePlaylist = null;
		
		try {
			ePlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());
		}catch(NullPointerException e) {}
		
		if(ePlaylist != null) return ;
		
		ePlaylist = new Entidad();
		ePlaylist.setNombre("playlist");
		
		
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(
	            Arrays.asList(
	                new Propiedad("nombre", playlist.getNombre()),
	                new Propiedad("canciones", obtenerIdCanciones(playlist.getCanciones()))
	                )));
		
		ePlaylist = servicioPersistencia.registrarEntidad(ePlaylist);
		playlist.setId(ePlaylist.getId());
		
	}

	@Override
	public Playlist recuperarPlaylist(int id) {
		
		if (PoolDAO.getUnicaInstancia().contains(id))
			return (Playlist) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad ePlaylist = servicioPersistencia.recuperarEntidad(id);
		
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(ePlaylist, "nombre");
		
		List<Cancion> canciones = obtenerCancionesId(servicioPersistencia.recuperarPropiedadEntidad(ePlaylist, "canciones"));;
		
		Playlist playlist = new Playlist(nombre);
		playlist.setId(id);
		playlist.setPlaylist(canciones);;
		
		return playlist;		
	}

	@Override
	public List<Playlist> recuperarTodasPlaylists() {
		List<Playlist> playlists = new LinkedList<>();
		List<Entidad> eUsuarios = servicioPersistencia.recuperarEntidades("usuario");
		
		for (Entidad eUsuario : eUsuarios) {
			playlists.add(recuperarPlaylist(eUsuario.getId()));
		}
		
		return playlists;
	}

	@Override
	public void modificarPlaylist(Playlist playlist) {
		Entidad ePlaylist;
		ePlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());
		
		for(Propiedad prop : ePlaylist.getPropiedades()) {
			
			if(prop.getNombre().equals("nombre")) {
				prop.setValor(playlist.getNombre());
			}else if(prop.getNombre().equals("canciones")) {
				String canciones = obtenerIdCanciones(playlist.getCanciones());
				prop.setValor(canciones);
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public void borrarPlaylist(Playlist playlist) {
		Entidad ePlaylist;
		ePlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());
		
		servicioPersistencia.borrarEntidad(ePlaylist);
		
	}
	
	
	private String obtenerIdCanciones(List<Cancion> canciones) {
		return canciones.stream()
						.map(cancion -> cancion.getId())
						.map(id -> id.toString() + " ")
						.reduce("", String::concat);
	}
	
	private List<Cancion> obtenerCancionesId(String idsCanciones) {
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		StringTokenizer strTok = new StringTokenizer(idsCanciones, " ");
		
		IAdaptadorCancion adaptadorCancion = AdaptadorCancion.getUnicaInstancia();
		
		while(strTok.hasMoreTokens()) {
			canciones.add(adaptadorCancion.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return canciones;
	}

}
