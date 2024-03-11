package br.com.Pixelink.entidades;

public class Comentario {

	private Integer id;
	private String IdUsuario;
	private String conteudo; 
	
	public Comentario(Integer idPosts,String IdUsuario,String conteudo) {
		this.id = idPosts;
		this.IdUsuario = IdUsuario;
		this.conteudo = conteudo;
	}
	public Comentario(Integer idPosts,String conteudo) {
		this.id = idPosts;
		this.conteudo = conteudo;
	}
	
	public Integer getId() {
		return id;
	}

	public String getUsuario() {
		return IdUsuario;
	}

	public String getConteudo() {
		return conteudo;
	}
	public Integer getIdPots() {
		return id;
	}
	public void setIdPots(Integer idPots) {
		id = idPots;
	}


}
