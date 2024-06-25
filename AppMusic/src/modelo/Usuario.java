package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Usuario {
	// ATRIBUTOS PRIMITIVOS
		private String password;
		private String nombre;
		private boolean premium;
		private int id;
		private String email;

		
		// ATRIBUTOS QUE CONTIENEN CLASES
		private String fechaNacimiento;
		private Set<Playlist> playlists;
		private List<Cancion> recientes;
		private Descuento descuento;
		
		// CONSTRUCTORES
		public Usuario(String nombre, String password, String email, String fechaNacimiento) {
			this.password=password;
			this.nombre=nombre;
			this.email=email;
			this.fechaNacimiento=fechaNacimiento;
			premium=false;
			id=0;
			playlists=new HashSet<>();
			recientes=new LinkedList<>();
			//descuento=null;
			
		}
		
//		public Usuario(String nombre, String password, Descuento descuento) {
//			this(nombre, password);
//			this.descuento=descuento;
//		}
		
		// MÉTODOS GET
		public String getNombre() {
			return new String(nombre);
		}
		
		public int getId() {
			return id;
		}

		public String getPassword() {
			return new String(password);
		}

		public boolean isPremium() {
			return premium;
		}
		
		public String getEmail() {
			return new String(email);
		}

		public String getFechaNacimiento() {
			return new String(fechaNacimiento);
		}
		
		public Set<Playlist> getPlaylists() {
			return new HashSet<Playlist>(playlists);
		}
		
		public List<Cancion> getRecientes(){
			return new LinkedList<Cancion>(recientes);
		}

		public Descuento getDescuento() {
			return descuento;
		}
		
		public double getPrecioDescuento(double precio) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);
			
			Period period = Period.between(fecha, LocalDate.now());
	        int diferencia = period.getYears();
			
			if(diferencia < 20) {
				descuento = new DescuentoJovenes();
				
			}else if (diferencia > 50){
				descuento = new DescuentoTerceraEdad();
			}else {
				return precio;
			}
			
			return descuento.calcularDescuento(precio);
		}

		public void realizarPago() {
			premium = true;
		}

		// MÉTODO SET codigo
		public void setId(int id) {
			this.id=id;
		}
		
		public void setPremium(boolean premium) {
			this.premium=premium;
		}
		
		public void setDescuento(Descuento descuento) {
			this.descuento = descuento;
		}
		
		public void setPlaylist(Set<Playlist> playlists) {
			this.playlists = playlists;
		}
		
		public void setRecientes(List<Cancion> canciones) {
			recientes = canciones;
		}
		
		public void addPlaylist(Playlist playlist) {
			playlists.add(playlist);
		}
		
		public void addCancionReciente(Cancion cancion) {
			recientes.add(cancion);
		}
}
