package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import excepciones.CampoNuloException;
import excepciones.SinConfiguracionException;
import accesoDB.Conexion;
import campos.Entero;
import campos.CarEncriptados;

public class Aplicacion {

	private HierarchicalINIConfiguration configuracion;
	private HashMap<String,Fuente> fuentes;
	private String caminoFuentes;
	private static Logger alog = Logger.getLogger( Aplicacion.class );
	
	public HierarchicalINIConfiguration getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(HierarchicalINIConfiguration configuracion) {
		this.configuracion = configuracion;
	}

	public HashMap<String, Fuente> getFuentes() {
		return fuentes;
	}

	public void setFuentes(HashMap<String, Fuente> fuentes) {
		this.fuentes = fuentes;
	}

	public String getCaminoFuentes() {
		return caminoFuentes;
	}

	public void setCaminoFuentes(String caminoFuentes) {
		this.caminoFuentes = caminoFuentes;
	}
	
	public Aplicacion( Configuracion configuracion) {
		super();
		this.configuracion = configuracion.getInifile();
	}

	public void cargar() throws SinConfiguracionException{
		
			alog.info( "Cargando fuente y campos" );
			
			String[] fuentes = configuracion.getStringArray( "principal.fuentes" );
			setCaminoFuentes( configuracion.getString( "principal.camino" ) );
			Fuente fuente = null;
			HashMap<String,Fuente> lasFuentes = new HashMap<String,Fuente>();
			
			for ( int i=0 ; i < fuentes.length ; i++ ){
				//System.out.println("Las fuentes son: " + fuentes[i] );
				alog.info( "Cargando fuente: " + fuentes[i] );
				fuente = new Fuente( fuentes[i], getConfiguracion() );
				fuente.cargar();
				lasFuentes.put( fuentes[i], fuente );
			}
			setFuentes( lasFuentes );
			
	}
	
	public void procesar() {

		Fuente fuente = null;
		for ( Iterator<Fuente> lstFnte =  getFuentes().values().iterator() ; lstFnte.hasNext() ; ){
			
			fuente = lstFnte.next();
			Connection con = Conexion.getConexion( getCaminoFuentes() );
			Statement stmt = null;
			String selTable = "SELECT * FROM " + fuente.getName().toUpperCase() + " ";
			
			alog.info( "Procesando fuente: " + fuente.getName() );
			
			try {
				
				stmt = con.createStatement();
		
				ResultSet rs = stmt.executeQuery(selTable);
				
				while (rs.next()){
					try{
						fuente.procesar(rs);
						if ( fuente.getName().equalsIgnoreCase("CODIGOS") ){
							alog.info( "Fuente CODIGOS. ");
						}
						
						fuente.volcar();
					} catch ( CampoNuloException e ){
						alog.error("Campo nulo en registro: " + rs.getRow() );
					}
				} 
				
				fuente.cerrar();
				
			} catch (SQLException e) {
				alog.error( "Error al procesar fuentes: " + e.getMessage() );
			}		
			
		}
		
	}

}
