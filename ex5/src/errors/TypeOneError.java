package errors;

public class TypeOneError extends Exception{
	/** A serial number that solves spesific exception */
	private static final long serialVersionUID = 1L;

	public TypeOneError(int line){
		super("Warning in line "+line);
	}
}