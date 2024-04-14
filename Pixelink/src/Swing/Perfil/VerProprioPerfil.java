package Swing.Perfil;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Swing.Componentes.CircularImagePanel;
import Swing.Componentes.DialogEditar;
import Swing.Componentes.DialogImagem;
import Swing.Feed.FeedDeNoticias;
import Swing.Login.Login;
import data.Dados;
import entidades.Usuario;

public class VerProprioPerfil extends JPanel {
	private Usuario usuario;
	private JFrame frame;
	private Image fundo;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice[] gd = ge.getScreenDevices();
	private DisplayMode mode = gd[0].getDisplayMode();
	private static final long serialVersionUID = 1L;
	private JPanel painelPerfil;
	LineBorder bordaRoxa = new LineBorder(Color.decode("#7304D7"), 5, false);

	public VerProprioPerfil(Usuario usuario) {
		try {
            fundo = ImageIO.read(getClass().getResource("/img/fundoPesquisar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		this.usuario = Dados.getLogado();
		setSize(new Dimension(mode.getWidth(), mode.getHeight()));
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
		painelPerfil.setBackground(Color.decode("#7304D7"));
		painelPerfil.setBorder(bordaRoxa);
		painelPerfil.setOpaque(true);

		add(painelPerfil);
		SpringLayout sl_painelPerfil = new SpringLayout();
		painelPerfil.setLayout(sl_painelPerfil);

		CircularImagePanel imgPerfil = new CircularImagePanel(usuario.getFotoDoPerfil().toString(), 110);
		imgPerfil.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new DialogImagem((Frame) SwingUtilities.getWindowAncestor(imgPerfil),usuario.getFotoDoPerfil(), 3);
			}
		
        	@Override
			public void mouseEntered(MouseEvent e) {
        		imgPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				imgPerfil.setCursor(Cursor.getDefaultCursor());
			}
           
			
		});
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, imgPerfil, 17, SpringLayout.NORTH, painelPerfil);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, imgPerfil, 67, SpringLayout.WEST, painelPerfil);
		painelPerfil.add(imgPerfil);

		JLabel lblNome = new JLabel(usuario.getName());
		lblNome.setForeground(Color.white);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblNome, 34, SpringLayout.EAST, imgPerfil);
		lblNome.setFont(Dados.loadFontFromFile(25));
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblNome, 43, SpringLayout.NORTH, painelPerfil);
		painelPerfil.add(lblNome);

		JLabel lblID = new JLabel(usuario.getID());
		lblID.setFont(new Font("Arial", Font.BOLD, 15));
		lblID.setForeground(Color.white);
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblID, 33, SpringLayout.SOUTH, lblNome);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblID, 0, SpringLayout.WEST, lblNome);
		painelPerfil.add(lblID);

		JLabel lblSeguidores = new JLabel(usuario.getListaSeguidores().size() + " Seguidores");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblSeguidores, 3, SpringLayout.NORTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblSeguidores, 81, SpringLayout.EAST, lblID);
		lblSeguidores.setForeground(Color.white);
		lblSeguidores.setFont(Dados.loadFontFromFile(20));
		painelPerfil.add(lblSeguidores);

		JLabel lblSeguindo = new JLabel(usuario.getListaSeguindo().size() + " Seguindo");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, lblSeguindo, 3, SpringLayout.NORTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, lblSeguindo, 81, SpringLayout.EAST, lblSeguidores);
		lblSeguindo.setForeground(Color.white);
		lblSeguindo.setFont(Dados.loadFontFromFile(20));
		painelPerfil.add(lblSeguindo);

		JTextArea biografiaTextArea = new JTextArea(usuario.getBiografia());
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
		biografiaTextArea.setBackground(Color.decode("#7304D7"));
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, biografiaScroll, 33, SpringLayout.SOUTH, lblID);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, biografiaScroll, 0, SpringLayout.WEST, lblNome);
		painelPerfil.add(biografiaScroll);

		JButton btnEditar = criaBotao("/img/botãoEditar.png", "/img/botãoEditarClicado.png");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, btnEditar, 36, SpringLayout.NORTH, painelPerfil);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, btnEditar, 48, SpringLayout.EAST, lblNome);
		btnEditar.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogEditar editarPerfil = new DialogEditar(usuario);
				editarPerfil.setVisible(true);
				
				
			}
		});
		painelPerfil.add(btnEditar);

		JButton btnLogout = criaBotao("/img/botãoLogout.png", "/img/botãoLogoutClicado.png");
		sl_painelPerfil.putConstraint(SpringLayout.NORTH, btnLogout, 36, SpringLayout.NORTH, painelPerfil);
		sl_painelPerfil.putConstraint(SpringLayout.WEST, btnLogout, 49, SpringLayout.EAST, btnEditar);
		painelPerfil.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(frame, "Condirmar o logout?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					Dados.apagarTudo("src\\data\\cache.csv");
					FeedDeNoticias.getJFrame().dispose();
					Login voltar = new Login();
					voltar.getFrame().setVisible(true);
				
				}
			}
		});
		
		CarrocelDePosts postsUsuario = new CarrocelDePosts(usuario);
		springLayout.putConstraint(SpringLayout.NORTH, postsUsuario, 0, SpringLayout.SOUTH, painelPerfil);
		springLayout.putConstraint(SpringLayout.WEST, postsUsuario, 0, SpringLayout.WEST, painelPerfil);
		springLayout.putConstraint(SpringLayout.SOUTH, postsUsuario, 384, SpringLayout.SOUTH, painelPerfil);
		springLayout.putConstraint(SpringLayout.EAST, postsUsuario, 0, SpringLayout.EAST, painelPerfil);
		add(postsUsuario);

	}

	private JButton criaBotao(String imgNormal, String imgClick) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(103, 43));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(getClass().getResource(imgNormal)));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(imgClick)));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(imgNormal)));
				button.setCursor(Cursor.getDefaultCursor());
			}
		});
		return button;
	}
}
