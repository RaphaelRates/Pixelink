package br.com.Pixelink.entidades;

import java.security.SecureRandom;
import java.util.List;

public abstract class Conta {
	
	private String name;
	private String email;
	private String senha;
	
	//LISTS
	private List<PostVideo> Videos;
	private List<PostText> Textos;
	private List<Seguidor> seguidores;

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
	

	public List<PostVideo> getVideos() {
		return Videos;
	}

	public List<PostText> getTextos() {
		return Textos;
	}
	
	public List<Seguidor> getSeguidores() {
		return seguidores;
	}
	
//======================= funcionalidades ==============================
	
	public final String generateID() {
        // Verifica se o nome de usuário é válido
        
        // Define os caracteres possíveis para o conjunto aleatório
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Define o tamanho do conjunto aleatório (neste caso, 5 caracteres)
        int length = 5;
        
        // Gera o conjunto aleatório de caracteres
        StringBuilder randomString = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        
        // Concatena "#" com o conjunto aleatório, o nome de usuário e um número aleatório
        String id = "#" + randomString.toString();
        return id;
    }
	
}
