package Swing.Login;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import Swing.Cadastro.Cadastro;
import Swing.Componentes.Alert;
import Swing.Componentes.ImagePanel;
import Swing.Feed.FeedDeNoticias;
import data.Dados;
import entidades.Usuario;

public class Login {

	private JFrame frmPixelink;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel cadeadoIcon;
	private JLabel usuarioIcon;
	private JLabel lblTituloSenha;
	private ImagePanel painelCentral;
	private SpringLayout sl_painelCentral;
	private JLabel tituloSemConta;
	private JLabel lblLogoPixelink;
	private ImagePanel panel;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] gd = ge.getScreenDevices();
	DisplayMode mode = gd[0].getDisplayMode();

	public Login() {
		initialize();

	}

	private void initialize() {
		frmPixelink = new JFrame();
		frmPixelink.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPixelink.setForeground(Color.BLACK);
		frmPixelink.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));
		frmPixelink.setTitle("Pixelink");
		frmPixelink.setSize(mode.getWidth(), mode.getHeight());
		frmPixelink.getContentPane().setSize(mode.getWidth(), mode.getHeight());
		frmPixelink.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPixelink.getContentPane().setLayout(null);

		panel = new ImagePanel("/img/fundoLogin.png");
		panel.setBounds(0, 0, mode.getWidth(), mode.getHeight());

		frmPixelink.getContentPane().add(panel);
		panel.setLayout(null);
		painelCentral = new ImagePanel("/img/painelLogin.png");
		painelCentral.setBounds(297, 142, 756, 445);
		panel.add(painelCentral);

		int panelX = (panel.getWidth() - painelCentral.getWidth()) / 2;
		int panelY = (panel.getHeight() - painelCentral.getHeight()) / 2;
		painelCentral.setLocation(panelX, panelY);

		sl_painelCentral = new SpringLayout();
		painelCentral.setLayout(sl_painelCentral);

		JLabel tituloLogin = new JLabel("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, tituloLogin, 25, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, tituloLogin, 35, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloLogin, -380, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, tituloLogin, -560, SpringLayout.EAST, painelCentral);
		tituloLogin.setIcon(new ImageIcon(Login.class.getResource("/img/tituloLogin.png")));
		painelCentral.add(tituloLogin);

		textField = new JTextField();
		sl_painelCentral.putConstraint(SpringLayout.NORTH, textField, -325, SpringLayout.SOUTH, painelCentral);
		textField.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.WEST, textField, 123, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, textField, -282, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, textField, -360, SpringLayout.EAST, painelCentral);
		painelCentral.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		sl_painelCentral.putConstraint(SpringLayout.NORTH, passwordField, -215, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, passwordField, 123, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, passwordField, -171, SpringLayout.SOUTH, painelCentral);
		passwordField.setBorder(new LineBorder(Color.BLACK, 3, false));
		sl_painelCentral.putConstraint(SpringLayout.EAST, passwordField, -360, SpringLayout.EAST, painelCentral);
		passwordField.setColumns(10);
		painelCentral.add(passwordField);

		cadeadoIcon = new JLabel("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, cadeadoIcon, 0, SpringLayout.NORTH, passwordField);
		sl_painelCentral.putConstraint(SpringLayout.WEST, cadeadoIcon, 75, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, cadeadoIcon, 0, SpringLayout.SOUTH, passwordField);
		sl_painelCentral.putConstraint(SpringLayout.EAST, cadeadoIcon, -20, SpringLayout.WEST, passwordField);
		cadeadoIcon.setIcon(new ImageIcon(Login.class.getResource("/img/cadeado.png")));
		painelCentral.add(cadeadoIcon);

		usuarioIcon = new JLabel("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, usuarioIcon, 0, SpringLayout.NORTH, textField);
		sl_painelCentral.putConstraint(SpringLayout.WEST, usuarioIcon, 70, SpringLayout.WEST, painelCentral);
		usuarioIcon.setIcon(new ImageIcon(Login.class.getResource("/img/usuarioIcon.png")));
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, usuarioIcon, 0, SpringLayout.SOUTH, textField);
		sl_painelCentral.putConstraint(SpringLayout.EAST, usuarioIcon, -10, SpringLayout.WEST, textField);
		painelCentral.add(usuarioIcon);

		JLabel lblTituloEmail = new JLabel("E-mail ou ID:");
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, lblTituloEmail, -6, SpringLayout.NORTH, textField);
		lblTituloEmail.setFont(Dados.loadFontFromFile(25));
		sl_painelCentral.putConstraint(SpringLayout.WEST, lblTituloEmail, 0, SpringLayout.WEST, textField);
		painelCentral.add(lblTituloEmail);

		lblTituloSenha = new JLabel("Senha:");
		sl_painelCentral.putConstraint(SpringLayout.WEST, lblTituloSenha, 0, SpringLayout.WEST, textField);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, lblTituloSenha, -6, SpringLayout.NORTH, passwordField);
		lblTituloSenha.setFont(Dados.loadFontFromFile(25));
		painelCentral.add(lblTituloSenha);

		JButton Cadastrar = createButton("/Img/botãoCadastrar.png", "/Img/botãoCadastrarClicado.png");
		sl_painelCentral.putConstraint(SpringLayout.WEST, Cadastrar, 65, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, Cadastrar, -60, SpringLayout.SOUTH, painelCentral);
		Cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				cadastro.getFrame().setVisible(true);
				frmPixelink.dispose();
			}
		});

		painelCentral.add(Cadastrar);

		JButton Entrar = createButton("/Img/botão Entrar.png", "/Img/botão EntrarClicado.png");
		sl_painelCentral.putConstraint(SpringLayout.WEST, Entrar, 36, SpringLayout.EAST, Cadastrar);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, Entrar, 0, SpringLayout.SOUTH, Cadastrar);
		Entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					Alert.exibirAlerta("Por favor, verifique se os campos estão devidamente preenchidos.");
				} else if (!(username.endsWith("@gmail.com") || username.endsWith("@hotmail.com")
						|| (username.startsWith("#") && username.length() == 6))
						|| Character.isDigit(username.charAt(0))) {
					Alert.exibirAlerta("Formato de E-mail ou ID inválido inválido!");
				} else if (!Dados.verificarUsuarioExistente(username, password)) {
					Alert.exibirAlerta("Conta não existente ou senha incorreta");
				} else if (Dados.verificarUsuarioExistente(username, password)) {
					Dados.Logar(username, password);
					Usuario logado = Dados.getLogado();
					FeedDeNoticias feed = new FeedDeNoticias(logado);
					feed.getFrame().setVisible(true);
					frmPixelink.dispose();
				}
			}
		});
		painelCentral.add(Entrar);

		tituloSemConta = new JLabel("Não tem uma conta?");
		tituloSemConta.setFont(Dados.loadFontFromFile(15));
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, tituloSemConta, -5, SpringLayout.NORTH, Cadastrar);
		sl_painelCentral.putConstraint(SpringLayout.WEST, tituloSemConta, 99, SpringLayout.WEST, painelCentral);
		painelCentral.add(tituloSemConta);

		lblLogoPixelink = new JLabel("");
		sl_painelCentral.putConstraint(SpringLayout.NORTH, lblLogoPixelink, 59, SpringLayout.NORTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.WEST, lblLogoPixelink, 485, SpringLayout.WEST, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.SOUTH, lblLogoPixelink, -130, SpringLayout.SOUTH, painelCentral);
		sl_painelCentral.putConstraint(SpringLayout.EAST, lblLogoPixelink, -60, SpringLayout.EAST, painelCentral);
		lblLogoPixelink.setIcon(new ImageIcon(Login.class.getResource("/img/iconLoginPixelink.png")));
		painelCentral.add(lblLogoPixelink);

	}

	public JFrame getFrame() {
		return frmPixelink;
	}

	private JButton createButton(String defaultIconPath, String clickedIconPath) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(200, 50));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		ImageIcon defaultIcon = new ImageIcon(getClass().getResource(defaultIconPath));
		ImageIcon clickedIcon = new ImageIcon(getClass().getResource(clickedIconPath));
		Image scaledDefaultImage = defaultIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		Image scaledClickedImage = clickedIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(scaledDefaultImage));
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(new ImageIcon(scaledClickedImage));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(new ImageIcon(scaledDefaultImage));
				button.setCursor(Cursor.getDefaultCursor());
			}
		});
		return button;
	}
}