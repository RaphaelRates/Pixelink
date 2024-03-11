package br.com.Pixelink.entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public interface post {
	public static Integer idPost = null;
	public static String usuario = "";
	public static List<Comentario> commentarios = null; 
	public static List<Curtida> curtidas = null;


	public Integer getIdPost() ;
	public void setIdPost(Integer idPost);


	public String getUsuario() ;
	public void setUsuario(String usuario);


	public List<Comentario> getCommentarios();
	public static List<Curtida> getCurtidas() {
		return curtidas;
	}
	
	@SuppressWarnings("null")
	public static List<Comentario> getComentarios() {
		List<Comentario> comentarios = null;
        String filePath = "src\\br\\com\\Pixelink\\data\\usuarios"+ this.getUsuario() +"\\comentarios.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(";");
                String IdDonoDoComment = values[0];
                int IdComentario = Integer.parseInt(values[1]);
                String Comentario = values[2];
                comentarios.add(new Comentario(IdComentario,IdDonoDoComment, Comentario));
            }
            scanner.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return comentarios;
    }
	
	public static void AddCurtida() {
		curtidas.add(new Curtida());
    }
}
