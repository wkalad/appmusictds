package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class VentanaCargarCanciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCargarCanciones frame = new VentanaCargarCanciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCargarCanciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel etiqRuta = new JLabel("Ruta");
		GridBagConstraints gbc_etiqRuta = new GridBagConstraints();
		gbc_etiqRuta.insets = new Insets(0, 0, 5, 5);
		gbc_etiqRuta.anchor = GridBagConstraints.NORTHEAST;
		gbc_etiqRuta.gridx = 1;
		gbc_etiqRuta.gridy = 2;
		panel.add(etiqRuta, gbc_etiqRuta);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton botonExplorar = new JButton("Explorar");
		GridBagConstraints gbc_botonExplorar = new GridBagConstraints();
		gbc_botonExplorar.insets = new Insets(0, 0, 5, 5);
		gbc_botonExplorar.gridx = 4;
		gbc_botonExplorar.gridy = 2;
		panel.add(botonExplorar, gbc_botonExplorar);
		
		JLabel labelError = new JLabel("Introduce una ruta valida");
		labelError.setEnabled(false);
		labelError.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_labelError = new GridBagConstraints();
		gbc_labelError.insets = new Insets(0, 0, 5, 5);
		gbc_labelError.gridx = 2;
		gbc_labelError.gridy = 3;
		panel.add(labelError, gbc_labelError);
		
		JButton botonCargar = new JButton("Cargar Canciones");
		GridBagConstraints gbc_botonCargar = new GridBagConstraints();
		gbc_botonCargar.insets = new Insets(0, 0, 5, 5);
		gbc_botonCargar.gridx = 2;
		gbc_botonCargar.gridy = 4;
		panel.add(botonCargar, gbc_botonCargar);
		
		JLabel labelTextoExp = new JLabel("Introduce una ruta al archivo de canciones \r\nen formato xml");
		GridBagConstraints gbc_labelTextoExp = new GridBagConstraints();
		gbc_labelTextoExp.insets = new Insets(0, 0, 5, 5);
		gbc_labelTextoExp.gridx = 2;
		gbc_labelTextoExp.gridy = 5;
		panel.add(labelTextoExp, gbc_labelTextoExp);
	}

}
