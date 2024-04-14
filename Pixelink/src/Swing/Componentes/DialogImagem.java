package Swing.Componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DialogImagem extends JDialog {
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;

    public DialogImagem(Component parent, ImageIcon icon) {
        super(getParentFrame(parent), "Imagem", true);
        this.icon = icon;
        initialize(1);
    }
    
    public DialogImagem(Component parent, ImageIcon icon, double scale) {
        super(getParentFrame(parent), "Image Dialog", true);
        this.icon = icon;
        initialize(scale);
    }
    
    private static Frame getParentFrame(Component parent) {
        if (parent instanceof Frame) {
            return (Frame) parent;
        } else if (parent instanceof JPanel) {
            Frame frame = (Frame) SwingUtilities.getWindowAncestor((JPanel) parent);
            if (frame != null) {
                return frame;
            }
        }
        return (Frame) Dialog.getWindows()[0];
    }

    private void initialize(double scale) {
        if (icon == null) {
            return;
        }

        int newWidth = (int) (icon.getIconWidth() * scale);
        int newHeight = (int) (icon.getIconHeight() * scale);

        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel label = new JLabel(scaledIcon);
        label.setOpaque(false);
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    dispose();
                
            }

            public void mouseEntered(MouseEvent e) {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                label.setCursor(Cursor.getDefaultCursor());
            }
        });
        
        getContentPane().add(label);
        setSize(newWidth + 20, newHeight + 50);
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point pointRelativeToDialog = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), DialogImagem.this);
                if (!getBounds().contains(pointRelativeToDialog)) {
                    dispose(); 
                }
            }
        });
        pack();
        setVisible(true);
    }

    public static void MostrarImagem(Component parent, ImageIcon icon) {
        new DialogImagem(parent, icon);
    }
}