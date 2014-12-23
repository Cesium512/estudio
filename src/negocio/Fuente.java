package negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.log4j.Logger;

import campos.CarEncriptados;
import campos.Entero;
import excepciones.CampoNuloException;
import excepciones.SinConfiguracionException;

public class Fuente {

	private String name;
	private Lector lector;
	private HierarchicalINIConfiguration config;
	private ArrayList<Campo> campos ;
	private Vuelco vuelco;
	private Logger log = Logger.getLogger( Fuente.class );
	private boolean encriptada;
	private String campoClave;
	private String campoEncriptado;
	
	public Fuente() {
		
	}
		
	public Fuente(String name, HierarchicalINIConfiguration config) {
		super();
		this.name = name;
		this.config = config;
		this.encriptada = false;
	}

	public Fuente(String name, Lector lector,
			HierarchicalINIConfiguration config) {
		super();
		this.name = name;
		this.lector = lector;
		this.config = config;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public HierarchicalINIConfiguration getConfig() {
		return config;
	}

	public void setConfig(HierarchicalINIConfiguration config) {
		this.config = config;
	}
	
	public ArrayList<Campo> getCampos() {
		return campos;
	}

	public void setCampos( ArrayList<Campo> campos) {
		this.campos = campos;
	}
	
	public Vuelco getVuelco() {
		return vuelco;
	}

	public void setVuelco(Vuelco vuelco) {
		this.vuelco = vuelco;
	}

	public boolean isEncriptada() {
		return encriptada;
	}

	public void setEncriptada(boolean encriptada) {
		this.encriptada = encriptada;
	}
	
	public String getCampoClave() {
		return campoClave;
	}

	public void setCampoClave(String campoClave) {
		this.campoClave = campoClave;
	}

	public String getCampoEncriptado() {
		return campoEncriptado;
	}

	public void setCampoEncriptado(String campoEncriptado) {
		this.campoEncriptado = campoEncriptado;
	}

	public void cargar() throws SinConfiguracionException{
		
		String[] campos = config.getStringArray( getName() + ".campos" );
		Fabrica laFabrica = Fabrica.getInstance();
		Campo campo = null;
		ArrayList<Campo> losCampos = new ArrayList<Campo>();
		// Para las fuentes con campos encriptados cargo el nombre de campo encriptado
		// y el campo que contiene la clave. 
		if ( "CTACTE".equalsIgnoreCase(this.getName()) || "CTAMOV".equalsIgnoreCase(this.getName()) ){
			setEncriptada(true);
			setCampoClave("empresa");
			setCampoEncriptado("descripcio");
		} else if ( "CODIGOS".equalsIgnoreCase(this.getName()) ) {
			setEncriptada(true);
			setCampoClave("codigo");
			setCampoEncriptado("descripcio");
		}
		
		for ( int i=0 ; i < campos.length ; i ++ ){
		
			String[] configCampo = config.getStringArray( getName() + "." + campos[i] );
			log.debug("Creando campo: " + campos[i] );
			campo = laFabrica.crearCampo( campos[i] , configCampo[0], configCampo );
			campo.setPermiteNulo( ("S".equalsIgnoreCase(configCampo[2]))?true:false );
			campo.configInter( laFabrica.crearInterprete( configCampo[1] ) );
			losCampos.add( campo );
		
		}
		
		setCampos( losCampos );
		
		log.debug("En la fuente, cargando vuelco: " + config.getString(  getName() + ".vuelco" ) );
		this.setVuelco( laFabrica.crearVuelco( getName() , config.getString(  getName() + ".vuelco" ) ) );
		
	}
	
	public void procesar( ResultSet fila ) throws CampoNuloException{
//		int nrofila=0;
//		try {
//			nrofila = fila.getRow();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Campo cmp =  null;
		int nroEmpresa = 0;
		for ( Iterator<Campo> lstCamp = this.getCampos().iterator() ; lstCamp.hasNext() ; ){
			cmp = lstCamp.next();
			cmp.cargar( fila , 0 );
			
			if ( this.isEncriptada() ) {
				if ( cmp.getNombre().equalsIgnoreCase( getCampoClave() ) ) {
					nroEmpresa = ((Entero)cmp).getValueI();
				}
				if( cmp.getNombre().equalsIgnoreCase( getCampoEncriptado() ) ) {
					CarEncriptados c = (CarEncriptados)cmp;
					c.cargarClave( nroEmpresa );
				}
			}
			cmp.interpretar();
		}
	}
	
	public void volcar(){
		getVuelco().volcar( getCampos() );
	}

	public void cerrar() {
		
		getVuelco().cerrar();
		
	}
}
