package br.com.Pixelnik.Login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.Pixelink.entidades.Usuario;

public class Cadastro extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoSenha;
    private JTextField campoSenhaISafe;
    private JButton botaoEnviar;

    public Cadastro() {
        // Configurações básicas do JFrame
    	 setTitle("Formulário em Java Swing");
    	    setSize(600, 400);
    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    setLocationRelativeTo(null);

    	    // Criação do painel
    	    JPanel painel = new JPanel();
    	    painel.setLayout(new GridLayout(5, 2, 10, 10));

    	    // Adiciona componentes ao painel
    	    painel.add(new JLabel("Email: "));
    	    campoEmail = new JTextField();
    	    painel.add(campoEmail);

    	    painel.add(new JLabel("Nome: "));
    	    campoNome = new JTextField();
    	    painel.add(campoNome);

    	    painel.add(new JLabel("Senha:"));
    	    campoSenha = new JTextField();
    	    painel.add(campoSenha);

    	    painel.add(new JLabel("Confirmar senha:"));
    	    campoSenhaISafe = new JTextField();
    	    painel.add(campoSenhaISafe);

        botaoEnviar = new JButton("Enviar");
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para processar o envio do formulário
                String nome = campoNome.getText();
                String email = campoEmail.getText();
                String senha = campoSenha.getText();
                String confirmacao = campoSenhaISafe.getText();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmacao.isEmpty() ) {
                	 JOptionPane.showMessageDialog(Cadastro.this, "Preencha todos os campos.");
                } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
                	 JOptionPane.showMessageDialog(Cadastro.this, "formato de email invalido.");
                }else if(!(confirmacao.equals(senha))) {
                	 JOptionPane.showMessageDialog(Cadastro.this, "senha incorreta na confirmação"); 
                } else  {
                	escritaCSV("C:\\Users\\Raphael\\git\\Repository\\Pixelink\\src\\br\\com\\Pixelink\\data\\usuarios.csv", ";", new Usuario(nome,email,senha));
                    JOptionPane.showMessageDialog(Cadastro.this, "Formulário enviado:\nNome: " + nome + "\nE-mail: " + email);
                    setVisible(false);
                }
            }
        });
        painel.add(botaoEnviar);

        // Adiciona o painel ao JFrame
        add(painel);

        // Exibe o JFrame
        setVisible(true);
    }
    
    public void escritaCSV(String caminho, String separador, Usuario novaConta) {
    	String filePath = caminho;
    	if(Login.leituraCSV(caminho, separador, novaConta.getID(), novaConta.getEmail())) {
    		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                // Escreve os dados do usuário no formato CSV
                String linha = novaConta.getName() + separador + novaConta.getID() + separador + novaConta.getEmail() + separador + novaConta.getSenha() + separador;
                writer.write(linha);
                writer.newLine();

                System.out.println("Dados do usuário foram escritos com sucesso no arquivo CSV.");
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}else {
    		System.err.println("deu ruim se vira");
    	}  
    }

    public static void main(String[] args) {
        // Cria uma instância do formulário
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cadastro();
            }
        });
    }
}
