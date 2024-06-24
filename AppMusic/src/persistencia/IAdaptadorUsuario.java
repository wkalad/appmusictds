package persistencia;

import java.util.List;
import modelo.Usuario;

public interface IAdaptadorUsuario {

	public void crearUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int id);
	public List<Usuario> recuperarTodosUsuarios();
	public void modificarUsuario(Usuario usuario);
	public void borrarUsuario(Usuario usuario);
	
}
