package excepciones;

public class CampoNuloException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4024908985428917876L;

	public CampoNuloException() {

	}

	public CampoNuloException(String arg0) {
		super(arg0);

	}

	public CampoNuloException(Throwable arg0) {
		super(arg0);

	}

	public CampoNuloException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public CampoNuloException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	
	}

}
