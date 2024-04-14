package Swing.Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CircularImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private int raio;

    public CircularImagePanel(String imagePath, int raio) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.raio = raio;
        setPreferredSize(new Dimension(raio * 2, raio * 2));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth() - raio * 2) / 2;
        int y = (getHeight() - raio * 2) / 2;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, raio * 2, raio * 2);
        g2d.setClip(circle);
        g2d.drawImage(image, x, y, raio * 2, raio * 2, null);
        g2d.dispose();
        
    }
}
