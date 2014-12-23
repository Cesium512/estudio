package negocio;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.log4j.Logger;

import campos.CarEncriptados;
import excepciones.SinConfiguracionException;

public class Fabrica {

	//private HierarchicalINIConfiguration configuracion;
	private static Fabrica instance;
	private HashMap<String,String> campos;
	private HashMap<String,String> interpretes;
	private HashMap<String,String> vuelcos;
	//private HashMap<String,IFuente> fuentes;
	private static Logger log = Logger.getLogger( Fabrica.class );
	
	private Fabrica() throws SinConfiguracionException {
		
		HierarchicalINIConfiguration cfg = Configuracion.getInstance().getInifile();
		
		log.debug( "Cargando lista de Campos ");
		String[] cmp = cfg.getStringArray( "fabrica.campos" );
		campos = new HashMap<String,String>();
		for ( int i=0 ; i < cmp.length ; i++ ){
			campos.put(cmp[i], "campos." + cmp[i] );
		}

		log.debug( "Cargando lista de interpretes " );
		String[] inter = cfg.getStringArray("fabrica.interpretes");
		interpretes = new HashMap<String,String>();
		for ( int i=0; i < inter.length ; i++ ){
			interpretes.put( inter[i], "interprete." + inter[i] );
		}
		
		log.debug( "Cargando lista de vuelcos " );
		String[] vlcs = cfg.getStringArray("fabrica.vuelcos");
		vuelcos= new HashMap<String,String>();
		for ( int i=0; i < vlcs.length ; i++ ) {
			vuelcos.put( vlcs[i], "persistencia." + vlcs[i] );
		}
				
	}

	public static synchronized Fabrica getInstance( ) throws SinConfiguracionException{
		
		if ( instance == null )
			instance = new Fabrica();

		return instance;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized Campo crearCampo( String nombre , String tipo, String[] configuracion ){
		
		Campo nuevoCampo = null;
		
		String claseDeCampo = campos.get( tipo );
		
		Class laClase = null ;
		try {
			
			log.debug( "Reflection de " + claseDeCampo );
			laClase = Class.forName( claseDeCampo );
			Constructor constructor = null ;
			/**
			 * Si el campo es encriptado, en la configuracion viene un parametro mas, 
			 * que es el nombre de campo que se usa como clave. 
			 * Como que esto esta mal, constructor deberia se el mismos para todos los campos
			 * hay que pasar siempre el array de configuracion y que el constructur internamente lo 
			 * parsee y extraiga los valores que le importa. 
			 */
			if ( tipo.equalsIgnoreCase( "CarEncriptados"  ) ) {
				constructor = laClase.getConstructor( new Class[] { String.class, String.class, String.class } );
				nuevoCampo = (CarEncriptados)constructor.newInstance( tipo, nombre, configuracion[3] );
			}else {
				constructor = laClase.getConstructor( new Class[] { String.class, String.class } );
				nuevoCampo = (Campo)constructor.newInstance( tipo, nombre );
			}
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error( " Error en carga de campo por Reflection " + e.getMessage() );
		}
		
		return nuevoCampo;
	}
	
	public synchronized Interprete crearInterprete( String tipo ){
		
		Interprete nuevoInter = null;
		
		String claseDeInterprete = this.interpretes.get( tipo ); 
		
		Class laClase = null;
		
		try {
			
			log.debug( "Reflection de " + claseDeInterprete );
			laClase = Class.forName( claseDeInterprete );
			Constructor constructor = laClase.getConstructor( new Class[] { String.class } );
			nuevoInter = (Interprete)constructor.newInstance( tipo );
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error( " Error en carga de interprete por Reflection " + e.getMessage() );
		}
		
		return nuevoInter;
	}
	
	public synchronized Vuelco crearVuelco( String nombreCampo, String tipo ){

		Vuelco nuevoVo = null;
		
		String claseDeVuelco = this.vuelcos.get( tipo );
		
		Class laClase = null;
		
		try {
			
			log.debug( "Reflection de " + claseDeVuelco );
			laClase  = Class.forName( claseDeVuelco );
			Constructor constructor = laClase.getConstructor( new Class[] { String.class,  String.class } );
			nuevoVo = (Vuelco)constructor.newInstance( nombreCampo, tipo ); 
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error( " Error en la carga de Vuelcos " + e.getMessage() );
		}
		
		return nuevoVo;
		
	}
	
}
