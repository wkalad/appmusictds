package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancion;

public class CatalogoCanciones {
	
	private Map<String, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = new CatalogoCanciones();
	
	private FactoriaDAO dao;
	private IAdaptadorCancion adaptadorCancion;
	
	private CatalogoCanciones() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_APPMUSIC);
			adaptadorCancion = dao.getCancionDAO();
			canciones = new HashMap<String, Cancion>();
			this.cargarCatalogo();
		}catch(DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
			
	public static CatalogoCanciones getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public List<Cancion> getCanciones(){
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for (Cancion c:canciones.values()) 
			lista.add(c);
		return lista;
	}
	
	
	public Cancion getCancion(int id) {
		for(Cancion cancion : canciones.values()) {
			if(cancion.getId() == id) return cancion;
		}
		return null;
	}
	
	public Cancion getCancion(String titulo) {
		return canciones.get(titulo); 
	}
	
	public void addCancion(Cancion cancion) {
		canciones.put(cancion.getTitulo(), cancion);
	}
	
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion.getTitulo());
	}
	
	private void cargarCatalogo() throws DAOException {
		List<Cancion> cancionesBD = adaptadorCancion.recuperarTodasCanciones();
		for(Cancion cancion : cancionesBD) {
			canciones.put(cancion.getTitulo(), cancion);
		}
	}
}
