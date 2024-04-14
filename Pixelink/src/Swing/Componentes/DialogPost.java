package Swing.Componentes;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Swing.Posts.PostTextPanel;

public class DialogPost extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel post;

	public DialogPost(Frame frame, JPanel post) {
		super(frame, "Post", true);
		this.post = post;
		initialize();
	}

	private void initialize() {
		if (post instanceof PostTextPanel)
			setSize(600, 400);
		else
			setSize(600, 650);
		getContentPane().add(post);
		setLocationRelativeTo(getParent());
		setResizable(false);
		setVisible(true);
	}

}