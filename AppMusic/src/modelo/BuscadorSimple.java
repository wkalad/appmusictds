package modelo;

import java.util.List;

public class BuscadorSimple implements Buscador{

	private List<Cancion> canciones;
	
	public BuscadorSimple(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	@Override
	public List<Cancion> buscarCancion() {
		return canciones;
	}
	
}
