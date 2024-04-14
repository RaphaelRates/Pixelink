package Swing.Componentes;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entidades.Curtida;

public class CurtidasDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private Set<Curtida> curtidas;

    public CurtidasDialog(Frame frame, Set<Curtida> curtidas) {
        super(frame, "Curtidas", true);
        this.curtidas = curtidas;

        initComponents();
    }

    private void initComponents() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        setSize(300, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
        setModal(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));

        for (Curtida curtida : curtidas) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)) {
				private static final long serialVersionUID = 1L;
				@Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int width = getWidth();
                    int height = getHeight();
                    int arc = 10; 
                    g.setColor(Color.decode("#7304D7")); 
                    g.fillRoundRect(0, 0, width, height, arc, arc); 
                }
            };
            panel.setMaximumSize(new Dimension(250, 70));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            if(curtida.getUsuario() == null) {
            	ImageIcon imageIcon = new ImageIcon("src\\img\\fotoPerfilPadrão.png");
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(image));
                JLabel nameLabel = new JLabel("User deleteded");
                nameLabel.setForeground(Color.WHITE);
                JLabel IdLabel = new JLabel("#00000");
                IdLabel.setForeground(Color.WHITE);
                contentPanel.add(Box.createVerticalStrut(10));
                panel.add(label);
                panel.add(nameLabel);
                panel.add(IdLabel);
            }else {
            	ImageIcon imageIcon = new ImageIcon(curtida.getUsuario().getFotoDoPerfil().toString());
                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JLabel label = new JLabel(new ImageIcon(image));
                JLabel nameLabel = new JLabel(curtida.getUsuario().getName());
                nameLabel.setForeground(Color.WHITE);
                JLabel IdLabel = new JLabel(curtida.getUsuario().getID());
                IdLabel.setForeground(Color.WHITE);
                contentPanel.add(Box.createVerticalStrut(10));
                panel.add(label);
                panel.add(nameLabel);
                panel.add(IdLabel);
            }

            
            contentPanel.add(panel);
        }

        add(scrollPane, BorderLayout.CENTER);
    }
}
