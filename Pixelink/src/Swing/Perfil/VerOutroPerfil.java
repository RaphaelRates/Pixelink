package Swing.Perfil;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import Swing.Componentes.CircularImagePanel;
import data.Dados;
import entidades.Seguidor;
import entidades.Usuario;

public class VerOutroPerfil extends JPanel {
	private Usuario usuario;
	private Usuario observer;
	private JFrame frame;
	private Image fundo;
	private JLabel lblSeguidores;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice[] gd = ge.getScreenDevices();
	private DisplayMode mode = gd[0].getDisplayMode();
	private static final long serialVersionUID = 1L;
	private JPanel painelPerfil;
	LineBorder bordaRoxa = new LineBorder(Color.decode("#FF0070"), 5, false);

	
	public VerOutroPerfil(Usuario usuario, Usuario observer) {
		try {
			fundo = ImageIO.read(getClass().getResource("/img/fundoVerOutroPerfil.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.observer = observer;
		setSize(mode.getWidth() - 150, mode.getHeight());
		this.usuario = usuario;
		initialize();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (fundo != null) {
			g.drawImage(fundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(fundo.getWidth(this), fundo.getHeight(this));
	}

	private void initialize() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		painelPerfil = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, painelPerfil, 59, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, painelPerfil, 100, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, painelPerfil, 320, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, painelPerfil, 1100, SpringLayout.WEST, this);
		painelPerfil.setBackground(Color.decode("#FF0070"));
		painelPerfil.setBorder(bordaRoxa);
		painelPerfil.setOpaque(true);

		add(painelPerfil);
		SpringLayout sl_painelPerfil = new SpringLayout();
		painelPerfil.setLayout(sl_painelPerfil);

		CircularImagePanel imgPerfil = new CircularImagePanel(observer.getFotoDoPerfil().toString(), 110);
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, imgPerfil, 17, SpringLayout.NORTH, painelPerfil);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, imgPerfil, 67, SpringLayout.WEST, painelPerfil);
		painelPerfil.add(imgPerfil);

		JLabel lblNome = new JLabel(observer.getName());
		lblNome.setForeground(Color.white);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblNome, 34, SpringLayout.EAST, imgPerfil);
		lblNome.setFont(Dados.loadFontFromFile(25));
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblNome, 43, SpringLayout.NORTH, painelPerfil);
		painelPerfil.add(lblNome);

		JLabel lblID = new JLabel(observer.getID());
		lblID.setFont(new Font("Arial", Font.BOLD, 15));
		lblID.setForeground(Color.white);
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblID, 33, SpringLayout.SOUTH, lblNome);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblID, 0, SpringLayout.WEST, lblNome);
		painelPerfil.add(lblID);

		lblSeguidores = new JLabel(observer.getListaSeguidores().size() + " Seguidores");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblSeguidores, 3, SpringLayout.NORTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblSeguidores, 81, SpringLayout.EAST, lblID);
		lblSeguidores.setForeground(Color.white);
		lblSeguidores.setFont(Dados.loadFontFromFile(20));
		painelPerfil.add(lblSeguidores);

		JLabel lblSeguindo = new JLabel(observer.getListaSeguindo().size() + " Seguindo");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblSeguindo, 3, SpringLayout.NORTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblSeguindo, 81, SpringLayout.EAST, lblSeguidores);
		lblSeguindo.setForeground(Color.white);
		lblSeguindo.setFont(Dados.loadFontFromFile(20));
		painelPerfil.add(lblSeguindo);

		JTextArea biografiaTextArea = new JTextArea(observer.getBiografia());
		biografiaTextArea.setEditable(false);
		biografiaTextArea.setBorder(null);
		biografiaTextArea.setLineWrap(true);
		biografiaTextArea.setWrapStyleWord(true);
		biografiaTextArea.setColumns(35);
		biografiaTextArea.setRows(5);
		biografiaTextArea.setForeground(Color.white);
		biografiaTextArea.setBackground(new Color(0, 0, 0, 0));
		biografiaTextArea.setFont(new Font("Arial", Font.BOLD, 17));

		JScrollPane biografiaScroll = new JScrollPane(biografiaTextArea);
		biografiaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		biografiaScroll.setBorder(null);
		biografiaTextArea.setBackground(Color.decode("#FF0070"));
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, biografiaScroll, 33, SpringLayout.SOUTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, biografiaScroll, 0, SpringLayout.WEST, lblNome);
		painelPerfil.add(biografiaScroll);

		JButton btnSeguir = criaBotao();
		if (usuario.verificarSeguidor(new Seguidor(observer))) {
			btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/btnSeguindo.png")));
		} else {
			btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/seguir.png")));
		}
		btnSeguir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!usuario.verificarSeguidor(new Seguidor(observer))) {
					usuario.seguir(new Seguidor(observer));
					usuario.setSeguidores(usuario.getSeguidores() + 1);
					Seguidor seguidor = new Seguidor(observer);
					seguidor.update("seguidor");
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/btnSeguindo.png")));
					usuario.enviarNotificacao(seguidor, "seguidor");
					
				} else {
					usuario.deixarDeSeguir(new Seguidor(observer));
					usuario.setSeguidores(usuario.getSeguidores() - 1);
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/seguir.png")));
					
				}
				
				atualizarNumeroSeguidores();

			}
		});
		btnSeguir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (usuario.verificarSeguidor(new Seguidor(observer))) {
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/btnSeguindoClicado.png")));
				} else {
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/seguirClicado.png")));
				}
				btnSeguir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (usuario.verificarSeguidor(new Seguidor(observer))) {
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/btnSeguindo.png")));
				} else {
					btnSeguir.setIcon(new ImageIcon(getClass().getResource("/img/seguir.png")));
				}
				btnSeguir.setCursor(Cursor.getDefaultCursor());
			}
		});

		
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, btnSeguir, 36, SpringLayout.NORTH, painelPerfil);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, btnSeguir, 48, SpringLayout.EAST, lblNome);
		painelPerfil.add(btnSeguir);

		CarrocelDePostsOutroUser postsUsuario = new CarrocelDePostsOutroUser(observer);
		springLayout.putConstraint(SpringLayout.NORTH, postsUsuario, 0, SpringLayout.SOUTH, painelPerfil);
		springLayout.putConstraint(SpringLayout.WEST, postsUsuario, 0, SpringLayout.WEST, painelPerfil);
		springLayout.putConstraint(SpringLayout.SOUTH, postsUsuario, 384, SpringLayout.SOUTH, painelPerfil);
		springLayout.putConstraint(SpringLayout.EAST, postsUsuario, 0, SpringLayout.EAST, painelPerfil);
		add(postsUsuario);

	}
	
	private void atualizarNumeroSeguidores() {
	    lblSeguidores.setText(observer.getListaSeguidores().size() + " Seguidores");
	    painelPerfil.repaint();
	}

	private JButton criaBotao() {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(103, 43));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		return button;
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
