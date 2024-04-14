package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class PostImage extends Post {

	private ImageIcon conteudo;
	private String texto;
	private List<Comentario> comentarios;

	public PostImage(String IDusuario, String idPost, ImageIcon conteudo, String texto, int curtidas) {
		super(IDusuario, idPost, curtidas);
		this.comentarios = getComentarios();
		setConteudo(conteudo);
		this.setTexto(texto);

	}

	public PostImage(String IDusuario, String idPost, ImageIcon conteudo, String texto) {
		super(IDusuario, idPost);
		this.comentarios = getComentarios();
		setConteudo(conteudo);
		this.setTexto(texto);
	}

	public PostImage(String IDusuario, ImageIcon conteudo, String texto) {
		super(IDusuario);
		this.comentarios = getComentarios();
		setConteudo(conteudo);
		setTexto(texto);
	}

	public ImageIcon getConteudo() {
		return conteudo;
	}

	public void setConteudo(ImageIcon conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public ArrayList<Comentario> getComentarios() {
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		String filePath = "src\\data\\usuarios\\.user_" + this.getUsuario() + "\\comentarios.csv";
		File file = new File(filePath);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				String[] values = line.split(";");
				String IdDonoDoComment = values[0];
				String IdComentario = values[1];
				String Comentario = values[2];
				if (this.getIdPost().equals(IdComentario)) {
					comentarios.add(new Comentario(IdDonoDoComment, IdComentario, Comentario));
				}
			}
			scanner.close();
		} catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
		return comentarios;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
