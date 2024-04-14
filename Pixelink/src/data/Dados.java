package data;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Swing.Componentes.Alert;
import entidades.PostImage;
import entidades.Usuario;

public class Dados {
	private static final String CAMINHO_ARQUIVO_USUARIOS = "src\\data\\usuarios.csv";

	public static boolean verificarUsuarioExistente(String email, String senha) {
		List<Usuario> usuarios = Dados.lerUsuarios();
		for (Usuario usuario : usuarios) {

			if ((usuario.getEmail().equals(email) || usuario.getID().equals(email))
					&& usuario.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}

	public static boolean verificarIDExiste(String ID) {
		List<Usuario> usuarios = Dados.lerUsuarios();
		for (Usuario usuario : usuarios) {
			if (usuario.getID().equals(ID))
				return true;
		}
		return false;
	}

	public static Usuario getUsuario(String ID) {
		List<Usuario> usuarios = Dados.lerUsuarios();
		for (Usuario usuario : usuarios) {
			if(usuario == null) continue;
			if (usuario.getID().equals(ID))
				return usuario;
		}
		return null;
	}
 
	public static void CriarConta(Usuario novaConta) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\usuarios.csv", true))) {

			String caminhoPasta = "src\\data\\usuarios\\." + "user_" + novaConta.getID();
			File pasta = new File(caminhoPasta);
			if (!pasta.exists()) {
				if (pasta.mkdir()) {
					criarArquivo(new File(caminhoPasta, "PostsTexto.csv"));
					criarArquivo(new File(caminhoPasta, "notificacoes.csv"));
					criarArquivo(new File(caminhoPasta, "PostsImagens.csv"));
					criarArquivo(new File(caminhoPasta, "seguidores.csv"));
					criarArquivo(new File(caminhoPasta, "seguindo.csv"));
					criarArquivo(new File(caminhoPasta, "curtidas.csv"));
					criarArquivo(new File(caminhoPasta, "comentarios.csv"));
					criarArquivo(new File(caminhoPasta, "biografia.csv"));
					new File(caminhoPasta + "\\imagens").mkdir();
					SalvarImagem(novaConta, "src\\data\\usuarios\\." + "user_" + novaConta.getID(), "foto_perfil_",
							null);
				} else {
					Alert.exibirErro("Ocorreu erro na criação da sua conta, tente novamente");
				}
			} else {
				Alert.exibirErro("parece que esse usuario ja existe na plataforma");
			}
			String linha = novaConta.getName() + ";" + novaConta.getID() + ";" + novaConta.getEmail() + ";"
					+ novaConta.getSenha() + ";" + novaConta.getFotoDoPerfil().toString() + ";"
					+ novaConta.getSeguidores() + ";" + novaConta.getSeguindo() + ";";

			writer.write(linha);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 
	private static void criarArquivo(File arquivo) {
		try {
			if (arquivo.createNewFile()) {
				
			} else {
				System.out.println("O arquivo " + arquivo.getName() + " já existe.");
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao criar o arquivo " + arquivo.getName() + ": " + e.getMessage());
		}
	}

	public static void Logar(String email, String senha) {
		List<Usuario> usuarios = lerUsuarios();
		for (Usuario usuario : usuarios) {
			if ((usuario.getEmail().equals(email) || usuario.getID().equals(email))
					&& usuario.getSenha().equals(senha)) {
				escreverCache(usuario);
				return;
			}
		}
	}

	public static void escreverCache(Usuario usuario) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\cache.csv"))) {
			Dados.apagarTudo("src\\data\\cache.csv");
			String linha = usuario.getName() + ";" + usuario.getID() + ";" + usuario.getEmail() + ";"
					+ usuario.getSenha() + ";" + usuario.getFotoDoPerfil().toString() + ";" + usuario.getSeguidores()
					+ ";" + usuario.getSeguindo() + ";";
			writer.write(linha);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void apagarTudo(String caminho) {
		try {
			FileWriter writer = new FileWriter(caminho);
			writer.close();
		} catch (IOException e) {
			System.err.println("Erro ao apagar o conteúdo do arquivo CSV: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String filePath = "src\\data\\usuarios.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line == "") {
					continue;
				}
				String[] values = line.split(";");
				String nome = values[0];
				String username = values[1];
				String email = values[2];
				String senha = values[3];
				String ImagePerfil = values[4];
				String seguidores = values[5];
				String seguindo = values[6];
				usuarios.add(new Usuario(nome, email, senha, username, new ImageIcon(ImagePerfil),
						Integer.parseInt(seguidores), Integer.parseInt(seguindo)));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public static Usuario getLogado() {
		Usuario logado = null;
		String filePath = "src\\data\\cache.csv";
		File file = new File(filePath);
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line == "") {
					return null;
				}
				String[] values = line.split(";");
				String nome = values[0];
				String id = values[1];
				String email = values[2];
				String senha = values[3];
				String imagePerfil = values[4];
				String seguidores = values[5];
				String seguindo = values[6];
				if (camposVazios(nome, id, email, senha, imagePerfil, seguidores, seguindo)) {
					return null;
				}
				logado = new Usuario(nome, email, senha, id, new ImageIcon(imagePerfil), Integer.parseInt(seguidores),
						Integer.parseInt(seguindo));
			}
			scanner.close();
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logado;
	}

	private static boolean camposVazios(String... campos) {
		for (String campo : campos) {
			if (campo.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static void SalvarImagem(Usuario conta, String caminho, String tipo, PostImage post) throws IOException {
		String caminhoDoArquivo;
		String novoNomeArquivo;
		Path path;
		String nomeDoArquivo;
		String diretorioOrigem;
		String nomeArquivoOrigem;

		ImageIcon img = null;
		if (post != null) {
			img = post.getConteudo();
			caminhoDoArquivo = img.toString();

		} else {
			caminhoDoArquivo = conta.getFotoDoPerfil().toString();
		}

		path = Paths.get(caminhoDoArquivo);
		nomeDoArquivo = path.getFileName().toString();
		diretorioOrigem = caminhoDoArquivo;
		nomeArquivoOrigem = nomeDoArquivo;

		if (post != null) {
			novoNomeArquivo = post.getIdPost() + "." + VerificarExtensaoArquivo(nomeArquivoOrigem);

		} else {
			novoNomeArquivo = tipo + conta.getID() + "." + VerificarExtensaoArquivo(nomeArquivoOrigem);
		}
		String diretorioDestino = caminho + "\\" + novoNomeArquivo;

		try {
			File arquivoOrigem = new File(diretorioOrigem);
			InputStream inputStream = new FileInputStream(arquivoOrigem);
			BufferedImage imagem = ImageIO.read(inputStream);
			inputStream.close();
			File arquivoDestino = new File(diretorioDestino);
			if (!arquivoDestino.exists()) {
				arquivoDestino.createNewFile();
			}
			OutputStream outputStream = new FileOutputStream(arquivoDestino);
			switch (VerificarExtensaoArquivo(nomeArquivoOrigem)) {
			case "png": {
				ImageIO.write(imagem, "png", outputStream);
				outputStream.close();
				File arquivoOriginal = new File(diretorioDestino);
				File novoArquivo = new File(arquivoOriginal.getParent(), novoNomeArquivo);
				arquivoOriginal.renameTo(novoArquivo);
				if (tipo.equals("foto_perfil_")) {
					conta.setFotoDoPerfil(new ImageIcon(caminho + "\\" + tipo + conta.getID() + "."
							+ VerificarExtensaoArquivo(novoArquivo.toString())));
				}
				if (post != null) {
					post.setConteudo(new ImageIcon(caminho + "\\" + novoNomeArquivo));
				}

				break;
			}
			case "jpg": {
				ImageIO.write(imagem, "jpg", outputStream);
				outputStream.close();
				File arquivoOriginal = new File(diretorioDestino);
				File novoArquivo = new File(arquivoOriginal.getParent(), novoNomeArquivo);
				arquivoOriginal.renameTo(novoArquivo);

				if (tipo.equals("foto_perfil_")) {
					conta.setFotoDoPerfil(new ImageIcon(caminho + "\\" + tipo + conta.getID() + "."
							+ VerificarExtensaoArquivo(novoArquivo.toString())));
				}
				if (post != null) {
					post.setConteudo(new ImageIcon(caminho + "\\" + novoNomeArquivo));
				}
				break;
			}
			case "jpeg": {
				ImageIO.write(imagem, "jpeg", outputStream);
				outputStream.close();
				File arquivoOriginal = new File(diretorioDestino);
				File novoArquivo = new File(arquivoOriginal.getParent(), novoNomeArquivo);
				arquivoOriginal.renameTo(novoArquivo);
				if (tipo.equals("foto_perfil_")) {
					conta.setFotoDoPerfil(new ImageIcon(caminho + "\\" + tipo + conta.getID() + "."
							+ VerificarExtensaoArquivo(novoArquivo.toString())));
				}
				if (post != null) {
					post.setConteudo(new ImageIcon(caminho + "\\" + novoNomeArquivo));
				}
				break;
			}

			}

		} catch (IOException e) {
			System.out.println("Erro ao copiar imagem: " + e.getMessage());
		}
	}

	private static String VerificarExtensaoArquivo(String arquivo) {
		int indiceponto = arquivo.lastIndexOf(".");
		String extenssão = arquivo.substring(indiceponto + 1);
		return extenssão;
	}

	public static Font loadFontFromFile(float fontSize) {
		Font customFont = null;
		try {
			File fontFile = new File("src\\data\\LeagueSpartan-Bold.ttf");
			customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(fontSize);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		return customFont;
	}

	public static ArrayList<Usuario> pesquisarUsuario(String textoPesquisa) {
		ArrayList<Usuario> usuariosEncontrados = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO_USUARIOS))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] dadosUsuario = linha.split(";");
				if (linha == "") {
					return null;
				}
				String nome = dadosUsuario[0];
				String ID = dadosUsuario[1];
				String imagemPerfil = dadosUsuario[4];
				ImageIcon imagemUsuario = new ImageIcon(imagemPerfil);
				if (nome.contains(textoPesquisa) || ID.equals(textoPesquisa)) {
					usuariosEncontrados.add(new Usuario(nome, "", "", ID, imagemUsuario));
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo de usuários: " + e.getMessage());
		}

		return usuariosEncontrados;
	}

}
