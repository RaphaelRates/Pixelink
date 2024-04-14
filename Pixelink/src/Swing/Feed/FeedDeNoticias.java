package Swing.Feed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Swing.Notificacoes.AbaDeNotificacoes;
import Swing.Perfil.VerProprioPerfil;
import Swing.PesquisarUsuarios.PesquisarUsuarios;
import Swing.Posts.CreatePostImage;
import Swing.Posts.CreatePostText;
import entidades.Usuario;

public class FeedDeNoticias {
	private static JFrame frame;
	private Usuario usuario;
	private JPanel btnCriarPost;
	private static JPanel painelLateral_1;
	private static SpringLayout springLayout;
	private SpringLayout sl_painelLateral_1;
	private JDialog dialog;
	static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	static GraphicsDevice[] gd = ge.getScreenDevices();
	static DisplayMode mode = gd[0].getDisplayMode();

	public FeedDeNoticias(Usuario logado) {
		frame = new JFrame();
		dialog = new JDialog(frame, "Criando post", Dialog.ModalityType.MODELESS);
		this.usuario = logado;
		springLayout = new SpringLayout();
		painelLateral_1 = new JPanel();
		sl_painelLateral_1 = new SpringLayout();
		initialize();
	}

	private void initialize() {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));
		frame.setTitle("Pixelink");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(mode.getWidth(), mode.getHeight());
		frame.getContentPane().setLayout(springLayout);

		FeedPosts ver = new FeedPosts(usuario);
		adicionarPainel(ver);
		painelLateral_1.setLayout(sl_painelLateral_1);
		springLayout.putConstraint(SpringLayout.NORTH, painelLateral_1, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, painelLateral_1, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, painelLateral_1, mode.getHeight(), SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, painelLateral_1, 149, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(painelLateral_1);
		Color corFundoLateral = Color.decode("#7304D7");
		painelLateral_1.setBackground(corFundoLateral);

		JPanel btnVerProprioPerfil = criarBotaoComLabel("/Img/BotãoPerfil.png", "/Img/BotãoPerfilClicado.png", "Perfil",
				45, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VerProprioPerfil ver = new VerProprioPerfil(usuario);
						adicionarPainel(ver);
						if (dialog.isVisible()) {
							dialog.dispose();
						}
					}
				});
		painelLateral_1.add(btnVerProprioPerfil);

		JPanel btnFeed = criarBotaoComLabel("/Img/botãoFeed.png", "/Img/BotãoFeedClicado.png", "Feed", 183,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FeedPosts ver = new FeedPosts(usuario);
						adicionarPainel(ver);
						dialog.dispose();
					}
				});
		painelLateral_1.add(btnFeed);

		JPanel btnNotificações = criarBotaoComLabel("/Img/botãoNotificações.png", "/Img/BotãonotificaçõesClicado.png",
				"Notificações", 321, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AbaDeNotificacoes ver = new AbaDeNotificacoes(usuario);
						adicionarPainel(ver);
						if (dialog.isVisible()) {
							dialog.dispose();
						}
					}
				});
		painelLateral_1.add(btnNotificações);
		JPanel btnPesquisar = criarBotaoComLabel("/Img/BotãoPesquisar.png", "/Img/BotãoPesquisarClicado.png",
				"Pesquisar", 459, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PesquisarUsuarios ver = new PesquisarUsuarios(usuario, frame);
						adicionarPainel(ver);
						if (dialog.isVisible()) {
							dialog.dispose();
						}
					}
				});
		painelLateral_1.add(btnPesquisar);
		btnCriarPost = criarBotaoComLabel("/Img/botãoCriarPost.png", "/Img/BotãoCriarPostClicado.png", "Criar", 591,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (dialog == null || !dialog.isVisible()) {
							Dialog(frame);
						} else {
							dialog.dispose();
						}

					}
				});
		painelLateral_1.add(btnCriarPost);

	}

	public static void adicionarPainel(JPanel novoPainel) {
		for (Component component : frame.getContentPane().getComponents()) {
			if (component != painelLateral_1) {
				frame.getContentPane().remove(component);
			}
		}
		frame.getContentPane().add(novoPainel);
		springLayout.putConstraint(SpringLayout.NORTH, novoPainel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, novoPainel, 150, SpringLayout.WEST, painelLateral_1);
		springLayout.putConstraint(SpringLayout.SOUTH, novoPainel, mode.getHeight(), SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, novoPainel, 0, SpringLayout.EAST, frame.getContentPane());
		frame.revalidate();
		frame.repaint();
	}
	

	private JPanel criarBotaoComLabel(String iconePadrao, String iconeMouseOver, String labelText, int north,
			ActionListener listener) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);

		JLabel button = new JLabel();
		button.setIcon(new ImageIcon(getClass().getResource(iconePadrao)));
		button.setPreferredSize(new Dimension(79, 79));
		button.setHorizontalAlignment(JLabel.CENTER);

		JLabel label = new JLabel(labelText);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);

		panel.add(button, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);

		sl_painelLateral_1.putConstraint(SpringLayout.NORTH, panel, north, SpringLayout.NORTH, painelLateral_1);
		sl_painelLateral_1.putConstraint(SpringLayout.WEST, panel, 35, SpringLayout.WEST, painelLateral_1);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.actionPerformed(new ActionEvent(panel, ActionEvent.ACTION_PERFORMED, null));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(iconeMouseOver)));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(iconePadrao)));
				button.setCursor(Cursor.getDefaultCursor());

			}

		});

		return panel;
	}

	public JFrame getFrame() {
		return frame;
	}

	private void Dialog(JFrame parent) {
		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.setBackground(new Color(0, 0, 0, 0));
		dialogPanel.setOpaque(false);

		JLabel messageLabel = new JLabel("Criar:");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Arial", Font.BOLD, 15));
		dialogPanel.add(messageLabel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		JButton btnImagem = new JButton();
		btnImagem.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostImagem.png")));
		btnImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnImagem.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostImagemClicado.png")));
				btnImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnImagem.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostImagem.png")));
				btnImagem.setCursor(Cursor.getDefaultCursor());
			}
		});
		btnImagem.setBorderPainted(false);
		btnImagem.setFocusPainted(false);
		btnImagem.setContentAreaFilled(false);
		btnImagem.setPreferredSize(new Dimension(109, 40));
		buttonPanel.add(btnImagem);

		JButton btnTexto = new JButton();
		btnTexto.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostTexto.png")));
		btnTexto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTexto.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostTextoClicado.png")));
				btnTexto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTexto.setIcon(new ImageIcon(getClass().getResource("/img/botãoPostTexto.png")));
				btnTexto.setCursor(Cursor.getDefaultCursor());
			}
		});
		btnTexto.setBorderPainted(false);
		btnTexto.setFocusPainted(false);
		btnTexto.setContentAreaFilled(false);
		btnTexto.setPreferredSize(new Dimension(109, 40));
		buttonPanel.add(Box.createVerticalStrut(15));
		buttonPanel.add(btnTexto);
		dialogPanel.add(buttonPanel, BorderLayout.CENTER);
		dialog.setUndecorated(true);
		dialog.setBackground(new Color(0, 0, 0, 0));
		dialog.getContentPane().add(dialogPanel);
		dialog.pack();
		dialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!dialog.getBounds().contains(e.getPoint())) {
					dialog.dispose();
				}
			}
		});

		Point posBotaoCriar = painelLateral_1.getLocationOnScreen();
		int xDialog = posBotaoCriar.x + painelLateral_1.getWidth();
		int yDialog = posBotaoCriar.y + btnCriarPost.getY();
		dialog.setLocation(xDialog - 20, yDialog - 25);
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				CreatePostImage criador = new CreatePostImage(usuario);
				criador.setBorder(new EmptyBorder(10, 50, 50, 90));
				centerPanel.add(new CreatePostImage(usuario));
				adicionarPainel(centerPanel);
				dialog.dispose();
			}
		});
		btnTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatePostText cria = new CreatePostText(usuario);
				JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				centerPanel.add(cria);
				adicionarPainel(centerPanel);
				dialog.dispose();
			}
		});

		parent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!dialog.getBounds().contains(e.getLocationOnScreen())) {
					dialog.dispose();
				}
			}

		});

		dialog.setVisible(true);
	}
	
	public static JFrame getJFrame() {
		return frame;
	}
	public static JPanel  getPainelLateral_1() {
		return painelLateral_1;
	}

}