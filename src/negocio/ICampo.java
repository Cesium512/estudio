package negocio;

import java.sql.ResultSet;
import java.util.Date;

import excepciones.CampoNuloException;

public interface ICampo {

	public void cargar( ResultSet fila, int nrofila ) throws CampoNuloException;
	
	public void interpretar();
	
	public void procesar();
	
	public String getValueS();
	
	public int getValueI();
	
	public Date getValueD();
	
	public boolean getValueB();
	
	public float getValueF();

}
