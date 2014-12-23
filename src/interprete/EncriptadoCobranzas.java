package interprete;

import java.text.DecimalFormat;
import java.util.Date;

import negocio.Interprete;

public class EncriptadoCobranzas extends Interprete {
	
	private int clave;

	public EncriptadoCobranzas(String nombre) {
		super(nombre);
	}

	protected String desencriptar( String campoEncriptado ){	
		/** Codigo original en clipper
		 *   function ENCRIP
		 * 
 		 *   parameters campo, clave, lado
 		 *   private maximo, primo, base
 		 *   maximo:= Len(campo)
 		 *   primo:= 13
 		 *   base:= 19991
 		 *   private encode[maximo], campoe
 		 *   campoe:= ""
 		 *   for i:= 1 to maximo
 		 *      encode[i]:= Val(SubStr(Transform((i + 1) ^ (clave / base), ;
 		 *         "9.99999999999"), primo, primo))
 		 *   next
 		 *   for i:= 1 to Len(campo)
 		 *      if (lado)
 		 *         campoe:= campoe + Chr(Asc(SubStr(campo, i, i)) + encode[i])
 		 *      else
 		 *         campoe:= campoe + Chr(Asc(SubStr(campo, i, i)) - encode[i])
 		 *      endif
 		 *   next
 		 *   return campoe
		 */
		String campoDesencriptado = "";
		DecimalFormat formato = new DecimalFormat("#.00000000000");
		
		// Si la clave es 0 no hago nada y relleno el campo con un aviso de regitro nulo. 
		if ( this.getClave() != 0 ){
			int pos = 12; 
			int base = 19991;
			int largoCampo = campoEncriptado.length();
			int asciiShift[] = new int[ largoCampo ] ;
			// Calculo el array de desplazamientos a aplicar a cada caracter del String de entrada
			for ( int i=0 ; i< largoCampo ; i ++ ){
				String desplazamiento = formato.format( Math.pow( (double)(i+2) , (double)this.getClave()/base ) ) ;
				asciiShift[i] = Integer.valueOf( desplazamiento.substring( pos ) );
			}
			// Aplico los desplazamientos calculados al codigo ascii de cada caracter, luego convierto
			for ( int i=0 ; i < largoCampo ; i ++ ){
				campoDesencriptado = campoDesencriptado + (char)( (int)campoEncriptado.charAt(i) - asciiShift[i] ) ;
			}
		} else {
			campoDesencriptado = campoEncriptado;
		}
		return campoDesencriptado.trim();
		
	}

	@Override
	public String decodificar(String entrada) {
		
		return this.desencriptar( entrada );
		
	}

	@Override
	public int decodificar(int entrada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float decodificar(float entrada) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean convertir(String entrada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean convertir(int entrada) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date decodificarDate(String entrada) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void cargarClave(int clave) {
		this.setClave(clave);
	}
	
	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}


	
}
