package Swing.Notificacoes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import Swing.Componentes.ScrollbarCustomizado;
import entidades.Notificacao;
import entidades.Usuario;

public class AbaDeNotificacoes extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Image fundo;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice[] gd = ge.getScreenDevices();
	private DisplayMode mode = gd[0].getDisplayMode();
	LineBorder bordaRoxa = new LineBorder(Color.decode("#7304D7"), 2, false);

	public AbaDeNotificacoes(Usuario usuario) {
		try {
			fundo = ImageIO.read(getClass().getResource("/img/fundoPesquisar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.usuario = usuario;
		setLayout(new BorderLayout());
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setBorder(new javax.swing.border.EmptyBorder(100, 50, 150, 50));
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		scrollPane.setBorder(null);
		scrollPane.setFocusable(true);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false); 
		scrollPane.getVerticalScrollBar().setUI(new ScrollbarCustomizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollbarCustomizado());

		JPanel viewport = new JPanel();
		viewport.setOpaque(false); 
		viewport.setLayout(new BoxLayout(viewport, BoxLayout.Y_AXIS));

		List<Notificacao> notificacoes = usuario.getNotificacoes();
		int tamanho;
		if (notificacoes.isEmpty()) {
			tamanho = 0;
			JLabel nenhumLabel = new JLabel("Nenhuma notificação encontrada");
			nenhumLabel.setBorder(bordaRoxa);
			nenhumLabel.setFont(new Font("Arial", Font.BOLD, 15));
			nenhumLabel.setForeground(Color.decode("#7304D7"));
			JPanel nenhumPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			nenhumPanel.add(nenhumLabel);
			viewport.add(nenhumPanel);

		} else {
			tamanho = notificacoes.size();
		}

		for (int i = tamanho - 1; i > -1; i--) {
			viewport.add(new Notificacao(notificacoes.get(i).getUsuario(), notificacoes.get(i).getTipo()));
			viewport.add(Box.createVerticalStrut(20));
		}

		scrollPane.setViewportView(viewport);

		add(scrollPane, BorderLayout.CENTER);
		setPreferredSize(new Dimension(mode.getWidth() - 150, mode.getHeight()));
	}
}
