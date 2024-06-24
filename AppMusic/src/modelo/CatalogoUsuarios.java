package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuario;

public class CatalogoUsuarios {
	
	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	private FactoriaDAO dao;
	private IAdaptadorUsuario adaptadorUsuario;
	
	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_APPMUSIC);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		}catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}
	
	
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario u:usuarios.values()) 
			lista.add(u);
		return lista;
	}
	
	
	public Usuario getUsuario(int id) {
		for(Usuario usuario : usuarios.values()) {
			if(usuario.getId() == id) return usuario;
		}
		return null;
	}
	
	public Usuario getUsuario(String nombre) {
		return usuarios.get(nombre); 
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getNombre(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getNombre());
	}
	
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for(Usuario usuario : usuariosBD) {
			usuarios.put(usuario.getNombre(), usuario);
		}
	}
	
}
