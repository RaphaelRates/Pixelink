package br.com.Pixelnik.teste;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Myframe extends JFrame{

	private static final long serialVersionUID = 1L;

	public Myframe() {
    	setTitle("Pixelink");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(1920,1080);
    	setVisible(true);
    	
    	JLabel label = new JLabel();
		label.setText("mooomomomommomo");
		this.add(label);
    	
    	ImageIcon pixelIcon = new ImageIcon(Test.class.getResource("/IconPixelink.png"));
    	setIconImage(pixelIcon.getImage());
    	getContentPane().setBackground(new Color(255,255,255));
    	label.setIcon(pixelIcon);
    	label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalAlignment(JLabel.CENTER);
    	label.setForeground(new Color(0x0F000));

		
	}
}
