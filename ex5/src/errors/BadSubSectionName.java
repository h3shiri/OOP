package errors;

public class BadSubSectionName extends TypeTwoError{
	/** Magic String defining the error message */
	private static final String ERRORMSG = "One of The sub-sections has a non valid heading";

	public BadSubSectionName(){
		super(ERRORMSG);
	}
}