package modelo;

public class DescuentoTerceraEdad implements Descuento{

	private static int PORCENTAJE = 40;
	public DescuentoTerceraEdad(){
		
	}
	
	@Override
	public double calcularDescuento(double precio) {
		return PORCENTAJE*precio/100;
	}

}
