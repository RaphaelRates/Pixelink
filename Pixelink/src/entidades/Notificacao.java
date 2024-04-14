package entidades;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import data.Dados;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;

public class Notificacao extends JPanel {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String tipo;
	private static final int ARC_WIDTH = 20;
	private static final int ARC_HEIGHT = 20;
	private Color cor;
	private Color background;
	private String menssagem;
	LineBorder bordaBranca = new LineBorder(Color.WHITE, 3, false);

	public Notificacao(Usuario usuario, String tipo) {
		this.usuario = usuario;
		this.tipo = tipo;
		obterCorFundo();
		mensagem();

		setOpaque(false);
		setPreferredSize(new Dimension(800, 127));
		setMaximumSize(new Dimension(900, 127));
		setBorder(new javax.swing.border.EmptyBorder(35, 10, 0, 10));

		if (usuario != null) {
			ImageIcon img = new ImageIcon(usuario.getFotoDoPerfil().toString());
			Image imgRed = img.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			JLabel fotoPerfil = new JLabel(new ImageIcon(imgRed));
			fotoPerfil.setBorder(bordaBranca);
			add(fotoPerfil);

			JLabel labelTextoNotificacao = new JLabel(usuario.getName() + usuario.getID() + menssagem);
			labelTextoNotificacao.setFont(Dados.loadFontFromFile(25));
			labelTextoNotificacao.setForeground(Color.WHITE);
			labelTextoNotificacao.setHorizontalAlignment(SwingConstants.CENTER);
			add(labelTextoNotificacao);
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth() - 2, getHeight() - 2, ARC_WIDTH,
				ARC_HEIGHT);
		g2d.setColor(this.background);
		g2d.fill(roundedRectangle);
		g2d.setColor(Color.white);
		g2d.draw(roundedRectangle);
		g2d.dispose();
	}

	private void obterCorFundo() {
		switch (tipo) {
		case "curtida":
			this.background = new Color(255, 0, 112, 150); 
			break;
		case "comentario":
			this.background = new Color(115, 4, 215, 150); 
			break;
		case "seguidor":
			this.background = new Color(0, 0, 0, 150); 
			break;
		}
	}

	private void mensagem() {
		switch (tipo) {
		case "curtida":
			this.menssagem = " curtiu um dos seus posts";
			break;
		case "comentario":
			this.menssagem = " comentou em um dos seus posts";
			break;
		case "seguidor":
			this.menssagem = " começou a seguir você";
			break;
		}
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
}
