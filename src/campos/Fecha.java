package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class Fecha extends Campo {

	private String valorS;
	private Date valor;
	private Date decodificado;
	private Date interpetado;
	private static Logger log = Logger.getLogger( Caracteres.class );
	
	public Fecha(String tipo, String nombre) {
		super(tipo, nombre);
	}

	@Override
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException {
		
		try {
			this.setValor( fila.getDate( this.getNombre().toUpperCase() ));
			if ( getValor() == null  && !this.isPermiteNulo()) {
				throw new CampoNuloException( "Campo nulo " + this.getNombre() );
			} else {
				if ( getValor() == null ) {
					setValorS( "" );
				} else {
					setValorS( getValor().toString() );
				}
			}
		} catch (SQLException e) {
			log.error("Error al cargar campo: " +  this.getNombre() + ". " + e.getMessage());
		}

	}
	
	public String getValorS() {
		return valorS;
	}

	public void setValorS(String valorS) {
		this.valorS = valorS;
	}

	public Date getValor() {
		return valor;
	}

	public void setValor(Date valor) {
		this.valor = valor;
	}

	public Date getDecodificado() {
		return decodificado;
	}

	public void setDecodificado(Date decodificado) {
		this.decodificado = decodificado;
	}

	public Date getInterpetado() {
		return interpetado;
	}

	public void setInterpetado(Date interpetado) {
		this.interpetado = interpetado;
	}

	@Override
	public void interpretar() {
		
		Date elValor = this.valor;
		if ( elValor  != null ) {
			this.interpetado = elValor ;
		} else {
			this.interpetado = null ;
		}		

	}

	@Override
	public void procesar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getValueS() {

		return this.valorS;
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
