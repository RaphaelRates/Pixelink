package br.com.Pixelink.entidades;

import java.security.SecureRandom;

import br.com.Pixelink.data.Dados;

public abstract class Conta {
	
	private String name;
	private String email;
	private String senha;
	
	//LISTS
	

//================== CONTRUTORES =====================
	public Conta(String name, String email, String senha) {
		setName(name);
		setEmail(email);
		setSenha(senha);
	}
	
//================== GETTERS E SETTERS  =====================
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
//======================= funcionalidades ==============================
	
	public final String generateID() {
		String id;
		do {
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder randomString = new StringBuilder();
	        SecureRandom secureRandom = new SecureRandom();
	        for (int i = 0; i < 5; i++) {
	            int randomIndex = secureRandom.nextInt(characters.length());
	            randomString.append(characters.charAt(randomIndex));
	        }
	       id = "#" + randomString.toString();
		} while (Dados.verificarIDExiste(id));
        return id;
        
    }
	
}
