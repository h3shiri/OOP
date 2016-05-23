package errors;

public class NegativeDoubleException extends FilterNumberException{
	public NegativeDoubleException(int lineNumber){
		super(lineNumber);
	}
}