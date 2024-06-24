package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import com.toedter.calendar.JCalendar;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}
	
	public void mostrarVentana() {
		frame.setVisible(true);
	}
	
	private void inicializarJFrame() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(300, 150));
		frame.setResizable(false);
		frame.setBounds(100, 100, 789, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel creaPanelVentana() {
		JPanel ventana = new JPanel();
		frame.getContentPane().add(ventana, BorderLayout.CENTER);
		ventana.setLayout(new CardLayout(0, 0));
		return ventana;
	}
	
	private JPanel crearPanelLogin(JPanel ventana) {
		JPanel login = new JPanel();
		login.setLayout(new BorderLayout(0, 0));
		ventana.add(login, "login");
		return login;
	}
	
	private JPanel crearPanelRegistro(JPanel ventana) {
		JPanel registro = new JPanel();
		ventana.add(registro, "registro");
		registro.setLayout(new BorderLayout(0, 0));
		return registro;
	}
	
	private JPanel gridBagLayourLogin(JPanel login) {
		JPanel panelLogin = new JPanel();
		login.add(panelLogin, BorderLayout.CENTER);
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[]{5, 0, 5, 0};
		gbl_panelLogin.rowHeights = new int[]{0, 80, 0, 0, 0, 30, 0, 0, 0};
		gbl_panelLogin.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelLogin.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelLogin.setLayout(gbl_panelLogin);
		return panelLogin;
	}
	
	private void iconoYLabelAppMusic(JPanel panelLogin) {
		JLabel labelAppMusic = new JLabel("AppMusic");
		labelAppMusic.setFont(new Font("Verdana", Font.BOLD, 24));
		GridBagConstraints gbc_labelAppMusic = new GridBagConstraints();
		gbc_labelAppMusic.anchor = GridBagConstraints.SOUTH;
		gbc_labelAppMusic.insets = new Insets(0, 0, 5, 5);
		gbc_labelAppMusic.gridx = 1;
		gbc_labelAppMusic.gridy = 0;
		panelLogin.add(labelAppMusic, gbc_labelAppMusic);
		
		JLabel iconoAppMusic = new JLabel("");
		iconoAppMusic.setIcon(new ImageIcon(VentanaLogin.class.getResource("/recursos/iconoAppMusicGrand.png")));
		GridBagConstraints gbc_iconoAppMusic = new GridBagConstraints();
		gbc_iconoAppMusic.anchor = GridBagConstraints.NORTH;
		gbc_iconoAppMusic.insets = new Insets(0, 0, 5, 5);
		gbc_iconoAppMusic.gridx = 1;
		gbc_iconoAppMusic.gridy = 1;
		panelLogin.add(iconoAppMusic, gbc_iconoAppMusic);
	}
	
	private JPanel construyeCampoUsuario(JPanel panelLogin) {
		JPanel panelUsuarioLogin = new JPanel();
		panelUsuarioLogin.setOpaque(false);
		panelUsuarioLogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelUsuarioLogin = new GridBagConstraints();
		gbc_panelUsuarioLogin.anchor = GridBagConstraints.NORTH;
		gbc_panelUsuarioLogin.insets = new Insets(0, 0, 5, 5);
		gbc_panelUsuarioLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelUsuarioLogin.gridx = 1;
		gbc_panelUsuarioLogin.gridy = 2;
		panelLogin.add(panelUsuarioLogin, gbc_panelUsuarioLogin);
		panelUsuarioLogin.setLayout(new BoxLayout(panelUsuarioLogin, BoxLayout.X_AXIS));
		return panelUsuarioLogin;
	}
	
	private JPanel construyeCampoContrasena(JPanel panelLogin) {
		JPanel panelContrasenaLogin = new JPanel();
		panelContrasenaLogin.setOpaque(false);
		panelContrasenaLogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelContrasenaLogin = new GridBagConstraints();
		gbc_panelContrasenaLogin.insets = new Insets(0, 0, 5, 5);
		gbc_panelContrasenaLogin.fill = GridBagConstraints.BOTH;
		gbc_panelContrasenaLogin.gridx = 1;
		gbc_panelContrasenaLogin.gridy = 4;
		panelLogin.add(panelContrasenaLogin, gbc_panelContrasenaLogin);
		panelContrasenaLogin.setLayout(new BoxLayout(panelContrasenaLogin, BoxLayout.X_AXIS));
		return panelContrasenaLogin;
	}
	
	private void insertaLabelUsuario(JPanel panelUsuarioLogin) {
		JLabel labelUsuarioLogin = new JLabel("Usuario: ");
		labelUsuarioLogin.setBorder(null);
		labelUsuarioLogin.setPreferredSize(new Dimension(80, 13));
		labelUsuarioLogin.setFont(new Font("Verdana", Font.BOLD, 10));
		panelUsuarioLogin.add(labelUsuarioLogin);
	}
	
	private JTextField insertaTextFieldUsuario(JPanel panelUsuarioLogin) {
		JTextField textFieldUsuarioL = new JTextField();
		textFieldUsuarioL.setOpaque(false);
		textFieldUsuarioL.setForeground(new Color(255, 255, 255));
		textFieldUsuarioL.setFont(new Font("Verdana", Font.BOLD, 10));
		textFieldUsuarioL.setBorder(null);
		panelUsuarioLogin.add(textFieldUsuarioL);
		textFieldUsuarioL.setColumns(10);
		return textFieldUsuarioL;
	}
	
	private void insertaLabelContrasena(JPanel panelContrasenaLogin) {
		creaLabelContrasenaR(panelContrasenaLogin);
	}
	
	private JPasswordField insertaPasswordField(JPanel panelContrasenaLogin) {
		JPasswordField contrasenaFieldL = new JPasswordField();
		contrasenaFieldL.setCaretColor(new Color(38, 38, 38));
		contrasenaFieldL.setBorder(null);
		contrasenaFieldL.setForeground(new Color(255, 255, 255));
		contrasenaFieldL.setEchoChar('*');
		contrasenaFieldL.setFont(new Font("Verdana", Font.BOLD, 10));
		contrasenaFieldL.setOpaque(false);
		panelContrasenaLogin.add(contrasenaFieldL);
		return contrasenaFieldL;
	}
	
	private JPanel construyeBotoneraLogin(JPanel panelLogin) {
		JPanel panelBotonera = new JPanel();
		GridBagConstraints gbc_panelBotonera = new GridBagConstraints();
		gbc_panelBotonera.anchor = GridBagConstraints.NORTH;
		gbc_panelBotonera.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotonera.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBotonera.gridx = 1;
		gbc_panelBotonera.gridy = 6;
		panelLogin.add(panelBotonera, gbc_panelBotonera);
		panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		return panelBotonera;
	}
	
	private JButton crearBotonLogin(JPanel panelBotonera) {
		JButton botonLogin = new JButton("Login");
		botonLogin.setForeground(new Color(0, 255, 0));
		botonLogin.setFont(new Font("Verdana", Font.BOLD, 10));
		panelBotonera.add(botonLogin);
		return botonLogin;
	}
	
	private JLabel creaLabelUsuarioIncorrecto(JPanel panelLogin) {
		JLabel usuarioContrIncorrecto = new JLabel("Usuario o contraseña incorrectos");
		usuarioContrIncorrecto.setVisible(false);
		usuarioContrIncorrecto.setForeground(new Color(255, 0, 0));
		usuarioContrIncorrecto.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_usuarioContrIncorrecto = new GridBagConstraints();
		gbc_usuarioContrIncorrecto.anchor = GridBagConstraints.WEST;
		gbc_usuarioContrIncorrecto.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioContrIncorrecto.gridx = 1;
		gbc_usuarioContrIncorrecto.gridy = 3;
		panelLogin.add(usuarioContrIncorrecto, gbc_usuarioContrIncorrecto);
		return usuarioContrIncorrecto;
	}
	
	private JButton crearBotonLoginGitHub(JPanel panelBotonera) {
		JButton botonLoginGitHub = new JButton("Login con GitHub");
		botonLoginGitHub.setForeground(new Color(0, 255, 0));
		botonLoginGitHub.setFont(new Font("Verdana", Font.BOLD, 10));
		panelBotonera.add(botonLoginGitHub);
		return botonLoginGitHub;
	}
	
	private JButton crearBotonRegistro() {
		JButton botonRegistro = new JButton("Registro");
		botonRegistro.setFont(new Font("Verdana", Font.BOLD, 10));
		return botonRegistro;
	}
	
	private JButton crearBotonRegistro(JPanel ventana, JPanel panelLogin) {
		JButton botonRegistro = crearBotonRegistro();
		GridBagConstraints gbc_botonRegistro = new GridBagConstraints();
		gbc_botonRegistro.anchor = GridBagConstraints.NORTHEAST;
		gbc_botonRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_botonRegistro.gridx = 1;
		gbc_botonRegistro.gridy = 7;
		panelLogin.add(botonRegistro, gbc_botonRegistro);
		return botonRegistro;
	}
	
	private void crearLabelAppMusic(JPanel panelArriba) {
		JLabel labelAppMusic = new JLabel("AppMusic");
		labelAppMusic.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_labelAppMusic = new GridBagConstraints();
		gbc_labelAppMusic.insets = new Insets(0, 0, 0, 5);
		gbc_labelAppMusic.anchor = GridBagConstraints.WEST;
		gbc_labelAppMusic.gridx = 1;
		gbc_labelAppMusic.gridy = 0;
		panelArriba.add(labelAppMusic, gbc_labelAppMusic);
	}

	private JPanel crearPanelArriba() {
		JPanel panelArriba = new JPanel();
		panelArriba.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(panelArriba, BorderLayout.NORTH);
		GridBagLayout gbl_panelArriba = new GridBagLayout();
		gbl_panelArriba.columnWidths = new int[]{5, 0, 0, 0};
		gbl_panelArriba.rowHeights = new int[]{0, 0};
		gbl_panelArriba.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelArriba.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelArriba.setLayout(gbl_panelArriba);
		return panelArriba;
	}

	private void crearBotonRegistrarse(JTextField textFieldUsuarioR, JPanel panelMensajesUsuario,
			JPasswordField contrasenaFieldR, JLabel labelContrasenaVacia, JTextField textFieldEmailR,
			JLabel labelEmailVacio, JCalendar calendario, JPanel panelBotoneraRegistro) {
		JButton botonRegistrarse = new JButton("Registrarse");
		
		botonRegistrarse.setForeground(new Color(0, 255, 0));
		botonRegistrarse.setFont(new Font("Verdana", Font.BOLD, 10));
		panelBotoneraRegistro.add(botonRegistrarse);
	}

	private void crearBotonVolverALogin(JPanel ventana, JPanel panelBotoneraRegistro) {
		JButton botonVolverALogin = new JButton("Volver a Login");
		botonVolverALogin.setFont(new Font("Verdana", Font.BOLD, 10));
		botonVolverALogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout c1=(CardLayout)ventana.getLayout();
				c1.show(ventana, "login");
			}
		});
		panelBotoneraRegistro.add(botonVolverALogin);
	}

	private JPanel crearPanelBotoneraRegistro(JPanel gridBagRegistro) {
		JPanel panelBotoneraRegistro = new JPanel();
		GridBagConstraints gbc_panelBotoneraRegistro = new GridBagConstraints();
		gbc_panelBotoneraRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotoneraRegistro.fill = GridBagConstraints.BOTH;
		gbc_panelBotoneraRegistro.gridx = 1;
		gbc_panelBotoneraRegistro.gridy = 11;
		gridBagRegistro.add(panelBotoneraRegistro, gbc_panelBotoneraRegistro);
		return panelBotoneraRegistro;
	}

	private JCalendar crearCalendario(JPanel gridBagRegistro) {
		JCalendar calendario = new JCalendar();
		calendario.getYearChooser().getSpinner().setFont(new Font("Verdana", Font.BOLD, 10));
		calendario.getMonthChooser().getComboBox().setFont(new Font("Verdana", Font.BOLD, 10));
		calendario.getDayChooser().getDayPanel().setForeground(new Color(255, 255, 255));
		calendario.getYearChooser().getSpinner().setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_calendario = new GridBagConstraints();
		gbc_calendario.insets = new Insets(0, 0, 5, 5);
		gbc_calendario.fill = GridBagConstraints.BOTH;
		gbc_calendario.gridx = 1;
		gbc_calendario.gridy = 10;
		gridBagRegistro.add(calendario, gbc_calendario);
		return calendario;
	}

	private void crearLabelFechaNacimiento(JPanel gridBagRegistro) {
		JLabel labelFechaNacimiento = new JLabel("Introduce tu fecha de nacimiento: ");
		labelFechaNacimiento.setForeground(new Color(255, 255, 255));
		labelFechaNacimiento.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.WEST;
		gbc_labelFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 9;
		gridBagRegistro.add(labelFechaNacimiento, gbc_labelFechaNacimiento);
	}

	private JLabel crearLabelEmailVacio(JPanel gridBagRegistro_1) {
		JLabel labelEmailVacio = new JLabel("Introduce un email");
		labelEmailVacio.setVisible(false);
		labelEmailVacio.setForeground(new Color(255, 0, 0));
		labelEmailVacio.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_labelEmailVacio = new GridBagConstraints();
		gbc_labelEmailVacio.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmailVacio.gridx = 1;
		gbc_labelEmailVacio.gridy = 8;
		gridBagRegistro_1.add(labelEmailVacio, gbc_labelEmailVacio);
		return labelEmailVacio;
	}

	private JTextField crearTextFieldEmailR(JPanel panelEmailR) {
		JTextField textFieldEmailR;
		textFieldEmailR = new JTextField();
		textFieldEmailR.setFont(new Font("Verdana", Font.BOLD, 10));
		textFieldEmailR.setOpaque(false);
		textFieldEmailR.setBorder(null);
		panelEmailR.add(textFieldEmailR);
		textFieldEmailR.setColumns(10);
		return textFieldEmailR;
	}

	private void creaLabelEmailR(JPanel panelEmailR) {
		JLabel labelEmailR = new JLabel("Email:");
		labelEmailR.setPreferredSize(new Dimension(80, 13));
		labelEmailR.setBorder(null);
		labelEmailR.setFont(new Font("Verdana", Font.BOLD, 10));
		panelEmailR.add(labelEmailR);
	}

	private JPanel creaPanelEmailR(JPanel gridBagRegistro) {
		JPanel panelEmailR = new JPanel();
		panelEmailR.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelEmailR = new GridBagConstraints();
		gbc_panelEmailR.insets = new Insets(0, 0, 5, 5);
		gbc_panelEmailR.fill = GridBagConstraints.BOTH;
		gbc_panelEmailR.gridx = 1;
		gbc_panelEmailR.gridy = 7;
		gridBagRegistro.add(panelEmailR, gbc_panelEmailR);
		panelEmailR.setLayout(new BoxLayout(panelEmailR, BoxLayout.X_AXIS));
		return panelEmailR;
	}

	private JLabel creaLabelContrasenaVacia(JPanel gridBagRegistro_1) {
		JLabel labelContrasenaVacia = new JLabel("Introduce una contraseña");
		labelContrasenaVacia.setVisible(false);
		labelContrasenaVacia.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasenaVacia.setForeground(new Color(255, 0, 0));
		labelContrasenaVacia.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_labelContrasenaVacia = new GridBagConstraints();
		gbc_labelContrasenaVacia.insets = new Insets(0, 0, 5, 5);
		gbc_labelContrasenaVacia.gridx = 1;
		gbc_labelContrasenaVacia.gridy = 6;
		gridBagRegistro_1.add(labelContrasenaVacia, gbc_labelContrasenaVacia);
		return labelContrasenaVacia;
	}

	private JPasswordField creaPasswordFieldR(JPanel panelContrasenaR) {
		JPasswordField contrasenaFieldR = new JPasswordField();
		contrasenaFieldR.setFont(new Font("Verdana", Font.BOLD, 10));
		contrasenaFieldR.setOpaque(false);
		contrasenaFieldR.setBorder(null);
		panelContrasenaR.add(contrasenaFieldR);
		return contrasenaFieldR;
	}

	private void creaLabelContrasenaR(JPanel panelContrasenaR) {
		JLabel labelContrasenaR = new JLabel("Contraseña: ");
		labelContrasenaR.setPreferredSize(new Dimension(80, 13));
		labelContrasenaR.setFont(new Font("Verdana", Font.BOLD, 10));
		panelContrasenaR.add(labelContrasenaR);
	}

	private JPanel creaPanelContrasenaR(JPanel gridBagRegistro) {
		JPanel panelContrasenaR = new JPanel();
		panelContrasenaR.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelContrasenaR.setFont(new Font("Verdana", Font.BOLD, 10));
		GridBagConstraints gbc_panelContrasenaR = new GridBagConstraints();
		gbc_panelContrasenaR.insets = new Insets(0, 0, 5, 5);
		gbc_panelContrasenaR.fill = GridBagConstraints.BOTH;
		gbc_panelContrasenaR.gridx = 1;
		gbc_panelContrasenaR.gridy = 5;
		gridBagRegistro.add(panelContrasenaR, gbc_panelContrasenaR);
		panelContrasenaR.setLayout(new BoxLayout(panelContrasenaR, BoxLayout.X_AXIS));
		return panelContrasenaR;
	}

	private void creaLabelUsuarioVacio(JPanel panelMensajesUsuario) {
		JLabel labelUsuarioVacio = new JLabel("Introduce un nombre de usuario");
		labelUsuarioVacio.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuarioVacio.setForeground(new Color(255, 0, 0));
		labelUsuarioVacio.setFont(new Font("Verdana", Font.BOLD, 10));
		panelMensajesUsuario.add(labelUsuarioVacio, "usuarioVacio");
	}

	private void creaLabelUsuarioExistente(JPanel panelMensajesUsuario) {
		JLabel labelUsuarioExistente = new JLabel("El nombre de usuario ya existe");
		labelUsuarioExistente.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsuarioExistente.setForeground(new Color(255, 0, 0));
		labelUsuarioExistente.setFont(new Font("Verdana", Font.BOLD, 10));
		panelMensajesUsuario.add(labelUsuarioExistente, "usuarioExistente");
	}

	private void creaLabelVacio(JPanel panelMensajesUsuario) {
		JLabel labelVacio = new JLabel("");
		labelVacio.setForeground(new Color(255, 0, 0));
		labelVacio.setFont(new Font("Verdana", Font.BOLD, 10));
		panelMensajesUsuario.add(labelVacio, "labelVacio");
	}

	private JPanel creaPanelMensajesUsuario(JPanel gridBagRegistro) {
		JPanel panelMensajesUsuario = new JPanel();
		panelMensajesUsuario.setPreferredSize(new Dimension(10, 20));
		GridBagConstraints gbc_panelMensajesUsuario = new GridBagConstraints();
		gbc_panelMensajesUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_panelMensajesUsuario.fill = GridBagConstraints.BOTH;
		gbc_panelMensajesUsuario.gridx = 1;
		gbc_panelMensajesUsuario.gridy = 4;
		gridBagRegistro.add(panelMensajesUsuario, gbc_panelMensajesUsuario);
		panelMensajesUsuario.setLayout(new CardLayout(0, 0));
		return panelMensajesUsuario;
	}

	private JTextField creaTextFieldUsuarioR(JPanel panelUsuarioR) {
		JTextField textFieldUsuarioR = new JTextField();
		textFieldUsuarioR.setFont(new Font("Verdana", Font.BOLD, 10));
		textFieldUsuarioR.setBorder(null);
		textFieldUsuarioR.setOpaque(false);
		panelUsuarioR.add(textFieldUsuarioR);
		textFieldUsuarioR.setColumns(10);
		return textFieldUsuarioR;
	}

	private void creaLabelUsuarioR(JPanel panelUsuarioR) {
		JLabel labelUsuarioR = new JLabel("Usuario: ");
		labelUsuarioR.setPreferredSize(new Dimension(80, 13));
		labelUsuarioR.setFont(new Font("Verdana", Font.BOLD, 10));
		panelUsuarioR.add(labelUsuarioR);
	}

	private JPanel creaPanelUsuarioR(JPanel gridBagRegistro) {
		JPanel panelUsuarioR = new JPanel();
		panelUsuarioR.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelUsuarioR = new GridBagConstraints();
		gbc_panelUsuarioR.insets = new Insets(0, 0, 5, 5);
		gbc_panelUsuarioR.fill = GridBagConstraints.BOTH;
		gbc_panelUsuarioR.gridx = 1;
		gbc_panelUsuarioR.gridy = 3;
		gridBagRegistro.add(panelUsuarioR, gbc_panelUsuarioR);
		panelUsuarioR.setLayout(new BoxLayout(panelUsuarioR, BoxLayout.X_AXIS));
		return panelUsuarioR;
	}

	private void creaLabelRegistro(JPanel gridBagRegistro) {
		JLabel labelRegistroR = new JLabel("REGISTRO");
		labelRegistroR.setForeground(new Color(255, 255, 255));
		labelRegistroR.setFont(new Font("Verdana", Font.BOLD, 35));
		GridBagConstraints gbc_labelRegistroR = new GridBagConstraints();
		gbc_labelRegistroR.anchor = GridBagConstraints.NORTH;
		gbc_labelRegistroR.insets = new Insets(0, 0, 5, 5);
		gbc_labelRegistroR.gridx = 1;
		gbc_labelRegistroR.gridy = 1;
		gridBagRegistro.add(labelRegistroR, gbc_labelRegistroR);
	}

	private JPanel crearGridBagRegistro(JPanel registro) {
		JPanel gridBagRegistro_1;
		gridBagRegistro_1 = new JPanel();
		registro.add(gridBagRegistro_1, BorderLayout.CENTER);
		GridBagLayout gbl_gridBagRegistro_1 = new GridBagLayout();
		gbl_gridBagRegistro_1.columnWidths = new int[]{5, 0, 0, 0};
		gbl_gridBagRegistro_1.rowHeights = new int[]{5, 0, 10, 0, 20, 0, 20, 0, 0, 30, 0, 0, 0};
		gbl_gridBagRegistro_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_gridBagRegistro_1.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagRegistro_1.setLayout(gbl_gridBagRegistro_1);
		return gridBagRegistro_1;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// INICIALIZACIÓN JFRAME Y CARDLAYOUR
		inicializarJFrame();
		JPanel ventana=creaPanelVentana();
		JPanel login=crearPanelLogin(ventana);
		JPanel registro=crearPanelRegistro(ventana);
		
		// PANEL DE LOGIN
		JPanel panelLogin=gridBagLayourLogin(login);
		iconoYLabelAppMusic(panelLogin);
		
		// PANEL PARA INTRODUCIR USUARIO
		JPanel panelUsuarioLogin=construyeCampoUsuario(panelLogin);
		insertaLabelUsuario(panelUsuarioLogin);
		JTextField textFieldUsuarioL=insertaTextFieldUsuario(panelUsuarioLogin);
		
		// PANEL PARA INTRODUCIR CONTRASEÑA
		JPanel panelContrasenaLogin=construyeCampoContrasena(panelLogin);
		insertaLabelContrasena(panelContrasenaLogin);
		JPasswordField contrasenaFieldL=insertaPasswordField(panelContrasenaLogin);
		
		// LABEL PARA USUARIOS INCORRECTOS
		JLabel labelUsuarioContrasenaIncorrecto=creaLabelUsuarioIncorrecto(panelLogin);
		
		// PANEL PARA LOS BOTONES DE LOGIN Y LOGIN CON GITHUB
		JPanel panelBotonera=construyeBotoneraLogin(panelLogin);
		
		// BOTÓN LOGIN Y ACTION LISTENER
		JButton botonLogin=crearBotonLogin(panelBotonera);
		
		
		// BOTÓN LOGIN CON GITHUB
		JButton botonLoginGitHub=crearBotonLoginGitHub(panelBotonera);
		
		// BOTÓN REGISTRO Y ACTION LISTENER
		JButton botonRegistro=crearBotonRegistro(ventana, panelLogin);
		botonRegistro.addActionListener(e->
		{
			CardLayout c1=(CardLayout)ventana.getLayout();
			c1.show(ventana, "registro");
		});
		
		JPanel gridBagRegistro_1 = crearGridBagRegistro(registro);
		
		creaLabelRegistro(gridBagRegistro_1);
		
		JPanel panelUsuarioR = creaPanelUsuarioR(gridBagRegistro_1);
		
		creaLabelUsuarioR(panelUsuarioR);
		
		JTextField textFieldUsuarioR = creaTextFieldUsuarioR(panelUsuarioR);
		
		JPanel panelMensajesUsuario = creaPanelMensajesUsuario(gridBagRegistro_1);
		
		creaLabelVacio(panelMensajesUsuario);
		
		creaLabelUsuarioExistente(panelMensajesUsuario);
		
		creaLabelUsuarioVacio(panelMensajesUsuario);
		
		JPanel panelContrasenaR = creaPanelContrasenaR(gridBagRegistro_1);
		
		creaLabelContrasenaR(panelContrasenaR);
		
		JPasswordField contrasenaFieldR = creaPasswordFieldR(panelContrasenaR);
		
		JLabel labelContrasenaVacia = creaLabelContrasenaVacia(gridBagRegistro_1);
		
		JPanel panelEmailR = creaPanelEmailR(gridBagRegistro_1);
		
		creaLabelEmailR(panelEmailR);
		
		JTextField textFieldEmailR = crearTextFieldEmailR(panelEmailR);

		JLabel labelEmailVacio = crearLabelEmailVacio(gridBagRegistro_1);
		
		crearLabelFechaNacimiento(gridBagRegistro_1);
		
		JCalendar calendario = crearCalendario(gridBagRegistro_1);
		
		JPanel panelBotoneraRegistro = crearPanelBotoneraRegistro(gridBagRegistro_1);
		
		crearBotonVolverALogin(ventana, panelBotoneraRegistro);
		
		crearBotonRegistrarse(textFieldUsuarioR, panelMensajesUsuario, contrasenaFieldR, labelContrasenaVacia,
				textFieldEmailR, labelEmailVacio, calendario, panelBotoneraRegistro);
		
		JPanel panelArriba = crearPanelArriba();
		
		crearLabelAppMusic(panelArriba);
		
	}

}
