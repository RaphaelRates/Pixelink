package br.com.Pixelnik.entidades;

public class Comentario {

	private Integer id;
	private Usuario usuario;
	private String conteudo; 
	
	public Comentario(Integer id,Usuario usuario,String conteudo) {
		this.id = id;
		this.usuario = usuario;
		this.conteudo = conteudo;
	}
	public Comentario(Integer id,String conteudo) {
		this.id = id;
		this.conteudo = conteudo;
	}
	

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getConteudo() {
		return conteudo;
	}


}
