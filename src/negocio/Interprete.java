package negocio;

import org.apache.log4j.Logger;

public abstract class Interprete implements IInterprete {

	private String nombre;
	
	public Interprete( String nombre ) { 
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
