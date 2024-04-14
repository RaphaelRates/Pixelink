package Swing.Componentes;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField implements FocusListener {
	private static final long serialVersionUID = 1L;
	private String placeholder;

    public PlaceholderTextField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.setForeground(Color.GRAY); 
        this.addFocusListener(this);
        this.setText(placeholder);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equals(placeholder)) {
            this.setText("");
            this.setForeground(Color.BLACK); 
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            this.setForeground(Color.GRAY);
            this.setText(placeholder);
        }
    }
}
