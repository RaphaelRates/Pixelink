package Interfaces;

import entidades.Seguidor;

public interface SubjectUsuario {
	public void seguir(Seguidor seguidor);
	public void deixarDeSeguir(Seguidor seguidor);
	public void enviarNotificacaoParaTodos(String tipo);
	public void enviarNotificacao(Seguidor seguidor, String tipo);
}
