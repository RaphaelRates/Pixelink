package br.com.Pixelnik.Login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNome;
    private JTextField campoEmail;
    private JButton botaoEnviar;

    public Login() {
        // Configurações básicas do JFrame
        setTitle("Formulário em Java Swing");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação do painel
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

        // Adiciona componentes ao painel
        painel.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painel.add(campoNome);

        painel.add(new JLabel("E-mail:"));
        campoEmail = new JTextField();
        painel.add(campoEmail);

        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para processar o envio do formulário
                String nome = campoNome.getText();
                String email = campoEmail.getText();

                if (!nome.isEmpty() && !email.isEmpty() && (email.contains("@gmail.com") || email.contains("@hotmail.com"))) {
                	boolean verificacao = leituraCSV("C:\\Users\\Raphael\\git\\Repository\\Pixelink\\src\\br\\com\\Pixelink\\data\\usuarios.csv", ";",nome, email);
                	if(!verificacao) {
                		System.out.println(verificacao);
                		   JOptionPane.showMessageDialog(Login.this, "Formulário enviado:\nNome: " + nome + "\nE-mail: " + email);
                	}
                 
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Preencha todos os campos.");
                }
            }
        });
        painel.add(botaoEnviar);

        // Adiciona o painel ao JFrame
        add(painel);

        // Exibe o JFrame
        setVisible(true);
    }
    
    public static boolean leituraCSV(String caminho, String separador ,String username,String email) {
    	

    	
    	String filePath = caminho;
        File file = new File(filePath);
        
        try {
            Scanner scanner = new Scanner(file);
            
            // Lê cada linha do arquivo CSV
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Divide a linha em valores usando a vírgula como delimitador
                String[] values = line.split(separador);
                
                // Faça o que desejar com os valores
                String usuario = values[1];
                String Email = values[2];
                if(usuario.equals(username) || Email.equals(email)) {
                	return false;
                }
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Cria uma instância do formulário
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }
}

