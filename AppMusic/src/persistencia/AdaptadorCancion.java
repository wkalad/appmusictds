package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import modelo.Cancion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorCancion implements IAdaptadorCancion {

	
	private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorCancion unicaInstancia = null;
	
    private AdaptadorCancion() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
	
	
    public static AdaptadorCancion getUnicaInstancia() {
        // Singleton
        if (unicaInstancia == null)
            return new AdaptadorCancion();
        else
            return unicaInstancia;
    }
    
	@Override
	public void crearCancion(Cancion cancion) {
		
		Entidad eCancion = null;
		
		try {
			eCancion = servicioPersistencia.recuperarEntidad(cancion.getId());
		}catch(NullPointerException e) {}
		
		if(eCancion != null) return ;
		
		
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		
		eCancion.setPropiedades(new ArrayList<Propiedad>(
	            Arrays.asList(
	                new Propiedad("titulo", cancion.getTitulo()),
	                new Propiedad("rutaFichero", cancion.getRutaFichero()),
	                new Propiedad("numReproducciones", String.valueOf(cancion.getNumReproducciones())),
	                new Propiedad("interprete", cancion.getInterprete()),
	                new Propiedad("estilo", cancion.getEstilo())
	                )));
		
		eCancion = servicioPersistencia.registrarEntidad(eCancion);
		
		cancion.setId(eCancion.getId());
		
	}

	@Override
	public Cancion recuperarCancion(int id) {
		
		if (PoolDAO.getUnicaInstancia().contains(id))
			return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad eCancion = servicioPersistencia.recuperarEntidad(id);
		
		String titulo = servicioPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		String rutaFichero = servicioPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		int numReproducciones = Integer.parseInt(servicioPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));
		String interprete = servicioPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
		String estilo = servicioPersistencia.recuperarPropiedadEntidad(eCancion, "estilo");
		
		Cancion cancion = new Cancion(titulo, rutaFichero, estilo, interprete);
		cancion.setNumReproducciones(numReproducciones);
		cancion.setId(id);
		
		return cancion;		
	}

	@Override
	public List<Cancion> recuperarTodasCanciones() {
		
		List<Cancion> canciones = new LinkedList<>();
		List<Entidad> eCanciones = servicioPersistencia.recuperarEntidades("cancion");
		
		for (Entidad eCancion : eCanciones) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		
		return canciones;
		
	}

	@Override
	public void modificarCancion(Cancion cancion) {
		
		Entidad eCancion;
		eCancion = servicioPersistencia.recuperarEntidad(cancion.getId());
		
		for(Propiedad prop : eCancion.getPropiedades()) {
			
			if(prop.getNombre().equals("titulo")) {
				prop.setValor(cancion.getTitulo());
			}else if(prop.getNombre().equals("rutaFichero")) {
				prop.setValor(cancion.getRutaFichero());
			}else if(prop.getNombre().equals("numReproducciones")) {
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
			}else if(prop.getNombre().equals("interprete")) {
				prop.setValor(cancion.getInterprete());
			}else if(prop.getNombre().equals("estilo")) {
				prop.setValor(cancion.getEstilo());
			}
			
			servicioPersistencia.modificarPropiedad(prop);
		}
		
	}

	@Override
	public void borrarCancion(Cancion cancion) {

		Entidad eCancion;
		
		eCancion =  servicioPersistencia.recuperarEntidad(cancion.getId());
		
		servicioPersistencia.borrarEntidad(eCancion);
		
		
	}

}
