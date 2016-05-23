package errors;

public class BadFilterName extends TypeOneError{
	public BadFilterName(int lineNumber){
		super(lineNumber);
	}
}