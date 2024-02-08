package br.com.Pixelink.entidades;

public class Usuario extends Conta{

	private final String ID;
	
	public Usuario(String nome, String email, String senha) {
		super(nome, email, senha);
		this.ID = generateID();
		
	}

	public String getID() {
		return ID;
	}

}
