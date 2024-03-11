package br.com.Pixelink.Swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import br.com.Pixelink.data.Dados;
import br.com.Pixelink.entidades.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class Cadastro {

    private JFrame frmPixelink;
    private JTextField textEmail;
    private JTextField textName;
    private JPasswordField passwordField;
    private JPasswordField passwordField_verificador;

    public Cadastro() {
        initialize();
    }

    private void initialize() {
        frmPixelink = new JFrame();
//        frmPixelink.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmPixelink.setTitle("Pixelink");
        frmPixelink.setBounds(100, 100, 1366, 768);
        frmPixelink.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPixelink.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));
        frmPixelink.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1366, 768);
        frmPixelink.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login voltarLogin = new Login();
                voltarLogin.getFrame().setVisible(true); 
                frmPixelink.dispose();
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/Img/Voltaf.png")));
        btnNewButton_1.setBounds(765, 536, 200, 50);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/Img/3.png")));
        lblNewLabel_1.setBounds(648, 250, 400, 200);
        panel.add(lblNewLabel_1);
    
        //Botão Cadastrar
        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String Email = textEmail.getText();
            	String Name = textName.getText();
            	String password = new String(passwordField.getPassword());
            	String password_validator = new String(passwordField_verificador.getPassword());
            	
            	if(Name.isEmpty() || Email.isEmpty() || password.isEmpty() || password_validator.isEmpty()) {
            		Alert.exibirAlerta("Preencha todos os campos obrigatórios");
            	} else if(!(Email.endsWith("@gmail.com") || Email.endsWith("@hotmail.com")) || Character.isDigit(Email.charAt(0))) {
		        	Alert.exibirAlerta("Formato de E-mail ou ID inválido inválido.");
            	}else if(!password.equals(password_validator)) {
            		Alert.exibirAlerta("As senhas não correspondem");
            	}else if(Name.length() < 8 || password.length() < 8) {
            		Alert.exibirAlerta("Nome ou senha precisam no minimo de 8 caracteres");
            	} else if(Dados.verificarUsuarioExistente(Email, password)) {
            		Alert.exibirAlerta("Usuário já existente");
            	}else {
            		Dados.CriarConta(new Usuario(Name, Email, password));
            		Alert.exibirInformacao("cadastrado com sucesso");
            		Login voltarLogin = new Login();
                    voltarLogin.getFrame().setVisible(true); 
                    frmPixelink.dispose();
            	}
            }
        });
        btnNewButton.setIcon(new ImageIcon(getClass().getResource("/Img/BotãoCadastar.png")));
        btnNewButton.setBounds(765, 475, 200, 50);
        panel.add(btnNewButton);
        
        //Campos de dígito(Text Fields e Senhas)
        
        textEmail = new JTextField();
        textEmail.setToolTipText("");
        textEmail.setBounds(345,245, 320,40);
        panel.add(textEmail);
        textEmail.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(345, 475, 320, 40);
        panel.add(passwordField);
        
        passwordField_verificador = new JPasswordField();
        passwordField_verificador.setBounds(345, 580, 320, 40);
        panel.add(passwordField_verificador);
        
        textName = new JTextField();
        textName.setBounds(345, 355, 320, 40);
        panel.add(textName);
        textName.setColumns(10);
        
        
        //Fundo CADASTRO
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, -25, 1366, 768);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/Img/FundoCadastro - 1366x768.png")));
        panel.add(lblNewLabel);
    }
    
    public JFrame getFrame() {
        return frmPixelink;
    }
}
