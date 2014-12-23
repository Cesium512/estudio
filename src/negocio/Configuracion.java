package negocio;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import excepciones.SinConfiguracionException;

public class Configuracion {

	private static Configuracion instance;
	private HierarchicalINIConfiguration inifile;
	private static Logger log = Logger.getLogger( Configuracion.class );
	
	private Configuracion() throws SinConfiguracionException {
		//log.setLevel( Level.WARN );
		try {
			inifile = new HierarchicalINIConfiguration( "batidora.ini" );
						
		} catch (ConfigurationException e) {
			log.log(Level.ERROR, e.getMessage() );
			throw new SinConfiguracionException( e );
		}
	}
	
	public static synchronized Configuracion getInstance() throws SinConfiguracionException{
			
		if (instance == null)
			instance = new Configuracion();

		return instance;

	}

	public synchronized HierarchicalINIConfiguration getInifile() {
		return inifile;
	}



}
