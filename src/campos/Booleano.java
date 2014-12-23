package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class Booleano extends Campo {
	
	private String valor;
	private boolean decodificado;
	private boolean interpetado;
	private static Logger log = Logger.getLogger( Booleano.class );
	
	
	public Booleano(String tipo, String nombre) {
		super(tipo, nombre);
		// TODO Auto-generated constructor stub
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public boolean isDecodificado() {
		return decodificado;
	}
	public void setDecodificado(boolean decodificado) {
		this.decodificado = decodificado;
	}
	public boolean isInterpetado() {
		return interpetado;
	}
	public void setInterpetado(boolean interpetado) {
		this.interpetado = interpetado;
	}

	@Override
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException {
		try {
			this.setValor( fila.getString( this.getNombre().toUpperCase() ));
		} catch (SQLException e) {
			log.error("Error al cargar el campo:" +  this.getNombre() + ". " + e.getMessage() );
		}

	}

	@Override
	public void interpretar() {
		
		this.interpetado = getInter().convertir( this.valor );

	}

	@Override
	public void procesar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getValueS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getValueI() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getValueD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getValueB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getValueF() {
		// TODO Auto-generated method stub
		return 0;
	}

}
