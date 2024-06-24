package persistencia;

import java.util.List;

import modelo.Cancion;

public interface IAdaptadorCancion {
	
	public void crearCancion(Cancion cancion);
	public Cancion recuperarCancion(int id);
	public List<Cancion> recuperarTodasCanciones();
	public void modificarCancion(Cancion cancion);
	public void borrarCancion(Cancion cancion);
}
