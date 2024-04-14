package Swing.Componentes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage fundo;

	public ImagePanel(String caminhoImagem) {
	        try {
	            fundo = ImageIO.read(getClass().getResource(caminhoImagem));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
}