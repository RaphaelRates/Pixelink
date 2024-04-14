package Swing.Posts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import Swing.Componentes.Alert;
import Swing.Componentes.ComentarioPost;
import Swing.Componentes.CurtidasDialog;
import Swing.Componentes.DialogImagem;
import Swing.Componentes.ScrollbarCustomizado;
import data.Dados;
import entidades.Comentario;
import entidades.PostText;
import entidades.Seguidor;
import entidades.Usuario;

public class PostTextPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private PostText post;
	private boolean curtiu;

	public PostTextPanel(Usuario usuario, PostText post) {
		this.usuario = usuario;
		this.post = post;
		this.curtiu = post.VerificarCurtida(Dados.getLogado().getID());
		setPreferredSize(new Dimension(600, 250));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.decode("#7304D7"), 5));
		setupComponents();
		setMaximumSize(new Dimension(600, 400));
	}

	private void setupComponents() {
		ImageIcon imageIcon = usuario.getFotoDoPerfil();
		Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (SwingUtilities.getWindowAncestor(imageLabel) instanceof Frame) {
						new DialogImagem((Frame) SwingUtilities.getWindowAncestor(imageLabel),
								usuario.getFotoDoPerfil(), 2.5);
					} else {
						new DialogImagem(
								(Frame) SwingUtilities.getWindowAncestor(SwingUtilities.getWindowAncestor(imageLabel)),
								usuario.getFotoDoPerfil(), 2.5);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				imageLabel.setCursor(Cursor.getDefaultCursor());
			}
		});
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.decode("#7304D7"));
		topPanel.add(imageLabel);

		JLabel nameLabel = new JLabel(usuario.getName());
		nameLabel.setFont(Dados.loadFontFromFile(16));
		nameLabel.setForeground(Color.WHITE);
		topPanel.add(nameLabel);

		JLabel IdLabel = new JLabel(usuario.getID());
		IdLabel.setFont(Dados.loadFontFromFile(12));
		IdLabel.setForeground(Color.WHITE);
		IdLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IdLabel.setForeground(Color.decode("#ff0070"));
				IdLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Font font = IdLabel.getFont();
				Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				IdLabel.setFont(font.deriveFont(attributes));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				imageLabel.setCursor(Cursor.getDefaultCursor());
				IdLabel.setForeground(Color.WHITE);
				Font font = IdLabel.getFont();
				Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
				attributes.put(TextAttribute.UNDERLINE, -1);
				IdLabel.setFont(font.deriveFont(attributes));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Alert.exibirInformacao("Você pode pesquisar o perfil do "+post.getUsuario()+" em pesquisar");
			}

		});
		topPanel.add(IdLabel);

		add(topPanel, BorderLayout.NORTH); 
		
		JTextArea contentArea = new JTextArea(post.getConteudo());
		contentArea.setEditable(false);
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setFont(Dados.loadFontFromFile(18));
		add(contentArea, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton commentButton = new JButton();
		commentButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoComentar.png")));
		commentButton.setBorderPainted(false);
		commentButton.setFocusPainted(false);
		commentButton.setContentAreaFilled(false);
		commentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				commentButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoComentarClicado.png")));
				commentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			};

			@Override
			public void mouseExited(MouseEvent e) {
				commentButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoComentar.png")));
				commentButton.setCursor(Cursor.getDefaultCursor());
			}
		});
		commentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerComentarios();
			}
		});
		JButton likeButton = new JButton();
		if (curtiu) {
			likeButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoCurtirClicado.png")));
		} else {
			likeButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoCurtir.png")));
		}

		likeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Usuario curtidor = Dados.getLogado();
					if (!curtiu) {
						curtidor.CurtirPost(post);
						likeButton.setIcon(
								new ImageIcon(PostImagePanel.class.getResource("/img/botãoCurtirClicado.png")));
						if (!post.getUsuario().equals(curtidor.getID())) {
							curtidor.enviarNotificacao(new Seguidor(usuario), "curtida");
						}
					} else {
						curtidor.DescurtirPost(post);
						likeButton.setIcon(new ImageIcon(PostImagePanel.class.getResource("/img/botãoCurtir.png")));
					}
					curtiu = !curtiu;

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				likeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				likeButton.setCursor(Cursor.getDefaultCursor());
			}
		});
		likeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					CurtidasDialog dialog;
					if (SwingUtilities.getWindowAncestor(likeButton) instanceof Frame) {
						dialog = new CurtidasDialog((Frame) SwingUtilities.getWindowAncestor(likeButton),
								post.getListaCurtidas());
					} else {
						dialog = new CurtidasDialog(
								(Frame) SwingUtilities.getWindowAncestor(SwingUtilities.getWindowAncestor(likeButton)),
								post.getListaCurtidas());
					}

					dialog.setVisible(true);
				}
			}
		});
		likeButton.setBorderPainted(false);
		likeButton.setFocusPainted(false);
		likeButton.setContentAreaFilled(false);
		buttonPanel.add(likeButton);
		buttonPanel.add(commentButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void VerComentarios() {

		JPanel dialogPanel = new JPanel();
		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane container = new JScrollPane();
		container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		container.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		container.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		container.setBorder(null);
		container.setFocusable(isFocusable());

		JPanel commentsPanel = new JPanel();
		commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
		container.setViewportView(commentsPanel);

		JTextField commentField = new JTextField();
		dialogPanel.add(commentField, BorderLayout.NORTH);
		commentField.setBorder(null);
		commentField.setFocusable(isFocusable());
		commentField.setText("comente aqui");
		commentField.setFont(Dados.loadFontFromFile(16));
		commentField.setPreferredSize(new Dimension(450, 30));

		JButton sendButton = new JButton("Enviar");
		sendButton.setBorder(null);
		sendButton.setForeground(Color.WHITE);
		sendButton.setBackground(Color.decode("#7304D7"));
		sendButton.setFont(Dados.loadFontFromFile(15));
		sendButton.setPreferredSize(new Dimension(400, 30));
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				EnviarComentario(commentField, commentsPanel);
			}
		});
		sendButton.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EnviarComentario(commentField, commentsPanel);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

		dialogPanel.add(sendButton, BorderLayout.SOUTH);
		dialogPanel.add(container, BorderLayout.CENTER);

		ArrayList<Comentario> comentarios = post.getComentarios();
		int tamanho;
		if (comentarios == null) {
			tamanho = 0;
		} else {
			tamanho = comentarios.size();
		}

		if (tamanho > 0) {
			for (int i = 0; i < tamanho; i++) {
				if (Dados.getUsuario(comentarios.get(i).getUsuario()) == null) {
					continue;
				}
				ComentarioPost comentario = new ComentarioPost(
						Dados.getUsuario(comentarios.get(i).getUsuario()).getID(), comentarios.get(i).getConteudo(),
						Dados.getUsuario(comentarios.get(i).getUsuario()).getFotoDoPerfil());
				comentario.setFont(Dados.loadFontFromFile(15));
				commentsPanel.add(comentario);
				commentsPanel.add(Box.createVerticalStrut(10));
			}
		}

		container.setPreferredSize(new Dimension(450, 600));
		container.getVerticalScrollBar().setUnitIncrement(15);

		JOptionPane.showMessageDialog(null, dialogPanel, "Comentários", JOptionPane.PLAIN_MESSAGE);
	}

	private void EnviarComentario(JTextField commentField, JPanel commentsPanel) {
		String commentText = commentField.getText().trim();
		if (!commentText.isEmpty()) {
			Usuario executor = Dados.getLogado();
			ComentarioPost novoComentario = new ComentarioPost(Dados.getLogado().getName(), commentText,
					Dados.getLogado().getFotoDoPerfil());
			novoComentario.setFont(Dados.loadFontFromFile(13));
			executor.Comentar(post, commentText);
			if (!post.getUsuario().equals(executor.getID())) {
				executor.enviarNotificacao(new Seguidor(Dados.getUsuario(post.getUsuario())), "comentario");
			}
			commentsPanel.add(novoComentario);
			commentsPanel.add(Box.createVerticalStrut(10));
			commentsPanel.revalidate();
			commentField.setText("");
		}
	}

}