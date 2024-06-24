package persistencia;

public class APPMUSICFactoriaDAO extends FactoriaDAO{

	public APPMUSICFactoriaDAO() {}
	
	@Override
	public IAdaptadorUsuario getUsuarioDAO() {
		return AdaptadorUsuario.getUnicaInstancia();
	}

	@Override
	public IAdaptadorCancion getCancionDAO() {
		return AdaptadorCancion.getUnicaInstancia();
	}

	@Override
	public IAdaptadorPlaylist getPlaylistDAO() {
		return AdaptadorPlaylist.getUnicaInstancia();
	}

}
