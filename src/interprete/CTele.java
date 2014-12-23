package interprete;


public class CTele extends Copia {

	public CTele( String nombre) {
		super( nombre);

	}

	public String decodificar(String entrada) {
		String salida = "";
	
		if ( entrada == null ) {
			salida = "";
		} else {
			String sinSepara = entrada.replaceAll( "[^0-9]" , "" );
			if ( sinSepara.length() == 8 ) {
				salida = sinSepara;
			} else if ( sinSepara.length() == 7 && ( sinSepara.startsWith( "916") || sinSepara.startsWith( "906" ) ) ) {
				salida = "2916" + sinSepara.substring(3);
				
			} else if ( sinSepara.length() == 6 && ( sinSepara.startsWith("96") || sinSepara.startsWith( "95" ) ) ) {
				salida = "2" + sinSepara.charAt(0) + "1" + sinSepara.charAt(1) + sinSepara.substring(2) ;
				
			} else {
				salida = sinSepara;
			}
		
			
		
			
		}
		
		return salida;
	}
	
}
