package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import modelo.Cancion;
import pulsador.Luz;
import pulsador.IEncendidoListener;
import java.util.EventObject;
import java.util.List;

public class VentanaPrincipal extends JFrame {

	private JFrame frame;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JTextField textField;
	private JTable table_1;
	JScrollPane scrollPane; //Scroll para la tabla de canciones
	
	//Controlador
	private ControladorAppMusic controladorAppMusic = ControladorAppMusic.getUnicaInstancia();
	
	//
	List<Cancion> cancionesBuscadas;
	private static String[] NOMBRES_COLUMNAS = {"Titulo", "Interprete", "Estilo", "Favoritas"};
	//
	private JPanel panelActual;
	DefaultTableModel modelo;
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		initialize();
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
		panelLateral.setAlignmentX(0.0f);
		frame.getContentPane().add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoton = new JPanel();
		panelLateral.add(panelBoton, BorderLayout.NORTH);
		
		JPanel panelListas = new JPanel();
		panelListas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelLateral.add(panelListas, BorderLayout.CENTER);
		panelListas.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panelListas.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout());
		
		
		
		JList list = new JList();
		list.setVisibleRowCount(6);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1", "lista2", "lista3", "lista1", "lista2", "lista3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panelListas.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Listas");
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

		JButton btnNewButton_4 = new JButton("Premium");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 4;
		gbc_btnNewButton_4.gridy = 0;
		panelArriba.add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_5 = new JButton("logout");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_5.gridx = 5;
		gbc_btnNewButton_5.gridy = 0;
		panelArriba.add(btnNewButton_5, gbc_btnNewButton_5);
		
		Luz luz = new Luz();
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
				}
				
				
			}
		});
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
		
		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setModel(new DefaultComboBoxModel(new String[] {"Estilo", "Pop"}));
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
		
		Object[][] tabla = new Object[1][4];
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
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panelAñadirNorte.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton_13 = new JButton("Crear");
		GridBagConstraints gbc_btnNewButton_13 = new GridBagConstraints();
		gbc_btnNewButton_13.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_13.gridx = 1;
		gbc_btnNewButton_13.gridy = 2;
		panelAñadirNorte.add(btnNewButton_13, gbc_btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
		gbc_btnNewButton_14.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_14.gridx = 2;
		gbc_btnNewButton_14.gridy = 2;
		panelAñadirNorte.add(btnNewButton_14, gbc_btnNewButton_14);
		
		
		////TODO: Tiene que mostrar la misma tabla, por ahora asi.
		JPanel panelTabla = new JPanel();
		panelGestionPlaylists.add(panelTabla, BorderLayout.CENTER);
		GridBagConstraints gbc_botonBuscar_1 = new GridBagConstraints();
		gbc_botonBuscar_1.insets = new Insets(0, 0, 5, 0);
		gbc_botonBuscar_1.gridx = 0;
		gbc_botonBuscar_1.gridy = 1;
		GridBagLayout gbl_panelBoton = new GridBagLayout();
		gbl_panelBoton.columnWidths = new int[]{117, 0};
		gbl_panelBoton.rowHeights = new int[]{33, 37, 33, 33, 0};
		gbl_panelBoton.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBoton.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_botonMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonMisPlaylists.gridx = 0;
		gbc_botonMisPlaylists.gridy = 3;
		panelBoton.add(botonMisPlaylists, gbc_botonMisPlaylists);
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
		
		JPanel panel_6 = new JPanel();
		panelRecientes.add(panel_6, BorderLayout.CENTER);

		JPanel panelMisPlaylists = new JPanel();
		panelCard.add(panelMisPlaylists, "panelMisPlaylists");
		panelMisPlaylists.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panelMisPlaylists.add(panel_8, BorderLayout.CENTER);
		
		JPanel panelReproduccion = new JPanel();
		panel.add(panelReproduccion, BorderLayout.SOUTH);
		GridBagLayout gbl_panelReproduccion = new GridBagLayout();
		gbl_panelReproduccion.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelReproduccion.rowHeights = new int[] {0};
		gbl_panelReproduccion.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelReproduccion.rowWeights = new double[]{0.0};
		panelReproduccion.setLayout(gbl_panelReproduccion);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/izquierda.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panelReproduccion.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/pausa.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panelReproduccion.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/boton-de-play.png")));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panelReproduccion.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/recursos/chevron-derecho.png")));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 0;
		panelReproduccion.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_7 = new JButton("Añadir a Lista");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.gridx = 8;
		gbc_btnNewButton_7.gridy = 0;
		panelReproduccion.add(btnNewButton_7, gbc_btnNewButton_7);

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
		
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.setVisible(false);
				panelBuscar.setVisible(true);
				panelActual = panelBuscar;
				panelReproduccion.setVisible(true);
				//TODO mostrarPanelRepro
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
					tabla[i][3] = false;
					i++;
				}
				modelo = new DefaultTableModel(tabla, NOMBRES_COLUMNAS);
				table.setModel(modelo);
				table.setSelectionBackground(new Color(0, 128, 0));
				table.setVisible(true);
				
				//scrollPane = new JScrollPane(table);
				//scrollPane.setPreferredSize(new Dimension(452, 150));
				//scrollPane.setVisible(true);
				//panelTablaCenter.add(scrollPane);
				
				
			}
		});
		
		botonGestionPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelReproduccion.setVisible(true);
				
				
				
				panelActual.setVisible(false);
				panelGestionPlaylists.setVisible(true);
				panelActual = panelGestionPlaylists;
				
				
			}
		});
	}

}
