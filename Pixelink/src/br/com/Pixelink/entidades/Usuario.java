package br.com.Pixelink.entidades;

import java.util.List;
import javax.swing.*;

public class Usuario extends Conta{

	private final String iD;
	private List<PostImage> postImagens;
	private List<PostText> textos;
	private List<Seguidor> seguidores;
	private JLabel fotoPerfu;
	
	public Usuario(String nome, String email, String senha) {
		super(nome, email, senha);
		this.iD = generateID();
		
	}
	public Usuario(String nome, String email, String senha, String id) {
		super(nome, email, senha);
		this.iD = id;
	}

	public String getID() {
		return iD;
	}

	public List<PostImage> getImagens() {
		
		return postImagens;
	}
	public void setImagens(List<PostImage> imagens) {
		postImagens = imagens;
	}
	public List<PostText> getTextos() {
		return textos;
	}
	public void setTextos(List<PostText> textos) {
		textos = textos;
	}
	public List<Seguidor> getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(List<Seguidor> seguidores) {
		this.seguidores = seguidores;
	}

	

}
