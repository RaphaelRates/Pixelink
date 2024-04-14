package Swing.Componentes;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class ScrollbarCustomizado extends BasicScrollBarUI {
    private final Dimension dim = new Dimension();
    private final Timer timer;
    private int scrollAmount = 0;

    public ScrollbarCustomizado() {
        timer = new Timer(10, new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollAmount = Math.max(scrollAmount - 1, 0);
                scrollByUnit(scrollAmount);
                if (scrollAmount == 0) {
                    timer.stop();
                }
            }
        });
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
			private static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return dim;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
        	static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return dim;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(Color.decode("#7304D7"));
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(Color.decode("#FF0070"));
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
    }

    @Override
    protected void installListeners() {
        super.installListeners();

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                timer.start();
            }
        };

        if (incrButton != null) {
            incrButton.addMouseListener(mouseAdapter);
        }

        if (decrButton != null) {
            decrButton.addMouseListener(mouseAdapter);
        }
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
            return new Dimension(8, super.getPreferredSize(c).height);
        } else {
            return new Dimension(super.getPreferredSize(c).width, 8);
        }
    }
}