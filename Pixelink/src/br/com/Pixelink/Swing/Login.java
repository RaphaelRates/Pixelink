package br.com.Pixelink.Swing;


import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmPixelink;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnNewButton;

	public Login() {
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmPixelink.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmPixelink = new JFrame();
		frmPixelink.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPixelink.setForeground(Color.BLACK);
		frmPixelink.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));
		frmPixelink.setTitle("Pixelink");
		frmPixelink.setBounds(100, 100, 1366, 768);
		frmPixelink.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPixelink.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1366, 768);
		panel.setLayout(null);
		frmPixelink.getContentPane().add(panel);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				cadastro.getFrame().setVisible(true);
				frmPixelink.dispose();
			}
		});
		
		btnNewButton.setBounds(350, 500, 200, 50);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/Img/BotãoCadastar.png")));
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/Img/botão Entrar.png")));
		btnNewButton_1.setBounds(580, 500, 200, 50);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = textField.getText();
		        String password = new String(passwordField.getPassword());

		        if (username.isEmpty() || password.isEmpty()) {
		            Alerta.exibirAlerta("Por favor, verifique se os campos de E-mail e Senha estão devidamente preenchidos.");
		        } else {
		            Cadastro cadastro = new Cadastro();
		            cadastro.getFrame().setVisible(true);
		            frmPixelink.dispose();
		        }
		    }
		});


		textField = new JTextField();
		textField.setBounds(355, 293, 320, 40);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(355, 400, 290, 40);
		panel.add(passwordField);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/Img/TelaLogin.png")));
		panel.add(lblNewLabel);
	}
	public class Alerta {
	    public static void exibirAlerta(String mensagem) {
	        JOptionPane.showMessageDialog(null, mensagem, "Alerta", JOptionPane.WARNING_MESSAGE);
	    }
	}
	public JFrame getFrame() {
		return frmPixelink;
	}
}
