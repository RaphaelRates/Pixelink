package entidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.ImageIcon;

import Interfaces.SubjectUsuario;
import data.Dados;

public class Usuario extends Conta implements SubjectUsuario {

	private final String ID;
	private List<PostImage> postsImage;
	private List<PostText> PostsText;
	private ImageIcon fotoDoPerfil;
	private Integer seguidores;
	private Integer seguindo;
	private String biografia;

	public Usuario(String nome, String email, String senha, String id, ImageIcon fotoDePerfil, Integer seguidores,
			Integer seguindo, String biografia) {
		super(nome, email, senha);
		this.ID = id;
		this.fotoDoPerfil = fotoDePerfil;
		this.seguidores = seguidores;
		this.seguindo = seguindo;
		this.biografia = biografia;
	}

	public Usuario(String nome, String email, String senha) {
		super(nome, email, senha);
		this.ID = generateID();
		fotoDoPerfil = new ImageIcon(getClass().getResource("/Img/fotoPerfilPadrão.png"));
		this.setSeguidores(0);
		this.setSeguindo(0);
		this.biografia = "olá, sou um novo usuario do Pixelink";
	}

	public Usuario(String nome, String email, String senha, ImageIcon fotoDoPerfil) {
		super(nome, email, senha);
		this.ID = generateID();
		this.fotoDoPerfil = fotoDoPerfil;
		this.setSeguidores(0);
		this.setSeguindo(0);
		this.biografia = "olá, sou um novo usuario do Pixelink";

	}

	public Usuario(String nome, String email, String senha, String id, ImageIcon fotoDoPerfil) {
		super(nome, email, senha);
		this.ID = id;
		this.fotoDoPerfil = fotoDoPerfil;
		this.setSeguidores(0);
		this.setSeguindo(0);
		this.biografia = "olá, sou um novo usuario do Pixelink";
	}

	public Usuario(String nome, String email, String senha, String id) {
		super(nome, email, senha);
		this.ID = id;
		this.setSeguidores(0);
		this.setSeguindo(0);
	}

	public Usuario(String nome, String email, String senha, String id, ImageIcon fotoDePerfil, Integer seguidores,
			Integer seguindo) {
		super(nome, email, senha);
		this.ID = id;
		this.fotoDoPerfil = fotoDePerfil;
		this.seguidores = seguidores;
		this.seguindo = seguindo;
	}

	public Usuario(String nome, String id, ImageIcon fotoDePerfil) {
		super(nome);
		this.ID = id;
		this.fotoDoPerfil = fotoDePerfil;
		this.setSeguidores(0);
		this.setSeguindo(0);
	}

//---------------- GETTERS E SETTERS ----------------

	public ImageIcon getFotoDoPerfil() {
		return fotoDoPerfil;
	}

	public String getID() {
		return ID;
	}

	public void setFotoDoPerfil(ImageIcon fotoDoPerfil) {
		this.fotoDoPerfil = fotoDoPerfil;
	}

	public Integer getSeguidores() {
		return seguidores;
	}

	public Integer getSeguindo() {
		return seguindo;
	}

	public void setSeguidores(Integer seguidores) {
		this.seguidores = seguidores;
	}

	public void setSeguindo(Integer seguindo) {
		this.seguindo = seguindo;
	}

	public String getBiografia() {
		String biografia = "Olá, estou no Pixelink!";
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\biografia.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line == "") {
					continue;
				} else {

				}
				biografia = line;

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return biografia;
	}

//---------------- FUNCIONALIDADE DOS POSTS----------------------
	public ArrayList<PostImage> getPostsImage() {
		this.postsImage = new ArrayList<PostImage>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\PostsImagens.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line == "") {
					Dados.apagarTudo(filePath);
					return (ArrayList<PostImage>) postsImage;
				}
				String[] values = line.split(";");
				String idUsuario = values[0];
				String idPost = values[1];
				String caminhoFoto = values[2];
				String sobre = values[3];
				String curtidas = values[4];
				postsImage.add(new PostImage(idUsuario, idPost, new ImageIcon(caminhoFoto), sobre,
						Integer.parseInt(curtidas)));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return (ArrayList<PostImage>) postsImage;
	}

	public List<PostText> getPostText() {
		this.PostsText = new ArrayList<PostText>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\PostsTexto.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) { // Correção aqui
					continue; // Ignora linhas vazias
				}
				String[] values = line.split(";");
				String idUsuario = values[0];
				String idPost = values[1];
				String conteudo = values[2];
				String curtidas = values[3];
				PostsText.add(new PostText(idUsuario, idPost, conteudo, Integer.parseInt(curtidas)));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return PostsText;
	}

	public ArrayList<Post> getAllPost() {
		ArrayList<Post> posts = new ArrayList<Post>(this.getPostText());
		posts.addAll(this.getPostsImage());
		return posts;
	}

	public void criarPostImage(PostImage post) throws IOException {
		Dados.SalvarImagem(this, "src\\data\\usuarios\\.user_" + this.getID() + "\\imagens", "imagePost_", post);
		String linha = post.getUsuario() + ";" + post.getIdPost() + ";" + post.getConteudo().toString() + ";"
				+ post.getTexto() + ";0;";
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + this.getID() + "\\PostsImagens.csv", true))) {
			writer.write(linha);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void criarPostText(PostText post) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + this.getID() + "\\PostsTexto.csv", true))) {
			String linha = post.getUsuario() + ";" + post.getIdPost() + ";" + post.getConteudo() + ";0;";
			writer.write(linha);
			writer.newLine(); // Adiciona uma nova linha
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//--------------------- SEGUINDO E EGUIDORES----------------------	

	public ArrayList<Seguidor> getListaSeguidores() {
		ArrayList<Seguidor> seguidores = new ArrayList<Seguidor>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\seguidores.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] values = line.split(";");
				String IdSeguidor = values[0];
				seguidores.add(new Seguidor(Dados.getUsuario(IdSeguidor)));
			}
			scanner.close();
			this.setSeguidores(seguidores.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return seguidores;
	}

	public ArrayList<Seguidor> getListaSeguindo() {
		ArrayList<Seguidor> seguindo = new ArrayList<Seguidor>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\seguindo.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] values = line.split(";");
				String IdSeguindo = values[0];
				seguindo.add(new Seguidor(Dados.getUsuario(IdSeguindo)));
				this.setSeguindo(seguindo.size());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return seguindo;
	}

	@Override
	public void seguir(Seguidor observer) {
		ArrayList<Seguidor> seguindo = getListaSeguindo();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + this.getID() + "\\seguindo.csv", true))) {
			String linha = observer.getseguidor().getID() + ";";
			writer.write(linha);
			writer.newLine();
			seguindo.add(new Seguidor(Dados.getUsuario(observer.getseguidor().getID())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Seguidor> seguidores = Dados.getUsuario(observer.getseguidor().getID()).getListaSeguidores();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				"src\\data\\usuarios\\.user_" + observer.getseguidor().getID() + "\\seguidores.csv", true))) {
			String linha = this.getID() + ";";
			writer.write(linha);
			writer.newLine();
			seguidores.add(new Seguidor(Dados.getUsuario(this.getID())));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deixarDeSeguir(Seguidor observer) {
	    List<Seguidor> seguindo = getListaSeguindo();
	    List<Seguidor> novaListaSeguindo = new ArrayList<>();

	    try (BufferedWriter writer = new BufferedWriter(
	            new FileWriter("src\\data\\usuarios\\.user_" + this.getID() + "\\seguindo.csv"))) {
	        for (Seguidor seguidor : seguindo) {
	            if (!seguidor.getseguidor().getID().equals(observer.getseguidor().getID())) {
	                String linha = seguidor.getseguidor().getID() + ";";
	                writer.write(linha);
	                writer.newLine();
	                novaListaSeguindo.add(new Seguidor(Dados.getUsuario(seguidor.getseguidor().getID())));
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    seguindo.clear();
	    seguindo.addAll(novaListaSeguindo);

	    List<Seguidor> seguidores = Dados.getUsuario(observer.getseguidor().getID()).getListaSeguidores();
	    List<Seguidor> novaListaSeguidores = new ArrayList<>();

	    try (BufferedWriter writer = new BufferedWriter(
	            new FileWriter("src\\data\\usuarios\\.user_" + observer.getseguidor().getID() + "\\seguidores.csv"))) {
	        for (Seguidor seguidor : seguidores) {
	            if (seguidor.getseguidor() != null && !seguidor.getseguidor().getID().equals(this.getID())) {
	                String linha = seguidor.getseguidor().getID() + ";";
	                writer.write(linha);
	                writer.newLine();
	                novaListaSeguidores.add(new Seguidor(Dados.getUsuario(seguidor.getseguidor().getID())));
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    seguidores.clear();
	    seguidores.addAll(novaListaSeguidores);
	}

	public boolean verificarSeguidor(Seguidor observer) {
		ArrayList<Seguidor> seguidores = this.getListaSeguindo();
		for (Seguidor seguidor : seguidores) {
			if (seguidor.getseguidor().getID().equals(observer.getseguidor().getID())) {
				return true;
			}
		}
		return false;
	}

//------------------ COMENTARIOS ------------------ 

	public void Comentar(Post post, String comentario) {
		post.addComentario(this, post, comentario);
	}

// ----------------- NOTIFICAÇÕES -----------------

	public ArrayList<Notificacao> getNotificacoes() {
		ArrayList<Notificacao> notificacoes = new ArrayList<Notificacao>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\notificacoes.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] values = line.split(";");
				String UsuarioDaAcao = values[0];
				String tipo = values[1];
				if (Dados.verificarIDExiste(UsuarioDaAcao)) {
					notificacoes.add(new Notificacao(Dados.getUsuario(UsuarioDaAcao), tipo));
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return notificacoes;
	}

	@Override
	public void enviarNotificacao(Seguidor observer, String tipo) {
		ArrayList<Notificacao> notificacões = this.getNotificacoes();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				"src\\data\\usuarios\\.user_" + observer.getseguidor().getID() + "\\notificacoes.csv", true))) {
			String linha = this.getID() + ";" + tipo + ";";
			writer.write(linha);
			writer.newLine(); // Adiciona uma nova linha
			notificacões.add(new Notificacao(observer.getseguidor(), tipo));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void enviarNotificacaoParaTodos(String tipo) {
		ArrayList<Seguidor> seguidores = getListaSeguidores();
		for (Seguidor seguidor : seguidores) {
			enviarNotificacao(seguidor, tipo);
		}

	}

// ----------------- CURTIDAS ---------------------

	public void CurtirPost(Post post) {
		Set<Curtida> curtidas = post.getListaCurtidas();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + post.getUsuario() + "\\curtidas.csv", true))) {
			String linha = this.getID() + ";" + post.getIdPost() + ";";
			writer.write(linha);
			writer.newLine(); // Adiciona uma nova linha
			curtidas.add(new Curtida(this, post.getIdPost()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Set<Curtida> getAllListaCurtidas() {
		Set<Curtida> curtidas = new HashSet<Curtida>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getID() + "\\curtidas.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] values = line.split(";");
				String IdUsuario = values[0];
				String IdPost = values[1];
				curtidas.add(new Curtida(Dados.getUsuario(IdUsuario), IdPost));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return curtidas;
	}

	public void DescurtirPost(Post post) {
		Set<Curtida> curtidas = Dados.getUsuario(post.getUsuario()).getAllListaCurtidas();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + post.getUsuario() + "\\curtidas.csv"))) {
			for (Curtida curtida : curtidas) {
				if (curtida.getUsuario() == null)
					continue;
				if (curtida.getUsuario().getID().equals(this.getID()) && post.getIdPost().equals(curtida.getPost()))
					continue;
				String linha = curtida.getUsuario().getID() + ";" + curtida.getPost() + ";";
				writer.write(linha);
				writer.newLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
