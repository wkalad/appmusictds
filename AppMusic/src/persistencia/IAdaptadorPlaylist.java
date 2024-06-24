package persistencia;

import java.util.List;

import modelo.Playlist;


public interface IAdaptadorPlaylist {

	public void crearPlaylist(Playlist playlist);
	public Playlist recuperarPlaylist(int id);
	public List<Playlist> recuperarTodasPlaylists();
	public void modificarPlaylist(Playlist playlist);
	public void borrarPlaylist(Playlist playlist);
}
