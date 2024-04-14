package Program;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Swing.Cadastro.Cadastro;
import Swing.Feed.FeedDeNoticias;
import Swing.Login.Login;
import data.Dados;
import entidades.Usuario;


public class Main extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    private float alpha = 0.0f;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 500;
    private static final int ANIMATION_DURATION = 1500; 
    private static final int FPS = 60;
    private JFrame frame;

    public Main(JFrame frame) {
        this.frame = frame;
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro.class.getResource("/img/logo.png")));
        loadBackgroundImage();
        setupUI();
        startBackgroundAnimation();
    }

    private void loadBackgroundImage() {
        try {
            String imagePath = "src\\img\\logoPixel.png";
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        JLabel instructionLabel = new JLabel();
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(instructionLabel, BorderLayout.CENTER);

        instructionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startApplication();
            }
        });
    }

    private void startBackgroundAnimation() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        int screenWidth = mode.getWidth();
        int screenHeight = mode.getHeight();
        frame.setLocation((screenWidth - FRAME_WIDTH) / 2, (screenHeight - FRAME_HEIGHT) / 2);

        Timer backgroundTimer = new Timer(1000 / FPS, new ActionListener() {
            private long startTime = -1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long elapsedTime = System.currentTimeMillis() - startTime;
                float progress = (float) elapsedTime / ANIMATION_DURATION;

                float alpha = progress;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    ((Timer) e.getSource()).stop();
                    try {
                        Usuario logado = Dados.getLogado();
                        if (logado == null) {
                            Login window = new Login();
                            frame.setVisible(false);
                            window.getFrame().setVisible(true);
                        } else {
                            FeedDeNoticias feed = new FeedDeNoticias(logado);
                            frame.setVisible(false);
                            feed.getFrame().setVisible(true);
                            
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                frame.setOpacity(alpha);
                updateAlpha(alpha);
            }
        });

        backgroundTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(image, 50, 50, getWidth() - 100, getHeight() - 100, this);
        g2d.dispose();
    }

    // Método para atualizar a transparência da imagem
    public void updateAlpha(float newAlpha) {
        this.alpha = newAlpha;
        repaint();
    }

    private void startApplication() {
        try {
            Usuario logado = Dados.getLogado();
            if (logado == null) {
                Login window = new Login();
                window.getFrame().setVisible(true);
            } else {
                FeedDeNoticias feed = new FeedDeNoticias(logado);
                feed.getFrame().setVisible(true);
                frame.setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Transparent Animation Example");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true);
            Main backgroundPanel = new Main(frame);
            frame.getContentPane().add(backgroundPanel);

            frame.setVisible(true);
        });
    }
}