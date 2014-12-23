package interprete;

public class CBooleano extends Copia {

	public CBooleano(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}


	public boolean convertir(String entrada) {
		
		if ( "s".equalsIgnoreCase(entrada) ) {
			return true;
		} else {
			return false;
		
		}
	}
}
