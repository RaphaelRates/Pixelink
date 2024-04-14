package Swing.Componentes;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Dados;
import entidades.Usuario;

public class DialogEditar extends JDialog {
	private static final long serialVersionUID = 1L;

	public DialogEditar(Usuario usuario) {
		setTitle("Editar Perfil");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.MODELESS);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logo.png")));

		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);

		JButton editarNome = criaBotao("/img/btnEditarNome.png", "/img/btnEditarNomeClicado.png");
		layout.putConstraint(SpringLayout.EAST, editarNome, -20, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, editarNome, 50, SpringLayout.NORTH, panel);
		editarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCampo(usuario, "nome");
			}
		});
		panel.add(editarNome);

		JButton editarSenha = criaBotao("/img/btnEditarSenha.png", "/img/btnEditarSenhaClicado.png");
		layout.putConstraint(SpringLayout.WEST, editarSenha, 90, SpringLayout.HORIZONTAL_CENTER, editarNome);
		layout.putConstraint(SpringLayout.NORTH, editarSenha, 0, SpringLayout.NORTH, editarNome);
		editarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCampo(usuario, "senha");
			}
		});
		panel.add(editarSenha);

		JButton editarFoto = criaBotao("/img/btnEditarFoto.png", "/img/btnEditarFotoClicado.png");
		layout.putConstraint(SpringLayout.EAST, editarFoto, 50, SpringLayout.HORIZONTAL_CENTER, editarNome);
		layout.putConstraint(SpringLayout.NORTH, editarFoto, 50, SpringLayout.SOUTH, editarNome);
		editarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AlterarFotoDoPerfil(usuario);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(editarFoto);

		JButton editarBiografia = criaBotao("/img/btnEditarBiografia.png", "/img/btnEditarBiografiaClicado.png");
		layout.putConstraint(SpringLayout.WEST, editarBiografia, 90, SpringLayout.HORIZONTAL_CENTER, editarFoto);
		layout.putConstraint(SpringLayout.NORTH, editarBiografia, 0, SpringLayout.NORTH, editarFoto);
		editarBiografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCampo(usuario, "biografia");
			}
		});
		panel.add(editarBiografia);

		JButton excluir = criaBotao("/img/btnExcluirConta.png", "/img/btnExcluirContaClicado.png");
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, excluir, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, excluir, 50, SpringLayout.SOUTH, editarFoto);
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirConta(usuario);
			}
		});
		panel.add(excluir);

		setPreferredSize(new Dimension(300, 350));
		add(panel);
		pack();
		setLocationRelativeTo(null);
	}
	
	private JButton criaBotao(String caminhoImagem, String caminhoImagemClicada) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setIcon(new ImageIcon(getClass().getResource(caminhoImagem)));
		button.setPreferredSize(new Dimension(103, 43));
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(caminhoImagemClicada)));
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				button.setIcon(new ImageIcon(getClass().getResource(caminhoImagem)));
				button.setCursor(Cursor.getDefaultCursor());
			}
		});
		return button;
	}

	private void AlterarCampo(Usuario usuario, String campo) {
		String caminhoArquivo = "src\\data\\usuarios.csv";
		String idUsuario = usuario.getID();
		String CampoAlterado = "";
		String key;
		if (!campo.equals("imagemPerfil")) {
			do {
				key = permissao(usuario);
				if (key.equals("exit")) {
					return;
				} else {
					continue;
				}
			} while (!(key.equals("yes")));
		}

		switch (campo) {
		case "senha":
			do {
				CampoAlterado = JOptionPane.showInputDialog(null, "Senha atual: " + usuario.getSenha(), "Alterar Senha",
						JOptionPane.PLAIN_MESSAGE);
				if (CampoAlterado == null)
					return;
			} while (CampoAlterado.length() <= 8);
			break;
		case "nome":
			do {
				CampoAlterado = JOptionPane.showInputDialog(null, "Nome atual: " + usuario.getName(), "Alterar Nome",
						JOptionPane.PLAIN_MESSAGE);
				if (CampoAlterado == null)
					return;
			} while (CampoAlterado.isEmpty());
			break;
		case "biografia":
			do {
				CampoAlterado = JOptionPane.showInputDialog(null, "Biografia atual: " + usuario.getBiografia(),
						"Alterar Biografia", JOptionPane.PLAIN_MESSAGE);
				if (CampoAlterado == null)
					return;
			} while (CampoAlterado.isEmpty());
			break;
		case "imagemPerfil":
			CampoAlterado = usuario.getFotoDoPerfil().toString();
			break;
		default:
			break;
		}
		List<Usuario> usuarios = Dados.lerUsuarios();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
			for (Usuario user : usuarios) {
				String linha = user.getName() + ";" + user.getID() + ";" + user.getEmail() + ";" + user.getSenha() + ";"
						+ user.getFotoDoPerfil().toString() + ";" + user.getSeguidores() + ";" + user.getSeguindo()
						+ ";";
				String[] partes = linha.split(";");
				if (partes[1].equals(idUsuario) && campo.equals("senha")) {
					partes[3] = CampoAlterado;
					Dados.escreverCache(new Usuario(user.getName(), user.getEmail(), CampoAlterado, user.getID(),
							user.getFotoDoPerfil(), user.getSeguidores(), user.getSeguindo()));
				}
				if (partes[1].equals(idUsuario) && campo.equals("nome")) {
					partes[0] = CampoAlterado;
					Dados.escreverCache(new Usuario(CampoAlterado, user.getEmail(), user.getSenha(), user.getID(),
							user.getFotoDoPerfil(), user.getSeguidores(), user.getSeguindo()));
				}
				if (partes[1].equals(idUsuario) && campo.equals("biografia")) {
					AlterarBiografia(usuario, CampoAlterado);
				}
				if (partes[1].equals(idUsuario) && campo.equals("imagemPerfil")) {
					partes[4] = CampoAlterado;
				}
				linha = String.join(";", partes);
				bw.write(linha);
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void AlterarFotoDoPerfil(Usuario usuario) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("escolha uma foto de perfil");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens (*.jpeg, *.png, *.jpg)", "jpg", "jpeg",
				"png");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(new Frame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			java.io.File file = fileChooser.getSelectedFile();
			String caminhoNovaImagem = file.getAbsolutePath();
			usuario.setFotoDoPerfil(new ImageIcon(caminhoNovaImagem));
			String caminhoArquivo = "src\\data\\usuarios\\.user_" + usuario.getID() + "\\foto_perfil_"
					+ usuario.getID();
			String[] extensoes = { ".jpeg", ".png", ".jpg" };
			for (String versoes : extensoes) {
				File arquivo = new File(caminhoArquivo + versoes);
				if (arquivo.exists()) {
					if (arquivo.delete()) {
					}
				} else {
				}
			}
			Dados.SalvarImagem(usuario, "src\\data\\usuarios\\." + "user_" + usuario.getID(), "foto_perfil_", null);
			AlterarCampo(usuario, "imagemPerfil");
			Dados.escreverCache(usuario);

		}
	}

	private void AlterarBiografia(Usuario usuario, String Novabiografia) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + usuario.getID() + "\\biografia.csv"))) {
			String linha = Novabiografia;
			writer.write(linha);
			writer.newLine(); // Adiciona uma nova linha
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ExcluirConta(Usuario usuario) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Deseja mesmo excluir sua conta? seus posts, curtidas e comentarios não irão mais existir, mas sua seguida em outros usuarios continuarão salvos",
				"Confirmação", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {

			String caminhoPasta = "src\\data\\usuarios\\.user_" + usuario.getID();
			File pasta = new File(caminhoPasta);
			if (pasta.exists() && pasta.isDirectory()) {
				excluirPastaRecursivamente(pasta);
				String caminhoArquivo = "src\\data\\usuarios.csv";
				String idUsuario = usuario.getID();
				List<Usuario> usuarios = Dados.lerUsuarios();
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
					for (Usuario user : usuarios) {
						String linha = user.getName() + ";" + user.getID() + ";" + user.getEmail() + ";"
								+ user.getSenha() + ";" + user.getFotoDoPerfil().toString() + ";" + user.getSeguidores()
								+ ";" + user.getSeguindo() + ";";
						String[] partes = linha.split(";");
						if (!partes[1].equals(idUsuario)) {
							bw.write(linha);
							bw.newLine();
						}
						;
					}
					Dados.apagarTudo("src\\data\\cache.csv");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("A pasta especificada não existe ou não é um diretório.");

			}
		}
		try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.exit(0);
		
	}

	private static void excluirPastaRecursivamente(File pasta) {
		if (pasta.listFiles() != null) {
			for (File arquivo : pasta.listFiles()) {
				if (arquivo.isDirectory()) {
					excluirPastaRecursivamente(arquivo);
				} else {
					arquivo.delete();
				}
			}
		}
		pasta.delete();
	}

	private String permissao(Usuario usuario) {
	    try {
	        if (usuario == null) {
	            JOptionPane.showMessageDialog(null, "Usuário inválido", "Erro", JOptionPane.ERROR_MESSAGE);
	            return "exit";
	        }

	        JPasswordField passwordField = new JPasswordField();
	        JCheckBox showPassword = new JCheckBox("Mostrar senha");
	        showPassword.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                
	                if (showPassword.isSelected()) {
	                    passwordField.setEchoChar((char) 0); 
	                } else {
	                    passwordField.setEchoChar('*'); 
	                }
	            }
	        });

	        Object[] message = { "Digite sua senha atual:", passwordField, showPassword };

	        int option = JOptionPane.showConfirmDialog(null, message, "Confirmação", JOptionPane.OK_CANCEL_OPTION);

	        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
	            return "exit";
	        }

	        String scan = new String(passwordField.getPassword());

	        if (scan.equals(usuario.getSenha())) {
	            return "yes";
	        } else {
	            JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
	            return "no";
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro",
	                JOptionPane.ERROR_MESSAGE);
	        return "exit";
	    }
	}
}