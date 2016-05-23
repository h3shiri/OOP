package errors;

public class MisMatchingNumberOfHeadings extends TypeTwoError{
	private static final String ERRORMSG = "A mis-matching number of Filters to Orders sections";
	public MisMatchingNumberOfHeadings(){
		super(ERRORMSG);
	}
}