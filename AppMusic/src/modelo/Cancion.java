package modelo;

public class Cancion {
	// ATRIBUTOS PRIMITIVOS
	private String titulo;
	private String rutaFichero;
	private int numReproducciones;
	private int id;
	private String interprete;
	private String estilo;
	
	// ATRIBUTOS QUE CONTIENEN CLASES
	
	// CONSTRUCTOR
	public Cancion(String titulo, String rutaFichero, String estilo, String interprete) {
		this.titulo=titulo;
		this.rutaFichero=rutaFichero;
		this.interprete=interprete;
		this.estilo=estilo;
		numReproducciones=0;
		id=0;
	}
	
	// MÉTODOS GET
	public String getTitulo() {
		return titulo;
	}

	public String getRutaFichero() {
		return rutaFichero;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public int getId() {
		return id;
	}

	public String getEstilo() {
		return estilo;
	}

	public String getInterprete() {
		return interprete;
	}
	
	public String getCancionInfo() {
		return getTitulo() + " " + getInterprete() + " " + getEstilo();
	}

	// MÉTODOS SET
	public void setId(int id) {
		this.id=id;
	}
	
	public void setEstiloMusical(String estilo) {
		this.estilo=estilo;
	}
	
	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones=numReproducciones;
	}
	
	public void addReproduccion() {
		numReproducciones = numReproducciones + 1;
	}
	
  public boolean equals(Object obj) {
	  Cancion cancion = (Cancion) obj;
	  if(this.getTitulo().equals(cancion.getTitulo())) {
		  return true;
	  }
	  return false;       
  }
}
