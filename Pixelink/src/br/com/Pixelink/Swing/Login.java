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

import br.com.Pixelink.data.Dados;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmPixelink;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JButton JBCadastro;

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
//		frmPixelink.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

		JBCadastro = new JButton("");
		JBCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				cadastro.getFrame().setVisible(true);
				frmPixelink.dispose();
			}
		});
		
		JBCadastro.setBounds(350, 500, 200, 50);
		JBCadastro.setIcon(new ImageIcon(getClass().getResource("/Img/BotãoCadastar.png")));
		panel.add(JBCadastro);

		JButton JButtonEntrar = new JButton("");
		JButtonEntrar.setIcon(new ImageIcon(getClass().getResource("/Img/botão Entrar.png")));
		
		
		JButtonEntrar.setBounds(580, 500, 200, 50);
		panel.add(JButtonEntrar);
		JButtonEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = emailField.getText();
		        String password = new String(passwordField.getPassword());

		        if (username.isEmpty() || password.isEmpty() ) {
		            Alerta.exibirAlerta("Por favor, verifique se os campos estão devidamente preenchidos.");
		        } else if(!(username.endsWith("@gmail.com") || username.endsWith("@hotmail.com") || (username.startsWith("#") && username.length() == 6)) || Character.isDigit(username.charAt(0))) {
		        	Alerta.exibirAlerta("Formato de E-mail ou ID inválido inválido.");
		        }else if(Dados.verificarUsuarioExistente(username, password)){
		        	Dados.Logar(username, password);
		        	Alerta.exibirAlerta("logado com sucesso");
		        	System.exit(0);
		        }
		    }
		});


		emailField = new JTextField();
		emailField.setBounds(355, 293, 320, 40);
		panel.add(emailField);
		emailField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(355, 400, 290, 40);
		panel.add(passwordField);

		JLabel IconLogo = new JLabel("");
		IconLogo.setBounds(0, 0, 1366, 768);
		IconLogo.setIcon(new ImageIcon(getClass().getResource("/Img/TelaLogin.png")));
		panel.add(IconLogo);
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
