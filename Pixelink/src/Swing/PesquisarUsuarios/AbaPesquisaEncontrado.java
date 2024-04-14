package Swing.PesquisarUsuarios;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import Swing.Feed.FeedDeNoticias;
import Swing.Perfil.VerOutroPerfil;
import Swing.Perfil.VerProprioPerfil;
import entidades.Usuario;

public class AbaPesquisaEncontrado extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario usuarioEncontrado;
	private Usuario usuario;
	private JButton botaoVerPerfil = new JButton();
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice[] gd = ge.getScreenDevices();
	DisplayMode mode = gd[0].getDisplayMode();

	public AbaPesquisaEncontrado(Usuario usuarioEncontrado, Usuario usuario) {
		setBackground(Color.white);
		this.usuarioEncontrado = usuarioEncontrado;
		this.usuario = usuario;
		initialize();
	}

	private void initialize() {
		ImageIcon imgPerfil = new ImageIcon(usuarioEncontrado.getFotoDoPerfil().toString());
		Image imgPerfilRed = imgPerfil.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JLabel imagemPerfil = new JLabel(new ImageIcon(imgPerfilRed));
		springLayout.putConstraint(SpringLayout.NORTH, imagemPerfil, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, imagemPerfil, 43, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, imagemPerfil, 88, SpringLayout.NORTH, this);
		add(imagemPerfil);

		springLayout.putConstraint(SpringLayout.SOUTH, botaoVerPerfil, 68, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, botaoVerPerfil, -10, SpringLayout.EAST, this);

		botaoVerPerfil.setBorderPainted(false);
		botaoVerPerfil.setFocusPainted(false);
		botaoVerPerfil.setContentAreaFilled(false);
		botaoVerPerfil.setIcon(new ImageIcon(AbaPesquisaEncontrado.class.getResource("/img/verPerfil.png")));
		botaoVerPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botaoVerPerfil
						.setIcon(new ImageIcon(AbaPesquisaEncontrado.class.getResource("/img/verPerfilClicado.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botaoVerPerfil.setIcon(new ImageIcon(AbaPesquisaEncontrado.class.getResource("/img/verPerfil.png")));
			}
		});
		botaoVerPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuarioEncontrado.getID().equals(usuario.getID())) {
					VerProprioPerfil verProrpioPerfil = new VerProprioPerfil(usuario);
					FeedDeNoticias.adicionarPainel(verProrpioPerfil);
				} else {
					VerOutroPerfil verOutroPerfil = new VerOutroPerfil(usuario, usuarioEncontrado);
					FeedDeNoticias.adicionarPainel(verOutroPerfil);
				}
			}
		});
		add(botaoVerPerfil);

		Font fonteLabel = new Font("Arial", Font.BOLD, 22);
		JLabel lblNome = new JLabel(usuarioEncontrado.getName());
		springLayout.putConstraint(SpringLayout.NORTH, botaoVerPerfil, 0, SpringLayout.NORTH, lblNome);
		springLayout.putConstraint(SpringLayout.WEST, botaoVerPerfil, 83, SpringLayout.EAST, lblNome);
		springLayout.putConstraint(SpringLayout.EAST, imagemPerfil, -22, SpringLayout.WEST, lblNome);
		springLayout.putConstraint(SpringLayout.NORTH, lblNome, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNome, 144, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNome, 334, SpringLayout.WEST, this);
		lblNome.setFont(fonteLabel);
		add(lblNome);

		JLabel lblID = new JLabel(usuarioEncontrado.getID());
		springLayout.putConstraint(SpringLayout.NORTH, lblID, 6, SpringLayout.SOUTH, lblNome);
		springLayout.putConstraint(SpringLayout.WEST, lblID, 10, SpringLayout.WEST, lblNome);
		springLayout.putConstraint(SpringLayout.EAST, lblID, 309, SpringLayout.WEST, this);
		add(lblID);
	}

}
