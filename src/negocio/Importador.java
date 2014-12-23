package negocio;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import excepciones.SinConfiguracionException;

public class Importador {
	
	static Logger log = Logger.getLogger(Importador.class.getName());

	public static void main(String[] args) {

		try {
			PropertyConfigurator.configure( "log4j.properties" );
			
			log.log( Level.INFO, "Antes de cargar el ini" );
			
			Configuracion config = Configuracion.getInstance();
					
			log.log( Level.INFO , "Iniciando Aplicacion." );
			
			Aplicacion app = new Aplicacion( config );
		
			log.log( Level.INFO , "Cargando Objetos y configuracion " );
			
			app.cargar();
			
			log.log(Level.INFO , "Procesando .... " );
			
			app.procesar();
			
			log.log( Level.INFO , "Fin proceso de archivos" );
		
		} catch (SinConfiguracionException e) {
			
			log.log(Level.ERROR, "No hay archivo de configuracion de la aplicacion " );
			
		}

	}

}
