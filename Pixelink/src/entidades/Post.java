package entidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import data.Dados;

public abstract class Post {

	private String idPost;
	private String IDusuario;
	private int curtidas;

	public Post(String IDusuario, String idPost, int curtidas) {
		setUsuario(IDusuario);
		setIdPost(idPost);
		this.curtidas = curtidas;
	}

	public Post(String IDusuario, String idPost) {
		setUsuario(IDusuario);
		setIdPost(idPost);
		this.curtidas = getCurtidas();
	}

	public Post(String IDusuario) {
		setUsuario(IDusuario);
		setIdPost(gerarID());
		this.curtidas = getCurtidas();
	}

	public String gerarID() {
		String id = "";
		if (getIdPost() == null) {
			do {
				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
				StringBuilder randomString = new StringBuilder();
				SecureRandom secureRandom = new SecureRandom();
				for (int i = 0; i < 5; i++) {
					int randomIndex = secureRandom.nextInt(characters.length());
					randomString.append(characters.charAt(randomIndex));
				}
				id = this.getUsuario() + "_" + randomString.toString();
			} while (Dados.verificarIDExiste(id));
			return id;
		} else {
			return id;
		}
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getUsuario() {
		return IDusuario;
	}

	public void setUsuario(String iDusuario) {
		this.IDusuario = iDusuario;
	}

	public abstract List<Comentario> getComentarios();

	public void addComentario(Usuario usuario, Post post, String comentario) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("src\\data\\usuarios\\.user_" + post.getUsuario() + "\\comentarios.csv", true))) {
			String linha = usuario.getID() + ";" + post.getIdPost() + ";" + comentario + ";";
			writer.write(linha);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCurtidas() {
		int quantidade = (getListaCurtidas().size() > 0) ? getListaCurtidas().size() : 0;
		return quantidade;
	}

	public Set<Curtida> getListaCurtidas() { 
		Set<Curtida> ListaCurtidas = new HashSet<Curtida>();
		this.curtidas = 0;
		String filePath = "src\\data\\usuarios\\.user_" + this.getUsuario() + "\\curtidas.csv";
		File file = new File(filePath);
		if (file.exists()) {
			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.isEmpty()) {
						continue;
					}
					String[] values = line.split(";");
					String UsuarioDaAcao = values[0];
					String PostID = values[1];
					if (PostID.equals(this.getIdPost())) {
						ListaCurtidas.add(new Curtida(Dados.getUsuario(UsuarioDaAcao), PostID));
						this.curtidas++;
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		return ListaCurtidas;
	}

	public boolean VerificarCurtida(String IDUsuario) {
		Set<Curtida> curtidas = this.getListaCurtidas();
		for (Curtida curtida : curtidas) {
			if(curtida.getUsuario() == null) {
				return true;
			}
			if (curtida.getUsuario().getID().equals(IDUsuario)) {
				return true;
			}
		}
		return false;
	}

}
