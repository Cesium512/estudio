package negocio;

public abstract class Vuelco implements IVuelco {

	private String tipo;
	private String nfuente;
	
	public Vuelco( String nombre, String tipo ) {
		this.nfuente = nombre;
		this.tipo = tipo;
		
	}

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNfuente() {
		return nfuente;
	}


	public void setNfuente(String nfuente) {
		this.nfuente = nfuente;
	}

	
	
}
