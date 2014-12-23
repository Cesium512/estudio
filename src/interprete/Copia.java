package interprete;

import java.util.Date;

import negocio.Interprete;

public class Copia extends Interprete {

	public Copia(String nombre) {
		super(nombre);
	}

	@Override
	public String decodificar(String entrada) {
		return entrada.trim();
	}

	@Override
	public int decodificar(int entrada) {
		return entrada;
	}

	@Override
	public float decodificar(float entrada) {
		return entrada;
	}

	@Override
	public boolean convertir(String entrada) {
		return false;
	}

	@Override
	public boolean convertir(int entrada) {
		return false;
	}

	@Override
	public Date decodificarDate(String entrada) {
		return null;
	}

	@Override
	public void cargarClave(int clave) {
		
	}

}
