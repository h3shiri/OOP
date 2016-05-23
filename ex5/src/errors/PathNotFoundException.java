package errors;

public class PathNotFoundException extends TypeTwoError{
	/** Magic String defining the error message */
	private static final String ERRORMSG = "One of the file's Path is invalid";
	public PathNotFoundException(){
		super(ERRORMSG);
	}
}