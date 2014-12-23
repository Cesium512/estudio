package persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import excepciones.SinConfiguracionException;
import negocio.Campo;
import negocio.Configuracion;
import negocio.Vuelco;

public class CSV extends Vuelco {
	
	private BufferedWriter salida;
	private static Logger log = Logger.getLogger( Txt.class );

	public CSV(String nombre, String tipo) throws SinConfiguracionException {
		super(nombre, tipo);
		try {
			String nombreArchivo = Configuracion.getInstance().getInifile().getString( getNfuente() + ".csv" );
			log.debug("Incializando Vuelco CSV en archivo: " + nombreArchivo);
			salida = new BufferedWriter( new FileWriter( nombreArchivo ) );
		} catch (IOException e) {
			log.error("Error inicializando archivo de vuelco:" + e.getMessage() );
		}
		
	}

	@Override
	public void volcar(ArrayList<Campo> campos) {
		String newLine = System.getProperty("line.separator");
		try {
			String linea = "";
			
			for ( Iterator<Campo> lstCamp = campos.iterator() ; lstCamp.hasNext() ; ){
				linea += lstCamp.next().getValueS() + ";" ; 
			}
			linea = linea.substring( 0 , linea.length() -1  );
			getSalida().write( linea + newLine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cerrar() {
		try {
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}

	public BufferedWriter getSalida() {
		return salida;
	}

	public void setSalida(BufferedWriter salida) {
		this.salida = salida;
	}

	
	
}
