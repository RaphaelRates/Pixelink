package entidades;

public class Curtida {
	private String postId;
	private Usuario usuario;

	public Curtida(Usuario usuario, String postId) {
		this.postId = postId;
		this.usuario = usuario;
	}

	public Curtida(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPost() {
		return postId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
