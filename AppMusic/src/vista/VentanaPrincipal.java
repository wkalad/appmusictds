package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAppMusic;
import javafx.scene.control.ComboBox;
import modelo.Cancion;
import pulsador.Luz;
import pulsador.IEncendidoListener;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private JFrame frame;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JTextField textNombrePlaylist;
	private JTable table_1;
	private JTable tablaPlaylist;
	private JTable tablaRecientes;
	private JTable tablaTopTen;
	private JScrollPane scrollPane; //Scroll para la tabla de canciones
	private JButton botonPDF;
	private JButton botonTopTen;
	private DefaultListModel<String> listModel;
	
	//Controlador
	private ControladorAppMusic controladorAppMusic = ControladorAppMusic.getUnicaInstancia();
	
	//
	List<Cancion> cancionesBuscadas;
	private static String[] NOMBRES_COLUMNAS = {"Titulo", "Interprete", "Estilo", "Favoritas"};
	private static String[] NOMBRES_COLUMNAS2 = {"Titulo", "Interprete", "Estilo"};
	private static String[] NOMBRES_COLUMNAS3 = {"Titulo", "Interprete", "Estilo", "Num Reproducciones"};
	//
	private JPanel panelActual;
	private DefaultTableModel modelo;
	private JComboBox comboBoxEstilo;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		initialize();
	}
	
	private void mostrarFuncPremium() {		
		if(controladorAppMusic.isPremium()) {
			botonPDF.setVisible(true);
			botonTopTen.setVisible(true);
		}else {
			botonPDF.setVisible(false);
			botonTopTen.setVisible(false);
		}
	}
	
	private void initialize() {
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setTitle("AppMusic");
		frame.setVisible(true);
		frame.setBounds(100, 100, 691, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelLateral = new JPanel();
		panelLateral.setMaximumSize(new Dimension(10, 10));
		panelLateral.setAlignmentX(0.0f);
		frame.getContentPane().add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoton = new JPanel();
		panelLateral.add(panelBoton, BorderLayout.NORTH);
		
		JPanel panelListas = new JPanel();
		panelListas.setMaximumSize(new Dimension(10, 10));
		panelListas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelLateral.add(panelListas, BorderLayout.CENTER);
		panelListas.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(2, 26, 115, 221);
		panelListas.add(panel_4);
		
		
		listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		list.setMaximumSize(new Dimension(10, 10));
		
		
		List<String> playlists = controladorAppMusic.getPlaylists();
		int j = 0;		
		for(String p : playlists) {
			listModel.add(j, p);
			j++;
		}
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(0, 0, 115, 221);
		scrollPane_1.setMinimumSize(new Dimension(10, 10));
		scrollPane_1.setMaximumSize(new Dimension(10, 10));
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_4.add(scrollPane_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(2, 0, 115, 24);
		panel_5.setMaximumSize(new Dimension(10, 10));
		panelListas.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Listas");
		lblNewLabel_5.setBounds(36, 0, 43, 24);
		panel_5.add(lblNewLabel_5);
		
		
		
		JPanel panelCard = new JPanel();
		//TODO: Aqui estaban los botones del panel izquierda

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelArriba = new JPanel();
		panelArriba.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(panelArriba, BorderLayout.NORTH);
		GridBagLayout gbl_panelArriba = new GridBagLayout();
		gbl_panelArriba.columnWidths = new int[] { 128, 94, 73, 63, 0, 0, 0, 0 };
		gbl_panelArriba.rowHeights = new int[] { 23, 0 };
		gbl_panelArriba.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelArriba.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelArriba.setLayout(gbl_panelArriba);

		JLabel lblNewLabel = new JLabel("Bienvenido " + controladorAppMusic.getUsuarioActual());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelArriba.add(lblNewLabel, gbc_lblNewLabel);

		JButton botonPremium = new JButton("Premium");

		GridBagConstraints gbc_botonPremium = new GridBagConstraints();
		gbc_botonPremium.anchor = GridBagConstraints.NORTHWEST;
		gbc_botonPremium.insets = new Insets(0, 0, 0, 5);
		gbc_botonPremium.gridx = 4;
		gbc_botonPremium.gridy = 0;
		panelArriba.add(botonPremium, gbc_botonPremium);

		JButton botonLogout = new JButton("logout");
		botonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin ventanaLogin = new VentanaLogin();
				//ventanaLogin.setVisible(true);
				ventanaLogin.mostrarVentana();
				frame.getContentPane().removeAll();
				frame.getContentPane().revalidate();
				frame.dispose();
			}
		});
		GridBagConstraints gbc_botonLogout = new GridBagConstraints();
		gbc_botonLogout.insets = new Insets(0, 0, 0, 5);
		gbc_botonLogout.anchor = GridBagConstraints.NORTHWEST;
		gbc_botonLogout.gridx = 5;
		gbc_botonLogout.gridy = 0;
		panelArriba.add(botonLogout, gbc_botonLogout);
		
		Luz luz = new Luz();
		
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.gridx = 6;
		gbc_luz.gridy = 0;
		panelArriba.add(luz, gbc_luz);

		panel.add(panelCard, BorderLayout.CENTER);
		panelCard.setLayout(new CardLayout(0, 0));

		JPanel panelBuscar = new JPanel();
		panelCard.add(panelBuscar, "panelBuscar");
		panelBuscar.setLayout(new BorderLayout(0, 0));
		
		//Ponemos el panel inical
		panelActual = panelBuscar;
		
		JPanel panelBuscarNorte = new JPanel();
		panelBuscarNorte.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBuscar.add(panelBuscarNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelBuscarNorte = new GridBagLayout();
		gbl_panelBuscarNorte.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelBuscarNorte.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelBuscarNorte.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBuscarNorte.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBuscarNorte.setLayout(gbl_panelBuscarNorte);
		
		JLabel lblNewLabel_3 = new JLabel("Buscar");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panelBuscarNorte.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		
		txtInterprete = new JTextField();
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.gridx = 0;
		gbc_txtInterprete.gridy = 1;
		panelBuscarNorte.add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setName("titulo");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		panelBuscarNorte.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JCheckBox checkBoxFavoritas = new JCheckBox("favoritas");
		GridBagConstraints gbc_checkBoxFavoritas = new GridBagConstraints();
		gbc_checkBoxFavoritas.anchor = GridBagConstraints.WEST;
		gbc_checkBoxFavoritas.insets = new Insets(0, 0, 5, 5);
		gbc_checkBoxFavoritas.gridx = 0;
		gbc_checkBoxFavoritas.gridy = 2;
		panelBuscarNorte.add(checkBoxFavoritas, gbc_checkBoxFavoritas);
		
		comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setModel(new DefaultComboBoxModel(new String[] {"Estilo"}));
		comboBoxEstilo.setToolTipText("");
		GridBagConstraints gbc_comboBoxEstilo = new GridBagConstraints();
		gbc_comboBoxEstilo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilo.gridx = 1;
		gbc_comboBoxEstilo.gridy = 2;
		panelBuscarNorte.add(comboBoxEstilo, gbc_comboBoxEstilo);
		
		JButton botonBuscarCancion = new JButton("buscar");
		
		GridBagConstraints gbc_botonBuscarCancion = new GridBagConstraints();
		gbc_botonBuscarCancion.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscarCancion.gridx = 1;
		gbc_botonBuscarCancion.gridy = 4;
		panelBuscarNorte.add(botonBuscarCancion, gbc_botonBuscarCancion);
		
		JPanel panelTablaCenter = new JPanel();
		panelBuscar.add(panelTablaCenter, BorderLayout.CENTER);
		
		JCheckBox fav = new JCheckBox();
		/*
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Titulo", "Interprete", "Estilo", true},
			},
			new String[] {
				"Titulo", "Interprete", "Estilo", "Favorito"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});*/
		table = new JTable();
		
		Object[][] tabla = new Object[0][4];
		modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS);
		table.setModel(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(452, 150));
		//scrollPane.setVisible(false);
		panelTablaCenter.add(scrollPane);

		JPanel panelGestionPlaylists = new JPanel();
		panelCard.add(panelGestionPlaylists, "panelGestionPlaylists");
		panelGestionPlaylists.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAñadirNorte = new JPanel();
		panelGestionPlaylists.add(panelAñadirNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelAñadirNorte = new GridBagLayout();
		gbl_panelAñadirNorte.columnWidths = new int[]{5, 0, 0, 0, 0};
		gbl_panelAñadirNorte.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelAñadirNorte.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAñadirNorte.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAñadirNorte.setLayout(gbl_panelAñadirNorte);
		
		textNombrePlaylist = new JTextField();
		GridBagConstraints gbc_textNombrePlaylist = new GridBagConstraints();
		gbc_textNombrePlaylist.gridwidth = 2;
		gbc_textNombrePlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_textNombrePlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombrePlaylist.gridx = 1;
		gbc_textNombrePlaylist.gridy = 1;
		panelAñadirNorte.add(textNombrePlaylist, gbc_textNombrePlaylist);
		textNombrePlaylist.setColumns(10);
		
		JButton botonCrearPlaylist = new JButton("Crear");
		
		GridBagConstraints gbc_botonCrearPlaylist = new GridBagConstraints();
		gbc_botonCrearPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_botonCrearPlaylist.gridx = 1;
		gbc_botonCrearPlaylist.gridy = 2;
		panelAñadirNorte.add(botonCrearPlaylist, gbc_botonCrearPlaylist);
		
		JButton botonEliminarPlaylist = new JButton("Eliminar");
		
		GridBagConstraints gbc_botonEliminarPlaylist = new GridBagConstraints();
		gbc_botonEliminarPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_botonEliminarPlaylist.gridx = 2;
		gbc_botonEliminarPlaylist.gridy = 2;
		panelAñadirNorte.add(botonEliminarPlaylist, gbc_botonEliminarPlaylist);
		
		
		////TODO: Tiene que mostrar la misma tabla, por ahora asi.
		JPanel panelTabla = new JPanel();
		panelGestionPlaylists.add(panelTabla, BorderLayout.CENTER);
		GridBagConstraints gbc_botonBuscar_1 = new GridBagConstraints();
		gbc_botonBuscar_1.insets = new Insets(0, 0, 5, 0);
		gbc_botonBuscar_1.gridx = 0;
		gbc_botonBuscar_1.gridy = 1;
		GridBagLayout gbl_panelBoton = new GridBagLayout();
		gbl_panelBoton.columnWidths = new int[]{117, 0};
		gbl_panelBoton.rowHeights = new int[]{33, 37, 33, 0, 0, 33, 0};
		gbl_panelBoton.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBoton.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBoton.setLayout(gbl_panelBoton);
		
		JButton botonBuscar = new JButton("Buscar");
		
		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_botonBuscar.gridx = 0;
		gbc_botonBuscar.gridy = 0;
		panelBoton.add(botonBuscar, gbc_botonBuscar);
		
		JButton botonGestionPlaylist = new JButton("GestionPlaylist");
		
		GridBagConstraints gbc_botonGestionPlaylist = new GridBagConstraints();
		gbc_botonGestionPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonGestionPlaylist.insets = new Insets(0, 0, 5, 0);
		gbc_botonGestionPlaylist.gridx = 0;
		gbc_botonGestionPlaylist.gridy = 1;
		panelBoton.add(botonGestionPlaylist, gbc_botonGestionPlaylist);
		
		JButton botonRecientes = new JButton("Recientes");
		
		GridBagConstraints gbc_botonRecientes = new GridBagConstraints();
		gbc_botonRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRecientes.insets = new Insets(0, 0, 5, 0);
		gbc_botonRecientes.gridx = 0;
		gbc_botonRecientes.gridy = 2;
		panelBoton.add(botonRecientes, gbc_botonRecientes);
		
		JButton botonMisPlaylists = new JButton("MisPlaylists");
		
		GridBagConstraints gbc_botonMisPlaylists = new GridBagConstraints();
		gbc_botonMisPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_botonMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonMisPlaylists.gridx = 0;
		gbc_botonMisPlaylists.gridy = 3;
		panelBoton.add(botonMisPlaylists, gbc_botonMisPlaylists);
		
		botonTopTen = new JButton("TopTen");
		
		GridBagConstraints gbc_botonTopTen = new GridBagConstraints();
		gbc_botonTopTen.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonTopTen.insets = new Insets(0, 0, 5, 0);
		gbc_botonTopTen.gridx = 0;
		gbc_botonTopTen.gridy = 4;
		panelBoton.add(botonTopTen, gbc_botonTopTen);
		
		botonPDF = new JButton("CreadorPDF");
		
		GridBagConstraints gbc_botonPDF = new GridBagConstraints();
		gbc_botonPDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonPDF.gridx = 0;
		gbc_botonPDF.gridy = 5;
		panelBoton.add(botonPDF, gbc_botonPDF);

		mostrarFuncPremium();
		
		GridBagConstraints gbc_botonGestion_1 = new GridBagConstraints();
		gbc_botonGestion_1.insets = new Insets(0, 0, 5, 0);
		gbc_botonGestion_1.gridx = 0;
		gbc_botonGestion_1.gridy = 2;
		GridBagConstraints gbc_botonRecientes_1 = new GridBagConstraints();
		gbc_botonRecientes_1.insets = new Insets(0, 0, 5, 0);
		gbc_botonRecientes_1.gridx = 0;
		gbc_botonRecientes_1.gridy = 3;
		GridBagConstraints gbc_botonMisPlaylists_1 = new GridBagConstraints();
		gbc_botonMisPlaylists_1.gridx = 0;
		gbc_botonMisPlaylists_1.gridy = 4;
		GridBagConstraints gbl_panelGBotones = new GridBagConstraints();
		gbl_panelGBotones.fill = GridBagConstraints.HORIZONTAL;

		JPanel panelRecientes = new JPanel();
		panelCard.add(panelRecientes, "panelRecientes");
		panelRecientes.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTablaRecientes = new JPanel();
		panelRecientes.add(panelTablaRecientes, BorderLayout.CENTER);

		JPanel panelMisPlaylists = new JPanel();
		panelCard.add(panelMisPlaylists, "panelMisPlaylists");
		panelMisPlaylists.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTablaPlay = new JPanel();
		panelMisPlaylists.add(panelTablaPlay, BorderLayout.CENTER);
		
		JPanel panelReproduccion = new JPanel();
		panel.add(panelReproduccion, BorderLayout.SOUTH);
		GridBagLayout gbl_panelReproduccion = new GridBagLayout();
		gbl_panelReproduccion.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelReproduccion.rowHeights = new int[] {0};
		gbl_panelReproduccion.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelReproduccion.rowWeights = new double[]{0.0};
		panelReproduccion.setLayout(gbl_panelReproduccion);
		
		JButton botonAtras = new JButton("");
		
		botonAtras.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/izquierda.png")));
		GridBagConstraints gbc_botonAtras = new GridBagConstraints();
		gbc_botonAtras.insets = new Insets(0, 0, 0, 5);
		gbc_botonAtras.gridx = 0;
		gbc_botonAtras.gridy = 0;
		panelReproduccion.add(botonAtras, gbc_botonAtras);
		
		JButton botonPausa = new JButton("");
		
		botonPausa.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/pausa.png")));
		GridBagConstraints gbc_botonPausa = new GridBagConstraints();
		gbc_botonPausa.insets = new Insets(0, 0, 0, 5);
		gbc_botonPausa.gridx = 1;
		gbc_botonPausa.gridy = 0;
		panelReproduccion.add(botonPausa, gbc_botonPausa);
		
		JButton botonPlay = new JButton("");
		
		botonPlay.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/boton-de-play.png")));
		GridBagConstraints gbc_botonPlay = new GridBagConstraints();
		gbc_botonPlay.insets = new Insets(0, 0, 0, 5);
		gbc_botonPlay.gridx = 2;
		gbc_botonPlay.gridy = 0;
		panelReproduccion.add(botonPlay, gbc_botonPlay);
		
		JButton botonSiguiente = new JButton("");
		
		botonSiguiente.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/chevron-derecho.png")));
		GridBagConstraints gbc_botonSiguiente = new GridBagConstraints();
		gbc_botonSiguiente.insets = new Insets(0, 0, 0, 5);
		gbc_botonSiguiente.gridx = 3;
		gbc_botonSiguiente.gridy = 0;
		panelReproduccion.add(botonSiguiente, gbc_botonSiguiente);
		
		JButton botonAnadirLista = new JButton("Añadir a Lista");
		
		GridBagConstraints gbc_botonAnadirLista = new GridBagConstraints();
		gbc_botonAnadirLista.gridx = 8;
		gbc_botonAnadirLista.gridy = 0;
		panelReproduccion.add(botonAnadirLista, gbc_botonAnadirLista);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 271, 46, 0 };
		gbl_panel_1.rowHeights = new int[] { 14, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel_1 = new JLabel("AppMusic");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		tablaPlaylist = new JTable();
		Object[][] tablaP = new Object[0][3];
		modelo = new DefaultTableModel(tablaP, NOMBRES_COLUMNAS2);
		tablaPlaylist.setModel(modelo);
		JScrollPane scrollPaneP = new JScrollPane(tablaPlaylist);
		scrollPaneP.setPreferredSize(new Dimension(452, 150));
		
		panelTablaPlay.add(scrollPaneP);
		
		
		
		tablaRecientes = new JTable();
		Object[][] tablaR = new Object[0][3];
		modelo = new DefaultTableModel(tablaR, NOMBRES_COLUMNAS2);
		tablaRecientes.setModel(modelo);
		JScrollPane scrollPaneR = new JScrollPane(tablaRecientes);
		scrollPaneR.setPreferredSize(new Dimension(452, 150));
		
		panelTablaRecientes.add(scrollPaneR);
		
		JPanel panelTopTen = new JPanel();
		panelCard.add(panelTopTen, "name_90058731106300");
		
		JPanel panelPDF = new JPanel();
		panelCard.add(panelPDF, "name_90071555260300");
		GridBagLayout gbl_panelPDF = new GridBagLayout();
		gbl_panelPDF.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPDF.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelPDF.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPDF.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPDF.setLayout(gbl_panelPDF);
		
		JButton botonGenerarPDF = new JButton("Generar PDF");
		
		botonGenerarPDF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_botonGenerarPDF = new GridBagConstraints();
		gbc_botonGenerarPDF.gridx = 8;
		gbc_botonGenerarPDF.gridy = 3;
		panelPDF.add(botonGenerarPDF, gbc_botonGenerarPDF);
		
		
		tablaTopTen = new JTable();
		Object[][] tablaT = new Object[0][4];
		modelo = new DefaultTableModel(tablaT, NOMBRES_COLUMNAS3);
		tablaTopTen.setModel(modelo);
		JScrollPane scrollPaneT = new JScrollPane(tablaTopTen);
		scrollPaneT.setPreferredSize(new Dimension(452, 150));
		
		panelTopTen.add(scrollPaneT);
		
		
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Seleccione el archivo de las canciones");
				
				int resultado = fileChooser.showOpenDialog(frame);
				
				if(resultado == JFileChooser.APPROVE_OPTION) {
					//TODO:Aqui y si el archivo no funciona? o es incorrecto
					System.out.println(fileChooser.getSelectedFile().getPath());
					String path = fileChooser.getSelectedFile().getPath();
					controladorAppMusic.cargarCanciones(path);
					List<String> estilos = controladorAppMusic.getEstilos();
					
					estilos.stream()
						   .forEach(e -> comboBoxEstilo.addItem(e));		
				}
				
				
			}
		});
		
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAnadirLista.setVisible(true);
				botonAnadirLista.setText("Añadir a Lista");
				panelActual.setVisible(false);
				panelBuscar.setVisible(true);
				panelActual = panelBuscar;
				panelReproduccion.setVisible(true);
			}
		});
		
		botonBuscarCancion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String interprete = txtInterprete.getText();
				String titulo = txtTitulo.getText();
				String estilo = (String) comboBoxEstilo.getSelectedItem();
				boolean favoritas = checkBoxFavoritas.isSelected();
				if(estilo == "Estilo") {
					estilo = null;
				}
				
				cancionesBuscadas = controladorAppMusic.buscarCancion(titulo, interprete, estilo, favoritas);
				
				
				Object[][] tabla = new Object[cancionesBuscadas.size()][4];
				int i = 0;
				
				for(Cancion c:cancionesBuscadas) {
					tabla[i][0] = c.getTitulo();
					tabla[i][1] = c.getInterprete();
					tabla[i][2] = c.getEstilo();
					tabla[i][3] = controladorAppMusic.isCancionFavorita(c);
					i++;
				}
				modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS);
				table.setModel(modelo);
				table.setSelectionBackground(new Color(0, 128, 0));
				table.setVisible(true);	
			}
		});
		
		botonGestionPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelReproduccion.setVisible(true);
				
				botonAnadirLista.setVisible(false);
				panelActual.setVisible(false);
				panelGestionPlaylists.setVisible(true);
				panelActual = panelGestionPlaylists;
				
				
			}
		});
		
		botonPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String precio = String.valueOf(controladorAppMusic.getPrecioPremium());			
				
				VentanaPago ventanaPago = new VentanaPago(precio);
				ventanaPago.setVisible(true);
				
				if(ventanaPago.isPagado()) {
					controladorAppMusic.pagar();
					botonPDF.setVisible(true);
					botonTopTen.setVisible(true);
				}
			}
		});
		
		
		botonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object valor = null;
				if(panelActual == panelBuscar) {
					int fila = table.getSelectedRow();
					valor = table.getValueAt(fila, 0);
				}else if(panelActual == panelMisPlaylists) {
					int fila = tablaPlaylist.getSelectedRow();
					valor = tablaPlaylist.getValueAt(fila, 0);
				}else if(panelActual == panelRecientes) {
					int fila = tablaRecientes.getSelectedRow();
					valor = tablaRecientes.getValueAt(fila, 0);
				}else if(panelActual == panelTopTen) {
					int fila = tablaTopTen.getSelectedRow();
					valor = tablaTopTen.getValueAt(fila, 0);
				}
				
				controladorAppMusic.reproducirCancion(valor.toString());	
			}
		});
		
		botonPausa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorAppMusic.pausarCancion();
			
			}
		});
		
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = controladorAppMusic.getCancionActual();
				int i;
				for(i = 0; i < table.getRowCount();i++) {
					Object valor = table.getValueAt(i, 0);
					if(valor != null && valor.toString().equalsIgnoreCase(titulo)) {
						break;
					}
				}
				
				int filas = table.getRowCount();
				int siguiente = (i + 1) % filas;
				Object valor = table.getValueAt(siguiente, 0);
				controladorAppMusic.reproducirCancion(valor.toString());
			}
		});
		
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = controladorAppMusic.getCancionActual();
				int i;
				for(i = 0; i < table.getRowCount();i++) {
					Object valor = table.getValueAt(i, 0);
					if(valor != null && valor.toString().equalsIgnoreCase(titulo)) {
						break;
					}
				}
				
				int filas = table.getRowCount();
				if(i == 0)
					i = filas;
				int siguiente = (i - 1) % filas;
				Object valor = table.getValueAt(siguiente, 0);
				controladorAppMusic.reproducirCancion(valor.toString());
			}
		});
		
		
		botonCrearPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombrePlaylist.getText();
				
				boolean creada = controladorAppMusic.crearPlaylist(nombre);
				if(creada) {
					int i = listModel.getSize();
					listModel.add(i, nombre);
				}
			}
		});
		
		botonAnadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(panelActual == panelBuscar) {
					String lista = list.getSelectedValue();
					
					//System.out.println("PLaulsit " +  lista);
					

					
					int[] lineas = table.getSelectedRows();
					Object[][] tabla = new Object[lineas.length][3];
					List<String> titulos = new LinkedList<>();
					for(int i = 0; i < lineas.length; i++) {
						Object valor = table.getValueAt(lineas[i], 0);
						titulos.add(valor.toString());
						tabla[i][0] = table.getValueAt(lineas[i], 0);
						tabla[i][1] = table.getValueAt(lineas[i], 1);
						tabla[i][2] = table.getValueAt(lineas[i], 2);
						
					}
					
					modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS2);
					tablaPlaylist.setModel(modelo);
					tablaPlaylist.setSelectionBackground(new Color(0, 128, 0));
					tablaPlaylist.setVisible(true);
					
					
					controladorAppMusic.anadirCancionPlaylist(lista, titulos);
					
				}else if(panelActual == panelMisPlaylists) {
					
					String lista = list.getSelectedValue();
					int[] lineas = tablaPlaylist.getSelectedRows();
					
					List<String> titulos = new LinkedList<>();
					for(int i = 0; i < lineas.length; i++) {
						Object valor = tablaPlaylist.getValueAt(lineas[i], 0);
						titulos.add(valor.toString());
						DefaultTableModel model = (DefaultTableModel)tablaPlaylist.getModel();
						model.removeRow(lineas[i]);
					}
					
					controladorAppMusic.eliminarCancionPlaylist(lista, titulos);
					
				}
				
				
			}
		});
		
		botonMisPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				botonAnadirLista.setVisible(true);
				botonAnadirLista.setText("Eliminar de la Lista");
				panelActual.setVisible(false);
				panelMisPlaylists.setVisible(true);
				panelActual = panelMisPlaylists;
				panelReproduccion.setVisible(true);
				
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(panelActual == panelGestionPlaylists) {
					
					String nombre = list.getSelectedValue();
					textNombrePlaylist.setText(nombre);
					
				}else if(panelActual == panelMisPlaylists) {
					String nombre = list.getSelectedValue();
					
					System.out.println("PLaulsit " +  nombre);
					
					List<Cancion> canciones = controladorAppMusic.getCancionePlaylists(nombre);
					
					
					Object[][] tabla = new Object[canciones.size()][3];
					int i = 0;
					for(Cancion c:canciones) {
						tabla[i][0] = c.getTitulo();
						tabla[i][1] = c.getInterprete();
						tabla[i][2] = c.getEstilo();
						
						i++;
					}
					modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS2);
					tablaPlaylist.setModel(modelo);
					tablaPlaylist.setSelectionBackground(new Color(0, 128, 0));
					tablaPlaylist.setVisible(true);
				}
			}
		});
		
		
		botonRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cancion> canciones = controladorAppMusic.getCancionesRecientes();
				
				botonAnadirLista.setVisible(false);
				
				Object[][] tabla = new Object[canciones.size()][3];
				int i = 0;
				for(Cancion c:canciones) {
					tabla[i][0] = c.getTitulo();
					tabla[i][1] = c.getInterprete();
					tabla[i][2] = c.getEstilo();
					System.out.println("tit " +  c.getTitulo());
					
					i++;
				}
				modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS2);
				tablaRecientes.setModel(modelo);
				tablaRecientes.setSelectionBackground(new Color(0, 128, 0));
				tablaRecientes.setVisible(true);
				
				panelActual.setVisible(false);
				panelRecientes.setVisible(true);
				panelActual = panelRecientes;
				panelReproduccion.setVisible(true);

			}
		});
		
		botonTopTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Cancion> canciones = controladorAppMusic.getTopTen();
				
				
				Object[][] tabla = new Object[canciones.size()][4];
				int i = 0;
				for(Cancion c:canciones) {
					tabla[i][0] = c.getTitulo();
					tabla[i][1] = c.getInterprete();
					tabla[i][2] = c.getEstilo();
					tabla[i][3] = c.getNumReproducciones();
					
					i++;
				}
				modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS3);
				tablaTopTen.setModel(modelo);
				tablaTopTen.setSelectionBackground(new Color(0, 128, 0));
				tablaTopTen.setVisible(true);
				
				panelActual.setVisible(false);
				panelTopTen.setVisible(true);
				panelActual = panelTopTen;
				panelReproduccion.setVisible(true);

			}
		});
		
		botonPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.setVisible(false);
				panelPDF.setVisible(true);
				panelActual = panelPDF;
				panelReproduccion.setVisible(false);
			}
		});
		
		botonGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controladorAppMusic.generarPDF();
			}
		});
		
		botonEliminarPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombrePlaylist.getText();
				
				
				listModel.removeElement(nombre);
				
				controladorAppMusic.eliminarPlaylist(nombre);
			}
		});
	}

}
