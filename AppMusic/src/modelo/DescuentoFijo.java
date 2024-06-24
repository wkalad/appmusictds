package modelo;

public class DescuentoFijo implements Descuento{
	private int porcentajeDescuentoFijo=10;
	
	public DescuentoFijo() {
		
	}
	
	@Override
	public double calcularDescuento(double precio) {
		return porcentajeDescuentoFijo*precio/100;
	}
	public String getTipoDescuento() {
		return "DescuentoFijo";
	}
}
