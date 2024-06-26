package modelo;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	// ATRIBUTOS PRIMITIVOS
	private String nombre;
	private int id;

	// ATRIBUTOS QUE CONTIENEN CLASES
	private List<Cancion> canciones; // Lista porque en Spotify puedes añadir una canción varias veces

	// CONSTRUCTORES
	public Playlist(String nombre) {
		this.nombre = nombre;
		canciones = new ArrayList<Cancion>();
		id = 0;
	}

	public Playlist(String nombre, Cancion... canciones) {
		this(nombre);
		for (Cancion cancion : canciones)
			this.canciones.add(cancion);
	}

	// MÉTODOS GET
	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	public List<Cancion> getCanciones() {
		return new ArrayList<Cancion>(canciones); // Le paso una copia.
	}

	// MÉTODO SET CÓDIGO
	public void setId(int id) {
		this.id = id;
	}

	public void setPlaylist(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	// MÉTODOS
	public boolean addCancion(Cancion cancion) {
		return canciones.add(cancion);
	}

	public boolean removeCancion(Cancion cancion) {
		return canciones.remove(cancion);
	}

	public boolean equals(Object obj) {
		Playlist playlist = (Playlist) obj;
		
		if (this.getNombre().equals(playlist.getNombre())) {
			return true;
		}
		return false;
	}
}
