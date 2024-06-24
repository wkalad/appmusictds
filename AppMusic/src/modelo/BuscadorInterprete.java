package modelo;

import java.util.LinkedList;
import java.util.List;

public class BuscadorInterprete extends BuscadorDecorator{

	private String interprete;
	
	public BuscadorInterprete(Buscador buscador, String interprete) {
		super(buscador);
		this.interprete = interprete;
	}
	
	@Override
	public List<Cancion> buscarCancion() {
		if(interprete.isEmpty())
			return new LinkedList<Cancion>();
		
		return super.buscarCancion().stream()
									.filter(c -> c.getInterprete().contains(interprete))
									.toList();
	}
	
}
