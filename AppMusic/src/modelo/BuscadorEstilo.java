package modelo;

import java.util.List;

public class BuscadorEstilo extends BuscadorDecorator{

	private String estilo;
	
	public BuscadorEstilo(Buscador buscador, String estilo) {
		super(buscador);
		this.estilo = estilo;
	}
	
	@Override
	public List<Cancion> buscarCancion() {
		return super.buscarCancion().stream()
									.filter(c -> c.getEstilo().equals(estilo))
									.toList();
	}
}
