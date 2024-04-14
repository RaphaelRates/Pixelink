package Swing.Posts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Swing.Componentes.Alert;
import Swing.Feed.FeedDeNoticias;
import Swing.Feed.FeedPosts;
import entidades.PostImage;
import entidades.Usuario;

public class CreatePostImage extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String followerName;
	private Image image;
	private ImageIcon Imageupload;
	private File file;
	private String textoUpload;
	Border bordaRoxa = BorderFactory.createLineBorder(Color.white, 5);

	public CreatePostImage(Usuario usuario) {
		this.setUsuario(usuario);
		this.followerName = usuario.getID();
		this.image = usuario.getFotoDoPerfil().getImage();
		setPreferredSize(new Dimension(600, 600));
		setBorder(BorderFactory.createLineBorder(Color.decode("#7304D7"), 5));
		setupComponents(usuario);
		setMaximumSize(new Dimension(600, 600));
		setBackground(Color.white);
	}

	private void setupComponents(Usuario usuario) {
		setLayout(new BorderLayout());

		// Painel superior com foto do seguidor e nome
		FlowLayout fl_topPanel = new FlowLayout(FlowLayout.LEFT);
		JPanel topPanel = new JPanel(fl_topPanel);
		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(imageIcon);
		topPanel.add(imageLabel);

		topPanel.add(imageLabel);

		JLabel nameLabel = new JLabel(usuario.getName() + " " + followerName);
		nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		nameLabel.setForeground(Color.WHITE);
		topPanel.add(nameLabel);
		add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.decode("#7304D7"));

		JPanel imageContentLabel = new JPanel();
		imageContentLabel.setLayout(new BorderLayout());
		imageContentLabel.setBackground(Color.decode("#7304D7"));
		imageContentLabel.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));

		ImageIcon img = new ImageIcon("src/Img/uploadImagem.png");
		Image imgRedimensionada = img.getImage().getScaledInstance(290, 270, Image.SCALE_SMOOTH);
		ImageIcon img2 = new ImageIcon("src/Img/uploadImagemClicado.png");
		Image imgRedimensionada2 = img2.getImage().getScaledInstance(290, 270, Image.SCALE_SMOOTH);

		JButton ButtonImageEscolher = new JButton();
		ButtonImageEscolher.setHorizontalAlignment(SwingConstants.CENTER);
		ButtonImageEscolher.setVerticalAlignment(SwingConstants.CENTER);
		ButtonImageEscolher.setOpaque(true);
		ButtonImageEscolher.setBackground(Color.decode("#7304D7"));

		ButtonImageEscolher.setIcon(new ImageIcon(imgRedimensionada));
		ButtonImageEscolher.setBorderPainted(false);
		ButtonImageEscolher.setFocusPainted(false);
		ButtonImageEscolher.setContentAreaFilled(false);
		ButtonImageEscolher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				ButtonImageEscolher.setIcon(new ImageIcon(imgRedimensionada));
				imageContentLabel.setBackground(Color.decode("#7304D7"));
				ButtonImageEscolher.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ButtonImageEscolher.setIcon(new ImageIcon(imgRedimensionada2));
				ButtonImageEscolher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				imageContentLabel.setBackground(Color.decode("#ff0070"));
			}
		});
		ButtonImageEscolher.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("escolha uma foto de perfil");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens (*.jpeg, *.png, *.jpg)", "jpg",
						"jpeg", "png");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(ButtonImageEscolher);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					for (MouseListener listener : ButtonImageEscolher.getMouseListeners()) {
						ButtonImageEscolher.removeMouseListener(listener);
					}
					file = fileChooser.getSelectedFile();
					Imageupload = new ImageIcon(file.getAbsolutePath());
					Image imageIconRedmensionada = Imageupload.getImage().getScaledInstance(400, 400,
							Image.SCALE_SMOOTH);

					ButtonImageEscolher.setIcon(new ImageIcon(imageIconRedmensionada));
				}

				ButtonImageEscolher.addMouseListener(this);
			}
		});
		ButtonImageEscolher.setPreferredSize(new Dimension(400, 400));
		imageContentLabel.add(ButtonImageEscolher, BorderLayout.NORTH);

		JTextArea textContentArea = new JTextArea("Digite seu texto aqui");
		textContentArea.setToolTipText("");
		textContentArea.setForeground(new Color(128, 0, 128));
		textContentArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textContentArea.setMargin(new Insets(5, 5, 5, 5));
		textContentArea.setLineWrap(true);
		textContentArea.setWrapStyleWord(true);
		textContentArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(textContentArea);
		scrollPane.setBorder(new LineBorder(Color.decode("#7304D7"), 5));
		imageContentLabel.add(scrollPane);
		add(imageContentLabel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton sendButton = new JButton();
		buttonPanel.setBorder(bordaRoxa);
		sendButton.setBorderPainted(false);
		sendButton.setFocusPainted(false);
		sendButton.setContentAreaFilled(false);
		sendButton.setIcon(new ImageIcon("src/img/btnEnviar.png"));
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(buttonPanel, "Deseja realmente enviar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					try {
						if (file != null) {
							textoUpload = textContentArea.getText();
							usuario.criarPostImage(new PostImage(usuario.getID(), Imageupload, textoUpload));
							JOptionPane.showMessageDialog(buttonPanel, "Upload realizado com sucesso!");
							FeedPosts voltar = new FeedPosts(usuario);
							FeedDeNoticias.adicionarPainel(voltar);
						}else {
							Alert.exibirAlerta("Escolha uma imagem primeiro!");
						}
						
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				sendButton.setIcon(new ImageIcon("src/img/btnEnviar.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				sendButton.setIcon(new ImageIcon("src/img/btnEnviarClicado.png"));
			}
		});
		buttonPanel.add(sendButton);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}