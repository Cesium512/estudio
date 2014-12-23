package negocio;

import java.util.Date;

public interface IInterprete {

	public String decodificar( String entrada );
	
	public int decodificar( int entrada );
	
	public float decodificar( float entrada ) ;
	
	public boolean convertir( String entrada );
	
	public boolean convertir( int entrada );
	
	public Date decodificarDate( String entrada );
	
	public void cargarClave( int clave );
	
}
