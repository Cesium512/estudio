package interprete;


public class CCiudad extends Copia {

	public CCiudad(String nombre) {
		super(nombre);
	}
	
	public String decodificar(String entrada) {
		String salida = "";
		//String city = entrada.trim();
		
		if ( entrada == null ) {
			salida = "";
		}else {
			String city = entrada.trim();
			if ( "MONTEVIDEO".equalsIgnoreCase( city ) ) {
				salida = "Montevideo" ;
			} else if ( city.contains( "MDEO".subSequence(0, 3) ) ) {
				salida = "Montevideo" ;
			} else if ( city.contains( "MONTE".subSequence(0, 4)  )  ) {
				if ( city.contains( "DELESTE".subSequence( 0 , 6 )) ) {
					salida = "Montevideo_Pta.del.Este";
				} else if ( city.contains( "ROU".subSequence(0, 2)) ){
					salida = "Const=Montevideo-Uruguay";
				} else  {
					salida = "Montevideo" ;
				}
			} else {
				salida = city;
			}
		}

		return salida;
	}

}
