package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPago extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean pagado;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			VentanaPago dialog = new VentanaPago();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	public VentanaPago(String precio) {
		this.setModal(true);
		setBounds(100, 100, 462, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel labelTexto = new JLabel("Precio Premium :");
			labelTexto.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(labelTexto);
		}
		{
			JLabel labelPrecio = new JLabel(precio + " €");
			labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(labelPrecio);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton botonPago = new JButton("Pagar");
				botonPago.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pagado = true;
						dispose();
					}
				});
				botonPago.setActionCommand("OK");
				buttonPane.add(botonPago);
				getRootPane().setDefaultButton(botonPago);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pagado = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean isPagado() {
		return pagado;
	}

}
