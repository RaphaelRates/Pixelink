package Swing.Cadastro;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Swing.Componentes.Alert;
import Swing.Componentes.ImagePanel;
import Swing.Login.Login;
import data.Dados;
import entidades.Usuario;

public class Cadastro {

	private JFrame frmPixelink;
	private JButton Cadastrar;
	private JButton Voltar;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice[] gd = ge.getScreenDevices();
	private DisplayMode mode = gd[0].getDisplayMode();
	private JTextField textNome;
	private ImageIcon fotoPerfil;
	private JTextField textEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordField_verificador;

	public Cadastro() {
		initialize();
	}

	private void initialize() {
		frmPixelink = new JFrame();
		frmPixelink.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPixelink.setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro.class.getResource("/img/logo.png")));
		frmPixelink.setTitle("Cadastrar Usuário");
		frmPixelink.setSize(mode.getWidth(), mode.getHeight());
		SpringLayout springLayout = new SpringLayout();
		frmPixelink.getContentPane().setLayout(springLayout);
		fotoPerfil = new ImageIcon("src\\img\\fotoPerfilPadrão.png");

		ImagePanel fundoPrincipal = new ImagePanel("/img/FundoCadastro.png");
		springLayout.putConstraint(SpringLayout.NORTH, fundoPrincipal, 0, SpringLayout.NORTH,
				frmPixelink.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, fundoPrincipal, 0, SpringLayout.WEST,
				frmPixelink.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, fundoPrincipal, 0, SpringLayout.SOUTH,
				frmPixelink.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, fundoPrincipal, 0, SpringLayout.EAST,
				frmPixelink.getContentPane());
		frmPixelink.getContentPane().add(fundoPrincipal);
		fundoPrincipal.setLayout(null);

		ImagePanel painelCentral = new ImagePanel("/img/fundoCentralCadastro.png");
		painelCentral.setBounds(300, 102, 743, 542);
		int panelX = (mode.getWidth() - painelCentral.getWidth()) / 2;
		int panelY = (mode.getHeight() - painelCentral.getHeight()) / 2;
		painelCentral.setLocation(panelX, panelY);

		fundoPrincipal.add(painelCentral);
		SpringLayout sl_painelCentral = new SpringLayout();
		painelCentral.setLayout(sl_painelCentral);

		JLabel lblCriarTitulo = new JLabel("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, lblCriarTitulo, 45, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, lblCriarTitulo, 45, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, lblCriarTitulo, -460, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, lblCriarTitulo, -400, SpringLayout.EAST, painelCentral);
		lblCriarTitulo.setIcon(new ImageIcon(Cadastro.class.getResource("/img/tituloCriarConta.png")));
		painelCentral.add(lblCriarTitulo);

		JLabel lblTituloNome = new JLabel("Nome de Usuário:");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, lblTituloNome, 200, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, lblTituloNome, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, lblTituloNome, 220, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, lblTituloNome, -450, SpringLayout.EAST, painelCentral);
		lblTituloNome.setFont(Dados.loadFontFromFile(25));
		painelCentral.add(lblTituloNome);

		textNome = new JTextField();
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, textNome, -280, SpringLayout.SOUTH, painelCentral);
		textNome.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.WEST, textNome, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.NORTH, textNome, 223, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, textNome, -400, SpringLayout.EAST, painelCentral);
		textNome.setColumns(10);
		painelCentral.add(textNome);

		JLabel tituloEmail = new JLabel("Crie um E-mail:");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, tituloEmail, 105, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, tituloEmail, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloEmail, -415, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, tituloEmail, -490, SpringLayout.EAST, painelCentral);
		tituloEmail.setFont(Dados.loadFontFromFile(25));
		painelCentral.add(tituloEmail);

		textEmail = new JTextField();
		sl_painelCentral.putConstraint(SpringLayout.NORTH, textEmail, -410, SpringLayout.SOUTH, painelCentral);
		textEmail.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.WEST, textEmail, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, textEmail, 170, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, textEmail, -400, SpringLayout.EAST, painelCentral);
		painelCentral.add(textEmail);
		textEmail.setColumns(10);

		JLabel tituloSenha = new JLabel("Crie uma senha:");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, tituloSenha, 290, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloSenha, -230, SpringLayout.SOUTH, painelCentral);
		tituloSenha.setFont(Dados.loadFontFromFile(25));
		sl_painelCentral.putConstraint(SpringLayout.WEST, tituloSenha, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, tituloSenha, -450, SpringLayout.EAST, painelCentral);
		painelCentral.add(tituloSenha);

		passwordField = new JPasswordField();
		sl_painelCentral.putConstraint(SpringLayout.NORTH, passwordField, 320, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, passwordField, -183, SpringLayout.SOUTH, painelCentral);
		passwordField.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.WEST, passwordField, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, passwordField, -400, SpringLayout.EAST, painelCentral);
		painelCentral.add(passwordField);
		passwordField.setColumns(10);

		JLabel tituloValidador = new JLabel("Confirmar senha:");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, tituloValidador, 380, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, tituloValidador, 70, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloValidador, -130, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, tituloValidador, -400, SpringLayout.EAST, painelCentral);
		tituloValidador.setFont(Dados.loadFontFromFile(25));
		painelCentral.add(tituloValidador);

		passwordField_verificador = new JPasswordField();
		sl_painelCentral.putConstraint(SpringLayout.NORTH, passwordField_verificador, 410, SpringLayout.NORTH,
				painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, passwordField_verificador, -92, SpringLayout.SOUTH,
				painelCentral);
		passwordField_verificador.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.WEST, passwordField_verificador, 70, SpringLayout.WEST,
				painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, passwordField_verificador, -400, SpringLayout.EAST,
				painelCentral);
		painelCentral.add(passwordField_verificador);
		passwordField_verificador.setColumns(10);

		JButton inserirFotoPerfil = new JButton("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, inserirFotoPerfil, 130, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, inserirFotoPerfil, 450, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, inserirFotoPerfil, -210, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, inserirFotoPerfil, -90, SpringLayout.EAST, painelCentral);
		ImageIcon img = new ImageIcon(Cadastro.class.getResource("/Img/botãoFotoPerfil.png"));
		Image imgRedimensionada = img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon img2 = new ImageIcon(Cadastro.class.getResource("/Img/botãoFotoPerfilClicado.png"));
		Image imgRedimensionada2 = img2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		inserirFotoPerfil.setIcon(new ImageIcon(imgRedimensionada));
		inserirFotoPerfil.setBorderPainted(false);
		inserirFotoPerfil.setFocusPainted(false);
		inserirFotoPerfil.setContentAreaFilled(false);
		inserirFotoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				inserirFotoPerfil.setIcon(new ImageIcon(imgRedimensionada));
				inserirFotoPerfil.setCursor(Cursor.getDefaultCursor());
			}
		});
		inserirFotoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inserirFotoPerfil.setIcon(new ImageIcon(imgRedimensionada2));
				inserirFotoPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		inserirFotoPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("escolha uma foto de perfil");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens (*.jpeg, *.png, *.jpg)", "jpg",
						"jpeg", "png");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(frmPixelink);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					for (MouseListener listener : inserirFotoPerfil.getMouseListeners()) {
						inserirFotoPerfil.removeMouseListener(listener);
					}
					java.io.File file = fileChooser.getSelectedFile();
					ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
					Image imageIconRedmensionada = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					setFotoPerfil(imageIcon);
					inserirFotoPerfil.setIcon(new ImageIcon(imageIconRedmensionada));
				}

				inserirFotoPerfil.addMouseListener(this);
			}
		});
		painelCentral.add(inserirFotoPerfil);

		JLabel tituloFotoPerfil = new JLabel("Foto de Perfil(Opcional)");
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloFotoPerfil, -10, SpringLayout.NORTH,
				inserirFotoPerfil);
		sl_painelCentral.putConstraint(SpringLayout.EAST, tituloFotoPerfil, -110, SpringLayout.EAST, painelCentral);
		tituloFotoPerfil.setFont(Dados.loadFontFromFile(16));
		painelCentral.add(tituloFotoPerfil);

		Cadastrar = new JButton("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, Cadastrar, 15, SpringLayout.SOUTH, inserirFotoPerfil);
		sl_painelCentral.putConstraint(SpringLayout.EAST, Cadastrar, -91, SpringLayout.EAST, painelCentral);
		Cadastrar.setBorderPainted(false);
		Cadastrar.setFocusPainted(false);
		Cadastrar.setContentAreaFilled(false);
		ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/Img/botãoCadastrar.png"));
		ImageIcon clickedIcon = new ImageIcon(getClass().getResource("/Img/botãoCadastrarClicado.png"));
		Image scaledDefaultImage = defaultIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		Image scaledClickedImage = clickedIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		Cadastrar.setPreferredSize(new Dimension(200, 50));
		Cadastrar.setIcon(new ImageIcon(scaledDefaultImage));
		Cadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cadastrar.setIcon(new ImageIcon(scaledClickedImage));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cadastrar.setIcon(new ImageIcon(scaledDefaultImage));
			}
		});
		Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Email = textEmail.getText();
				String Name = textNome.getText();
				String password = new String(passwordField.getPassword());
				String password_validator = new String(passwordField_verificador.getPassword());

				if (Name.isEmpty() || Email.isEmpty() || password.isEmpty() || password_validator.isEmpty()) {
					Alert.exibirAlerta("Preencha todos os campos obrigatórios!");
				} else if (!(Email.endsWith("@gmail.com") || Email.endsWith("@hotmail.com"))
						|| Character.isDigit(Email.charAt(0))) {
					Alert.exibirAlerta("Formato de E-mail ou ID inválido inválido.");
				} else if (!password.equals(password_validator)) {
					Alert.exibirAlerta("As senhas não correspondem");
				} else if (password.length() < 8) {
					Alert.exibirAlerta("A senha deve conter no minimo de 8 caracteres");
				} else if (Name.length() > 30) {
					Alert.exibirAlerta("O nome de usuario não pode conter mais de 30 caracteres!");
				} else if (Dados.verificarUsuarioExistente(Email, password)) {
					Alert.exibirAlerta("Este usuário já existe!");
					Login voltarLogin = new Login();
					voltarLogin.getFrame().setVisible(true);
					frmPixelink.dispose();
				} else {
					Usuario u = new Usuario(Name, Email, password, fotoPerfil);
					Dados.CriarConta(u);
					System.out.println(u.getFotoDoPerfil() + "  aoba");
					Alert.exibirInformacao("Cadastrado com sucesso!");
					Login voltarLogin = new Login();
					voltarLogin.getFrame().setVisible(true);
					frmPixelink.dispose();
				}
			}
		});
		painelCentral.add(Cadastrar);

		Voltar = new JButton("");
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, Voltar, -85, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, Voltar, -91, SpringLayout.EAST, painelCentral);
		Voltar.setPreferredSize(new Dimension(200, 50));
		ImageIcon defaultIcon1 = new ImageIcon(getClass().getResource("/Img/botãoVoltar.png"));
		ImageIcon clickedIcon1 = new ImageIcon(getClass().getResource("/Img/botãoVoltarClicado.png"));
		Image imagemRedimensionada1 = defaultIcon1.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		Image imagemRedimensionada2 = clickedIcon1.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		Voltar.setIcon(new ImageIcon(imagemRedimensionada1));
		Voltar.setBorderPainted(false);
		Voltar.setFocusPainted(false);
		Voltar.setContentAreaFilled(false);
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login voltarAoLogin = new Login();
				voltarAoLogin.getFrame().setVisible(true);
				frmPixelink.dispose();
			}
		});

		Voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Voltar.setIcon(new ImageIcon(imagemRedimensionada2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Voltar.setIcon(new ImageIcon(imagemRedimensionada1));
			}
		});
		painelCentral.add(Voltar);

	}

	public JFrame getFrame() {
		return frmPixelink;
	}

	public ImageIcon getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(ImageIcon fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
}