package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PostText extends Post{

	private String conteudo;
	
	public PostText(String IDusuario,String idPost,String conteudo) {
		super(IDusuario,idPost);
		setConteudo(conteudo);
	}
	public PostText(String IDusuario,String idPost,String conteudo, int curtidas) {
		super(IDusuario,idPost, curtidas);
		setConteudo(conteudo);
	}
	public PostText(String IDusuario,String conteudo) {
		super(IDusuario);
		setConteudo(conteudo);
		}

	@Override 
	public ArrayList<Comentario> getComentarios() {
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        String filePath = "src\\data\\usuarios\\.user_"+ this.getUsuario() +"\\comentarios.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
            	
                String line = scanner.nextLine();
                if(line.isEmpty()) {
                	continue;
                }
                String[] values = line.split(";");
                String IdDonoDoComment = values[0];
                String IdComentario = values[1];
                String Comentario = values[2];
                if(this.getIdPost().equals(IdComentario)) {
                	comentarios.add(new Comentario(IdDonoDoComment,IdComentario, Comentario));
                }                              	
            }
            scanner.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
            return null;
        }
        return comentarios;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
