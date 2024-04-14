package Swing.Componentes;

import javax.swing.*;

import data.Dados;
import entidades.Usuario;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ComentarioPost extends JPanel {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String comentario;

	public ComentarioPost(String username, String comment, ImageIcon userImage) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setMinimumSize(new Dimension(200, 350));
		setMaximumSize(new Dimension(400, 400));
		Font customFont = Dados.loadFontFromFile(16);
		
		JLabel usernameLabel = new JLabel(username);
		usernameLabel.setFont(customFont);
		JTextArea commentArea = new JTextArea(comment);
		commentArea.setFont(customFont);
		commentArea.setEditable(false);
		commentArea.setLineWrap(true);
		commentArea.setWrapStyleWord(true);
		commentArea.setBackground(getBackground());
		commentArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JScrollPane scroll = new JScrollPane(commentArea);
		scroll.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scroll.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());
		scroll.setBorder(null);
		scroll.setFocusable(isFocusable());
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		setBorder(null);
		setFocusable(isFocusable());
		scroll.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				int scrollAmount = 10;

				if (keyCode == KeyEvent.VK_UP) {
					JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
					verticalScrollBar.setValue(verticalScrollBar.getValue() - scrollAmount);
				} else if (keyCode == KeyEvent.VK_DOWN) {
					JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
					verticalScrollBar.setValue(verticalScrollBar.getValue() + scrollAmount);
				}
			}
		});

		JLabel imageLabel = new JLabel(
				new ImageIcon(userImage.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(Color.decode("#7304D7"));
		topPanel.add(imageLabel);
		topPanel.add(usernameLabel);

		add(topPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}