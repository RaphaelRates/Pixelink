package br.com.Pixelnik.entidades;

public class Usuario extends Conta{

	private final String ID;
	
	public Usuario(String nome, String email, String senha) {
		super(nome, email, senha);
		this.ID = generateID(nome);
		
	}

	public String getID() {
		return ID;
	}

}
