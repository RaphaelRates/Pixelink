package br.com.Pixelnik.entidades;

import java.util.List;

public abstract class Post {

	private Integer idPost;
	private Usuario usuario;
	private List<Comentario> commentarios; 
	private List<Curtida> curtidas;


	public Post() {
		
	}


	public Integer getIdPost() {
		return idPost;
	}


	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Comentario> getCommentarios() {
		return commentarios;
	}
	public List<Curtida> getCurtidas() {
		return curtidas;
	}

}
