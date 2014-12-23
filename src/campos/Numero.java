package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class Numero extends Campo {
	
	private float valor;
	private float decodificado;
	private float interpetado;
	private static Logger log = Logger.getLogger( Numero.class );

	public Numero(String tipo, String nombre) {
		super(tipo, nombre);
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getDecodificado() {
		return decodificado;
	}

	public void setDecodificado(float decodificado) {
		this.decodificado = decodificado;
	}

	public float getInterpetado() {
		return interpetado;
	}

	public void setInterpetado(float interpetado) {
		this.interpetado = interpetado;
	}

	@Override
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException {
		try {
			this.setValor( fila.getFloat( this.getNombre().toUpperCase() ));
		} catch (SQLException e) {
			log.error("Error al cargar el campo:" +  this.getNombre() + ". " + e.getMessage() );
		}
	}

	@Override
	public void interpretar() {
		
		this.interpetado = getInter().decodificar( this.valor );

	}

	@Override
	public void procesar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getValueS() {
		
		return Float.toString( getValor() ) ;
	}

	@Override
	public int getValueI() {

		return Math.round( getValor() ) ;
		
	}

	@Override
	public Date getValueD() {
		
		return null;
	}

	@Override
	public boolean getValueB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getValueF() {
		 
		return this.getValor();
		
	}

}
