package modelo;

import java.util.List;

public abstract class BuscadorDecorator implements Buscador{
	
	// Atributos
    private Buscador buscador;

    // Constructores
    public BuscadorDecorator(Buscador buscador) {
        this.buscador = buscador;
    }

    // Funcionalidad	
	@Override
	public List<Cancion> buscarCancion(){
		return buscador.buscarCancion();
	}
}
