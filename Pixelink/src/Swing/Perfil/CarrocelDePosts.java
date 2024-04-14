package Swing.Perfil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Swing.Componentes.DialogPost;
import Swing.Componentes.ScrollbarCustomizado;
import Swing.Posts.PostImagePanel;
import Swing.Posts.PostTextPanel;
import data.Dados;
import entidades.Post;
import entidades.PostImage;
import entidades.PostText;
import entidades.Usuario;

public class CarrocelDePosts extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel painelTitulo;
	private JPanel painelScroll;
	private JLabel lblTitulo;
	private Usuario usuario;
	private JLabel lblCartinha;
	private JLabel gatinho;

	public CarrocelDePosts(Usuario usuario) {
		this.setUsuario(usuario);
		setSize(700, 400);

		painelTitulo = new JPanel();
		painelTitulo.setBackground(Color.decode("#7304D7"));
		painelTitulo.setPreferredSize(new Dimension(600, 50));

		painelScroll = new JPanel();
		painelScroll.setLayout(new FlowLayout(FlowLayout.LEFT));
		JScrollPane scrollPane = new JScrollPane(painelScroll);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);

		ArrayList<Post> allPosts = usuario.getAllPost();

		if (allPosts.isEmpty()) {
			gatinho = new JLabel("");
			gatinho.setIcon(new ImageIcon(CarrocelDePostsOutroUser.class.getResource("/img/gatinho.png")));
			painelScroll.add(gatinho);
		} else {
			int maxPostsRenderizados = allPosts.size();
			for (int i = 0; i < maxPostsRenderizados; i++) {
				Post post = allPosts.get(i);
				JButton button = new JButton();
				if (post instanceof PostImage) {
					ImageIcon originalIcon = ((PostImage) post).getConteudo();
					Image originalImage = originalIcon.getImage();
					Image resizedImage = originalImage.getScaledInstance(295, 295, Image.SCALE_SMOOTH);
					ImageIcon resizedIcon = new ImageIcon(resizedImage);
					button.setIcon(resizedIcon);
					button.setBorder(new LineBorder(Color.decode("#7304D7"), 5, false));
					button.setBackground(Color.WHITE);
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							new DialogPost((Frame) SwingUtilities.getWindowAncestor(button),
									new PostImagePanel(usuario, (PostImage) post));
						}

						public void mouseEntered(MouseEvent e) {
							button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						}

						public void mouseExited(MouseEvent e) {
							button.setCursor(Cursor.getDefaultCursor());
						}

					});
				} else if (post instanceof PostText) {
					String conteudo = ((PostText) post).getConteudo();
					button.setPreferredSize(new Dimension(300, 300));
					button.setFont(Dados.loadFontFromFile(20));
					button.setForeground(Color.BLACK);
					button.setBackground(Color.WHITE);
					button.setText(conteudo);    
					button.setBorder(new LineBorder(Color.decode("#7304D7"), 5, false));
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							new DialogPost((Frame) SwingUtilities.getWindowAncestor(button),
									new PostTextPanel(usuario, (PostText) post));
						}

						public void mouseEntered(MouseEvent e) {
							button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						}

						public void mouseExited(MouseEvent e) {
							button.setCursor(Cursor.getDefaultCursor());
						}
					});
				}
				button.setPreferredSize(new Dimension(300, 300));

				painelScroll.add(button);
			}

		}

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JPanel painelPrincipal = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, painelPrincipal, 5, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, painelPrincipal, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, painelPrincipal, 385, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, painelPrincipal, 1550, SpringLayout.WEST, this);
		painelPrincipal.setLayout(new BorderLayout());
		painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
		SpringLayout sl_painelTitulo = new SpringLayout();
		painelTitulo.setLayout(sl_painelTitulo);

		lblTitulo = new JLabel("Seus posts");
		sl_painelTitulo.putConstraint(SpringLayout.NORTH, lblTitulo, 14, SpringLayout.NORTH, painelTitulo);
		sl_painelTitulo.putConstraint(SpringLayout.SOUTH, lblTitulo, -14, SpringLayout.SOUTH, painelTitulo);
		sl_painelTitulo.putConstraint(SpringLayout.EAST, lblTitulo, -1233, SpringLayout.EAST, painelTitulo);
		lblTitulo.setFont(Dados.loadFontFromFile(27));
		lblTitulo.setForeground(Color.white);
		painelTitulo.add(lblTitulo);

		lblCartinha = new JLabel("");
		sl_painelTitulo.putConstraint(SpringLayout.WEST, lblTitulo, 6, SpringLayout.EAST, lblCartinha);
		sl_painelTitulo.putConstraint(SpringLayout.SOUTH, lblCartinha, -10, SpringLayout.SOUTH, painelTitulo);
		sl_painelTitulo.putConstraint(SpringLayout.EAST, lblCartinha, -1495, SpringLayout.EAST, painelTitulo);
		sl_painelTitulo.putConstraint(SpringLayout.NORTH, lblCartinha, 10, SpringLayout.NORTH, painelTitulo);
		sl_painelTitulo.putConstraint(SpringLayout.WEST, lblCartinha, 30, SpringLayout.WEST, painelTitulo);
		lblCartinha.setIcon(new ImageIcon("src/img/cartinha.png"));
		painelTitulo.add(lblCartinha);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CarrocelDePosts.class.getResource("/img/gatinho.png")));
		painelTitulo.add(lblNewLabel);
		painelPrincipal.add(scrollPane, BorderLayout.CENTER);

		add(painelPrincipal);
		setVisible(true);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}