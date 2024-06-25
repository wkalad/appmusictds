package lanzador;

import java.awt.EventQueue;

import javax.swing.UIManager;

import vista.VentanaLogin;

public class Lanzador {

	public static void main(String[] args) {
		System.out.println("\n");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					VentanaLogin ventana = new VentanaLogin();
					//ventana.mostrarVentana();
					//ventana.setVisible(true);
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}
		});
	}
}
