package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class Entero extends Campo {

	private int valor;
	private int decodificado;
	private int interpetado;
	private static Logger log = Logger.getLogger( Entero.class );
	
	public Entero(String tipo, String nombre) {
		super(tipo, nombre);
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getDecodificado() {
		return decodificado;
	}

	public void setDecodificado(int decodificado) {
		this.decodificado = decodificado;
	}

	public int getInterpetado() {
		return interpetado;
	}

	public void setInterpetado(int interpetado) {
		this.interpetado = interpetado;
	}

	@Override
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException {
		
		try {
			this.setValor( fila.getInt( this.getNombre().toUpperCase() ));
			if ( getValor() == 0  && !this.isPermiteNulo()) {
				throw new CampoNuloException( "Campo cero " + this.getNombre() );
			}
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

		return Integer.toString( getValor() );
	}

	@Override
	public int getValueI() {

		return getValor();
		
	}

	@Override
	public Date getValueD() {
		 
		return null;
		
	}

	@Override
	public boolean getValueB() {
		if ( getValor() == 0 ){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public float getValueF() {
		
		return 0;
		
	}

}
