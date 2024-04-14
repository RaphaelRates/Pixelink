package entidades;

import Interfaces.Observer;

public class Seguidor implements Observer {

	private Usuario seguidor;

	public Seguidor(Usuario seguidor) {
		super();
		setseguidor(seguidor);
	}

	public Usuario getseguidor() {
		return seguidor;
	}

	public void setseguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}

	@Override
	public void update(String tipo) {
		System.out.println(seguidor.getID() + " atualizado");

	}
}
