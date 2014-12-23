package negocio;

import java.sql.ResultSet;

import excepciones.CampoNuloException;

//import org.apache.commons.configuration.HierarchicalINIConfiguration;

/**
 * @author Administrador
 *
 */
public abstract class Campo implements ICampo {

	private String nombre;
	private String tipo;
	private Interprete inter;
	private boolean permiteNulo;
	
	public Campo( String tipo , String nombre ){
		this.tipo = tipo;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Interprete getInter() {
		return inter;
	}

	public void setInter(Interprete inter) {
		this.inter = inter;
	}

	public boolean isPermiteNulo() {
		return permiteNulo;
	}

	public void setPermiteNulo(boolean permiteNulo) {
		this.permiteNulo = permiteNulo;
	}

	public void configInter(Interprete interprete) {
		
		this.setInter( interprete );
		
	}
	
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException{
		
	}
}
