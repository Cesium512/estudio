package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class Caracteres extends Campo {
	
	private String valor;
	private String decodificado;
	private String interpetado;
	private static Logger log = Logger.getLogger( Caracteres.class );

	public Caracteres(String tipo, String nombre) {
		super(tipo, nombre);
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public void cargar(ResultSet fila, int nrofila ) throws CampoNuloException {
		
		try {
			this.setValor( fila.getString( this.getNombre().toUpperCase() ));
			if ( getValor() == ""  && !this.isPermiteNulo()) {
				throw new CampoNuloException( "Campo nulo " + this.getNombre() );
			}
		} catch (SQLException e) {
			log.error("Error al cargar campo: " +  this.getNombre() + ". " + e.getMessage());
		}

	}

	@Override
	public void interpretar() {
		
		String elValor = this.valor;
		if ( elValor  != null ) {
			this.interpetado = getInter().decodificar( elValor );
		} else {
			this.interpetado = "";
		}
		
	}

	@Override
	public void procesar() {


	}

	@Override
	public String getValueS() {
		// TODO Auto-generated method stub
		return this.interpetado ;
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
