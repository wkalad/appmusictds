package modelo;

import java.util.LinkedList;
import java.util.List;

public class BuscadorTitulo extends BuscadorDecorator{

	private String titulo;
	
	public BuscadorTitulo(Buscador buscador, String titulo) {
		super(buscador);
		this.titulo = titulo;
	}
	
	@Override
	public List<Cancion> buscarCancion() {
		if(titulo.isEmpty())
			return new LinkedList<Cancion>();
		
		return super.buscarCancion().stream()
									.filter(c -> c.getTitulo().contains(titulo))
									.toList();
	}
}
