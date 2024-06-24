package persistencia;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;
	
	public static final String DAO_APPMUSIC = "persistencia.APPMUSICFactoriaDAO";
	
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if(unicaInstancia == null) {
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch(Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		return unicaInstancia;
	}
	
	public static FactoriaDAO getInstancia() throws DAOException{
		if(unicaInstancia == null) return getInstancia(FactoriaDAO.DAO_APPMUSIC);
		else return unicaInstancia;
	}
	
	protected FactoriaDAO() {}
	
	public abstract IAdaptadorUsuario getUsuarioDAO();
	public abstract IAdaptadorCancion getCancionDAO();
	public abstract IAdaptadorPlaylist getPlaylistDAO();
	
}
