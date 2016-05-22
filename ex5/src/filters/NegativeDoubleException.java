package filters;

public class NegativeDoubleException extends FilterNumberException{
	private static final long serialVersionUID = 1L;

	public NegativeDoubleException(){
		super("A negative value has been entered inappropriately");
	}
}