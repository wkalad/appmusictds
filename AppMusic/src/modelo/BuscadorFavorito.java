package modelo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuscadorFavorito extends BuscadorDecorator{

	private Usuario usuario;
	public BuscadorFavorito(Buscador buscador, Usuario usuario) {
		super(buscador);
		this.usuario = usuario;
	}
	
	@Override
	public List<Cancion> buscarCancion() {
		Set<Cancion> canciones = usuario.getPlaylists().stream()
							  .flatMap(p -> p.getCanciones().stream())
							  .collect(Collectors.toSet());
		
		return super.buscarCancion().stream()
									.filter(c ->  canciones.contains(c))
									.toList();
	}
}
