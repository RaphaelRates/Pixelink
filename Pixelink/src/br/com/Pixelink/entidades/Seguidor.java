package br.com.Pixelink.entidades;

public class Seguidor {

	private Usuario seguir;
    private Usuario seguidor;
		    
		    
		    public Seguidor(Usuario seguir, Usuario seguidor) {
			super();
			setSeguidor(seguidor);
			setSeguir(seguir);
		    }

		    public Seguidor(Usuario seguidor) {
			super();
			setSeguidor(seguidor);
		    }
		    
		    public Usuario getSeguir() {
				return seguir;
			}

			public void setSeguir(Usuario seguir) {
				this.seguir = seguir;
			}

			public Usuario getSeguidor() {
				return seguidor;
			}

			public void setSeguidor(Usuario seguidor) {
				this.seguidor = seguidor;
			}

}
