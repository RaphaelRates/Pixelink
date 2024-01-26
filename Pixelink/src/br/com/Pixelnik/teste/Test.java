package br.com.Pixelnik.teste;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
  

public class Test{

    public static void main(String[] args) {
        
    	 Myframe myframe = new Myframe();
    	 
    	 JToolBar toolBar = new JToolBar();
    	 myframe.getContentPane().add(toolBar, BorderLayout.WEST);
    	
    	
    }
}

