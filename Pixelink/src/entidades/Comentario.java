package entidades;

public class Comentario {

	private String id;
	private String IdUsuario;
	private String conteudo; 
	
	public Comentario(String IdUsuario,String idPost,String conteudo) {
		this.id = idPost;
		this.IdUsuario = IdUsuario;
		this.conteudo = conteudo;
	}
	public Comentario(String idPosts,String conteudo) {
		this.id = idPosts;
		this.conteudo = conteudo;
	}
	
	public String getId() {
		return id; 
	}

	public String getUsuario() {
		return IdUsuario;
	}

	public String getConteudo() {
		return conteudo;
	}
	public String getIdPot() {
		return id;
	}
	public void setIdPot(String idPots) {
		id = idPots;
	}


}