package modelo;

public class DescuentoJovenes implements Descuento{
	
	private int PORCENTAJE_JOVENES=15;
	public DescuentoJovenes() {
		
	}
	
	@Override
	public double calcularDescuento(double precio) {
		return PORCENTAJE_JOVENES*precio/100;
	}
	
	public String getTipoDescuento() {
		return "DescuentoJovenes";
	}
}
