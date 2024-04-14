package Swing.Posts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Swing.Componentes.ScrollbarCustomizado;
import Swing.Feed.FeedDeNoticias;
import Swing.Feed.FeedPosts;
import data.Dados;
import entidades.PostText;
import entidades.Usuario;

public class CreatePostText extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String content;

	public CreatePostText(Usuario usuario) {
		this.setUsuario(usuario);
		this.content = "insira aqui seu texto";
		setPreferredSize(new Dimension(600, 300));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.decode("#7304D7"), 5));
		setupComponents(usuario);
		setMaximumSize(new Dimension(600, 400));
	}

	private void setupComponents(Usuario usuario) {
		// Desenhar a foto do seguidor (redimensionada para 50x50)
		ImageIcon imageIcon = usuario.getFotoDoPerfil();
		Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.decode("#7304D7"));
		topPanel.add(imageLabel);

		// Adicionar nome do seguidor
		JLabel nameLabel = new JLabel(usuario.getName());
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(Dados.loadFontFromFile(16));
		topPanel.add(nameLabel);

		// Adicionar nome do seguidor
		JLabel IdLabel = new JLabel(usuario.getID());
		IdLabel.setFont(Dados.loadFontFromFile(12));
		IdLabel.setForeground(Color.WHITE);
		topPanel.add(IdLabel);

		add(topPanel, BorderLayout.NORTH);

		// Adicionar conteúdo do post
		JTextArea contentArea = new JTextArea(content);
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setFont(new Font("Arial", Font.PLAIN, 15));
		contentArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateVariavel();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateVariavel();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			private void updateVariavel() {
				content = contentArea.getText();
			}
		});
		contentArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume(); 
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(contentArea);
		scrollPane.setBorder(null);
		scrollPane.setFocusable(isFocusable());
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		add(scrollPane, BorderLayout.CENTER);

		// Adicionar botões de comentar e curtir
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton sendButton = new JButton("Enviar");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ConfirmarEnvio(buttonPanel, contentArea, usuario);
			}
		});
		sendButton.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConfirmarEnvio(buttonPanel, contentArea, usuario);
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonPanel.add(sendButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void ConfirmarEnvio(JPanel buttonPanel, JTextArea textContentArea, Usuario usuario) {
		JOptionPane optionPane = new JOptionPane();
		JDialog dialog = optionPane.createDialog("Confirmação");
		optionPane.setOptionType(JOptionPane.YES_NO_OPTION);

		dialog.getRootPane().registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose(); 
				
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		int option = JOptionPane.showConfirmDialog(buttonPanel, "Deseja realmente enviar?", "Confirmação",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			if (content == "") {
				JOptionPane.showMessageDialog(buttonPanel,
						"Conteudo não encontrado, porfavor escreve o que queira dizer");
				return;
			}
			usuario.criarPostText(new PostText(usuario.getID(), content));
			JOptionPane.showMessageDialog(buttonPanel, "Mensagem enviada com sucesso!");
			FeedPosts voltar = new FeedPosts(usuario);
			FeedDeNoticias.adicionarPainel(voltar);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}