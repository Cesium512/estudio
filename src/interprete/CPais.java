package interprete;


public class CPais extends Copia {

	public CPais(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	public String decodificar(String entrada) {
		String salida = "";
		
		if ( entrada == null ) {
			salida = "";
		}else {
			String nacion = entrada.trim();
			if ( "Uruguay".equalsIgnoreCase( nacion ) ) {
				salida = "Uruguay" ;
			} else if ( nacion.contains( "R.O.U.".subSequence(0, 5) ) ) {
				if ( nacion.contains( "RIVERA".subSequence(0, 5) ) ) {
					salida="Fiscal=Rivera-Uruguay";
				} else {
					salida = "Uruguay" ;
				}
			} else if ( nacion.contains( "MONTE".subSequence(0, 4)  )  ) {
				salida = "Uruguay" ;
			} else {
				salida = nacion;
			}
		}

		return salida;
		
	}
	
}
