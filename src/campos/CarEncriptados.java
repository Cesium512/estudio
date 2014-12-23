package campos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import negocio.Interprete;
import interprete.EncriptadoCobranzas;

import org.apache.log4j.Logger;

import excepciones.CampoNuloException;
import negocio.Campo;

public class CarEncriptados extends Campo {

	private String valor;
	private String decodificado;
	private String interpetado;
	private int clave;
	private String campoClave;
	private static Logger log = Logger.getLogger( Caracteres.class );
	
/*	public CarEncriptados(String tipo, String nombre ) {
		super(tipo, nombre);
	}*/
	
	public CarEncriptados( String tipo, String nombre, String campoClave){
		super(tipo,nombre);
		this.setCampoClave( campoClave );
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
//		try {
//			// Valor de campo clave no esta hay que verificarlo 
//			//fila.absolute( nrofila );
//			String nombreCampoClave = getCampoClave();
//			int numeroColumna = fila.findColumn( nombreCampoClave.toUpperCase() );
//			int clave = fila.getInt( numeroColumna );
//			this.setClave( clave );
//			this.cargarClave( clave );
//			this.setValor( fila.getString( this.getNombre().toUpperCase() ));
//			if ( getValor() == ""  && !this.isPermiteNulo()) {
//				throw new CampoNuloException( "Campo nulo " + this.getNombre() );
//			}
//		} catch (SQLException e) {
//			log.error("Error al cargar campo: " +  this.getNombre() + ". " + e.getMessage());
//		}
	}

	@Override
	public void configInter(Interprete interprete) {
		super.configInter( interprete );
	}
	
	public void cargarClave( int clave ){
		this.setClave( clave );
		this.getInter().cargarClave(clave);
	} 
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getCampoClave() {
		return campoClave;
	}

	public void setCampoClave(String campoClave) {
		this.campoClave = campoClave;
	}

	@Override
	public void interpretar() {
		
		String elValor = this.valor;
		if ( elValor  != null ) {
			this.interpetado = getInter().decodificar( elValor );
			this.decodificado = this.interpetado;
		} else {
			this.interpetado = "";
			this.decodificado = "";
		}
		
	}

	@Override
	public void procesar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValueS() {
		// TODO Auto-generated method stub
		return this.interpetado;
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
